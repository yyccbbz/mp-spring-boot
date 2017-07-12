var g = {};
g.dayPass = false;
g.hourPass = false;

g.base = "/";
g.maxWidth = 1100;

g.w = window.innerWidth;
g.h = window.innerHeight;

var p = {}; //pages

var p0 = {};
p.homepage = p0;
var p1 = {};
p.daily = p1;
var p2 = {};
p.finalReview = p2;
var p3 = {};
p.mobileReview = p3;
var p4 = {};
p.firstReview = p4;
var p5 = {};
p.secondReview = p5;
var p6 = {};
p.apply = p6;
var p7 = {};
p.reviewTotal = p7;
var p8 = {};
p.contractSign = p8;
var p9 = {};
p.reviewManager = p9;
var p10 = {};
p.loanBatch = p10;

var p11 = {};
p.user = p11;

//命名空间的问题


function formatDate(d) {
    d.date = new Date();
    d.month = d.date.format("yyyy年MM月");
    d.month2 = d.date.format("yyyyMM");
    d.fdate = d.date.format("yyyy-MM-dd");
    //    d.yestoday = d.date.add('d', -1).format("yyyy-MM-dd");
    //    d.date.add('d', 1);
    //    d.seven = d.date.add('d', -7).format("yyyy-MM-dd");
    //    d.date.add('d', 7);
    d.time = d.date.format("yyyy-MM-dd hh:mm:ss");
    d.hour = d.date.getHours();
    d.mins = d.date.getMinutes();
    d.day = new Date(d.fdate).getDate();
    d.dayPass = false;
    d.hourPass = false;

    return d;
}

formatDate(g);

function dateInterval() {
    var tmp = {};
    formatDate(tmp);
    if (tmp.fdate !== g.fdate) {
        g.dayPass = true;
        g = tmp;
    }
    if (tmp.hour !== g.hour) {
        g.hour = tmp.hour;
        g.hourPass = true;
    }
}

(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null)
            return unescape(r[2]);
        return null;
    }
})(jQuery);


g.timeout = 10000;


$(document).on("pageInit", function (e, pageId, $page) {


});




g.initPageDate = function (pageId) {
    $("#" + pageId + " .report-date").change(function () {
        //$.showIndicator();
        try {
            p[pageId].date = $(this).val();
            //var url = g.base + "#" + pageId;
            $.cookie(pageId, p[pageId].date);

            window.setTimeout(function () {
                var ua = window.navigator.userAgent.toLowerCase();
                var url = String(window.location.href).split("?")[0].split("#")[0];
                url = url + "?ran=" + 10000 * Math.random() + "#" + pageId;
                window.location.href = url;
            }, 300);
        } catch (e) {
            console.debug(e);
            $.hideIndicator();
        }
    });

    console.debug($("#" + pageId + " .report-date:first"));
    //calendar init
    $("#" + pageId + " .report-date:first").calendar();
    p[pageId].geturl = "/pages/" + pageId;

    var cookieDate = $.cookie(pageId);

    if (cookieDate && cookieDate !== "null" && cookieDate.length !== 0) {
        $("#" + pageId + " .span-date").text(cookieDate);
        p[pageId].date = cookieDate;
        // alert($.cookie(pageId));
        $.cookie(pageId, null);
    } else {
        $("#" + pageId + " .span-date").text(g.fdate);
        p[pageId].date = g.fdate;
    }
};

//default call initPage when  page animation end 
$(document).on("pageAnimationEnd", function (e, pageId, $page) {
    try {
        if (p[pageId] && p[pageId].initPage && !p[pageId].initPaged) {
            p[pageId].initPage();
        }
    } catch (e) {
        console.error(e);
    } finally {
        $.hideIndicator();
    }
});

//show loading 
$(document).on("pageAnimationStart", function (e, pageId, $page) {
    $.showIndicator();
});

g.loadPage = function (pageId, noAnimation, param, force) {

    p.currentPageId = pageId;

    if (!noAnimation)
        noAnimation = false;

    if (param)
        p[pageId].param = param;
    var html = $("#" + pageId).html();

    var load = function () {

        $.router.loadPage({
            url: "#" + pageId,
            noAnimation: noAnimation,
            replace: false
        });
        if (noAnimation) {
            try {
                if (p[pageId] && p[pageId].initPage)
                    p[pageId].initPage();
            } catch (e) {
                console.error(e);
            } finally {
                $.hideIndicator();
            }
        }
    };

    if (force || !html || html.length === 0) {
        $.ajax({
            // url: "/pages/" + pageId + ".html",
            url: "/pages/" + pageId + ".html",
            // url: pageId,
            async: true,
            type: 'GET',
            success: function (text, textStatus) {
                $("#" + pageId).children().remove();
                $("#" + pageId).html(text);
                g.loadToolbar(pageId);
                load();
            }
        });
    } else {
        g.loadToolbar(pageId);
        load();
    }
};

