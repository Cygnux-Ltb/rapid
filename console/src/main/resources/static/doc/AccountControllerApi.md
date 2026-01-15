
# [REST] - 历史行情服务
## 获取指定交易账户参数

**URL:** `/account/v1`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取1分钟BAR



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|accountId|int32|true|交易账户ID|-|


**Request-example:**
```bash
curl -X GET -i /account/v1?accountId=0
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|accountId|int32|交易账户ID|-|
|accountParamList|array|交易账户参数列表|-|
|└─paramName|string|参数名称|-|
|└─paramValue|string|参数值|-|
|└─remark|string|参数备注|-|

**Response-example:**
```json
{
  "accountId": 0,
  "accountParamList": [
    {
      "paramName": "",
      "paramValue": "",
      "remark": ""
    }
  ]
}
```

