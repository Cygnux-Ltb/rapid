
# [REST] - 历史行情服务
## 获取1分钟BAR

**URL:** `/bar/v1`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取1分钟BAR



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|code|string|true|标的代码 (不支持查询多个标的)|-|
|td|int32|true|    交易日|-|


**Request-example:**
```bash
curl -X GET -i /bar/v1?td=0&code=
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
|volume|int32|成交量|-|
|turnover|double|成交额|-|

**Response-example:**
```json
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
    "volume": 0,
    "turnover": 0.0
  }
]
```

## 更新BAR [內部接口]

**URL:** `/bar/v1`

**Type:** `POST`


**Content-Type:** `application/json;charset=utf-8`

**Description:** 更新BAR (內部接口)





**Request-example:**
```bash
curl -X POST -H 'Content-Type: application/json;charset=utf-8' -i /bar/v1
```

**Response-example:**
```json
OK
```

