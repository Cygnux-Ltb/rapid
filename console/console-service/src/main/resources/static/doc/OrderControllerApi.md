
# 订单服务接口
## 查询Order

**URL:** `/order/{tradingDay}`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 查询Order


**Path-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|tradingDay|int32|true|    String|-|

**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyId|int32|true|    int|-|
|investorId|string|true|    String|-|
|instrumentCode|string|true|int|-|


**Request-example:**
```
curl -X GET -i /order/594?strategyId=268&investorId=19&instrumentCode=96385 --data '&268&19&96385'
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|uid|int64|No comments found.|-|
|tradingDay|int32|tradingDay [*]|-|
|strategyId|int32|strategyId [*]|-|
|instrumentCode|string|instrumentCode [*]|-|
|investorId|string|investorId [*]|-|
|brokerId|string|brokerId [*]|-|
|accountId|int32|accountId [*]|-|
|subAccountId|int32|subAccountId [*]|-|
|userId|int32|userId [*]|-|
|ordSysId|int64|ordSysId [*]|-|
|ordType|string|ordType|-|
|ordRef|string|orderRef|-|
|direction|string|direction|-|
|side|string|side|-|
|offerPrice|double|offerPrice|-|
|offerQty|int32|offerQty|-|
|insertTime|string|insertTime|-|
|updateTime|string|updateTime|-|
|cancelTime|string|cancelTime|-|
|frontId|int32|frontId|-|
|sessionId|int32|sessionId|-|
|fee|double|fee double 19_4|-|
|adaptorType|string|adaptorType|-|
|remark|string|remark|-|

**Response-example:**
```
[
  {
    "uid": 843,
    "tradingDay": 460,
    "strategyId": 690,
    "instrumentCode": "96385",
    "investorId": "19",
    "brokerId": "19",
    "accountId": 862,
    "subAccountId": 27,
    "userId": 385,
    "ordSysId": 616,
    "ordType": "zw7fvg",
    "ordRef": "ngy939",
    "direction": "c",
    "side": "9",
    "offerPrice": 87.91,
    "offerQty": 132,
    "insertTime": "2023-04-26 16:44:29",
    "updateTime": "2023-04-26 16:44:29",
    "cancelTime": "2023-04-26 16:44:29",
    "frontId": 335,
    "sessionId": 45,
    "fee": 23.80,
    "adaptorType": "x6g34o",
    "remark": "3op9cv"
  }
]
```

## 获取订单最新状态

**URL:** `/order/status`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取订单最新状态



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|tradingDay|int32|true|int|-|
|strategyId|int32|true|int|-|


**Request-example:**
```
curl -X GET -i /order/status?tradingDay=792&strategyId=751 --data '&792&751'
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|uid|int64|No comments found.|-|
|tradingDay|int32|tradingDay [*]|-|
|strategyId|int32|strategyId [*]|-|
|instrumentCode|string|instrumentCode [*]|-|
|investorId|string|investorId [*]|-|
|brokerId|string|brokerId [*]|-|
|accountId|int32|accountId [*]|-|
|subAccountId|int32|subAccountId [*]|-|
|userId|int32|userId [*]|-|
|ordSysId|int64|ord_sys_id [*]|-|
|ordRef|string|order_ref|-|
|ordMsgType|int32|order_msg_type|-|
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
    "uid": 357,
    "tradingDay": 919,
    "strategyId": 398,
    "instrumentCode": "96385",
    "investorId": "19",
    "brokerId": "19",
    "accountId": 92,
    "subAccountId": 253,
    "userId": 73,
    "ordSysId": 822,
    "ordRef": "b1fgxp",
    "ordMsgType": 526,
    "ordOffset": "0",
    "direction": "m",
    "limitPrice": 82.05,
    "status": 801,
    "statusMsg": "s00mn5",
    "brokerSysID": 458,
    "volume": 961,
    "volumeFilled": 96,
    "volumeRemained": 774,
    "price": 82.25,
    "tradeId": "19",
    "ordRejReason": 344,
    "insertTime": 874,
    "updateTime": 877,
    "cancelTime": 146,
    "remark": "x1hzik"
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

