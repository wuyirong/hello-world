<div class="navbar-header pull-left">
    <a href="#" class="navbar-brand">
        <small>
        <#--企业logo-->
            <img src="${(logoImg)!""}">
        </small>
    </a><!-- /.brand -->
</div><!-- /.navbar-header -->
        <div class="navbar-header operating pull-left">

        </div>
        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <span class="time"><em id="time"></em></span>&nbsp;&nbsp;&nbsp;<span class=""><small>欢迎光临,<@shiro.principal property="username"/></small></span>
                        <i class="icon-caret-down"></i>
                    </a>
                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li><a href="javascript:void(0" name="Systems.html" title="系统设置" class="iframeurl"><i
                                class="icon-cog"></i>网站设置</a></li>
                        <li><a href="javascript:void(0)" name="admin_info.html" title="个人信息" class="iframeurl"><i
                                class="icon-user"></i>个人资料</a></li>
                        <li class="divider"></li>
                        <li><a href="javascript:ovid(0)" id="Exit_system"><i class="icon-off"></i>退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>