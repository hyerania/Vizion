import os
import cv2
import numpy
from PIL import Image

def getFaces(path):
    imagePaths = [os.path.join(path, f) for f in os.listdir(path)]
    
    faces = []
    IDs = []

    for imagePath in imagePaths:
        image = Image.open(imagePath).convert("L");
        imageData = numpy.array(image, "uint8")
        ID = int(os.path.split(imagePath)[-1].split(".")[1])
        faces.append(imageData)
        IDs.append(ID)
        cv2.waitKey(10)
        
    return faces, numpy.array(IDs)

# -------------------------------------------------------------------------

recognizer = cv2.createLBPHFaceRecognizer()
path = "trainingSets"

faces, IDs = getFaces(path)
recognizer.train(faces, IDs)
recognizer.save("recognitionData/recognitionData.yml")
cv2.destroyAllWindows()
