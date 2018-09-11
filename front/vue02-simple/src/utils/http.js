import axios from 'axios'

const inst = axios.create();
inst.defaults.baseURL = 'http://localhost:9080/';
inst.defaults.timeout = 5000;
// axios.defaults.withCredentials = true;

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
            inst.post(url, data).then(response => {
                resolve(response);
            }).catch(e => reject(e));
        })
    }

    put(url, data = {}){
        return new Promise((resolve, reject) => {
            inst.put(url, data).then(response => {
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