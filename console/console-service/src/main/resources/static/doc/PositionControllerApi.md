
# 仓位服务
## 查询当前持仓 ([page1.jpg]-持仓板块)

**URL:** `/position/current`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 查询当前持仓 ([page1.jpg]-持仓板块)



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
|└─instrumentName|string|标的名称|-|
|└─pnl|double|盈亏|-|
|└─pnlPercent|double|盈亏百分比|-|
|└─todayPnl|double|今日净盈亏|-|
|└─todayPnlPercent|double|今日盈亏百分比|-|
|└─costPrice|double|成本价|-|
|└─longPos|int32|多头头寸|-|
|└─shortPos|int32|空头头寸|-|
|└─netPos|int32|净头寸|-|
|└─availablePos|int32|可用头寸|-|
|└─duration|int32|持仓时间|-|

**Response-example:**
```json
{
  "userId": 0,
  "positions": [
    {
      "instrumentCode": "",
      "instrumentName": "",
      "pnl": 0.0,
      "pnlPercent": 0.0,
      "todayPnl": 0.0,
      "todayPnlPercent": 0.0,
      "costPrice": 0.0,
      "longPos": 0,
      "shortPos": 0,
      "netPos": 0,
      "availablePos": 0,
      "duration": 0
    }
  ]
}
```

