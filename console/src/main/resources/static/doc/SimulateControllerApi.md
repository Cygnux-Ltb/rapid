
# [REST] - 模拟测试服务
## 提交测试请求

**URL:** `/simulate/v1/start`

**Type:** `POST`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 提交测试请求



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|userid|int32|false|[不可为空] 用户ID|-|
|strategyId|string|false|[不可为空] 策略ID|-|
|portfolioCode|string|false|[不可为空] 投资组合(股票池/目标池) 代码|-|
|offerAmount|double|false|[不可为空] 委托金额|-|
|startTime|string|false|[可为空] 开始时间, 精确到秒, 时间格式[YYYYMMDD-HHMMSS]|-|
|endTime|string|false|[可为空] 结束时间, 精确到秒, 时间格式[YYYYMMDD-HHMMSS]|-|


**Request-example:**
```bash
curl -X POST -i /simulate/v1/start
```

**Response-example:**
```json
OK
```

