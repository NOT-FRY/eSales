INSERT INTO Utente(codiceFiscale,nome,cognome,mail,pwd,dataDiNascita,telefono,indirizzo,ruolo) values(?,?,?,?,MD5(?),?,?,?,?);

SELECT *
FROM Annuncio
WHERE codiceFiscale='VRDMRA80A01H703B'
ORDER BY dataInserzione;

SELECT *
FROM Utente JOIN (SELECT Count(*) AS numeroAcquisti,codiceFiscale
				  FROM Acquisto
				  GROUP BY codiceFiscale 
				  Having MAX(numeroAcquisti) ) MaxAcquistiUtente ON Utente.codiceFiscale=MaxAcquistiUtente.codiceFiscale;

SELECT *
FROM Approvato
WHERE codiceFiscale=?;                                                                              

insert into Amministratore (cognome,nome,codiceFiscale,telefono,mail,pwd)values (?,?,?,?,?,MD5(?));

SELECT C.nome, A.nome, A.cognome
FROM Amministratore AS A, Categoria AS C
WHERE A.codiceFiscale=C.codiceFiscale;

SELECT *
FROM MetodoPagamento
WHERE codiceFiscale=?;

SELECT * 
FROM Messaggio
WHERE codiceFiscaleMittente='RSSMRA80A01H703F' ORDER BY dataInvio DESC;

SELECT nome,cognome, telefono
FROM Utente
WHERE codiceFiscale IN(SELECT codiceFiscale
						FROM Annuncio
                        WHERE approvato=true AND locazione like '%salerno%');
                        
SELECT * FROM Annuncio WHERE approvato=false;
UPDATE Annuncio SET approvato=true WHERE idAnnuncio=2;
INSERT INTO Approvato values(?,?);

SELECT * FROM Annuncio WHERE codiceFiscale='VRDMRA80A01H703B';
UPDATE Annuncio SET titolo='Iphone 13' WHERE idAnnuncio=3;

SELECT * FROM prodotto WHERE condizione LIKE '%nuovo%' AND venduto=false;
INSERT INTO Acquisto values(?,?,?);
UPDATE prodotto SET venduto=true WHERE nome= ? AND idAnnuncio = ?;




SELECT Count(*) AS numeroAcquisti,codiceFiscale
		FROM Acquisto
		GROUP BY codiceFiscale 
        Having MAX(numeroAcquisti);
        
CREATE VIEW numeroAcquistiPerCliente AS (SELECT Count(*) AS numeroAcquisti,codiceFiscale
		FROM Acquisto
		GROUP BY codiceFiscale);  
        
SELECT MAX(numeroAcquisti),codiceFiscale
FROM numeroAcquistiPerCliente
Group by codiceFiscale;

        
select * from Acquisto;
use eSales;
select * from Annuncio;
select * from Utente;
select * from prodotto where idAnnuncio =8;