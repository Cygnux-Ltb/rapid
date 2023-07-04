
# 订单服务接口
## 查询Order

**URL:** `/order`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 查询Order



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|td|int32|true|    交易日|-|
|strategyId|int32|true|    策略ID|-|
|investorId|string|true|    交易账户|-|
|code|string|true|交易标的|-|


**Request-example:**
```
curl -X GET -i /order?td=0&strategyId=0&investorId=&code=
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|tradingDay|int32|交易日|-|
|strategyId|int32|策略ID|-|
|instrumentCode|string|交易标的代码|-|
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
|offerPrice|double|委托价格|-|
|offerQty|int32|委托数量|-|
|insertTime|string|创建时间|-|
|updateTime|string|更新时间|-|
|cancelTime|string|取消时间|-|
|frontId|int32|前置机ID|-|
|sessionId|int32|会话ID|-|
|fee|double|交易费用|-|
|channelType|string|交易通道类型|-|
|remark|string|备注|-|

**Response-example:**
```
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
    "ordType": "",
    "orderRef": "",
    "direction": "",
    "side": "",
    "offerPrice": 0.0,
    "offerQty": 0,
    "insertTime": "yyyy-MM-dd HH:mm:ss",
    "updateTime": "yyyy-MM-dd HH:mm:ss",
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
|td|int32|true|交易日|-|
|strategyId|int32|true|策略ID|-|


**Request-example:**
```
curl -X GET -i /order/event?td=0&strategyId=0
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
|ordSysId|int64|ord_sys_id [*]|-|
|orderRef|string|order_ref|-|
|orderMsgType|int32|order_msg_type|-|
|ordOffset|string|ord_offset|-|
|direction|string|direction|-|
|limitPrice|double|limit_price double 19_4|-|
|status|int32|order_status char|-|
|statusMsg|string|status_msg|-|
|brokerSysID|int64|brokerSysID|-|
|volume|int32|volume int|-|
|volumeFilled|int32|volume_filled int|-|
|volumeRemained|int32|volume_remained int|-|
|price|double|price double 19_4|-|
|tradeId|string|trade_id varchar 21|-|
|ordRejReason|int32|ord_rej_reason|-|
|insertTime|int32|insert_time|-|
|updateTime|int32|update_time|-|
|cancelTime|int32|cancel_time|-|
|remark|string|remark|-|

**Response-example:**
```
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
    "ordOffset": "",
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
    "insertTime": 0,
    "updateTime": 0,
    "cancelTime": 0,
    "remark": ""
  }
]
```

## 新增订单

**URL:** `/order`

**Type:** `PUT`


**Content-Type:** `APPLICATION_JSON_UTF8`

**Description:** 新增订单





**Request-example:**
```
curl -X PUT -H 'Content-Type: APPLICATION_JSON_UTF8' -i /order
```

**Response-example:**
```
OK
```

