
# 历史行情接口
## 获取1分钟BAR

**URL:** `/bar`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取1分钟BAR



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|td|int32|true|    交易日|-|
|code|string|true|标的代码 (不支持查询多个标的)|-|


**Request-example:**
```
curl -X GET -i /bar?td=0&code=
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|instrumentCode|string|交易标的代码|-|
|tradingDay|int32|交易日|-|
|actualDate|int32|实际日期|-|
|timePoint|int32|时间点|-|
|open|double|开盘价|-|
|high|double|最高价|-|
|low|double|最低价|-|
|close|double|收盘价|-|
|volume|double|成交量|-|
|turnover|double|成交额|-|

**Response-example:**
```
[
  {
    "instrumentCode": "",
    "tradingDay": 0,
    "actualDate": 0,
    "timePoint": 0,
    "open": 0.0,
    "high": 0.0,
    "low": 0.0,
    "close": 0.0,
    "volume": 0.0,
    "turnover": 0.0
  }
]
```

## 更新BAR [內部接口]

**URL:** `/bar`

**Type:** `POST`


**Content-Type:** `APPLICATION_JSON_UTF8`

**Description:** 更新BAR (內部接口)





**Request-example:**
```
curl -X POST -H 'Content-Type: APPLICATION_JSON_UTF8' -i /bar
```

**Response-example:**
```
OK
```

