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

public class TelaPrincipal extends JFrame implements ActionListener, 
		KeyListener {

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

    public TelaPrincipal(String title) {
    
    	super(title);
    	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    	setSize(700, 700);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		
		try {
		
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			 
		} catch(Exception e) {
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
		itemNovo.setMnemonic(KeyEvent.VK_N);
		itemNovo.addActionListener(this);
		
		itemAbrir = new JMenuItem("Abrir");
		itemAbrir.setMnemonic(KeyEvent.VK_A);
		itemAbrir.addActionListener(this);
		
		itemSalvar = new JMenuItem("Salvar");
		itemSalvar.setMnemonic(KeyEvent.VK_S);
		itemSalvar.setEnabled(false);
		itemSalvar.addActionListener(this);
		
		itemSair = new JMenuItem("Sair");
		itemSair.setMnemonic(KeyEvent.VK_R);
		itemSair.addActionListener(this);
		
		menuFile.add(itemNovo);
		menuFile.add(itemAbrir);
		menuFile.add(itemSalvar);
		menuFile.add(itemSair);

		itemDesenvolvedores = new JMenuItem("Desenvolvedores");
		itemDesenvolvedores.setMnemonic(KeyEvent.VK_D);
		itemDesenvolvedores.addActionListener(this);
		
		itemAjuda = new JMenuItem("Ajuda");
		itemAjuda.setMnemonic(KeyEvent.VK_J);
		itemAjuda.addActionListener(this);

		menuAbout.add(itemDesenvolvedores);
		menuAbout.add(itemAjuda);

		panel.add(menuBar, BorderLayout.NORTH);
		//FIM Menu

		panelContent = new JPanel();
		panelContent.setLayout(new BorderLayout());
		panel.add(panelContent);
		
		//Input da Formula
		panelLogica = new JPanel();
		panelLogica.setLayout(new BorderLayout());
		panelContent.add(panelLogica, BorderLayout.NORTH);
		
		panelLogica1 = new JPanel();
		panelLogica1.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelLogica.add(panelLogica1, BorderLayout.NORTH);
		
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
		JScrollPane scroll = new JScrollPane(tabelaVerdade);  
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
		panelTable.add(scroll);
		pack();

    }
    
    public void actionPerformed(ActionEvent e) {//HUB de eventos
    
    	switch (e.getActionCommand()) {
    	
    		case "Gerar Tabela":
    			
    			button_GerarTabela_Pressed();
    			break;
    			
    		case "Desenhar Arvore":
    		
    			button_DesenharArvore_Pressed();
    			break;
    			
    		case "E":
    			
    			button_E_Pressed(); 
    			inputFormula.requestFocus();
    			break;
    			
    		case "OU":
    			
    			button_OU_Pressed();
    			inputFormula.requestFocus();
    			break;

    		case "NEGAÇÃO":	
    		
    			button_NEGACAO_Pressed();
    			inputFormula.requestFocus();
    			break;
    			
    		case "SE_ENTAO":
    			
    			button_SE_ENTAO_Pressed();
    			inputFormula.requestFocus();
    			break;
  
    		case "SSE":
    			
    			button_SSE_Pressed();
    			inputFormula.requestFocus();
    			break;
    			
    		case "Abrir":
    			
    			button_Abrir_Pressed();
    			break;
    			
    		case "Novo":
    			
    			button_Novo_Pressed();
    			inputFormula.requestFocus();
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

    public void setTable() {//Monta tabela
    
   		TableModel dataModel = new AbstractTableModel() {
			   		
    		public int getColumnCount() { //Pega quantidade de Conluna na tabela
          		return formula.getQuantidadeAtomos() + 1; 
          	}
          	
          	public int getRowCount() { //Pega quantidade de Linhas na tabela
          		return (int) (Math.pow(2, formula.getQuantidadeAtomos()));
          	}
          	
          	public Object getValueAt(int row, int col) {//Pega valor na célula
          	 
            	String binario = Integer.toBinaryString(row);//Consegue forma binária da linha
            	String complemento = "";
            	
            	for (int i = 0; i < formula.getQuantidadeAtomos() - 
            			binario.length(); i++) {
                	complemento += "0";
                }//preenche com 0 até possuir 5 bits
                
            	binario = complemento + binario;
            	
            	for (int i = 0; i < formula.getQuantidadeAtomos(); i++) {//Verifica valor binário da linha e o seta como valor do átomo
            	
                	if (binario.charAt(i) == '0') {
                    		formula.setValorIndex(i, false);        
                	} else {
                    		formula.setValorIndex(i, true);
                	}
                	
            	}
            	
          		if (col < formula.getQuantidadeAtomos()) {//Coluna de resultado
          			return formula.getValorProposicao(formula.getProposicao(col));
          		}
          		
          		return formula.getValor();

          }
          
          public String getColumnName(int columnIndex) {//Pega nome da Coluna
          
          	if (columnIndex < formula.getQuantidadeAtomos()) {//Seta nome como o rótulo do átomo
          		return String.valueOf(formula.getProposicao(columnIndex));
          	}
          		
          	return "Resultado";

          }
          
		};
      
      tabelaVerdade.setModel(dataModel);
      
      pack();
      
    }
    //Métodos de Eventos
    public void button_E_Pressed() {
    
    	String texto = inputFormula.getText();
    	texto += " " + '\u2227' + " ";
    	inputFormula.setText(texto);

    }
    
    public void button_OU_Pressed(){

    	String texto = inputFormula.getText();
    	texto += " " + '\u2228' + " ";
    	inputFormula.setText(texto);

    }
    
    public void button_NEGACAO_Pressed(){
    
    	String texto = inputFormula.getText();
    	texto += " " + '\u223c' + " ";
    	inputFormula.setText(texto);

    }
    
    public void button_SE_ENTAO_Pressed(){

    	String texto = inputFormula.getText();
    	texto += " " + '\u2192' + " ";
    	inputFormula.setText(texto);

    }

    public void button_SSE_Pressed(){

    	String texto = inputFormula.getText();
    	texto += " " + '\u2194' + " ";
    	inputFormula.setText(texto);

    }

    public void button_GerarTabela_Pressed(){

    	String texto = inputFormula.getText();

    	try {
    	
 	   	Formula formulaTeste = new Formula(texto);
    		botaoDesenharArvore.setEnabled(true);
    		itemSalvar.setEnabled(true);
    		formula = formulaTeste;
   
    		setTable();

    	} catch (IllegalArgumentException e) {
    		JOptionPane.showMessageDialog(null,"A formula não é válida");
    	} catch (Exception e) {
    		System.out.println("Algum Erro");
    	}

    }
    
    public void button_DesenharArvore_Pressed() {
    	
    	formula.draw();
 
    }
    
    public void button_Abrir_Pressed(){
    		
    	JFileChooser chooser = new JFileChooser();
    		
    	chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    	
    	int retrival = chooser.showOpenDialog(this);
    	
    	if (retrival == JFileChooser.APPROVE_OPTION) {
    	
    	    File file = chooser.getSelectedFile();
    	    
    	    if (file.getName().endsWith(".flg")) {
    	
        		try (BufferedReader br = new BufferedReader(
        		        new FileReader(file))) {

        	        inputFormula.setText(br.readLine());
        	        
        	        button_GerarTabela_Pressed();
        	
        		} catch (IOException e) {
        		    e.printStackTrace();
        		}
    		
    		} else {
    		    JOptionPane.showMessageDialog(this, "O arquivo não é um .FLG", 
    		            "Arquivo inválido", JOptionPane.WARNING_MESSAGE);
    		}
    	
    	}
    	
    }
    
    public void button_Novo_Pressed(){
    	
    	inputFormula.setText("");
    	
    	tabelaVerdade.setModel(new DefaultTableModel());
    	
    }
    
    public void button_Salvar_Pressed() {
    	
    	JFileChooser save = new JFileChooser();
    	
    	int retrival = save.showSaveDialog(this);
   
		if (retrival == JFileChooser.APPROVE_OPTION) {
		
		    String fileName;
	
	        File file = save.getSelectedFile();
	        fileName = file.getPath();
	        
	        fileName += (fileName.endsWith(".flg")) ? "" : ".flg";
		
			try (BufferedWriter bw = new BufferedWriter(
						new FileWriter(fileName))) {
		
				String formula = inputFormula.getText();
						
				bw.write(formula);		
		
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}	
		
    }
    
    public void button_Sair_Pressed(){
    	
    	System.exit(0);

    }
    
    public void button_Desenvolvedores_Pressed() {
    
    	JOptionPane.showMessageDialog(null, "Anderson Gomes\nCaio Viktor\n" + 
    			"Jose Rodrigo\nJuan Jackson\nKarin Oliveira", "Desenvolvedores",
    			JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void button_Ajuda_Pressed() {
    
        String ajuda = "";

        try {
        
            BufferedReader leitor = new BufferedReader(
            		new FileReader("../notas-importantes/ajuda.txt"));
            		
            while (leitor.ready()) {
                ajuda += leitor.readLine() + "\n";
            }
            
        } catch (Exception e) {
        
            ajuda = "Ocorreu um erro interno e infelizmente " + 
            		"não podemos ajuda-lo";
        }
        
    	JOptionPane.showMessageDialog(null,ajuda,"Ajuda",JOptionPane.PLAIN_MESSAGE);

    }
    
    public void keyPressed(KeyEvent e) {     
		itemSalvar.setEnabled(false);
    }
    //Atalhos do teclado
    public void keyReleased(KeyEvent e) {
      
    	if (e.getKeyCode() >= KeyEvent.VK_1 && e.getKeyCode() <= KeyEvent.VK_5) {
    	
	    	String texto = inputFormula.getText();
	    	
	    	texto = texto.replace("1", " " + '\u2227' + " ");
	    	texto = texto.replace("2", " " + '\u2228' + " ");
	    	texto = texto.replace("3", " " + '\u223c' + " ");
	    	texto = texto.replace("4", " " + '\u2192' + " ");
	    	texto = texto.replace("5", " " + '\u2194' + " ");

	    	inputFormula.setText(texto);
 
    	}
    	
    }  
    
    public void keyTyped(KeyEvent e) {}
    
} // fim da classe TelaPrincipal
