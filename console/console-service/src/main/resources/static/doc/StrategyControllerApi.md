
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
|strategyId|int32|No comments found.|-|
|strategyName|string|No comments found.|-|
|strategyOwner|string|No comments found.|-|
|strategyInfo|string|No comments found.|-|

**Response-example:**
```
[
  {
    "strategyId": 0,
    "strategyName": "",
    "strategyOwner": "",
    "strategyInfo": ""
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
curl -X GET -i /strategy/0
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|strategyId|int32|No comments found.|-|
|strategyName|string|No comments found.|-|
|strategyOwner|string|No comments found.|-|
|strategyInfo|string|No comments found.|-|

**Response-example:**
```
{
  "strategyId": 0,
  "strategyName": "",
  "strategyOwner": "",
  "strategyInfo": ""
}
```

## 使用StrategyId作为URI访问Param

**URL:** `/strategy/{strategyName}/param`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 使用StrategyId作为URI访问Param


**Path-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyName|string|true|String|-|



**Request-example:**
```
curl -X GET -i /strategy/param
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|group|string|参数组|-|
|name|string|名称|-|
|paramName|string|参数名|-|
|paramType|string|参数类型|-|
|paramValue|string|参数值|-|

**Response-example:**
```
[
  {
    "group": "",
    "name": "",
    "paramName": "",
    "paramType": "",
    "paramValue": ""
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
curl -X PUT -H 'Content-Type: APPLICATION_JSON_UTF8' -i /strategy/0/param
```

**Response-example:**
```
true
```

