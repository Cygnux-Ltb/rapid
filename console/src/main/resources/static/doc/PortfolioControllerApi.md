
# [REST] - 投资组合(股票池/目标池)服务
## 添加投资标的(目标池页面中'添加分组'和'添加股票'调用)<br>(1).创建(或修改)目标池时需要提供 'userid', 'portfolioCode', 'portfolioName'<br>(2).添加投资标的时需要提供 'userid', 'portfolioCode', 'instrumentCode'<br>(3).当提供全部4个参数时, 将创建(或修改)目标池, 并将投资标的加入目标池中

**URL:** `/portfolio/v1/new`

**Type:** `POST`


**Content-Type:** `application/json;charset=utf-8`

**Description:** 添加投资标的(目标池页面中'添加分组'和'添加股票'调用)
(1).创建(或修改)目标池时需要提供 'userid', 'portfolioCode', 'portfolioName'
(2).添加投资标的时需要提供 'userid', 'portfolioCode', 'instrumentCode'
(3).当提供全部4个参数时, 将创建(或修改)目标池, 并将投资标的加入目标池中




**Body-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|userid|int32|false|[不可为空] 用户ID|-|
|portfolioCode|string|false|[不可为空] 投资组合(股票池/目标池)代码|-|
|portfolioName|string|false|[可为空] 投资组合(股票池/目标池)名称|-|
|instrumentCode|string|false|[可为空] 交易标的(股票代码/期货代码)|-|

**Request-example:**
```bash
curl -X POST -H 'Content-Type: application/json;charset=utf-8' -i /portfolio/v1/new --data '{
  "userid": 0,
  "portfolioCode": "",
  "portfolioName": "",
  "instrumentCode": ""
}'
```

**Response-example:**
```json
OK
```

## 删除指定用户投资组合<br>(1).删除投资组合(股票池), 需要提供 'userid', 'portfolioCode'<br>(2).删除投资组合(股票池)中的标的, 需要提供 'userid', 'portfolioCode', 'instruments', 其中 'instruments' 为数组类型

**URL:** `/portfolio/v1/delete`

**Type:** `DELETE`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 删除指定用户投资组合
(1).删除投资组合(股票池), 需要提供 'userid', 'portfolioCode'
(2).删除投资组合(股票池)中的标的, 需要提供 'userid', 'portfolioCode', 'instruments', 其中 'instruments' 为数组类型



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|userid|int32|true|       (int 必须项) 用户ID|-|
|code|string|true|(String 必须项) 投资组合(股票池/目标池)代码|-|
|instruments|string|true|  (String[] 非必须项) 投资标的代码(可提供多个投资标的代码, 使用','分割)|-|


**Request-example:**
```bash
curl -X DELETE -i /portfolio/v1/delete?userid=0&code=&instruments=
```

**Response-example:**
```json
OK
```

## 获取用户投资组合(股票池/目标池)列表

**URL:** `/portfolio/v1/list`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取用户投资组合(股票池/目标池)列表



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|userid|int32|true|(int) 用户ID|-|


**Request-example:**
```bash
curl -X GET -i /portfolio/v1/list?userid=0
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|userid|int32|用户ID|-|
|portfolioList|array|投资组合(股票池/目标池) 列表|-|
|└─portfolioCode|string|投资组合(股票池/目标池) 代码|-|
|└─portfolioName|string|投资组合(股票池/目标池) 名称|-|

**Response-example:**
```json
{
  "userid": 0,
  "portfolioList": [
    {
      "portfolioCode": "",
      "portfolioName": ""
    }
  ]
}
```

## 获取用户投资组合(股票池/目标池)详细信息

**URL:** `/portfolio/v1/detail`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取用户投资组合(股票池/目标池)详细信息



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|userid|int32|true|       (int)    用户ID|-|
|code|string|true|(String) 投资组合(股票池/目标池)名称|-|


**Request-example:**
```bash
curl -X GET -i /portfolio/v1/detail?userid=0&code=
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|userid|int32|用户ID|-|
|portfolioCode|string|投资组合(股票池/目标池) 代码|-|
|portfolioName|string|投资组合(股票池/目标池) 名称|-|
|instruments|array|标的(股票/期货) 列表|-|
|└─instrumentCode|string|标的(股票/期货) 代码|-|
|└─instrumentName|string|标的(股票/期货) 名称|-|
|└─lastPrice|double|标的(股票/期货) 最新价格|-|
|└─change|double|标的(股票/期货) 涨跌幅|-|
|└─netPos|double|标的(股票/期货) 净持仓|-|
|└─netPnl|double|标的(股票/期货) 净盈亏|-|

**Response-example:**
```json
{
  "userid": 0,
  "portfolioCode": "",
  "portfolioName": "",
  "instruments": [
    {
      "instrumentCode": "",
      "instrumentName": "",
      "lastPrice": 0.0,
      "change": 0.0,
      "netPos": 0.0,
      "netPnl": 0.0
    }
  ]
}
```

## 获取用户第一投资组合 (首页左侧优先股票池)

**URL:** `/portfolio/v1/first`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取用户第一投资组合 (首页左侧优先股票池)



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|userid|int32|true|(int) 用户ID|-|


**Request-example:**
```bash
curl -X GET -i /portfolio/v1/first?userid=0
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|userid|int32|用户ID|-|
|portfolioCode|string|投资组合(股票池/目标池) 代码|-|
|portfolioName|string|投资组合(股票池/目标池) 名称|-|
|instruments|array|标的(股票/期货) 列表|-|
|└─instrumentCode|string|标的(股票/期货) 代码|-|
|└─instrumentName|string|标的(股票/期货) 名称|-|
|└─lastPrice|double|标的(股票/期货) 最新价格|-|
|└─change|double|标的(股票/期货) 涨跌幅|-|
|└─netPos|double|标的(股票/期货) 净持仓|-|
|└─netPnl|double|标的(股票/期货) 净盈亏|-|

**Response-example:**
```json
{
  "userid": 0,
  "portfolioCode": "",
  "portfolioName": "",
  "instruments": [
    {
      "instrumentCode": "",
      "instrumentName": "",
      "lastPrice": 0.0,
      "change": 0.0,
      "netPos": 0.0,
      "netPnl": 0.0
    }
  ]
}
```

## 获取用户第二投资组合 (首页右侧次优股票池)

**URL:** `/portfolio/v1/second`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取用户第二投资组合 (首页右侧次优股票池)



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|userid|int32|true|(int) 用户ID|-|


**Request-example:**
```bash
curl -X GET -i /portfolio/v1/second?userid=0
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|userid|int32|用户ID|-|
|portfolioCode|string|投资组合(股票池/目标池) 代码|-|
|portfolioName|string|投资组合(股票池/目标池) 名称|-|
|instruments|array|标的(股票/期货) 列表|-|
|└─instrumentCode|string|标的(股票/期货) 代码|-|
|└─instrumentName|string|标的(股票/期货) 名称|-|
|└─lastPrice|double|标的(股票/期货) 最新价格|-|
|└─change|double|标的(股票/期货) 涨跌幅|-|
|└─netPos|double|标的(股票/期货) 净持仓|-|
|└─netPnl|double|标的(股票/期货) 净盈亏|-|

**Response-example:**
```json
{
  "userid": 0,
  "portfolioCode": "",
  "portfolioName": "",
  "instruments": [
    {
      "instrumentCode": "",
      "instrumentName": "",
      "lastPrice": 0.0,
      "change": 0.0,
      "netPos": 0.0,
      "netPnl": 0.0
    }
  ]
}
```

