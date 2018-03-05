<html>
<head>
    <link rel="stylesheet" href="/js/layui-v2.2.5/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/plugins/ztree/zTreeStyle.css" media="all">
    <title>角色添加</title>
    <script src="/js/jquery-1.9.1.min.js"></script>
    <script src="/js/jquery.form.js"></script>
    <script src="/js/layui-v2.2.5/layui/layui.all.js"></script>
</head>
<script>
</script>
<body>
<table class="layui-table" lay-even="" lay-skin="nob" lay-filter="roleEmp">
    <tr>
        <td align="right">角色名称：</td>
        <td>${(role.name)!""}</td>
        <td align="right">归属机构：</td>
        <td>暂无分配机构</td>
        <td align="right">英文名称：</td>
        <td>${(role.enName)!""}</td>
    </tr>
    <tr>
        <td align="right">角色类型：</td>
        <td>
        <#if role.roleType = 'assignment'>
            任务分配
        <#elseif role.roleType = 'security-role'>
            管理角色
        <#elseif role.roleType = 'user'>
            普通角色
        </#if>
        </td>
        <td align="right">数据范围：</td>
        <td>
            <#if role.dataScope = 1>
                所有数据
            <#elseif role.dataScope = 2>
                所在公司及以下数据
            <#elseif role.dataScope = 3>
                所在公司数据
            <#elseif role.dataScope = 4>
                所在部门及以下数据
            <#elseif role.dataScope = 5>
                所在部门数据
            <#elseif role.dataScope = 8>
                仅本人数据
            <#elseif role.dataScope = 9>
                按明细设置
            </#if>
        </td>
        <td></td>
        <td></td>
    </tr>
</table>
<div style="margin-left: 50px">
    <button class="layui-btn" id="assignRoleBtn">分配角色</button>
</div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>已分配的员工</legend>
</fieldset>

<div class="layui-form">
    <table class="layui-table">
        <colgroup>
            <col width="150">
            <col width="150">
            <col width="200">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th>归属公司</th>
            <th>归属部门</th>
            <th>登录名</th>
            <th>姓名</th>
            <th>电话</th>
            <th>手机</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
<#list empList as item>
<tr>
    <td></td>
    <td></td>
    <td>${(item.username)!""}</td>
    <td>${(item.name)!""}</td>
    <td>暂无登记电话</td>
    <td>暂无登记手机</td>
    <td><a class="layui-btn layui-btn-danger layui-btn-xs removeBtn" lay-event="del"
           data-empid="${(item.id)!""}">移除</a></td>
</tr>
</#list>
        </tbody>
    </table>
</div>
</body>
<script>
    $("#assignRoleBtn").click(function () {
        var rid = '/role/assignForm?id=${(role.id)!""}';
        layer.open({
            title: '角色分配'
            ,
            content: '<iframe runat="server" id="roleForm" name="roleAssignForm"  width="100%" height="100%" frameborder="no" border="0" src="' + rid + '"></iframe>'
            ,
            area: ['500px', '500px']
            ,
            btn: ['确定分配', '取消']
            ,
            yes: function (index, layero) {
                var body = layer.getChildFrame('body', index);
                var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                iframeWin.submitRole();
            }
        });
    })

    function reloadThis() {
        window.location.reload();
    }

    $(function () {
        //移除关系操作
        $(".removeBtn").click(function () {
            var current = $(this);
            layer.confirm("亲,请问确定要移除吗？", function () {
                $.get('/role/removeRoleAndEmpRelation', {
                    rid:${(role.id)!""},
                    empId: current.data("empid")
                }, function (data) {
                    if (data.success) {
                        layer.confirm(data.msg, function () {
                            window.location.reload();
                        })
                    } else {
                        layer.msg(data.msg);
                    }
                });
            });

        })
    })
</script>
</html>
