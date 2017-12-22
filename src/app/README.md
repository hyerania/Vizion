# Vizion Android Application

The following instructions describe how to use the Android application and the features that are provided
with this application along with facial detection.
1. After cloning the repository from GitHub, you will find the Android application within the src/app/VizionApp folder. This project can be opened through Android Studio.
2. Once the project has installed all the dependencies, the application can be run on the emulator or an actual Android phone.
    1. If installing the application on the emulator, the configurations used for testing purposes was an API 24.4 for Android 4.4.
    2. If installing the application on an Android application, install the Google USB Driver
from the SDK Manager located under Android Studio > Tools > Android > SDK
Manager . For testing purposes, we used a Google Nexus 5 with Android version 5.0.1.
3. Once the application builds and runs, the login screen will appear with Vizion as the application name. Log in if you already have an account to this application or create a new user.
4. After logging in, the application will load the main dashboard that displays the various door locks that you have registered. Each door lock consists of the name of the door you gave it, the address the door is referring to, and the status of the door.
5. If further door locks need to be added, this can be done so through the Add Door button found on the bottom right.
6. When you select a door lock from the table view presented in the main dashboard, a new window will show displaying all the users capable of accessing that same specific door.
7. If you would like to add further users to have access to this specific door, then you can add them through the Add User button found on the bottom right.
8. In the Access activity page that is presented, the lower left side button allows you to unlock or lock the door.
   1. If the status shows Locked , this refers to the door being in a locked state. If the button is pressed then the door will unlock.
   2. If the status shows Unlocked , this refers to the door being in an unlocked state. If the button is pressed then the door will lock.
