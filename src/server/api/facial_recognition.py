from django.http import JsonResponse
from server.models import User, Lock

def logon(response):
    response_user_id = response.GET['userid']
    response_lock_id = response.GET['lockid']


def lock_lock():
    #setup pins
    GPIO.setmode(GPIO.BOARD)  #physical pin numbering
    GPIO.setup(11, GPIO.OUT)  #Setup pin 11 for output mode
    my_pwm = GPIO.PWM(11, 50)  #setup pin 11 for 50 Hz signal
    my_pwm.start(8)  #Duty Cycle: approximate a signal at 5% of pin output
    #turn off voltages
    my_pwm.stop()
    GPIO.cleanup()

def unlock_lock():
    #setup pins
    GPIO.setmode(GPIO.BOARD)  #physical pin numbering
    GPIO.setup(11, GPIO.OUT)  #Setup pin 11 for output mode
    my_pwm = GPIO.PWM(11, 50)  #setup pin 11 for 50 Hz signal
    my_pwm.start(5)  #Duty Cycle: approximate a signal at 5% of pin output
    #turn off voltages
    my_pwm.stop()
    GPIO.cleanup()

def lock_door(response):
    response_user_id = response.GET['userid']
    response_lock_id = response.GET['lockid']

    user_success = False
    username = None
    lock_success = False
    unlock = False
    try_compare = True

    try:
        user = User.objects.all().get(id=response_user_id)
        user_success = True
        username = recognized_user.identifier
        requested_lock = Lock.objects.all().get(id=response_lock_id)
        lock_success = True
    except User.DoesNotExist, Lock.DoesNotExist:
        try_compare = False

    if (try_compare):
        if (requested_lock.id) in user.access_locks.split(","):
            # Only lock if door is unlocked
            if (requested_lock.state == Lock.UNLOCKED):
                lock_lock(requested_lock.id)
            lock = True
        else:
            lock = False

    # TODO: make transaction
    return JsonResponse({
        'lock_id': response_lock_id,
        'lock_success': lock_success,
        'lock': lock,
        'user_id': response_face_id,
        'user_success': user_success,
        'username': username
    })

def unlock_door(response):
    response_user_id = response.GET['userid']
    response_lock_id = response.GET['lockid']

    user_success = False
    username = None
    lock_success = False
    unlock = False
    try_compare = True

    try:
        user = User.objects.all().get(id=response_user_id)
        user_success = True
        username = recognized_user.identifier
        requested_lock = Lock.objects.all().get(id=response_lock_id)
        lock_success = True
    except User.DoesNotExist, Lock.DoesNotExist:
        try_compare = False

    if (try_compare):
        if (requested_lock.id) in user.access_locks.split(","):
            # Only unlock if door is locked
            if (requested_lock.state == Lock.LOCKED):
                unlock_lock(requested_lock.id)
            unlock = True
        else:
            unlock = False

    # TODO: make transaction
    return JsonResponse({
        'lock_id': response_lock_id,
        'lock_success': lock_success,
        'unlock': unlock,
        'user_id': response_face_id,
        'user_success': user_success,
        'username': username
    })

def face_lookup(response):
    response_face_id = response.GET['faceid']
    response_lock_id = response.GET['lockid']

    user_success = False
    username = None
    lock_success = False
    unlock = False
    try_compare = True

    try:
        recognized_user = User.objects.all().get(id=response_face_id)
        user_success = True
        username = recognized_user.identifier
        requested_lock = Lock.objects.all().get(id=response_lock_id)
        lock_success = True
    except User.DoesNotExist, Lock.DoesNotExist:
        try_compare = False

    if (try_compare):
        if (requested_lock.id) in recognized_user.access_locks.split(","):
            unlock_lock(requested_lock.id)
            unlock = True
        else:
            unlock = False

    # TODO: make transaction
    return JsonResponse({
        'lock_id': response_lock_id,
        'lock_success': lock_success,
        'unlock': unlock,
        'user_id': response_face_id,
        'user_success': user_success,
        'username': username
    })
