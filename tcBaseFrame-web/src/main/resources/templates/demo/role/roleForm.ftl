<html>
<head>
    <link rel="stylesheet" href="/js/layui-v2.2.5/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/plugins/ztree/zTreeStyle.css" media="all">
    <title>角色添加</title>
    <script src="/js/jquery-1.9.1.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery.form/4.2.2/jquery.form.js"></script>
    <script src="/plugins/ztree/jquery.ztree.core.js"></script>
    <script src="/plugins/ztree/jquery.ztree.excheck.min.js"></script>
    <script src="/js/layui-v2.2.5/layui/layui.all.js"></script>
</head>
<script>
    //清空表单方法
    function clear() {
        layui.use(['layer', 'form'], function () {
            var form = layui.form;
            $("#isSys").prop("checked", false);
            $("#useable").prop("checked", false);
            $('input').val('');
            $("select").find("option:selected").attr('selected', false);
            $("#remarks").html('');
            form.render();//没有写这个，操作后没有效果
        })
    }
</script>
<body>

<br>
<form class="layui-form" id="roleForm" action="/role/saveOrUpdate" method="post">
    <input type="hidden" name="id" id="roleId">
    <div class="layui-form-item">
        <div class="layui-inline" style="width: 100%">
            <label class="layui-form-label" style="margin-left: 120px">归属机构</label>
            <div class="layui-input-inline">
                <input type="tel" name="office.id" style="width: 250px;" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="margin-left: 120px">角色名称</label>
            <div class="layui-input-inline">
                <input type="text" name="name" id="name" lay-verify="required" value="" placeholder="请输入用户名"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="margin-left: 120px">英文名称</label>
            <div class="layui-input-inline">
                <input type="text" name="enName" id="enName" lay-verify="required" autocomplete="off"
                       value="" class="layui-input" placeholder="请输入英文名称">
            </div>
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label" style="margin-left: 120px">角色类型</label>
        <div class="layui-input-inline">
            <select name="roleType" id="roleType" lay-verify="required" lay-search="" placeholder="请选择角色类型">
                <option></option>
                <option value="assignment">任务分配</option>
                <option value="security-role">管理角色</option>
                <option value="user">普通角色</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 8px">
        <label class="layui-form-label" style="margin-left: 120px;">系统数据</label>
        <div class="layui-input-block">
            <input type="checkbox" id="isSys" name="isSys" value="1" lay-skin="switch" lay-text="是|否">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="margin-left: 120px">是否可用</label>
        <div class="layui-input-block">
            <input type="checkbox" id="useable" name="useable" value="1" lay-skin="switch" lay-text="是|否">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label" style="margin-left: 120px">数据范围</label>
        <div class="layui-input-inline">
            <select lay-search="" id="dataScope" name="dataScope">
                <option></option>
                <option value="1">所有数据</option>
                <option value="2">所在公司及以下数据</option>
                <option value="3">所在公司数据</option>
                <option value="4">所在部门及以下数据</option>
                <option value="5">所在部门数据</option>
                <option value="8">仅本人数据</option>
                <option value="9">按明细设置</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label" style="margin-left: 120px">角色授权</label>
        <label class="layui-form-label">
            <div class="zTreeDemoBackground left">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
        </label>
        <input type="hidden" name="menuIdList" id="menuIds">
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label" style="margin-left: 120px">备注</label>
        <div class="layui-input-block">
            <textarea name="remarks" id="remarks" placeholder="请输入内容" class="layui-textarea"
                      style="width: 220px;margin-top: 20px"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block" style="margin-left: 300px">
            <button class="layui-btn" lay-submit="" id="saveBtn" lay-filter="sumb">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script>
    var menuIds = null;
    var tree = null;
    $(function () {
        //zTree相关
        var setting = {// 配置对象
            data: {// 数据相关配置
                simpleData: {// 简单数据模式
                    enable: true// 启用简单数据模式,默认为false
                }
            },
            callback: {// 点击加载后的子菜单会执行这个函数
                onClick: function (event, treeId, treeNode) {
                    tree.checkNode(node, !node.checked, true, true);
                    return false;
                }
            },
            async: {
                enable: true,
                url: "/systemMenu/getTreeResult",
            },
            check: {
                enable: true,
                chkStyle: "checkbox",
                chkboxType: {"Y": "p", "N": "ps"},
                nocheckInherit: true
            },
            view: {selectedMulti: false},
        };
        tree = $.fn.zTree.init($("#treeDemo"), setting);
        tree.expandAll(true);


        //数据提交
        $("#saveBtn").click(function () {
            //获取选中的树节点
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            var nodes = treeObj.getCheckedNodes(true);
            var ids = [], nodes = tree.getCheckedNodes(true);
            for (var i = 0; i < nodes.length; i++) {
                ids.push(nodes[i].id);
            }
            $("#menuIds").val(ids);
            layui.use('form', function () {
                var form = layui.form;
                form.on('submit(sumb)', function (data) {
                    var datas = data.field;
                    var action = data.form.action;
                    $.ajax({
                        url: action,
                        data: datas,
                        type: "POST",
                        dataType: "json",
                        success: function (data) {
                            if (data.success) {
                                layer.confirm(data.msg, function () {
                                    parent.reload();
                                });
                            } else {
                                layer.msg(data.msg);
                            }
                        },
                        error: function (error) {
                            alert(error)
                        }
                    });
                    return false;
                });
            })
        })
    })

    //加载样式
    layui.use('form', function () {
        var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
        form.render();
    });


    //数据回显方法
    function addInfo(data) {
        $("#name").val(data.name);
        $("#enName").val(data.enName);
        $("#roleId").val(data.id);
        $("#remarks").val(data.remarks);
        menuIds = data.menuIdList;

        if (menuIds) {
            for (var i = 0; i < menuIds.length; i++) {
                var node = tree.getNodeByParam("id", menuIds[i]);
                try {
                    tree.checkNode(node, true, false);
                } catch (e) {

                }
            }
        }
        tree.expandAll(true);
        layui.use(['layer', 'form'], function () {
            var form = layui.form;
            var roleType = data.roleType;
            var dataScope = data.dataScope;
            //角色类型回显
            $("#roleType option[value=" + roleType + "]").prop("selected", true);
            //数据范围回显
            $("#dataScope option[value=" + dataScope + "]").prop("selected", true);
            if (data.isSys = '1') {
                $("#isSys").prop("checked", true);
            } else {
                $("#isSys").prop("checked", false);
            }
            if (data.useable = '1') {
                $("#useable").prop("checked", true);
            } else {
                $("#useable").prop("checked", false);
            }
            form.render();//没有写这个，操作后没有效果
        });
    }

</script>
</body>
</html>
