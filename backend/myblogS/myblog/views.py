'''
Description: 
Version: 1.0
Autor: Jann
Date: 2020-09-21 19:23:53
LastEditors: Jann
LastEditTime: 2020-10-11 14:10:33
'''
from django.shortcuts import render, get_object_or_404, Http404

# Create your views here.
from rest_framework import viewsets, mixins
from rest_framework.decorators import api_view, action
from rest_framework.response import Response
from rest_framework import status
from .models import User, Article, BlogCategory, PrivateCategory
from .serializers import UserSerializer, ArticleSerializer, BlogCategorySerializer, PrivateCategorySerializer
from django.db.models import Count

class UserViewSet(viewsets.GenericViewSet, 
    mixins.CreateModelMixin, mixins.RetrieveModelMixin,mixins.UpdateModelMixin):
    queryset = User.objects.all()
    serializer_class = UserSerializer

    # TODO 登录处理逻辑
    @action(methods=["post"], detail=True)
    def login(self, request, pk):
        user = None
        try:
            user = get_object_or_404(User, pk=pk)
        except Http404:
            return Response('用户不存在')
        
        return Response(user.userid)

    # TODO 登出处理逻辑
    @action(methods=["delete"], detail=True)
    def logout(self, request, pk):
        user = User.objects.get(pk=pk)
        return Response(user.userid)

# @api_view(["GET"])
# def get_blog_categories():
#     pass

class BlogCategoryViewSet(viewsets.GenericViewSet, mixins.ListModelMixin, mixins.RetrieveModelMixin):
    queryset = BlogCategory.objects.all()
    serializer_class = BlogCategorySerializer

# @api_view(["GET"])
# def get_persional_categories():
#     pass

class PrivateCategoryViewSet(viewsets.GenericViewSet, mixins.ListModelMixin, mixins.RetrieveModelMixin):
    # queryset = PrivateCategory.objects.all()
    serializer_class = PrivateCategorySerializer

    def get_queryset(self):
        # query_params
        userid = self.request.userid
        return PrivateCategory.objects.filter(userid=userid)

class ArticleViewSet(viewsets.ModelViewSet):
    # queryset = Article.objects.all()
    serializer_class = ArticleSerializer

    def get_queryset(self):
        userid = self.request.userid
        return Article.objects.filter(userid=userid)

    # 按个人分类统计
    @action(methods=["get"], detail=False)
    def statsByPrivateCategory(self, request):
        userid = request.query_params.userid
        return Response(ArticleCategory.objects.filter(userid=userid)
                        .values('category').annotate(total=Count('article_id')))

    # 按创建日期统计
    @action(methods=["get"], detail=False)
    def statsByCreateDate(self, request):
        userid = request.query_params.userid
        return Response(Article.objects.filter(userid=userid)
                        .values('create_time__year').annotate(total=Count('id')))
       

    # 按文章状态统计
    @action(methods=["get"], detail=False)
    def statsByStatus(self, request):
        userid = request.query_params.userid
        return Response(Article.objects.filter(userid=userid)
                        .values('status').annotate(total=Count('id')))
