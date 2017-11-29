from django.http import JsonResponse
from server.models import User
# import RPi.GPIO as GPIO

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
