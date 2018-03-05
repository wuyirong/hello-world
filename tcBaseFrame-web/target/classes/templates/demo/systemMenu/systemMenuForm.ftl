<html>
<head>
    <title>Title</title>
    <link href="/plugins/bootstrapValidator/bootstrapValidator.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/plugins/ztree/zTreeStyle.css" media="all">
    <#include "demo/common/header.ftl"/>
    <script src="/js/jquery.form.js"></script>
    <script src="/plugins/bootstrapValidator/bootstrap.min.js"></script>
    <script src="/plugins/bootstrapValidator/bootstrapValidator.js"></script>
    <script type="text/javascript" src="/artDialog/jquery.artDialog.source.js?skin=blue"></script>
    <script type="text/javascript" src="/artDialog/plugins/iframeTools.js"></script>
    <script src="/plugins/ztree/jquery.ztree.core.js"></script>
</head>
<script>
    //数据回显
    function addInfo(data) {
        $("#systemMenuName").val(data.text);
        $("#menuHref").val(data.href);
        $("#menuTarget").val(data.target);
        $("#menuSort").val(data.sort);
        $("#menuId").val(data.id);
        if (data.icon) {
            $("#icon").addClass(data.icon);
            $("#sysIcon").val(data.icon);
        }
        if (data.parent) {
            $("#parentId").val(data.parent.id);
            $("#parentText").val(data.parent.text);
        }
        $("#menuIcon").val(data.icon);
        $("#menuPermission").val(data.permission);
        $("#remarks").html(data.remarks);
        if (data.isShow) {
            console.log(data.isShow);
            $("#radio1").prop("checked", true);
            $("#radio1").val(true);
        } else {
            $("#radio2").prop("checked", true);
            $("#radio2").val(false);
        }
    }

    //清空内容
    function clearForm() {
        $('input').val('');
        $("#remarks").html('');
        $("#parentText").val("根目录");
    }

    //添加新节点方法
    function addMenu(pId, pText) {
        clearForm();
        $("#parentId").val(pId);
        $("#parentText").val(pText);
    }

    $(function () {
        //返回上个页面
        $("#backButton").click(function () {
            parent.back();
        })
        //选中单选框操作
        $("#radio1").click(function () {
            $("#radio1").val(true);
        })
        $("#radio2").click(function () {
            $("#radio2").val(false);
        })
        //弹出图标选择框
        $("#iconSelect").click(function () {
            //var currentTr = $(this).closest("tr"); //获取最近一个匹配的tr document对象
            var url = "/getIcon";
            $.dialog.open(url, {
                title: '图标选择列表',
                width: 1050,
                height: 500,
                close: function () {//当对话框关闭时
                    var json = $.dialog.data("json");
                    if (json) {
                        $("#icon").removeClass();
                        $("#icon").addClass(json);
                        $("#sysIcon").val(json);
                    }
                }
            });
        })
    })
    $(document).ready(function () {
        $('#systemMenuForm').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                text: {
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {
                            message: '名称是必填的'
                        }
                    }
                },
                sort: {
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {
                            message: '序号是必填的'
                        },
                        numeric: {message: '序号只能输入数字'}
                    }

                }
            }
        });
        //认证通过允许发送请求
        $('#saveButton').click(function () {
            var bv = $("#systemMenuForm").data('bootstrapValidator');
            bv.validate();
            if (bv.isValid()) {
                console.log($('form').serialize());
                //发送ajax请求
                $.ajax({
                    url: '/systemMenu/saveOrUpdate',
                    async: false,//同步，会阻塞操作
                    type: 'POST',//PUT DELETE POST
                    data: $('form').serialize(),
                    complete: function (msg) {
                        console.log(msg);
                    },
                    success: function (data) {
                        if (data.success) {
                            //$.message.alert("操作成功")
                            parent.reload();
                        } else {
                            $.message.alert("操作失败,请稍后再试")
                        }
                    }
                })
            }
        });
    });
</script>
<body>
<div class="container">
    <div class="row">
        <!-- form: -->
        <section>
            <div class="col-lg-8 col-lg-offset-2">
                <div class="page-header">
                    <h2>菜单管理</h2>
                </div>

                <form id="systemMenuForm" method="post" class="form-horizontal" action="target.php">
                    <input type="hidden" name="parent.id" id="parentId">
                    <input type="hidden" name="id" id="menuId">
                    <div class="form-group">
                        <label class="col-lg-3 control-label">上级菜单</label>
                        <div class="col-lg-4">
                            <input type="text" id="parentText" disabled class="form-control" name="parent.text"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">名称：</label>
                        <div class="col-lg-5">
                            <input type="text" id="systemMenuName" class="form-control" name="text"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">链接：</label>
                        <div class="col-lg-5">
                            <input type="text" class="form-control" id="menuHref" name="href"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">权限标识：</label>
                        <div class="col-lg-5">
                            <input type="text" class="form-control" id="menuPermission" name="permission"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">目标：</label>
                        <div class="col-lg-5">
                            <input type="text" id="menuTarget" class="form-control" name="target"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">图标：</label>
                        <div class="col-lg-5">
                            <i class="" id="icon"></i>
                            <input type="hidden" name="icon" id="sysIcon">
                            <button class="btn btn-default" type="button" id="iconSelect">选择</button>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">排序：</label>
                        <div class="col-lg-5">
                            <input type="text" id="menuSort" class="form-control" name="sort"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">可见：</label>
                        <div class="col-lg-5">
                            <div class="radio">
                                <label>
                                    <input type="radio" id="radio1" name="isShow"/> 显示
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" id="radio2" name="isShow"/> 隐藏
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">备注：</label>
                        <div class="col-lg-5">
                            <textarea class="form-control" id="remarks" name="remarks" style="width: 100%"
                                      rows="3"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-9 col-lg-offset-3">
                            <button type="button" class="btn btn-primary alert-success" id="saveButton">保存</button>
                            <button type="button" class="btn btn-primary" id="backButton">返回</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>
        <!-- :form -->
    </div>
</div>
</body>
</html>
