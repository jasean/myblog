<template>
  <div id="app">
    <!-- <img src="./assets/logo.png">
    <h1>{{ msg }}</h1>
    <h2>Essential Links</h2>
    <ul>
      <li><a href="https://vuejs.org" target="_blank">Core Docs</a></li>
      <li><a href="https://forum.vuejs.org" target="_blank">Forum</a></li>
      <li><a href="https://chat.vuejs.org" target="_blank">Community Chat</a></li>
      <li><a href="https://twitter.com/vuejs" target="_blank">Twitter</a></li>
    </ul>
    <h2>Ecosystem</h2>
    <ul>
      <li><a href="http://router.vuejs.org/" target="_blank">vue-router</a></li>
      <li><a href="http://vuex.vuejs.org/" target="_blank">vuex</a></li>
      <li><a href="http://vue-loader.vuejs.org/" target="_blank">vue-loader</a></li>
      <li><a href="https://github.com/vuejs/awesome-vue" target="_blank">awesome-vue</a></li>
    </ul>
    <router-link to='/index'>进入APP</router-link> -->
    <router-view></router-view>
  </div>
</template>

<script>
import { USER_GET_CURRENT } from './store/types'
import {mapActions} from 'vuex'

export default {
  name: 'app',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },

  methods:{
    ...mapActions([USER_GET_CURRENT])
  },

  created(){
    console.info('```````created````````````')
    // localStorage.getItem("appState") && 
    //   this.$store.replaceState(
    //     Object.assign({}, this.$store.state,JSON.parse(localStorage.getItem("appState")))
    //   );

    console.info(this.$store.state)
    console.info(this.$router)

    // window.addEventListener("beforeunload",()=>{
    //   console.info('beforeunload.....')
    //   localStorage.setItem("appState",JSON.stringify(this.$store.state))
    //   console.info(localStorage.getItem("appState"))
    // })

  },

  mounted() {

  },

  beforeRouteEnter(to, from, next){
    console.info('...to:');
    console.info(to);
    console.info('...from...');
    console.info(from);
    next(vm => {
      let {auth = true} = to.meta;
      if(auth){
        //TODO
        vm[USER_GET_CURRENT]().then(() => {
            console.info('USER_GET_CURRENT success....')
        }).catch(e => alert(e));
      }
    })
    
  }
}
</script>

<style>
  body {
    background-color: #F6F7F8;
  }
  #app {
      margin:0;
      padding:0;
      font-family: "SF Pro Display",Roboto,Noto,Arial,"PingFang SC","Hiragino Sans GB","Microsoft YaHei",sans-serif;
      font-size: 14px;
      line-height: 1.57142857;
      color: #000;
  }

  a {
		text-decoration: none;
    color:#000;
	}

</style>
