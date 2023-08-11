-- Database: ds0

-- DROP DATABASE IF EXISTS ds0;

CREATE DATABASE ds0
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
    
-- Table: public.t_order0

-- DROP TABLE IF EXISTS public.t_order0;

CREATE TABLE IF NOT EXISTS public.t_order0
(
    order_id bigint NOT NULL,
    user_id numeric NOT NULL,
    description text COLLATE pg_catalog."default" NOT NULL,
    created_time timestamp without time zone NOT NULL,
    CONSTRAINT t_order0_pkey PRIMARY KEY (order_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.t_order0
    OWNER to postgres;

-- Table: public.undo_log
-- DROP TABLE IF EXISTS public.undo_log;
CREATE TABLE public.undo_log
(
    id            serial NOT NULL,
    branch_id     numeric NOT NULL,
    xid           text NOT NULL,
    context       varchar(128) NOT NULL,
    rollback_info jsonb     NOT NULL,
    log_status    numeric NOT NULL,
    log_created   timestamp without time zone NOT NULL,
    log_modified  timestamp without time zone NOT NULL,
    CONSTRAINT undo_log_pkey PRIMARY KEY (id),
    CONSTRAINT ux_undo_log UNIQUE (xid,branch_id)
) WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.t_order0
    OWNER to postgres;

