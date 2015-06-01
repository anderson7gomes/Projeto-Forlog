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
import javax.swing.table.*;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class TelaPrincipal extends JFrame implements ActionListener,KeyListener{
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
    private JButton botaoSE_ENTAO;
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
		setResizable(false);
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
		// itemNovo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, 0));
		itemNovo.setMnemonic(KeyEvent.VK_N);
		itemNovo.addActionListener(this);
		
		itemAbrir = new JMenuItem("Abrir");
		// itemAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, 0));
		itemAbrir.setMnemonic(KeyEvent.VK_A);
		itemAbrir.addActionListener(this);
		
		itemSalvar = new JMenuItem("Salvar");
		// itemSalvar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, 0));
		itemSalvar.setMnemonic(KeyEvent.VK_S);
		itemSalvar.setEnabled(false);
		itemSalvar.addActionListener(this);
		
		itemSair = new JMenuItem("Sair");
		// itemSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, 0));
		itemSair.setMnemonic(KeyEvent.VK_R);
		itemSair.addActionListener(this);
		
		menuFile.add(itemNovo);
		menuFile.add(itemAbrir);
		menuFile.add(itemSalvar);
		menuFile.add(itemSair);

		itemDesenvolvedores = new JMenuItem("Desenvolvedores");
		// itemDesenvolvedores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, 0));
		itemDesenvolvedores.setMnemonic(KeyEvent.VK_D);
		itemDesenvolvedores.addActionListener(this);
		
		itemAjuda = new JMenuItem("Ajuda");
		// itemAjuda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, 0));
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
		inputFormula.addKeyListener(this);

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
		botaoSE_ENTAO = new JButton("SE_ENTAO");
		botaoSSE = new JButton("SSE");
		botaoNEGACAO = new JButton("NEGAÇÃO");

		botaoE.addActionListener(this);
		botaoOU.addActionListener(this);
		botaoSE_ENTAO.addActionListener(this);
		botaoSSE.addActionListener(this);
		botaoNEGACAO.addActionListener(this);

		panelLogica2.add(botaoE);
		panelLogica2.add(botaoOU);
		panelLogica2.add(botaoNEGACAO);
		panelLogica2.add(botaoSE_ENTAO);
		panelLogica2.add(botaoSSE);
		//FIM Botões proposições

		panelTable = new JPanel();
		panelContent.add(panelTable);
		tabelaVerdade = new JTable();
		//tabelaVerdade.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scroll = new JScrollPane(tabelaVerdade);  
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
		panelTable.add(scroll);
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
    		case "SE_ENTAO":	
    			button_SE_ENTAO_Pressed();
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
    public void setTable(){
    	TableModel dataModel = new AbstractTableModel() {
          public int getColumnCount() { 
          	return formula.getQuantidadeAtomos() + 1; 
          }
          public int getRowCount() { 
          	return (int)(Math.pow(2,formula.getQuantidadeAtomos()));
          }
          public Object getValueAt(int row, int col) { 
            String binario = Integer.toBinaryString(row);
            String complemento = "";
            for(int i = 0 ; i < formula.getQuantidadeAtomos() - binario.length();i++)
                complemento+="0";
            binario = complemento+binario;
            for(int i = 0 ; i < formula.getQuantidadeAtomos();i++){
                if(binario.charAt(i) == '0'){
                    formula.setValorIndex(i,false);        
                }
                else{
                    formula.setValorIndex(i,true);
                }
            }
          	if(col < formula.getQuantidadeAtomos())
          		return formula.getValorProposicao(formula.getProposicao(col));
          	return formula.getValor();
          }
          public String getColumnName(int columnIndex){
          	if(columnIndex < formula.getQuantidadeAtomos())
          		return String.valueOf(formula.getProposicao(columnIndex));
          	return "Resultado";
          }
      };
      tabelaVerdade.setModel(dataModel);
      pack();
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
    public void button_SE_ENTAO_Pressed(){
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
    		itemSalvar.setEnabled(true);
    		formula = formulaTeste;
    		//JOptionPane.showMessageDialog(null,"Formula Válida");
    		setTable();
    	}catch(IllegalArgumentException e){
    		JOptionPane.showMessageDialog(null,"A formula não é válida");
    	}catch(Exception e){
    		System.out.println("Algum Erro");
    	}

    }
    
    public void button_DesenharArvore_Pressed(){
    	// JOptionPane.showMessageDialog(null,"Foi Desenhar");
    	formula.draw();
    }
    
    public void button_Abrir_Pressed(){
    		
    	JFileChooser chooser = new JFileChooser();
    		
    	chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    	
    	int retrival = chooser.showOpenDialog(this);
    	
    	if (retrival == JFileChooser.APPROVE_OPTION) {
    	
    		try {
    	
    			File file = chooser.getSelectedFile();
    		
    			BufferedReader br = new BufferedReader(new FileReader(file));
    	
    		}
    	
    	}
    	
    }
    
    public void button_Novo_Pressed(){
    	JOptionPane.showMessageDialog(null,"Foi Novo");
    }
    
    public void button_Salvar_Pressed() {
    	
    	JFileChooser save = new JFileChooser();
    	
    	int retrival = save.showSaveDialog(this);
   
		if (retrival == JFileChooser.APPROVE_OPTION) {
		
			try (BufferedWriter bw = new BufferedWriter(
						new FileWriter(save.getSelectedFile() + ".flg"))) {
		
				String formula = inputFormula.getText();
						
				bw.write(formula);		
		
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}	
		
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
    public void keyPressed(KeyEvent e) {     
 		   // String texto = inputFormula.getText();
 		   // JOptionPane.showMessageDialog(null,texto);
 		   itemSalvar.setEnabled(false);
    }  
    public void keyReleased(KeyEvent e){  
    	if(e.getKeyCode() >= KeyEvent.VK_1 && e.getKeyCode() <= KeyEvent.VK_5){
	    	String texto = inputFormula.getText();
	    	texto = texto.replace("1"," " + '\u2227' + " ");
	    	texto = texto.replace("2"," " + '\u2228' + " ");
	    	texto = texto.replace("3"," " + '\u223c' + " ");
	    	texto = texto.replace("4"," " + '\u2192' + " ");
	    	texto = texto.replace("5"," " + '\u2194' + " ");
	    	inputFormula.setText(texto);
    	}
    }  
    public void keyTyped(KeyEvent e){         
    }
} // fim da classe TelaPrincipal
