<style lang="less" scoped>
    .original-flag {
        background-color: rgba(64,158,255,.1);
        display: inline-block;
        padding: 0 10px;
        height: 32px;
        line-height: 30px;
        font-size: 12px;
        color: #409eff;
        border-radius: 50%;
        box-sizing: border-box;
        border: 1px solid rgba(64,158,255,.2);
        white-space: nowrap;
    }
</style>
<template>
    <div>
        <div>
            <span class="original-flag">{{original?'原':'转'}}</span>
            <span>{{title}}</span>
        </div>
        <div>
            {{contentIntro}}
        </div>
        <div>
            <span>{{createDate}}</span>
            <span>阅读数：{{readCount}}}</span>
            <span>评论数：{{commentCount}}</span>
        </div>
    </div>
</template>
<script>
    export default {
        props: {
            title: String,
            /** 是否原创 */
            original: Boolean,
            /** 内容简介 */
            contentIntro: String,
            createDate: Date,
            readCount: Number,
            commentCount: Number
        },
        data(){
            return {
                title: this.title,
                original: this.original,
                contentIntro: this.contentIntro,
                createDate: this.createDate,
                readCount: this.readCount,
                commentCount: this.commentCount
            }
        },


    }
</script>