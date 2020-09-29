'''
Description: 
Version: 1.0
Autor: Jann
Date: 2020-09-21 19:23:53
LastEditors: Jann
LastEditTime: 2020-09-22 19:44:17
'''
from django.db import models

# Create your models here.

# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey has `on_delete` set to the desired behavior.
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class User(models.Model):
    userid = models.CharField(primary_key=True, max_length=32)
    password = models.CharField(max_length=64)
    usernm = models.CharField(max_length=128, blank=True, null=True)
    signature = models.CharField(max_length=128, blank=True, null=True)
    image_path = models.CharField(max_length=255, blank=True, null=True)
    level = models.IntegerField(blank=True, null=True)
    page_view = models.BigIntegerField(blank=True, null=True)
    score = models.IntegerField(blank=True, null=True)
    rank = models.IntegerField(blank=True, null=True)

    class Meta:
        managed = True
        db_table = 't_user'


        
class Article(models.Model):
    id = models.BigAutoField(primary_key=True)
    article_type = models.CharField(max_length=1, blank=True, null=True)
    owner = models.ForeignKey('User', related_name='articles', on_delete=models.CASCADE)
    # userid = models.CharField(max_length=32)
    article_title = models.CharField(max_length=64)
    article_label = models.CharField(max_length=64, blank=True, null=True)
    article_category = models.CharField(max_length=32, blank=True, null=True)
    article_private_category = models.CharField(max_length=64, blank=True, null=True)
    last_modified = models.DateTimeField(blank=True, null=True)
    create_time = models.DateTimeField(blank=True, null=True)
    article_content = models.TextField(blank=True, null=True)
    status = models.IntegerField(blank=True, null=True)
    comment_count = models.IntegerField(blank=True, null=True)


    class Meta:
        managed = True
        db_table = 't_article'


class ArticleCategory(models.Model):
    article_id = models.BigIntegerField(primary_key=True)
    category = models.CharField(max_length=32)
    # userid = models.CharField(max_length=32, blank=True, null=True)
    owner = models.ForeignKey('User', related_name='articleCategory', on_delete=models.CASCADE)

    class Meta:
        managed = True
        db_table = 't_article_category'
        unique_together = (('article_id', 'category'),)


class ArticleStat(models.Model):
    article_id = models.BigIntegerField(primary_key=True)
    read_count = models.BigIntegerField()

    class Meta:
        managed = True
        db_table = 't_article_stat'


class BlogCategory(models.Model):
    id = models.IntegerField(primary_key=True)
    category = models.CharField(max_length=64)

    class Meta:
        managed = True
        db_table = 't_blog_category'


class PrivateCategory(models.Model):
    # userid = models.CharField(primary_key=True, max_length=32)
    owner = models.ForeignKey('User', related_name='privateCategories', on_delete=models.CASCADE)
    category = models.CharField(max_length=64)

    class Meta:
        managed = True
        db_table = 't_private_category'
        unique_together = (('owner', 'category'),)


