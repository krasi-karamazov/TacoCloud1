delete from ingredients;
delete from orders;
delete from tacos;
delete from taco_ingredients;
delete from taco_orders;


insert into ingredients(ingredientIdentifier, name, type) values ('FLTO', 'Flour Tortilla', 'WRAP');
insert into ingredients(ingredientIdentifier, name, type) values ('COTO', 'Corn Torilla', 'WRAP');
insert into ingredients(ingredientIdentifier, name, type) values ('GRBF', 'Ground Beef', 'PROTEIN');
insert into ingredients(ingredientIdentifier, name, type) values ('CARN', 'Carnitas', 'PROTEIN');
insert into ingredients(ingredientIdentifier, name, type) values ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into ingredients(ingredientIdentifier, name, type) values ('LETC', 'Lettuce', 'VEGGIES');
insert into ingredients(ingredientIdentifier, name, type) values ('CHED', 'Cheddar', 'CHEESE');
insert into ingredients(ingredientIdentifier, name, type) values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into ingredients(ingredientIdentifier, name, type) values ('SALS', 'Salsa', 'SAUSE');
insert into ingredients(ingredientIdentifier, name, type) values ('SRCR', 'Sour Cream', 'SAUSE');
