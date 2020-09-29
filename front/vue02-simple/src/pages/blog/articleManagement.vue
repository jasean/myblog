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
					<span slot="title">全部({{allNum}})</span>
				</el-menu-item>
				<el-menu-item index="2">
					<!-- <i class="el-icon-document"></i> -->
					<span slot="title">已发表({{publishedNum}})</span>
				</el-menu-item>
				<el-menu-item index="3">
					<!-- <i class="el-icon-setting"></i> -->
					<span slot="title">私密({{privateNum}})</span>
				</el-menu-item>
				<el-menu-item index="4">
					<!-- <i class="el-icon-setting"></i> -->
					<span slot="title">草稿箱({{draftNum}})</span>
				</el-menu-item>
				<el-menu-item index="5">
					<i class="el-icon-delete"></i>
					<span slot="title">回收站({{trashNum}})</span>
				</el-menu-item>
			</el-menu>
		</div>
		<div class="search-box">
			<el-form :inline="true" class="demo-form-inline" size="small" v-model="searchForm">
				<el-form-item label="发布时间">
					<!-- <el-col :span="11">
						<el-form-item> -->
							<el-select  placeholder="不限" style="width:75px;" v-model="searchForm.startDate">
								<el-option label="测试" value="0"></el-option>
							</el-select>
						<!-- </el-form-item>
					</el-col>
					<el-col :span="11">
						<el-form-item> -->
							<el-select  placeholder="不限" style="width:75px;" v-model="searchForm.endDate">
								<el-option label="测试" value="0"></el-option>
							</el-select>
						<!-- </el-form-item>
					</el-col> -->
				</el-form-item>
				<el-form-item label="">
					<el-select  placeholder="不限类型" style="width:100px;" v-model="searchForm.type">
						<el-option label="原创" value="0"></el-option>
						<el-option label="转载" value="1"></el-option>
						<el-option label="翻译" value="2"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="">
					<el-select  placeholder="选择个人分类" style="width:200px;" v-model="searchForm.category">
						<el-option label="测试" value="0"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="">
					<el-input placeholder="仅支持标题关键词搜索" style="width:200px;" v-model="searchForm.content"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="onSearch">搜索</el-button>
				</el-form-item>
			</el-form>
		</div>
		<div class="post-list">
			<v-post-list :list-data="articles" ></v-post-list>
		</div>
	</div>
</template>
<script>
	import { mapState } from 'vuex'
	import * as funcs from '../../funcs/getData'
    export default {
		created(){
			funcs.getAllArticles(this.user.userid, true).then(res => {
				if(res.data && res.data.data){
					this.allArticlesContainTrash = res.data.data;
					this.filter(this.allArticlesContainTrash);
					this.articles = this.allArticles;
				}
			}).catch(e => alert(e))
		},

		data(){
			return {
				allArticlesContainTrash: [],
				articles: [],
				allArticles: [],
				publishedArticles: [],
				privateArticles: [],
				draftArticles: [],
				trashArticles: [],
				allNum: 0,
				publishedNum: 0,
				privateNum: 0,
				draftNum: 0,
				trashNum: 0,

				searchForm: {
					startDate: '',
					endDate: '',
					type: '',
					category: '',
					content: ''
				}
			}
		},

        computed: mapState({ user: state => {
			return state.user;
		} }),


		methods: {
			menuSelected(key, keypath){
				console.info(key,keypath)
				switch(key){
					case '1':
						this.articles = this.allArticles;
						break;
					case '2':
						this.articles = this.publishedArticles;
						break;
					case '3':
						this.articles = this.privateArticles;
						break;
					case '4':
						this.articles = this.draftArticles;
						break;
					case '5':
						this.articles = this.trashArticles;
						break;
				}
			},

			onSearch(){

			},


			/** 归类处理 */
			filter(articles){
				let articlesIndexes = [[], [], [], []];
				articles.forEach(article => {
					let status = article.status;
					articlesIndexes[status].push(article);
				});

				this.publishedArticles = articlesIndexes[0];
				this.publishedNum = articlesIndexes[0].length;

				this.privateArticles = articlesIndexes[1];
				this.privateNum = articlesIndexes[1].length;

				this.draftArticles = articlesIndexes[2];
				this.draftNum = articlesIndexes[2].length;

				this.trashArticles = articlesIndexes[3];
				this.trashNum = articlesIndexes[3].length;

				this.allArticles = [...articlesIndexes[0], ...articlesIndexes[1], ...articlesIndexes[2]];
				this.allNum = this.allArticles.length;
			}

		},

		
    }
</script>