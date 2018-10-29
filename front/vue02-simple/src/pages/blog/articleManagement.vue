<style lang="scss" scoped>

	.container {
		margin: 10px 0;
	}

	.search-box {
		margin-top: 16px;
		padding: 16px;
		font-size: 14px;
		background-color: #f2f5f7;

		.el-form--inline .el-form-item {
			vertical-align: middle;
			margin-bottom: 0;
		}
	}

	

</style>
<template>
	<div class="container ">
		<div>
			<el-menu
					default-active="1"
					class="el-menu-vertical-demo"
					mode="horizontal"
					@select="menuSelected">
				
				<el-menu-item index="1">
					<!-- <i class="el-icon-menu"></i> -->
					<span slot="title">全部</span>
				</el-menu-item>
				<el-menu-item index="2">
					<!-- <i class="el-icon-document"></i> -->
					<span slot="title">已发表</span>
				</el-menu-item>
				<el-menu-item index="3">
					<!-- <i class="el-icon-setting"></i> -->
					<span slot="title">私密</span>
				</el-menu-item>
				<el-menu-item index="4">
					<!-- <i class="el-icon-setting"></i> -->
					<span slot="title">草稿箱</span>
				</el-menu-item>
				<el-menu-item index="5">
					<i class="el-icon-delete"></i>
					<span slot="title">回收站</span>
				</el-menu-item>
			</el-menu>
		</div>
		<div class="search-box">
			<el-form :inline="true" class="demo-form-inline" size="small">
				<el-form-item label="发布时间">
					<!-- <el-col :span="11">
						<el-form-item> -->
							<el-select  placeholder="不限" style="width:75px;">
							</el-select>
						<!-- </el-form-item>
					</el-col>
					<el-col :span="11">
						<el-form-item> -->
							<el-select  placeholder="不限" style="width:75px;">
							</el-select>
						<!-- </el-form-item>
					</el-col> -->
				</el-form-item>
				<el-form-item label="">
					<el-select  placeholder="不限类型" style="width:100px;">
						<el-option label="原创" value="0"></el-option>
						<el-option label="转载" value="1"></el-option>
						<el-option label="翻译" value="2"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="">
					<el-select  placeholder="选择个人分类" style="width:200px;">
					</el-select>
				</el-form-item>
				<el-form-item label="">
					<el-input placeholder="仅支持标题关键词搜索" style="width:200px;"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="onSearch">搜索</el-button>
				</el-form-item>
			</el-form>
		</div>
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
			},

			onSearch(){

			}
		}
    }
</script>