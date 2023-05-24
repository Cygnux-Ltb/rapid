
# 交易标的查询接口
## 获取结算价格

**URL:** `/instrument/settlement`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取结算价格



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|tradingDay|int32|true|    int|-|
|instrumentCode|string|true|String|-|


**Request-example:**
```
curl -X GET -i /instrument/settlement?tradingDay=0&instrumentCode=
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|instrumentCode|string|交易标的代码|-|
|tradingDay|int32|交易日|-|
|closePrice|double|收盘价|-|
|openPrice|double|开盘价|-|
|settlementPrice|double|结算价|-|

**Response-example:**
```
[
  {
    "instrumentCode": "",
    "tradingDay": 0,
    "closePrice": 0.0,
    "openPrice": 0.0,
    "settlementPrice": 0.0
  }
]
```

## 获取最新价格

**URL:** `/instrument/last`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取最新价格



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|instrumentCodes|string|true|String|-|


**Request-example:**
```
curl -X GET -i /instrument/last?instrumentCodes=
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|instrumentCode|string|No comments found.|-|
|lastPrice|double|No comments found.|-|

**Response-example:**
```
[
  {
    "instrumentCode": "",
    "lastPrice": 0.0
  }
]
```

## 更新最新价格

**URL:** `/instrument/last`

**Type:** `PUT`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 更新最新价格





**Request-example:**
```
curl -X PUT -i /instrument/last
```

**Response-example:**
```
OK
```

## 获取可交易的标的

**URL:** `/instrument/tradable/{tradingDay}/{symbol}`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取可交易的标的


**Path-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|tradingDay|int32|true|String|-|
|symbol|string|true|    String|-|



**Request-example:**
```
curl -X GET -i /instrument/tradable/0/
```

**Response-example:**
```
OK
```

