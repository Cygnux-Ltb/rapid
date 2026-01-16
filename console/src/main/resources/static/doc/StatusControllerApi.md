
# [REST] - 交易系统状态服务
## 获取全部策略状态

**URL:** `/status/v1`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取全部策略状态





**Request-example:**
```bash
curl -X GET -i /status/v1
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|productId|int32|产品ID|-|
|strategyId|int32|策略ID|-|
|instrumentCode|string|交易标的|-|
|tradable|boolean|可交易标识|-|

**Response-example:**
```json
[
  {
    "productId": 0,
    "strategyId": 0,
    "instrumentCode": "",
    "tradable": true
  }
]
```

## 发送状态指令

**URL:** `/status/v1/command`

**Type:** `PUT`


**Content-Type:** `application/json;charset=utf-8`

**Description:** 发送状态指令





**Request-example:**
```bash
curl -X PUT -H 'Content-Type: application/json;charset=utf-8' -i /status/v1/command
```

**Response-example:**
```json
OK
```

## 更新状态

**URL:** `/status/v1/update`

**Type:** `PUT`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 更新状态



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|prodCode|int32|true|int|-|


**Request-example:**
```bash
curl -X PUT -i /status/v1/update
```

**Response-example:**
```json
OK
```

