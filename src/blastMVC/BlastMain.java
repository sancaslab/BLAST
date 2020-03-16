package blastMVC;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class BlastMain {

	public static void createAndShowBlastGUI() {

		// Creamos un frame y un controlador llamando a los métodos constructores de sus
		// respectivas clases
		BlastFrame frame = new BlastFrame();
		BlastControl controller = new BlastControl(frame);

		// Añadimos funcionalidad a los elementos (a través del controlador)
		frame.getComboSeq().addActionListener(controller);
		frame.getButtonQuery().addActionListener(controller);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		// Añadimos un mensaje de información en la inicialización del frame (informando
		// sobre el porcentaje por defecto)
		ImageIcon icon = new ImageIcon("percentage.jpg");
		JOptionPane.showMessageDialog(null, "Default Similarity Percentage: 0.8", "Message", JOptionPane.INFORMATION_MESSAGE,
				icon);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				createAndShowBlastGUI();
			}
		});

	}

}
