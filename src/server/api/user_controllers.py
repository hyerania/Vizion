from django.http import JsonResponse
from server.models import User, Lock

def get_locks(response):
    try:
        response_user_email = response.GET['userEmail']
    except Exception:
        return JsonResponse({'error': ex})

    lock_ids = None
    locks = []
    locks_json = []
    try:
        user = User.objects.all().get(email=response_user_email)
        lock_ids = user.access_locks.split(",")
    except:
        return JsonResponse()

    if lock_ids is not None:
        for lock_id in lock_ids:
            locks.append(Lock.objects.all().get(id=int(lock_id)))

        for lock in locks:
            locks_json.append({
                'id': lock.id,
                'location': lock.location,
                'address': lock.address,
                'state': lock.state
            })


    return JsonResponse({'locks': locks_json})

def add_lock(response):
    try:
        response_user_email = response.GET['userEmail']
        response_location = response.GET['location']
        response_address = response.GET['address']

        user = User.objects.all().get(email=response_user_email)
    except Exception as ex:
        return JsonResponse({'error': ex})



    new_lock = Lock(
            location=response_location,
            address=response_address,
        )

    user.access_locks.append(new_lock.id)

    print(user.access_locks)

    new_lock.save()
    user.save()

    return JsonResponse({'success': True})
