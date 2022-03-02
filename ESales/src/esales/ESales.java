package esales;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Utente;


public class ESales {

	public static void main(String[] args) {
		try {
			ESales.ui();
		}catch(IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void ui() throws IOException {
		int i = 1000;
		String scelta;

		InputStreamReader keyIS = new InputStreamReader(System.in);
		BufferedReader keyBR = new BufferedReader(keyIS);
		while (i != 0) {
			System.out.println("\nESALES:");
			System.out.println("*****************************");
			System.out.println("0) ESCI");
			System.out.println("1) Inserisci nuovo utente");
			System.out.println("2) Visualizza gli annunci pubblicati da un utente");
			System.out.println("3) Visualizza utente con più acquisti");
			System.out.println("4) Visualizza annunci approvati da un amministratore");
			System.out.println("5) Inserisci un nuovo amministratore");
			System.out.println("6) Visualizza categorie e gli amministratori che le hanno inserite");
			System.out.println("7) Visualizza metodi di pagamento di un utente");
			System.out.println("8) Visualizza messaggi inviati da un utente");
			System.out.println("9) Visualizza inserzionisti che hanno pubblicato annunci approvati, in località Salerno");
			System.out.println("10) Fai approvare ad un amministratore un annuncio");
			System.out.println("11) Modificare il titolo di un annuncio di un dato inserzionista");
			System.out.println("12) Acquista un prodotto che abbia condizione 'nuovo' ");
			System.out.println("*****************************");

			System.out.print("Inserisci scelta: ");
			scelta = keyBR.readLine();

			try {
				i = Integer.parseInt(scelta);
			} catch (NumberFormatException e) {
				i = 999;
			}

			System.out.println();
			
			switch (i) {
			case 0: {
				System.out.println("Uscita...");
				keyIS.close();
				keyBR.close();
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {};
				break;
			}			
			case 1: {
				System.out.println("------- Inserimento utente -------");
				String nome=Utility.readLine("Inserisci nome",null,true);
				String cognome=Utility.readLine("Inserisci cognome",null,true);
				String codiceFiscale=Utility.readLine("Inserisci codice fiscale",null,true);
				String mail=Utility.readLine("Inserisci mail",null,true);
				String password=Utility.readLine("Inserisci password",null,true);
				String dataDiNascita=Utility.readLine("Inserisci data di nascita",null,true);
				String telefono=Utility.readLine("Inserisci numero di telefono",null,true);
				String indirizzo=Utility.readLine("Inserisci indirizzo",null,true);
				int ruolo=Integer.parseInt(Utility.readLine("Inserisci il tipo di account (0 per Acquirente,1 per inserzionista)",null,true));
				ESales.inserisciUtente(new Utente(codiceFiscale,nome,cognome,mail,password,dataDiNascita,telefono,indirizzo,ruolo));
				
				try {
					Thread.sleep(2000);
				}catch(InterruptedException e) {};
				
				break;
			}
			case 2: {
				System.out.println("------- Visualizza annunci di un utente -------");
				String codiceFiscale = Utility.readLine("Inserisci codice Fiscale Utente", null, true);
				selezionaAnnunciDiUtente(codiceFiscale);
				try {
					Thread.sleep(4000);
				}catch(InterruptedException e) {};
				break;
			}
			case 3: {
				System.out.println("------- Visualizza utente con più acquisti -------");
				selezionaUtenteConPiuAcquisti();
				try {
					Thread.sleep(4000);
				}catch(InterruptedException e) {};
				break;
			}
			case 4: {
				System.out.println("------- Visualizza annunci approvati da un amministratore -------");
				String codiceFiscale = Utility.readLine("Inserisci codice Fiscale amministratore", null, true);
				selezionaApprovati(codiceFiscale);
				try {
					Thread.sleep(4000);
				}catch(InterruptedException e) {};
				
				break;
			}
			case 5: {
				System.out.println("------- Inserimento amministratore -------");
				String nome=Utility.readLine("Inserisci nome",null,true);
				String cognome=Utility.readLine("Inserisci cognome",null,true);
				String codiceFiscale=Utility.readLine("Inserisci codice fiscale",null,true);
				String mail=Utility.readLine("Inserisci mail",null,true);
				String password=Utility.readLine("Inserisci password",null,true);
				String telefono=Utility.readLine("Inserisci numero di telefono",null,true);
				inserisciAmministratore(codiceFiscale, nome, cognome, mail, password, telefono);
				try {
					Thread.sleep(2000);
				}catch(InterruptedException e) {};
				break;
			}
			case 6: {
				System.out.println("------- Visualizza categorie -------");
				visualizzaCategorie();
				try {
					Thread.sleep(4000);
				}catch(InterruptedException e) {};
				break;
			}
			case 7: {
				System.out.println("------- Visualizza metodi di pagamento di un utente -------");
				String codiceFiscale = Utility.readLine("Inserisci codice Fiscale Utente", null, true);
				visualizzaMetodiDiPagamentoUtente(codiceFiscale);
				try {
					Thread.sleep(4000);
				}catch(InterruptedException e) {};
				break;
			}
			case 8: {
				System.out.println("------- Visualizza messaggi inviati da un utente -------");
				String codiceFiscale = Utility.readLine("Inserisci codice Fiscale Utente", null, true);
				visualizzaMessaggiInviatiDaUtente(codiceFiscale);
				try {
					Thread.sleep(4000);
				}catch(InterruptedException e) {};
				break;
			}
			case 9: {
				System.out.println("------- Visualizza inserzionisti che hanno pubblicato annunci approvati, in località Salerno -------");
				visualizzaAnnunciSalerno();
				try {
					Thread.sleep(4000);
				}catch(InterruptedException e) {};
				break;
			}
			case 10: {
				System.out.println("------- Fai approvare ad un Amministratore un annuncio -------");
				visualizzaAnnunciNonApprovati();
				int idAnnuncio=Integer.parseInt(Utility.readLine("Inserisci l'id dell'annuncio da approvare",null, true));
				String codiceFiscale = Utility.readLine("Inserisci codice Fiscale amministratore", null, true);
				approvaAnnuncio(idAnnuncio, codiceFiscale);
				try {
					Thread.sleep(4000);
				}catch(InterruptedException e) {};
				break;

			}
			case 11: {
				System.out.println("------- Modificare il titolo di un annuncio di un dato inserzionista -------");
				String codiceFiscale = Utility.readLine("Inserisci codice Fiscale utente", null, true);
				visualizzaAnnunciDiUtente(codiceFiscale);
				int idAnnuncio=Integer.parseInt(Utility.readLine("Inserisci l'id dell'annuncio da modificare",null, true));
				String titolo = Utility.readLine("Inserisci il nuovo titolo dell'annuncio",null, true);
				modificaTitoloDiAnnuncio(idAnnuncio, titolo);
				try {
					Thread.sleep(4000);
				}catch(InterruptedException e) {};
				break;
			}
			case 12: {
				System.out.println("------- Acquista un prodotto che abbia condizione 'nuovo' -------");
				visualizzaProdottiNuovi();
				int idAnnuncio=Integer.parseInt(Utility.readLine("Inserisci l'id dell'annuncio del prodotto da acquistare",null, true));
				String nomeProdotto = Utility.readLine("Inserisci nome del prodotto da acquistare", null, true);
				String codiceFiscale = Utility.readLine("Inserisci codice fiscale dell'utente", null, true);
				acquistaProdotto(idAnnuncio, nomeProdotto, codiceFiscale);
				try {
					Thread.sleep(4000);
				}catch(InterruptedException e) {};
				break;
			}

			default: {
				System.out.println("Scelta non presente");
				break;
			}
			} 
		}

		
		
	}
	
	public static void inserisciUtente(Utente u) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnectionPool.getConnection();

			String sql = "INSERT INTO Utente VALUES (?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, u.getCodiceFiscale());
			ps.setString(2, u.getNome());
			ps.setString(3, u.getCognome());
			ps.setString(4, u.getMail());
			ps.setString(5, u.getPassword());
			ps.setString(6, u.getDataDiNascita());
			ps.setString(7, u.getTelefono());
			ps.setString(8, u.getIndirizzo());
			ps.setInt(9, u.getRuolo());
			
			System.out.println("QUERY: " + ps);			
			ps.executeUpdate();
			
			con.commit();
			System.out.println("Inserimento Utente effettuato");
			
		} catch (SQLException s) {
			System.out.println("Errore: Inserimento Utente non effettuato");
			System.err.println(s.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				DBConnectionPool.releaseConnection(con);
			} catch (SQLException s) {
				System.err.println(s.getMessage());
			}
		}
		
	}
	
