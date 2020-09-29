<style lang="scss" scoped>
	.article-list{
		margin-bottom: 1px;
    	padding: 12px 18px;
	}
</style>
<template>
	<div class="article-list">
		<el-row v-for="(articleData, index) in listData" :key="articleData.id">
			<el-col >
				<my-article 
					v-bind="articleData" 
					:list-index="index" 
					@click="openArticle(articleData)"
					@opt-selected="handleOptClicked">
				</my-article>
			</el-col>
		</el-row>
	</div>
</template>
<script>
	import article from './post-list-item'
	// @click.native="openArticle(articleData)"
    export default {
		// 
        props: {
			listData: Array,
			/** 是否为简单列表 */
			/* simple: Boolean */
			itemTop: Function,
			itemEdit: Function,
			itemDelete: Function,
		},
		components: {
			myArticle: /* this.simple?simpleArticle: */article
		},
		methods: {
			openArticle(article){
				console.info("...openArticle...");
				this.$router.push({name: 'articleDetail', params: {id: article.id}})
			},

			handleOptClicked(data){
				console.info(`...handleOptClicked...`)
				let {type, listIndex} = data;
				if(type === 'delete'){
					listData.splice(listIndex, 1);
					//TODO 数据库操作
				}else if(type === 'top'){
					// TODO 触发置顶操作
				}else if(type === 'view'){
					// TODO 跳转到查看页面
					this.$router.push({name: 'articleDetail', params: {id: this.listData[listIndex].id}});
				}else if(type === 'forbid'){
					//TODO 禁止评论
				}
			}
		}
		
    }
</script>