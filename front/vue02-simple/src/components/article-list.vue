<style lang="scss" scoped>

</style>
<template>
	<div >
		<el-row v-for="(articleData, index) in listData" :key="articleData.id">
			<el-col >
				<my-article v-bind="articleData" :list-index="index" @opt-selected="handleOptClicked"></my-article>
			</el-col>
		</el-row>
	</div>
</template>
<script>
	import article from './article'
	import simpleArticle from './simple-article'
	
    export default {
		// @click.native="openArticle(articleData)"
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
				}else if(type === 'top'){
					// TODO 触发置顶操作
				}else if(type === 'edit'){
					// TODO 跳转到编辑页面

				}
			}
		}
		
    }
</script>