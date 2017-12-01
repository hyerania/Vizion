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

