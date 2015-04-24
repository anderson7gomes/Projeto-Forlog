public class No<T>{
	private T elemento;
	private No esquerdo,direito;
	private boolean isFolha;
	public No(T elemento,No direito , No esquerdo){
		if(!(elemento instanceof Atomo) || !(elemento instanceof Conectivo))
			throw new IllegalArgumentException("É necessário que o elemento seja ou um Atomo ou Conectivo");
		if((elemento instanceof Atomo) && (direito != null || esquerdo != null))
			throw new IllegalArgumentException("Um nó folha não pode recber filhos");
		this.elemento = elemento;
		this.esquerdo = esquerdo;
		this.direito = direito;
		if(elemento instanceof Atomo)
			isFolha = true;
		else
			isFolha = false;
	}
	public No(T elemento){
		this(elemento,null,null);
	}
	public No(){
		this(null);
	}
	public T getElemento(){
		return elemento;
	}
	public void setElemento(T elemento){
		this.elemento = elemento;
	}
	public No getFilhoDireito(){
		return direito;
	}
	public No getFilhoEsquerdo(){
		return esquerdo;
	}
	public void setFilhoDireito(No direito){
		this.direito = direito;
	}
	public void setFilhoEsquerdo(No esquerdo){
		this.esquerdo = esquerdo;
	}
	public boolean ehUmaFolha(){
		return isFolha;
	}
	public boolean avaliar(){
		if(ehUmaFolha()){
			Atomo el = (Atomo) elemento;
			return el.getValor();
		}
		if(elemento instanceof ConectivoBinario){
			ConectivoBinario conectivo = (ConectivoBinario) elemento;
			return conectivo.aplicar(esquerdo.avaliar(),direito.avaliar());
		}
		if(elemento instanceof ConectivoUnario){
			ConectivoUnario conectivo = (ConectivoUnario) elemento;
			return conectivo.aplicar(esquerdo.avaliar());
		}
		return false;
	}
}
