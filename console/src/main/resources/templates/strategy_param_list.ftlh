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
    -> 策略参数
</p>
<table id="customers">
    <thead>
    <tr>
        <td>用户</td>
        <td>策略</td>
        <td>算法</td>
        <td>参数名</td>
        <td>参数类型</td>
        <td>参数值</td>
        <td>备注</td>
        <td>操作</td>
    </tr>
    </thead>
    <tbody>
    <#list params as param>
        <tr>
            <td>${param.username}(${param.userid})</td>
            <td>${param.strategyDisplayName}</td>
            <td>${param.algoDisplayName}</td>
            <td>${param.paramName}</td>
            <td>${param.paramType}</td>
            <td>${param.paramValue}</td>
            <td>${param.remark}</td>
            <td>
                <a href="/strategy/ui/go_edit_param?userid=${param.userid}&strategyId=${param.strategyId}&algoId=${param.algoId}&paramName=${param.paramName}">
                    编辑
                </a>
            </td>
        </tr>
    </#list>
    <tr>
        <td colspan="11">
            <a href="/console/ui">返回</a>
            &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
            <a href="/strategy/ui/go_add_param">添加</a>
        </td>
    </tr>
    </tbody>
</table>

</body>

</html>
