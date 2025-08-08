CREATE SCHEMA if not exists sofka;
ALTER SCHEMA sofka OWNER TO "sofka_owner";

--Persona
CREATE table IF NOT EXISTS persona
(
    id_per                  BIGSERIAL primary key,
    nombre_per              text NOT NULL,
    genero_per              varchar(5) NOT NULL,
    edad_per                integer NOT NULL CHECK (edad_per >= 0) ,
    identificacion_per      varchar(30) NOT NULL UNIQUE,
    direccion_per           text NOT NULL,
    telefono_per            varchar(10) NOT NULL
)

ALTER TABLE sofka.persona
    OWNER to "sofka_owner";

--Cliente
CREATE table IF NOT EXISTS cliente
(
    id_cli             BIGSERIAL primary key,
    contrasenia_cli     text NOT NULL,,
    estado_cli          boolean NOT NULL,
    id_per              bigint
                        constraint fk_cliente_persona_id_fk
                        references sofka.persona(id_per)
)

ALTER TABLE sofka.cliente
    OWNER to "sofka_owner";

--Cuenta
CREATE TABLE tipo_cuenta (
    id_tipo_cue SERIAL PRIMARY KEY,
    nombre_tipo_cue VARCHAR(50) NOT NULL UNIQUE,
    descripcion TEXT
);

CREATE table IF NOT EXISTS cuenta
(
    id_cue              BIGSERIAL primary key,
    numero_cue          text NOT NULL,
    tipo_cue            INT NOT NULL REFERENCES tipo_cuenta(id_tipo_cue),
    saldo_inicial_cue   numeric(19, 2) NOT NULL,
    estado_cue          boolesn NOT NULL,
    id_cli              bigint
                    constraint fk_cuenta_cliente_id_fk
                    references sofka.cliente(id_cli)
)

ALTER TABLE sofka.cuenta
    OWNER to "sofka_owner";

--Movimiento
CREATE TABLE tipo_movimiento (
    id_tipo_mov SERIAL PRIMARY KEY,
    nombre_tipo_mov VARCHAR(50) NOT NULL UNIQUE,
    descripcion TEXT
);

CREATE table IF NOT EXISTS movimiento
(
    id_mov          BIGSERIAL primary key,
    fecha_mov       timestamp NOT NULL,
    tipo_mov        INT NOT NULL REFERENCES tipo_movimiento(id_tipo_mov),
    valor_mov       numeric(19, 2) NOT NULL,
    saldo_mov       numeric(19, 2) NOT NULL,
    id_cue          bigint
                    constraint fk_movimiento_cuenta_id_fk
                    references sofka.cuenta(id_cue)
)

ALTER TABLE sofka.movimiento
    OWNER to "sofka_owner";
