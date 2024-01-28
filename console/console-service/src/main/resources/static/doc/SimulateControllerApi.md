
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
|strategyId|int32|false|策略ID|-|
|startTradingDay|int32|false|开始交易日|-|
|endTradingDay|int32|false|结束交易日|-|


**Request-example:**
```bash
curl -X POST -i /simulate/start
```

**Response-example:**
```json
OK
```

