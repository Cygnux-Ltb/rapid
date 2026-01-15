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
    >> <a href="/strategy/ui/param">策略参数</a>
    -> 策略参数添加
</p>
<form name="edit" action="/strategy/ui/update_param" method="post">
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
        <tr>
            <td>
                <label>
                    <select name="userid">
                        <#list userOptions as userOption>
                            <option value=${userOption.id}>
                                ${userOption.name}
                            </option>
                        </#list>
                    </select>
                </label>
            </td>
            <td>
                <label>
                    <select name="strategyId">
                        <#list strategyOptions as strategyOption>
                            <option value=${strategyOption.id}>
                                ${strategyOption.name}
                            </option>
                        </#list>
                    </select>
                </label>
            </td>
            <td>
                <label>
                    <select name="algoId">
                        <#list algoOptions as algoOption>
                            <option value=${algoOption.id}>
                                ${algoOption.name}
                            </option>
                        </#list>
                    </select>
                </label>
            </td>
            <td>
                <label>
                    <input name="paramName" type="text"/>
                </label>
            </td>
            <td>
                <label>
                    <select name="paramType">
                        <#list paramTypeOptions as paramTypeOption>
                            <option value=${paramTypeOption.name}>
                                ${paramTypeOption.name}
                            </option>
                        </#list>
                    </select>
                </label>
            </td>
            <td>
                <label>
                    <input name="paramValue" type="text"/>
                </label>
            </td>
            <td>
                <label>
                    <input name="remark" type="text"/>
                </label>
            </td>
            <td>
                <label>
                    <input name="submit" type="submit" value="提交"/>
                </label>
            </td>
        </tr>
        <tr>
            <td colspan="11"><a href="/strategy/ui/param">返回</a></td>
        </tr>
        </tbody>
    </table>
</form>
</body>

</html>
