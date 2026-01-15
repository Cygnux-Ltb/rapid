
# [REST] - 用户服务
## 用户登陆

**URL:** `/user/v1/signin`

**Type:** `POST`


**Content-Type:** `application/json`

**Description:** 用户登陆




**Body-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|sign|string|false|用户名/邮箱/手机号|-|
|password|string|false|密码|-|
|verifyCode|string|false|验证码|-|

**Request-example:**
```bash
curl -X POST -H 'Content-Type: application/json' -i /user/v1/signin --data '{
  "sign": "",
  "password": "",
  "verifyCode": ""
}'
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|authenticated|boolean|是否已验证|-|
|userid|int32|用户ID|-|
|message|string|消息内容|-|
|securityCode|int64|安全码|-|

**Response-example:**
```json
{
  "authenticated": true,
  "userid": 0,
  "message": "",
  "securityCode": 0
}
```

## 用户注册, 当前不支持新用户注册

**URL:** `/user/v1/signup`

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
```bash
curl -X POST -i /user/v1/signup
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

