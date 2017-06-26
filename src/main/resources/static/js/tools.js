String.prototype.insert = function (index, string) {
    if (index > 0)
        return this.substring(0, index) + string + this.substring(index, this.length);
    else
        return string + this;
};


Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

Date.prototype.add = function (part, value) {

    value *= 1;
    if (isNaN(value)) {
        value = 0;
    }
    switch (part) {
        case "y":
            this.setFullYear(this.getFullYear() + value);
            break;
        case "m":
            this.setMonth(this.getMonth() + value);
            break;
        case "d":
            this.setDate(this.getDate() + value);
            break;
        case "h":
            this.setHours(this.getHours() + value);
            break;
        case "n":
            this.setMinutes(this.getMinutes() + value);
            break;
        case "s":
            this.setSeconds(this.getSeconds() + value);
            break;
        default:

    }
    return this;
};

//var Date = window.Date;
//console.debug(Date.prototype);
var Date1 = function (time) {
    if ((typeof time).toLocaleString() === 'string')
        time = time.replace(/-/g, "/");
    return new window.Date(time);
};
//alert(new Date1("2017-01-01 22:22:22").format("yyyy-MM-dd hh:mm"));


var getRandomColor = function () {

    return  '#' +
            (function (color) {
                return (color += '0123456789abcdef'[Math.floor(Math.random() * 16)])
                        && (color.length == 6) ? color : arguments.callee(color);
            })('');
};


(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null)
            return unescape(r[2]);
        return null;
    }
})(jQuery);


//Array.prototype.max = function () {
//    var arr = this;
//    var max = arr[0];
//
//    for (var i = 1; i < arr.length; i++) {
//        if (parseInt(arr[i]) > max) {
//            max = arr[i];
//        }
//    }
//    return max;
//};
//var arr = [1, 4, 9, 0, 2];
//var m = arr.max();
//
//console.debug(m);

function ShowToggle() {
    this.count = 0;
    this.nodes = [];
    this.push = function (node) {
        this.nodes.push(node);
    };
    this.toggle = function () {

        if (this.nodes.length === 0)
            return;
        try {
            var index = this.count % this.nodes.length;
            if (this.nodes.length === 1) {
                this.nodes[index].toggle(300);
            } else {
                for (var i = 0; i < this.nodes.length; i++) {
                    this.nodes[i].hide();
                }
                this.nodes[index].show(300);
            }
        } catch (e) {
            console.error(e);
        }
        this.count++;
    }
}