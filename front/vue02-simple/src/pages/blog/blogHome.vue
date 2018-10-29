<style lang="scss" scoped>

	*{
		margin:0;
		padding:0;
	}

	.header {
		background-color: #232E3C;
		
	}
	.container {
		padding: 24px 0 0;
		margin: 0 auto;
		-webkit-box-sizing: border-box;
		box-sizing: border-box;
		width: 100%;
		display: inline-block;
	}
	
	h1.title-blog {
		display: block;
		font-weight: bold;
		font-size: 24px;
		color: white;
		max-width: 720px;
		
	}

	.description {
		display: block;
		color: #b8b8b8;
	}

	.title-box {
		float: left;
		margin-left: 50px;
	}

	.tool-box {
		float: right;
		margin-right: 50px;

		button {
			padding: 8px;
		}
	}

	.aside{
		width: 300px;
		background-color:white;
	}
	.aside-box{
		margin-bottom: 8px;
		background-color: #fff;
		-webkit-box-shadow: 0 2px 4px 0 rgba(0,0,0,0.05);
		box-shadow: 0 2px 4px 0 rgba(0,0,0,0.05);
	}
	.intro {
		padding:16px;
		border-bottom: 1px solid #e3e3e3;
	}
	.d-flex {
		display: flex !important;
	}
	.justify-content-center{
		justify-content: center !important;
	}
	.flex-column{
		flex-direction: column !important;
	}
	.avatar{
		display: block;
		width: 48px;
		height: 48px;
		border-radius: 50%;
	}
	.usernm {
		margin-left:8px;
	}
	
	.stat {
		padding: 16px 10px;
		border-bottom: 1px solid #e3e3e3;
	}
	.item-tiling>* {
		width: 100%;
	}
	.text-center {
		text-align: center;
	}
	dl {
		display:block;
	}
	.count {
		font-weight: 700;
	}
	
	.grade {
		margin-top: -8px;
		padding: 16px;
	}
	.grade dl {
		margin-top: 8px;
		float: left;
		width: 50%;
	}
	.grade dl dt {
		display: inline;
		width: 42px;
	}
	.grade dl dd {
		display: inline;
	}
	.clearfix::after {
		content: " ";
		display: block;
		height: 0;
		visibility: hidden;
		clear: both;
	}
	
	
	div.aside-box {
		margin-bottom: 8px;
		background-color: #fff;
		-webkit-box-shadow: 0 2px 4px 0 rgba(0,0,0,0.05);
		box-shadow: 0 2px 4px 0 rgba(0,0,0,0.05);
		word-wrap: break-word;
	}
	.aside-title {
		padding: 16px 16px 0;
		font-size: 14px;
		color: #333;
	}
	.aside-title::before {
		display: inline-block;
		margin-right: 8px;
		content: '';
		width: 4px;
		height: 22px;
		vertical-align: -6px;
		background-color: #ca0c16;
	}
	div.aside-content {
		padding: 16px;
		overflow: hidden;
	}
	ul {
		margin-top: -8px;
		list-style: none;
		display: block;
	}
	ul li {
		margin-top: 8px;
		display: list-item;
	}
	ul li a {
		display:block;
		cursor: pointer;
		text-decoration: none;
	}
	ul li a .title {
		width: 84%;
		float: left;
	}
	ul li a span.count {
		font-size: 12px;
		color: #858585;
	}
	.oneline {
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap !important;
	}
	.float-right {
		float: right !important;
	}

	.main {
		background-color: #ffffff;
		margin-right: 80px;
	}
	

	.el-main {
		padding: 0;
		margin-left: 8px;
	}
	

	// .clearfix::before, 
	// .clearfix::after {
	// 	display: table;
	// 	content: '';
	// }
	// .clearfix::after {
	// 	clear: both;
	// }
