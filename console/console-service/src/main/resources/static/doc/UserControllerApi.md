
# 用户服务
## 用户登陆

**URL:** `/user/signin`

**Type:** `POST`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 用户登陆



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|sign|string|false|    用户名/邮箱/手机号|-|
|password|string|false|密码|-|


**Request-example:**
```
curl -X POST -i /user/signin
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|authenticated|boolean|是否已验证|-|
|message|string|消息内容|-|
|securityCode|int64|安全码|-|

**Response-example:**
```
{
  "authenticated": true,
  "message": "",
  "securityCode": 0
}
```

## 用户注册, 当前不支持新用户注册

**URL:** `/user/signup`

**Type:** `POST`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 用户注册, 当前不支持新用户注册



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|sign|string|false|    标识|-|
|type|int32|false|    标识类型|-|
|password|string|false|密码|-|


**Request-example:**
```
curl -X POST -i /user/signup
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|code|int32|No comments found.|-|
|message|string|No comments found.|-|
|array|boolean|No comments found.|-|
|data|object|No comments found.|-|

**Response-example:**
```
{
  "code": 0,
  "message": "",
  "array": true,
  "data": {}
}
```

