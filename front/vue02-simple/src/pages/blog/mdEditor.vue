<style lang="scss" scoped>
	.main {
		text-align: left;
	}
	.title {
		.btn {
			margin-left: 20px;
		}

		margin-bottom: 10px;
		padding: 5px 10px;
	}
	.el-checkbox+.el-checkbox {
		margin-left: 0;
	}
	.el-checkbox {
		margin-right: 40px;
		
	}
	.el-checkbox-group {
		border: 1px solid rgb(216, 214, 214);
		padding: 5px 10px;
		background-color: #FBFBFB;
	}
</style>
<template>
	<div class="main">
		<div class="title">
			<el-row>
				<el-col :span="20">
					<el-input
						placeholder="输入文章标题"
						v-model="articleTitle"
						clearable>
					</el-input>
				</el-col>
				<el-button type="danger" class="btn" @click="dialogVisible = true">发布文章</el-button>
			</el-row>
		</div>
		<div class="edit-area">
			<mavon-editor 
				v-model="articleContent"/>
		</div>

		<el-dialog
				title="发布文章"
				:visible.sync="dialogVisible"
				width="40%">
			<el-form label-width="100px">
				<el-form-item label="文章标签">
					<div>
						<v-dynamic-tag title="添加标签"  :tags="articleLabels" ref="dynamicTags" :max="5"></v-dynamic-tag>
						<div>最多添加5个标签</div>
					</div>
				</el-form-item>
				<el-form-item label="个人分类">
					<!-- 怎么处理手动输入个人分类的问题？ -->
					<v-dynamic-tag title="添加新分类" ref="dynamicCategories" :tags="selectedPersonalCategories"></v-dynamic-tag>
					<div v-if="allPersonalCategories.length > 0">
						<el-checkbox-group v-model="selectedPersonalCategories">
							<el-checkbox 
								v-for="catetory in allPersonalCategories" 
								:key="catetory" 
								:label="catetory">
							</el-checkbox>
						</el-checkbox-group>
					</div>
				</el-form-item>
				<el-row>
					<el-col :span="12">
						<el-form-item label="文章分类" required>
							<el-select v-model="articleType" placeholder="请选择" size>
								<el-option label="原创" value="0"></el-option>
								<el-option label="转载" value="1"></el-option>
								<el-option label="翻译" value="2"></el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="博客分类" required>
							<el-select v-model="selectedBlogCategory" placeholder="请选择">
								<el-option 
									:key="category.id"
									v-for="category in allBlogCategories" 
									:label="category.category" 
									:value="category.category">
								</el-option>
							</el-select>
						</el-form-item>
					</el-col>
				</el-row>
				<el-form-item label="私密文章">
					<el-switch v-model="privacy">
					</el-switch>
					<span>{{isPrivate}}</span>
				</el-form-item>
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button @click="dialogVisible = false" type="text">取消</el-button>
				<el-button type="text" @click="handleSaveDraft">保存为草稿</el-button>
				<el-button type="text" @click="handlePublish">发布文章</el-button>
			</span>
		</el-dialog>
		
	</div>
</template>
<script>
	import { mapState } from 'vuex';
	import * as funcs from '../../funcs/getData';
	import user from '../../store/user';
	import DateUtils from '../../utils/dateUtils'
    export default {
		created(){
			this.init();
			let articleid = this.$route.params.articleId;
			console.info('...articleid:' + articleid);
			if(articleid){//编辑模式
				this.editMode = true;
				this.articleId = articleid;
				funcs.getArticle(this.user.userid, articleid).then(res => {
					console.info('mdEditor res:' + JSON.stringify(res))
					if(res.data && res.data.data){
						let article = res.data.data;
						this.articleTitle = article.articleTitle;
						this.articleContent = article.articleContent;
						this.articleType = article.articleType;
						this.privacy = article.privacy == 1;
						let labels = JSON.parse(article.articleLabel);
						this.articleLabels = labels;
						let privateCategories = article.articlePrivateCategory;
						this.selectedPersonalCategories = privateCategories;
						let articleCategory = article.articleCategory;
						this.selectedBlogCategory = articleCategory;
					}
				}).catch(e => alert(e))
			}
		},
		data(){
			return {
				editMode: false,
				articleId: '',
				articleTitle: '欢迎使用MyBlog-MarkDown编辑器',
				articleContent: '测试文字',
				dialogVisible: false,
				articleLabels:[],
				allPersonalCategories: [],
				initSelectedPersonalCategories:[],
				selectedPersonalCategories: [],
				allBlogCategories: ['编程','读书','艺术','互联网','教育'],
				selectedBlogCategory: '',
				privacy: false,
				articleType: ''
			}
		},
		methods: {
			init(){
				//FIXME 看是不是可以通过store来实现 动态获取个人分类和博客分类
				funcs.getBlogPersonalCategories(this.user.userid).then(res => {
					console.info(res)
					if(res.data){
						this.allPersonalCategories = res.data.data;
					}
				}).catch(e => alert(e));

				funcs.getBlogCategories().then(res => {
					console.info(res)
					if(res.data){
						this.allBlogCategories = res.data.data;
					}
				}).catch(e => alert(e));
			},

			handleSaveDraft(){
				this.dialogVisible = false;
				//TODO 发布文章
				let article = this.getArticleObj();
				article.status = 2;

				this.submit(article);
			},
			handlePublish(){
				this.dialogVisible = false;
				//TODO 发布文章
				let article = this.getArticleObj();
				console.info(`...publish article: ${JSON.stringify(article)}`);
				if(this.privacy){
					article.status = 1;
				}

				this.submit(article);
			},
			getArticleObj(){
				let time = DateUtils.getDate();
				let articlePrivateCategory = this.$refs.dynamicCategories.value;
				console.info(`...articlePrivateCategory: ${JSON.stringify(articlePrivateCategory)}`);
				console.info(`...allPersonalCategories: ${JSON.stringify(this.allPersonalCategories)}`);
				let newCategory = articlePrivateCategory.filter(ele => {
					if(this.allPersonalCategories.indexOf(ele) < 0){
						return true;
					}else{
						return false;
					}
				});

				console.info(`...newCategory: ${JSON.stringify(newCategory)}`);

				let articleLabel = JSON.stringify(this.$refs.dynamicTags.value);
				return {
					id: this.articleId,
					articleType: this.articleType,
					userid: this.user.userid,
					articleTitle: this.articleTitle,
					articleLabel,
					articleCategory: this.selectedBlogCategory,
					articlePrivateCategory,
					newArticlePrivateCategory: newCategory,
					lastModified: time,
					createTime: time,
					articleContent: this.articleContent,
					status: 0,
					readCount:1,
					commentCount:0,
				}
			},

			submit(article){
				let methodName = this.editMode?'modifyArticle':'publishArticle';
				if(this.editMode){
					delete article.createTime;
					delete article.commentCount;
				}
				console.info(`-----mdEditor submit:${methodName}, article: ${JSON.stringify(article)}`)
				funcs[methodName](article).then(() => {
					//TODO 跳转
					this.$router.push({name: 'blogHome'});
				})
			}
		},
		computed: {
			isPrivate: function(){
				return this.privacy?'已设置成私密':'公开';
			},
			...mapState({
				user: state => {
					return state.user;
				}
			})
		}
    }
</script>