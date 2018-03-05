<div class="sidebar-shortcuts" id="sidebar-shortcuts">
    <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
        <a class="btn btn-success">
            <i class="icon-signal"></i>
        </a>

        <a class="btn btn-info">
            <i class="icon-pencil"></i>
        </a>

        <a class="btn btn-warning">
            <i class="icon-group"></i>
        </a>

        <a class="btn btn-danger">
            <i class="icon-cogs"></i>
        </a>
    </div>

    <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
        <span class="btn btn-success"></span>

        <span class="btn btn-info"></span>

        <span class="btn btn-warning"></span>

        <span class="btn btn-danger"></span>
    </div>
</div>
<#list menu as parent>
<ul class="nav nav-list" id="nav_list">
    <li class="home"><a href="javascript:void(0)" name="/view/list" class="iframeurl" title="${(parent.text)!""}"><i
            class="${(parent.icon)!"icon-home"}"></i><span class="menu-text"> ${(parent.text)!""}</span></a></li>
    <#if parent.children??>
        <#list parent.children as childs>
    <li><a href="#" class="dropdown-toggle"><i class="${(childs.icon)!"icon-desktop"}"></i>
        <span class="menu-text"> ${(childs.text)!""} </span><b class="arrow icon-angle-down"></b></a>
        <#if childs.children??>
        <ul class="submenu">
            <#list childs.children as cchilds>
                <li class="home"><a href="javascript:void(0)" name="${(cchilds.href)!""}" title="${(cchilds.text)!""}"
                                    class="iframeurl"><i class="icon-double-angle-right"></i>${(cchilds.text)!""}</a>
                </li>
            </#list>
        </ul>
        </#if>
    </li>
        </#list>
    </#if>
</ul>
</#list>