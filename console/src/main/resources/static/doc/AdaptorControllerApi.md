
# [UI] - 适配器服务
## [Adaptor]列表

**URL:** `/adaptor/ui`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** [Adaptor]列表





**Request-example:**
```bash
curl -X GET -i /adaptor/ui
```

**Response-example:**
```json
Forward or redirect to a page view.
```

## 添加[Adaptor]中转链接

**URL:** `/adaptor/ui/go_add`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 添加[Adaptor]中转链接





**Request-example:**
```bash
curl -X GET -i /adaptor/ui/go_add
```

**Response-example:**
```json
Forward or redirect to a page view.
```

## 编辑[Adaptor]中转链接

**URL:** `/adaptor/ui/go_edit`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 编辑[Adaptor]中转链接



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|adaptorId|int32|true|int|-|


**Request-example:**
```bash
curl -X GET -i /adaptor/ui/go_edit?adaptorId=0
```

**Response-example:**
```json
Forward or redirect to a page view.
```

## 修改[Adaptor]

**URL:** `/adaptor/ui/update`

**Type:** `POST`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 修改[Adaptor]



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|adaptorId|int32|false|Adaptor ID|-|
|adaptorName|string|false|Adaptor Name|-|
|adaptorType|string|false|Adaptor Type|-|
|remark|string|false|Remark|-|


**Request-example:**
```bash
curl -X POST -i /adaptor/ui/update
```

**Response-example:**
```json
Forward or redirect to a page view.
```

## 启用[Adaptor]

**URL:** `/adaptor/ui/enable`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 启用[Adaptor]



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|adaptorId|int32|true|int|-|


**Request-example:**
```bash
curl -X GET -i /adaptor/ui/enable?adaptorId=0
```

**Response-example:**
```json
Forward or redirect to a page view.
```

## 禁用[Adaptor]

**URL:** `/adaptor/ui/disable`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 禁用[Adaptor]



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|adaptorId|int32|true|int|-|


**Request-example:**
```bash
curl -X GET -i /adaptor/ui/disable?adaptorId=0
```

**Response-example:**
```json
Forward or redirect to a page view.
```

## [Adaptor参数]列表

**URL:** `/adaptor/ui/param`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** [Adaptor参数]列表





**Request-example:**
```bash
curl -X GET -i /adaptor/ui/param
```

**Response-example:**
```json
Forward or redirect to a page view.
```

## 添加[Adaptor参数]中转链接

**URL:** `/adaptor/ui/go_add_param`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 添加[Adaptor参数]中转链接





**Request-example:**
```bash
curl -X GET -i /adaptor/ui/go_add_param
```

**Response-example:**
```json
Forward or redirect to a page view.
```

## 编辑[Adaptor参数]中转链接

**URL:** `/adaptor/ui/go_edit_param`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 编辑[Adaptor参数]中转链接



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|adaptorId|int32|true| int|-|
|paramGroup|string|true|String|-|
|paramName|string|true| String|-|


**Request-example:**
```bash
curl -X GET -i /adaptor/ui/go_edit_param?adaptorId=0&paramGroup=&paramName=
```

**Response-example:**
```json
Forward or redirect to a page view.
```

## 修改[Adaptor参数]

**URL:** `/adaptor/ui/update_param`

**Type:** `POST`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 修改[Adaptor参数]



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
curl -X POST -i /adaptor/ui/update_param
```

**Response-example:**
```json
Forward or redirect to a page view.
```

