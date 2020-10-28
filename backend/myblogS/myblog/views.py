'''
Description: 
Version: 1.0
Autor: Jann
Date: 2020-09-21 19:23:53
LastEditors: Jann
LastEditTime: 2020-10-28 21:30:01
'''
from django.shortcuts import render, get_object_or_404, Http404

# Create your views here.
from rest_framework import viewsets
from rest_framework.decorators import api_view, action
from rest_framework.response import Response
from rest_framework import status
from .models import User, Article, BlogCategory, PrivateCategory
from .serializers import UserSerializer, ArticleSerializer, BlogCategorySerializer, PrivateCategorySerializer
from django.db.models import Count
from myblogS.util.result import Result, ResultCode
from myblogS.util.md5util import MD5Utils
from myblogS.util import mixins

class UserViewSet(viewsets.GenericViewSet, 
    mixins.CreateModelMixin, mixins.RetrieveModelMixin, mixins.UpdateModelMixin):
    queryset = User.objects.all()
    serializer_class = UserSerializer

    def perform_create(self, serializer):
        '''
        新增用户时需要写入密文信息
        '''
        password = self.request.data.get('password')
        md5 = MD5Utils()
        password = md5.md5sum_by_salt(password)
        
        serializer.save(password=password)
    
    # TODO 登录处理逻辑
    @action(methods=["post"], detail=True)
    def login(self, request, pk):
        user = None
        try:
            user = get_object_or_404(User, pk=pk)
            digest_db = user.password
            password = request.data.get('password')
            md5 = MD5Utils()
            result = md5.verify_md5(password, digest_db)
            if result == False:
                return Response(Result.get(ResultCode.user_passwd_error).build())

            request.session['user'] = user.defer('password')

        except Http404:
            return Response(Result.get(ResultCode.user_not_exist).build())
        
        return Response(Result.get().set_data(user.userid).build())

    # TODO 登出处理逻辑
    @action(methods=["delete"], detail=True)
    def logout(self, request, pk):
        user = None
        try:
            user = get_object_or_404(User, pk=pk)
            del request.session['user']
        except Http404:
            return Response(Result.get(ResultCode.user_not_exist).build())

        return Response(Result.get().set_data(user.userid).build())

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
