package apprendreSQL.View;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

/**
 * 
 * This class observes if a specific component is empty and if it's the case it
 * highlights its border red
 *
 */
public class HighlightListener implements DocumentListener {

	JTextComponent component;
	Border defaultBorder;
	Border highlightBorder;

	public HighlightListener(JTextComponent jtc) {
		highlightBorder = BorderFactory.createLineBorder(Color.red);
		component = jtc;
		defaultBorder = component.getBorder();
		component.getDocument().addDocumentListener(this);
		this.maybeHighlight();
	}

	public void insertUpdate(DocumentEvent e) {
		maybeHighlight();
	}

	public void removeUpdate(DocumentEvent e) {
		maybeHighlight();
	}

	public void changedUpdate(DocumentEvent e) {
		maybeHighlight();
	}

	/**
	 * The function responsible for highlighting the border red
	 */
	private void maybeHighlight() {
		if (!component.getText().isEmpty())
			component.setBorder(defaultBorder);
		else
			component.setBorder(highlightBorder);
	}

}