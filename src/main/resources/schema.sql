create table if not exists ingredients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY not null,
    ingredientIdentifier varchar(4) not null,
    name varchar(25) not null,
    type varchar(20) not null
);

create table if not exists tacos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY not null,
    name varchar(50) not null,
    createdAt timestamp not null
);

create table if not exists tacos_ingredients (
    taco BIGINT,
    ingredient bigint,
    foreign key (taco) references tacos(id),
    foreign key (ingredient) references ingredients(id)
);

create table if not exists orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY not null,
    deliveryName varchar(50) not null,
    deliveryStreet varchar(50) not null,
    deliveryCity varchar(50) not null,
    deliveryState varchar(50) not null,
    deliveryZip varchar(50) not null,
    ccNumber varchar(16) not null,
    ccExpiration varchar(5) not null,
    ccCVV varchar(3) not null,
    placedAt timestamp not null
);

create table if not exists taco_orders (
    tacoOrder BIGINT,
    taco BIGINT,
    foreign key (tacoOrder) references orders(id),
    foreign key (taco) references tacos(id)
);

