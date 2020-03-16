package blastMVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import blast.BlastController;

public class BlastControl implements ActionListener {

	private BlastFrame frame;
	private static final String dataBaseFile = new String("yeast.aa");
	private static final String dataBaseIndexes = new String("yeast.aa.indexs");

	private char typeQuery;
	private Float percent;
	private String seq;

	// Constructor del controlador
	public BlastControl(BlastFrame f) {

		this.frame = f;
	}

	// Método que comprueba si la secuencia que se ha introducido en el comboBox se
	// encuentra ya en el mismo
	public boolean compruebaSiLaSecuenciaEstaEnElCombo(String s) {
		boolean res = false;
		for (int i = 0; i < frame.getComboSeq().getItemCount(); i++) {
			if (frame.getComboSeq().getItemAt(i).equals(s)) {
				res = true;
			}
		}

		return res;
	}

	// Método que controla las acciones que se pueden llevar a cabo en nuestra
	// interfaz
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// Introducción de una secuencia nueva
		// Llama al método anterior para así evitar que la secuencia que se introduzca
		// sea repetida
		if (e.getActionCommand() == "comboBoxEdited") {
			String s = frame.getComboSeq().getEditor().getItem().toString();
			if (!compruebaSiLaSecuenciaEstaEnElCombo(s)) {
				frame.getComboSeq().addItem(s);
			}
		}

		// Modificación de la secuancia seleccionada
		if (e.getActionCommand() == "comboBoxChanged") {
			Object s = frame.getComboSeq().getSelectedItem();
			frame.getComboSeq().setSelectedItem(s.toString());

		}

		// Pulsar el botón "MAKE QUERY" -> realiza la consulta a la base de datos y la
		// muestra en la interfaz en el componente correspondiente (textArea)
		if (e.getActionCommand() == "MAKE QUERY") {

			if (frame.getRadioButtonProt().isSelected()) {
				typeQuery = 'p';
			} else {
				typeQuery = 'n';
			}

			percent = Float.valueOf(frame.getTextFieldPerc().getText());
			seq = frame.getComboSeq().getSelectedItem().toString();
			BlastController bCnt = new BlastController();

			try {
				String result = bCnt.blastQuery(typeQuery, dataBaseFile, dataBaseIndexes, (float) percent, seq);
				frame.getTextAreaResult().append(result);

			}

			catch (Exception exc) {
				System.out.println("Error en la llamada: " + exc.toString());
			}

		}

	}

}
