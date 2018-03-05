<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <head>
        <title>测试</title>
        <!-- Site CSS -->
        <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="/js/jquery.twbsPagination.min.js"></script>
        <script type="text/javascript">
            $(function () {
                //分页
                $("#pagination").twbsPagination({
                    totalPages: ${result.totalPage} || 1,
                        startPage
            : ${qo.currentPage},
                visiblePages : 5,
                        first
            :
                "首页",
                        prev
            :
                "上一页",
                        next
            :
                "下一页",
                        last
            :
                "最后一页",
                        onPageClick
            :

                function (event, page) {
                    $("#currentPage").val(page);
                    $("#listFrom").submit();
                }
            })
                //查询提交前页数设为1
                $(".btn-default").click(function () {
                    $("#listFrom").submit(function () {
                        $("#currentPage").val(1);
                    })
                })
            })
        </script>
    </head>
<body>

<div class="container">
    <div class="row">
        <div class="col-sm-10">
            <div class="col-sm-30">
                <div class="page-header">
                    <h3>员工管理</h3>
                </div>

                <div class="panel panel-default">
                    <div id="myTabs">
                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs" role="tablist">
                            <li role="presentation" class="active"><a href="#list" aria-controls="base" role="tab"
                                                                      data-toggle="tab">列表</a></li>
                            <li role="presentation"><a href="#saveOrUpdate" aria-controls="detail" role="tab"
                                                       data-toggle="tab">添加</a></li>
                        </ul>
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane active" id="list">
                                <form action="/view/list" method="post" id="listFrom" class="form-inline">
                                    <input type="hidden" name="currentPage" id="currentPage" value="${qo.currentPage}">
                                    <div class="form-group">
                                        <label>姓名</label>
                                        <input type="text" class="form-control" name="name" value="${(qo.name)!''}">
                                    </div>
                                    <div class="form-group">
                                        <label>年龄</label>
                                        <input type="text" class="form-control" name="age" value="${qo.age}">
                                    </div>
                                    <button type="submit" class="btn btn-default">搜索</button>
                                    <table class="table table-striped">
                                        <tr>
                                            <td>id</td>
                                            <td>名称</td>
                                            <td>年龄</td>
                                        </tr>
                                    <#list result.rows as item>
                                        <tr>
                                            <td>${item.id}</td>
                                            <td>${item.name}</td>
                                            <td>${item.age}</td>
                                        </tr>
                                    </#list>
                                    </table>
                                </form>
                                <div style="text-align: center;">
                                    <ul id="pagination" class="pagination"></ul>
                                </div>
                            </div>

                            <div role="tabpanel" class="tab-pane" id="saveOrUpdate">
                                <form action="/view/saveOrUpdate" id="saveOrUpdateForm" method="post"
                                      class="form-inline">

                                    <div class="form-group">
                                        <label for="exampleInputName2">姓名</label>
                                        <input type="text" class="form-control" name="name">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputEmail2">年龄</label>
                                        <input type="text" class="form-control" name="age">
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" id="saveButton">保存</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <script>
                $("#saveButton").click(function () {
                    $("#saveOrUpdateForm").submit();
                })
            </script>

</body>
</html>