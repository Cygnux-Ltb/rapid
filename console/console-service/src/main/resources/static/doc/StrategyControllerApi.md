
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
    "uid": 110,
    "strategyId": 981,
    "strategyName": "janetta.rippin",
    "strategyOwner": "f6p0yf",
    "strategyInfo": "458fy1"
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
curl -X GET -i /strategy/258
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
  "uid": 810,
  "strategyId": 995,
  "strategyName": "janetta.rippin",
  "strategyOwner": "7kq2v3",
  "strategyInfo": "k7iyr7"
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
curl -X GET -i /strategy/33/param
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
    "uid": 49,
    "strategyId": 387,
    "strategyName": "janetta.rippin",
    "ownerType": "kf7mea",
    "owner": "8nl73i",
    "paramName": "janetta.rippin",
    "paramType": "ss3j7y",
    "paramValue": "o5095a"
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
curl -X PUT -H 'Content-Type: APPLICATION_JSON_UTF8' -i /strategy/23/param
```

**Response-example:**
```
true
```

