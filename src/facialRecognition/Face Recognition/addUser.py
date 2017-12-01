import os
import cv2
import numpy
from PIL import Image

# ----------------------------------------------------------------------------
# Variables
# ----------------------------------------------------------------------------

IDnumber = 1
samplesToTake = 30
picturePath = "trainingSets"
dataPath = "recognitionData"

# ----------------------------------------------------------------------------
# Functions
# ----------------------------------------------------------------------------

# Collect a training set of images by taking multiple pictures of someone's face
def createImages(ID, path):
    faceLocator = cv2.CascadeClassifier("haarcascade_frontalface_default.xml")
    camera = cv2.VideoCapture(0)

    currSample = 1

    while (currSample < samplesToTake): # Take a particular number of screenshots when a face is recognized
        status, image = camera.read()
        grayImage = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
        faces = faceLocator.detectMultiScale(grayImage, 1.3, 5)
        
        for (x, y, w, h) in faces:
            currSample = currSample + 1
            cv2.imwrite(path + "/Face." + str(ID) + "." + str(currSample) + ".jpg", grayImage[y:y+h, x:x+w]) # Save each detected face in a .jpg file
            cv2.rectangle(image, (x, y), (x + w, y + h), (0, 255, 0), 2)
            cv2.waitKey(100)
                        
        cv2.imshow("Image", image)
        cv2.waitKey(1)
        
    camera.release()
    cv2.destroyAllWindows()

# Obtain the raw data of the images and their corresponding user ID's
def getFacialData(path):
    imagePaths = [os.path.join(path, f) for f in os.listdir(path)]
    
    faces = []
    IDs = []

    for imagePath in imagePaths:
        image = Image.open(imagePath).convert("L")
        imageData = numpy.array(image, "uint8") # The raw data for each image is stored in an array of bytes
        ID = int(os.path.split(imagePath)[-1].split(".")[1])
        faces.append(imageData)
        IDs.append(ID)
        cv2.waitKey(10)
        
    return faces, numpy.array(IDs)

# ----------------------------------------------------------------------------
# Code execution
# ----------------------------------------------------------------------------

createImages(IDnumber, picturePath)
faces, IDs = getFacialData(picturePath)

recognizer = cv2.createLBPHFaceRecognizer()
recognizer.train(faces, IDs)
recognizer.save(dataPath + "/recognitionData.yml")
