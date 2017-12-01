
import RPi.GPIO as GPIO
from time import sleep


def runPrompt(promptString, maxChoice):
    validInput = False
    while True:
        a = raw_input(promptString)
        for num in range(1, maxChoice+1):
            print num
        if a.isdigit() and (int(a) in range(1, maxChoice+1)):
            return a
        else:
            print "Please choose a valid number"


def loadTests(tests):
    print "Choose a test script:"
    for i in range(len(tests)):
        print " - ", i+1
    return
	
def lock():
    #setup pins
    GPIO.setmode(GPIO.BOARD)  #physical pin numbering
    GPIO.setup(11, GPIO.OUT)  #Setup pin 11 for output mode
    my_pwm = GPIO.PWM(11, 50)  #setup pin 11 for 50 Hz signal
    my_pwm.start(8)  #Duty Cycle: approximate a signal at 5% of pin output
    #turn off voltages
    my_pwm.stop()
    GPIO.cleanup()
    
def unlock():
    #setup pins
    GPIO.setmode(GPIO.BOARD)  #physical pin numbering
    GPIO.setup(11, GPIO.OUT)  #Setup pin 11 for output mode
    my_pwm = GPIO.PWM(11, 50)  #setup pin 11 for 50 Hz signal
    my_pwm.start(5)  #Duty Cycle: approximate a signal at 5% of pin output
    #turn off voltages
    my_pwm.stop()
    GPIO.cleanup()
    
def testServo():
    #setup pins
    GPIO.setmode(GPIO.BOARD)  #physical pin numbering
    GPIO.setup(11, GPIO.OUT)  #Setup pin 11 for output mode
    my_pwm = GPIO.PWM(11, 50)  #setup pin 11 for 50 Hz signal
    #my_pwm.ChangeDutyCycle(20)  -- change duty cycle to 20%
    #my_pwm.ChangeFrequency(1000) -- change frequency to 1000Hz
    #move left
    my_pwm.start(8)  #Duty Cycle: approximate a signal at 5% of pin output
    sleep(1)
    #move right
    #my_pwm.ChangeDutyCycle(8)  #Duty Cycle: approximate a signal at 5% of pin output
    sleep(1)
    #move middle
    #my_pwm.ChangeDutyCycle(13)  #Duty Cycle: approximate a signal at 5% of pin output
    sleep(1)
    curr_pos = 8
    new_pos = 8
    while(new_pos >= 0):
        if (curr_pos > new_pos):
            for i in range(curr_pos-1, new_pos-1, -1):
                for j in range(100, -1, -1):
                    print float(i)+float(j)/100.
                    my_pwm.ChangeDutyCycle(float(i)+float(j)/100.)
                    sleep(0.001)
        else:
            for i in range(curr_pos, new_pos, 1):
                for j in range (0, 100):
                    print float(i)+float(j)/100.
                    my_pwm.ChangeDutyCycle(float(i)+float(j)/100.)
                    sleep(0.001)
        curr_pos = new_pos
        new_pos = int(raw_input("Change servo position: "))
    #turn off voltages
    my_pwm.stop()
    GPIO.cleanup()

def __main__():
    #tests = [1, 2, 3]
    #loadTests(tests)
    testServo()
    #runPrompt("Which test script? [1,"+len(tests).__str__()+"]: ", len(tests))
    return

__main__()