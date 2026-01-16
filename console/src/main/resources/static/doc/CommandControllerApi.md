
# [UI] - 系统指令服务[X]
## 更新参数 [内部接口]

**URL:** `/command/ui/param`

**Type:** `POST`


**Content-Type:** `application/json;charset=utf-8`

**Description:** 更新参数 [内部接口]



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|prodId|int32|true|产品ID|-|

**Body-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|uid|int64|false||-|
|paramCategory|string|true|[e.g. ADAPTOR, STRATEGY...]|-|
|ownerGroup|string|true|[e.g. CTP, SIMNOW]|-|
|ownerName|string|true|[e.g. 联通1, 电信1]|-|
|paramGroup|string|true|[e.g. ]|-|
|paramName|string|true||-|
|paramType|string|true||-|
|paramValue|string|true||-|

**Request-example:**
```bash
curl -X POST -H 'Content-Type: application/json;charset=utf-8' -i /command/ui/param?prodId=0 --data '[
  {
    "uid": 0,
    "paramCategory": "",
    "ownerGroup": "",
    "ownerName": "",
    "paramGroup": "",
    "paramName": "",
    "paramType": "",
    "paramValue": ""
  }
]'
```

**Response-example:**
```json
OK
```

## 安全更新参数

**URL:** `/command/ui/safe`

**Type:** `PUT`


**Content-Type:** `application/json;charset=utf-8`

**Description:** 安全更新参数





**Request-example:**
```bash
curl -X PUT -H 'Content-Type: application/json;charset=utf-8' -i /command/ui/safe
```

**Response-example:**
```json
OK
```

