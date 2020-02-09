package apprendreSQL.Controller;

import java.sql.SQLException;

/**
 * Launcher class for the application.
 *
 */
public class AppMain {

	public static void main(String[] args) throws SQLException {

		UtilitiesFactory.createJsonTemp();
		new EventManager();

	}

}
