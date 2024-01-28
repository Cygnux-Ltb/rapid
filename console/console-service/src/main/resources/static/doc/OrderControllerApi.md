
# 订单服务
## 查询订单

**URL:** `/order`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 查询订单



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|accountId|int32|true|     交易账户ID [int 必须项]|-|
|strategyId|int32|true|    策略ID [int 可选项]|-|
|code|string|true|交易标的 [String 股票代码/期货代码]|-|
|td|int32|true|    交易日 [int 可选项, 8位日期格式:YYYYMMDD]|-|
|status|int32|true|        订单状态 [int 可选项`, 1:委托状态, 2:成交状态, 0或不传为全部状态订单]|-|


**Request-example:**
```bash
curl -X GET -i /order?accountId=0&strategyId=0&td=0&status=0&code=
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|tradingDay|int32|交易日|-|
|strategyId|int32|策略ID|-|
|instrumentCode|string|交易标的代码|-|
|instrumentName|string|交易标的名称|-|
|investorId|string|投资者ID|-|
|brokerId|string|经纪商ID|-|
|accountId|int32|交易账户ID|-|
|subAccountId|int32|子账户ID|-|
|userId|int32|用户ID|-|
|ordSysId|int64|订单系统编号 [*]|-|
|ordType|string|订单类型|-|
|orderRef|string|订单引用|-|
|direction|string|订单方向|-|
|side|string|订单交易类型|-|
|status|int32|当前状态 [V]|-|
|offerPrice|double|委托价格|-|
|offerQty|int32|委托数量|-|
|insertTime|string|创建时间|-|
|cancelTime|string|取消时间|-|
|frontId|int32|前置机ID|-|
|sessionId|int32|会话ID|-|
|fee|double|交易费用|-|
|channelType|string|交易通道类型|-|
|remark|string|备注|-|

**Response-example:**
```json
[
  {
    "tradingDay": 0,
    "strategyId": 0,
    "instrumentCode": "",
    "instrumentName": "",
    "investorId": "",
    "brokerId": "",
    "accountId": 0,
    "subAccountId": 0,
    "userId": 0,
    "ordSysId": 0,
    "ordType": "",
    "orderRef": "",
    "direction": "",
    "side": "",
    "status": 0,
    "offerPrice": 0.0,
    "offerQty": 0,
    "insertTime": "yyyy-MM-dd HH:mm:ss",
    "cancelTime": "yyyy-MM-dd HH:mm:ss",
    "frontId": 0,
    "sessionId": 0,
    "fee": 0.0,
    "channelType": "",
    "remark": ""
  }
]
```

## 获取订单最新状态

**URL:** `/order/event`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取订单最新状态



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyId|int32|true|策略ID [int 必须项]|-|
|td|int32|true|交易日|-|


**Request-example:**
```bash
curl -X GET -i /order/event?strategyId=0&td=0
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|tradingDay|int32|交易日 [*]|-|
|strategyId|int32|Strategy ID [*]|-|
|instrumentCode|string|交易标的代码 [*]|-|
|investorId|string|Investor ID [*]|-|
|brokerId|string|Broker ID [*]|-|
|accountId|int32|Account ID [*]|-|
|subAccountId|int32|SubAccount ID [*]|-|
|userId|int32|User ID [*]|-|
|ordSysId|int64|Order System ID [*]|-|
|orderRef|string|订单引用(Order Reference)|-|
|orderMsgType|int32|订单消息类型|-|
|offset|string|买/卖|-|
|direction|string|方向|-|
|limitPrice|double|指定价格|-|
|status|int32|订单状态|-|
|statusMsg|string|状态信息|-|
|brokerSysID|int64|经纪商系统ID|-|
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
    "investorId": "",
    "brokerId": "",
    "accountId": 0,
    "subAccountId": 0,
    "userId": 0,
    "ordSysId": 0,
    "orderRef": "",
    "orderMsgType": 0,
    "offset": "",
    "direction": "",
    "limitPrice": 0.0,
    "status": 0,
    "statusMsg": "",
    "brokerSysID": 0,
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

## 创建订单 [前端调用: 开仓, 平仓, 一键平仓]

**URL:** `/order`

**Type:** `POST`


**Content-Type:** `APPLICATION_JSON_UTF8`

**Description:** 创建订单 [前端调用: 开仓, 平仓, 一键平仓]



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|userId|int32|false|用户ID|-|
|instrumentCode|string|false|交易标的 (例: 期货代码, 股票代码)|-|
|side|int32|false|订单交易类型 (1:买入, 2:卖出)|-|
|action|int32|false|交易动作 (1:开仓; 2:平仓)|-|
|offerPrice|double|false|委托价格|-|
|priceLimitType|int32|false|价格限制类型 (0:无限制; 1:涨停不买; 2:跌停不卖; 3:涨跌停同时限制)|-|
|offerQty|int32|false|委托数量|-|
|offerStartTime|string|false|委托开始时间, 可精确到毫米, 时间格式[yyyymmdd-HHMMSS.SSS]|-|
|offerEndTime|string|false|委托结束时间, 可精确到毫米, 时间格式[yyyymmdd-HHMMSS.SSS]|-|
|type|int32|false|订单类型 (1:COMMON, 2:FAK, 3:FOK) [可为空]|-|
|valid|int32|false|有效类型 (1:GTC, 2:GTD, 3:GFD) [可为空]|-|
|requestTime|int64|false|请求时间|-|


**Request-example:**
```bash
curl -X POST -H 'Content-Type: APPLICATION_JSON_UTF8' -i /order --data 'userId=0&side=0&action=0&offerPrice=0.0&priceLimitType=0&offerQty=0&type=0&valid=0&requestTime=0&offerEndTime=&offerStartTime=&instrumentCode='
```

**Response-example:**
```json
OK
```

## 新增订单 [非前端界面调用]

**URL:** `/order`

**Type:** `PUT`


**Content-Type:** `APPLICATION_JSON_UTF8`

**Description:** 新增订单 [非前端界面调用]





**Request-example:**
```bash
curl -X PUT -H 'Content-Type: APPLICATION_JSON_UTF8' -i /order
```

**Response-example:**
```json
OK
```

