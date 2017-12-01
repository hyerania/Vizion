from django.http import JsonResponse
from server.models import User, Lock

def logon(request):
    logon = False

    try:
        requested_user_email = response.GET['userEmail']
        requested_user_password = response.GET['userPassword']
    except Exception:
        print(response.GET)
        return JsonResponse({"error": "unable to authenticate"})

    try:
        user = User.objects.all().get(email=requested_user_email)
    except User.DoesNotExist, Lock.DoesNotExist:
        try_compare = False

        if (user.password == requested_user_password)
            logon = True

    return JsonResponse({'login': logon})
