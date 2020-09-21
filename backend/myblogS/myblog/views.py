'''
Description: 
Version: 1.0
Autor: Jann
Date: 2020-09-21 19:23:53
LastEditors: Jann
LastEditTime: 2020-09-21 20:27:54
'''
from django.shortcuts import render

# Create your views here.
from rest_framework import viewsets, mixins
from rest_framework.decorators import api_view


class UserViewSet(viewsets.GenericViewSet, 
    mixins.CreateModelMixin, mixins.RetrieveModelMixin,mixins.UpdateModelMixin):
    pass


@api_view(["GET"])
def get_blog_categories():
    pass

@api_view(["GET"])
def get_persional_categories():
    pass

class ArticalViewSet(viewsets.ModelViewSet):
    pass