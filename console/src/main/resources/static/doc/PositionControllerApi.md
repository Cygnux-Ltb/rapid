
# [REST] - 仓位服务
## 查询当前持仓 ([page1.jpg]-持仓板块)

**URL:** `/position/v1/current`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 查询当前持仓 ([page1.jpg]-持仓板块)



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|userid|int32|true|用户ID|-|


**Request-example:**
```bash
curl -X GET -i /position/v1/current?userid=0
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|userid|int32|用户ID|-|
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
|└─duration|int32|持仓时间(天数)|-|

**Response-example:**
```json
{
  "userid": 0,
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

## 持仓平仓操作<br><br>传[用户ID]时; 动作为:全部平仓, [一键平仓]功能使用<br>传[用户ID],[投资标的代码]时; 动作为:平仓所有指定投资标的的仓位<br>传[用户ID],[投资标的代码],[方向]时; 动作为:平仓指定投资标的和指定方向的仓位<br>传[用户ID],[投资标的代码],[方向],[数量]时; 动作为:平仓指定数量的指定投资标的和指定方向的仓位和

**URL:** `/position/v1/close`

**Type:** `POST`


**Content-Type:** `APPLICATION_JSON_UTF8`

**Description:** 持仓平仓操作
<p>
传[用户ID]时; 动作为:全部平仓, [一键平仓]功能使用
传[用户ID],[投资标的代码]时; 动作为:平仓所有指定投资标的的仓位
传[用户ID],[投资标的代码],[方向]时; 动作为:平仓指定投资标的和指定方向的仓位
传[用户ID],[投资标的代码],[方向],[数量]时; 动作为:平仓指定数量的指定投资标的和指定方向的仓位和




**Body-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|userid|int32|false|[不可为空] 用户ID|-|
|instrumentCode|string|false|[可为空] 交易标的 (股票代码/期货代码)|-|
|side|int32|false|[可为空, 默认: 0] 方向 (0:全部; 1:平多仓; 2:平空仓)|-|
|qty|int32|false|[可为空, 默认: 0] 数量|-|
|startTime|string|false|[可为空] 开始时间, 精确到秒, 时间格式[YYYYMMDD-HHMMSS]|-|

**Request-example:**
```bash
curl -X POST -H 'Content-Type: APPLICATION_JSON_UTF8' -i /position/v1/close --data '{
  "userid": 0,
  "instrumentCode": "",
  "side": 0,
  "qty": 0,
  "startTime": ""
}'
```

**Response-example:**
```json
OK
```

