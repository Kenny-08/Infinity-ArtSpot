


CREATE Table user(
    id int not null auto_increment;
    firstName varchar(225) not null;
    lastName varchar (225) ;
    email varchar(225) not null;
    phoneNo varchar(13) not null;
    password varchar (1000) not null;
    address longtext not null;
    role varchar(50) not null;
    PRIMARY KEY (id);
);

CREATE TABLE artwork(
    id int not null auto_increment;
    title varchar(225) not null;
    description varchar (2000) not null;
    category varchar (225) not null;
    label varchar(225) not null;
    price int not null;
    likes int not null;
    imgUrl varchar(2000) not null;
    owner_id int not null;
    PRIMARY KEY (id);
    FOREIGN KEY (owner_id) references user(id);
);

CREATE TABLE orders(
  id int not null auto_increment;
    email varchar(225) not null;
    price int not null;
    address varchar(5000) not null;
    status varchar(225) not null;
    ordered_at datetime not null;
    user_id int not null;
    artwork_id int not null;
    PRIMARY KEY (id);
    FOREIGN KEY (user_id) references user(id);
    FOREIGN KEY (artwork_id) references artwork(id);
);

CREATE TABLE workshop(
    id int not null auto_increment,
    title varchar(1000) not null,
    description varchar(5000) not null,
    mode varchar(225) not null,
    organizer_id int not null,
    datetime datetime not null,
    venue varchar(2000) not null,
    total_seats int not null,
    registered_seats int,
    imgUrl varchar(1000) not null,
    status varchar(225) not null,
    PRIMARY KEY(id),
    FOREIGN KEY (organizer_id) references user(id)
);

CREATE TABLE exhibition(
    id int not null auto_increment,
    title varchar(1000) not null,
    description varchar(5000) not null,
    mode varchar(225) not null,
    datetime datetime not null,
    venue varchar(2000) not null,
    total_seats int not null,
    registered_seats int,
    imgUrl varchar(1000) not null,
    status varchar(225) not null,
    PRIMARY KEY (id)
);

CREATE TABLE exhibitionRegister(
    id int not null auto_increment,
    confirm varchar(225) not null,
    user_id int not null,
    exhibition_id int not null,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) references user(id),
    FOREIGN KEY (exhibition_id) references exhibition(id)
);

CREATE TABLE workshopRegister(
   id int not null auto_increment,
   confirm varchar(225) not null,
   user_id int not null,
   workshop_id int not null,
   PRIMARY KEY (id),
   FOREIGN KEY (user_id) references user(id),
   FOREIGN KEY (workshop_id) references workshop(id)
);
