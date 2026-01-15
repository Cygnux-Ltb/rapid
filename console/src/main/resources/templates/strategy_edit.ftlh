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
    >> <a href="/strategy/ui">策略管理</a>
    -> 策略编辑
</p>
<form name="edit" action="/strategy/ui/update" method="post">
    <table id="customers">
        <thead>
        <tr>
            <td>策略ID</td>
            <td>策略名称</td>
            <td>策略类型</td>
            <td>策略描述</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                <label>
                    <input name="strategyId" type="number" value="${strategy.strategyId}">
                </label>
            </td>
            <td>
                <label>
                    <input name="strategyName" type="text" value="${strategy.strategyName}">
                </label>
            </td>
            <td>
                <label>
                    <select name="strategyType">
                        <#list strategyTypeOptions as strategyTypeOption>
                            <option value=${strategyTypeOption.name}
                                    <#if strategy.strategyType == strategyTypeOption.name>selected</#if>
                            >
                                ${strategyTypeOption.name}
                            </option>
                        </#list>
                    </select>
                </label>
            </td>
            <td>
                <label>
                    <input name="remark" type="text" value="">
                        ${strategy.remark}
                    </input>
                </label>
            </td>
            <td>
                <label>
                    <input name="submit" type="submit" value="提交"/>
                </label>
            </td>
        </tr>
        <tr>
            <td colspan="11"><a href="/strategy/ui">返回</a></td>
        </tr>
        </tbody>
    </table>
</form>
</body>

</html>
