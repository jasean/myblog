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
export const getArticles = (userid, orderBy) => http.get(`/articles/${userid}`,{orderBy});

//获取文章详情
export const getArticle = (userid, articleid) => http.get(`/articles/${userid}/${articleid}`);

//获取按个人分类维度的统计信息
export const getStatsByCategory = (userid) => http.get(`/articles/stats/category/${userid}`);

//获取按创建年月维度的统计信息
export const getStatsByDate = (userid) => http.get(`/articles/stats/date/${userid}`);

//获取按文章状态维度的统计信息
export const getStatsByStatus = (userid) => http.get(`/articles/stats/status/${userid}`);