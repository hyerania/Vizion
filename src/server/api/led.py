from django.http import HttpResponse
import RPi.GPIO as GPIO

def turn_on_led(response):
    # code goes here
	GPIO.setmode(GPIO.BCM)
	GPIO.setup(18, GPIO.OUT)
	GPIO.output(18, 1)

    # if the function execute successfully, return true, otherwise return false
    return HttpResponse({'success': False})
