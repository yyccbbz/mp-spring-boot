var g = {};
g.dayPass = false;
g.hourPass = false;

g.base = "/";
g.maxWidth = 1100;

g.w = window.innerWidth;
g.h = window.innerHeight;

var p = {}; //pages
var p1 = {};
p.final = p1;

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
                if (ua.match(/micromessenger/i)) {
                    var url = String(window.location.href).split("?")[0];
                    window.location.href = url + "?ran=" + 10000 * Math.random() + "#" + pageId;
                } else {
                    window.location.reload(true);
                }
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
        alert($.cookie(pageId));
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
}

g.loadToolbar = function (pageId) {
    $.ajax({
        url: "/pages/toolbar.html",
        async: false,
        type: 'GET',
        success: function (text, textStatus) {
            $("#" + pageId + " .top-bar").html(text);
            g.initPageDate(pageId);
        }
    });
};

g.queryData = function (date, url, callback) {

    // $.ajax({
    //     url: url,
    //     async: true,
    //     type: 'POST',
    //     timeout: g.timeout,
    //     data: { day: date },
    //     xhrFields: {
    //         withCredentials: false
    //     },
    //     success: function (text, textStatus) {
    //         try {
    //             var o = JSON.parse(text);
    //             callback(o);
    //         } catch (e) {
    //             console.error(e);
    //         }finally{
    //             $.hideIndicator();
    //         }
    //     },
    //     error: function (XMLHttpRequest, textStatus, errorThrown) {
    //         $.hideIndicator();
    //         $.toast("获取数据超时");
    //     }
    // });
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