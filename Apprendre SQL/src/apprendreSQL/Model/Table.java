package apprendreSQL.Model;

/**
 * This class represents a Table on a database.
 *
 */
public class Table {
	String name;
	String database;

	public Table(String name, String database) {
		this.name = name;
		this.database = database;
	}

	public String getDatabase() {
		return database;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
