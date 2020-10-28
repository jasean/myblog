/*
 * @Description: 
 * @Version: 1.0
 * @Autor: Jann
 * @Date: 2018-09-22 18:32:43
 * @LastEditors: Jann
 * @LastEditTime: 2020-10-09 06:43:17
 */
import http from '../utils/http'

//登陆
export const login = (user) => http.post('/users/'+user.userid+'/login/', user);

//退出登陆
export const logout = (userid) => http.delete('/users/' + userid+ '/logout/');

//注册
export const register = (user) => http.post('/users/', user);

//获取当前用户信息
export const getCurrentUserInfo = (userid) => http.get(`/users/${userid}/`);

//获取个人分类
export const getBlogPersonalCategories = (userid) => http.get('/privateCategories/' + userid);

//获取博客分类
export const getBlogCategories = () => http.get("/blogCategories/");

//发布文章
export const publishArticle = (article) => http.post("/articles/", article);

//修改文章
export const modifyArticle = (article) => http.put(`/articles/${article.id}`, article);

//获取文章列表
export const getArticles = (userid, orderBy) => http.get('/articles/',{orderBy,userid});

//获取文章详情
export const getArticle = (userid, articleid) => http.get(`/articles/${articleid}`,{userid});

//获取按个人分类维度的统计信息
export const getStatsByCategory = (userid) => http.get('/articles/statsByPrivateCategory/',{userid});

//获取按创建年月维度的统计信息
export const getStatsByDate = (userid) => http.get('/articles/statsByCreateDate/',{userid});

//获取按文章状态维度的统计信息
export const getStatsByStatus = (userid) => http.get('’/articles/statsByStatus/',{userid});

//获取所有文章列表，可以包含垃圾箱里的文章
export const getAllArticles = (userid, containTrash) => http.get('/articles/', {trash: containTrash,userid});
