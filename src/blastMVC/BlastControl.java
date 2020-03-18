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
	private Object seq;

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

	// Métodos creados para comprobación del estado del porcentaje y del comboSeq
	public Boolean errorPorcentaje(Float p) {

		Boolean res = false;
		if (p < 0.1 || p > 1.0) {
			res = true;
		}

		return res;

	}

	public Boolean errorComboSeq(Object s) {

		// De alguna manera detectar que no se ha seleccionado ninguna secuencia
		Boolean res = false;
		if (s == null) {
			res = true;
		}
		return res;
	}

	// Método que hace la petición, llama al controlador que hemos importado
	// inicialmente
	public void query() {

		BlastController bCnt = new BlastController();

		try {
			String result = bCnt.blastQuery(typeQuery, dataBaseFile, dataBaseIndexes, (float) percent, seq.toString());
			frame.getTextAreaResult().append(result);
		}

		catch (Exception exc) {
			System.out.println("Error en la llamada: " + exc.toString());

			// De alguna forma hemos intentado solucionar el resto de errores anteriormente
			// antes de entrar en este
			// punto del método, por tanto sería el error que nos queda por solucionar
			// que la secuencia no esté en la base de datos
			frame.getTextAreaResult().setText("The sequence is incorrect");

		}

	}

	// Método que controla los fallos que hemos considerado relevantes a la hora de
	// utilizar la interfaz
	public void controlaFallosQuery() {

		// Suponemos que la petición será de proteínas, ya que la base de datos que
		// disponemos es de proteínas
		// este sería el código a añadir para la selección diferente
		/*
		 * if (frame.getRadioButtonProt().isSelected()) { typeQuery = 'p'; } else {
		 * typeQuery = 'n'; }
		 */

		typeQuery = 'p';
		percent = Float.valueOf(frame.getTextFieldPerc().getText());
		seq = frame.getComboSeq().getSelectedItem();

		if (errorPorcentaje(percent)) {
			frame.getTextAreaResult().setText("Error in Similarity Percentage: it must be between 0.1 and 1.0\n");
		}

		if (errorComboSeq(seq)) {
			frame.getTextAreaResult().setText("Error in Sequence: there isn't any sequence selected to make query");
		}

		if (errorPorcentaje(percent) & errorComboSeq(seq)) {
			frame.getTextAreaResult().setText("Error in Similarity Percentage: it must be between 0.1 and 1.0\n");
			frame.getTextAreaResult().append("Error in Sequence: there isn't any sequence selected to make query");
		}

		if (errorPorcentaje(percent) == false & errorComboSeq(seq) == false) {

			query();
		}

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
		// todo ello ya realizado por el método controlaFallosQuery()
		if (e.getActionCommand() == "MAKE QUERY") {

			controlaFallosQuery();

			/*
			 * if (frame.getRadioButtonProt().isSelected()) { typeQuery = 'p'; } else {
			 * typeQuery = 'n'; }
			 * 
			 * percent = Float.valueOf(frame.getTextFieldPerc().getText());
			 * errorPorcentaje(percent);
			 * 
			 * errorComboSeq(frame.getComboSeq().getSelectedItem()); seq =
			 * frame.getComboSeq().getSelectedItem().toString(); // errorComboSeq(seq);
			 * 
			 * BlastController bCnt = new BlastController();
			 * 
			 * try { String result = bCnt.blastQuery(typeQuery, dataBaseFile,
			 * dataBaseIndexes, (float) percent, seq);
			 * frame.getTextAreaResult().append(result);
			 * 
			 * }
			 * 
			 * catch (Exception exc) { System.out.println("Error en la llamada: " +
			 * exc.toString());
			 * 
			 * }
			 */

		}
	}

}
