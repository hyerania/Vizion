import cv2
import numpy
import time
import requests

# ----------------------------------------------------------------------------
# Variables
# ----------------------------------------------------------------------------

host = "192.168.1.13:8080"
extension = "facelookup"
url = "http://" + host + "/" + extension + "/"

dataPath = "recognitionData"

lockID = '1'
requestTimeInterval = 5
recognitionStrictness = 50

# ----------------------------------------------------------------------------
# Functions
# ----------------------------------------------------------------------------

# Ongoing function that attempts to recognize faces
# If the faces are recognized, their corresponding facial ID is sent to the server
def recognizeFaces():
    faceLocator = cv2.CascadeClassifier("haarcascade_frontalface_default.xml")
    camera = cv2.VideoCapture(0)

    recognizer = cv2.createLBPHFaceRecognizer()
    recognizer.load(dataPath + "\\recognitionData.yml")

    faceID = 0
    IDmargin = 50
    font = cv2.cv.InitFont(cv2.cv.CV_FONT_HERSHEY_DUPLEX, 1, 1, 0, 2)

    while (True):
        status, image = camera.read()
        grayImage = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
        faces = faceLocator.detectMultiScale(grayImage, 1.3, 5) # When a face is located

        for (x, y, w, h) in faces:
            cv2.rectangle(image, (x, y), (x + w, y + h), (0, 255, 0), 2)
            faceID, uncertainty = recognizer.predict(grayImage[y:y+h, x:x+w]) # Make a guess the correspondent ID of the face

            if (uncertainty < recognitionStrictness): # Make sure that the recognizer is confident in its predecting before assuming who it is
                # r = requests.get(url, params = {'lockid': lockID, 'faceid': faceID})
                # print(r.content) # ************** TEMPORARY **************
                # time.sleep(requestTimeInterval)
                x = 1 # ************** TEMPORARY **************

            cv2.cv.PutText(cv2.cv.fromarray(image), str(faceID), (IDmargin, IDmargin), font, (0, 0, 0)) # ************** TEMPORARY **************

        cv2.imshow("Image", image)
        if (cv2.waitKey(1) == ord("q")):
            break

    camera.release()
    cv2.destroyAllWindows()

# ----------------------------------------------------------------------------
# Code execution
# ----------------------------------------------------------------------------

recognizeFaces()
