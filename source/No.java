/**
 * Classe que implementa uma estrutura de dados do tipo 
 * arvore para armazenar Conectivos e Atomos de forma a 
 * construir uma arvore sintatica
 * @see Atomo
 * @see Conectivo
 */

public class No<T> {

	private T elemento;
	private No esquerdo, direito;
	private boolean isFolha;
	private int x, y;
	
	/**
	 * Recupera a coordenada do eixo x. Essa coordenada e usada 
	 * internamente pela aplicacao com o intuito de desenhar a arvore
	 * @return coordenada x do desenho da arvore
	 */
	
	public int getX() {
		return x;
	}
	
	/**
	 * Modifica a coordenada do eixo x, essa coordenada e usada 
	 * internamente pela aplicacao com o intuito de desenhar a arvore
	 * @param x coordenada x do desenho da arvore
	 */
	
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Recupera a coordenada do eixo y. Essa coordenada e usada 
	 * internamente pela aplicacao com o intuito de desenhar a arvore
	 * @return coordenada y do desenho da arvore
	 */
	
	public int getY() {
		return y;
	}
	
	/**
	 * Modifica a coordenada do eixo y, essa coordenada e usada 
	 * internamente pela aplicacao com o intuito de desenhar a arvore
	 * @param y coordenada x do desenho da arvore
	 */
	
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Constroi um No com dois filhos, 
	 * esquerdo e direito, respectivamente
	 * @param elemento no que sera o no pai
	 * @param esquerdo no que sera o filho esquerdo
	 * @param direito no que sera o filho direito
	 */
	
	public No(T elemento, No esquerdo, No direito) {
	
		if (!(elemento instanceof Atomo) && !(elemento instanceof Conectivo)) {

			throw new IllegalArgumentException("É necessário que o elemento " + 
			        "seja ou um Atomo ou Conectivo");

		}
			
		if ((elemento instanceof Atomo) && 
		        (direito != null || esquerdo != null)) {
		        
			throw new IllegalArgumentException("Um nó folha não " + 
			        "pode receber filhos");

		}
			        
		this.elemento = elemento;
		this.esquerdo = esquerdo;
		this.direito = direito;
		
		if (elemento instanceof Atomo) {
			isFolha = true;
		} else {
			isFolha = false;
		}	

	}
	
	/**
	 * Constroi um No com um filho esquerdo
	 * @param elemento no que sera o no pai
	 * @param esquerdo no que sera o filho esquerdo
	 */
	
	public No(T elemento, No esquerdo) {
	    this(elemento, esquerdo, null);
	} 
	
	/**
	 * Constroi um No sem filhos
	 * @param elemento no isolado
	 */
	
	public No(T elemento) {
		this(elemento, null, null);
	}
	
	/**
	 * Constroi um no vazio
	 * @deprecated
	 */
	
	public No() {
		this(null);
	}
	
	/**
	 * Recupera o elemento armazenado no noh pai
	 * @return Atomo ou Conectivo armazenado no no
	 */
	
	public T getElemento() {
		return elemento;
	}
	
	/**
	 * Modifica o elemento armazenado no noh pai
	 * @param elemento Atomo ou Conectivo a ser armazenado no noh
	 */
	
	public void setElemento(T elemento) {
		this.elemento = elemento;
	}
	
	/**
	 * Recupera o No que eh o filho direito do noh principal(pai)
	 * @return No indicado pelo filho direito do noh pai
	 */
	
	public No getFilhoDireito() {
		return direito;
	}
	
	/**
	 * Recupera o No que eh o filho esquerdo do noh principal(pai)
	 * @return No indicado pelo filho esquerdo do noh pai
	 */
	
	public No getFilhoEsquerdo() {
		return esquerdo;
	}
	
	/**
	 * Troca o noh indicado pelo filho direito do noh pai
	 * @param direito novo noh a ser apontado pelo noh pai como filho direito
	 */
	
	public void setFilhoDireito(No direito) {
		this.direito = direito;
	}
	
	/**
	 * Troca o noh indicado pelo filho esquerdo do noh pai
	 * @param esquerdo novo noh a ser apontado pelo noh pai como filho esquerdo
	 */
	
	public void setFilhoEsquerdo(No esquerdo) {
		this.esquerdo = esquerdo;
	}
	
	/**
	 * Metodo que informa se um No eh uma folha(nao possui filhos)
	 * @return true, se o No nao possui filhos ou false caso contrario
	 */
	
	public boolean isFolha() {
		return isFolha;
	}
	
	/**
	 * Metodo que aplica os Conectivos nos valores dos Atomos 
	 * armazenados nos Nos da arvore atraves de chamadas recursivas
	 * @return valor booleano resultado a expressao 
	 *         armazenada na arvore sintatica
	 */
	
	public boolean avaliar() {
	
		if (isFolha()) {
		
			Atomo el = (Atomo) elemento;
			return el.getValor();

		}
		
		if (elemento instanceof ConectivoBinario) {
		
			ConectivoBinario conectivo = (ConectivoBinario) elemento;
			return conectivo.aplicar(esquerdo.avaliar(),direito.avaliar());

		}
		
		if (elemento instanceof ConectivoUnario) {
		
			ConectivoUnario conectivo = (ConectivoUnario) elemento;
			return conectivo.aplicar(esquerdo.avaliar());

		}

		return false;

	}

} // fim da classe genérica No
