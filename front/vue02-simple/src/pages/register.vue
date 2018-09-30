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

		.register {
			color: blue;
			font-size: 50%;
			padding: 0 20px;
			margin-left: 20px;
		}
	}
</style>
<template>
	<div>
		<v-header title="注册">
			<router-link slot="left" to="/">返回</router-link>
		</v-header>
		<form class="login" v-on:submit.prevent="submit">
			<div class="line">	
				<div v-show="btn && !form.userid">用户号不能为空</div>
				<input type="text" placeholder="请输入用户号" v-model="form.userid">
			</div>
			
			<div class="line">
				<div v-show="btn && !form.password">密码不能为空</div>
				<input type="password" placeholder="请输入密码" v-model="form.password">
			</div>

			<div class="line">
				<div v-show="btn && form.password2!==form.password">两次密码输入不一致</div>
				<input type="password" placeholder="请再次输入密码" v-model="form.password2">
			</div>

			<div class="line">	
				<div v-show="btn && !form.usernm">昵称不能为空</div>
				<input type="text" placeholder="请输入用户昵称" v-model="form.usernm">
			</div>

			<div class="line">	
				<input type="text" placeholder="请输入用户签名" v-model="form.signature">
			</div>

			<button>登录</button>
			<router-link to="/register" class="register">新用户?</router-link>
		</form>
	</div>
</template>
<script>
    import { mapActions } from 'vuex'
    import { USER_REGISTER } from '../store/types'

    export default {
        data() {
			return {
				btn: false, //true 已经提交过， false没有提交过
				form: {
					userid: '',
					password: '',
					password2:'',
					usernm:'',
					signature:''
				}
			}
		},

		methods: {
            ...mapActions([USER_REGISTER]),
			submit() {
				this.btn = true
				if(!this.form.userid || !this.form.password || 
					this.form.password !== this.form.password2) {
						return
					}
				this.USER_REGISTER(this.form).then(() => {
					this.$router.replace({ name: 'login' })
				}).catch(e => {
					alert(JSON.stringify(e))
				})
			}
		}
    }
</script>