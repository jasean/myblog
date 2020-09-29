import Vue from 'vue'
import Vuex from 'vuex'
import user from './user'
import createPersistedState from 'vuex-persistedstate'
import createLogger from 'vuex/dist/logger'

Vue.use(Vuex)

/**
 * 
 * 
 */
const debug = process.env.NODE_ENV !== 'production';
export default new Vuex.Store({
    strict: debug,//在非生产环境下，使用严格模式
    modules: {
        user
    },
    /**
     * 默认存储到localStorage
     */
    plugins: debug?[createLogger(), createPersistedState()]:[createPersistedState()],
    /**
     * 存储到sessionStorage
     * plugins: [createPersistedState({
            storage: window.sessionStorage
        })]
        也可以使用cookie

        指定需要持久化的state
        plugins: [createPersistedState({
            storage: window.sessionStorage,
            reducer(val) {
                return {
                // 只储存state中的assessmentData
                assessmentData: val.assessmentData
                }
            }
        })]
     */


})