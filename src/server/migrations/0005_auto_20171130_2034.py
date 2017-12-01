# -*- coding: utf-8 -*-
# Generated by Django 1.11.7 on 2017-12-01 02:34
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('server', '0004_auto_20171129_0506'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='user',
            name='face_data',
        ),
        migrations.AddField(
            model_name='user',
            name='state',
            field=models.CharField(default='unlock', max_length=40),
            preserve_default=False,
        ),
    ]
