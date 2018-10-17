import http from '../utils/http'

//登陆
export const login = (user) => http.post('/users/sessions',user);

//退出登陆
export const logout = (userid) => http.delete('/users/sessions/' + userid);

//注册
export const register = (user) => http.post('/users', user);

//获取当前用户信息
export const getCurrentUserInfo = () => http.get('/users/data/cur');

//获取个人分类
export const getBlogPersonalCategories = (userid) => http.get('/blogs/personal-categories/' + userid);

//获取博客分类
export const getBlogCategories = () => http.get("/blogs/categories");

//发布文章
export const publishArticle = (article) => http.post("/articles/" + article.userid, article);

//获取文章列表
export const getArticles = (userid) => http.get("/articles/" + userid);