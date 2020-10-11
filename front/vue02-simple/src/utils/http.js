/*
 * @Description: 
 * @Version: 1.0
 * @Autor: Jann
 * @Date: 2020-04-02 10:58:13
 * @LastEditors: Jann
 * @LastEditTime: 2020-10-10 20:28:33
 */
import axios from 'axios'
import router from '../config/router'
import {REQ_RESULT_401} from '../constants/constants'
import qs from 'qs'

const inst = axios.create();
inst.defaults.baseURL = 'http://localhost:8000/';
inst.defaults.timeout = 5000;
inst.defaults.withCredentials = true;

inst.interceptors.response.use(
    response => response,
    error => {
        console.error('response error....')
        console.error(JSON.stringify(error))
        if(error.response){
            switch(error.response.status){
                case 401:
                    //TODO 跳转
                    router.replace({path: '/login'})
                    return REQ_RESULT_401;
            }
        }
        return Promise.reject(error);
    })

class Http {
    constructor(){}

    get(url, params = {}){
        return new Promise((resolve, reject) => {
            inst.get(url, {
                params: params
            }).then(response => {
                resolve(response);
            }).catch(e => reject(e));
        })
        
    }

    post(url, data = {}){
        return new Promise((resolve, reject) => {
            inst.post(url, qs.stringify(data)).then(response => {
                resolve(response);
            }).catch(e => reject(e));
        })
    }

    put(url, data = {}){
        return new Promise((resolve, reject) => {
            inst.put(url, qs.stringify(data)).then(response => {
                resolve(response);
            }).catch(e => reject(e));
        })
    }

    delete(url, params = {}){
        return new Promise((resolve, reject) => {
            inst.delete(url, {
                params: params
            }).then(response => {
                resolve(response);
            }).catch(e => reject(e));
        })
    }
}

const httpInst = new Http();
export default httpInst;