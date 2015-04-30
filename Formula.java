public class Formula{
	private No arvore;
	private boolean valor;
	private int qtdAtomos;
	private Atomo atomos[];

	public Formula(String formulaRaw){
		String formula = formulaRaw.replace(" ","");

		//TODO: ler a string e formar a arvore
		valor = false; // TODO:calcular
		qtdAtomos = 2; // TODO:calcular
		atomos = new Atomo[qtdAtomos];
		atomos[0] = new Atomo('p',true);
		atomos[1] = new Atomo('q',true);
		arvore = criarArvore(formula);
	}
	private No criarArvore(String formulaComParensetes){
		String formula = formulaComParensetes;
		if(formulaComParensetes.charAt(0) == '(')
			formula = formulaComParensetes.substring(1,formulaComParensetes.length()-1);
		if(formula.length() == 1){
			Atomo a1 = recuperarAtomo(formula.charAt(0));
			No<Atomo> atomo = new No<>(a1);
			return atomo;
		}

		// if(formula.length() == 2)
		// 	if(formula.charAt(0) == '\u223c'){ // NEGACAO
		// 		ConectivoUnario c2 = new ConectivoUnario(TipoConectivo.NEGACAO);
		// 		Atomo a1 = recuperarAtomo(formula.charAt(1));
		// 		No atomo = new No(a1);
		// 		return new No(c2, atomo);
		// 	}
		// if(formula.length() == 3){
		// 	ConectivoBinario conectivo = null;
		// 	switch(formula.charAt(1)){
		// 		case '\u2227'://E
		// 			conectivo = new ConectivoBinario(TipoConectivo.E);
		// 		break;
		// 		case '\u2228'://OU
		// 			conectivo = new ConectivoBinario(TipoConectivo.OU);
		// 		break;
		// 		case '\u2192'://SE
		// 			conectivo = new ConectivoBinario(TipoConectivo.SE_ENTAO);
		// 		break;
		// 		case '\u2194'://SSE
		// 			conectivo = new ConectivoBinario(TipoConectivo.SSE);
		// 		break;
		// 		default:
		// 			conectivo = new ConectivoBinario(TipoConectivo.OU);
		// 		break;
		// 	}
		// 	Atomo a1 = recuperarAtomo(formula.charAt(0));
		// 	Atomo a2 = recuperarAtomo(formula.charAt(2));
			
		// 	No noA1 = new No(a1);
  //       	No noA2 = new No(a2);

  //       	return new No(conectivo,noA1,noA2);
		// }
		int parenteses = 0, atual = -1;
		No raiz = null;
		do{
			atual++;
			if(formula.charAt(atual) == '(')
				parenteses++;
			if(formula.charAt(atual) == ')')
				parenteses--;
		}while(parenteses > 0);
		switch(formula.charAt(atual)){
			case '\u223c'://NEGACAO
				ConectivoUnario c2 = new ConectivoUnario(TipoConectivo.NEGACAO);
				raiz = new No(c2, criarArvore( formula.substring(atual + 1) ) );
			break;
			default://Outros casos (Binário)
				atual++;
				ConectivoBinario conectivo = null;
				switch(formula.charAt(atual)){
					case '\u2227'://E
						conectivo = new ConectivoBinario(TipoConectivo.E);
					break;
					case '\u2228'://OU
						conectivo = new ConectivoBinario(TipoConectivo.OU);
					break;
					case '\u2192'://SE
						conectivo = new ConectivoBinario(TipoConectivo.SE_ENTAO);
					break;
					case '\u2194'://SSE
						conectivo = new ConectivoBinario(TipoConectivo.SSE);
					break;
					default:
						conectivo = new ConectivoBinario(TipoConectivo.OU);
					break;
				}
				No esquerda = criarArvore(formula.substring(0,atual));
				No direita = criarArvore(formula.substring(atual + 1 ));
				raiz = new No(conectivo,esquerda,direita);
			break;
		}
		return raiz;
	}

	private Atomo recuperarAtomo(char rotulo){
		for(int i = 0 ; i < qtdAtomos ; i++)
			if(atomos[i].getRotulo() == rotulo)
				return atomos[i];
		return null;
	}

	private boolean isValida(){
		//TODO:Verificação da validade da formula, criar e contar os átomos
		return true;
	}
	public boolean getValor(){
		return valor;
	}
	public int quantidadeAtomos(){
		return qtdAtomos;
	}
	public boolean getValorProposicao(char rotulo){
		for(int i = 0 ; i < qtdAtomos ; i++)
			if(atomos[i].getRotulo() == rotulo)
				return atomos[i].getValor();
		throw new IllegalArgumentException("Átomo não existente");
	}
	//TODO: setValorPosicao e etc
	public void setValorPreposicao(char rotulo, boolean valor){
		for(int i = 0 ; i < qtdAtomos ; i++)
			if(atomos[i].getRotulo() == rotulo){
				atomos[i].setValor(valor);
				break;
			}
		throw new IllegalArgumentException("Átomo não existente");	
	}
	public boolean avaliarArvore(){
		valor = arvore.avaliar();
		return valor;
	}
	public static void main(String args[]){
		Formula f = new Formula("p"+'\u2227'+"q");
		System.out.println(f.getValor());
	}
}
