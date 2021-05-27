package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Balsalevy;

// Toteuttaa rajapinnan BalsalevyDAO. Katso metodien valinnasta lisää siellä.

public class JDBCBalsalevyDAO implements BalsalevyDAO {
	
	public List<Balsalevy> getAllItems() {
		
		List<Balsalevy> tuotteet = new ArrayList<Balsalevy>();
		
		try {
    		Class.forName("org.sqlite.JDBC"); 		
    		String url = "jdbc:sqlite:./balsa.sqlite";
    		Connection connection = DriverManager.getConnection(url);	

    		String sql = "SELECT * FROM Balsalevy;";	
    		PreparedStatement statement = connection.prepareStatement(sql);
    		ResultSet tulokset = statement.executeQuery();
    		
    		while(tulokset.next()) {
    			long id = tulokset.getInt("id");
    			double tiheys = tulokset.getDouble("tiheys");
    			double korkeus = tulokset.getDouble("korkeus");
    			double leveys = tulokset.getDouble("leveys");
    			double paino = tulokset.getDouble("paino");
    			double pituus = tulokset.getDouble("pituus");
    			String grain = tulokset.getString("grain");
    			
    			Balsalevy tuote = new Balsalevy(tiheys, korkeus, leveys, paino, pituus, grain);    			
    			tuote.setId(id);    			
    			tuotteet.add(tuote);
    		}
    		statement.close();
    		connection.close();    		
    	} catch(Exception ex) {
    		System.out.println(ex.getMessage());
    		System.out.println("Connection to database failed!");
    	}
		return tuotteet;		
	}


	@Override
	public Balsalevy getItem(long id) {	    	
    	return null;
    }

	@Override
	public boolean addItem(Balsalevy newItem) {
		double tiheys = newItem.getTiheys();
		double korkeus = newItem.getKorkeus();
		double leveys = newItem.getLeveys();
		double paino = newItem.getPaino();
		double pituus = newItem.getPituus();
		String grain = newItem.getGrain();
    	
    	try {    		
    		Class.forName("org.sqlite.JDBC");    		
    		String url = "jdbc:sqlite:./balsa.sqlite";
    		Connection connection = DriverManager.getConnection(url);    		
    		// Seuraavassa suojaudutaan SQLnjektiota vastaan VALUES-merkintätavalla.  	
    		String sql = "INSERT INTO Balsalevy(tiheys,korkeus,leveys,paino,pituus,grain) VALUES(?,?,?,?,?,?); "; 	
    		PreparedStatement statement = connection.prepareStatement(sql);    		
    		statement.setDouble(1, tiheys);
    		statement.setDouble(2, korkeus);
    		statement.setDouble(3, leveys);
    		statement.setDouble(4, paino);
    		statement.setDouble(5, pituus);
    		statement.setString(6, grain);
    		statement.executeUpdate();
    		
    		System.out.println("\nSuccessfully added new calculation into the database.");
    		statement.close();
    		connection.close();
    		
    	} catch(Exception ex) {
    		return false;
    	}
    	return true;
    }

	@Override
	public boolean removeItem(Balsalevy item) {
    	long poista = item.getId();
    	
    	try {    		
    		Class.forName("org.sqlite.JDBC");    		
    		String url = "jdbc:sqlite:./balsa.sqlite";
    		Connection connection = DriverManager.getConnection(url);    		
    		String sql = "DELETE FROM Balsalevy WHERE ID=?";    		
    		PreparedStatement statement = connection.prepareStatement(sql);    		
    		statement.setLong(1, poista);    		
    		int tulos = statement.executeUpdate();
    		
    		int deletedId = tulos;
			if (deletedId > 0) {
				System.out.println("\nSuccessfully removed " + poista);
			}
			else {
				System.out.println("\nCould not remove " + "'" + poista + "'. Please try again.");
			}
    		statement.close();
    		connection.close();
    		
    	} catch(Exception ex) {
    		return false;
    	}
    	return true;
	}


	public double tiheys(double paino, double leveys, double paksuus, double pituus) {
		return 0;
	}

}
