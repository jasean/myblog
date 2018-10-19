<style lang="less" scoped>
</style>
<template>
	<div>
		<el-row v-for="articleData in listData" :key="articleData.id">
			<el-col>
				<my-article v-bind="articleData"></my-article>
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
			/* simple: Boolean */
			itemOpen: Function,
			itemTop: Function,
			itemEdit: Function,
			itemDelete: Function,
		},
		components: {
			myArticle: /* this.simple?simpleArticle: */article
		},
		
    }
</script>