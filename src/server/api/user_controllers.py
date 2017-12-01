from django.http import JsonResponse
from server.models import User, Lock

def get_locks(response):
    try:
        response_user_email = response.GET['userEmail']
    except Exception:
        pass

    lock_ids = None
    locks = []
    locks_json = []
    try:
        user = User.objects.all().get(email=response_user_email)
        lock_ids = user.access_locks
    except:
        return JsonResponse()

    if lock_ids is not None:
        for lock_id in lock_ids:
            locks.append(Lock.objects.all().get(id=lock_id))

        for lock in locks:
            locks_json.append({
                'id': lock.id,
                'location': lock.location,
                'address': lock.address,
                'status': lock.status
            })


    return JsonResponse({'locks': locks_json})
