
# [UI] - 交易服务
## 

**URL:** `/trading/ui`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 





**Request-example:**
```bash
curl -X GET -i /trading/ui
```

**Response-example:**
```json
Forward or redirect to a page view.
```

## 

**URL:** `/trading/ui/go_add`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 





**Request-example:**
```bash
curl -X GET -i /trading/ui/go_add
```

**Response-example:**
```json
Forward or redirect to a page view.
```

## 

**URL:** `/trading/ui/go_edit`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|adaptorId|int32|true|No comments found.|-|


**Request-example:**
```bash
curl -X GET -i /trading/ui/go_edit?adaptorId=0
```

**Response-example:**
```json
Forward or redirect to a page view.
```

## 

**URL:** `/trading/ui/update`

**Type:** `POST`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|adaptorId|int32|false|Adaptor ID|-|
|adaptorName|string|false|Adaptor Name|-|
|adaptorType|string|false|Adaptor Type|-|
|remark|string|false|Remark|-|


**Request-example:**
```bash
curl -X POST -i /trading/ui/update
```

**Response-example:**
```json
Forward or redirect to a page view.
```

## 

**URL:** `/trading/ui/enable`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|adaptorId|int32|true|No comments found.|-|


**Request-example:**
```bash
curl -X GET -i /trading/ui/enable?adaptorId=0
```

**Response-example:**
```json
Forward or redirect to a page view.
```

## 

**URL:** `/trading/ui/disable`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|adaptorId|int32|true|No comments found.|-|


**Request-example:**
```bash
curl -X GET -i /trading/ui/disable?adaptorId=0
```

**Response-example:**
```json
Forward or redirect to a page view.
```

## 

**URL:** `/trading/ui/param`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 





**Request-example:**
```bash
curl -X GET -i /trading/ui/param
```

**Response-example:**
```json
Forward or redirect to a page view.
```

## 

**URL:** `/trading/ui/go_add_param`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 





**Request-example:**
```bash
curl -X GET -i /trading/ui/go_add_param
```

**Response-example:**
```json
Forward or redirect to a page view.
```

## 

**URL:** `/trading/ui/go_edit_param`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|adaptorId|int32|true| String|-|
|paramGroup|string|true|String|-|
|paramName|string|true| String|-|


**Request-example:**
```bash
curl -X GET -i /trading/ui/go_edit_param?adaptorId=0&paramGroup=&paramName=
```

**Response-example:**
```json
Forward or redirect to a page view.
```

## 

**URL:** `/trading/ui/update_param`

**Type:** `POST`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|adaptorId|int32|false|AdaptorId|-|
|paramGroup|string|false|ParamGroup|-|
|paramName|string|false|ParamName|-|
|paramValue|string|false|ParamValue|-|
|remark|string|false|Remark|-|


**Request-example:**
```bash
curl -X POST -i /trading/ui/update_param
```

**Response-example:**
```json
Forward or redirect to a page view.
```

