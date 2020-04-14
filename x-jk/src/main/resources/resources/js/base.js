var defaultTip = '服务繁忙';
var currTc = 0;

function forbidBackSpace(e) {
    var ev = e || window.event;
    var obj = ev.target || ev.srcElement;

    var t = obj.type || obj.getAttribute('type');

    var vReadOnly = obj.readOnly;
    var vDisabled = obj.disabled;

    vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
    vDisabled = (vDisabled == undefined) ? false : vDisabled;

    var flag1 = ev.keyCode == 8 && (t == 'password' || t == 'text' || t == 'textarea') && (vReadOnly == true || vDisabled == true);

    var flag2 = ev.keyCode == 8 && t != 'password' && t != 'text' && t != 'textarea';

    if (flag2 || flag1) {
        return false;
    }

}

//firefox,Opera
document.onkeypress = forbidBackSpace;
//Chrome ,IE
document.onkeydown = forbidBackSpace;

function checkTimeout(msg) {
    if (msg && msg.code == 101) {
        alert('登录已过期，请重新登录');
        window.location.href = msg.url;
    }
}

function ajaxCallback(params, callback) {
    var url = params.url;
    var type = params.type ? params.type : 'GET';
    var data = params.data;
    if (data) {
        if (typeof data === 'string') {
            data = simpleFilterReqChar(data) + '&_dc=' + Math.random();
        } else if (typeof data === 'object') {
            var tmpParamArr = [];
            for (var attr in data) {
                tmpParamArr.push(attr + "=" + multiFilterReqChar(data[attr]));
            }
            data = tmpParamArr.join('&') + '&_dc=' + Math.random();
        }
    }
    $.ajax({
        url: url,
        type: type,
        data: data,
        success: function (msg, success, resp) {
            checkTimeout(msg);
            if (callback) {
                callback(msg, success, resp);
            }
        }, error: function (xhr, textStatus, errorThrown) {
            // 状态码
            console.log(xhr.status);
            // 状态
            console.log(xhr.readyState);
            // 错误信息
            console.log(textStatus);
        }
    });
}

function simpleFilterReqChar(str) {
    if (str) {
        str = str.replace(/\+/g, "%2B");
    }
    return str;
}

function multiFilterReqChar(str) {
    if (str) {
        if (typeof str == 'string') {
            str = str.replace(/\+/g, "%2B");
            str = str.replace(/\&/g, "%26");
        }
    }
    return str;
}

/**
 * json解析处理空值
 */
function getValueByKey(key, item) {
    if (!item) {
        return "";
    }
    if (item.hasOwnProperty(key)) {
        return item[key] ? item[key] : "";
    }
    return "";
}

function notNull(str) {
    if (!str) {
        return "";
    }
    return str;
}

/**
 * 中文验证
 */
function isSpecialSymbol(str) {
    var reg = /[\u4E00-\u9FA5]|[\uFE30-\uFFA0]/gi;
    var i = reg.test(str);
    if (i) {
        return true;
    }
    return false;
}

/**
 * 字符串两边空白符
 */
String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "");
};
String.prototype.ltrim = function () {
    return this.replace(/(^\s*)/g, "");
};
String.prototype.rtrim = function () {
    return this.replace(/(\s*$)/g, "");
};
String.prototype.replaceAll = function (s1, s2) {
    return this.replace(new RegExp(s1, "gm"), s2);
};

//字符太长是省略
function ellipsisChar(str) {
    if (str && str.length > 40) {
        return str.substring(0, 30) + ' ......';
    }
    return str;
}

/**
 * 错误提示
 */
function alertTip(msg) {
    layer.alert(msg);
}

/***
 * 通用的layer提示
 */
function commonTip(msg) {
    layer.msg(msg, {
        offset: '50px'
    });
}

/***
 * 成功提示
 */
function successTip(msg, callback) {
    if (callback) {
        layer.msg(msg, {
            icon: 1,
            offset: '50px',
            time: 1000
        }, callback);
    } else {
        layer.msg(msg, {
            icon: 1,
            offset: '50px',
            time: 1000
        });
    }
}

/***
 * 失败提示
 */
function failureTip(msg, callback) {
    if (callback) {
        layer.msg(msg, {
            icon: 2,
            offset: '50px'
        }, callback);
    } else {
        layer.msg(msg, {
            icon: 2,
            offset: '50px'
        });
    }
}

/**
 * 验证是否数字
 */
function numberCheck(str) {
    if (!str) {
        return false;
    }
    if (/^\d+$/.test(str)) {
        return true;
    }
    return false;
}



