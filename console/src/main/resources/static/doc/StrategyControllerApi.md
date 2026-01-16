
# [REST] - 策略服务
## 获取全部策略

**URL:** `/strategy/v1`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取全部策略





**Request-example:**
```bash
curl -X GET -i /strategy/v1
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|strategyId|int32|策略ID|-|
|strategyName|string|策略名称|-|
|strategyType|string|策略类型|-|
|remark|string|策略相关信息|-|
|enabled|boolean|可交易标识|-|

**Response-example:**
```json
[
  {
    "strategyId": 0,
    "strategyName": "",
    "strategyType": "",
    "remark": "",
    "enabled": true
  }
]
```

## 获取策略

**URL:** `/strategy/v1/get`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取策略



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyId|int32|true|策略ID|-|


**Request-example:**
```bash
curl -X GET -i /strategy/v1/get?strategyId=0
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|strategyId|int32|策略ID|-|
|strategyName|string|策略名称|-|
|strategyType|string|策略类型|-|
|remark|string|策略相关信息|-|
|enabled|boolean|可交易标识|-|

**Response-example:**
```json
{
  "strategyId": 0,
  "strategyName": "",
  "strategyType": "",
  "remark": "",
  "enabled": true
}
```

## 根据策略名称获取策略相关参数

**URL:** `/strategy/v1/param`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 根据策略名称获取策略相关参数



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyName|string|true|String|-|


**Request-example:**
```bash
curl -X GET -i /strategy/v1/param?strategyName=
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
```json
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

**URL:** `/strategy/v1/param`

**Type:** `POST`


**Content-Type:** `application/json;charset=utf-8`

**Description:** 添加策略参数 (内部接口)



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyId|int32|true|int|-|


**Request-example:**
```bash
curl -X POST -H 'Content-Type: application/json;charset=utf-8' -i /strategy/v1/param --data 'strategyId=0'
```

**Response-example:**
```json
true
```

