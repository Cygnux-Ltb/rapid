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
    >> <a href="/adapter/ui">Adaptor管理</a>
    -> Adaptor参数编辑
</p>
<form name="edit" action="/adapter/ui/update_param" method="post">
    <table id="customers">
        <thead>
        <tr>
            <td>Adapter 类型</td>
            <td>参数组</td>
            <td>参数名称</td>
            <td>参数值</td>
            <td>备注</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                <label>
                    <select name="adaptorId">
                        <#list adapterOptions as adapterOption>
                            <option value=${adapterOption.id}
                                    <#if param.adapterId == adapterOption.id>selected</#if>
                                    disabled
                            >
                                ${adapterOption.name}
                            </option>
                        </#list>
                    </select>
                </label>
            </td>
            <td>
                ${param.paramGroup}
                <input name="paramGroup" type="hidden" value="${param.paramGroup}"/>
            </td>
            <td>
                <label>
                    <input name="paramName" type="text" value="${param.paramName}"/>
                </label>
            </td>
            <td>
                <label>
                    <input name="paramValue" type="text" value="${param.paramValue}"/>
                </label>
            </td>
            <td>
                <label>
                    <input name="remark" type="text" value="${param.remark}"/>
                </label>
            </td>
            <td>
                <label>
                    <input name="submit" type="submit" value="提交"/>
                </label>
            </td>
        </tr>
        <tr>
            <td colspan="11"><a href="/adapter/ui/param">返回</a></td>
        </tr>
        <tbody>
    </table>
</form>
</body>

</html>
