package blockPlaceTracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BlockTracker {
	
	public Connection connection;
	
	public BlockTracker() {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:plugins/betterAttachments/data.db");
		} catch (SQLException e) {
		    e.printStackTrace(); // Log or handle error
		}
	}

}
