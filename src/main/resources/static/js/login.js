$(function () {
    // Waves初始化
    Waves.displayEffect();
    // 输入框获取焦点后出现下划线
    $('.form-control').focus(function () {
        $(this).parent().addClass('fg-toggled');
    }).blur(function () {
        $(this).parent().removeClass('fg-toggled');
    });
});
Checkbix.init();
$(function () {
    // 点击登录按钮
    $('#login-bt').click(function () {
        login();
    });
    // 回车事件
    $('#loginname, #password').keypress(function (event) {
        if (13 == event.keyCode) {
            login();
        }
    });
});
// 登录
function login() {
    $.ajax({
        url: '/loginpost',
        type: 'POST',
        data: {
            loginname: $('#loginname').val(),
            password: $('#password').val(),
            rememberMe: $('#rememberMe').is(':checked'),
            backurl: ""
        },
        beforeSend: function () {

        },
        success: function (data) {
            console.log(data)
            if (data.code == 1) {
                alert(JSON.stringify(data));
                console.log(data.obj);
                window.location.href = data.msg;
                // localStorage.setItem("user","123");
            } else {
                alert(data.msg);
                if (0 == data.code) {
                    $('#loginname').focus();
                }
                /*if (10102 == data.code) {
                    $('#password').focus();
                }*/
            }
        },
        error: function (error) {
            console.log(error);
        }
    });
}