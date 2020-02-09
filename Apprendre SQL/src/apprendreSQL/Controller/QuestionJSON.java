package apprendreSQL.Controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import apprendreSQL.Model.*;

public class QuestionJSON {

	/**
	 * Creates a question in a JSON
	 * 
	 * @param question
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public JSONObject createQinJSON(Question question) {

		JSONObject objQuestion = new JSONObject();
		objQuestion.put("bd", question.getDatabase());
		objQuestion.put("sujet", question.getSubject());
		objQuestion.put("titre", question.getTitleQuestion());
		objQuestion.put("contenu", question.getContentQuestion());
		objQuestion.put("bonne_reponse", question.getAnswer());

		return objQuestion;
	}

	/**
	 * Reads a question from a JSON
	 * 
	 * @param jsonObjectA
	 * @return
	 */
	public Question readQinJSON(JSONObject jsonObjectA) {
		String bd = (String) jsonObjectA.get("bd");
		String sujet = (String) jsonObjectA.get("sujet");
		String titre = (String) jsonObjectA.get("titre");
		String contenu = (String) jsonObjectA.get("contenu");
		String bonn_reponse = (String) jsonObjectA.get("bonne_reponse");

		return new Question(bd, sujet, titre, contenu, bonn_reponse);

	}

	/**
	 * Transforms a list of question to a JSONArray
	 * 
	 * @param listeE
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public JSONArray createQListJSON(ArrayList<Question> listeE) {
		Iterator<Question> iteratorListeE = listeE.iterator();
		JSONArray listEJson = new JSONArray();
		while (iteratorListeE.hasNext()) {
			Question ex = iteratorListeE.next();
			listEJson.add(createQinJSON(ex));
		}
		return listEJson;
	}

	/**
	 * Transforms a JSONArray to LIST of Question
	 * 
	 * @param a
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Question> readQListJSON(JSONArray a) {
		ArrayList<Question> listeA = new ArrayList<>();
		Iterator<JSONObject> iterator = a.iterator();
		while (iterator.hasNext()) {
			JSONObject e = iterator.next();
			listeA.add(readQinJSON(e));
		}
		return listeA;
	}

}
