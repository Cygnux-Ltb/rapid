
# PNL服务(盈亏)
## 查询PNL (查询盈亏)

**URL:** `/pnl`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 查询PNL (查询盈亏)



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|td|int32|true|交易日|-|
|strategyId|int32|true|策略ID|-|


**Request-example:**
```
curl -X GET -i /pnl?td=0&strategyId=0
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|strategyId|int32|策略ID|-|
|instrumentCode|string|交易标的代码 [*]|-|
|tradingDay|int32|交易日 [*]|-|
|avgBuyPrice|double|平均多头价格|-|
|avgSellPrice|double|平均空头价格|-|
|buyQuantity|int32|多头数量|-|
|sellQuantity|int32|空头数量|-|
|todayLong|int32|今多头数量|-|
|todayShort|int32|今空头数量|-|
|yesterdayLong|int32|昨多头数量|-|
|yesterdayShort|int32|昨空头数量|-|
|netPosition|int32|净头寸|-|
|aggregatedFee|double|聚合交易手续费|-|
|turnover|int32|成交额|-|
|approved|int32|认证状态|-|

**Response-example:**
```
[
  {
    "strategyId": 0,
    "instrumentCode": "",
    "tradingDay": 0,
    "avgBuyPrice": 0.0,
    "avgSellPrice": 0.0,
    "buyQuantity": 0,
    "sellQuantity": 0,
    "todayLong": 0,
    "todayShort": 0,
    "yesterdayLong": 0,
    "yesterdayShort": 0,
    "netPosition": 0,
    "aggregatedFee": 0.0,
    "turnover": 0,
    "approved": 0
  }
]
```

## 更新PNL (内部接口, 策略引擎调用)

**URL:** `/pnl`

**Type:** `PUT`


**Content-Type:** `APPLICATION_JSON_UTF8`

**Description:** 更新PNL (内部接口, 策略引擎调用)





**Request-example:**
```
curl -X PUT -H 'Content-Type: APPLICATION_JSON_UTF8' -i /pnl
```

**Response-example:**
```
OK
```

## 查询结算PNL (查询结算盈亏)

**URL:** `/pnl/settlement`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 查询结算PNL (查询结算盈亏)



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|td|int32|true|交易日|-|
|strategyId|int32|true|策略ID|-|


**Request-example:**
```
curl -X GET -i /pnl/settlement?td=0&strategyId=0
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|strategyId|int32|策略ID|-|
|instrumentCode|string|交易标的代码 [*]|-|
|tradingDay|int32|交易日 [*]|-|
|position|int32|仓位|-|
|pnlTotal|double|盈亏|-|
|pnlNet|double|净盈亏|-|
|tradeCost|double|交易成本|-|
|exposure|double|风险暴露|-|
|approved|int32|认证状态|-|

**Response-example:**
```
[
  {
    "strategyId": 0,
    "instrumentCode": "",
    "tradingDay": 0,
    "position": 0,
    "pnlTotal": 0.0,
    "pnlNet": 0.0,
    "tradeCost": 0.0,
    "exposure": 0.0,
    "approved": 0
  }
]
```

