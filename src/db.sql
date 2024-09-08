
create type transportType as enum ('airplane', 'train', 'bus');
create type partnerStatus as enum ('active', 'inactive', 'suspended');
create type contractStatus as enum ('ongoing', 'terminated', 'suspended');
create type discountType as enum ('pourcentage', 'fixe');
create type offerStatus as enum ('active', 'expired', 'suspended');
create type ticketStatus as enum ('sold', 'canceled', 'pending');

create table partner
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

create table contract
(
    id UUID primary key ,
    startDate date,
    endDate date,
    specialRate decimal,
    agreementConditions text,
    renewable boolean,
    contractStatus contractStatus,
    partnerId UUID,
    foreign key (partnerId) references partner(id)
);

create table promo
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
    foreign key (contractId) references contract(id)
)

create table ticket
(
    id UUID primary key ,
    transportType transportType,
    purchasePrice decimal,
    salePrice decimal,
    saleDate date,
    ticketStatus ticketStatus,
    contractId UUID,
    foreign key (contractId) references contract(id),
    departureCity varchar(200),
    destinationCity varchar(200),
    departureDate date
)

create table clients
(
    id int primary key,
    firstName varchar(50),
    lastName varchar(50),
    email varchar(200) unique,
    phone varchar(20)
)

create table reservations
(
    id int primary key,
    clientId int,
    foreign key (clientId) references clients(id),
    ticketId int,
    foreign key (ticketId) references ticket(id),
    date date,
    price float,
    status boolean
)

create table reservationTicket
(
    id int primary key,
    reservationId int,
    foreign key (reservationId) references reservations(id),
    ticketId int,
    foreign key (ticketId) references ticket(id)
)