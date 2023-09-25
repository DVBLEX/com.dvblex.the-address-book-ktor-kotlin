create table contacts
(
    contactId        varchar(100) primary key,
    firstName        varchar(150) not null,
    lastName         varchar(150) not null,
    contactNumbersId varchar(100) foreign key,
    emailAddressesId varchar(100) foreign key,
);

create table contactNumbers
(
    id              varchar(50) primary key,
    number          varchar(50) not null,
    contactNumberId varchar(100) foreign key
);

create table emailAddresses
(
    id             varchar(50) primary key,
    email          varchar(50) not null,
    emailAddressId varchar(100) foreign key
);