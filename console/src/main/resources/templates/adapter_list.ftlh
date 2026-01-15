<!DOCTYPE html>
<html lang="zh-Hans">
<head>
    <meta content="text/html; charset=UTF-8" charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/style.css">
    <title>Rapid Parameter Settings</title>
</head>
<body>
<p>
    >> <a href="/console/ui">控制面板</a>
    -> Adaptor管理
</p>
<table id="customers">
    <thead>
    <tr>
        <td>Adaptor ID</td>
        <td>Adaptor 名称</td>
        <td>策略类型</td>
        <td>策略描述</td>
        <td>是否可交易</td>
        <td>操作</td>
    </tr>
    </thead>
    <tbody>
    <#list adapterList as adapter>
        <tr>
            <td>${adapter.adaptorId}</td>
            <td>${adapter.adaptorName}</td>
            <td>${adapter.adaptorType}</td>
            <td>${adapter.remark}</td>
            <#if adapter.enabled == true >
                <td id="enable">已启用</td>
            <#else>
                <td id="disable">已禁用</td>
            </#if>
            <td>
                <a href="/adapter/ui/enable?adaptorId=${adapter.adaptorId}">
                    交易启用
                </a>
                &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                <a href="/adapter/ui/disable?adaptorId=${adapter.adaptorId}">
                    交易禁用
                </a>
                &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                <a href="#">
                    编辑
                </a>
            </td>
        </tr>
    </#list>
    <tr>
        <td colspan="11">
            <a href="/adapter/ui">刷新</a>
            &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
            <a href="/console/ui">返回</a>
            &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
            <a href="/adapter/ui/go_add">添加</a>
        </td>
    </tr>
    </tbody>
</table>

</body>

</html>
