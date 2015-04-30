public class Formula{
	private No arvore;
	private boolean valor;
	private int qtdAtomos;
	private Atomo atomos[];

	public Formula(String formulaRaw){
		String formula = formulaRaw.replace(" ","").toLowerCase();
		if(!isValida())
			throw new IllegalArgumentException("A formula não é válida:\n" + formula);
		qtdAtomos = 2; // TODO:calcular
		
		atomos = new Atomo[qtdAtomos];

		atomos[0] = new Atomo('p',false);//teste
		atomos[1] = new Atomo('q',true);//teste

		arvore = criarArvore(formula);
		valor = avaliarArvore();
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
				return;
			}
		throw new IllegalArgumentException("Átomo não existente");	
	}
	public boolean avaliarArvore(){
		valor = arvore.avaliar();
		return valor;
	}
	public static void main(String args[]){
		String formula;
		// formula = "((p"+'\u2227'+"q)"+'\u2228'+"("+'\u223c'+"p))";
		formula = "("+'\u223c' +"p)";
		// formula = "(p"+'\u2192'+"q)";
		// formula = "(p"+'\u2192'+"p)";
		// formula = "(p "+'\u2194'+" q)";
		Formula f = new Formula(formula);
		System.out.println("p="+f.getValorProposicao('p'));
		System.out.println(formula + "\n" + f.getValor());
		f.setValorPreposicao('p',true);
		System.out.println("p="+f.getValorProposicao('p'));
		f.avaliarArvore();
		System.out.println(formula + "\n" + f.getValor());
	}
}
