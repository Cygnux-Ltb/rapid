
# 交易系统状态服务
## 获取全部策略状态

**URL:** `/status`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取全部策略状态





**Request-example:**
```bash
curl -X GET -i /status
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|productId|int32|No comments found.|-|
|strategyId|int32|No comments found.|-|
|instrumentCode|string|No comments found.|-|
|tradable|boolean|No comments found.|-|

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

**URL:** `/status/command`

**Type:** `PUT`


**Content-Type:** `APPLICATION_JSON_UTF8`

**Description:** 发送状态指令





**Request-example:**
```bash
curl -X PUT -H 'Content-Type: APPLICATION_JSON_UTF8' -i /status/command
```

**Response-example:**
```json
OK
```

## 更新状态

**URL:** `/status/update`

**Type:** `PUT`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 更新状态



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|productId|int32|true|int|-|


**Request-example:**
```bash
curl -X PUT -i /status/update
```

**Response-example:**
```json
OK
```

