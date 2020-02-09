package apprendreSQL.View;

import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;

import apprendreSQL.Controller.EventManager;

/**
 * This is the panel that holds both ExercisesPanel and EditorPanel.
 * 
 */

@SuppressWarnings("serial")
public class WindowedUpperPanel extends JPanel {
	
	private JSplitPane panelSpliter;
	private ExercisesPanel exercisePanel;
	private EditorPanel editorPanel;
	private JScrollPane exercisesScrollPane, editorScrollPane;

	public WindowedUpperPanel(EventManager manager) {
		init(manager);
	}

	/**
	 * Initialize our swing components.
	 * 
	 * @param manager object that coordinates, edits and transfers information
	 *                between the different swing objects of our project
	 */
	private void init(EventManager manager) {
		setLayout(new CardLayout());
		editorPanel = new EditorPanel();
		exercisePanel = new ExercisesPanel(manager);
		editorScrollPane = new JScrollPane(editorPanel);
		exercisesScrollPane = new JScrollPane(exercisePanel);

		editorScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		exercisesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		exercisesScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		panelSpliter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, exercisesScrollPane, editorScrollPane);
		panelSpliter.setOneTouchExpandable(true);
		add(panelSpliter);

	}

	public ExercisesPanel getExercisesPanel() {
		return exercisePanel;
	}

	public void setExercicesPanel(ExercisesPanel exercicesPanel) {
		this.exercisePanel = exercicesPanel;
	}

	public EditorPanel getEditorPanel() {
		return editorPanel;
	}

	public void setEditorPanel(EditorPanel editorPanel) {
		this.editorPanel = editorPanel;
	}

	public void updateExercisesView() throws IOException {
		exercisePanel.updateExercicesView();
		
	}

}
