
# 策略服务
## 获取全部策略

**URL:** `/strategy`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取全部策略





**Request-example:**
```
curl -X GET -i /strategy
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|strategyId|int32|策略ID|-|
|strategyName|string|策略名称|-|
|strategyType|string|策略类型|-|
|strategyInfo|string|策略相关信息|-|

**Response-example:**
```
[
  {
    "strategyId": 0,
    "strategyName": "",
    "strategyType": "",
    "strategyInfo": ""
  }
]
```

## 获取策略

**URL:** `/strategy/get`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取策略



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyId|int32|true|策略ID|-|


**Request-example:**
```
curl -X GET -i /strategy/get?strategyId=0
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|strategyId|int32|策略ID|-|
|strategyName|string|策略名称|-|
|strategyType|string|策略类型|-|
|strategyInfo|string|策略相关信息|-|

**Response-example:**
```
{
  "strategyId": 0,
  "strategyName": "",
  "strategyType": "",
  "strategyInfo": ""
}
```

## 根据策略名称获取策略相关参数

**URL:** `/strategy/param`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 根据策略名称获取策略相关参数



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyName|string|true|String|-|


**Request-example:**
```
curl -X GET -i /strategy/param?strategyName=
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

## 添加策略参数 (内部接口)

**URL:** `/strategy/param`

**Type:** `PUT`


**Content-Type:** `APPLICATION_JSON_UTF8`

**Description:** 添加策略参数 (内部接口)



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyId|int32|true|int|-|


**Request-example:**
```
curl -X PUT -H 'Content-Type: APPLICATION_JSON_UTF8' -i /strategy/param --data 'strategyId=0'
```

**Response-example:**
```
true
```

