# -*- coding: utf-8 -*-
# Generated by Django 1.11.7 on 2017-12-01 02:42
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('server', '0005_auto_20171130_2034'),
    ]

    operations = [
        migrations.AlterField(
            model_name='user',
            name='state',
            field=models.CharField(default='unlocked', max_length=40),
        ),
    ]