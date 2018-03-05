<html>
<head>
    <title>菜单管理</title>
<#include "demo/common/header.ftl"/>
</head>
<script>
    function jump(data, parentId, pText) {
        if (parentId) {
            systemMenuForm.window.addMenu(parentId, pText);
        } else {
            systemMenuForm.window.addInfo(data);
        }
        $('#myTabs a[href="#profile"]').tab("show");
    }

    function back() {
        $('#myTabs a[href="#home"]').tab("show");
    }

    function reload() {
        window.location.href = '/systemMenu/view';
    }

    $(function () {
        $("#systemAdd").click(function () {
            systemMenuForm.window.clearForm();
        });
    })
    /*function todo() {
        /!*alert("调用到子类了")*!/
        window.location.href = '/systemMenu/view';
    }*/
</script>
<body>
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist" id="myTabs">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">菜单列表</a></li>
<@shiro.hasPermission name="menu:add">
        <li role="presentation"><a href="#profile" id="systemAdd" aria-controls="profile" role="tab" data-toggle="tab">菜单添加</a>
</@shiro.hasPermission>
    </li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="home">
            <iframe runat="server" src="/systemMenu/tree" width="100%" height="80%"
                    frameborder="no"
                    border="0"></iframe>
        </div>
<@shiro.hasPermission name="menu:add">
        <div role="tabpanel" class="tab-pane" id="profile">
            <iframe runat="server" name="systemMenuForm" src="/systemMenu/systemMenuForm"
                    width="100%" height="90%"
                    frameborder="no"
                    border="0"></iframe>
        </div>
</@shiro.hasPermission>
    </div>
</div>

</body>
</html>
