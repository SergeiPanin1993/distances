create table distance (id serial primary key, from_city integer references cities(id), to_city integer references cities(id), distance numeric);