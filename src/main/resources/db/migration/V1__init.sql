CREATE TABLE sender(
    id serial,
    type varchar(20) NOT NULL,
    index varchar(20) NOT NULL,
    address varchar(50) NOT NULL,
    recipient_name varchar(50) NOT NULL,

    PRIMARY KEY (id)
);

INSERT INTO sender (type,index,address,recipient_name) VALUES ('message','130005','Qyzyltobe, st.Turkestan 4/6','Yermukhan');

CREATE TABLE post(
    id serial,
    index varchar(20) NOT NULL,
    title varchar(50) NOT NULL,
    address varchar(50) NOT NULL,

    PRIMARY KEY (id)

);

CREATE TABLE mail_history(
    id serial,
    id_send bigint NOT NULL,
    id_post bigint NOT NULL,
    arrival_date date,
    arrival_post varchar(50),
    departure_date date,
    receiving_date date,

    PRIMARY KEY(id),
    FOREIGN KEY (id_send)
    REFERENCES sender (id),
    FOREIGN KEY (id_post)
    REFERENCES post (id)

);
