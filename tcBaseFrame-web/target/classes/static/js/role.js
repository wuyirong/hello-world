$(function () {
    layui.use('table', function () {
        var table = layui.table;

        //第一个实例
        table.render({
            elem: '#roleTable'
            , url: '/role/listAll' //数据接口
            , cellMinWidth: 100
            , cols: [[ //表头
                {field: 'name', title: '名称', fixed: 'left', type: 'normal'}
                , {field: 'enName', title: '英文名称'}
                , {
                    field: 'roleType', title: '归属机构', type: 'normal', templet: function (data) {
                        return data.roleType = '' ? "暂无分配归属机构" : data.roleType;
                    }
                }
                , {
                    field: 'dataScope', title: '数据范围', type: 'normal', templet: function (data) {
                        //console.log(data.dataScope);
                        if (data.dataScope = 1) {
                            return '所有数据';
                        } else if (data.dataScope = '2') {
                            return '所在公司及以下数据';
                        } else if (data.dataScope = '3') {
                            return '所在公司数据';
                        } else if (data.dataScope = '4') {
                            return '所在部门及以下数据';
                        } else if (data.dataScope = '5') {
                            return '所在部门数据';
                        } else if (data.dataScope = '8') {
                            return '仅本人数据';
                        } else if (data.dataScope = '9') {
                            return '按明细设置';
                        }
                    }
                },
                {fixed: 'right', align: 'center', toolbar: '#barDemo'}
            ]],
        });
        //监听工具条
        table.on('tool(roleBar)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                /*layui.use('element', function(){
                 var element = layui.element;

                 console.log(element);
                 element.on('tab(docDemoTabBrief)', function(data){
                 console.log(data);
                 });
                 element.tabAdd('docDemoTabBrief', {
                 title: '选项卡的标题'
                 ,content: '选项卡的内容' //支持传入html
                 ,id: 'add'
                 })
                 })*/
                parent.changeTab(data);
            } else if (obj.event === 'del') {
                layer.confirm('真的删除行么', function () {
                    $.get('/role/deleteRole', {id: data.id}, function (res) {
                        if (res.success) {
                            layer.confirm(res.msg, function () {
                                parent.reload();
                            })
                        } else {
                            layer.confirm(res.msg, function () {
                            })
                        }
                    }, 'json')
                });
            } else if (obj.event === 'assign') {

                parent.changeRoleAssign(data.id);
                //window.location.href='/role/roleAssign?id='+data.id;
            }
        });
    });

    //删除tab元素
    $("#roleList").click(function () {
        layui.use('element', function () {
            var element = layui.element;
            element.tabDelete('docDemoTabBrief', 'roleAssign');
        })
    });
});

function changeTab(data) {
    roleForm.window.addInfo(data);
    layui.use('element', function () {
        var element = layui.element;
        element.tabChange("docDemoTabBrief", "add");
    })
}
//调用清空表单方法
$(function () {
    $("#addRole").click(function () {
        roleForm.window.clear();
    })
})
//刷新页面
function reload() {
    window.location.href = '/role/view';
}

function changeRoleAssign(roleId) {
    var roleAssignUrl = '/role/roleAssign?id=' + roleId;
    layui.use('element', function () {
        var element = layui.element;
        element.tabAdd('docDemoTabBrief', {
            title: '分配角色'
            ,
            content: '<iframe runat="server" id="roleForm" name="roleAssignForm"  width="100%" height="100%" frameborder="no" border="0" src="' + roleAssignUrl + '"></iframe>' //支持传入html
            ,
            id: 'roleAssign'
        });
        //切换tab
        element.tabChange('docDemoTabBrief', 'roleAssign');
    });
}