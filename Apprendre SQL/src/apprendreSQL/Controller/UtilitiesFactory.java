package apprendreSQL.Controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

/**
 * Functions that don't really fit anywhere specific are put in this class.
 *
 */
public class UtilitiesFactory {
	/**
	 * Opens a URL on the web browser.
	 * @param uri
	 */
	public static void open(URI uri) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(uri);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * A method that creates the questions in filmExercises.json
	 */
	protected static void createJsonTemp() {
		JsonManager jsonManager = new JsonManager();

		jsonManager.addQuestion("films.db", "SELECT", "Question 1 ", "1- Lister tous les films existants .",
				"SELECT * FROM FILM;");

		jsonManager.addQuestion("films.db", "SELECT", "Question 2", "2- Afficher les films tri�s par ordre de sortie .",
				"SELECT * from Film ORDER BY annee; ");
		jsonManager.addQuestion("films.db", "SELECT", "Question 3",
				"3- Afficher le titre de tous les films dont la date d'apparition est 1937 .",
				"SELECT titre FROM Film WHERE annee=1937; ");
		jsonManager.addQuestion("films.db", "SELECT", "Question 4",
				"4- Trouver l' ann�e d'apparition du film 'Star Wars' .",
				"SELECT annee From Film WHERE titre='Star Wars';");
		jsonManager.addQuestion("films.db", "SELECT", "Question 5",
				"5- Trouver le nom du r�alisateur du film 'Titanic' .",
				"Select realisateur  FROM film WHERE titre='Titanic';");
		jsonManager.addQuestion("films.db", "SELECT", "Question 6",
				"6- Est-ce qu'il existe un film dans la base de donn�es 'Films' qui s'appelle 'Alice In Wonderland'?(afficher tout s'il existe)",
				"Select * FROM film WHERE titre='Alice In Wonderland';");
		jsonManager.addQuestion("films.db", "SELECT", "Question 7",
				"7- Afficher tous les titres des films par ordre Alphabetique",
				"select titre from film order by titre;");
		jsonManager.addQuestion("films.db", "SELECT", "Question 8",
				"8- Afficher tous les nom des critiques par ordre Alphabetique",
				"select nom from critique order by nom;");

		jsonManager.addQuestion("films.db", "SELECT", "Question 9",
				"9- Afficher tous les critiques(nom,rID) par ordre croissant(riD)",
				"select nom,rID from critique order by rID;");
		jsonManager.addQuestion("films.db", "SELECT", "Question 10",
				"10- Afficher toutes les evaluations par ordre de dateEvaluation",
				"select * from evaluation order by dateEvaluation;");

		// Insert

		jsonManager.addQuestion("films.db", "INSERT", "Question 11",
				"1- Ajouter le film 'The Magnificent Seven' de '2016' qui est r�alis� par 'Antoine Fuqua' avec mID=111",
				"INSERT INTO FILM VALUES(111,'The Magnificent Seven',2016,'Antoine Fuqua');");
		jsonManager.addQuestion("films.db", "INSERT", "Question 12",
				"2- Ajouter le film r�aliser par 'Jacques Audiard' qui porte le titre 'the sisters brothers' , r�aliser en '2018' avec mID=112",
				"INSERT INTO FILM VALUES (112,'the sisters brothers' , 2018 , 'Jacques Audiard');");
		jsonManager.addQuestion("films.db", "INSERT", "Question 13",
				"3- Ajouter le film :'111,Joker,2019,Todd Phillips' Ainsi que '110,Avengers Endgame,2019,Anthony et Joe Russo' en utilisant une seule requ�te.",
				"INSERT INTO FILM VALUES(111,'Joker',2019,'Todd Phillips'),(110,'Avengers Endgame',2019,'Anthony et Joe Russo');");
		jsonManager.addQuestion("films.db", "INSERT", "Question 14",
				"4- Ajouter le critique 'Leonard Maltin' avec rID='209'.",
				"INSERT INTO critique values(209,'Leonard Maltin');");
		jsonManager.addQuestion("films.db", "INSERT", "Question 15",
				"4- Faites en sort que le film 'Titanic'(mid 105) re�oit une critique de (205,'Chris Jackson') avec la note de 5 le '2012-01-01' .",
				"INSERT INTO evaluation values(205,105,5,'2012-01-01');");

		// delete

		jsonManager.addQuestion("films.db", "DELETE", "Question 16", "1- Supprimer le film 'Star Wars'.",
				"DELETE FROM Film Where titre = 'Star Wars';");
		jsonManager.addQuestion("films.db", "DELETE", "Question 17",
				"2- Supprimer tous les films r�alis�s par James Cameron.",
				"DELETE FROM film where realisateur = 'James Cameron';");
		jsonManager.addQuestion("films.db", "DELETE", "Question 18",
				"3- Supprimer tous les films qui ont �t� r�alis� entre 1937 et 1977.",
				"DELETE FROM Film where annee < 1977 and annee > 1937;");
		jsonManager.addQuestion("films.db", "DELETE", "Question 19",
				"4- Supprimer l'�valuation dont la date d'�valuation est '2011-01-12'.",
				"DELETE FROM evaluation where DateEvaluation='2011-01-12';");

		jsonManager.addQuestion("films.db", "DELETE", "Question 20",
				"5- Supprimer le critique dont le nom est 'Daniel Lewis'.",
				"DELETE FROM critique where nom='Daniel Lewis';");

		jsonManager.addQuestion("films.db", "DELETE", "Question 21",
				"6- Supprimer l'�valuation dont la date d'�valuation est '2011-01-27' et la note de 4.",
				"DELETE FROM evaluation where DateEvaluation='2011-01-27' and note=4;");

		// JOIN

		jsonManager.addQuestion("films.db", "JOIN", "Question 22 ",
				"1-Donner le titre des films ayant obtenus une note sup�rieur � 2",
				"select titre from film inner join evaluation ON (film.mID=evaluation.mId) where EVALUATION.NOTE>2;");
		jsonManager.addQuestion("films.db", "JOIN", "Question 23 ",
				"2-Afficher les films qui ont �t� not�s par Sarah Martinez et qui ont la note de 4 ",
				"select * from ( (film f inner join evaluation e ON (e.mID=f.mID) ) as fe inner join critique c ON (c.rID=fe.rID))  where c.nom='Sarah Martinez';");
		jsonManager.addQuestion("films.db", "JOIN", "Question 24 ",
				"3-Donnez tous les films qui ont �t� evalu�s entre le 01-01-2011 et le 15-01-2011 ",
				"select * from film f inner join evaluation e ON (f.mID = e.mID) where dateEvaluation >'01-01-2011' and dateEvaluation<'15-01-2011';");
		jsonManager.addQuestion("films.db", "JOIN", "Question 25 ",
				"4-Donner les notes du film critiqu� par 'James Cameron'",
				"select note from evaluation e , critique c where e.rID=c.rID and c.nom='James Cameron';");
		jsonManager.addQuestion("films.db", "JOIN", "Question 26 ", "5-Quelle est la note du film 'Avatar'?",
				"select note from Evaluation e inner join film f ON (e.mID=f.mID) where titre='Avatar';");

		// COUNT/SUM
		jsonManager.addQuestion("films.db", "COUNT&SUM", "Question 27",
				"1- Donner la somme de toutes les �toiles obtenues par tous les films.",
				" select Sum(note) from evaluation;");

		jsonManager.addQuestion("films.db", "COUNT&SUM", "Question 28",
				"2- R�pertoriez tous les r�alisateurs une seule fois.", "select distinct (realisateur) from film;");

		jsonManager.addQuestion("films.db", "COUNT&SUM", "Question 29",
				"3- Combien de films ont �t� r�alis�s par James Cameron.(nombre de titre)",
				" select count(titre) from film where realisateur='James Cameron';");
		jsonManager.addQuestion("films.db", "COUNT&SUM", "Question 30",
				"4- Combien de films ont obtenu une note inf�rieur � 4 (nombre de mID)",
				" select count(mID) from evaluation where note>4;");
		jsonManager.addQuestion("films.db", "COUNT&SUM", "Question 31",
				"5-Combien de films ont �t� realis�s par James Cameron � l'ann�e 1997 (nombre de titre)",
				"select count(titre) from film where realisateur='James Cameron'and annee=1997;");

		// Update

		jsonManager.addQuestion("films.db", "UPDATE", "Question 32",
				"1- Modifier la date de sortie du film 'Titanic' � 1993.",
				"update film set annee=1993 where titre ='Titanic';");

		jsonManager.addQuestion("films.db", "UPDATE", "Question 33",
				"2- Modifier le titre du film de Victor Fleming � 'GWTW'.",
				"update film set titre='GWTW' where realisateur='Victor Fleming';");

		jsonManager.addQuestion("films.db", "UPDATE", "Question 34",
				"3- Modifier le nom du r�alisateur du film Avatar par celui de David Crew.",
				"update film set realisateur='David Crew' where titre='Avatar';");

		jsonManager.addQuestion("films.db", "UPDATE", "Question 35",
				"4- Modifier l'ann�e de r�alisation dont le r�alisateur est 'James Cameron' � 1944.",
				"update film set annee=1944 where realisateur='James Cameron';");
		jsonManager.addQuestion("films.db", "UPDATE", "Question 36",
				"5- Modifier la note des films dont la note = 2 par la note 4  l� o� la date d'�valuation est le '2011-01-22'.",
				"update evaluation set note=4 where dateEvaluation='2011-01-22'and note=2;");

		jsonManager.addQuestion("films.db", "UPDATE", "Question 37",
				"6- Changer le nom de critique dont le rID est 203 par BH.",
				"Update critique set nom='BH' where rId=203;");

		// select dans select

		jsonManager.addQuestion("films.db", "SELECT dans SELECT", "Question 38",
				"1- Indiquez le titre des films o� l'ann�e est superieure � celle du film Titanic",
				"select titre from film where annee > (select annee from film where titre='Titanic');");

		jsonManager.addQuestion("films.db", "SELECT dans SELECT", "Question 39 ",
				"2- Afficher tous les film dont le r�alisateur est celui du film 'Snow White'",
				"select * from film where realisateur=(select realisateur from film where titre='Snow White');");
		jsonManager.addQuestion("films.db", "SELECT dans SELECT", "Question 40 ",
				"3- Donner le titre ainsi que l'ann�e des films dont l'ann�e de r�alisation est inferieure � l'ann�e de r�alisation du film Avatar",
				"select titre,annee from film where annee<(select annee from film where titre='Avatar');");

		jsonManager.addQuestion("films.db", "SELECT dans SELECT", "Question 41",
				"4- Donnez les evaluations ayant obtenus la note maximum.",
				"select * from evaluation where note=(select max(note) from evaluation);");

		jsonManager.createJSON("resource/filmExercises.json");

	}

}
