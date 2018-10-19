<style lang="less" scoped>

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
    }
</style>
<template>
    <div class="main">
        <div>
            <span class="original-flag">{{articleTypeDesc.slice(0,1)}}</span>
            <span class="title">{{articleTitle}}</span>
        </div>
        <div class="content-intro">
            {{contentIntro}}
        </div>
        <div class="footer">
            <span>{{formatDate}}</span>
            <span>阅读数：{{readCount}}</span>
            <span>评论数：{{commentCount}}</span>
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
            articleContent: String,
            /** 毫秒数 */
            createTime: Number,
            readCount: Number,
            commentCount: Number
        },
        data(){
            return {
                contentIntro: this.articleContent.slice(0, 256),
                articleTypeDesc: ARTICLE_TYPE[this.articleType],
                formatDate: DateUtils.getDateFromMilliseconds(this.createTime)
            }
        },
        

    }
</script>