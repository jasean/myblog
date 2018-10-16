<style lang="less" scoped>

	.header {
		background-color: #bbbbbb; 
	}
	.main {
		background-color: aqua;
	}
	.aside {
		background-color: pink;
		width: 300px;
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
	<div>
		<el-container>
			<el-header class='header' style="height:100px;">
				<div>{{user.usernm}}的博客</div>
				<div>{{user.signature?user.signature:'这家伙很懒 ~.~'}}</div>
				<div>
					<el-button plain icon="el-icon-edit el-icon--right">写博客</el-button>
				</div>
			</el-header>
			<el-container>
				<el-aside class="aside">
					<el-card class="box-card">
						<div>头像</div>
					</el-card>
					<el-card class="box-card">
						<div>原创</div>
						<div>粉丝</div>
						<div>喜欢</div>
						<div>评论</div>
					</el-card>
					<el-card class="box-card">
						<div>等级</div>
						<div>访问</div>
						<div>积分排名</div>
						<div>粉丝</div>
					</el-card>
					<el-card class="box-card">
						 <div slot="header" class="clearfix">
							 <i class="el-icon-caret-right el-icon--right"></i>
							<span>个人分类</span>
						</div>
						<div>...</div>
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
				<el-main class='main'>Main</el-main>
			</el-container>
		</el-container>
	</div>
</template>
<script>
    import { mapState } from 'vuex'
    export default {
        computed: mapState({ user: state => {
			return state.user;
		} }),
    }
</script>