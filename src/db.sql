
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
    discountValue float,
    conditions text,
    offerStatus offerStatus,
    contractId UUID,
    foreign key (contractId) references contracts(id)
);

create table clients
(
    id UUID primary key,
    firstName varchar(50),
    lastName varchar(50),
    email varchar(200) unique not null ,
    phone varchar(20)
);

create table cities (
    id SERIAL primary key,
    cityName varchar(50) unique
);

create table journeys(
    id SERIAL primary key,
    departureCity int,
    foreign key (departureCity) references cities(id),
    destinationCity int,
    foreign key (destinationCity) references cities(id),
    distance float
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
    foreign key (contractId) references contracts(id),
    journeyId int,
    foreign key (journeyId) references journeys (id),
    departureDate timestamp
);

create table reservations
(
    id UUID primary key,
    clientId UUID,
    foreign key (clientId) references clients(id),
    date date,
    price float,
    canceled boolean
);

create table reservationTicket
(
    id SERIAL primary key,
    reservationId UUID,
    foreign key (reservationId) references reservations(id),
    ticketId UUID,
    foreign key (ticketId) references tickets(id)
);

INSERT INTO cities (cityName)
VALUES
    ('Agadir'),
    ('Beni Mellal'),
    ('Casablanca'),
    ('El Jadida'),
    ('Fès'),
    ('Kénitra'),
    ('Marrakech'),
    ('Meknès'),
    ('Ouarzazate'),
    ('Oujda'),
    ('Rabat'),
    ('Safi'),
    ('Settat'),
    ('Tanger'),
    ('Tétouan');


INSERT INTO journeys (departureCity, destinationCity, distance)
VALUES
    -- Trajets depuis Agadir
    (1, 3, 240.5),  -- Agadir à Casablanca
    (1, 5, 460.2),  -- Agadir à Fès
    (1, 6, 540.0),  -- Agadir à Kénitra
    (1, 7, 240.0),  -- Agadir à Marrakech
    (1, 11, 600.0), -- Agadir à Rabat
    (1, 14, 900.0), -- Agadir à Tanger

    -- Trajets depuis Casablanca
    (3, 1, 240.5),  -- Casablanca à Agadir
    (3, 6, 120.0),  -- Casablanca à Kénitra
    (3, 7, 250.0),  -- Casablanca à Marrakech
    (3, 11, 95.0),  -- Casablanca à Rabat
    (3, 12, 130.0), -- Casablanca à Safi
    (3, 14, 350.0), -- Casablanca à Tanger

    -- Trajets depuis Marrakech
    (7, 1, 240.0),  -- Marrakech à Agadir
    (7, 3, 250.0),  -- Marrakech à Casablanca
    (7, 5, 540.0),  -- Marrakech à Fès
    (7, 11, 330.0), -- Marrakech à Rabat
    (7, 12, 150.0), -- Marrakech à Safi

    -- Trajets depuis Rabat
    (11, 3, 95.0),  -- Rabat à Casablanca
    (11, 5, 210.0), -- Rabat à Fès
    (11, 6, 40.0),  -- Rabat à Kénitra
    (11, 7, 330.0), -- Rabat à Marrakech
    (11, 14, 250.0), -- Rabat à Tanger

    -- Trajets depuis Tanger
    (14, 3, 350.0), -- Tanger à Casablanca
    (14, 11, 250.0), -- Tanger à Rabat
    (14, 12, 370.0), -- Tanger à Safi
    (14, 13, 120.0), -- Tanger à Tétouan

    -- Trajets depuis Safi
    (12, 3, 130.0), -- Safi à Casablanca
    (12, 7, 150.0), -- Safi à Marrakech
    (12, 13, 60.0),  -- Safi à Settat

    -- Autres trajets
    (5, 6, 160.0),  -- Fès à Kénitra
    (5, 7, 540.0),  -- Fès à Marrakech
    (5, 11, 210.0), -- Fès à Rabat
    (6, 11, 40.0),  -- Kénitra à Rabat
    (13, 14, 120.0); -- Tétouan à Tanger
