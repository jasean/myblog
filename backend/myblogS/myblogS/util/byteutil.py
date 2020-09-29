'''
Description: 字节操作工具包
Version: 1.0
Autor: Jann
Date: 2020-09-24 20:16:11
LastEditors: Jann
LastEditTime: 2020-09-24 21:08:26
'''

class ByteUtils:

    HEX_NUMS_STR="0123456789ABCDEF"
    
    @staticmethod
    def byte_hex(bs):
        ''' 
            将字节数组转换为十六进制字符串 
              ...
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
              ...

        '''
        ha = []
        for b in bs:
            h = hex(b)
            hs = str(h)[2:]
            if len(hs) == 1:
                hs = '0' + hs
            ha.append(hs)
        return ''.join(ha)


    @staticmethod
    def hex_byte(hstr):
        '''
            将十六进制字符串转化为字节数组
        '''
        result = []
        bl = len(hstr)/2
        for i in range(bl):
            pos = i*2
            result[i] = HEX_NUMS_STR.index(hstr[pos]) << 4 | HEX_NUMS_STR.index(hstr[pos+1])
            
        return result
        