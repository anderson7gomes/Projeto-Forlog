import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

public class DrawTree extends JFrame {

	private No raiz;
	private int tamanho;
	
	public DrawTree(String formula, No raiz, int tamanho) {
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		this.setBackground(Color.white);
		this.setTitle(formula);
		this.setSize(700,700);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.raiz = raiz;
		this.tamanho = tamanho;
		
		/*
		JScrollPane scrollPanel = new JScrollPane();
		
		JPanel panel = new JPanel();
		
	    Graphics2D g = (Graphics2D) panel.getGraphics();  
		panel.paint(arvore);
		scrollPanel.add(panel);
		this.add(scrollPanel);
		*/
		
		this.setVisible(true);
		
	}
	
	public void paint(Graphics g) {
        drawTree(raiz,g,350 - 15,0,30);
	}
	
	private void drawTree(No no, Graphics g, int x, int y, int dist) {

		// if(no.isFolha())

		g.setColor(Color.black);
		g.drawOval(x, y, tamanho, tamanho);
		
		String texto;
		Object ob = no.getElemento();
		
		if (ob instanceof Atomo) {
		    
			Atomo ob1 = (Atomo) ob;
			texto = String.valueOf(ob1.getRotulo());

		} else {
			texto = String.valueOf(ob);
		}
		
		g.drawString(texto, (x + (tamanho / 2)), (y + (tamanho / 2)));
		 
		if (!no.isFolha()) {
		
			if (no.getFilhoDireito() != null) {
			
				int xEs = x - tamanho - dist;
				int xDi = x + tamanho + dist;
				int yEs = y + tamanho + dist;
				int yDi = y + tamanho + dist;
				
				g.drawLine(x + (tamanho) / 2, y + tamanho, xEs + (tamanho / 2), 
				        yEs);
				        
				drawTree(no.getFilhoEsquerdo(), g, xEs, yEs, dist);

				g.drawLine(x + (tamanho) / 2, y + tamanho, xDi, yDi);
				drawTree(no.getFilhoDireito(), g, xDi - (tamanho / 2), 
				        yDi, dist);
				
			} else {
			
				g.drawLine(x + (tamanho) / 2, y + tamanho, x + (tamanho) / 2, 
				        y + tamanho + dist);
				drawTree(no.getFilhoEsquerdo(), g, x, y + tamanho + dist, dist);

			}
		}
	}
} // fim da classe DrawTree
