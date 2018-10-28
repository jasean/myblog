<style lang="scss" scoped>
	.main {
		text-align: left;
	}
	.titleArea {
		margin-bottom: 10px;
		padding: 5px 10px;

		div {
			margin: 10px 5px;

			span {
				margin-right: 10px;
			}
		}

		.separator {
			border-top: 1px solid rgb(190, 187, 187);
			margin-top: 30px;
		}

	}

	.original-flag {
        background-color: rgba(64,158,255,.1);
        display: inline-block;
        padding: 0 5px;
        height: 22px;
        line-height: 20px;
        font-size: 12px;
        color: #409eff;
        border-radius: 50%;
        box-sizing: border-box;
        border: 1px solid rgba(64,158,255,.2);
        white-space: nowrap;
    }
	.title {
		font-weight: bold;
		font-size: large;
	}
	.btn-edit {
		float: right;
	}
</style>
<template>
	<div class="main">
		<div class="titleArea">
			<div>
				<span class="original-flag">{{articleTypeDesc}}</span>
				<span class="title">{{articleTitle}}</span>
			</div>
			<div>
				<span>{{formatDate}}</span>
				<span>{{user.userid}}</span>
				<span>阅读数：{{readCount}}</span>
				<el-button type="text" class="btn-edit" @click="editClick">编辑</el-button>
			</div>
			<div class="separator"></div>
		</div>
		<div class="edit-area">
			<mavon-editor 
				v-model="articleContent" 
				:subfield="false" 
				:editable="false" 
				defaultOpen="preview"
				:toolbarsFlag="false"/>
		</div>
		
	</div>
</template>
<script>
	import { mapState } from 'vuex';
	import DateUtils from '../../utils/dateUtils'
	import { ARTICLE_TYPE } from '../../constants/dict';
	import * as funcs from '../../funcs/getData' 
    export default {
		mounted(){
			let articleId = this.$route.params.articleId;
			this.articleId = articleId;
			funcs.getArticle(this.user.userid, articleId).then(res => {
				console.info('...getArticle...')
				console.info(res)
				if(res.data && res.data.data){
					let article = res.data.data;
					this.articleTitle = article.articleTitle;
					this.articleType = article.articleType;
					this.createTime = article.createTime;
					this.readCount = article.readCount;
					this.articleContent = article.articleContent;
				}
			}).catch(e => this.$alert(e));
		},
		
		data(){
			return {
				articleId: '',
				articleTitle: '',
				articleType: '',
				createTime: 0,
				readCount: 0,
				articleContent: ''
			}
		},

		methods: {
			editClick(){
				this.$router.push({name: 'editArticle', params: {articleId: this.articleId}});
			}
		},

		computed: {
			...mapState({
				user: state => {
					return state.user;
				}
			}),
			articleTypeDesc: function() {
				return this.articleType?
					ARTICLE_TYPE[this.articleType].slice(0,1):'';
			},

            formatDate: function() {
				return DateUtils.getDateFromMilliseconds(this.createTime)
			}
		},
    }
</script>