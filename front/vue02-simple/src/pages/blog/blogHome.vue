<style lang="scss" scoped>
	.all {
		background-color: #F6F7F8;
		text-align: left;
	}
	.text-align {
		text-align: center;
	}
	.header {
		background-color: #bbbbbb; 
	}
	.main {
		background-color: #ffffff;
	}
	.aside {
		width: 300px;
		.profile {
			padding: 16px 10px;
		}
		.grade {
			@extend .profile;
			dl {
				width: 50%;
				dt,dd {
					display: inline;
				}
			}
		}
	}
	.el-card__body {
		padding: 2px;
	}

	dl {
		float: left;
	}
	

	.clearfix::before, 
	.clearfix::after {
		display: table;
		content: '';
	}
	.clearfix::after {
		clear: both;
	}
</style>
<template>
	<div class="all">
		<el-container>
			<el-header class='header' style="height:100px;">
				<div>{{user.usernm}}的博客</div>
				<div>{{user.signature?user.signature:'这家伙很懒 ~.~'}}</div>
				<div>
					<el-button plain icon="el-icon-edit el-icon--right" @click="newArticle">写博客</el-button>
				</div>
			</el-header>
			<el-container>
				<el-aside class="aside">
					<el-card class="box-card">
						<div>头像</div>
					</el-card>
					<el-card class="box-card profile">
						<dl class="text-align">
							<dt>
								<a href="#">原创</a>
							</dt>
							<dd>
								<a href="#"><span class="count">2</span></a>
							</dd>
						</dl>
						<dl class="text-align">
							<dt>
								<a href="#">粉丝</a>
							</dt>
							<dd>
								<a href="#"><span class="count">2</span></a>
							</dd>
						</dl>
						<dl class="text-align">
							<dt>
								<a href="#">喜欢</a>
							</dt>
							<dd>
								<a href="#"><span class="count">2</span></a>
							</dd>
						</dl>
						<dl class="text-align">
							<dt>
								<a href="#">评论</a>
							</dt>
							<dd>
								<a href="#"><span class="count">2</span></a>
							</dd>
						</dl>
					</el-card>
					<el-card class="box-card grade">
						<dl class="text-align">
							<dt>
								<a href="#">等级</a>
							</dt>
							<dd>
								<a href="#"><span class="count">2</span></a>
							</dd>
						</dl>
						<dl class="text-align">
							<dt>
								<a href="#">访问</a>
							</dt>
							<dd>
								<a href="#"><span class="count">2</span></a>
							</dd>
						</dl>
						<dl class="text-align">
							<dt>
								<a href="#">积分</a>
							</dt>
							<dd>
								<a href="#"><span class="count">2</span></a>
							</dd>
						</dl>
						<dl class="text-align">
							<dt>
								<a href="#">排名</a>
							</dt>
							<dd>
								<a href="#"><span class="count">2</span></a>
							</dd>
						</dl>
					</el-card>
					<el-card class="box-card">
						 <div slot="header" class="clearfix">
							 <i class="el-icon-caret-right el-icon--right"></i>
							<span>个人分类</span>
						</div>
						<div >

						</div>
					</el-card>
					<el-card class="box-card">
						 <div slot="header" class="clearfix">
							 <i class="el-icon-caret-right el-icon--right"></i>
							<span>归档</span>
						</div>
						<div>...</div>
					</el-card>
					<el-card class="box-card">
						 <div slot="header" class="clearfix">
							 <i class="el-icon-caret-right el-icon--right"></i>
							<span>热门文章</span>
						</div>
						<div>...</div>
					</el-card>
				</el-aside>
				<el-main class='main'>
					<v-article-list :list-data="articles"></v-article-list>
				</el-main>
			</el-container>
		</el-container>
	</div>
</template>
<script>
	import { mapState } from 'vuex'
	import * as funcs from '../../funcs/getData'
    export default {
		created(){
			funcs.getArticles(this.user.userid).then(res => {
				console.info('getArticles:'+JSON.stringify(res))
				if(res.data && res.data.data){
					this.articles = res.data.data ;
				}
			}).catch(e => alert(e))
		},


        computed: mapState({ user: state => {
			return state.user;
		} }),

		data(){
			return {
				articles: []
			}
		},

		methods: {
			newArticle(){
				this.$router.push({name: 'newArticle'});
			}
		}
    }
</script>