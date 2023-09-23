
# 投资组合(股票池/目标池)服务
## 获取用户投资组合<br>[股票池/目标池]

**URL:** `/portfolio`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取用户投资组合
[股票池/目标池]



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|userId|int32|true|       用户ID|-|
|portfolio|string|true|投资组合(股票池/目标池)名称|-|


**Request-example:**
```
curl -X GET -i /portfolio?userId=0&portfolio=
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|userId|int32|用户ID|-|
|portfolioName|string|投资组合(股票池/目标池)名称|-|
|instrumentCodes|array|交易标的(股票/期货)列表|-|

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

## 获取用户第一投资组合 (左侧优先股票池)

**URL:** `/portfolio/first`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取用户第一投资组合 (左侧优先股票池)



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|userId|int32|true|用户ID|-|


**Request-example:**
```
curl -X GET -i /portfolio/first?userId=0
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|userId|int32|用户ID|-|
|portfolioName|string|投资组合(股票池/目标池)名称|-|
|instrumentCodes|array|交易标的(股票/期货)列表|-|

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

## 获取用户第二投资组合 (右侧次优股票池)

**URL:** `/portfolio/second`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取用户第二投资组合 (右侧次优股票池)



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|userId|int32|true|用户ID|-|


**Request-example:**
```
curl -X GET -i /portfolio/second?userId=0
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|userId|int32|用户ID|-|
|portfolioName|string|投资组合(股票池/目标池)名称|-|
|instrumentCodes|array|交易标的(股票/期货)列表|-|

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

