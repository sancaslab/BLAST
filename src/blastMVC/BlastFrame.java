package blastMVC;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class BlastFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JRadioButton rbProt;
	private JRadioButton rbNuc;
	private JComboBox<String> comboSeq;
	private JLabel labelPerc;
	private JTextField tfPerc;
	private JButton bQuery;
	private JTextArea taResult;

	public BlastFrame() {
		// Método constructor a partir del cual vamos a obtener el frame con los
		// distintos paneles distribuidos correctamente

		setTitle("BLAST");
		setPreferredSize(new Dimension(700, 500));
		setLayout(new BorderLayout());

		// Primer panel al cual le dejamos el borde por defecto (FlowLayout)
		JPanel p1 = new JPanel();
		// Componentes de p1
		rbProt = new JRadioButton("PROTEIN", true);
		rbProt.setFont(new Font("SansSerif", Font.BOLD, 13));
		rbNuc = new JRadioButton("NUCLEOTID");
		rbNuc.setFont(new Font("SansSerif", Font.BOLD, 13));
		labelPerc = new JLabel("Percentage");
		labelPerc.setFont(new Font("SansSerif", Font.BOLD, 9));
		tfPerc = new JTextField("0.8", 3); // Añadimos un porcentaje por defecto = 0.8
		// Añadimos los diferentes componentes a p1
		p1.add(rbProt);
		p1.add(rbNuc);
		p1.add(labelPerc);
		p1.add(tfPerc);
		// Establecemos un borde vacío (espacio con el resto de componentes del frame)
		p1.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
		// Añadimos p1 al JFrame que estamos creando
		add(p1, BorderLayout.BEFORE_FIRST_LINE);

		// Segundo panel con GridLayout para la distribución de los elementos
		JPanel p2 = new JPanel(new GridLayout(2, 1, 60, 20));
		// Borde vacío principalmente para dar espacio entre los elementos y los bordes
		// del JFrame
		p2.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		// Componentes de p2
		comboSeq = new JComboBox<String>();
		comboSeq.setEditable(true);
		Border b1 = new LineBorder(Color.GRAY);
		Font font = new Font("Courier", Font.BOLD, 14);
		Border b2 = new TitledBorder(b1, "Introduce or select SEQUENCE", TitledBorder.LEFT, TitledBorder.ABOVE_TOP,
				font);
		comboSeq.setBorder(b2);
		taResult = new JTextArea(70, 15);
		Border b3 = new LineBorder(Color.LIGHT_GRAY);
		Border b4 = new TitledBorder(b3, "RESULT of query", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, font);
		taResult.setBorder(b4);
		// Añadimos los elementos al panel
		p2.add(comboSeq);
		p2.add(taResult);
		JScrollPane pScroll = new JScrollPane(taResult); // JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
															// JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		p2.add(pScroll);
		// Añadimos p2 al JFrame
		add(p2, BorderLayout.CENTER);

		// Añadidos para hacer pruebas de la funcionalidad
		/*
		 * comboSeq.addItem(
		 * "MYYIMFLYNMLLIIILIFYSIVGVPIIIFNNNYYWDPDIFLFIIYYFIKFIIIFNLYLYYMINYIVYTPSGSPPGRGTYILLYNMLYSYNMFIDYVMKFITCVTYMYLMFWLLSPTPSPYYVSEVPVS"
		 * );
		 * 
		 * comboSeq.addItem(
		 * "MVQRWLYSTNAKDIAVLYFMLAIFSGMAGTAMSLIIRLELAAPGSQYLHGNSQLFNGAPTSAYISLMRTALVLWIINRYLKHMTNSVGANFTGTMACHKTPMISVGGVKCYMVRLTNFLQVFIRITISSYHLDMVKQVWLFYVEVIRLWFIVLDSTGSVKKMKD"
		 * +
		 * "TNNTKGNTKSEGSTERGNSGVDRGMVVPNTQMKMRFLNQVRYYSVNNNLKMGKDTNIELSKDTSTSDLLEFEKLVM"
		 * +
		 * "DNMNEENMNNNLLSIMKNVDMLMLAYNRIKSKPGNMTPGTTLETLDGMNMMYLNKLSNELGTGKFKFKPMRMVNIPKPKGGMRPLSVGNPRDKIVQEVMRMILDTIFDKKMSTHSHGFRKNMSCQTAIWEVRNMFGGSNWFIEVDLKKCFDTISHDLIIK"
		 * +
		 * "ELKRYISDKGFIDLVYKLLRAGYIDEKGTYHKPMLGLPQGSLISPILCNIVMTLVDNWLEDYINLYNKGKVKKQHPTYKKLSRMIAKAKMFSTRLKLHKERAKGPTFIYNDPNFKRMKYVRYADDILIGVLGSKNDCKMIKRDLNNFLNSLGLTMNEEKT"
		 * +
		 * "LITCATETPARFLGYNISITPLKRMPTVTKTIRGKTIRSRNTTRPIINAPIRDIINKLATNGYCKHNKNGRMGVPTRVGRWTYEEPRTIINNYKALGRGILNYYKLATNYKRLRERIYYVLYYSCVLTLASKYRLKTMSKTIKKFGYNLNIIENDKLIAN"
		 * +
		 * "FPRNTFDNIKKIENHGMFMYMSEAKVTDPFEYIDSIKYMLPTAKANFNKPCSICNSTIDVEMHHVKQLHRGMLKATKDYITGRMITMNRKQIPLCKQCHIKTHKNKFKNMGPGM"
		 * );
		 */

		// Tercer Panel con BorderLayout
		JPanel p3 = new JPanel(new BorderLayout());
		// Componente de p3
		bQuery = new JButton("MAKE QUERY");
		bQuery.setFont(new Font("SansSerif", Font.BOLD, 15));
		bQuery.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		// Añadimos el componente al panel con su respectivo borde vacío
		p3.add(bQuery, BorderLayout.LINE_START);
		p3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		// Añadimos p3 al JFrame
		add(p3, BorderLayout.SOUTH);

	}

	// Métodos getters para poder acceder a estos elementos que son necesarios en el
	// controlador y/o en la clase principal (main)

	public JRadioButton getRadioButtonProt() {
		return this.rbProt;
	}

	public JComboBox<String> getComboSeq() {
		return this.comboSeq;
	}

	public JTextArea getTextAreaResult() {
		return this.taResult;
	}

	public JButton getButtonQuery() {
		return this.bQuery;
	}

	public JTextField getTextFieldPerc() {
		return this.tfPerc;

	}

}
