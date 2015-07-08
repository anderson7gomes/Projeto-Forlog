/**
 * Classe que representa um atomo, com um caractere identificando o 
 * simbolo(ex: p, q) e um valor booleano identificando o valor logico do atomo
 */

public class Atomo {

	private boolean valor;
	private char rotulo;
	
	/**
	 * Constroi um Atomo com um rotulo e um valor booleano especificos
	 * @param rotulo rotulo que representa o atomo visualmente
	 * @param valor valor booleano do atomo em uma expressao
	 */
	
	public Atomo(char rotulo, boolean valor) {
	
		this.valor = valor;
		this.rotulo = rotulo;

	}
	
	/**
	 * Constroi um Atomo com um rotulo especifico e valor logico default(false)
	 * @param rotulo rotulo que representa o atomo visualmente
	 */
	
	public Atomo(char rotulo ) {
	
		this.rotulo = rotulo;
		valor = false;

	}
	
	/**
	 * Recupera o valor booleano do atomo
	 * @return valor logico do atomo
	 */
	
	public boolean getValor() {
		return valor;
	}
	
	/**
	 * Atualiza o valor booleano do atomo
	 * @param valor valor logico do atomo
	 */
	
	public void setValor(boolean valor) {
		this.valor = valor;
	}
	
	/**
	 * Recupera o valor do rotulo do atomo
	 * @return caractere que representa o atomo visualmente
	 */
	
	public char getRotulo() {
		return rotulo;
	}
	
	/**
	 * Atualiza o valor do rotulo do atomo
	 * @param rotulo caractere que representa o atomo visualmente
	 */
	
	public void setRotulo(char rotulo) {
		this.rotulo = rotulo;
	}
	
	/**
	 * Compara dois Atomos e retorna true caso os rotulos sejam iguais
	 * @param e Atomo a ser comparado
	 * @return true, se os rotulos forem iguais e false caso contrario.
	 */
	
	public boolean equals(Object e) {
	
		if (e == this) {
		    return true;
		}
			
		if(!(e instanceof Atomo)) {
			return false;
		}
			
		Atomo e1 = (Atomo) e;
		if (e1.getRotulo() == this.getRotulo()) {
			return true;
		}
			
		return false;
		
	}
	
	/** 
	 * Valida um rotulo de atomo. Um rotulo de atomo valido e 
	 * definido como sendo um caractere minusculo da tabela ASCII 
	 * @param character caractere a ser verificado
	 * @return true, se o caractere representar um atomo 
	 *         valido(a-z) ou false caso contrario
	 */
	 
	public static boolean isAtomo(char character) {
	
		if (character < '\u0061' || character > '\u007A') {
			return false;	
		}
		
		return true;
	
	} 
	
	/**
	 *Retorna uma String com a representacao do rotulo e valor logico do Atomo
	 *@return String representando o rotulo e o valor logico do atomo
	 */
	
	public String toString() {
		return "Rotulo: " + rotulo + "\tValor: " + valor;
	}
	
} // fim da classe Atomo
