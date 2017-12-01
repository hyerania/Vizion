from django.http import JsonResponse
from server.models import User, Lock, Transaction
import RPi.GPIO as GPIO
from time import sleep


def lock_lock():
    #setup pins
    GPIO.setmode(GPIO.BOARD)  #physical pin numbering
    GPIO.setup(11, GPIO.OUT)  #Setup pin 11 for output mode
    my_pwm = GPIO.PWM(11, 50)  #setup pin 11 for 50 Hz signal
    my_pwm.start(8)  #Duty Cycle: approximate a signal at 8% of pin output
    sleep(1)
    #turn off voltages
    my_pwm.stop()
    GPIO.cleanup()
    return

def unlock_lock():
    #setup pins
    GPIO.setmode(GPIO.BOARD)  #physical pin numbering
    GPIO.setup(11, GPIO.OUT)  #Setup pin 11 for output mode
    my_pwm = GPIO.PWM(11, 50)  #setup pin 11 for 50 Hz signal
    my_pwm.start(5)  #Duty Cycle: approximate a signal at 5% of pin output
    sleep(1)
    #turn off voltages
    my_pwm.stop()
    GPIO.cleanup()
    return

def lock_door(response):
    response_user_id = response.GET['userid']
    response_lock_id = response.GET['lockid']

    user_success = False
    username = None
    lock_success = False
    lock = False
    try_compare = True

    try:
        user = User.objects.all().get(id=response_user_id)
        user_success = True
        username = user.identifier
        requested_lock = Lock.objects.all().get(id=response_lock_id)
        lock_success = True
    except User.DoesNotExist, Lock.DoesNotExist:
        try_compare = False

    if (try_compare):
        if str(requested_lock.id) in user.access_locks.split(","):
            # Only lock if door is unlocked
            if (requested_lock.state == Lock.UNLOCKED):
                lock_lock()
                requested_lock.state = Lock.LOCKED
                requested_lock.save()
            lock = True
        else:
            lock = False

    transaction = Transaction(
        user=user,
        lock=requested_lock,
        success=lock,
        access_type="lock"
    )
    transaction.save()

    return JsonResponse({
        'lock_id': response_lock_id,
        'lock_success': lock_success,
        'lock': lock,
        'user_id': response_user_id,
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
        username = user.identifier
        requested_lock = Lock.objects.all().get(id=response_lock_id)
        lock_success = True
    except User.DoesNotExist, Lock.DoesNotExist:
        try_compare = False

    if (try_compare):
        if str(requested_lock.id) in user.access_locks.split(","):
            # Only unlock if door is locked
            if (requested_lock.state == Lock.LOCKED):
                unlock_lock()
                requested_lock.state = Lock.UNLOCKED
                requested_lock.save()
            unlock = True
        else:
            unlock = False

    transaction = Transaction(
        user=user,
        lock=requested_lock,
        success=unlock,
        access_type="unlock"
    )
    transaction.save()

    return JsonResponse({
        'lock_id': response_lock_id,
        'lock_success': lock_success,
        'unlock': unlock,
        'user_id': response_user_id,
        'user_success': user_success,
        'username': username
    })

def face_lookup(response):
    try:
        response_face_id = response.GET['faceid']
        response_lock_id = response.GET['lockid']
    except Exception:
        print(response.GET)
        return JsonResponse({"error": "invalid json data"})

    user_success = False
    username = None
    lock_success = False
    success = False
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
        if str(requested_lock.id) in recognized_user.access_locks.split(","):

            if requested_lock.state == Lock.LOCKED:
                print("Test")
                unlock_lock()
                requested_lock.state = UNLOCKED
                success = True
        else:
            success = False

    requested_lock.save()

    transaction = Transaction(
        user=recognized_user,
        lock=requested_lock,
        success=success,
        access_type="auto unlock"
    )
    transaction.save()

    return JsonResponse({
        'lock_id': response_lock_id,
        'lock_success': lock_success,
        'success': success,
        'user_id': response_face_id,
        'user_success': user_success,
        'username': username
    })

def lock_door_app(request):
    try:
        response_user_email = request.GET['useremail']
        response_lock_id = request.GET['lockid']
    except Exception as ex:
        return JsonResponse({'exception': ex})

    user_success = False
    username = None
    lock_success = False
    lock = False
    try_compare = True

    try:
        user = User.objects.all().get(email=response_user_email)
        user_success = True
        username = user.identifier
        requested_lock = Lock.objects.all().get(id=response_lock_id)
        lock_success = True
    except User.DoesNotExist, Lock.DoesNotExist:
        try_compare = False

    if (try_compare):
        if str(requested_lock.id) in user.access_locks.split(","):
            # Only lock if door is unlocked
            if (requested_lock.state == Lock.UNLOCKED):
                lock_lock()
                requested_lock.state = Lock.LOCKED
                requested_lock.save()
            lock = True
        else:
            lock = False


    transaction = Transaction(
        user=user,
        lock=requested_lock,
        success=lock,
        access_type="lock"
    )
    transaction.save()

    return JsonResponse({
        'state': requested_lock.state
    })


def unlock_door_app(request):
    try:
        response_user_email = request.GET['useremail']
        response_lock_id = request.GET['lockid']
    except Exception as ex:
        return JsonResponse({'exception': ex})

    try_compare = True

    try:
        user = User.objects.all().get(email=response_user_email)
        requested_lock = Lock.objects.all().get(id=response_lock_id)
    except User.DoesNotExist, Lock.DoesNotExist:
        try_compare = False

    if (try_compare):
        if str(requested_lock.id) in user.access_locks.split(","):
            # Only unlock if door is locked
            if (requested_lock.state == Lock.LOCKED):
                unlock_lock()
                requested_lock.state = Lock.UNLOCKED
                requested_lock.save()
            unlock = True
        else:
            unlock = False

    transaction = Transaction(
        user=user,
        lock=requested_lock,
        success=unlock,
        access_type="unlock"
    )
    transaction.save()


    return JsonResponse({
        'state': requested_lock.state
    })
