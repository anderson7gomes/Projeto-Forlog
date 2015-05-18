import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.Dimension;

public class JPanelD extends JPanel {

	private No raiz;
	private int tamanho;
	private JPanelD panel;
	private JScrollPane scroll;
	
	public JPanelD(String formula, No raiz, int tamanho) {
	
		super();
		this.setBackground(Color.white);
		this.raiz = raiz;
		this.tamanho = tamanho;
		this.setPreferredSize(new Dimension(1000,1000));
	
		
	}
	private int setUpTree(No no,int limX,int y,int dist){
		if(no.isFolha()){
			int x = limX + dist + tamanho;
			no.setX(x);
			no.setY(y + tamanho);
			return x;
		}
		int xEs = setUpTree(no.getFilhoEsquerdo(),limX + dist + tamanho,y+dist+tamanho,dist);
		int x = xEs;
		if(no.getFilhoDireito() != null){
			int xDi = setUpTree(no.getFilhoDireito(),xEs,y+dist+tamanho,dist);
			x = (int)((xEs+xDi)/2);
		}
		no.setX(x);
		no.setY(y + tamanho);
		return x;
	}
	public void paint(Graphics g) {
		g.clearRect(0, 0, 1000, 1000);
		setUpTree(raiz,0,0,30);

        drawTree(raiz,g,30);
        // System.out.println(panel.getWidth());
        
        // pack();
	}
	
	private void drawTree(No no, Graphics g, int dist) {

		// if(no.isFolha())

		// g.setColor(Color.black);
		// g.drawOval(x, y, tamanho, tamanho);
		
		// String texto;
		// Object ob = no.getElemento();
		
		// if (ob instanceof Atomo) {
		    
		// 	Atomo ob1 = (Atomo) ob;
		// 	texto = String.valueOf(ob1.getRotulo());

		// } else {
		// 	texto = String.valueOf(ob);
		// }
		
		// g.drawString(texto, (x + (tamanho / 2)), (y + (tamanho / 2)));
		 
		// if (!no.isFolha()) {
		
		// 	if (no.getFilhoDireito() != null) {
			
		// 		int xEs = x - tamanho - dist;
		// 		int xDi = x + tamanho + dist;
		// 		int yEs = y + tamanho + dist;
		// 		int yDi = y + tamanho + dist;
				
		// 		g.drawLine(x + (tamanho) / 2, y + tamanho, xEs + (tamanho / 2), 
		// 		        yEs);
				        
		// 		drawTree(no.getFilhoEsquerdo(), g, xEs, yEs, dist);

		// 		g.drawLine(x + (tamanho) / 2, y + tamanho, xDi, yDi);
		// 		drawTree(no.getFilhoDireito(), g, xDi - (tamanho / 2), 
		// 		        yDi, dist);
				
		// 	} else {
			
		// 		g.drawLine(x + (tamanho) / 2, y + tamanho, x + (tamanho) / 2, 
		// 		        y + tamanho + dist);
		// 		drawTree(no.getFilhoEsquerdo(), g, x, y + tamanho + dist, dist);

		// 	}
		// }

		int x = no.getX();
		int y = no.getY();
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
			
				int xEs = no.getFilhoEsquerdo().getX();
				int xDi = no.getFilhoDireito().getX();
				int yEs = no.getFilhoEsquerdo().getY();
				int yDi = no.getFilhoDireito().getY();
				
				g.drawLine(x + (tamanho) / 2, y + tamanho, xEs + (tamanho / 2), 
				        yEs);
				        
				drawTree(no.getFilhoEsquerdo(), g,dist);

				g.drawLine(x + (tamanho) / 2, y + tamanho, xDi + tamanho/2, yDi);
				drawTree(no.getFilhoDireito(), g, dist);
				
			} else {
			
				g.drawLine(x + (tamanho) / 2, y + tamanho, x + (tamanho) / 2, 
				        no.getFilhoEsquerdo().getY());

				drawTree(no.getFilhoEsquerdo(), g, dist);

			}
		}
	}
} // fim da classe DrawTree
