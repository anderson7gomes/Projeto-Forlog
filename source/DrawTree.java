import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

public class DrawTree extends JFrame {

	private JPanelD panel;
	private JScrollPane scroll;
	
	public DrawTree(String formula, No raiz, int tamanho) {
	
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);  
		this.setBackground(Color.white);
		this.setTitle(formula);
		this.setSize(700,700);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		panel = new JPanelD(formula,raiz,tamanho);
		panel.setAutoscrolls(true);


		scroll = new JScrollPane(panel);  
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBackground(Color.white);
		setContentPane(scroll);


		this.setVisible(true);
		
	}
} // fim da classe DrawTree
