
function httpRequest(settings){
    var requestParam = {
        aysnc: false, // 默认同步加载
        type: "GET", // 默认使用POST方式
        dataType: 'json',
        headers: { // 默认添加请求头
            "Author": "Jann",
            "Powered-By": "Jann"
        },
        xhrFields: {withCredentials: true},
        error: function (jqXHR, textStatus, errorMsg) { // 出错时默认的处理函数
            // jqXHR 是经过jQuery封装的XMLHttpRequest对象
            // textStatus 可能为： null、"timeout"、"error"、"abort"或"parsererror"
            // errorMsg 可能为： "Not Found"、"Internal Server Error"等

            // 提示形如：发送AJAX请求到"/index.html"时出错[404]：Not Found

            console.error("jqxhr status:" + jqXHR.status + ',textStatus:' + textStatus + ',errorMsg:' + errorMsg);
            if (jqXHR.status == 500) {
                alert('出错[' + jqXHR.status + ']：' + errorMsg + ':::' + jqXHR.responseText);
            }
            if (jqXHR.status == 404) {
                alert('出错[' + jqXHR.status + ']：' + errorMsg + ':::' + jqXHR.responseText);
            }

            if (jqXHR.status == 403) {
                alert('403');
                window.location.href = 'login.html';
            }
        }
    };

    requestParam = $.extend(requestParam, settings);
    console.info(requestParam);
    $.ajax(requestParam);
}
