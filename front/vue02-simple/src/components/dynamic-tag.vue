<!-- 可关闭的动态标签组件 -->
<style lang="scss" scoped>
	.el-tag {
		margin-left: 10px;
	}
	.button-new-tag {
		margin-left: 10px;
		height: 32px;
		line-height: 30px;
		padding-top: 0;
		padding-bottom: 0;
	}
	.input-new-tag {
		width: 90px;
		margin-left: 10px;
		vertical-align: bottom;
	}
</style>
<template>
	<div>
		<el-tag
				:key="tag"
				v-for="tag in value"
				closable
				:disable-transitions="false"
				@close="handleClose(tag)">
				{{tag}}
		</el-tag>
		<el-input
					class="input-new-tag"
					v-if="inputVisible"
					v-model="inputValue"
					ref="saveTagInput"
					size="small"
					@keyup.enter.native="handleInputConfirm"
					@blur="handleInputConfirm">
		</el-input>
		<el-button v-else-if="!isFull" class="button-new-tag" size="small" 
				@click="showInput">{{title?title: '+ New Tag'}}</el-button>
	</div>
</template>
<script>
    export default {
		props:{
			title: String,
			/** 从外部传入的默认值 */
			tags: Array,
			/** 最大个数 */
			max: Number
		},
		data() {
			return {
				dynamicTags: [],
				inputVisible: false,
				inputValue: '',
				isFull: false
			};
		},
		methods: {
			handleClose(tag) {
				console.info(tag);
				console.info(this.dynamicTags);
				this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
				console.info(this.dynamicTags);
			},

			showInput() {
				this.inputVisible = true;
				this.$nextTick(() => {
					this.$refs.saveTagInput.$refs.input.focus();
				});
			},

			handleInputConfirm() {
				let inputValue = this.inputValue;
				if (inputValue && this.dynamicTags.indexOf(inputValue) < 0) {
					this.dynamicTags.push(inputValue);
				}
				this.inputVisible = false;
				this.inputValue = '';
				console.info(this.dynamicTags);
			},
		},
		computed: {
			value: function(){
				let array = this.tags?this.tags.concat(this.dynamicTags):this.dynamicTags;
				if(this.max && this.max > 0 && this.max <= array.length){
					array = array.slice(0, this.max);
					this.isFull = true;
				}else{
					this.isFull = false;
				}
				return array;
			}
		}
  }
</script>