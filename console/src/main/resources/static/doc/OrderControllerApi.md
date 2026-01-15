
# [REST] - 订单服务
## 查询订单

**URL:** `/order/v1`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 查询订单



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|userid|int32|true|No comments found.|-|
|accountId|int32|true|     用户ID    [int 必须项]|-|
|strategyId|int32|true|    策略ID    [int 可选项]|-|
|code|string|true|交易标的  [String 可选项, 股票代码/期货代码]|-|
|td|int32|true|    交易日    [int 可选项, 8位日期格式:YYYYMMDD]|-|
|status|int32|true|        订单状态  [int 可选项, 1:委托状态, 2:成交状态, 0或不传为全部状态订单]|-|


**Request-example:**
```bash
curl -X GET -i /order/v1?userid=0&accountId=0&strategyId=0&td=0&status=0&code=
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|tradingDay|int32|交易日|-|
|strategyId|int32|策略ID|-|
|strategyName|string|策略名称|-|
|instrumentCode|string|交易标的代码|-|
|instrumentName|string|交易标的名称|-|
|investorCode|string|投资者CODE|-|
|brokerCode|string|经纪商Code|-|
|brokerName|string|经纪商名称|-|
|accountId|int32|交易账户ID|-|
|subAccountId|int32|子账户ID|-|
|userId|int32|用户ID|-|
|username|int32|用户名|-|
|ordSysId|int64|订单系统编号 [*]|-|
|ordType|int32|订单类型|-|
|ordTypeName|string|订单类型名称|-|
|orderRef|string|订单引用|-|
|direction|int32|订单方向|-|
|side|int32|订单交易类型|-|
|status|int32|当前状态 [V]|-|
|statusName|string|当前状态名称|-|
|offerPrice|double|委托价格|-|
|offerQty|int32|委托数量|-|
|fee|double|交易费用|-|
|insertTime|string|创建时间|-|
|cancelTime|string|取消时间|-|
|adaptorCode|int32|交易通道CODE|-|
|adaptorName|string|交易通道名称|-|
|remark0|string|备注0|-|
|remark1|string|备注1|-|

**Response-example:**
```json
[
  {
    "tradingDay": 0,
    "strategyId": 0,
    "strategyName": "",
    "instrumentCode": "",
    "instrumentName": "",
    "investorCode": "",
    "brokerCode": "",
    "brokerName": "",
    "accountId": 0,
    "subAccountId": 0,
    "userId": 0,
    "username": 0,
    "ordSysId": 0,
    "ordType": 0,
    "ordTypeName": "",
    "orderRef": "",
    "direction": 0,
    "side": 0,
    "status": 0,
    "statusName": "",
    "offerPrice": 0.0,
    "offerQty": 0,
    "fee": 0.0,
    "insertTime": "",
    "cancelTime": "",
    "adaptorCode": 0,
    "adaptorName": "",
    "remark0": "",
    "remark1": ""
  }
]
```

## 获取订单最新状态列表

**URL:** `/order/v1/event`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取订单最新状态列表



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyId|int32|true|策略ID [int 必须项]|-|
|td|int32|true|交易日|-|


**Request-example:**
```bash
curl -X GET -i /order/v1/event?strategyId=0&td=0
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|tradingDay|int32|交易日 [*]|-|
|strategyId|int32|Strategy ID [*]|-|
|instrumentCode|string|交易标的代码 [*]|-|
|investorCode|string|Investor Code [*]|-|
|brokerCode|string|Broker Code [*]|-|
|accountId|int32|Account ID [*]|-|
|subAccountId|int32|SubAccount ID [*]|-|
|userId|int32|User ID [*]|-|
|ordSysId|int64|Order System ID [*]|-|
|ordRef|string|订单引用(Order Reference)|-|
|orderMsgType|int32|订单消息类型|-|
|limitPrice|double|指定价格|-|
|status|int32|订单状态|-|
|statusMsg|string|状态信息|-|
|volume|int32|数量|-|
|volumeFilled|int32|成交数量|-|
|volumeRemained|int32|剩余数量|-|
|price|double|价格|-|
|tradeId|string|成交ID|-|
|ordRejReason|int32|订单拒绝理由|-|
|updateTime|string|更新时间|-|
|cancelTime|string|取消时间|-|
|remark|string|备注|-|

**Response-example:**
```json
[
  {
    "tradingDay": 0,
    "strategyId": 0,
    "instrumentCode": "",
    "investorCode": "",
    "brokerCode": "",
    "accountId": 0,
    "subAccountId": 0,
    "userId": 0,
    "ordSysId": 0,
    "ordRef": "",
    "orderMsgType": 0,
    "limitPrice": 0.0,
    "status": 0,
    "statusMsg": "",
    "volume": 0,
    "volumeFilled": 0,
    "volumeRemained": 0,
    "price": 0.0,
    "tradeId": "",
    "ordRejReason": 0,
    "updateTime": "yyyy-MM-dd HH:mm:ss",
    "cancelTime": "yyyy-MM-dd HH:mm:ss",
    "remark": ""
  }
]
```

