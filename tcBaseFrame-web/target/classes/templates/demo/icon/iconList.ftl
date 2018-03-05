<!DOCTYPE HTML>
<html>
<head>
    <title></title>
    <#include "/demo/common/header.ftl" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet"/>
    <script src="/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
    <link href="/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet"/>
    <script src="/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="/artDialog/jquery.artDialog.source.js?skin=blue"></script>
    <script type="text/javascript" src="/artDialog/plugins/iframeTools.js"></script>
    <style type="text/css">
        .page-header {
            clear: both;
            margin: 0 20px;
            padding-top: 20px;
        }

        .the-icons {
            padding: 25px 10px 15px;
            list-style: none;
        }

        .the-icons li {
            float: left;
            width: 8%;
            line-height: 25px;
            margin: 2px 5px;
            cursor: pointer;
        }

        .the-icons i {
            margin: 1px 5px;
            font-size: 16px;
        }

        .the-icons li:hover {
            background-color: #efefef;
        }

        .the-icons li.active {
            background-color: #0088CC;
            color: #ffffff;
        }

        .the-icons li:hover i {
            /*font-size: 20px;*/
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#icons li").click(function () {
                $("#icons li").removeClass("active");
                $("#icons li i").removeClass("icon-white");
                $(this).addClass("active");
                $(this).children("i").addClass("icon-white");
                //console.log($(this).children(":first").data("class"));
                $("#icon").val($(this).children(":first").data("class"));
                $.dialog.data("json", $("#icon").val());
                $.dialog.close();
            });
            $("#icons li").dblclick(function () {
                top.$.jBox.getBox().find("button[value='ok']").trigger("click");
            });
        });
    </script>