	public static void selezionaAnnunciDiUtente(String codiceFiscale) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnectionPool.getConnection();


			String sql = "SELECT * FROM Annuncio WHERE codiceFiscale =  ?  ORDER BY dataInserzione" ;
			ps=con.prepareStatement(sql);
			ps.setString(1, codiceFiscale);
			System.out.println("QUERY: " + ps.toString());
			rs = ps.executeQuery();

			PrintTable.printResultSet(rs);
		} catch (SQLException s) {
			System.err.println(s.getMessage());
			Utility.printSQLException(s);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				DBConnectionPool.releaseConnection(con);
			} catch (SQLException s) {
				System.err.println(s.getMessage());
				Utility.printSQLException(s);
			}
		}
	}
	
	public static void selezionaUtenteConPiuAcquisti() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnectionPool.getConnection();


			String sql = "SELECT MAX(numeroAcquisti), codiceFiscale\r\n"
					+ "FROM (SELECT Count(*) AS numeroAcquisti,codiceFiscale\r\n"
					+ "		FROM Acquisto\r\n"
					+ "		GROUP BY codiceFiscale ) AS numeroAcquistiPerCliente;" ;
			ps=con.prepareStatement(sql);
			System.out.println("QUERY: " + ps.toString());
			rs = ps.executeQuery();

			PrintTable.printResultSet(rs);
		} catch (SQLException s) {
			System.err.println(s.getMessage());
			Utility.printSQLException(s);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				DBConnectionPool.releaseConnection(con);
			} catch (SQLException s) {
				System.err.println(s.getMessage());
				Utility.printSQLException(s);
			}
		}
	}
	
	public static void selezionaApprovati(String codiceFiscale) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnectionPool.getConnection();


			String sql = "SELECT * FROM Approvato WHERE codiceFiscale =  ?" ;
			ps=con.prepareStatement(sql);
			ps.setString(1, codiceFiscale);
			System.out.println("QUERY: " + ps.toString());
			rs = ps.executeQuery();

			PrintTable.printResultSet(rs);
		} catch (SQLException s) {
			System.err.println(s.getMessage());
			Utility.printSQLException(s);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				DBConnectionPool.releaseConnection(con);
			} catch (SQLException s) {
				System.err.println(s.getMessage());
				Utility.printSQLException(s);
			}
		}
	}
	
	public static void inserisciAmministratore(String codiceFiscale,String nome, String cognome, String mail, String password,String telefono) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnectionPool.getConnection();

			String sql = "INSERT INTO Amministratore VALUES (?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, codiceFiscale);
			ps.setString(2, nome);
			ps.setString(3, cognome);
			ps.setString(4, mail);
			ps.setString(5, password);
			ps.setString(6, telefono);
			
			System.out.println("QUERY: " + ps);			
			ps.executeUpdate();

			con.commit();
			System.out.println("Inserimento Amministratore effettuato");
		} catch (SQLException s) {
			System.out.println("Errore : Inserimento Amministratore non effettuato");
			System.err.println(s.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				DBConnectionPool.releaseConnection(con);
			} catch (SQLException s) {
				System.err.println(s.getMessage());
			}
		}
	}
	
	public static void visualizzaCategorie() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DBConnectionPool.getConnection();

			String sql = "SELECT C.nome, A.nome, A.cognome\r\n"
					+ "FROM Amministratore AS A, Categoria AS C\r\n"
					+ "WHERE A.codiceFiscale=C.codiceFiscale;" ;
			st=con.createStatement();
			System.out.println("QUERY: " + sql);
			rs = st.executeQuery(sql);

			PrintTable.printResultSet(rs);
		} catch (SQLException s) {
			System.err.println(s.getMessage());
			Utility.printSQLException(s);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				DBConnectionPool.releaseConnection(con);
			} catch (SQLException s) {
				System.err.println(s.getMessage());
				Utility.printSQLException(s);
			}
		}
	}
	
	public static void visualizzaMetodiDiPagamentoUtente(String codiceFiscale) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnectionPool.getConnection();


			String sql = "SELECT * FROM MetodoPagamento WHERE codiceFiscale =  ?" ;
			ps=con.prepareStatement(sql);
			ps.setString(1, codiceFiscale);
			System.out.println("QUERY: " + ps.toString());
			rs = ps.executeQuery();

			PrintTable.printResultSet(rs);
		} catch (SQLException s) {
			System.err.println(s.getMessage());
			Utility.printSQLException(s);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				DBConnectionPool.releaseConnection(con);
			} catch (SQLException s) {
				System.err.println(s.getMessage());
				Utility.printSQLException(s);
			}
		}
	}
	
	public static void visualizzaMessaggiInviatiDaUtente(String codiceFiscale) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnectionPool.getConnection();


			String sql = "SELECT * \r\n"
					+ "FROM Messaggio\r\n"
					+ "WHERE codiceFiscaleMittente= ? ORDER BY dataInvio DESC" ;
			ps=con.prepareStatement(sql);
			ps.setString(1, codiceFiscale);
			System.out.println("QUERY: " + ps.toString());
			rs = ps.executeQuery();

			PrintTable.printResultSet(rs);
		} catch (SQLException s) {
			System.err.println(s.getMessage());
			Utility.printSQLException(s);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				DBConnectionPool.releaseConnection(con);
			} catch (SQLException s) {
				System.err.println(s.getMessage());
				Utility.printSQLException(s);
			}
		}
	}
	
	public static void visualizzaAnnunciSalerno() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DBConnectionPool.getConnection();

			String sql = "SELECT nome,cognome, telefono\r\n"
					+ "FROM Utente\r\n"
					+ "WHERE codiceFiscale IN(SELECT codiceFiscale\r\n"
					+ "						FROM Annuncio\r\n"
					+ "                        WHERE approvato=true AND locazione like '%salerno%')" ;
			st=con.createStatement();
			System.out.println("QUERY: " + sql);
			rs = st.executeQuery(sql);

			PrintTable.printResultSet(rs);
		} catch (SQLException s) {
			System.err.println(s.getMessage());
			Utility.printSQLException(s);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				DBConnectionPool.releaseConnection(con);
			} catch (SQLException s) {
				System.err.println(s.getMessage());
				Utility.printSQLException(s);
			}
		}
	}
	
	public static void visualizzaAnnunciNonApprovati(){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DBConnectionPool.getConnection();

			String sql = "SELECT * FROM Annuncio WHERE approvato=false" ;
			st=con.createStatement();
			System.out.println("QUERY: " + sql);
			rs = st.executeQuery(sql);

			PrintTable.printResultSet(rs);
		} catch (SQLException s) {
			System.err.println(s.getMessage());
			Utility.printSQLException(s);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				DBConnectionPool.releaseConnection(con);
			} catch (SQLException s) {
				System.err.println(s.getMessage());
				Utility.printSQLException(s);
			}
		}
	}

	public static void approvaAnnuncio(int idAnnuncio,String codiceFiscale) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		PreparedStatement ps2 = null;
		
		try {
			con = DBConnectionPool.getConnection();

			String sql="INSERT INTO Approvato values(?,?)";
			ps2=con.prepareStatement(sql);
			ps2.setInt(1,idAnnuncio);
			ps2.setString(2, codiceFiscale);
			ps2.executeUpdate();

			sql = "UPDATE Annuncio SET approvato=true WHERE idAnnuncio=?" ;
			ps=con.prepareStatement(sql);
			ps.setInt(1, idAnnuncio);
			System.out.println("QUERY: " + ps.toString());
			ps.executeUpdate();
			
			con.commit();
			System.out.println("Inserimento effettuato");
		} catch (SQLException s) {
			System.out.println("Errore: Inserimento non effettuato");
			System.err.println(s.getMessage());
			Utility.printSQLException(s);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (rs2 != null)
					rs2.close();
				if (ps != null)
					ps.close();
				if (ps2 != null)
					ps2.close();
				DBConnectionPool.releaseConnection(con);
			} catch (SQLException s) {
				System.err.println(s.getMessage());
				Utility.printSQLException(s);
			}
		}
		
	}
	
	public static void visualizzaAnnunciDiUtente(String codiceFiscale) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnectionPool.getConnection();


			String sql = "SELECT * FROM Annuncio WHERE codiceFiscale= ? " ;
			ps=con.prepareStatement(sql);
			ps.setString(1, codiceFiscale);
			System.out.println("QUERY: " + ps.toString());
			rs = ps.executeQuery();

			PrintTable.printResultSet(rs);
		} catch (SQLException s) {
			System.err.println(s.getMessage());
			Utility.printSQLException(s);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				DBConnectionPool.releaseConnection(con);
			} catch (SQLException s) {
				System.err.println(s.getMessage());
				Utility.printSQLException(s);
			}
		}
	}

	public static void modificaTitoloDiAnnuncio(int idAnnuncio,String titolo) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnectionPool.getConnection();


			String sql = "UPDATE Annuncio SET titolo= ? WHERE idAnnuncio=?" ;
			ps=con.prepareStatement(sql);
			ps.setString(1, titolo);
			ps.setInt(2, idAnnuncio);
			System.out.println("QUERY: " + ps.toString());
			ps.executeUpdate();
			System.out.println("Aggiornamento Annuncio effettuato!");
			con.commit();
		} catch (SQLException s) {
			System.err.println(s.getMessage());
			System.out.println("Errore: Aggiornamento Annuncio non effettuato!");
			Utility.printSQLException(s);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				DBConnectionPool.releaseConnection(con);
			} catch (SQLException s) {
				System.err.println(s.getMessage());
				Utility.printSQLException(s);
			}
		}
	}
	
	public static void acquistaProdotto(int idAnnuncio,String nomeProdotto,String codiceFiscale) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps2 = null;
		
		try {
			con = DBConnectionPool.getConnection();

			String sql = "INSERT INTO Acquisto VALUES (?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, nomeProdotto);
			ps.setInt(2, idAnnuncio);
			ps.setString(3, codiceFiscale);
			
			System.out.println("QUERY: " + ps.toString());			
			ps.executeUpdate();
			
			sql="UPDATE Prodotto SET venduto=true WHERE nome= ? AND idAnnuncio = ?";
			ps2= con.prepareStatement(sql);
			ps2.setString(1, nomeProdotto);
			ps2.setInt(2, idAnnuncio);
			
			System.out.println("QUERY: " + ps2.toString());
			
			ps2.executeUpdate();
			
			con.commit();
			System.out.println("Prodotto Acquistato!");
		} catch (SQLException s) {
			System.err.println(s.getMessage());
			System.out.println("Errore: Prodotto Non Acquistato!");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (ps2 != null)
					ps2.close();
				DBConnectionPool.releaseConnection(con);
			} catch (SQLException s) {
				System.err.println(s.getMessage());
			}
		}
	}
	
	public static void visualizzaProdottiNuovi() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnectionPool.getConnection();


			String sql = "SELECT * FROM Prodotto WHERE condizione LIKE '%nuovo%' AND venduto=false;" ;
			ps=con.prepareStatement(sql);
			System.out.println("QUERY: " + ps.toString());
			rs = ps.executeQuery();

			PrintTable.printResultSet(rs);
		} catch (SQLException s) {
			System.err.println(s.getMessage());
			Utility.printSQLException(s);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				DBConnectionPool.releaseConnection(con);
			} catch (SQLException s) {
				System.err.println(s.getMessage());
				Utility.printSQLException(s);
			}
		}
	}
}
