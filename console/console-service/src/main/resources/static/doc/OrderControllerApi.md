
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


**Request-example:**
```bash
curl -X GET -i /order?accountId=0&strategyId=0&td=0&code=
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|code|int32|No comments found.|-|
|message|string|No comments found.|-|
|info|string|No comments found.|-|
|array|boolean|No comments found.|-|
|data|object|No comments found.|-|

**Response-example:**
```json
{
  "code": 0,
  "message": "",
  "info": "",
  "array": true,
  "data": {}
}
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
|strategyId|int32|strategyId [*]|-|
|instrumentCode|string|交易标的代码 [*]|-|
|investorId|string|investorId [*]|-|
|brokerId|string|brokerId [*]|-|
|accountId|int32|accountId [*]|-|
|subAccountId|int32|subAccountId [*]|-|
|userId|int32|userId [*]|-|
|ordSysId|int64|ordSysId [*]|-|
|orderRef|string|订单引用|-|
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
|action|int32|false|交易动作 (1: 开仓, 2: 平仓)|-|
|offerPrice|double|false|委托价格|-|
|offerQty|int32|false|委托数量|-|
|type|int32|false|订单类型 (1:COMMON, 2:FAK, 3:FOK) [可为空]|-|
|valid|int32|false|有效类型 (1:GTC, 2:GTD, 3:GFD) [可为空]|-|


**Request-example:**
```bash
curl -X POST -H 'Content-Type: APPLICATION_JSON_UTF8' -i /order --data 'userId=0&side=0&action=0&offerPrice=0.0&offerQty=0&type=0&valid=0&instrumentCode='
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

