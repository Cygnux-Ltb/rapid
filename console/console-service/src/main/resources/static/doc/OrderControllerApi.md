
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
curl -X GET -i /order/853?strategyId=204&investorId=172&instrumentCode=38827 --data '&204&172&38827'
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
    "uid": 147,
    "tradingDay": 963,
    "strategyId": 639,
    "instrumentCode": "38827",
    "investorId": "172",
    "brokerId": "172",
    "accountId": 98,
    "subAccountId": 297,
    "userId": 558,
    "ordSysId": 824,
    "ordType": "3cn34e",
    "ordRef": "x2h0xo",
    "direction": "x",
    "side": "t",
    "offerPrice": 36.94,
    "offerQty": 853,
    "insertTime": "2023-05-02 01:00:50",
    "updateTime": "2023-05-02 01:00:50",
    "cancelTime": "2023-05-02 01:00:50",
    "frontId": 43,
    "sessionId": 783,
    "fee": 9.64,
    "adaptorType": "kyygud",
    "remark": "wvhnz0"
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
curl -X GET -i /order/status?tradingDay=335&strategyId=275 --data '&335&275'
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
    "uid": 493,
    "tradingDay": 104,
    "strategyId": 590,
    "instrumentCode": "38827",
    "investorId": "172",
    "brokerId": "172",
    "accountId": 183,
    "subAccountId": 848,
    "userId": 992,
    "ordSysId": 713,
    "ordRef": "5nze4w",
    "ordMsgType": 558,
    "ordOffset": "r",
    "direction": "r",
    "limitPrice": 90.92,
    "status": 55,
    "statusMsg": "ya9c9p",
    "brokerSysID": 511,
    "volume": 167,
    "volumeFilled": 934,
    "volumeRemained": 342,
    "price": 27.74,
    "tradeId": "172",
    "ordRejReason": 675,
    "insertTime": 922,
    "updateTime": 151,
    "cancelTime": 435,
    "remark": "cl6wz2"
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

