## Create  Java Web basic codes. 
Base on table schema to generate code to reduce duplcated work, use PG to do type mapping, if you want to use it for another database, you can update mapping in `db_cli.go`
```
var (
	TypeMapping = map[string]string{
		"int4":      "int",
		"numeric":   "int",
		"timestamp": "Date",
		"text":      "String",
		"int8":      "long",
		"bool":      "boolean",
	}
)
```

### Generate Code include: 
- ***Domain***
- ****Mapper***
- ****Service***
- ****ServiceImpl***
- ****Controller***

### Usage
- **workspace**: code that store in local workspace.
- **project**: project that will use to create package and folder.
- **domain**: assume that use multiple maven modules, domain will in separate folder.
- **host**: database host.
- **port**: database port.
- **dbname**: database name.
- **user**: database user name.
- **pwd**: database password.
- **tables**: tables that will be generate codes, use ',' to separate table.



