package apprendreSQL.Model;

import java.util.ArrayList;

import apprendreSQL.Controller.EventManager;

/**
 * This class represents a Database object.
 *
 */
public class DataBase {

	private long id_database;
	private String nameDatabase;
	private ArrayList<Table> tables;

	public DataBase(Long id_database, String name_Database) {
		this.id_database = id_database;
		this.nameDatabase = name_Database;
	}

	public DataBase(String name, EventManager manager) {
		this.nameDatabase = name;
		this.tables = new ArrayList<Table>();
		this.tables = manager.getTables(name);
	}

	public Long getId_database() {
		return id_database;
	}

	public void setId_database(long id_database) {
		this.id_database = id_database;
	}

	public String getNameDatabase() {
		return nameDatabase;
	}

	public void setNameDatabase(String name_Database) {
		this.nameDatabase = name_Database;
	}

	public ArrayList<Table> getTables() {
		return tables;
	}

	public void setTables(ArrayList<Table> tables) {
		this.tables = tables;
	}

}
