<style lang="less" scoped>
</style>
<template>
	<div>
		<el-row v-for="articleData in listData" :key="articleData.id">
			<el-col>
				<article v-bind="articleData"></article>
			</el-col>
		</el-row>
	</div>
</template>
<script>
	import article from './article'
	import simpleArticle from './simple-article'
    export default {
        props: {
			listData: Array,
			/** 是否为简单列表 */
			simple: Boolean
		},
		components: {
			article: this.simple?simpleArticle:article
		},
		data(){
			return {
				listData: this.listData,
				simple: this.simple
			}
		}
    }
</script>