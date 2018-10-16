<style lang="scss" scoped>
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
			<el-button type="danger" @click="dialogVisible = true">发布文章</el-button>
		</div>
		<div class="edit-area">
			<mavon-editor v-model="value"/>
		</div>

		<el-dialog
				title="发布文章"
				:visible.sync="dialogVisible"
				width="30%"
				:before-close="handleClose">
			<el-form>
				<el-form-item label="文章标签">
					<v-dynamic-tag title="添加标签"  ref="dynamicTags"></v-dynamic-tag>
					<div>最多添加5个标签</div>
				</el-form-item>
				<el-form-item label="个人分类">
					<!-- 怎么处理手动输入个人分类的问题？ -->
					<v-dynamic-tag title="添加新分类" ref="dynamicCategories"></v-dynamic-tag>
					<div>
						<el-checkbox-group v-model="selectedPersonalCategories">
							<el-checkbox 
								v-for="catetory in allPersonalCategories" 
								:key="catetory" 
								label="catetory">
							</el-checkbox>
						</el-checkbox-group>
					</div>
				</el-form-item>
				<el-row>
					<el-col :span="12">
						<el-form-item label="文章分类">
							<el-select v-model="original" placeholder="请选择">
								<el-option label="原创" value="0"></el-option>
								<el-option label="转载" value="1"></el-option>
								<el-option label="翻译" value="2"></el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="博客分类">
							<el-select v-model="selectedBlogCategory" placeholder="请选择">
								<el-option 
									:key="category.id" 
									v-for="category in allBlogCategories" 
									label="category.category" 
									value="category.id">
								</el-option>
							</el-select>
						</el-form-item>
					</el-col>
				</el-row>
				<el-form-item label="私密文章：">
					<el-switch
						v-model="privacy">
					</el-switch>
					<div>{{isPrivate}}}</div>
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
    export default {
		created(){
			//FIXME 看是不是可以通过store来实现 动态获取个人分类和博客分类
			funcs.getBlogPersonalCategories(user.userid).then(res => {
				if(res.data){
					allPersonalCategories = res.data.data;
				}
			}).catch(e => alert(e));

			funcs.getBlogCategories().then(res => {
				if(res.data){
					allBlogCategories = res.data.data;
				}
			}).catch(e => alert(e));
		},
		data(){
			return {
				value: '',
				dialogVisible: false,
				allPersonalCategories: [],
				selectedPersonalCategories: [],
				allBlogCategories: [],
				selectedBlogCategory: '',
				privacy: false
			}
		},
		methods: {
			handleClose(done) {
				this.$confirm('确认关闭？')
				.then(_ => {
					done();
				})
				.catch(_ => {});
			},
			handleSaveDraft(){
				dialogVisible = false;
				//TODO 发布文章
			},
			handlePublish(){
				dialogVisible = false;
				//TODO 发布文章
			}
		},
		computed: {
			isPrivate: function(){
				return privacy?'已设置成私密':'公开';
			},
			...mapState({
				user: state => state.user
			})
		}
    }
</script>