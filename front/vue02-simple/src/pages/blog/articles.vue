<style lang="less" scoped>
	.header {
		background-color: #bbbbbb; 
	}
	.main {
		background-color: aqua;
	}
</style>
<template>
	<div>
		<el-container>
			<el-header class='header'>Header</el-header>
			<el-main class='main'>Main</el-main>
		</el-container>
	</div>
</template>
<script>
    import { mapState } from 'vuex'
    export default {
        computed: mapState({ user: state => {
			return state.user;
		} }),
    }
</script>