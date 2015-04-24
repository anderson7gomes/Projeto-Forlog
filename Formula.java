public class Formula{
	private No arvore;
	private boolean valor;
	private int qtdAtomos;
	private Atomo atomos[];

	public Formula(String formula){
		//TODO: ler a string e formar a arvore
		valor = false; // TODO:calcular
		qtdAtomos = 0; // TODO:calcular
		atomos = new Atomo[qtdAtomos];
	}
	public boolean getValor(){
		return valor;
	}
	public int quantidadeAtomos(){
		return qtdAtomos;
	}
	public boolean getValorProposicao(int indice){
		if(indice > quantidadeAtomos())
			throw new IllegalArgumentException("Posição não existente");
		return atomos[indice].getValor();
	}
	//TODO: setValorPosicao e etc
}