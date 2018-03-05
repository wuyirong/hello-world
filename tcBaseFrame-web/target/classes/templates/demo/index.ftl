<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>网站后台管理系统 </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<#--    <link rel="stylesheet" href="icon/fontawesome-4.2.0/css/font-awesome.css"/>
    <link rel="stylesheet" href="icon/fontawesome-4.2.0/css/font-awesome-ie7.min.css"/>-->
    <link rel="stylesheet" href="assets/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css"/>
    <link rel="stylesheet" href="assets/css/ace.min.css"/>
    <link rel="stylesheet" href="assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="assets/css/ace-skins.min.css"/>
    <link rel="stylesheet" href="assets/css/ace-ie.min.css"/>

    <script src="assets/js/ace-extra.min.js"></script>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <script src="/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>" + "<" + "script>");</script>
    <script type="text/javascript">
        if ("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>" + "<" + "script>");
    </script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/typeahead-bs2.min.js"></script>
    <script src="assets/js/excanvas.min.js"></script>
    <script src="assets/js/ace-elements.min.js"></script>
    <script src="assets/js/ace.min.js"></script>
    <script src="assets/layer/layer.js" type="text/javascript"></script>
    <script src="assets/laydate/laydate.js" type="text/javascript"></script>
    <script src="js/jquery.nicescroll.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(function () {
            var cid = $('#nav_list> li>.submenu');
            cid.each(function (i) {
                $(this).attr('id', "Sort_link_" + i);
            })
            //设置所有页面刷新都只刷新iframe里面的
            // $("body").keydown(function (e) {
            //     if(e.keyCode == 116){
            //         e.preventDefault();
            //         //iframe是当前引入iframe的名称,todo()是我在子页面定义的方法
            //         iframe.window.todo();
            //     }
            // })
        })
        jQuery(document).ready(function () {
            $.each($(".submenu"), function () {
                var $aobjs = $(this).children("li");
                var rowCount = $aobjs.size();
                var divHeigth = $(this).height();
                $aobjs.height(divHeigth / rowCount);
            });
            //初始化宽度、高度
            $("#main-container").height($(window).height() - 76);
            $("#iframe").height($(window).height() - 140);

            $(".sidebar").height($(window).height() - 99);
            var thisHeight = $("#nav_list").height($(window).outerHeight() - 173);
            $(".submenu").height();
            $("#nav_list").children(".submenu").css("height", thisHeight);

            //当文档窗口发生改变时 触发
            $(window).resize(function () {
                $("#main-container").height($(window).height() - 76);
                $("#iframe").height($(window).height() - 140);
                $(".sidebar").height($(window).height() - 99);

                var thisHeight = $("#nav_list").height($(window).outerHeight() - 173);
                $(".submenu").height();
                $("#nav_list").children(".submenu").css("height", thisHeight);
            });
            $(document).on('click', '.iframeurl', function () {
                var cid = $(this).attr("name");
                var cname = $(this).attr("title");
                $("#iframe").attr("src", cid).ready();
                $("#Bcrumbs").attr("href", cid).ready();
                $(".Current_page a").attr('href', cid).ready();
                $(".Current_page").attr('name', cid);
                $(".Current_page").html(cname).css({"color": "#333333", "cursor": "default"}).ready();
                $("#parentIframe").html('<span class="parentIframe iframeurl"> </span>').css("display", "none").ready();
                $("#parentIfour").html('').css("display", "none").ready();
            });
        });
        /******/
        $(document).on('click', '.link_cz > li', function () {
            $('.link_cz > li').removeClass('active');
            $(this).addClass('active');
        });

        $(document).ready(function () {
            $('#nav_list,.link_cz').find('li.home').on('click', function () {
                $('#nav_list,.link_cz').find('li.home').removeClass('active');
                $(this).addClass('active');
            });

            //时间设置
            function currentTime() {
                var d = new Date(), str = '';
                str += d.getFullYear() + '年';
                str += d.getMonth() + 1 + '月';
                str += d.getDate() + '日';
                str += d.getHours() + '时';
                str += d.getMinutes() + '分';
                str += d.getSeconds() + '秒';
                return str;
            }

            setInterval(function () {
                $('#time').html(currentTime)
            }, 1000);

            $('#Exit_system').on('click', function () {
                layer.confirm('是否确定退出系统？', {
                            btn: ['是', '否'],//按钮
                            icon: 2,
                        },
                        function () {
                            location.href = "/logout";
                        });
            });
        });


    </script>
    <style>
        .footer_style {
            width: 100%;
            border-top: 1px solid #ddd;
            padding: 2px 0px;
            color: #666666;
            background-color: #f2f2f2;
            font-family: "Courier New", Courier, monospace;
            position: fixed;
            /*z-index: 1111;*/
            text-align: left;
        }

        .l_f {
            float: left;
        }

        .r_f {
            float: right;
        }
    </style>
</head>
<body>
<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>
    <div class="navbar-container" id="navbar-container">
        <#include "/demo/common/top.ftl"/>
    </div>
    <div class="main-container" id="main-container">
        <script type="text/javascript">
            try {
                ace.settings.check('main-container', 'fixed')
            } catch (e) {
            }
        </script>
        <div class="main-container-inner">
            <a class="menu-toggler" id="menu-toggler" href="#">
                <span class="menu-text"></span>
            </a>
            <div class="sidebar" id="sidebar">
                <script type="text/javascript">
                    try {
                        ace.settings.check('sidebar', 'fixed')
                    } catch (e) {
                    }
                </script>
                <div id="menu_style" class="menu_style">
                <#include "/demo/common/menu.ftl" />
                </div>
                <script type="text/javascript">
                    $("#menu_style").niceScroll({
                        cursorcolor: "#888888",
                        cursoropacitymax: 1,
                        touchbehavior: false,
                        cursorwidth: "5px",
                        cursorborder: "0",
                        cursorborderradius: "5px"
                    });
                </script>
                <div class="sidebar-collapse" id="sidebar-collapse">
                    <i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
                       data-icon2="icon-double-angle-right"></i>
                </div>
                <script type="text/javascript">
                    try {
                        ace.settings.check('sidebar', 'collapsed')
                    } catch (e) {
                    }
                </script>
            </div>

            <div class="main-content">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {
                    }
                </script>
                <div class="breadcrumbs" id="breadcrumbs">
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home home-icon"></i>
                            <a href="index">首页</a>
                        </li>
                        <li class="active"><span class="Current_page iframeurl footer_style"></span></li>
                    </ul>
                </div>
            <#--进入系统默认加载该链接-->
                <iframe id="iframe" style="border:0; width:100%; background-color:#FFF;" name="iframe" frameborder="0"
                        src="/view/list"></iframe>
            </div><!-- /.main-content -->
        </div>
        <!--底部样式-->
        <#include "/demo/common/bottom.ftl" />
</body>
</html>

