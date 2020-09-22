'''
Description: 
Version: 1.0
Autor: Jann
Date: 2020-09-21 20:29:01
LastEditors: Jann
LastEditTime: 2020-09-22 20:10:25
'''

from rest_framework import serializers
from .models import User, Article, ArticleCategory,PrivateCategory,BlogCategory

class UserSerializer(serializers.HyperlinkedModelSerializer):
    
    class Meta:
        model = User
        fields = '__all__'

class ArticleSerializer(serializers.HyperlinkedModelSerializer):
    owner = serializers.ReadOnlyField(source='owner.userid')
    class Meta:
        model = Article
        fields = ('url','owner', 'article_type', 'article_label', 'article_category', 'article_private_category',
            'last_modified', 'create_time', 'article_content', 'status', 'comment_count')

class ArticleCategorySerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = ArticleCategory
        fields = '__all__'

class BlogCategorySerializer(serializers.HyperlinkedModelSerializer):

    class Meta:
        model = BlogCategory
        fields = '__all__'

class PrivateCategorySerializer(serializers.HyperlinkedModelSerializer):

    owner = serializers.ReadOnlyField(source='owner.userid')
    class Meta:
        model = PrivateCategory
        fields = ('url', 'owner', 'category')