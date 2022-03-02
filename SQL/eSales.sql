drop schema if exists eSales;
create schema eSales;
use eSales;

create table Utente(
	codiceFiscale char(16) NOT NULL,
    nome varchar(30) NOT NULL,
    cognome varchar(30) NOT NULL,
    mail varchar(40) NOT NULL UNIQUE,
    pwd varchar(50) NOT NULL,
    dataDiNascita date NOT NULL,
    telefono varchar(15) NOT NULL,
    indirizzo varchar(50) NOT NULL,
    ruolo int NOT NULL,
    PRIMARY KEY (codiceFiscale)
);

create table MetodoPagamento(
	numeroCarta varchar(16) NOT NULL,
    dataScadenza date NOT NULL,
    cvc smallint NOT NULL,
    codiceFiscale char(16) NOT NULL,
    PRIMARY KEY (numeroCarta),
    FOREIGN KEY(codiceFiscale) REFERENCES Utente(codiceFiscale) on update cascade on delete cascade
);

create table Messaggio(
	idMessaggio int NOT NULL auto_increment,
    testo varchar(500) NOT NULL,
    dataInvio timestamp NOT NULL,
    codiceFiscaleMittente char(16) NOT NULL,
    codiceFiscaleDestinatario char(16) NOT NULL,
    PRIMARY KEY (idMessaggio),
    FOREIGN KEY (codiceFiscaleMittente) REFERENCES Utente(codiceFiscale) on update cascade on delete cascade,
	FOREIGN KEY (codiceFiscaleDestinatario) REFERENCES Utente(codiceFiscale) on update cascade on delete cascade
);

create table Annuncio(
	idAnnuncio int NOT NULL auto_increment,
    dataInserzione date NOT NULL,
    titolo varchar(100) NOT NULL,
    locazione varchar(50) NOT NULL,
    descrizione varchar(500) NOT NULL,
    approvato boolean NOT NULL,
    codiceFiscale char(16) NOT NULL,
    PRIMARY KEY (idAnnuncio),
    FOREIGN KEY(codiceFiscale) REFERENCES Utente(codiceFIscale) on update cascade on delete cascade
);

create table Prodotto(
	nome varchar(20) NOT NULL,
    idAnnuncio int NOT NULL,
    condizione varchar(20) NOT NULL,
    prezzo float NOT NULL,
    venduto boolean DEFAULT false,
    PRIMARY KEY(nome,idAnnuncio),
    FOREIGN KEY(idAnnuncio) REFERENCES Annuncio(idAnnuncio) on update cascade on delete cascade
);

create table Acquisto(
	nome varchar(20) NOT NULL,
    idAnnuncio int NOT NULL,
    codiceFiscale char(16) NOT NULL,
    PRIMARY KEY(nome,idAnnuncio,codiceFiscale),
    FOREIGN KEY(nome,idAnnuncio) REFERENCES Prodotto(nome,idAnnuncio) on update cascade on delete cascade, 
    FOREIGN KEY (codiceFiscale) REFERENCES Utente(codiceFiscale) on update cascade on delete cascade
);

create table Amministratore(
	codiceFiscale char(16) NOT NULL PRIMARY KEY,
    nome varchar(30) NOT NULL,
    cognome varchar(30) NOT NULL,
    mail varchar(40) NOT NULL,
    pwd varchar(50) NOT NULL,
    telefono varchar(15) NOT NULL
);

create table Approvato(
	idAnnuncio int NOT NULL,
    codiceFiscale char(16) NOT NULL,
    PRIMARY KEY(idAnnuncio,codiceFiscale),
    FOREIGN KEY(idAnnuncio) REFERENCES Annuncio(idAnnuncio)
    on update cascade
    on delete cascade,
    FOREIGN KEY(codiceFiscale) REFERENCES Amministratore(codiceFiscale)
    on update cascade
    on delete cascade
);

create table Categoria(
	codice int NOT NULL auto_increment,
    nome varchar(20) NOT NULL,
    codiceFiscale char(16),
    PRIMARY KEY (codice),
    FOREIGN KEY(codiceFiscale) REFERENCES Amministratore(codiceFiscale)
    on delete set null
    on update cascade
);

create table AppartieneA(
	codice int NOT NULL,
    nome varchar(20) NOT NULL,
    idAnnuncio int NOT NULL,
	PRIMARY KEY(codice,nome,idAnnuncio),
    FOREIGN KEY(codice) REFERENCES Categoria(codice)
    on update cascade
    on delete cascade,
    FOREIGN KEY(nome,idAnnuncio) REFERENCES Prodotto(nome,idAnnuncio)
    on update cascade
    on delete cascade
);

