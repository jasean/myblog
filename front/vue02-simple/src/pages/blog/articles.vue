<style lang="scss" scoped>
	

	.filter-sort-box {
		padding: 0 40px;
		border-bottom: 1px solid #e3e3e3;
		dl {
			margin-left: auto;
			font-size: 14px;
			a {
				text-decoration:none;
				color: #000;
			}
			.active {
				color: red;
			}
			dt {
				color: #999;
			}
		}
	}

	.content-box {
		padding: 0 8px;
	}

	.d-flex {
		display: flex !important;
		align-items: center !important;
	}
	.inline {
		display: inline-block;
	}
</style>
<template>
	<div>
		<div class="filter-sort-box d-flex">
			<el-checkbox v-model="filterOriginal">只看原创</el-checkbox>
			<dl class="d-flex inline">
				<dt>排序：</dt>
				<dd><a href="javascript:void(0);" :class="orderBy==='createTime'?'active':''" target="_self" @click="orderSelected('createTime')">默认</a></dd>
				<dd><a href="javascript:void(0);" :class="orderBy==='lastModified'?'active':''" target="_self" @click="orderSelected('lastModified')">按更新时间</a></dd>
				<dd><a href="javascript:void(0);" :class="orderBy==='readCount'?'active':'' " target="_self" @click="orderSelected('readCount')">按访问量</a></dd>
			</dl>
		</div>
		<div class="content-box">
			<v-article-list :list-data="articles"></v-article-list>
		</div>
	</div>
</template>
<script>
	import { mapState } from 'vuex'
	import * as funcs from '../../funcs/getData'
    export default {
		created(){
			this.fetchArticles(this.orderBy);
		},

		methods: {
			fetchArticles(orderby){
				funcs.getArticles(this.user.userid, orderby).then(res => {
					console.info('getArticles:'+JSON.stringify(res))
					if(res.data && res.data.data){
						this.articles = res.data.data ;
					}
				}).catch(e => alert(e))
			},

			orderSelected(orderBy){
				this.orderBy = orderBy;
				this.$router.push({name: 'blogHome', query: {orderBy}})
			}
		},
        computed: mapState({ user: state => {
			return state.user;
		} }),

		data(){
			return {
				articles: [],
				filterOriginal: false,
				orderBy: 'createTime'
			}
		},

		beforeRouteUpdate(to, from, next){
			this.orderBy = to.query.orderBy;
			this.fetchArticles(this.orderBy);
		}
    }
</script>