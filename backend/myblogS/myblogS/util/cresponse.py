'''
Description: 自定义response类：自定义返回数据类型格式
Version: 1.0
Autor: Jann
Date: 2020-10-28 18:39:38
LastEditors: Jann
LastEditTime: 2020-10-28 19:33:37
'''

from rest_framework.serializers import Serializer
from rest_framework.response import Response

class CustomResponse(Response):
    def __init__(self, data=None, code=0, msg='Success', status=None,
                 template_name=None, headers=None,
                 exception=False, content_type=None):
        """
        Alters the init arguments slightly.
        For example, drop 'template_name', and instead use 'data'.

        Setting 'renderer' and 'media_type' will typically be deferred,
        For example being set automatically by the `APIView`.
        """
        super().__init__(None, status=status)

        if isinstance(data, Serializer):
            msg = (
                'You passed a Serializer instance as data, but '
                'probably meant to pass serialized `.data` or '
                '`.error`. representation.'
            )
            raise AssertionError(msg)

        self.data = {"code": code, "msg": msg, "data": data}
        self.template_name = template_name
        self.exception = exception
        self.content_type = content_type

        if headers:
            for name, value in headers.items():
                self[name] = value