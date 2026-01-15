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
    -> 策略管理
</p>
<table id="customers">
    <thead>
    <tr>
        <td>策略ID</td>
        <td>策略名称</td>
        <td>策略类型</td>
        <td>策略备注</td>
        <td>是否可交易</td>
        <td>操作</td>
    </tr>
    </thead>
    <tbody>
    <#list strategyList as strategy>
        <tr>
            <td>${strategy.strategyId}</td>
            <td>${strategy.strategyName}</td>
            <td>${strategy.strategyType}</td>
            <td>${strategy.remark}</td>
            <#if strategy.enabled == true >
                <td id="enable">已启用</td>
            <#else>
                <td id="disable">已禁用</td>
            </#if>
            <td>
                <a href="/strategy/ui/enable?strategyId=${strategy.strategyId}">
                    交易启用
                </a>
                &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                <a href="/strategy/ui/disable?strategyId=${strategy.strategyId}">
                    交易禁用
                </a>
                &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                <a href="/strategy/ui/go_edit?strategyId=${strategy.strategyId}">
                    编辑
                </a>
            </td>
        </tr>
    </#list>
    <tr>
        <td colspan="11">
            <a href="/strategy/ui">刷新</a>
            &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
            <a href="/console/ui">返回</a>
            &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
            <a href="/strategy/ui/go_add">添加</a>
        </td>
    </tr>
    </tbody>
</table>

</body>

</html>
