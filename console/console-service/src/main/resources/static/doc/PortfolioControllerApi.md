
# 投资组合接口
## 

**URL:** `/portfolio`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|userId|int32|false|No comments found.|-|
|groupName|string|false|No comments found.|-|


**Request-example:**
```
curl -X GET -i /portfolio?userId=0&groupName=
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|userId|int32|No comments found.|-|
|groupName|string|No comments found.|-|
|instrumentCodes|array|No comments found.|-|

**Response-example:**
```
{
  "userId": 0,
  "groupName": "",
  "instrumentCodes": [
    ""
  ]
}
```

