
# 历史行情服务
## 获取1分钟BAR

**URL:** `/bar`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取1分钟BAR



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|code|string|true|标的代码 (不支持查询多个标的)|-|
|td|int32|true|    交易日|-|


**Request-example:**
```bash
curl -X GET -i /bar?td=0&code=
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|code|int32|No comments found.|-|
|message|string|No comments found.|-|
|info|string|No comments found.|-|
|array|boolean|No comments found.|-|
|data|object|No comments found.|-|

**Response-example:**
```json
{
  "code": 0,
  "message": "",
  "info": "",
  "array": true,
  "data": {}
}
```

## 更新BAR [內部接口]

**URL:** `/bar`

**Type:** `POST`


**Content-Type:** `APPLICATION_JSON_UTF8`

**Description:** 更新BAR (內部接口)





**Request-example:**
```bash
curl -X POST -H 'Content-Type: APPLICATION_JSON_UTF8' -i /bar
```

**Response-example:**
```json
OK
```

