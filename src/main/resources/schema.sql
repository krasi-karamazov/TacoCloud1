create table if not exists ingredients (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(20) not null
);

create table if not exists tacos (
    id identity,
    name varchar(50) not null,
    createAt timestamp not null
);

create table if not exists taco_ingredients (
    taco bigint not null,
    ingredient varchar(4) not null,
    foreign key (taco) references tacos(id),
    foreign key (ingredient) references ingredients(id)
);

create table if not exists orders (
    id identity,
    deliveryName varchar(50) not null,
    deliveryStreet varchar(50) not null,
    delivertyCity varchar(50) not null,
    deliveryState varchar(50) not null,
    deliveryZip varchar(50) not null,
    ccNumber varchar(16) not null,
    ccExpiration varchar(5) not null,
    ccCVV varchar(3) not null,
    placedAt timestamp not null
);

create table if not exists taco_orders (
    tacoOrder bigint not null,
    taco bigint not null,
    foreign key (tacoOrder) references orders(id),
    foreign key (taco) references tacos(id)
);