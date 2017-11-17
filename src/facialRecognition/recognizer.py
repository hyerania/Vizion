import cv2
import numpy

recognitionScrictness = 50

faceLocator = cv2.CascadeClassifier("haarcascade_frontalface_default.xml")
camera = cv2.VideoCapture(0)

recognizer = cv2.createLBPHFaceRecognizer()
recognizer.load("recognitionData\\recognitionData.yml")

ID = 0
font = cv2.cv.InitFont(cv2.cv.CV_FONT_HERSHEY_DUPLEX, 1, 1, 0, 2)

while (True):
    status, image = camera.read()
    grayImage = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    faces = faceLocator.detectMultiScale(grayImage, 1.3, 5)
    
    for (x, y, w, h) in faces:
        cv2.rectangle(image, (x, y), (x + w, y + h), (0, 255, 0), 2)
        ID, uncertainty = recognizer.predict(grayImage[y:y+h, x:x+w])

        if (uncertainty < recognitionScrictness):
            if (ID == 1):
                name = "Tyler"
            elif (ID == 4):
                name = "Shaun"
            else:
                name = "Unknown"
        else:
            name = "Unknown"

        cv2.cv.PutText(cv2.cv.fromarray(image), name, (x, y + h), font, (0, 0, 0))

    cv2.imshow("Image", image)
    if (cv2.waitKey(1) == ord("q")):
        break
    
camera.release()
cv2.destroyAllWindows()
