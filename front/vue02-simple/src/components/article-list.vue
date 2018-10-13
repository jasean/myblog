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
    export default {
        props: {
            listData: Array
		},
		components: {
			article
		},
		data(){
			return {
				listData: this.listData
			}
		}
    }
</script>