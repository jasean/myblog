import Vue from 'vue'
import * as funcs from '../funcs/getData'

export const USER_SIGNIN = 'USER_SIGNIN' //登录成功
export const USER_SIGNOUT = 'USER_SIGNOUT' //退出登录

export default {
    state: {},
    mutations: {
        [USER_SIGNIN](state, user) {
            Object.assign(state, user)
        },
        
        [USER_SIGNOUT](state) {
            Object.keys(state).forEach(k => Vue.delete(state, k))
        }
    },
    actions: {
        async [USER_SIGNIN]({commit}, user) {
            let res = await funcs.login(user);
            console.info(`user_signin response: ${JSON.stringify(res)}`);
            if(res.data.code == 0){
                commit(USER_SIGNIN, user);
            }else{
                throw new Error(res.data.msg);
            }
        },
        
        async [USER_SIGNOUT]({commit, state}) {
            let res = await funcs.logout(state.userid);
            console.info(`user_signout response: ${JSON.stringify(res)}`);
            if(res.data.code == 0){
                commit(USER_SIGNOUT);
            }else{
                throw new Error(res.data.msg);
            }
        },

    }
}