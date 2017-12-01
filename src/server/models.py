# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models
from django.core.mail import send_mail
from django.contrib.auth.models import PermissionsMixin
from django.contrib.auth.base_user import AbstractBaseUser
from django.utils.translation import ugettext_lazy as _

# Create your models here.
class User(AbstractBaseUser, PermissionsMixin, models.Model):
    identifier = models.CharField(max_length=40, unique=True)
    first_name = models.CharField(_('first name'), max_length=30, blank=True)
    last_name = models.CharField(_('last name'), max_length=30, blank=True)
    email = models.EmailField(_('email address'), unique=True)

    face_data = models.TextField(db_column='data', blank=True)

    # doorlock is ownership of a door
    doorlock = models.IntegerField(blank=True)
    # access_locks is what they can access
    access_locks = models.CommaSeparatedIntegerField(blank=True, max_length=255,)

    USERNAME_FIELD = 'identifier'

class Lock(models.Model):
    address = models.CharField(max_length=100)
    location = models.CharField(max_length=100)

class Transaction(models.Model):
    date_time = models.DateTimeField(auto_now=True)
    user = models.ForeignKey('User', on_delete=models.CASCADE, related_name='transactions')
    lock = models.ForeignKey('Lock', on_delete=models.CASCADE, related_name='transactions')
    success = models.BooleanField()
    access_type = models.SmallIntegerField
