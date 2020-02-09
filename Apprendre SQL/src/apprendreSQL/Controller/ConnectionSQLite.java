package apprendreSQL.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionSQLite {

	private String dBPathModif;
	private String dBPathOrigin = "";

	private Connection connection = null;
	private Statement statement = null;
	private String errorMessage = "";

	public ConnectionSQLite(String dBPath) {
		dBPathOrigin = dBPath;
		resetDatabase();
		dBPathModif = dBPathOrigin.replace(".db", "") + "_versionReset.db";
	}

	public ConnectionSQLite(String dBPath, boolean action) {
		dBPathOrigin = dBPath;
		resetDatabase();
		dBPathModif = dBPathOrigin.replace(".db", "") + "_versionReset.db";
	}

	/**
	 * Connects to the database.
	 * 
	 * @return
	 */
	public Boolean connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			this.connection = DriverManager.getConnection("jdbc:sqlite:" + dBPathModif);
			this.statement = connection.createStatement();
			System.out.println("Connexion à " + dBPathModif + " avec succès");
			return true;
		} catch (ClassNotFoundException notFoundException) {
			notFoundException.printStackTrace();
			System.out.println("Erreur de connection0");
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.out.println("Erreur de connection1");
		}
		return false;
	}

	/**
	 * Closes the connection.
	 */
	public void close() {
		try {
			connection.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Executes a query and returns the result as a ResultSet object.
	 * 
	 * @param query
	 * @return
	 */
	public ResultSet queryExecution(String query) {
		ResultSet result = null;
		errorMessage = "";
		try {
			result = statement.executeQuery(query);
		} catch (SQLException e) {
			errorMessage = e.getMessage();
		}
		return result;

	}

	/**
	 * Creates a backup file of the database.
	 */
	public void resetDatabase() {

		FileInputStream sourceDirectory = null;
		FileOutputStream targetDirectory = null;

		try {
			File source = new File(dBPathOrigin);
			File destination = new File(dBPathOrigin.replace(".db", "") + "_versionReset.db");

			sourceDirectory = new FileInputStream(source);
			targetDirectory = new FileOutputStream(destination);
			byte[] buffer = new byte[1024];
			int length;

			while ((length = sourceDirectory.read(buffer)) > 0) {
				targetDirectory.write(buffer, 0, length);
			}

			sourceDirectory.close();
			targetDirectory.close();

			System.out.println("File copied successfully.");

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public Statement getStatement() {
		return statement;
	}
	
	public String getdBPathOrigin() {
		return dBPathOrigin;
	}

}
