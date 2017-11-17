import cv2
import numpy

faceLocator = cv2.CascadeClassifier("haarcascade_frontalface_default.xml")
camera = cv2.VideoCapture(0)

ID = raw_input("Enter ID Number: ")

samplesToTake = 30
currSample = 1

while (currSample < samplesToTake):
    status, image = camera.read()
    grayImage = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    faces = faceLocator.detectMultiScale(grayImage, 1.3, 5)
    
    for (x, y, w, h) in faces:
        currSample = currSample + 1
        cv2.imwrite("trainingSets/Face." + str(ID) + "." + str(currSample) + ".jpg", grayImage[y:y+h, x:x+w])
        cv2.rectangle(image, (x, y), (x + w, y + h), (0, 255, 0), 2)
        cv2.waitKey(100)
                    
    cv2.imshow("Image", image)
    cv2.waitKey(1)
    
camera.release()
cv2.destroyAllWindows()
