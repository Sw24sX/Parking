create table "CAR" (
    "ID" bigint not null primary key,
    "NUMBER" varchar not null unique
);

create table "PARKING" (
    "ID" bigint not null primary key,
    "NAME" varchar not null unique
);

create table "BOOKING" (
    "ID" bigint not null primary key,
    "FROM_DATE" timestamp not null,
    "TO_DATE" timestamp not null,
    "PARKING_ID" bigint not null references "PARKING" ("ID"),
    "CAR_ID" bigint not null references "CAR" ("ID")
);