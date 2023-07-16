
# 系统指令服务[X]
## 更新参数 [内部接口]

**URL:** `/command/param`

**Type:** `PUT`


**Content-Type:** `APPLICATION_JSON_UTF8`

**Description:** 更新参数 [内部接口]



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|productId|int32|true|产品ID|-|


**Request-example:**
```
curl -X PUT -H 'Content-Type: APPLICATION_JSON_UTF8' -i /command/param --data 'productId=0'
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

