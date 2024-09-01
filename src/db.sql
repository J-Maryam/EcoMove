
create type transportType as enum ('airplane', 'train', 'bus');
create type partnerStatus as enum ('active', 'inactive', 'suspended');
create type contractStatus as enum ('ongoing', 'terminated', 'suspended');
create type discountType as enum ('pourcentage', 'fixedPrice');
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
    saleDate timestamp,
    ticketStatus ticketStatus,
    contractId UUID,
    foreign key (contractId) references contract(id)
)