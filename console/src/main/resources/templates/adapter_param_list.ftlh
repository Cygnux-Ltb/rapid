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
    -> Adaptor参数管理
</p>
<table id="customers">
    <thead>
    <tr>
        <td>Adaptor</td>
        <td>参数组</td>
        <td>参数名称</td>
        <td>参数值</td>
        <td>备注</td>
        <td>操作</td>
    </tr>
    </thead>
    <tbody>
    <#list params as param>
        <tr>
            <td>${param.adaptorDisplayName}</td>
            <td>${param.paramGroup}</td>
            <td>${param.paramName}</td>
            <td>${param.paramValue}</td>
            <td>${param.remark}</td>
            <td>
                <a href="/adapter/ui/go_edit_param?adaptorId=${param.adaptorId}&paramGroup=${param.paramGroup}&paramName=${param.paramName}">
                    编辑
                </a>
            </td>
        </tr>
    </#list>
    <tr>
        <td colspan="11">
            <a href="/console/ui">返回</a>
            &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
            <a href="/adapter/ui/go_add_param">添加</a>
        </td>
    </tr>
    </tbody>
</table>

</body>

</html>
