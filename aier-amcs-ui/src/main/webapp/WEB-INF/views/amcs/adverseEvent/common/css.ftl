<style>
    .likeTabs {
        width: auto;
    }

    .h2-title-a {
        color: #333333;
    }

    /* 保存 暂存按钮样式 */
    .fix-save-btn {
        width: 30px;
        padding: 12px 6px;
        white-space: inherit;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
        border-top-right-radius: 0;
    }

    .fix-stash-btn {
        width: 30px;
        padding: 12px 6px;
        white-space: inherit;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
        border-bottom-right-radius: 0;
    }

    .fix-print-btn {
        width: 30px;
        padding: 12px 6px;
        white-space: inherit;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
        border-bottom-right-radius: 0;
    }
    
    .fix-cancel-btn {
        width: 30px;
        padding: 12px 6px;
        white-space: inherit;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
        border-bottom-right-radius: 0;
    }

    .fix-merge-btn {
        width: 30px;
        padding: 12px 6px;
        white-space: inherit;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
        border-bottom-right-radius: 0;
    }
    .fix-archived-btn
    {
        width: 30px;
        padding: 12px 6px;
        white-space: inherit;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
        border-bottom-right-radius: 0;
    }
    .fix-review-btn {
        width: 30px;
        padding: 12px 6px;
        white-space: inherit;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
        border-bottom-right-radius: 0;
    }
    .fix-back-btn {
        width: 30px;
        padding: 12px 6px;
        white-space: inherit;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
        border-bottom-right-radius: 0;
    }
    
    .fix-btn {
   	    color: gray;
        width: 30px;
        padding: 12px 6px;
        white-space: inherit;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
        border-bottom-right-radius: 0;
    }
    .toolbar {margin-right:7px;}
    .searchWapper {width: 520px; margin-left: 50px;}

    .panel-mask {
        position: fixed;
        top: 35px;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: black;
        opacity: 0.1;
        /*-webkit-mask-image: linear-gradient(to bottom, transparent 0%, black 100%);*/
        /*mask-image: linear-gradient(to bottom, transparent 0%, black 100%);*/
        z-index: 999;
    }

    .filelist {
        display: flex;
        flex-wrap: wrap;
        gap: 10px; /* 图片之间的间距 */
    }

    .filelist li {
        list-style: none;
        margin: 0;
        padding: 0;
    }



    .panel-mask p {
        position: absolute;
        color: white;
        width: 100%;
        height: 100%;
        font-size: 50px;
        margin: 0;
        line-height: 50px;
        text-align: center;
        animation: marquee 10s linear infinite;
        -webkit-animation: marquee 10s linear infinite;
    }

    @keyframes marquee {
        0% { left: 100%; }
        100% { left: -100%; }
    }

    @-webkit-keyframes marquee {
        0% { left: 100%; }
        100% { left: -100%; }
    }
    .txt {
        text-indent:0px;
    }
</style>