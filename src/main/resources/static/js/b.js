var d = {
    data1 : [
        {'name':'首页','svg':'#icon-shouye_shouye'},
        {'name':'申请','svg':'#icon-crm-application'},
        {'name':'审批','svg':'#icon-shenpi'},
        {'name':'签约','svg':'#icon-qianyue',"Style":"transform: scale(1.1);top: calc(50% - 25px);"},
        {'name':'放款','svg':'#icon-tongguofangkuan01',"Style":"transform: scale(.9);top: calc(50% - 25px);"},
        {'name':'汇总','svg':'#icon-line_trend'},
        {'name':'待定'}
    ],
    values: {
        "申请":[
            {"name":'申请',"page":'apply'}
        ],
        "审批":[
            {"name":'电审',"page":'mobileReview'},
            {"name":'初审',"page":'firstReview'},
            {"name":'复审',"page":'secondReview'},
            {"name":'终审',"page":'finalReview'},
            {"name":'汇总',"page":'reviewTotal'},
            {"name":'信审专员',"page":'reviewManager'}
        ],
        "签约":[
            {"name":'面签',"page":'contractSign'}
        ],
        "放款":[
            {"name":'放款',"page":'loanBatch'}
        ],
        "汇总":[
            {"name":'汇总',"page":'daily'}
        ],
        "待定":[
            {"name":'用户',"page":'user'}
        ],
    }
};
var n = {
    inited:false,
    warp  : $('.nav'),
    module:$('.itemBox'),
    initText : function(dom,name){
        this.inited = true;
        if(!name){
            for(var i = 0;i < d.data1.length; i++){
                if(!d.data1[i].svg){
                    if($(dom).children().length-1 < i){
                        var li = '<li>'+ d.data1[i].name +'</li>';
                        $(dom).append(li);
                    }else{
                        $(dom).children().eq(i).text(d.data1[i].name);
                    }
                }else{
                    var svg = "";
                    if(!d.data1[i].Style){
                        svg = '<svg class="icon" aria-hidden="true"><use xlink:href="'+d.data1[i].svg+'"></use></svg>';
                    }else{
                        svg = '<svg class="icon" aria-hidden="true"style="'+ d.data1[i].Style +'"><use xlink:href="'+d.data1[i].svg+'"></use></svg>';
                    }
                    if($(dom).children().length-1 < i){
                        var li = '<li>'+ d.data1[i].name +svg+'</li>';
                        $(dom).append(li);
                    }else{
                        $(dom).children().eq(i).text(d.data1[i].name).append(svg);
                    }
                };
            }
        }else{
            $(dom).children(':first').html(name.Html).addClass('back');
            var obj = d.values[name.Text];
//                    console.log(obj)
            if(obj){// 当前二级不存在内容
                for(var i = 0;i < obj.length; i++){
                    if($(dom).children().eq(i+1) == undefined){
                        $(dom).append('<li data-page:"'+obj[i].page+'">'+ obj[i].name +'</li>');
                    }else{
                        $(dom).children().eq(i+1).text(obj[i].name).attr('data-page',obj[i].page);
                    }
                }
            }
        };
        this.initStyle();
        // 点击事件
        this.events($(dom));
    },
    initStyle:function(){
        this.warp.height($(window).height());
        var uList = this.module.children();
        // 获取UL
        for(var i = 0;i < uList.length; i++){
            var items = uList[i];
            // 判断长度布局
            for(var x = 0;x < $(items).children().length; x++){
                if($(items).children().length < 7){
                    //  三行 ？ 两行？
                    var h =  $(items).children().length > 4? $(items).height()/3 : $(items).height()/2;
                    $(items).addClass('trw');
                    // 是否存在 svg 图标
                    if($(items).children().eq(x).children('svg').length < 1){
                        $(items).children().eq(x).css({"height":h+'px',"line-height":h+'px'});
                    }else{
                        $(items).children().eq(x).css({"height":h+'px',"line-height":(h+35)+'px'});
                    }
                }else{
                    var h = 0;
                    if($(items).children().length > 9){// 超过9个子级就添加滚动条，并且li 高度 = ul.width()/3；
                        h = $(items).width()/3;
                        $(items).css({"overflow-y":'scroll'});
                    }else{// 9个以内 就没有滚动条，并且li 高度 = ul.height()/3；
                        h = $(items).height()/3;
                        $(items).css({"overflow":'hidden'});
                    }
                    $(items).addClass('three');
                    // 是否存在 svg 图标
                    if($(items).children().eq(x).children('svg').length < 1){
                        $(items).children().eq(x).css({"height":h+'px',"line-height":h+'px'});
                    }else{
                        $(items).children().eq(x).css({"height":h+'px',"line-height":(h+35)+'px'});
                    }
                }
            };
            $(items).css('opacity','1');// 目的是解决初始化样式太小，突然变大很突兀；
            this.borderStyle($(items));
        }
    },
    borderStyle:function(ul){
        var list = ul.children().length;
        for(var i = 0;i < list; i++){
            if(list < 7){ // 两列
                if(i < 2){ // 第一行
                    ul.children().eq(i).css({'border-top':'none',"border-bottom":"none"});
                };
                if( i > ul.children().length-3){ // 最后一行
                    ul.children().eq(i).css({'border-top':'none',"border-bottom":"none"});
                };
                if(i%2 == 1){ // 第二列
                    ul.children().eq(i).css({"border-right":"none"});
                }else{        // 最后一列
                    ul.children().eq(i).css({'border-left':'none',"border-right":"none"});
                }
            }else{ // 三列
                if(i < 3){ // 第一行
                    ul.children().eq(i).css({'border-top':'none',"border-bottom":"none"});
                };
                if( i >= ul.children().length-3){ //最后一行
                    ul.children().eq(i).css({'border-top':'none',"border-bottom":"none"});
                };
                if( i%3 == 0 || i%3 == 2){ // 第一列&最后一列
                    ul.children().eq(i).css({'border-left':'none',"border-right":"none"});
                };
            }
        }

    },
    events:function(dom){
        var self = this;
        dom.children().click(function(){
            self.module.children().css({"opacity":1});
            if($(this).text() == "")return;
            if(dom.hasClass('primary')){ // 点击主导航
                var name = {
                    'Text': $(this).text(),
                    'Html' : $(this).html()
                };
                if(name.Text == "首页"){
                    self.pageChange('homepage');
                }else{
                    var size = -(dom.outerWidth()+40);
                    dom.stop().animate({"opacity":0},400);
                    self.module.stop().animate({'left':size},500);
                    self.initText(self.module.children(':last'),name);
                }
            }else{
                $(this).unbind('click');
                if($(this).hasClass('back')){ // 返回首菜单
                    dom.stop().animate({"opacity":0},400);
                    self.module.stop().animate({'left':0},500);
                    dom.children().html(" ").attr('data-page',"");
                }else{ // 跳转页面
                    if($(this).attr('data-page').length < 1)return;
                    self.pageChange($(this).attr('data-page'));
                }
            }
        })
    },
    pageChange:function(URL){
        var self = this;
        // 样式回到初始状态
        self.module.stop().css({'left':0}).children().css("opacity",'1').last().children().html(" ").attr('data-page',"");
        self.warp.stop().hide(300);
        // 跳转
        g.loadPage(URL, false);
    },
    init:function(){
        this.warp.show(400).addClass('show');
        if(this.inited == false){
            this.initText(this.module.children(':first'));
        }
        this.warp.children().click(function(e){
            e.stopPropagation();
        })
    }
};

function show(){
    n.init();
}

function hide(e){
    n.module.stop().css({'left':0}).children().css("opacity",'1').last().children().html(" ").attr('data-page',"");
    $(e).stop().hide(300);
    setTimeout(function(){$(e).removeClass('show')},300);
}