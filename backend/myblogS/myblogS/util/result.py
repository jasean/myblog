'''
Description: 
Version: 1.0
Autor: Jann
Date: 2020-10-13 19:54:39
LastEditors: Jann
LastEditTime: 2020-10-13 20:28:26
'''
from .resultcode import ResultCode

class Result:
    
    def __init__(self, code, msg):
        self.code = code
        self.msg = msg

    @classmethod
    def get(cls, code, msg):
        if code is None:
            return cls(ResultCode.success.value, ResultCode.success.name)
        else:
            return cls(code, msg)