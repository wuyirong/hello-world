$(function () {
    var systemMenuTable = $('#systemMenuTable');
    systemMenuTable.treegrid({
        url: '/systemMenu/listAll',
        idField: 'id',
        treeField: 'text',
        columns: [[
            {title: '名称', field: 'text', width: 100},
            {field: 'href', title: '链接', width: 100, align: 'left'},
            {field: 'sort', title: '排序', width: 30},
            {
                field: 'isShow', title: '可见', width: 30, formatter: function (value, row, index) {
                    return value ? "显示" : "隐藏";
                }
            },
            {field: 'permission', title: '权限标识', width: 100},
        ]],
        toolbar: '#systemMenu_toolbar',
        striped: true,
        rownumbers: true,
        singleSelect: true,
        fitColumns:true,
        onDblClickRow: function (row) {
            parent.jump(row);
        },
    });

//3.事件统一绑定
    $("[data-cmd]").click(function () {
        //获取当前点击的按钮的data-cmd值
        var cmd = $(this).data("cmd");
        //调用方法
        methodObj[cmd]();
    })
    //管理方法的对象
    var methodObj = {
        edit: function () {
            //判断是否有选中数据
            var row = systemMenuTable.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'warning');
                return;
            }
            parent.jump(row);
        },
        add: function () {
            //判断是否有选中数据
            var row = systemMenuTable.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'warning');
                return;
            }
            parent.jump(row,row.id,row.text);
        },
        //删除按钮
        remove: function () {
            //判断是否有选中数据
            var row =systemMenuTable.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'warning');
                return;
            }
            /*有子菜单，无法删除*/
            if(row.children.length > 0){
                $.messager.alert('温馨提示', '当前菜单下有子菜单，无法删除！', 'info');
                return;
            }
            //发送删除请求
            $.messager.confirm('温馨提示', '您想要执行该操作吗？', function (r) {
                if(r){
                    $.get("/systemMenu/delete", {id: row.id}, function (data) {
                        if (data.success) {
                            //弹出提示
                            $.messager.alert('温馨提示', data.msg, "info", function () {
                                //重新加载页面
                                parent.reload();
                            });
                        }else{
                            $.messager.alert('温馨提示', data.msg, 'error');
                        }
                    });
                }
            });
        },
    }
})

