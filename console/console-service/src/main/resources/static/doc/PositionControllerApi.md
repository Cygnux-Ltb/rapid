
# 仓位服务
## 查询当前持仓

**URL:** `/position/current`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 查询当前持仓



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|userId|int32|true|用户ID|-|


**Request-example:**
```
curl -X GET -i /position/current?userId=0
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|userId|int32|用户ID|-|
|positions|array|头寸列表|-|
|└─instrumentCode|string|标的代码|-|
|└─longPos|int32|多头头寸|-|
|└─shortPos|int32|空头头寸|-|
|└─netPos|int32|净头寸|-|

**Response-example:**
```
{
  "userId": 0,
  "positions": [
    {
      "instrumentCode": "",
      "longPos": 0,
      "shortPos": 0,
      "netPos": 0
    }
  ]
}
```

