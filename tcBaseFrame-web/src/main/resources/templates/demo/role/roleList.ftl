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
<table id="roleTable" class="layui-hide" lay-filter="roleBar"></table>

<script type="text/html" id="barDemo">
    <@shiro.hasPermission name="role:assign">
    <a class="layui-btn layui-btn-xs" lay-event="assign">分配</a>
    </@shiro.hasPermission>
<@shiro.hasPermission name="role:edit">
    <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
</@shiro.hasPermission>
    <@shiro.hasPermission name="role:delete">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </@shiro.hasPermission>
</script>
</body>
</html>
