import http from '../utils/http'

//登陆
export const login = (user) => http.post('/users/sessions',user);

//退出登陆
export const logout = (userid) => http.delete('/users/sessions/' + userid);

//注册
export const register = (user) => http.post('/users', user);

//获取当前用户信息
export const getCurrentUserInfo = () => http.get('/users/data/cur');