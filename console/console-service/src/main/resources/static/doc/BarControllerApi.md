
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
curl -X GET -i /bar?tradingDay=298&instrumentCode=38827 --data '&298&38827'
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
    "instrumentCode": "38827",
    "tradingDay": 728,
    "actualDate": 318,
    "timePoint": 35,
    "open": 54.75,
    "high": 34.12,
    "low": 46.58,
    "close": 89.36,
    "volume": 95.00,
    "turnover": 93.03
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

