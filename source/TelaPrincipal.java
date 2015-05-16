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
import java.awt.event.*;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JOptionPane;

public class TelaPrincipal extends JFrame implements ActionListener{
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
    private JMenuItem itemNovo;
    private JMenuItem itemAbrir;
    private JMenuItem itemSalvar;
    private JMenuItem itemSair;
    private JMenuItem itemDesenvolvedores;
    private JMenuItem itemAjuda;
    public TelaPrincipal(String title){
    	super(title);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    	setSize(700,700);
		setLocationRelativeTo(null);
		setVisible(true);
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
		}catch(Exception e){
			System.out.println(e);
		}

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
		
		itemNovo = new JMenuItem("Novo");
		itemNovo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, 0));
		itemNovo.setMnemonic(KeyEvent.VK_N);
		itemNovo.addActionListener(this);
		
		itemAbrir = new JMenuItem("Abrir");
		itemAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, 0));
		itemAbrir.setMnemonic(KeyEvent.VK_A);
		itemAbrir.addActionListener(this);
		
		itemSalvar = new JMenuItem("Salvar");
		itemSalvar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, 0));
		itemSalvar.setMnemonic(KeyEvent.VK_S);
		itemSalvar.addActionListener(this);
		
		itemSair = new JMenuItem("Sair");
		itemSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, 0));
		itemSair.setMnemonic(KeyEvent.VK_R);
		itemSair.addActionListener(this);
		
		menuFile.add(itemNovo);
		menuFile.add(itemAbrir);
		menuFile.add(itemSalvar);
		menuFile.add(itemSair);

		itemDesenvolvedores = new JMenuItem("Desenvolvedores");
		itemDesenvolvedores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, 0));
		itemDesenvolvedores.setMnemonic(KeyEvent.VK_D);
		itemDesenvolvedores.addActionListener(this);
		
		itemAjuda = new JMenuItem("Ajuda");
		itemAjuda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, 0));
		itemAjuda.setMnemonic(KeyEvent.VK_J);
		itemAjuda.addActionListener(this);

		menuAbout.add(itemDesenvolvedores);
		menuAbout.add(itemAjuda);

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
		botaoGerarTabela.addActionListener(this);
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

		botaoE.addActionListener(this);
		botaoOU.addActionListener(this);
		botaoSE.addActionListener(this);
		botaoSSE.addActionListener(this);
		botaoNEGACAO.addActionListener(this);

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
    public void actionPerformed(ActionEvent e){
    	//Eventos
    	JOptionPane.showMessageDialog(null,"Tecla: " + e.getModifiers());
    }

} // fim da classe TelaPrincipal
