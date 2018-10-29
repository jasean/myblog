<style lang="scss" scoped>

	.container {
		margin: 10px 80px;
	}
	.bg-white {
		background-color: white;
	}
	.head {
		margin-bottom: 8px;
		-webkit-box-shadow: 0 2px 4px 0 rgba(0,0,0,0.05);
		box-shadow: 0 2px 4px 0 rgba(0,0,0,0.05);
	}

	.side {
		border-right: 1px solid #e3e3e3;

		.view-home {
			height: 56px;
    		line-height: 56px;
			padding: 0 20px;
			font-size: 14px;
			cursor: pointer;
			border-bottom: 1px solid #e3e3e3;

			:hover {
				background-color: #B9E0F5;
			}
		}

		.title {
			color: #bbb;
			height: 56px;
    		line-height: 56px;
			padding: 0 10px;
		}
	}

</style>
<template>
	<div class="container ">
		<el-container>
			<el-header class="head bg-white">
				鞋垫什么呢？
			</el-header>
			<el-container>
				<el-aside width="200px" class="side bg-white">
					<div class="view-home">
						<!-- <i class="el-icon-view"></i> -->
						查看主页
					</div>
					<div class="title">
						内容
					</div>
					<div>
						<el-menu
								default-active="1"
								class="el-menu-vertical-demo"
								@select="menuSelected">
							
							<el-menu-item index="1">
								<!-- <i class="el-icon-menu"></i> -->
								<span slot="title">文章管理</span>
							</el-menu-item>
							<el-menu-item index="2">
								<!-- <i class="el-icon-document"></i> -->
								<span slot="title">评论管理</span>
							</el-menu-item>
							<el-menu-item index="3">
								<!-- <i class="el-icon-setting"></i> -->
								<span slot="title">个人分类管理</span>
							</el-menu-item>
						</el-menu>
					</div>
				</el-aside>
				<el-main class="main bg-white">
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
		},

		data(){
			return {
			}
		},

        computed: mapState({ user: state => {
			return state.user;
		} }),


		methods: {
			menuSelected(key, keypath){
				console.info(key,keypath)
			}
		}
    }
</script>