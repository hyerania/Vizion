from django.http import JsonResponse
<<<<<<< HEAD
# from server.models import Lock
import RPi.GPIO as GPIO
=======
from server.models import User
# import RPi.GPIO as GPIO
>>>>>>> 79f055df5210baadd8ee720cfbfa9548daf91b3a

def turn_on_led(response):
    # code goes here
    # GPIO.setmode(GPIO.BCM)
    # GPIO.setup(18, GPIO.OUT)
    # GPIO.output(18, 1)

    # if the function execute successfully, return true, otherwise return false
    return JsonResponse({'success': True})

def turn_off_led(response):
    # code goes here
    # GPIO.setmode(GPIO.BCM)
    # GPIO.setup(18, GPIO.OUT)
    # GPIO.output(18, 0)
    shaun = User()
    shaun.first_name = "Shaun"
    shaun.last_name = "Fattig"
    shaun.identifier = "s.fattig"
    shaun.email = "shaun.fattig@tamu.edu"
    shaun.doorlock = -1
    shaun.save()
    # if the function execute successfully, return true, otherwise return false
    return JsonResponse({'success': True})
<<<<<<< HEAD

def create_lock(response):
	myAddress = "Rabi's Test Door"
	myLocation = "Front door"
	
	newLock = new Lock()
	newLock.address = myAddress
	newLock.location = myLocation

	newLock.save()
	return JsonResponse({'success': True})

def store_face(response):
	my_face = []
	
	myUser = User.objects.all().get(email="tyler@tamu.edu")
	# myUser.faceArray = my_face
	# myUser.save()
=======
>>>>>>> 79f055df5210baadd8ee720cfbfa9548daf91b3a
