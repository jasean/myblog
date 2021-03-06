'''
Description: 
Version: 1.0
Autor: Jann
Date: 2020-09-21 19:23:53
LastEditors: Jann
LastEditTime: 2020-10-10 20:04:08
'''
"""myblogS URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from django.conf.urls import url, include
from rest_framework.routers import DefaultRouter
from rest_framework.urlpatterns import format_suffix_patterns
from myblog import views


router = DefaultRouter()

#User路由
user_list = views.UserViewSet.as_view({
    'post':'create',

})
user_detail = views.UserViewSet.as_view({
    'get':'retrieve',
    'put':'update',
    'post':'login',
    'delete':'logout'
})

router.register(r'users', views.UserViewSet)
# router.register(r'^users/(?P<userid>[0-9A-Za-z_]{5,30})/', user_detail)


#article路由
router.register(r'articles', views.ArticleViewSet, basename='articles')

#博客分类
# router.register(r'^categories/', 
#     views.BlogCategoryViewSet.as_view({'get':'list'}))
router.register(r'blogCategories', views.BlogCategoryViewSet)

#个人分类
# router.register(r'^categories/(?P<userid>[0-9A-Za-z_]{5,30})/', 
#     views.PrivateCategoryViewSet.as_view({'get':'retrieve'}))
router.register(r'privateCategories', views.PrivateCategoryViewSet, basename='privateCategory')

urlpatterns = [
    url(r'^', include(router.urls)),
    url(r'^admin/', admin.site.urls),
    url(r'^api-auth/', include('rest_framework.urls', namespace='rest_framework'))

]

# urlpatterns = format_suffix_patterns(urlpatterns)