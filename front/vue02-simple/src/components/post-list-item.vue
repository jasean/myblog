<style lang="less" scoped>

    .title-box, .content-intro {
        cursor: pointer;
    }

    div.main {
        padding: 5px;
        text-align: left;
        div{
            margin:5px;
        }
    }
    .original-flag {
        background-color: rgba(64,158,255,.1);
        display: inline-block;
        padding: 0 5px;
        height: 22px;
        line-height: 20px;
        font-size: 12px;
        color: #409eff;
        border-radius: 50%;
        box-sizing: border-box;
        border: 1px solid rgba(64,158,255,.2);
        white-space: nowrap;
    }
    .title {
        margin-left: 5px;
        font-weight: bold;
    }
    
    div.footer span {
        margin-right: 10px;
        font-size: 14px;
        color: #6b6b6b;
    }

    .opt-box {
        position: absolute;
        right: 24px;
        bottom: 16px;
    }
    .btn-opt {
        border: none;
        text-decoration: none;
        color: #79a5e5;
        font-size: 14px;
        background-color: transparent;
        cursor: pointer;
    }

    .btn-opt[data-type="delete"] {
        color: #ca0c16;
    }

    h4 {
        margin: 0;
        margin-bottom: 6px;
        font-size: 18px;
        line-height: 24px;
        color: #3d3d3d;
        display: inline-block;
        font-weight: bold;
    }
    h4 a {
        display: block;
        padding-left: 36px;
        word-break: break-all;
    }
    span.article-type {
        color: #ca0c16;
        border: 1px solid #f4ced0;
        display: inline-block;
        width: 26px;
        height: 26px;
        line-height: 26px;
        text-align: center;
        font-size: 12px;
        border-radius: 50%;
        margin-left: -36px;
        margin-top: 0;
        vertical-align: 2px;
    }

    p.content {
        font-size: 14px;
        line-height: 22px;
        white-space: normal;
        color: #999;
        margin: 0;

        a {
            font-size: 14px;
            color: #999;
        }
    }

</style>
<template>
    <div class="main">
        <h4>
            <a href="#" @click="onOpenClick">
                {{articleTitle}}
            </a>
        </h4>
        <div class="footer">
            <span>{{articleTypeDesc}}</span>
            <span>{{formatDate}}</span>
            <span>阅读数：{{readCount}}</span>
            <span>评论数：{{commentCount}}</span>
        </div>
        <div class="opt-box">
            <button class="btn-opt" data-type="top" @click.stop="onOptClick('view')">查看</button>
            <button class="btn-opt" data-type="edit" @click.stop="onOptClick('forbid')">禁止评论</button>
            <button class="btn-opt" data-type="top" @click.stop="onOptClick('top')">置顶</button>
            <button class="btn-opt" data-type="delete" @click.stop="onOptClick('delete')">删除</button>
        </div>
    </div>
</template>
<script>
    import { ARTICLE_TYPE } from '../constants/dict';
    import DateUtils from '../utils/dateUtils'
    export default {
        props: {
            articleTitle: String,
            /** 是否原创 */
            articleType: String,
            /** 内容简介 */
            /** 毫秒数 */
            createTime: Number,
            readCount: Number,
            commentCount: Number,

            listIndex: Number
        },
        data(){
            return {
                articleTypeDesc: ARTICLE_TYPE[this.articleType],
                formatDate: DateUtils.getDateFromMilliseconds(this.createTime)
            }
        },

        methods: {
            onOptClick(type){
                console.info('..onOptClick:' + type)
                this.$emit('opt-selected', {type, listIndex: this.listIndex})
            },

            onOpenClick(){
                this.$emit('click');
            }
        }
        

    }
</script>