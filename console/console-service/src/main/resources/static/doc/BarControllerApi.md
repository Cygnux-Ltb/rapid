
# 历史行情接口
## 获取1分钟Bar

**URL:** `/bar`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取行情Bar



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|tradingDay|int32|true|    交易日|-|
|instrumentCode|string|true|标的代码|-|


**Request-example:**
```
curl -X GET -i /bar?tradingDay=239&instrumentCode=96385 --data '&239&96385'
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|instrumentCode|string|交易标的代码 [*]|-|
|tradingDay|int32|交易日 [*]|-|
|actualDate|int32|实际日期 [*]|-|
|timePoint|int32|时间点 [*]|-|
|open|double|开盘价 [*]|-|
|high|double|最高价 [*]|-|
|low|double|最低价 [*]|-|
|close|double|收盘价 [*]|-|
|volume|double|成交量 [*]|-|
|turnover|double|成交额 [*]|-|

**Response-example:**
```
[
  {
    "instrumentCode": "96385",
    "tradingDay": 638,
    "actualDate": 426,
    "timePoint": 662,
    "open": 57.94,
    "high": 25.94,
    "low": 58.41,
    "close": 68.40,
    "volume": 48.67,
    "turnover": 85.16
  }
]
```

## Put Bar

**URL:** `/bar`

**Type:** `POST`


**Content-Type:** `APPLICATION_JSON_UTF8`

**Description:** 添加行情Bar





**Request-example:**
```
curl -X POST -H 'Content-Type: APPLICATION_JSON_UTF8' -i /bar
```

**Response-example:**
```
OK
```

