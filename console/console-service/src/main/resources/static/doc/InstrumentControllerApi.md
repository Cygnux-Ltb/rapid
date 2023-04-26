
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
curl -X GET -i /instrument/settlement?tradingDay=197&instrumentCode=96385 --data '&197&96385'
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|instrumentCode|string|交易标的代码 [*]|-|
|tradingDay|int32|交易日 [*]|-|
|closePrice|double|收盘价|-|
|openPrice|double|开盘价|-|
|settlementPrice|double|结算价|-|

**Response-example:**
```
[
  {
    "instrumentCode": "96385",
    "tradingDay": 929,
    "closePrice": 1.24,
    "openPrice": 79.41,
    "settlementPrice": 85.58
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
curl -X GET -i /instrument/last?instrumentCodes=u2vbta --data '&u2vbta'
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
    "instrumentCode": "96385",
    "lastPrice": 62.92
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
curl -X GET -i /instrument/tradable/83/ci7tys
```

**Response-example:**
```
OK
```

