
# 投资组合接口
## 获取用户投资组合<br>[股票池/目标池都通过此接口]

**URL:** `/portfolio`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取用户投资组合
[股票池/目标池都通过此接口]



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|userId|int32|true|       用户ID|-|
|portfolio|string|true|投资组合名称|-|


**Request-example:**
```
curl -X GET -i /portfolio?userId=0&portfolio=
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|userId|int32|用户ID|-|
|portfolioName|string|投资组合名称|-|
|instrumentCodes|array|交易标的列表|-|

**Response-example:**
```
{
  "userId": 0,
  "portfolioName": "",
  "instrumentCodes": [
    ""
  ]
}
```

