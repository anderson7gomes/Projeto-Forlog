public class Atomo{
	private boolean valor;
	public Atomo(boolean valor){
		this.valor = valor;
	}
	public Atomo(){
		this(false);
	}
	public boolean getValor(){
		return valor;
	}
	public void setValor(boolean valor){
		this.valor = valor;
	}
}