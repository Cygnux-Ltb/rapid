
# PNL服务接口
## 查询PNL

**URL:** `/pnl/{tradingDay}`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 查询PNL


**Path-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|tradingDay|int32|true|int|-|

**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyId|int32|true|int|-|


**Request-example:**
```
curl -X GET -i /pnl/515?strategyId=757 --data '&757'
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|uid|int64|No comments found.|-|
|strategyId|int32|strategyId|-|
|instrumentCode|string|instrumentCode|-|
|tradingDay|int32|tradingDay|-|
|avgBuyPrice|double|avgBuyPrice|-|
|avgSellPrice|double|avgSellPrice|-|
|buyQuantity|int32|buyQuantity|-|
|sellQuantity|int32|sellQuantity|-|
|todayLong|int32|todayLong|-|
|todayShort|int32|todayShort|-|
|yesterdayLong|int32|yesterdayLong|-|
|yesterdayShort|int32|yesterdayShort|-|
|netPosition|int32|netPosition|-|
|aggregatedFee|double|aggregatedFee|-|
|approved|int32|approved|-|
|turnover|int32|turnover|-|

**Response-example:**
```
[
  {
    "uid": 120,
    "strategyId": 528,
    "instrumentCode": "96385",
    "tradingDay": 522,
    "avgBuyPrice": 57.34,
    "avgSellPrice": 52.15,
    "buyQuantity": 207,
    "sellQuantity": 531,
    "todayLong": 730,
    "todayShort": 410,
    "yesterdayLong": 212,
    "yesterdayShort": 581,
    "netPosition": 417,
    "aggregatedFee": 6.76,
    "approved": 723,
    "turnover": 660
  }
]
```

## Put PnlDaily

**URL:** `/pnl`

**Type:** `PUT`


**Content-Type:** `APPLICATION_JSON_UTF8`

**Description:** Put PnlDaily





**Request-example:**
```
curl -X PUT -H 'Content-Type: APPLICATION_JSON_UTF8' -i /pnl
```

**Response-example:**
```
OK
```

## 查询结算PNL

**URL:** `/pnl/settlement`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 查询结算PNL



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyId|int32|true|int|-|
|tradingDay|int32|true|int|-|


**Request-example:**
```
curl -X GET -i /pnl/settlement?strategyId=154&tradingDay=390 --data '&154&390'
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|uid|int64|No comments found.|-|
|strategyId|int32|strategyId|-|
|instrumentCode|string|instrumentCode|-|
|tradingDay|int32|tradingDay|-|
|position|int32|position|-|
|pnlTotal|double|pnlTotal|-|
|pnlNet|double|pnlNet|-|
|tradeCost|double|tradeCost|-|
|exposure|double|exposure|-|
|approved|int32|approved|-|

**Response-example:**
```
[
  {
    "uid": 138,
    "strategyId": 755,
    "instrumentCode": "96385",
    "tradingDay": 855,
    "position": 810,
    "pnlTotal": 54.56,
    "pnlNet": 92.29,
    "tradeCost": 98.60,
    "exposure": 46.93,
    "approved": 511
  }
]
```

