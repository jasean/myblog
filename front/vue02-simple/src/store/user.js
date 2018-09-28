import Vue from 'vue'
import * as funcs from '../funcs/getData'
import { getLocalStore } from '../utils/lstore'

import {USER_SIGNIN, USER_SIGNOUT, USER_GET_CURRENT} from './types'

export default {
    //TODO 初始化如何做？
    state: /* getLocalStore('appState.user') ||  */{},
    mutations: {
        [USER_SIGNIN](state, user) {
            Object.assign(state, user)
        },

        [USER_SIGNOUT](state) {
            Object.keys(state).forEach(k => Vue.delete(state, k))
        },

        [USER_GET_CURRENT](state, user){
            // state.userid = user.userid;
            Object.assign(state, user)
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

        async [USER_GET_CURRENT]({commit}){
            let res;
            try {
                res = await funcs.getCurrentUserInfo();
                console.info(`user_signout response: ${JSON.stringify(res)}`);
            }catch(e){
                
            }
            
            if(res.data){
                if(res.data.code == 0){
                    commit(USER_GET_CURRENT, res.data.data);
                }else{
                    throw new Error(res.data.msg);
                }
            }
            
        }

    }
}