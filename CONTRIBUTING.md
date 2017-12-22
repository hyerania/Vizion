# Contributing Guide

### Requirements
- [Python 2.7](https://www.python.org/downloads/)
- [Git](https://git-scm.com/downloads)
- Virtualenv `pip install virtualenv` (pip should come with python)

Note, these three will need to be in your PATH variable in order to work in any directory.

### Clone the repo



Go to the directory you want the repo to be in. Clone the repo:
```
git clone https://github.tamu.edu/russell-pier/vizion.git
```

### Set up the backend
Go into repo `cd vizion`

Activate virtualenv `source venv/bin/activate` on Linux and 
```
cd venvw/Scripts
activate.bat
cd ../..
```
for Windows (I think.) The windows environment may not have all the required packages. We didn't use it very much.

Go into the source code folder `cd src` 

Apply migrations
```
python manage.py migrate
```

Preload some data into the db.
```
python manage.py loaddata data
```

Run the server
```
python manage.py runserver
``` 
Go to the link that the command line now says is hosting the server to verify that it is running locally. If you want to be able to access it from a different computer change the command to 
```
python manage.py runserver [your_machines_ip_addr]:8000
```
Note you might have to disable the firewall on your machine (Google is helpful).

### OpenCV
Further helpful instructions to install open CV on Linux can be found at [OpenCV library documentation](https://docs.opencv.org/2.4/doc/tutorials/introduction/linux_install/linux_install.html)
1. Install it and either include it into the venv/lib/python2.7/stat-package s or pull our openCV scripts out and put them in a file that has access to the library
2. There are two make methods: recognizer.py and addUser.py
    1. recognizer.py : This method watches through the camera and sends a request to the server when it detects a face it recognizes
    2. addUser.py : This method trains openCV to recognize a particular person. To use this method, follow these steps:
        1. Run the method
        2. Enter an ID for the face about to be trained (hint: use one that is in the database. Preloaded users are id 1-4. By default,            only user #4 can open the lock.)
        3. Camera pop-up will open. Move your face around a little in front of the camera to train.
        4. It helps to have at least two faces trained considering the recognition software becomes much more precise.
