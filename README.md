# Rapid

Rapid Comprehensive Strategy Trade System

```mermaid
flowchart TB
subgraph CORE["core（Domain + Ports/Interfaces + DTO/Event）"]
C1["Domain Model\n(Account/SubAccount/Order/Trade...)"]
C2["Ports/Interfaces\n(AccountStore, OrderIngress, MarketDataIngress...)"]
C3["DTO / Commands / Events"]
C2 --- C1
C3 --- C1
end

subgraph OUTER["外层模块（实现/使用 core）"]
ENG["engine"]
INF["infra (JPA/DB/CQ/Redis...)"]
STR["strategy (plugins)"]
ADP["adaptor (business mapping)"]
GTW["gateway (FIX/CTP/REST transport)"]
RT["runtime (boot/wiring/DI)"]
end

%% runtime wiring
RT --> ENG
RT --> INF
RT --> STR
RT --> ADP
RT --> GTW

%% compile-time depends-on
ENG --> CORE
INF --> CORE
STR --> CORE
ADP --> CORE

%% optional (if your gateway needs core DTO; otherwise remove this line)
GTW -. "optional" .-> CORE

%% adaptor-gateway relationship (typically depends on gateway API)
ADP --> GTW
