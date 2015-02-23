import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TestMySQL {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Connection con = null;
		ResultSet rs = null;
		String requete = "";
		
		try {
			con = DriverManager
					.getConnection("jdbc:mysql://localhost/dbclients?" +
					"user=root&password=root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		requete = "SELECT * FROM clients";
		
		Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			rs = stmt.executeQuery(requete);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String data = "";
		try {
			rs.next();
			data = rs.getString(5);
			System.out.println(data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Boolean encore = false;
		try {
			encore = rs.next();
		} catch (SQLException e1) {
				e1.printStackTrace();
		}
		
		while(encore) {
			try {
				for(int i=1;i<rs.getMetaData().getColumnCount();i++) {
					data = rs.getObject(i).toString();
					
					System.out.print(data+"\t");
				}
				System.out.println("\n");
				encore = rs.next();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
