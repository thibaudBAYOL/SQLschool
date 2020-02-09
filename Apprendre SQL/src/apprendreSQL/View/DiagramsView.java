package apprendreSQL.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import apprendreSQL.Controller.DiagramUtils;
import apprendreSQL.Controller.UtilitiesFactory;

/**
 * 
 * The viewport for the application to show diagrams, as well as notifications and
 * ultimately error messages related to the installation.
 * 
 */

@SuppressWarnings("serial")
public class DiagramsView extends JPanel {
	private static final String URL = "https://graphviz.gitlab.io/_pages/Download/Download_windows.html";
	private JPanel viewPort;
	private JLabel relationalDiagram;
	private String dir = DiagramUtils.temp;
	private JScrollPane diagramScrollPane;
	private JProgressBar progressBar = new JProgressBar();

	public DiagramsView() {
		init();
	}

	/**
	 * Initialize our swing components.
	 */
	private void init() {
		this.relationalDiagram = new JLabel();
		this.viewPort = new JPanel();

		relationalDiagram.setHorizontalAlignment(JLabel.CENTER);
		relationalDiagram.setVerticalAlignment(JLabel.CENTER);

		viewPort.setLayout(new BorderLayout());
		viewPort.setBackground(Color.decode("#F7F7F7"));
		viewPort.add(relationalDiagram, BorderLayout.CENTER);

		if (new DiagramUtils().checkOS()) {
			if (new DiagramUtils().checkInstallation()) {
				relationalDiagram.setText("S�lectionnez une base de donn�es pour commencer.");
			} else {
				notifyMissingSoftware();
			}
		} else {
			notifyIncompatibleOS();
		}

		this.diagramScrollPane = new JScrollPane(viewPort);
		diagramScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.;
		c.weighty = 1.;
		add(diagramScrollPane, c);

		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		c.weighty = 0;
		add(progressBar, c);
	}

	/**
	 * Scans .../graphs/temp for a diagram file and draws it on the screen. Notifies
	 * the user when there are no relationships between the tables of the database.
	 */
	public void loadRelationalDiagram(boolean isInstalled) {
		File schemas = new File(dir + "\\diagrams\\summary");
		relationalDiagram.setIcon(null);
		relationalDiagram.revalidate();

		if (isInstalled) {
			if (schemas != null) {
				for (File file : schemas.listFiles()) {
					if (file != null && file.getName().equalsIgnoreCase("relationships.real.large.png")) {
						try {
							BufferedImage img = ImageIO.read(file);
							BufferedImage img_cropped = img.getSubimage(0, 0, img.getWidth(), img.getHeight() - 50);
							relationalDiagram.setText(null);
							relationalDiagram.setIcon(new ImageIcon(img_cropped));
							viewPort.repaint();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						relationalDiagram.setIcon(null);
						relationalDiagram.revalidate();
						relationalDiagram.setText("Les tables de la base de donn�es n'ont pas de relations.");
					}
				}
			}
		}

	}

	/**
	 * Notifies the user for missing Graphviz installation and links to the
	 * download page.
	 */
	void notifyMissingSoftware() {
		relationalDiagram.setText(null);
		relationalDiagram.revalidate();

		JButton link = new JButton();
		link.setText(
				"<html>Cette option utilise Graphviz. Vous pouvez t�lecharger la derniere version <font color=\"#8c00a1\"><U>ici.</U></font></html>");
		link.setHorizontalAlignment(SwingConstants.CENTER);
		link.setOpaque(true);

		URI uri;
		try {
			uri = new URI(URL);
			link.setToolTipText(uri.toString());
			link.addActionListener(e -> UtilitiesFactory.open(uri));
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		viewPort.add(link);
	}

	/**
	 * Notifies the user for incompatible OS.
	 */
	void notifyIncompatibleOS() {
		relationalDiagram.setText("Cette fonctionnalit� est disponible uniquement sous Windows.");
		relationalDiagram.revalidate();
	}

	/**
	 * Notifies the user for missing .../graphs directory.
	 */
	void notifyUnavailableDirectory() {
		relationalDiagram.setIcon(null);
		relationalDiagram.revalidate();
		relationalDiagram.setText("La r�pertoire 'graphs' est introuvable.");

	}

	/**
	 * Animates the progressbar when scanning for diagrams.
	 */
	public void showProgress() {
		progressBar.setIndeterminate(true);

	}

	/**
	 * Updates the progressbar when scanning for diagrams.
	 */
	public void hideProgress() {
		progressBar.setIndeterminate(false);

	}
}
