
# 系统指令服务[X]
## 更新参数 [内部接口]

**URL:** `/command/param`

**Type:** `POST`


**Content-Type:** `APPLICATION_JSON_UTF8`

**Description:** 更新参数 [内部接口]



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|productId|int32|true|产品ID|-|

**Body-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|uid|int64|false|No comments found.|-|
|ownerGroup|string|false|No comments found.|-|
|ownerName|string|false|No comments found.|-|
|paramGroup|string|false|No comments found.|-|
|paramName|string|false|No comments found.|-|
|paramType|string|false|No comments found.|-|
|paramValue|string|false|No comments found.|-|

**Request-example:**
```
curl -X POST -H 'Content-Type: APPLICATION_JSON_UTF8' -i /command/param?productId=0 --data '[
  {
    "uid": 0,
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
```
OK
```

## 安全更新参数

**URL:** `/command/safe`

**Type:** `PUT`


**Content-Type:** `APPLICATION_JSON_UTF8`

**Description:** 安全更新参数





**Request-example:**
```
curl -X PUT -H 'Content-Type: APPLICATION_JSON_UTF8' -i /command/safe
```

**Response-example:**
```
OK
```

