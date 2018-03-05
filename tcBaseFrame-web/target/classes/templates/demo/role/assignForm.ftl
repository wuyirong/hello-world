<html>
<head>
    <link rel="stylesheet" href="/js/layui-v2.2.5/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/plugins/ztree/zTreeStyle.css" media="all">
    <link href="/js/layui-v2.2.5/layui/common_style.css" rel="stylesheet" type="text/css">
    <title>角色添加</title>
    <script src="/js/jquery-1.9.1.min.js"></script>
    <script src="/js/jquery.form.js"></script>
    <script src="/js/layui-v2.2.5/layui/layui.all.js"></script>
</head>
<script>
</script>
<body>
<form id="editForm" action="/role/roleAssignSaveOrUpdate" method="post">
    <input type="hidden" name="rid" value="${id!""}">
    <table>
        <tr>
            <td>
                <label class="layui-form-label">未选择：</label>
                <select multiple="true" class="ui_multiselect01 all_roles">
                    <#list allEmployee as allEmp>
                        <option value="${allEmp.id}">${allEmp.name}</option>
                    </#list>
                </select>
            </td>
            <td align="center">
                <input type="button" class="layui-btn" style="width: 70px;margin-right: 28px" id="select" value="-->"/>
                <br/>
                <input type="button" class="layui-btn" style="width: 70px;margin-right: 28px" id="selectAll" value="==>"/>
                <br/>
                <input type="button" class="layui-btn" style="width: 70px;margin-right: 28px" id="deselect" value="<--"/>
                <br/>
                <input type="button" class="layui-btn" style="width: 70px;margin-right: 28px" id="deselectAll" value="<=="/>
                <br/>
            </td>
            <td style="margin-left: 30px">
                <label class="layui-form-label">已选择：</label>
                <select name="empIds" multiple="true"
                        class="ui_multiselect01 selected_roles" >
                   <#list roleEmpList as roleEmp>
                       <option value="${roleEmp.id}">${roleEmp.name}</option>
                   </#list>
                </select>
            </td>
        </tr>
    </table>
</form>
</body>
<script>
    $(function () {
        // 全部从左移动到右
        $("#selectAll").click(function () {
            $(".all_roles option").appendTo($(".selected_roles"));
        });
        // 把选中的从左移动到右
        $("#select").click(function () {
            $(".all_roles option:selected").appendTo($(".selected_roles"));
        });
        // 全部从右移动到左
        $("#deselectAll").click(function () {
            $(".selected_roles option").appendTo($(".all_roles"));
        });
        // 把选中的从右移动到左
        $("#deselect").click(function () {
            $(".selected_roles option:selected").appendTo($(".all_roles"));
        });
        //在表单提交之前,应该选中所有已经分配的权限选项
        /*$("#editForm").submit(function () {
            $(".selected_roles option").prop("selected", true)
        });*/
        //把已经分配的权限从所有的权限列表中移除
        //1:获取已经分配权限的的id,存储到ids数组中
        var ids = $.map($(".selected_roles option"), function (item) {
            return $(item).val();
        });
        //2:迭代所有的权限列表的每一个<option>
        $.each($(".all_roles option"), function (index, item) {
            var optionObject = $(item);
            var optionValue = $(item).val();
            //判断每一个值是否在ids数组中
            if ($.inArray(optionValue, ids) >= 0) {
                optionObject.remove();//如果在ids数组中,删除自己
            }
        });
    });

    // 将表单变成ajaxform
    function submitRole() {
        $(".selected_roles option").prop("selected", true);
        $("#editForm").ajaxSubmit(function (data) {
            if (data.success) {
                layer.confirm(data.msg, function () {
                    parent.reloadThis();
                });
            }else {
                layer.msg(data.msg);
            }
        });
    }
</script>
</html>
