<style lang="less" scoped>
	.login {
		padding: 50px;
		text-align: center;
		.line {
			padding: 5px;
			input {
				padding: 0 10px;
				line-height: 28px;
			}
		}
		button {
			padding: 0 20px;
			margin-top: 20px;
			line-height: 28px;
		}
	}
</style>
<template>
	<div>
		<v-header title="登录">
			<router-link slot="left" to="/">返回</router-link>
		</v-header>
		<form class="login" v-on:submit.prevent="submit">
			<div class="line">	
				<div v-show="btn && !form.userid">id不能为空</div>
				<input type="text" placeholder="请输入用户号" v-model="form.userid">
			</div>
			<div class="line">
				<div v-show="btn && !form.password">用户名不能为空</div>
				<input type="password" placeholder="请输入密码" v-model="form.password">
			</div>
			<button>登录</button>
		</form>
	</div>
</template>
<script>
    import { mapActions } from 'vuex'
    import { USER_SIGNIN } from '../store/types'

    export default {
        data() {
			return {
				btn: false, //true 已经提交过， false没有提交过
				form: {
					userid: '',
					password: '',
				}
			}
		},

		methods: {
            ...mapActions([USER_SIGNIN]),
			submit() {
				this.btn = true
				if(!this.form.userid || !this.form.password) return
				this.USER_SIGNIN(this.form).then(() => {
					this.$router.replace({ path: '/home' })
				}).catch(e => alert(e))
			}
		}
    }
</script>