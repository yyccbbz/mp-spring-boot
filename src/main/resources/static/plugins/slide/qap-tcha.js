jQuery.QapTcha = {
    build: function (options) {
        var defaults = {
            disabledSubmit: true,
            autoSubmit: false,
            autoRevert: true,
            txtLock: "请按住滑块，拖动到最右侧",
            txtUnlock: '验证通过',
            PHPfile: '/login/slide'
        };

        if (this.length > 0)
            return jQuery(this).each(function (i) {
                var opts = $.extend(defaults, options),
                        t = $(this),
                        form = $('form').has(t),
                        Clr = jQuery('<div>', {
                            'class': 'clr'
                        }),
                        bgSlider = jQuery('<div>', {
                            'class': 'bgSlider'
                        }),
                        bgMasks = jQuery('<div>', {
                            'class': 'bgMasks'
                        }),
                        Slider = jQuery('<div>', {
                            'class': 'Slider'
                        }),
                        Icons = jQuery('<div>', {
                            id: 'Icons'
                        }),
                        TxtStatus = jQuery('<div>', {
                            'class': ' TxtStatus dropError TxtStatusFont',
                            text: opts.txtLock
                        }),
                        divQapTcha = jQuery('<div>', {
                            name: generatePass(32),
                            value: generatePass(7),
                            type: 'hidden'
                        });
                if (opts.disabledSubmit)
                    form.find('div[type=\'submit\']').attr('disabled', 'disabled');
                bgSlider.appendTo(t);
                Icons.insertAfter(bgSlider);
                Clr.insertAfter(Icons);
                divQapTcha.appendTo(t);
                Slider.appendTo(bgSlider);
                bgMasks.appendTo(bgSlider);
                TxtStatus.appendTo(bgSlider);
                t.show();
                Slider.draggable({
                    drag: function (event, ui) {
                        $(bgMasks).width(bgSlider.width() - ui.position.left)
                    },
                    revert: function (event, ui) {
                        if (opts.autoRevert) {
                            if (parseInt(Slider.css("left")) > (bgSlider.width() - Slider.width() - 3))
                                return false;
                            else
                                return true
                        }
                    },
                    revertDuration: 15,
                    containment: bgSlider,
                    axis: 'x',
                    stop: function (event, ui) {
                        if (ui.position.left > (bgSlider.width() - Slider.width() - 3)) {
                            $.ajax({
                                type: "post",
                                async: "async",
                                data: {
                                    action: 'qaptcha',
                                    qaptcha_key: divQapTcha.attr('name')
                                },
                                url: opts.PHPfile,
                                dataType: "json",
                                success: function (data) {
                                    if (!data.error) {
                                        Slider.draggable('disable').css('cursor', 'default');
                                        divQapTcha.val('');
                                        
                                        TxtStatus.html(opts.txtUnlock).addClass('dropSuccess').removeClass('dropError');
                                    
                                        Slider.addClass('SliderSuccess');
                                        Slider.css('background-size', 'cover');
                                        Icons.css('background-position', '-16px 0');
                                        form.find('div[type=\'submit\']').removeClass('disabled');
                                        if (opts.autoSubmit)
                                            form.find('div[type=\'submit\']').trigger('click');
                                    } else {
                                        console.debug(data.error)
                                    }
                                },
                                beforeSend: function () {
                                    var htmLoading = '<span>加载中...</span>';
                                    TxtStatus.html(htmLoading).addClass('dropSuccess').removeClass('dropError');
                                },
                                complete: function (XMLHttpRequest, textStatus) {
                                    isAjaxing = false;
                                    $("#btn_loading").css('display', 'none')
                                },
                                error: function (e) {
                                    alert(1);
                                    TxtStatus.html("");
                                    var htmlError = '<img src ="/imgs/error.png" width="16px" height="16px" style="margin-top: 6px;margin-left: 30px;float: left;"/><span style="display: block;float: left;margin-left: 5px;height: 25px;line-height: 25px;">请求失败，点击<a style="color:red;" href="javascript:refurbishValidate(' + t.attr("id") + ')">刷新</a>重试</span>';
                                    TxtStatus.html(htmlError).addClass('dropError').removeClass('TxtStatusFont');
                                    bgSlider.css('background-color', '#DDDDDD');
                                    console.debug(e)
                                }
                            });
                        } else {
                            while (bgMasks.width() <= bgSlider.width()) {
                                $(bgMasks).width(bgMasks.width() + Slider.width())
                            }
                            $(bgMasks).width(bgMasks.width() - Slider.width())
                        }
                    }
                });

                if (termType === "iphone" || termType.indexOf("linux arm") >= 0) {//phone
                    var hh = $("#username").height() * 1.5;
                    $("#QapTcha").height(hh);
                    $(".Slider").height(hh);
                    $(".Slider").width(hh);
                    $(".QapTcha .TxtStatus").css("line-height", (hh - 10) + "px");
                    $(".QapTcha .TxtStatus").css("font-size", "25px");
                    $(".QapTcha .TxtStatus").css("font-weight", "bold");

                } else if (termType.indexOf("mac") >= 0 || termType.indexOf("win") >= 0) {
                    $("#QapTcha").height(42);
                    $(".QapTcha .TxtStatus").css("line-height", "30px");
                }

                function generatePass(nb) {
                    var chars = 'azertyupqsdfghjkmwxcvbn23456789AZERTYUPQSDFGHJKMWXCVBN_-#@';
                    var pass = '';
                    for (i = 0; i < nb; i++) {
                        var wpos = Math.round(Math.random() * chars.length);
                        pass += chars.substring(wpos, wpos + 1)
                    }
                    return pass
                }
            });


    }
};
jQuery.fn.QapTcha = jQuery.QapTcha.build;

function refurbishValidate(ctrl) {
    $(ctrl).children().remove();
    $(ctrl).QapTcha();
}