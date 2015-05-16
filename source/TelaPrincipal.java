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
    private JButton botaoDesenharArvore;
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

    private Formula formula;
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
		menuFile.setMnemonic(KeyEvent.VK_R);
		menuAbout = new JMenu("Sobre");
		menuAbout.setMnemonic(KeyEvent.VK_O);
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
		panelLogica1.setLayout(new FlowLayout(FlowLayout.CENTER));
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

		botaoDesenharArvore = new JButton("Desenhar Arvore");
		botaoDesenharArvore.addActionListener(this);
		botaoDesenharArvore.setEnabled(false);
		panelLogica1.add(botaoDesenharArvore);		
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
    	switch(e.getActionCommand()){
    		case "Gerar Tabela":	
    			button_GerarTabela_Pressed();
    		break;
    		case "Desenhar Arvore":
    			button_DesenharArvore_Pressed();
    		break;
    		case "E":	
    			button_E_Pressed();
    		break;
    		case "OU":	
    			button_OU_Pressed();
    		break;
    		case "NEGAÇÃO":	
    			button_NEGACAO_Pressed();
    		break;
    		case "SE":	
    			button_SE_Pressed();
    		break;
    		case "SSE":	
    			button_SSE_Pressed();
    		break;
    		case "Abrir":	
    			button_Abrir_Pressed();
    		break;
    		case "Novo":	
    			button_Novo_Pressed();
    		break;
    		case "Salvar":	
    			button_Salvar_Pressed();
    		break;
    		case "Sair":	
    			button_Sair_Pressed();
    		break;
    		case "Desenvolvedores":	
    			button_Desenvolvedores_Pressed();
    		break;
    		case "Ajuda":	
    			button_Ajuda_Pressed();
    		break;
    	}
    }
    public void button_E_Pressed(){
    	// JOptionPane.showMessageDialog(null,"Foi E");
    	String texto = inputFormula.getText();
    	texto += " " + '\u2227' + " ";
    	inputFormula.setText(texto);
    }
    public void button_OU_Pressed(){
    	// JOptionPane.showMessageDialog(null,"Foi OU");
    	String texto = inputFormula.getText();
    	texto += " " + '\u2228' + " ";
    	inputFormula.setText(texto);
    }
    public void button_NEGACAO_Pressed(){
    	// JOptionPane.showMessageDialog(null,"Foi N");
    	String texto = inputFormula.getText();
    	texto += " " + '\u223c' + " ";
    	inputFormula.setText(texto);
    }
    public void button_SE_Pressed(){
    	// JOptionPane.showMessageDialog(null,"Foi SE");
    	String texto = inputFormula.getText();
    	texto += " " + '\u2192' + " ";
    	inputFormula.setText(texto);
    }
    public void button_SSE_Pressed(){
    	// JOptionPane.showMessageDialog(null,"Foi SSE");
    	String texto = inputFormula.getText();
    	texto += " " + '\u2194' + " ";
    	inputFormula.setText(texto);
    }
    public void button_GerarTabela_Pressed(){
    	// JOptionPane.showMessageDialog(null,"Foi GE");
    	String texto = inputFormula.getText();
    	try{
 	   		Formula formulaTeste = new Formula(texto);
    		botaoDesenharArvore.setEnabled(true);
    	}catch(IllegalArgumentException e){
    		JOptionPane.showMessageDialog(null,"A formula não é válida");
    	}
    	JOptionPane.showMessageDialog(null,"Passou");

    }
    public void button_DesenharArvore_Pressed(){
    	JOptionPane.showMessageDialog(null,"Foi Desenhar");
    }
    public void button_Abrir_Pressed(){
    	JOptionPane.showMessageDialog(null,"Foi Abrir");
    }
    public void button_Novo_Pressed(){
    	JOptionPane.showMessageDialog(null,"Foi Novo");
    }
    public void button_Salvar_Pressed(){
    	JOptionPane.showMessageDialog(null,"Foi Salva");
    }
    public void button_Sair_Pressed(){
    	// JOptionPane.showMessageDialog(null,"Foi Sair");
    	System.exit(0);
    }
    public void button_Desenvolvedores_Pressed(){
    	JOptionPane.showMessageDialog(null,"Foi Dev");
    }
    public void button_Ajuda_Pressed(){
    	JOptionPane.showMessageDialog(null,"Foi Ajuda");
    }
} // fim da classe TelaPrincipal
