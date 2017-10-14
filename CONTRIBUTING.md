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

Activate virtualenv `source venv/bin/activate` on Linux and `venv/bin/activate.bat` for Windows (I think.)

Go into the source code folder `cd src` 

Run the server
```
python manage.py runserver
``` 
Go to the link that the command line now says is hosting the server to verify that it is running locally. If you want to be able to access it from a different computer change the command to `python manage.py runserver [your_machines_ip_addr]:8000` Note you might have to disable the firewall on your machine (Google is helpful).

### Make changes to the code
Since there is four of us, the best thing to do is each work on our own branch. Find a quick git tutorial to learn what these different terms mean. It can be somewhat complicated, but is EXTREMLY useful and good to know.

Make sure you are in the repo folder and make a branch `git branch [myname]-work`

Switch to that branch `git checkout [myname]-work`

Make changes to the code.

Track the changes in git `git add .`

Commit the changes to the branch `git commit -m "[A short description of what changes you made]"`

Switch back to your master branch `git checkout master`

Merge your working branch into your master `git merge [myname]-work`

Push the changes you just merged into your local master to the remote master branch `git push`

All done!

Next time you want to add some code start by pulling the most recent version (in case someone else added stuff) make sure you are on the master branch and run `git pull`

Then either make a new branch `git branch [branchname]` or merge master into your work branch `git checkout [myname]-work` `git merge master`

Hint: you don't type the []
