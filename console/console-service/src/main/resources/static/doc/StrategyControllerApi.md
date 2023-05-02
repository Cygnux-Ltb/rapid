
# 策略服务
## 返回全部Strategy

**URL:** `/strategy`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 返回全部Strategy





**Request-example:**
```
curl -X GET -i /strategy
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|uid|int64|No comments found.|-|
|strategyId|int32|No comments found.|-|
|strategyName|string|No comments found.|-|
|strategyOwner|string|No comments found.|-|
|strategyInfo|string|No comments found.|-|

**Response-example:**
```
[
  {
    "uid": 95,
    "strategyId": 66,
    "strategyName": "sona.kuhic",
    "strategyOwner": "d81lw0",
    "strategyInfo": "rvbirk"
  }
]
```

## 使用StrategyId作为get params访问Strategy

**URL:** `/strategy/{strategyId}`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 使用StrategyId作为get params访问Strategy


**Path-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyId|int32|true|int|-|



**Request-example:**
```
curl -X GET -i /strategy/565
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|uid|int64|No comments found.|-|
|strategyId|int32|No comments found.|-|
|strategyName|string|No comments found.|-|
|strategyOwner|string|No comments found.|-|
|strategyInfo|string|No comments found.|-|

**Response-example:**
```
{
  "uid": 152,
  "strategyId": 690,
  "strategyName": "sona.kuhic",
  "strategyOwner": "afp5hh",
  "strategyInfo": "pr3t5h"
}
```

## 使用StrategyId作为URI访问Param

**URL:** `/strategy/{strategyId}/param`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 使用StrategyId作为URI访问Param


**Path-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyId|int32|true|int|-|



**Request-example:**
```
curl -X GET -i /strategy/431/param
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|uid|int64|No comments found.|-|
|strategyId|int32|No comments found.|-|
|strategyName|string|No comments found.|-|
|ownerType|string|No comments found.|-|
|owner|string|No comments found.|-|
|paramName|string|No comments found.|-|
|paramType|string|No comments found.|-|
|paramValue|string|No comments found.|-|

**Response-example:**
```
[
  {
    "uid": 88,
    "strategyId": 115,
    "strategyName": "sona.kuhic",
    "ownerType": "gqqc8c",
    "owner": "vbjnos",
    "paramName": "sona.kuhic",
    "paramType": "m6pj0a",
    "paramValue": "kje42c"
  }
]
```

## Put StrategyParam URI is StrategyId

**URL:** `/strategy/{strategyId}/param`

**Type:** `PUT`


**Content-Type:** `APPLICATION_JSON_UTF8`

**Description:** Put StrategyParam URI is StrategyId


**Path-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyId|int32|true|int|-|



**Request-example:**
```
curl -X PUT -H 'Content-Type: APPLICATION_JSON_UTF8' -i /strategy/229/param
```

**Response-example:**
```
true
```