## 委托 [前端调用: 开仓, 平仓, 一键平仓, 手动单笔]

**URL:** `/order/v1/new`

**Type:** `POST`


**Content-Type:** `application/json;charset=utf-8`

**Description:** 委托 [前端调用: 开仓, 平仓, 一键平仓, 手动单笔]




**Body-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|userid|int32|false|[*] 用户ID|-|
|strategyId|int32|false|策略ID|-|
|instrumentCode|string|false|[*] 交易标的 (例: 期货代码, 股票代码)|-|
|side|int32|false|订单交易方向, 默认:0 (0:由策略自行决定; 1:买入; 2:卖出)|-|
|action|int32|false|[*] 交易动作 (1:开仓; 2:平仓)|-|
|offerPrice|double|false|委托价格 [*]|-|
|priceLimitType|int32|false|价格限制类型, 默认:0 (0:无限制; 1:涨停不卖; 2:跌停不买; 3:涨跌停同时限制)|-|
|offerAmount|double|false|委托金额 (委托金额/委托数量必须提交一项) [#]|-|
|offerQty|int32|false|委托数量 (委托金额/委托数量必须提交一项) [#]|-|
|type|int32|false|订单类型, 默认:1 (1:COMMON, 2:FAK, 4:FOK)|-|
|valid|int32|false|有效类型, 默认:1 (1:GTC, 2:GTD, 3:GFD)|-|
|offerStartTime|string|false|委托开始时间, 可精确到秒, 格式[YYYYMMDD-HHMMSS]|-|
|offerEndTime|string|false|委托结束时间, 可精确到秒, 格式[YYYYMMDD-HHMMSS]|-|
|requestTime|int64|false|请求时间, 前端请求时间戳, 可自行填充, 可精确到毫秒, 格式[YYYYMMDD-HHMMSS.SSS]|-|

**Request-example:**
```bash
curl -X POST -H 'Content-Type: application/json;charset=utf-8' -i /order/v1/new --data '{
  "userid": 0,
  "strategyId": 0,
  "instrumentCode": "",
  "side": 0,
  "action": 0,
  "offerPrice": 0.0,
  "priceLimitType": 0,
  "offerAmount": 0.0,
  "offerQty": 0,
  "type": 0,
  "valid": 0,
  "offerStartTime": "",
  "offerEndTime": "",
  "requestTime": 0
}'
```

**Response-example:**
```json
OK
```

## 批量委托

**URL:** `/order/v1/batch`

**Type:** `POST`


**Content-Type:** `application/json;charset=utf-8`

**Description:** 批量委托




**Body-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|userid|int32|false|[*] 用户ID|-|
|strategyId|string|false|[*] 策略ID|-|
|portfolioCode|string|false|[*] 投资组合(股票池/目标池) 代码|-|
|offerAmount|double|false|[*] 委托金额|-|
|executionType|int32|false|投资标的(单票)分配方式, 默认:0 (0:不指定; 1:金额平均; 2:数量平均)|-|
|priceLimitType|int32|false|价格限制类型, 默认:0 (0:无限制; 1:涨停不卖; 2:跌停不买; 3:涨跌停同时限制)|-|
|side|int32|false|订单交易方向, 默认:0 (0:由策略自行决定; 1:买入; 2:卖出)|-|
|offerStartTime|string|false|委托开始时间, 可精确到秒, 格式[YYYYMMDD-HHMMSS]|-|
|offerEndTime|string|false|委托结束时间, 可精确到秒, 格式[YYYYMMDD-HHMMSS]|-|
|requestTime|int64|false|请求时间, 前端请求时间戳, 可自行填充, 可精确到毫秒, 格式[YYYYMMDD-HHMMSS.SSS]|-|

**Request-example:**
```bash
curl -X POST -H 'Content-Type: application/json;charset=utf-8' -i /order/v1/batch --data '{
  "userid": 0,
  "strategyId": "",
  "portfolioCode": "",
  "offerAmount": 0.0,
  "executionType": 0,
  "priceLimitType": 0,
  "side": 0,
  "offerStartTime": "",
  "offerEndTime": "",
  "requestTime": 0
}'
```

**Response-example:**
```json
OK
```

## [X]新增订单 [非前端界面调用]

**URL:** `/order/v1`

**Type:** `PUT`


**Content-Type:** `application/json;charset=utf-8`

**Description:** [X]新增订单 [非前端界面调用]





**Request-example:**
```bash
curl -X PUT -H 'Content-Type: application/json;charset=utf-8' -i /order/v1
```

**Response-example:**
```json
OK
```

