-- Database: seata

-- DROP DATABASE IF EXISTS seata;

CREATE DATABASE seata
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- Table: public.branch_table

-- DROP TABLE IF EXISTS public.branch_table;

CREATE TABLE IF NOT EXISTS public.branch_table
(
    branch_id bigint NOT NULL,
    xid character varying(128) COLLATE pg_catalog."default" NOT NULL,
    transaction_id bigint,
    resource_group_id character varying(32) COLLATE pg_catalog."default",
    resource_id character varying(256) COLLATE pg_catalog."default",
    branch_type character varying(8) COLLATE pg_catalog."default",
    status smallint,
    client_id character varying(64) COLLATE pg_catalog."default",
    application_data character varying(2000) COLLATE pg_catalog."default",
    gmt_create timestamp(6) without time zone,
    gmt_modified timestamp(6) without time zone,
    CONSTRAINT pk_branch_table PRIMARY KEY (branch_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.branch_table
    OWNER to postgres;
-- Index: idx_xid

-- DROP INDEX IF EXISTS public.idx_xid;

CREATE INDEX IF NOT EXISTS idx_xid
    ON public.branch_table USING btree
    (xid COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;

-- Table: public.distributed_lock

-- DROP TABLE IF EXISTS public.distributed_lock;

CREATE TABLE IF NOT EXISTS public.distributed_lock
(
    lock_key character varying(20) COLLATE pg_catalog."default" NOT NULL,
    lock_value character varying(20) COLLATE pg_catalog."default" NOT NULL,
    expire bigint NOT NULL,
    CONSTRAINT pk_distributed_lock_table PRIMARY KEY (lock_key)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.distributed_lock
    OWNER to postgres;

-- Table: public.global_table

-- DROP TABLE IF EXISTS public.global_table;

CREATE TABLE IF NOT EXISTS public.global_table
(
    xid character varying(128) COLLATE pg_catalog."default" NOT NULL,
    transaction_id bigint,
    status smallint NOT NULL,
    application_id character varying(32) COLLATE pg_catalog."default",
    transaction_service_group character varying(32) COLLATE pg_catalog."default",
    transaction_name character varying(128) COLLATE pg_catalog."default",
    timeout integer,
    begin_time bigint,
    application_data character varying(2000) COLLATE pg_catalog."default",
    gmt_create timestamp(0) without time zone,
    gmt_modified timestamp(0) without time zone,
    CONSTRAINT pk_global_table PRIMARY KEY (xid)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.global_table
    OWNER to postgres;
-- Index: idx_status_gmt_modified

-- DROP INDEX IF EXISTS public.idx_status_gmt_modified;

CREATE INDEX IF NOT EXISTS idx_status_gmt_modified
    ON public.global_table USING btree
    (status ASC NULLS LAST, gmt_modified ASC NULLS LAST)
    TABLESPACE pg_default;
-- Index: idx_transaction_id

-- DROP INDEX IF EXISTS public.idx_transaction_id;

CREATE INDEX IF NOT EXISTS idx_transaction_id
    ON public.global_table USING btree
    (transaction_id ASC NULLS LAST)
    TABLESPACE pg_default;

-- Table: public.lock_table

-- DROP TABLE IF EXISTS public.lock_table;

CREATE TABLE IF NOT EXISTS public.lock_table
(
    row_key character varying(128) COLLATE pg_catalog."default" NOT NULL,
    xid character varying(128) COLLATE pg_catalog."default",
    transaction_id bigint,
    branch_id bigint NOT NULL,
    resource_id character varying(256) COLLATE pg_catalog."default",
    table_name character varying(32) COLLATE pg_catalog."default",
    pk character varying(36) COLLATE pg_catalog."default",
    status smallint NOT NULL DEFAULT 0,
    gmt_create timestamp(0) without time zone,
    gmt_modified timestamp(0) without time zone,
    CONSTRAINT pk_lock_table PRIMARY KEY (row_key)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.lock_table
    OWNER to postgres;

COMMENT ON COLUMN public.lock_table.status
    IS '0:locked ,1:rollbacking';
-- Index: idx_branch_id

-- DROP INDEX IF EXISTS public.idx_branch_id;

CREATE INDEX IF NOT EXISTS idx_branch_id
    ON public.lock_table USING btree
    (branch_id ASC NULLS LAST)
    TABLESPACE pg_default;
-- Index: idx_lid

-- DROP INDEX IF EXISTS public.idx_lid;

CREATE INDEX IF NOT EXISTS idx_lid
    ON public.lock_table USING btree
    (xid COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;
-- Index: idx_status

-- DROP INDEX IF EXISTS public.idx_status;

CREATE INDEX IF NOT EXISTS idx_status
    ON public.lock_table USING btree
    (status ASC NULLS LAST)
    TABLESPACE pg_default;