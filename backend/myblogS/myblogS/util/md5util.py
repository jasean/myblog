'''
Description: md5工具包
Version: 1.0
Autor: Jann
Date: 2020-09-24 21:07:34
LastEditors: Jann
LastEditTime: 2020-10-27 19:16:43
'''

from random import Random
import hashlib
from .byteutil import ByteUtils

class MD5Utils:
    '''
        每次调用md5.digest()时都必须使用重新新增一个md5实例,不能重用
    '''
        
    def _create_salt(self, length=16):
        '''
            获取由16位随机大小写字母、数字组成的salt值
        '''
        salt = ''
        chars = 'AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789'
        len_chars = len(chars) - 1
        random = Random()
        for i in range(length):
            salt += chars[random.randint(0,len_chars)]
        return salt

    def md5sum_by_salt(self, key):
        '''
            将随机生成的盐值拼接在加盐md5值的前面保存在数据库中，
           验证时可以根据数据库中的值取出salt，然后对密码做校验；
        '''
        salt = self._create_salt()
        md5_inst = hashlib.md5(salt.encode())

        md5_inst.update(key.encode())

        digest = md5_inst.hexdigest()
        print(salt,':',digest)
        return salt + digest
        
    def verify_md5(self, raw, md5_indb):
        '''
            校验给定原数据与md5值是否匹配
        '''
        salt = md5_indb[0:16]
        md5=md5_indb[16:]
        md5_inst = hashlib.md5(salt.encode())
        md5_inst.update(raw.encode())
        i_md5 = md5_inst.hexdigest()
        print(salt, ':', i_md5)
        if md5 != i_md5:
            return False
        else:
            return True
    
if __name__ == "__main__":
    md5 = MD5Utils()
    pwd='123456'
    md5_indb = md5.md5sum_by_salt(pwd)
    print('salt:',md5_indb[0:16])
    print('md5 indb:',md5_indb[16:])
    print(md5.verify_md5('123454', md5_indb))
    print(md5.verify_md5('123456', md5_indb))