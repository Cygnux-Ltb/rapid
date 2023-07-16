
# 产品服务接口
## 获取全部产品

**URL:** `/product`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取全部产品





**Request-example:**
```
curl -X GET -i /product
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|productId|int32|产品ID|-|
|productName|string|产品名称|-|
|subAccountId|string|子账户ID|-|
|userId|string|用户ID|-|
|interfaceType|string|接口名称|-|

**Response-example:**
```
[
  {
    "productId": 0,
    "productName": "",
    "subAccountId": "",
    "userId": "",
    "interfaceType": ""
  }
]
```

## 获取指定产品信息

**URL:** `/product/get`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取指定产品信息



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|productId|int32|true|int|-|


**Request-example:**
```
curl -X GET -i /product/get?productId=0
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|productId|int32|产品ID|-|
|productName|string|产品名称|-|
|subAccountId|string|子账户ID|-|
|userId|string|用户ID|-|
|interfaceType|string|接口名称|-|

**Response-example:**
```
{
  "productId": 0,
  "productName": "",
  "subAccountId": "",
  "userId": "",
  "interfaceType": ""
}
```

## 产品初始化

**URL:** `/product/init`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 产品初始化



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyId|int32|true|No comments found.|-|
|status|int32|true|No comments found.|-|


**Request-example:**
```
curl -X GET -i /product/init?strategyId=0&status=0
```

**Response-example:**
```
OK
```

