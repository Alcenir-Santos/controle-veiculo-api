create table t_adresses (
    id serial not null,
    complement varchar(255),
    district varchar(255),
    street varchar(255),
    zip_code varchar(255),
    city_id int4,
    primary key (id)
);

create table t_city (
    id serial not null,
    code_ibge varchar(255),
    name varchar(255) not null,
    state_id int4,
    primary key (id)
);

create table t_state (
    id serial not null,
    abbreviation varchar(255) not null,
    name varchar(200) not null,
    primary key (id)
);

create table t_user (
    id serial not null,
    email varchar(200) not null,
    name varchar(200) not null,
    password varchar(240) not null,
    username varchar(50) not null,
    primary key (id)
);

create table t_user_roles (
    id int4 not null,
    role_id varchar(255)
);

alter table
    t_state
add
    constraint UK_states unique (abbreviation);

alter table
    t_city
add
    constraint FK_city foreign key (state_id) references t_state;

alter table
    t_adresses
add
    constraint FK_Adresses foreign key (city_id) references t_city;

alter table
    t_user_roles
add
    constraint FK_user_role foreign key (id) references t_user;

insert into
    public.t_state (abbreviation, name)
values
    ('AC', 'ACRE');

insert into
    public.t_state (abbreviation, name)
values
    ('AL', 'ALAGOAS');

insert into
    public.t_state (abbreviation, name)
values
    ('AP', 'AMAPÁ');

insert into
    public.t_state (abbreviation, name)
values
    ('AM', 'AMAZONAS');

insert into
    public.t_state (abbreviation, name)
values
    ('BA', 'BAHIA');

insert into
    public.t_state (abbreviation, name)
values
    ('CE', 'CEARÁ');

insert into
    public.t_state (abbreviation, name)
values
    ('ES', 'ESPÍRITO SANTO');

insert into
    public.t_state (abbreviation, name)
values
    ('GO', 'GOIÁS');

insert into
    public.t_state (abbreviation, name)
values
    ('MA', 'MARANHÃO');

insert into
    public.t_state (abbreviation, name)
values
    ('MT', 'MATO GROSSO');

insert into
    public.t_state (abbreviation, name)
values
    ('MS', 'MATO GROSSO DO SUL');

insert into
    public.t_state (abbreviation, name)
values
    ('MG', 'MINAS GERAIS');

insert into
    public.t_state (abbreviation, name)
values
    ('PA', 'PARÁ');

insert into
    public.t_state (abbreviation, name)
values
    ('PB', 'PARAÍBA');

insert into
    public.t_state (abbreviation, name)
values
    ('PR', 'PARANÁ');

insert into
    public.t_state (abbreviation, name)
values
    ('PE', 'PERNAMBUCO');

insert into
    public.t_state (abbreviation, name)
values
    ('PI', 'PIAUÍ');

insert into
    public.t_state (abbreviation, name)
values
    ('RJ', 'RIO DE JANEIRO');

insert into
    public.t_state (abbreviation, name)
values
    ('RN', 'RIO GRANDE DO NORTE');

insert into
    public.t_state (abbreviation, name)
values
    ('RS', 'RIO GRANDE DO SUL');

insert into
    public.t_state (abbreviation, name)
values
    ('RO', 'RONDÔNIA');

insert into
    public.t_state (abbreviation, name)
values
    ('RR', 'RORAIMA');

insert into
    public.t_state (abbreviation, name)
values
    ('SC', 'SANTA CATARINA');

insert into
    public.t_state (abbreviation, name)
values
    ('SP', 'SÃO PAULO');

insert into
    public.t_state (abbreviation, name)
values
    ('SE', 'SERGIPE');

insert into
    public.t_state (abbreviation, name)
values
    ('TO', 'TOCANTINS');

INSERT INTO
    public.t_user (email, "name", "password", username)
VALUES
    (
        'teste@teste.com',
        'root',
        '$2a$10$lH0HjbBkhAs2YfOcRUUHqOHuVrJQsNlRBrsmSTCNB/VTIJ2ovurx.',
        'root'
    );

INSERT INTO
    public.t_user_roles (id, role_id)
VALUES
    (1, 'USERS'),
    (1, 'MANAGERS');