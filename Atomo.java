public class Atomo{
	private boolean valor;
	private char rotulo;
	public Atomo(char rotulo,boolean valor){
		this.valor = valor;
		this.rotulo = rotulo;
	}
	public Atomo(char rotulo ){
		this.rotulo = rotulo;
		valor = false;
	}
	public boolean getValor(){
		return valor;
	}
	public void setValor(boolean valor){
		this.valor = valor;
	}
	public char getRotulo(){
		return rotulo;
	}
	public void setRotulo(char rotulo){
		this.rotulo = rotulo;
	}
	public boolean equals(Object e){
		if(e == this)
			return true;
		if(!(e instanceof Atomo))
			return false;
		Atomo e1 = (Atomo) e;
		if(e1.getRotulo() == this.getRotulo())
			return true;
		return false;
	}
	
	/* um átomo válido é definido como sendo um 
	 * caractere minúsculo da tabela ASCII */
	public static boolean isAtomo(char character) {
	
		if (character < '\u0061' || character > '\u007A') {
			return false;	
		}
		
		return true;
	
	} 
	
	public String toString(){
		return "Rotulo: " + rotulo + "\tValor: " + valor;
	}
	
}
