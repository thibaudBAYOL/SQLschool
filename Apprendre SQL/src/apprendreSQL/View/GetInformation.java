package apprendreSQL.View;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import apprendreSQL.Model.Question;
import apprendreSQL.Controller.JsonManager;

public interface GetInformation {

	/**
	 * A function that gets the available subjects of a file
	 * 
	 * @param nameFile
	 * @return List<String>
	 */
	@SuppressWarnings("unchecked")
	public default ArrayList<String> getSubjects(String nameFile) {
		JSONParser jsonParser = new JSONParser();
		ArrayList<String> subjects = new ArrayList<>();
		System.out.println(nameFile);

		try {
			FileReader reader = new FileReader(new File("resource/" + nameFile));
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			JSONArray questions = (JSONArray) jsonObject.get("listeQuestion");
			JSONObject temp;
			Iterator<JSONObject> iterator = questions.iterator();
			while (iterator.hasNext()) {
				temp = iterator.next();
				String subject = temp.get("sujet").toString();
				System.out.println(subject);
				if (!subjects.contains(subject))
					subjects.add(subject);
			}
		} catch (Exception ex) {
			System.out.println("Le fichier spécifié est introuvable.");
			JOptionPane.showMessageDialog(null, "Le fichier spécifié est introuvable.", "Attention",
					JOptionPane.WARNING_MESSAGE);
		}
		return subjects;
	}

	/**
	 * A function that gets the available json files
	 * 
	 * @return List<String>
	 */
	public default ArrayList<String> getJSONFiles() {

		ArrayList<String> JSONFiles = new ArrayList<String>();
		File folder = new File("resource/");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".json") && listOfFiles[i].length() != 0) {
				JSONFiles.add(listOfFiles[i].getName());
			}
		}
		return JSONFiles;
	}

	/**
	 * A function that gets the available .db files
	 * 
	 * @return List<String>
	 */
	public default ArrayList<String> getDbFiles() {

		ArrayList<String> db = new ArrayList<String>();
		File folder = new File("resource/");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".db")
					&& !listOfFiles[i].getName().endsWith("_versionReset.db") && listOfFiles[i].length() != 0) {
				for (int j = 0; j < listOfFiles.length; j++) {
					if (listOfFiles[j].isFile() && listOfFiles[j].getName()
							.contentEquals(listOfFiles[i].getName().replace(".db", "_versionReset.db"))) {
						db.add(listOfFiles[i].getName());
						break;
					}
				}

			}
		}
		return db;
	}

	/**
	 * A function that gets the id of question
	 * 
	 * @param nomFichier
	 * @return List<String>
	 */
	public default List<Integer> getIDQuestion(String nomFichier, JsonManager jsonManager) {

		List<Integer> IDQuestions = new ArrayList<>();
		jsonManager.readFileQuestion("resource/" + nomFichier);

		for (int i = 0; i < jsonManager.getListQuestion().size(); i++) {
			IDQuestions.add(i + 1);
		}
		return IDQuestions;
	}

	/**
	 * A function that gets the id of an exercise
	 * 
	 * @param dbName
	 * @param sujetName
	 * @param exerciceName
	 * @param jsonManager
	 * @return
	 */
	public default int getIdExercise(String dbName, String sujetName, String exerciceName, JsonManager jsonManager) {

		ArrayList<Question> listQuestion = jsonManager.getListQuestion();
		int i = 0;
		for (Question e : listQuestion) {
			if (dbName.equalsIgnoreCase(e.getDatabase()) && sujetName.equalsIgnoreCase(e.getSubject())
					&& exerciceName.equalsIgnoreCase(e.getTitleQuestion())) {
				System.out.println(dbName + "  " + sujetName + "  " + exerciceName + " //" + e.getTitleQuestion());
				return i;
			}
			i++;
		}

		return -1;
	}

}