</head>
<body>
<div id="search-results" class="hide"></div>
<div id="icons">
    <input type="hidden" id="icon" value="" />
    <h2 class="page-header"> 请选择图标</h2>
    <ul class="the-icons">
        <div class="bs-glyphicons">
            <ul>
                <li>
                    <i class="glyphicon glyphicon-plus"  data-class="glyphicon glyphicon-plus"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-euro"  data-class="glyphicon glyphicon-euro"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-eur"  data-class="glyphicon glyphicon-eur"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-minus"  data-class="glyphicon glyphicon-minus"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-cloud"  data-class="glyphicon glyphicon-cloud"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-envelope"  data-class="glyphicon glyphicon-envelope"></i>
                </li>

                <li>
                    <i class="glyphicon glyphicon-pencil"  data-class="glyphicon glyphicon-pencil"></i>
                </li>

                <li>
                    <i class="glyphicon glyphicon-glass"  data-class="glyphicon glyphicon-glass"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-music"  data-class="glyphicon glyphicon-music"></i>
                </li>

                <li>
                    <i class="glyphicon glyphicon-search"  data-class="glyphicon glyphicon-search"></i>
                </li>

                <li>
                    <i class="glyphicon glyphicon-heart"  data-class="glyphicon glyphicon-heart"></i>
                </li>

                <li>
                    <i class="glyphicon glyphicon-star"  data-class="glyphicon glyphicon-star"></i>
                </li>

                <li>
                    <i class="glyphicon glyphicon-star-empty"  data-class="glyphicon glyphicon-star-empty"></i>
                </li>

                <li>
                    <i class="glyphicon glyphicon-user"  data-class="glyphicon glyphicon-user"></i>
                </li>

                <li>
                    <i class="glyphicon glyphicon-film"  data-class="glyphicon glyphicon-film"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-th-large"  data-class="glyphicon glyphicon-th-large"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-th"  data-class="glyphicon glyphicon-th"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-th-list"  data-class="glyphicon glyphicon-th-list"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-ok"  data-class="glyphicon glyphicon-ok"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-remove"  data-class="glyphicon glyphicon-remove"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-zoom-in"  data-class="glyphicon glyphicon-zoom-in"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-zoom-out"  data-class="glyphicon glyphicon-zoom-out"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-off"  data-class="glyphicon glyphicon-off"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-signal"  data-class="glyphicon glyphicon-signal"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-cog"  data-class="glyphicon glyphicon-cog"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-trash"  data-class="glyphicon glyphicon-trash"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-home"  data-class="glyphicon glyphicon-home"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-file"  data-class="glyphicon glyphicon-file"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-time"  data-class="glyphicon glyphicon-time"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-road"  data-class="glyphicon glyphicon-road"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-download-alt"  data-class="glyphicon glyphicon-download-alt"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-download"  data-class="glyphicon glyphicon-download"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-upload"  data-class="glyphicon glyphicon-upload"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-inbox"  data-class="glyphicon glyphicon-inbox"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-play-circle"  data-class="glyphicon glyphicon-play-circle"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-repeat"  data-class="glyphicon glyphicon-repeat"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-refresh"  data-class="glyphicon glyphicon-refresh"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-list-alt"  data-class="glyphicon glyphicon-list-alt"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-lock"  data-class="glyphicon glyphicon-lock"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-flag"  data-class="glyphicon glyphicon-flag"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-headphones"  data-class="glyphicon glyphicon-headphones"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-volume-off"  data-class="glyphicon glyphicon-volume-off"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-volume-down"  data-class="glyphicon glyphicon-volume-down"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-volume-up"  data-class="glyphicon glyphicon-volume-up"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-qrcode"  data-class="glyphicon glyphicon-qrcode"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-barcode"  data-class="glyphicon glyphicon-barcode"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-tag"  data-class="glyphicon glyphicon-tag"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-tags"  data-class="glyphicon glyphicon-tags"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-book"  data-class="glyphicon glyphicon-book"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-bookmark"  data-class="glyphicon glyphicon-bookmark"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-print"  data-class="glyphicon glyphicon-print"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-camera"  data-class="glyphicon glyphicon-camera"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-font"  data-class="glyphicon glyphicon-font"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-bold"  data-class="glyphicon glyphicon-bold"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-italic"  data-class="glyphicon glyphicon-italic"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-text-height"  data-class="glyphicon glyphicon-text-height"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-text-width"  data-class="glyphicon glyphicon-text-width"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-align-left"  data-class="glyphicon glyphicon-align-left"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-align-center"  data-class="glyphicon glyphicon-align-center"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-align-right"  data-class="glyphicon glyphicon-align-right"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-align-justify"  data-class="glyphicon glyphicon-align-justify"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-list"  data-class="glyphicon glyphicon-list"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-indent-left"  data-class="glyphicon glyphicon-indent-left"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-indent-right"  data-class="glyphicon glyphicon-indent-right"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-facetime-video"  data-class="glyphicon glyphicon-facetime-video"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-picture"  data-class="glyphicon glyphicon-picture"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-map-marker"  data-class="glyphicon glyphicon-map-marker"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-adjust"  data-class="glyphicon glyphicon-adjust"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-tint"  data-class="glyphicon glyphicon-tint"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-edit" data-class="glyphicon glyphicon-edit"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-share" data-class="glyphicon glyphicon-share"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-check" data-class="glyphicon glyphicon-check"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-move" data-class="glyphicon glyphicon-move"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-step-backward" data-class="glyphicon glyphicon-step-backward"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-fast-backward" data-class="glyphicon glyphicon-fast-backward"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-backward" data-class="glyphicon glyphicon-backward"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-play" data-class="glyphicon glyphicon-play"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-pause" data-class="glyphicon glyphicon-pause"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-stop" data-class="glyphicon glyphicon-stop"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-forward" data-class="glyphicon glyphicon-forward"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-fast-forward" data-class="glyphicon glyphicon-fast-forward"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-step-forward" data-class="glyphicon glyphicon-step-forward"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-eject" data-class="glyphicon glyphicon-eject"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-chevron-left" data-class="glyphicon glyphicon-chevron-left"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-chevron-right" data-class="glyphicon glyphicon-chevron-right"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-plus-sign" data-class="glyphicon glyphicon-plus-sign"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-minus-sign" data-class="glyphicon glyphicon-minus-sign"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-remove-sign" data-class="glyphicon glyphicon-remove-sign"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-ok-sign" data-class="glyphicon glyphicon-ok-sign"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-question-sign" data-class="glyphicon glyphicon-question-sign"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-info-sign" data-class="glyphicon glyphicon-info-sign"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-screenshot" data-class="glyphicon glyphicon-screenshot"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-remove-circle" data-class="glyphicon glyphicon-remove-circle"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-ok-circle" data-class="glyphicon glyphicon-ok-circle"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-ban-circle" data-class="glyphicon glyphicon-ban-circle"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-arrow-left" data-class="glyphicon glyphicon-arrow-left"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-arrow-right" data-class="glyphicon glyphicon-arrow-right"></i>
                    
                </li>
                <li>
                    <i class="glyphicon glyphicon-arrow-up" data-class="glyphicon glyphicon-arrow-up"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-arrow-down" data-class="glyphicon glyphicon-arrow-down"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-share-alt" data-class="glyphicon glyphicon-share-alt"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-resize-full" data-class="glyphicon glyphicon-resize-full"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-resize-small" data-class="glyphicon glyphicon-resize-small"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-exclamation-sign" data-class="glyphicon glyphicon-exclamation-sign"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-gift" data-class="glyphicon glyphicon-gift"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-leaf" data-class="glyphicon glyphicon-leaf"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-fire" data-class="glyphicon glyphicon-fire"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-eye-open" data-class="glyphicon glyphicon-eye-open"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-eye-close" data-class="glyphicon glyphicon-eye-close"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-warning-sign" data-class="glyphicon glyphicon-warning-sign"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-plane" data-class="glyphicon glyphicon-plane"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-calendar" data-class="glyphicon glyphicon-calendar"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-random" data-class="glyphicon glyphicon-random"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-comment" data-class="glyphicon glyphicon-comment"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-magnet" data-class="glyphicon glyphicon-magnet"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-chevron-up" data-class="glyphicon glyphicon-chevron-up"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-chevron-down" data-class="glyphicon glyphicon-chevron-down"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-retweet" data-class="glyphicon glyphicon-retweet"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-shopping-cart" data-class="glyphicon glyphicon-shopping-cart"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-folder-close" data-class="glyphicon glyphicon-folder-close"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-folder-open" data-class="glyphicon glyphicon-folder-open"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-resize-vertical" data-class="glyphicon glyphicon-resize-vertical"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-resize-horizontal" data-class="glyphicon glyphicon-resize-horizontal"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-hdd" data-class="glyphicon glyphicon-hdd"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-bullhorn" data-class="glyphicon glyphicon-bullhorn"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-bell" data-class="glyphicon glyphicon-bell"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-certificate" data-class="glyphicon glyphicon-certificate"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-thumbs-up" data-class="glyphicon glyphicon-thumbs-up"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-thumbs-down" data-class="glyphicon glyphicon-thumbs-down"></i>
                </li>
                <li>
                    <i class="glyphicon glyphicon-hand-right" data-class="glyphicon glyphicon-hand-right"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-hand-left" data-class="glyphicon glyphicon-hand-left"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-hand-up" data-class="glyphicon glyphicon-hand-up"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-hand-down" data-class="glyphicon glyphicon-hand-down"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-circle-arrow-right" data-class="glyphicon glyphicon-circle-arrow-right"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-circle-arrow-left" data-class="glyphicon glyphicon-circle-arrow-left"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-circle-arrow-up" data-class="glyphicon glyphicon-circle-arrow-up"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-circle-arrow-down" data-class="glyphicon glyphicon-circle-arrow-down"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-globe" data-class="glyphicon glyphicon-globe"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-wrench" data-class="glyphicon glyphicon-wrench"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-tasks" data-class="glyphicon glyphicon-tasks"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-filter" data-class="glyphicon glyphicon-filter"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-briefcase" data-class="glyphicon glyphicon-briefcase"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-fullscreen" data-class="glyphicon glyphicon-fullscreen"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-dashboard" data-class="glyphicon glyphicon-dashboard"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-paperclip" data-class="glyphicon glyphicon-paperclip"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-heart-empty" data-class="glyphicon glyphicon-heart-empty"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-link" data-class="glyphicon glyphicon-link"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-phone" data-class="glyphicon glyphicon-phone"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-pushpin" data-class="glyphicon glyphicon-pushpin"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-usd" data-class="glyphicon glyphicon-usd"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-gbp" data-class="glyphicon glyphicon-gbp"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-sort" data-class="glyphicon glyphicon-sort"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-sort-by-alphabet" data-class="glyphicon glyphicon-sort-by-alphabet"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-sort-by-alphabet-alt" data-class="glyphicon glyphicon-sort-by-alphabet-alt"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-sort-by-order" data-class="glyphicon glyphicon-sort-by-order"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-sort-by-order-alt" data-class="glyphicon glyphicon-sort-by-order-alt"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-sort-by-attributes" data-class="glyphicon glyphicon-sort-by-attributes"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-sort-by-attributes-alt" data-class="glyphicon glyphicon-sort-by-attributes-alt"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-unchecked" data-class="glyphicon glyphicon-unchecked"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-expand" data-class="glyphicon glyphicon-expand"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-collapse-down" data-class="glyphicon glyphicon-collapse-down"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-collapse-up" data-class="glyphicon glyphicon-collapse-up"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-log-in" data-class="glyphicon glyphicon-log-in"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-flash" data-class="glyphicon glyphicon-flash"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-log-out" data-class="glyphicon glyphicon-log-out"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-new-window" data-class="glyphicon glyphicon-new-window"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-record" data-class="glyphicon glyphicon-record"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-save" data-class="glyphicon glyphicon-save"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-open" data-class="glyphicon glyphicon-open"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-saved" data-class="glyphicon glyphicon-saved"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-import" data-class="glyphicon glyphicon-import"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-export" data-class="glyphicon glyphicon-export"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-send" data-class="glyphicon glyphicon-send"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-floppy-disk" data-class="glyphicon glyphicon-floppy-disk"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-floppy-saved" data-class="glyphicon glyphicon-floppy-saved"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-floppy-remove" data-class="glyphicon glyphicon-floppy-remove"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-floppy-save" data-class="glyphicon glyphicon-floppy-save"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-floppy-open" data-class="glyphicon glyphicon-floppy-open"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-credit-card" data-class="glyphicon glyphicon-credit-card"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-transfer" data-class="glyphicon glyphicon-transfer"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-cutlery" data-class="glyphicon glyphicon-cutlery"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-header" data-class="glyphicon glyphicon-header"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-compressed" data-class="glyphicon glyphicon-compressed"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-earphone" data-class="glyphicon glyphicon-earphone"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-phone-alt" data-class="glyphicon glyphicon-phone-alt"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-tower" data-class="glyphicon glyphicon-tower"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-stats" data-class="glyphicon glyphicon-stats"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-sd-video" data-class="glyphicon glyphicon-sd-video"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-hd-video" data-class="glyphicon glyphicon-hd-video"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-subtitles" data-class="glyphicon glyphicon-subtitles"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-sound-stereo" data-class="glyphicon glyphicon-sound-stereo"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-sound-dolby" data-class="glyphicon glyphicon-sound-dolby"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-sound-5-1" data-class="glyphicon glyphicon-sound-5-1"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-sound-6-1" data-class="glyphicon glyphicon-sound-6-1"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-sound-7-1" data-class="glyphicon glyphicon-sound-7-1"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-copyright-mark" data-class="glyphicon glyphicon-copyright-mark"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-registration-mark" data-class="glyphicon glyphicon-registration-mark"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-cloud-download" data-class="glyphicon glyphicon-cloud-download"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-cloud-upload" data-class="glyphicon glyphicon-cloud-upload"></i>

                </li>

                <li>
                    <i class="glyphicon glyphicon-tree-conifer" data-class="glyphicon glyphicon-tree-conifer"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-tree-deciduous" data-class="glyphicon glyphicon-tree-deciduous"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-cd" data-class="glyphicon glyphicon-cd"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-save-file" data-class="glyphicon glyphicon-save-file"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-open-file" data-class="glyphicon glyphicon-open-file"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-level-up" data-class="glyphicon glyphicon-level-up"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-copy" data-class="glyphicon glyphicon-copy"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-paste" data-class="glyphicon glyphicon-paste"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-alert" data-class="glyphicon glyphicon-alert"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-equalizer" data-class="glyphicon glyphicon-equalizer"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-king" data-class="glyphicon glyphicon-king"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-queen" data-class="glyphicon glyphicon-queen"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-pawn" data-class="glyphicon glyphicon-pawn"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-bishop" data-class="glyphicon glyphicon-bishop"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-knight" data-class="glyphicon glyphicon-knight"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-baby-formula" data-class="glyphicon glyphicon-baby-formula"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-tent" data-class="glyphicon glyphicon-tent"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-blackboard" data-class="glyphicon glyphicon-blackboard"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-bed" data-class="glyphicon glyphicon-bed"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-apple" data-class="glyphicon glyphicon-apple"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-erase" data-class="glyphicon glyphicon-erase"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-hourglass" data-class="glyphicon glyphicon-hourglass"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-lamp" data-class="glyphicon glyphicon-lamp"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-duplicate" data-class="glyphicon glyphicon-duplicate"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-piggy-bank" data-class="glyphicon glyphicon-piggy-bank"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-scissors" data-class="glyphicon glyphicon-scissors"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-bitcoin" data-class="glyphicon glyphicon-bitcoin"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-btc" data-class="glyphicon glyphicon-btc"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-xbt" data-class="glyphicon glyphicon-xbt"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-yen" data-class="glyphicon glyphicon-yen"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-jpy" data-class="glyphicon glyphicon-jpy"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-ruble" data-class="glyphicon glyphicon-ruble"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-rub" data-class="glyphicon glyphicon-rub"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-scale" data-class="glyphicon glyphicon-scale"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-ice-lolly" data-class="glyphicon glyphicon-ice-lolly"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-ice-lolly-tasted" data-class="glyphicon glyphicon-ice-lolly-tasted"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-education" data-class="glyphicon glyphicon-education"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-option-horizontal" data-class="glyphicon glyphicon-option-horizontal"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-option-vertical" data-class="glyphicon glyphicon-option-vertical"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-menu-hamburger" data-class="glyphicon glyphicon-menu-hamburger"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-modal-window" data-class="glyphicon glyphicon-modal-window"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-oil" data-class="glyphicon glyphicon-oil"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-grain" data-class="glyphicon glyphicon-grain"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-sunglasses" data-class="glyphicon glyphicon-sunglasses"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-text-size" data-class="glyphicon glyphicon-text-size"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-text-color" data-class="glyphicon glyphicon-text-color"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-text-background" data-class="glyphicon glyphicon-text-background"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-object-align-top" data-class="glyphicon glyphicon-object-align-top"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-object-align-bottom" data-class="glyphicon glyphicon-object-align-bottom"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-object-align-horizontal" data-class="glyphicon glyphicon-object-align-horizontal"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-object-align-left" data-class="glyphicon glyphicon-object-align-left"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-object-align-vertical" data-class="glyphicon glyphicon-object-align-vertical"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-object-align-right" data-class="glyphicon glyphicon-object-align-right"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-triangle-right" data-class="glyphicon glyphicon-triangle-right"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-triangle-left" data-class="glyphicon glyphicon-triangle-left"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-triangle-bottom" data-class="glyphicon glyphicon-triangle-bottom"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-triangle-top" data-class="glyphicon glyphicon-triangle-top"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-console" data-class="glyphicon glyphicon-console"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-superscript" data-class="glyphicon glyphicon-superscript"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-subscript" data-class="glyphicon glyphicon-subscript"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-menu-left" data-class="glyphicon glyphicon-menu-left"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-menu-right" data-class="glyphicon glyphicon-menu-right"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-menu-down" data-class="glyphicon glyphicon-menu-down"></i>
                    
                </li>

                <li>
                    <i class="glyphicon glyphicon-menu-up" data-class="glyphicon glyphicon-menu-up"></i>
                    
                </li>

            </ul>
        </div>
        <br/><br/>
</div>
</body>
</html>