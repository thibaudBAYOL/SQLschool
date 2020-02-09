package apprendreSQL.Model;

/**
 * The Subject object that represents the different subjects of the application.
 *
 */
public class Subject {

	private long id_Subject;
	private String titleSubject;
	private DataBase database;

	public Subject(String name, DataBase database) {
		this.titleSubject = name;
		this.setDatabase(database);
	}

	public long getId_Subject() {
		return id_Subject;
	}

	public void setId_Subject(long id_Subject) {
		this.id_Subject = id_Subject;
	}

	public String getTitleSubject() {
		return titleSubject;
	}

	public void setTitleSubject(String title_Subject) {
		this.titleSubject = title_Subject;
	}

	public DataBase getDatabase() {
		return database;
	}

	public void setDatabase(DataBase database) {
		this.database = database;
	}

}
