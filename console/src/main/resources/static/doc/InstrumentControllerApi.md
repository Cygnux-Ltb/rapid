
# [REST] - 交易标的服务
## 获取交易标的

**URL:** `/instrument/v1/all`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取交易标的





**Request-example:**
```bash
curl -X GET -i /instrument/v1/all
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|instrumentCode|string|交易标的代码|-|
|instrumentType|string|交易标的类型|-|
|exchangeCode|string|交易所代码|-|
|fee|double|交易费用|-|
|tradable|boolean|可交易标识|-|

**Response-example:**
```json
[
  {
    "instrumentCode": "",
    "instrumentType": "",
    "exchangeCode": "",
    "fee": 0.0,
    "tradable": true
  }
]
```

## 获取交易标的

**URL:** `/instrument/v1`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取交易标的



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|code|string|true|交易标的 [查询多个交易标的使用','分割]|-|


**Request-example:**
```bash
curl -X GET -i /instrument/v1?code=
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|instrumentCode|string|交易标的代码|-|
|instrumentType|string|交易标的类型|-|
|exchangeCode|string|交易所代码|-|
|fee|double|交易费用|-|
|tradable|boolean|可交易标识|-|

**Response-example:**
```json
[
  {
    "instrumentCode": "",
    "instrumentType": "",
    "exchangeCode": "",
    "fee": 0.0,
    "tradable": true
  }
]
```

## 获取结算信息

**URL:** `/instrument/v1/settlement`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取结算信息



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|td|int32|true|    int|-|
|code|string|true|交易标的 [查询多个交易标的使用','分割]|-|


**Request-example:**
```bash
curl -X GET -i /instrument/v1/settlement?td=0&code=
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|tradingDay|int32|交易日|-|
|instrumentCode|string|交易标的代码|-|
|closePrice|double|收盘价|-|
|openPrice|double|开盘价|-|
|highestPrice|double|最高价|-|
|lowestPrice|double|最低价|-|
|settlementPrice|double|结算价|-|

**Response-example:**
```json
[
  {
    "tradingDay": 0,
    "instrumentCode": "",
    "closePrice": 0.0,
    "openPrice": 0.0,
    "highestPrice": 0.0,
    "lowestPrice": 0.0,
    "settlementPrice": 0.0
  }
]
```

## 获取最新价格

**URL:** `/instrument/v1/price`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取最新价格



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|code|string|true|交易标的 [查询多个交易标的使用','分割]|-|


**Request-example:**
```bash
curl -X GET -i /instrument/v1/price?code=
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|lastPrices|array|No comments found.|-|
|└─instrumentCode|string|交易标的|-|
|└─lastPrice|double|最新价格|-|
|└─change|double|涨跌幅|-|

**Response-example:**
```json
{
  "lastPrices": [
    {
      "instrumentCode": "",
      "lastPrice": 0.0,
      "change": 0.0
    }
  ]
}
```

## 更新最新价格 (內部接口)

**URL:** `/instrument/v1/price`

**Type:** `PUT`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 更新最新价格 (內部接口)





**Request-example:**
```bash
curl -X PUT -i /instrument/v1/price
```

**Response-example:**
```json
OK
```

## 获取交易费用

**URL:** `/instrument/v1/fee`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取交易费用



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|code|string|true|交易标的 [查询多个标的使用','分割]|-|


**Request-example:**
```bash
curl -X GET -i /instrument/v1/fee?code=
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|instrumentCode|string|交易标的代码|-|
|instrumentType|string|交易标的类型|-|
|exchangeCode|string|交易所代码|-|
|fee|double|交易费用|-|
|tradable|boolean|可交易标识|-|

**Response-example:**
```json
[
  {
    "instrumentCode": "",
    "instrumentType": "",
    "exchangeCode": "",
    "fee": 0.0,
    "tradable": true
  }
]
```

## 获取可交易标的

**URL:** `/instrument/v1/tradable`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取可交易标的



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|td|int32|true|    交易日|-|
|code|string|true|交易标的 [查询多个标的使用','分割]|-|


**Request-example:**
```bash
curl -X GET -i /instrument/v1/tradable?td=0&code=
```

**Response-example:**
```json
OK
```

