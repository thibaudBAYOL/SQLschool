package jUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import apprendreSQL.Model.Question;
import apprendreSQL.Controller.ConnectionSQLite;
import apprendreSQL.Controller.Corrector;
import apprendreSQL.Controller.JsonManager;

public class TestExercisesTrue {

	Corrector c;
	private static ConnectionSQLite moviesConnection;

	@Test
	public void setUP() {
		c = new Corrector();
		moviesConnection = new ConnectionSQLite("resource/films.db");
		moviesConnection.connect();
	}

	@Test
	public void testExercisesALL() {
		setUP();
		boolean bb = true;
		JsonManager jsonManager = new JsonManager();

		jsonManager.readFileQuestion("resource/filmExercises.json");

		for (Question q : jsonManager.getListQuestion()) {

			Boolean b = c.correction(q.getAnswer(), q.getAnswer(), moviesConnection);
			if (!b) {
				System.out.println("==================================================>" + q.getTitleQuestion());
				System.out.println(c.getCommentaire().replaceAll("<br>", "\n"));
				bb = false;
				try {
					throw new Exception("LA BONNE REQUÊTE NE FONCTIONNE PAS EXO titre=" + q.getTitleQuestion()
							+ " reponce:" + q.getAnswer());
				} catch (Exception e) {
					System.out.println("A corriger");
					e.printStackTrace();

				}
			} else {
				System.out.println("Pas d'erreur trouver");
			}

		}
		assertTrue(bb);

	}

}
