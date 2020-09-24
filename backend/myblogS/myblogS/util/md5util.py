'''
Description: md5工具包
Version: 1.0
Autor: Jann
Date: 2020-09-24 21:07:34
LastEditors: Jann
LastEditTime: 2020-09-24 21:46:52
'''

from random import Random
import hashlib
from byteutil import ByteUtils

class MD5Utils:
    
    md5_inst = hashlib.md5();
    
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

    def md5_salt(self, key):
        '''
            将随机生成的盐值拼接在加盐md5值的前面保存在数据库中，
           验证时可以根据数据库中的值取出salt，然后对密码做校验；
        '''
        salt = self._create_salt()
        self.md5_inst.update(salt.encode())

        self.md5_inst.update(key.encode())

        digest = self.md5_inst.hexdigest()

        return salt + digest
        
        
if __name__ == "__main__":
    md5 = MD5Utils()
    print(md5.md5_salt('123456'))