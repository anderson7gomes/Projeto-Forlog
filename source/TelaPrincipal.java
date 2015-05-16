import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTable;

public class TelaPrincipal extends JFrame{
	private JPanel panel;
	private JPanel panelContent;
	private JPanel panelLogica;
	private JPanel panelTable;
	private JPanel panelLogica1;
	private JPanel panelLogica2;
    private JLabel label;
    private JTextField inputFormula;
    private JButton botaoGerarTabela;
    private JButton botaoE;
    private JButton botaoOU;
    private JButton botaoSE;
    private JButton botaoSSE;
    private JButton botaoNEGACAO;
    private JTable tabelaVerdade;
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenu menuAbout;
    public TelaPrincipal(String title){
    	super(title);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    	setSize(700,700);
		setLocationRelativeTo(null);
		setVisible(true);

		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		setContentPane(panel);
		//Menu
		menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuBar.setBackground(Color.white);
		menuFile = new JMenu("Arquivo");
		menuAbout = new JMenu("Sobre");
		menuBar.add(menuFile);
		menuBar.add(menuAbout);
		panel.add(menuBar,BorderLayout.NORTH);
		//FIM Menu

		panelContent = new JPanel();
		panelContent.setLayout(new BorderLayout());
		panel.add(panelContent);
		//Input da Formula
		panelLogica = new JPanel();
		panelLogica.setLayout(new BorderLayout());
		panelContent.add(panelLogica,BorderLayout.NORTH);
		panelLogica1 = new JPanel();
		panelLogica1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelLogica.add(panelLogica1,BorderLayout.NORTH);
		label = new JLabel("Formula:");
		panelLogica1.add(label);
		inputFormula = new JTextField("");
		inputFormula.setColumns(50);
		inputFormula.setSize(100,100);
		panelLogica1.add(inputFormula);
		botaoGerarTabela = new JButton("Gerar Tabela");
		panelLogica1.add(botaoGerarTabela);
		//FIM Input da Formula
		
		//Botões proposições
		panelLogica2 = new JPanel();
		panelLogica.add(panelLogica2);
		panelLogica2.setLayout(new FlowLayout(FlowLayout.CENTER));

		botaoE = new JButton("E");
		botaoOU = new JButton("OU");
		botaoSE = new JButton("SE");
		botaoSSE = new JButton("SSE");
		botaoNEGACAO = new JButton("NEGAÇÃO");
		panelLogica2.add(botaoE);
		panelLogica2.add(botaoOU);
		panelLogica2.add(botaoNEGACAO);
		panelLogica2.add(botaoSE);
		panelLogica2.add(botaoSSE);
		//FIM Botões proposições

		panelTable = new JPanel();
		panelContent.add(panelTable);
		tabelaVerdade = new JTable(3,3);
		panelTable.add(tabelaVerdade);
		pack();
    }

} // fim da classe TelaPrincipal
