<html>
<head>
    <link rel="stylesheet" href="/js/layui-v2.2.5/layui/css/layui.css" media="all">
    <title>角色管理</title>
    <script src="/js/jquery-1.9.1.min.js"></script>
    <script src="/js/layui-v2.2.5/layui/layui.all.js"></script>
    <script src="/js/role.js"></script>
</head>
<script>
</script>
<body>
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
    <ul class="layui-tab-title">
        <li class="layui-this" lay-id="list" id="roleList">角色列表</li>
    <@shiro.hasPermission name="role:add">
        <li lay-id="add" id="addRole">角色添加</li>
    </@shiro.hasPermission>
    </ul>
    <div class="layui-tab-content" style="height: 80%;">
        <div class="layui-tab-item layui-show">
            <iframe runat="server" src="/role/roleList" width="100%" height="100%" frameborder="no" border="0"></iframe>
        </div>
    <@shiro.hasPermission name="role:add">
        <div class="layui-tab-item">
            <iframe runat="server" id="roleForm" name="roleForm" src="/role/roleForm" width="100%" height="100%"
                    frameborder="no" border="0"></iframe>
        </div>
    </@shiro.hasPermission>
    </div>
</div>
</body>
</html>
