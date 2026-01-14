CREATE SCHEMA if not exists devsu;
ALTER SCHEMA devsu OWNER TO "devsu_owner";

--Persona
CREATE table IF NOT EXISTS devsu.persona
(
    id_per                  BIGSERIAL primary key,
    nombre_per              text NOT NULL,
    genero_per              varchar(10),
    edad_per                integer CHECK (edad_per >= 0) ,
    identificacion_per      varchar(30) UNIQUE,
    direccion_per           text NOT NULL,
    telefono_per            varchar(10) NOT NULL
);

ALTER TABLE devsu.persona
    OWNER to "devsu_owner";

--Cliente
CREATE table IF NOT EXISTS devsu.cliente
(
    id_cli              BIGSERIAL primary key,
    contrasenia_cli     text NOT NULL,
    estado_cli          boolean NOT NULL,
    id_per              bigint
                        constraint fk_cliente_persona_id
                        references devsu.persona(id_per)
);

ALTER TABLE devsu.cliente
    OWNER to "devsu_owner";

--Cuenta
CREATE table IF NOT EXISTS devsu.cuenta
(
    id_cue              BIGSERIAL primary key,
    numero_cue          text NOT NULL,
    tipo_cue            varchar(15) NOT NULL,
    saldo_inicial_cue   numeric(19, 2) NOT NULL,
    estado_cue          boolean NOT NULL,
    id_cli              bigint
                    constraint fk_cuenta_cliente_id
                    references devsu.cliente(id_cli)
);

ALTER TABLE devsu.cuenta
    OWNER to "devsu_owner";

--Movimiento
CREATE table IF NOT EXISTS devsu.movimiento
(
    id_mov          BIGSERIAL primary key,
    fecha_mov       timestamp NOT NULL,
    tipo_mov        varchar(15) NOT NULL,
    valor_mov       numeric(19, 2) NOT NULL,
    saldo_mov       numeric(19, 2) NOT NULL,
    id_cue          bigint
                    constraint fk_movimiento_cuenta_id
                    references devsu.cuenta(id_cue)
);

ALTER TABLE devsu.movimiento
    OWNER to "devsu_owner";

-----GRANTS
GRANT
USAGE
ON
SCHEMA
devsu TO "devsu_user";

GRANT
SELECT,
INSERT
,
UPDATE,
DELETE
ON ALL TABLES IN SCHEMA devsu
    TO "devsu_user";

GRANT
SELECT,
UPDATE
    ON ALL
    SEQUENCES IN SCHEMA devsu
    TO "devsu_user";

GRANT
SELECT,
INSERT,
UPDATE,
DELETE
ON ALL TABLES IN SCHEMA public
    TO "devsu_user";