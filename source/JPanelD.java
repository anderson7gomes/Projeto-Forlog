import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.Dimension;

public class JPanelD extends JPanel {

	private No raiz;
	private int tamanho;
	private JPanelD panel;
	private JScrollPane scroll;
	private int lim;
	
	public JPanelD(String formula, No raiz, int tamanho) {
	
		super();
		
		this.setBackground(Color.white);
		this.raiz = raiz;
		this.tamanho = tamanho;
		this.lim = setUpTree(raiz,0,0,30) + tamanho + 30;
		this.setPreferredSize(new Dimension(lim,lim));
		
	}
	
	private int setUpTree(No no, int limX, int y, int dist) { //Descobre o posicionamento dos nós
	
		y += tamanho;//abaixa o nó pelo tamanho de seu pai
		
		if (no.isFolha()) {
		
			int x = limX + dist + tamanho;//sua posição horizontal recebe o limite mínimo de x somada a distância mínima e seu tamanho
			
			no.setX(x);
			no.setY(y);
			
			return x;

		}
		
		int xEs = setUpTree(no.getFilhoEsquerdo(),limX ,y + dist,dist);//Caucula a posição de seu filho esquerdo
		int x = xEs;
		int lim = xEs;//impõe que todos os nós que serão calculados devarão vir depois de seu filho esquerdo
		
		if (no.getFilhoDireito() != null) {
		
			int xDi = setUpTree(no.getFilhoDireito(), xEs, y + dist, dist);//Caucula a posição de seu filho direito

			x = (int) ((xEs + xDi) / 2);//Coordenada X do pai recebe a média das dos filhos
			lim = xDi;//impõe que todos os nós que serão calculados devarão vir depois de seu filho direito

		}
		
		no.setX(x);
		no.setY(y);
		
		return lim;//Retorna o limite na horizontal para os próximos nós

	}
	
	public void paint(Graphics g) {

		g.clearRect(0, 0, lim, lim);//Limpa área de desenho
        drawTree(raiz, g, 30);//redesenha

	}
	
	private void drawTree(No no, Graphics g, int dist) {
	
		int x = no.getX();
		int y = no.getY();
		
		g.setColor(Color.black);
		g.drawOval(x, y, tamanho, tamanho);
		
		String texto;
		Object ob = no.getElemento();
		
		if (ob instanceof Atomo) {//Consegue os tótulos dos nós
		    
			Atomo ob1 = (Atomo) ob;
			texto = String.valueOf(ob1.getRotulo());

		} else {
			texto = String.valueOf(ob);
		}
		
		g.drawString(texto, (x + (tamanho / 2)), (y + (tamanho / 2))); //Desenha o rótulo no centro do nó


		if (!no.isFolha()) {
		
			if (no.getFilhoDireito() != null) {//Verifica se é binário ou unário
			
				int xEs = no.getFilhoEsquerdo().getX();
				int xDi = no.getFilhoDireito().getX();
				int yEs = no.getFilhoEsquerdo().getY();
				int yDi = no.getFilhoDireito().getY();
				
				g.drawLine(x + (tamanho) / 2, y + tamanho, xEs + tamanho / 2, yEs);//Desenha linha do pai até o filho esquerdo
				drawTree(no.getFilhoEsquerdo(), g,dist);//Desenha filho esquerdo


				g.drawLine(x + (tamanho) / 2, y + tamanho, xDi + tamanho / 2, yDi);//Desenha linha do pai até o filho direito
				drawTree(no.getFilhoDireito(), g, dist);//Desenha filho direito
				
			} else {//Operação unária
			
				g.drawLine(x + (tamanho) / 2, y + tamanho, 
						no.getFilhoEsquerdo().getX() + tamanho / 2, 
				        no.getFilhoEsquerdo().getY());//Desenha uma linha reta

				drawTree(no.getFilhoEsquerdo(), g, dist);//Desenha filho

			}
		}
	}
}
