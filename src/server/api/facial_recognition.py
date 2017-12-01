from django.http import JsonResponse
from server.models import User, Lock

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
            # unlock_lock(requested_lock.id)
            unlock = True
        else:
            unlock = False

    return JsonResponse({
        'lock_id': response_lock_id,
        'lock_success': lock_success,
        'unlock': unlock,
        'user_id': response_face_id,
        'user_success': user_success,
        'username': username
    })