</style>
<template>
	<div class="all">
		<el-container>
			<el-header class='header container' style="height:100px;">
				<div class="title-box">
					<h1 class="title-blog">{{user.usernm}}的博客</h1>
					<p class="description">{{user.signature?user.signature:'这家伙很懒 ~.~'}}</p>
				</div>
				<div class="tool-box">
					<el-button plain icon="el-icon-edit el-icon--right" 
						@click="newArticle">写博客</el-button>
					<el-button plain icon="el-icon-setting el-icon--right" 
						@click="manageArticle">管理博客</el-button>
				</div>
			</el-header>
			<el-container>
				<el-aside>
					<div class="aside">
						<div class="aside-box">
							<div class="intro d-flex" >
								<div class="d-flex justify-content-center flex-column">
									<a href="">
										<img src="../../assets/img.jpg" alt="jann" class="avatar"/>
									</a>
								</div>
								<div class="usernm d-flex justify-content-center flex-column">
									<p><a href="">jann</a></p>
								</div>
								
							</div>
							<div class="stat d-flex item-tiling">
								<dl class="text-center" title="3">
									<dt><a href="https://blog.csdn.net/jan8705_?t=1">原创</a></dt>
									<dd><a href="https://blog.csdn.net/jan8705_?t=1"><span class="count">3</span></a></dd>
								</dl>
								<dl class="text-center" title="3">
									<dt><a href="https://blog.csdn.net/jan8705_?t=1">粉丝</a></dt>
									<dd><a href="https://blog.csdn.net/jan8705_?t=1"><span class="count">3</span></a></dd>
								</dl>
								<dl class="text-center" title="3">
									<dt><a href="https://blog.csdn.net/jan8705_?t=1">喜欢</a></dt>
									<dd><a href="https://blog.csdn.net/jan8705_?t=1"><span class="count">3</span></a></dd>
								</dl>
								<dl class="text-center" title="3">
									<dt><a href="https://blog.csdn.net/jan8705_?t=1">评论</a></dt>
									<dd><a href="https://blog.csdn.net/jan8705_?t=1"><span class="count">3</span></a></dd>
								</dl>
							</div>
							<div class="grade clearfix">
								<dl>
									<dt>等级：</dt>
									<dd>
										<a href="#" title="1级,点击查看等级说明" target="_blank">
											<img src="../../assets/blog1.png" alt="" class=""/>
										</a>
									</dd>
								</dl>
								<dl>
									<dt>访问：</dt>
									<dd title="4606">
										4606            </dd>
								</dl>
								<dl>
									<dt>积分：</dt>
									<dd title="79">
										79            </dd>
								</dl>
								<dl title="1562182">
									<dt>排名：</dt>
									<dd>156万+</dd>
								</dl>
							</div>
						</div>
						
						
						
						<div  class="aside-box">
							<h3 class="aside-title">个人分类</h3>
							<div class="aside-content">
								<ul>
									<li v-for="stats in categoryStats" :key="stats.category">
										<a class="clearfix" href="#">
											<span class="title oneline">{{stats.category}}</span>
											<span class="count float-right">{{stats.count}}篇</span>
										</a>
									</li>
								</ul>
							</div>
						</div>
						<div class="aside-box">
							<h3 class="aside-title">归档</h3>
							<div class="aside-content">
								<ul>
									<li v-for="stats in dateStats" :key="stats.create_date">
										<a class="clearfix" href="#">
											<span class="title oneline">{{stats.create_date}}</span>
											<span class="count float-right">{{stats.count}}篇</span>
										</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
					
				</el-aside>
				<el-main class='main'>
					<router-view></router-view>
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
			Promise.all([
				funcs.getStatsByCategory(this.user.userid),
				funcs.getStatsByDate(this.user.userid)
			]).then(([res1, res2]) => {
				console.info(res1)
				console.info(res2)
				if(res1.data && res1.data.data){
					this.categoryStats = res1.data.data;
				}
				if(res2.data && res2.data.data){
					this.dateStats = res2.data.data;
				}
			}).catch(e => alert(e));

			/* funcs.getStatsByCategory(this.user.userid).then(res => {
				if(res.data && res.data.data){
					this.categoryStats = res.data.data;
				}
			}).catch(e => alert(e)) */
		},

		data(){
			return {
				categoryStats: [],
				dateStats: []
			}
		},

        computed: mapState({ user: state => {
			return state.user;
		} }),


		methods: {
			newArticle(){
				this.$router.push({name: 'newArticle'});
			},

			manageArticle(){
				this.$router.push({name: 'manageArticle'});
			}
		}
    }
</script>