## Integration.

### Result

#### Database/Table split
- Dependency
```
		<dependency>
			<groupId>org.apache.shardingsphere</groupId>
			<artifactId>shardingsphere-jdbc-core-spring-boot-starter</artifactId>
			<version>5.1.2</version>
		</dependency>
```
- Rules

*Detail configuration plese see application.properties*

    - Database split rules
    ```
    spring.shardingsphere.rules.sharding.sharding-algorithms.database_inline.type=INLINE
    spring.shardingsphere.rules.sharding.sharding-algorithms.database_inline.props.algorithm-expression=ds${user_id % 2}
    ```
    - Table split rules
    ```
    spring.shardingsphere.rules.sharding.sharding-algorithms.table_inline.type=INLINE
    spring.shardingsphere.rules.sharding.sharding-algorithms.table_inline.props.algorithm-expression=t_order${order_id % 2}
    ```
- Results
    - Database split results
        - userId=1
        ```
        2023-08-08 15:31:22.085  INFO 45504 --- [nio-8080-exec-5] ShardingSphere-SQL                       : Logic SQL: insert into t_order(order_id,user_id,description,created_time) values(?,?,?,?)
        2023-08-08 15:31:22.086  INFO 45504 --- [nio-8080-exec-5] ShardingSphere-SQL                       : SQLStatement: PostgreSQLInsertStatement(withSegment=Optional.empty)
        2023-08-08 15:31:22.086  INFO 45504 --- [nio-8080-exec-5] ShardingSphere-SQL                       : Actual SQL: ds1 ::: insert into t_order1(order_id,user_id,description,created_time) values(?, ?, ?, ?) ::: [31, 1, DEMO, 2023-08-08 15:31:22.071]
        ```
        - userId=2
        ```
        2023-08-08 15:37:24.044  INFO 45504 --- [nio-8080-exec-9] ShardingSphere-SQL                       : Logic SQL: insert into t_order(order_id,user_id,description,created_time) values(?,?,?,?)
        2023-08-08 15:37:24.045  INFO 45504 --- [nio-8080-exec-9] ShardingSphere-SQL                       : SQLStatement: PostgreSQLInsertStatement(withSegment=Optional.empty)
        2023-08-08 15:37:24.045  INFO 45504 --- [nio-8080-exec-9] ShardingSphere-SQL                       : Actual SQL: ds0 ::: insert into t_order1(order_id,user_id,description,created_time) values(?, ?, ?, ?) ::: [33, 2, DEMO, 2023-08-08 15:37:24.032]
        ```
    - Table split results
        - orderId=34
        ```
        2023-08-08 15:40:25.504  INFO 45504 --- [nio-8080-exec-1] ShardingSphere-SQL                       : Logic SQL: insert into t_order(order_id,user_id,description,created_time) values(?,?,?,?)
        2023-08-08 15:40:25.504  INFO 45504 --- [nio-8080-exec-1] ShardingSphere-SQL                       : SQLStatement: PostgreSQLInsertStatement(withSegment=Optional.empty)
        2023-08-08 15:40:25.505  INFO 45504 --- [nio-8080-exec-1] ShardingSphere-SQL                       : Actual SQL: ds0 ::: insert into t_order0(order_id,user_id,description,created_time) values(?, ?, ?, ?) ::: [34, 2, DEMO, 2023-08-08 15:40:25.495]
        ```
        - orderId=35
        ```
        2023-08-08 15:41:09.078  INFO 45504 --- [nio-8080-exec-3] ShardingSphere-SQL                       : Logic SQL: insert into t_order(order_id,user_id,description,created_time) values(?,?,?,?)
        2023-08-08 15:41:09.078  INFO 45504 --- [nio-8080-exec-3] ShardingSphere-SQL                       : SQLStatement: PostgreSQLInsertStatement(withSegment=Optional.empty)
        2023-08-08 15:41:09.079  INFO 45504 --- [nio-8080-exec-3] ShardingSphere-SQL                       : Actual SQL: ds0 ::: insert into t_order1(order_id,user_id,description,created_time) values(?, ?, ?, ?) ::: [35, 2, DEMO, 2023-08-08 15:41:09.064]
        ```

#### Distributed transaction

- Dependencies

```
		<dependency>
			<groupId>org.apache.shardingsphere</groupId>
			<artifactId>shardingsphere-transaction-base-seata-at</artifactId>
			<version>5.1.2</version>
		</dependency>
		<dependency>
			<groupId>io.seata</groupId>
			<artifactId>seata-all</artifactId>
			<version>1.7.0</version>
		</dependency>
```

- Configuration

*It base on BASE model, two phase committment.*
    
    - seata.conf 
        contains application basic information.
    - registry.conf
        contains seata server information.
    - file.conf
        contains group information, be sure that contains configuration which specify as value of transaction.service.group in seata.conf

- Results

```
2023-08-08 15:40:25.501  INFO 45504 --- [nio-8080-exec-1] i.seata.tm.api.DefaultGlobalTransaction  : Begin new global transaction [192.168.0.104:8091:5323676813342056907]
...
2023-08-08 15:40:25.554  INFO 45504 --- [nio-8080-exec-1] io.seata.rm.AbstractResourceManager      : branch register success, xid:192.168.0.104:8091:5323676813342056907, branchId:5323676813342056909, lockKeys:t_order0:34
2023-08-08 15:40:25.560  INFO 45504 --- [nio-8080-exec-1] i.seata.tm.api.DefaultGlobalTransaction  : transaction 192.168.0.104:8091:5323676813342056907 will be commit
2023-08-08 15:40:25.573  INFO 45504 --- [nio-8080-exec-1] i.seata.tm.api.DefaultGlobalTransaction  : transaction end, xid = 192.168.0.104:8091:5323676813342056907 
2023-08-08 15:40:25.574  INFO 45504 --- [nio-8080-exec-1] i.seata.tm.api.DefaultGlobalTransaction  : [192.168.0.104:8091:5323676813342056907] commit status: Committed
2023-08-08 15:40:26.439  INFO 45504 --- [h_RMROLE_1_5_32] i.s.c.r.p.c.RmBranchCommitProcessor      : rm client handle branch commit process:BranchCommitRequest{xid='192.168.0.104:8091:5323676813342056907', branchId=5323676813342056909, branchType=AT, resourceId='jdbc:postgresql://localhost:5432/ds0', applicationData='null'} 
2023-08-08 15:40:26.440  INFO 45504 --- [h_RMROLE_1_5_32] io.seata.rm.AbstractRMHandler            : Branch committing: 192.168.0.104:8091:5323676813342056907 5323676813342056909 jdbc:postgresql://localhost:5432/ds0 null
2023-08-08 15:40:26.440  INFO 45504 --- [h_RMROLE_1_5_32] io.seata.rm.AbstractRMHandler            : Branch commit result: PhaseTwo_Committed
```
### Last Update
- 2023-08-11: add custom key generator and sharding algorithm though SPI. 

### Problems

- Shardingsphere 5.x version seems not support springboot v3, so I downgrade springboot to 2.7.14
- Some fields of yaml incorrect in offical documents, so I changed to property.
- Can't use autoincreasement ID, need use a distributed ID generator, such as SNOWFLAKE.
- If use ZK as seata servers registration center and discovery center, it looks can't successful, need do more research.
- Be sure that files list above need exist in seata client.
- Using seata-all dependency, need use `Transactional` instead of `GlobalTransactional`.
