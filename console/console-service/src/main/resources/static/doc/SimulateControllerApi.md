
# 模拟测试
## 提交测试请求

**URL:** `/simulate/start`

**Type:** `POST`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 提交测试请求



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|userId|int32|false|用户ID|-|
|strategyId|int32|false||-|
|tradingDay|int32|false||-|


**Request-example:**
```bash
curl -X POST -i /simulate/start
```

**Response-example:**
```json
OK
```

