'''
Description: 
Version: 1.0
Autor: Jann
Date: 2020-10-13 19:54:39
LastEditors: Jann
LastEditTime: 2020-10-14 21:17:47
'''

# from .resultcode import ResultCode

return_code_dict = {
    'success': [0,'success'],
    'user_not_logged_in': [20001, '用户未登陆'],
    'user_login_error': [20002, '用户登陆错误'],
    'user_account_forbidden': [20003, '账户被限制登陆'],
    'user_not_exist': [20004, '用户不存在'],
    'user_has_existed' : [20005, '用户已存在'],
    'user_error_register': [20006, '用户注册出错'],
    'system_inner_error': [40001, '系统内部错误']
}
# class ResultCode(type):
#     def __new__(cls, clsname, bases, dict):
#         for key, value in return_code_dict.items():
#             dict[key] = 

from enum import Enum

class ResultCode(Enum):
    '''
        后台返回错误码定义
    '''
    success = 0
    
    # 用户错误：20001-29999
    user_not_logged_in = 20001
    user_login_error = 20002
    user_account_forbidden = 20003
    user_not_exist = 20004
    user_has_existed = 20005
    user_error_register = 20006

    # 业务错误：30001-39999

    # 系统错误：40001-49999
    system_inner_error = 40001

    # 数据错误：50001-599999
    result_data_none = 50001
    data_is_wrong = 50002
    data_already_existed = 50003

    # 权限错误：70001-79999
    permission_no_access = 70001


class Result:
    '''
        后台返回数据结构
    '''
    def __init__(self, resultcode: ResultCode, data):
        self.resultcode = resultcode
        self.data = data

    @classmethod
    def get(cls, resultcode: ResultCode=None, *, data=None):
        ''' 工厂方法 '''
        if resultcode is None:
            return cls(ResultCode.success, data) 
        return cls(resultcode, data)

    def set_code(self, resultcode):
        ''' 设置返回码 '''
        self.resultcode = resultcode
        return self

    def set_data(self, data):
        ''' 设置返回数据'''
        self.data = data
        return self
    
    def build(self):
        ''' builder方法'''
        return {
            'code': return_code_dict[self.resultcode.name][0],
            'msg': return_code_dict[self.resultcode.name][1],
            'data': self.data
        }