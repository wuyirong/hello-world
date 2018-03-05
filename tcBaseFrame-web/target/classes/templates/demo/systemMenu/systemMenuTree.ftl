<html>
<head>
    <title>child</title>
    <#--<link rel="stylesheet" type="text/css" href="/jquery-easyui-1.5.2/themes/default/easyui.css">-->
    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.5.2/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/plugins/insdep/easyui_full.css">
    <link rel="stylesheet" type="text/css" href="/plugins/insdep/reset.min.css">
    <script src="/jquery-easyui-1.5.2/jquery.min.js"></script>
    <script type="text/javascript" src="/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>

</head>
<body>
<table id="systemMenuTable"></table>
<div id="systemMenu_toolbar">
<@shiro.hasPermission name="menu:edit">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
</@shiro.hasPermission>
<@shiro.hasPermission name="menu:delete">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-cmd="remove">登记为删除</a>
</@shiro.hasPermission>
<@shiro.hasPermission name="menu:add">
    <a class="easyui-linkbutton" href="#" iconCls="icon-add" plain="true" data-cmd="add">增加下级菜单</a>
</@shiro.hasPermission>
</div>
<script src="/js/systemMenu.js"></script>
</body>
</html>