g.loadToolbar = function (pageId) {
    $.ajax({
        url: "/pages/toolbar.htm",
        async: false,
        type: 'GET',
        success: function (text, textStatus) {

            $("#" + pageId + " .top-bar").html(text);

            var menu = $('#' + pageId + '  .dl-menu:first').dlmenu(
                {
                    animationClasses: {classin: 'dl-animate-in-5', classout: 'dl-animate-out-5'}
                }
            );

            $("#" + pageId).click({menu: menu}, function (e) {
                e.data.menu.closeMenu();
            });

            // $("#" + pageId + " .top-bar").html(text);
            // g.initPageDate(pageId);
            // $("#" + pageId + " .toolbar-menu").click(function () {
            //     var nav = $('.g-nav');
            //     nav.show(300);
            //     nav.children().addClass('navAnimate');
            // });
        }
    });
};

g.queryData = function (date, url, callback) {

    $.ajax({
        url: url,
        async: true,
        type: 'POST',
        timeout: g.timeout,
        data: {day: date},
        xhrFields: {
            withCredentials: false
        },
        success: function (text, textStatus) {
            try {
                var o = JSON.parse(text);
                callback(o);
            } catch (e) {
                console.error(e);
            } finally {
                $.hideIndicator();
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $.hideIndicator();
            $.toast("获取数据超时");
        }
    });
    callback("");
};

g.parseFloat = function (record, value, x) {
    return parseFloat(value).toFixed(2);
};

var termType = String(navigator.platform).toLocaleLowerCase();
if (termType === "iphone" || termType.indexOf("linux") >= 0) { //phone
    g.terminal = "phone";
    g.maxWidth = g.w;
} else if (termType.indexOf("mac") >= 0 || termType.indexOf("win") >= 0) {
    g.terminal = "pc";
    g.maxWidth = 1100;
}


g.alert = function (message) {
    alert(message);
};


/**
 * excel 导出
 *
 * 传递参数：
 *      json:
 *      type:
 *
 */
function exportExcel() {
    var pageId = p.currentPageId;//p[p.currentPageId].th
    // console.log(p[pageId].th);
    var date = $("#" + p.currentPageId + " .span-date").text();
    $.ajax({
        url: "/" + pageId,
        type: 'POST',
        data: {day: date},
        success: function (data, status) {
            var str = data.insert(1, p[pageId].th);
            var json = JSON.parse(str);
            // console.log(json);
            g.downloadExl(json, pageId);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $.toast("获取数据超时");
        }
    });
}

g.downloadExl = function (json, pageId, type) {
    var tmpDown; //导出的二进制对象
    var keyMap = []; //获取键
    for (k in json[0]) {
        keyMap.push(k);
    }
    var tmpdata = [];//用来保存转换好的json

// 运用ES6内容
// json.map((v, i) => keyMap.map((k, j) => Object.assign({}, {
//     v: v[k],
//     position: (j > 25 ? getCharCol(j) : String.fromCharCode(65 + j)) + (i + 1)
// }))).reduce((prev, next) => prev.concat(next)).forEach((v, i) => tmpdata[v.position] = {
//     v: v.v
// });

    //运用ES5内容
    json.map(function (v, i) {
        return keyMap.map(function (k, j) {
            return Object.assign({}, {
                v: v[k],
                position: (j > 25 ? getCharCol(j) : String.fromCharCode(65 + j)) + (i + 1)
            });
        });
    }).reduce(function (prev, next) {
        return prev.concat(next);
    }).forEach(function (v, i) {
        tmpdata[v.position] = {
            v: v.v
        }
    });

    var outputPos = Object.keys(tmpdata); //设置区域,比如表格从A1到D10
    var tmpWB = {
        SheetNames: ['sheet'], //保存的表标题
        Sheets: {
            'sheet': Object.assign({},
                tmpdata, //内容
                {
                    '!ref': outputPos[0] + ':' + outputPos[outputPos.length - 1] //设置填充区域
                })
        }
    };
    tmpDown = new Blob([s2ab(XLSX.write(tmpWB, {
            bookType: (type == undefined ? 'xlsx' : type),
            bookSST: false,
            type: 'binary'
        } //这里的数据是用来定义导出的格式类型
    ))], {
        type: ""
    });
    //创建二进制对象写入转换好的字节流
    var href = URL.createObjectURL(tmpDown); //创建对象超链接
    document.getElementById("hf").href = href; //绑定a标签
    document.getElementById("hf").setAttribute("download", pageId + "_" + g.time + ".xlsx"); //修改下载文件名
    document.getElementById("hf").click(); //模拟点击实现下载
    setTimeout(function () { //延时释放
        URL.revokeObjectURL(tmpDown); //用URL.revokeObjectURL()来释放这个object URL
    }, 100);
};
//字符串转字符流
function s2ab(s) {
    var buf = new ArrayBuffer(s.length);
    var view = new Uint8Array(buf);
    for (var i = 0; i != s.length; ++i) {
        view[i] = s.charCodeAt(i) & 0xFF;
    }
    return buf;
}
// 将指定的自然数转换为26进制表示。映射关系：[0-25] -> [A-Z]。
function getCharCol(n) {
    var temCol = '',
        s = '',
        m = 0;
    while (n > 0) {
        m = n % 26 + 1;
        s = String.fromCharCode(m + 64) + s;
        n = (n - m) / 26;
    }
    return s;
}