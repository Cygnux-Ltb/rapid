
# [REST] - 产品服务
## 获取全部产品

**URL:** `/product/v1`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取全部产品





**Request-example:**
```bash
curl -X GET -i /product/v1
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|productId|int32|产品ID|-|
|productName|string|产品名称|-|
|subAccountId|string|子账户ID|-|
|userId|string|用户ID|-|
|interfaceType|string|接口名称|-|
|tradable|boolean|可交易标识|-|

**Response-example:**
```json
[
  {
    "productId": 0,
    "productName": "",
    "subAccountId": "",
    "userId": "",
    "interfaceType": "",
    "tradable": true
  }
]
```

## 获取指定产品信息

**URL:** `/product/v1/get`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取指定产品信息



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|prodCode|string|true|String|-|


**Request-example:**
```bash
curl -X GET -i /product/v1/get?prodCode=
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|productId|int32|产品ID|-|
|productName|string|产品名称|-|
|subAccountId|string|子账户ID|-|
|userId|string|用户ID|-|
|interfaceType|string|接口名称|-|
|tradable|boolean|可交易标识|-|

**Response-example:**
```json
{
  "productId": 0,
  "productName": "",
  "subAccountId": "",
  "userId": "",
  "interfaceType": "",
  "tradable": true
}
```

## 产品初始化

**URL:** `/product/v1/init`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 产品初始化



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyId|int32|true|No comments found.|-|
|status|int32|true|No comments found.|-|


**Request-example:**
```bash
curl -X GET -i /product/v1/init?strategyId=0&status=0
```

**Response-example:**
```json
OK
```

