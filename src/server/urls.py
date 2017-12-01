"""vizion URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.11/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url
from django.contrib import admin
from django.http import HttpResponse

from api.led import turn_on_led, turn_off_led
from api.facial_recognition import face_lookup
from api.user_controllers import get_locks
from authenticate import logon

urlpatterns = [
    url(r'^admin/', admin.site.urls),
    url(r'^turnonled/$', turn_on_led, name="Turn On LED"),
    url(r'^turnoffled/$', turn_off_led, name="Turn Off LED"),
    url(r'^facelookup/$', face_lookup, name="Face Lookup"),
    url(r'^logon/$', logon, name="Log In"),
    url(r'^getLocks/$', get_locks, name="Get Locks")
]
