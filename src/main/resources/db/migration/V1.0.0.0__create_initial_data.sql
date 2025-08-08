CREATE SCHEMA if not exists sofka;
ALTER SCHEMA sofka OWNER TO "sofka_owner";

--Persona
CREATE table IF NOT EXISTS persona
(
    id_per                  BIGSERIAL primary key,
    nombre_per              text NOT NULL,
    genero_per              varchar(1),
    edad_per                integer CHECK (edad_per >= 0) ,
    identificacion_per      varchar(30),
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
                        constraint fk_cliente_persona_id
                        references sofka.persona(id_per)
)

ALTER TABLE sofka.cliente
    OWNER to "sofka_owner";

--Cuenta
CREATE table IF NOT EXISTS cuenta
(
    id_cue              BIGSERIAL primary key,
    numero_cue          text NOT NULL,
    tipo_cue            varchar(15) NOT NULL,
    saldo_inicial_cue   numeric(19, 2) NOT NULL,
    estado_cue          boolean NOT NULL,
    id_cli              bigint
                    constraint fk_cuenta_cliente_id
                    references sofka.cliente(id_cli)
)

ALTER TABLE sofka.cuenta
    OWNER to "sofka_owner";

--Movimiento
CREATE table IF NOT EXISTS movimiento
(
    id_mov          BIGSERIAL primary key,
    fecha_mov       timestamp NOT NULL,
    tipo_mov        varchar(15) NOT NULL,
    valor_mov       numeric(19, 2) NOT NULL,
    saldo_mov       numeric(19, 2) NOT NULL,
    id_cue          bigint
                    constraint fk_movimiento_cuenta_id
                    references sofka.cuenta(id_cue)
)

ALTER TABLE sofka.movimiento
    OWNER to "sofka_owner";
