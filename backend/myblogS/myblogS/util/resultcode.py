'''
Description: 后台返回状态码
Version: 1.0
Autor: Jann
Date: 2020-10-13 20:17:39
LastEditors: Jann
LastEditTime: 2020-10-13 20:25:32
'''

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

