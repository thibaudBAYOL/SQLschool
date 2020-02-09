package apprendreSQL.View;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import apprendreSQL.Controller.EventManager;

public interface SimilarFunctions {

	/**
	 * A function that creates a new Subject
	 * 
	 * @param btnNouveauSujet
	 * @param comboBoxSujet
	 * @param textField_sujet
	 */
	public default void newSubject(JButton btnNouveauSujet, JComboBox<String> comboBoxSujet, JTextField textField_sujet,
			ArrayList<String> subjects) {
		if (btnNouveauSujet.getText().equals("Nouveau sujet")) {

			comboBoxSujet.setVisible(false);
			textField_sujet.setVisible(true);
			textField_sujet.setText("");
			btnNouveauSujet.setText("Liste des Sujet");
		} else {
			if (!textField_sujet.getText().isEmpty()) {
				subjects.add(textField_sujet.getText());
				comboBoxSujet.addItem(textField_sujet.getText());
			}
			comboBoxSujet.setVisible(true);
			textField_sujet.setVisible(false);
			btnNouveauSujet.setText("Nouveau sujet");

		}
	}

	/**
	 * This function implements the HighlightListener
	 * 
	 * @param frame
	 * @param textField
	 * @param textArea
	 * @param textArea_1
	 * @param lblNo
	 * @param lblBd
	 * @param comboBox
	 * @return
	 */
	public default boolean checkFields(JFrame frame, JTextField textField, JTextArea textArea, JTextArea textArea_1) {
		if (textField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Vous n'avez pas nomm� votre question.", "Erreur",
					JOptionPane.WARNING_MESSAGE);
			new HighlightListener(textField);

		} else {
			if (textArea.getText().isEmpty() || textArea_1.getText().isEmpty()) {
				JOptionPane.showMessageDialog(frame,
						"Vous n'avez pas compl�t� le(s) champ(s) 'Question' et/ou 'R�ponse'.", "Erreur",
						JOptionPane.WARNING_MESSAGE);
				new HighlightListener(textArea_1);
				new HighlightListener(textArea);
			} else {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * A function that verifies if the SQL query "answer" works with the data base "dbName". 
	 * 
	 * @param eventManager
	 * @param answer ( SQL query )
	 * @param dbName (data base name used)
	 * @param eventManager (to execute the request)
	 * @return Boolean if the query work
	 */
	public default Boolean verifyExecution(EventManager eventManager,String answer,String dbName) {
		
		String reponse = EventManager.submit(answer, eventManager.getConnectionsMap().get(dbName));
		if(reponse.startsWith("Erreur:")) {
			JOptionPane.showMessageDialog(null,
					"La requ�te SQL en r�ponse ne fonctionne pas. \n "+reponse, "Attention ",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

}
