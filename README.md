## Apache Shardingsphere + Seata + Postgresql integration.

*Current, a project will be split some of micro services, most of business generate huge of data, and some business operations must use transaction, use a single databse instance or just use a W/R database instance maybe not a good choise, so the example show how to use shardingsphere to split databases/tables and how to use seata to do distributed transaction, I choise postgresql not mysql just because I like postgresql more than mysql. :)*

### Apache Shardingsphere

**A framework inited by JD, then contributed it to Apach, you can use it easy to do database or table level split**

I assume it easily to use, but actually it contains lots of issues, the major issue it looks like don't support springboot v3.

### Seata

**A system own by Ali, it can help you setup a distributed transaction.**

We need start a seata server and create some required tables in both seata database and business database.

## Sentinel

*Sentinel as a traffic management framework, consducted by Alibaba, it includes **circuit breaker**, **Throlting**, and it provides different strategies that used to protent system, such as threads, qps..*
