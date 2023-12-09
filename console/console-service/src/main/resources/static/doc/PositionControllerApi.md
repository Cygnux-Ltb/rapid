
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
```bash
curl -X GET -i /position/current?userId=0
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|userId|int32|用户ID|-|
|positions|array|头寸列表|-|
|└─instrumentCode|string|标的代码|-|
|└─netPnl|double|净盈亏|-|
|└─costPrice|double|成本价|-|
|└─longPos|int32|多头头寸|-|
|└─shortPos|int32|空头头寸|-|
|└─netPos|int32|净头寸|-|
|└─duration|int32|持仓时间|-|

**Response-example:**
```json
{
  "userId": 0,
  "positions": [
    {
      "instrumentCode": "",
      "netPnl": 0.0,
      "costPrice": 0.0,
      "longPos": 0,
      "shortPos": 0,
      "netPos": 0,
      "duration": 0
    }
  ]
}
```

