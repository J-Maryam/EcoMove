
create type transportType as enum ('AIRPLANE', 'TRAIN', 'BUS');
create type partnerStatus as enum ('ACTIVE', 'INACTIVE', 'SUSPENDED');
create type contractStatus as enum ('ONGOING', 'TERMINATED', 'SUSPENDED');
create type discountType as enum ('POURCENTAGE', 'FIXE');
create type offerStatus as enum ('ACTIVE', 'EXPIRED', 'SUSPENDED');
create type ticketStatus as enum ('SOLD', 'CANCELED', 'PENDING');

create table partners
(
    id UUID primary key ,
    companyName varchar(200),
    businessContact varchar(200),
    transportType transportType,
    geographicZone varchar(200),
    specialConditions text,
    partnerStatus partnerStatus,
    creationDate date
);

create table contracts
(
    id UUID primary key ,
    startDate date not null ,
    endDate date not null ,
    specialRate decimal,
    agreementConditions text,
    renewable boolean,
    contractStatus contractStatus,
    partnerId UUID,
    foreign key (partnerId) references partners(id)
);

create table promos
(
    id UUID primary key ,
    offerName varchar(200),
    description text,
    startDate date,
    endDate date,
    discountType discountType,
    conditions text,
    offerStatus offerStatus,
    contractId UUID,
    foreign key (contractId) references contracts(id)
);

create table tickets
(
    id UUID primary key ,
    transportType transportType,
    purchasePrice decimal,
    salePrice decimal,
    saleDate date,
    ticketStatus ticketStatus,
    contractId UUID,
    foreign key (contractId) references contracts(id)
);

create table clients
(
    id int primary key,
    firstName varchar(50),
    lastName varchar(50),
    email varchar(200) unique,
    phone varchar(20)
);

create table reservations
(
    id int primary key,
    clientId int,
    foreign key (clientId) references clients(id),
    date date,
    price float,
    canceled boolean
);

create table reservationTicket
(
    id int primary key,
    reservationId int,
    foreign key (reservationId) references reservations(id),
    ticketId UUID,
    foreign key (ticketId) references tickets(id)
);

create table cities (
    id int primary key,
    cityName varchar(50)
);

create table journeys(
    id int primary key,
    departureCity int,
    foreign key (departureCity) references cities(id),
    destinationCity int,
    foreign key (destinationCity) references cities(id),
    departureDate date
)