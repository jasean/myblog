<style lang="less" scoped>
	.title {
		.btn {
			float: right;
		}

		margin-bottom: 10px;
		padding: 5px 10px;
	}
</style>
<template>
	<div>
		<div class="title">
			<span>欢迎使用MyBlog-MarkDown编辑器</span>
			<el-button type="danger">发布文章</el-button>
		</div>
		<div class="edit-area">
			<mavon-editor v-model="value"/>
		</div>
		
	</div>
</template>
<script>
    import { mapState } from 'vuex'
    export default {
        computed: mapState({ user: state => {
			return state.user;
		} }),
		data(){
			return {
				value: ''
			}
		}
    }
</script>