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
    -> Adaptor添加
</p>
<form name="edit" action="/adapter/ui/update" method="post">
    <table id="customers">
        <thead>
        <tr>
            <td>Adaptor ID</td>
            <td>Adaptor名称</td>
            <td>策略类型</td>
            <td>策略描述</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                <label>
                    <input name="adaptorId" type="number">
                </label>
            </td>
            <td>
                <label>
                    <input name="adaptorName" type="text">
                </label>
            </td>
            <td>
                <label>
                    <select name="adaptorType">
                        <#list adaptorTypeOptions as adaptorTypeOption>
                            <option value=${adaptorTypeOption.name}>
                                ${adaptorTypeOption.name}
                            </option>
                        </#list>
                    </select>
                </label>
            </td>
            <td>
                <label>
                    <input name="remark" type="text">
                </label>
            </td>
            <td>
                <label>
                    <input name="submit" type="submit" value="提交"/>
                </label>
            </td>
        </tr>
        <tr>
            <td colspan="11"><a href="/adapter/ui">返回</a></td>
        </tr>
        </tbody>
    </table>
</form>
</body>

</html>
