import java.util.*;
public class Formula{
	private No arvore;
	private boolean valor;
	private int qtdAtomos;
	private Atomo atomos[];
	private String formulaPassada;

	public Formula(String formulaRaw){
		formulaPassada = formulaRaw;
		String formula = formulaRaw.replace(" ","").toLowerCase();
		if(!isValida(formula))
			throw new IllegalArgumentException("A formula não é válida:\n" + formula);

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
				raiz = new No<ConectivoUnario>(c2, criarArvore( formula.substring(atual + 1) ) );
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
				raiz = new No<ConectivoBinario>(conectivo,esquerda,direita);
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

	private boolean isValida(String formula){
		if(formula.charAt(0) != '(' || formula.charAt(formula.length()-1) !=')')
			return false;
		
		int abreParentese = 0 , fechaParentese = 0;
		Set<Character> proposicoes = new HashSet<>();

		for(int i = 0 ; i < formula.length();i++){
			char atual = formula.charAt(i);
			if(atual != ')' && atual != '(' && (atual < '\u0061' && atual > '\u007A') && atual != '\u2227' && atual != '\u2228' && atual != '\u2192' && atual != '\u2194' && atual != '\u223c')//não for um character válido 
				return false;


			if(atual == '('){
				abreParentese++;
				if(formula.charAt(i+1) == ')')
					return false;
			}
			
			if(atual == ')')
				fechaParentese--;
			
			if(fechaParentese > abreParentese)
				return false;
			
			if (atual >= '\u0061' && atual <= '\u007A'){//É proposição
				proposicoes.add(atual);
				if(proposicoes.size() > 5)
					return false;
				char proximo = formula.charAt(i+1);
				if (proximo >= '\u0061' && proximo <= '\u007A')//É proposição
					return false;
				if(proximo == '(')
					return false;
			}
			if(atual == '\u2227' || atual == '\u2228' || atual == '\u2192' || atual == '\u2194'){//É operador binário
				char anterior = formula.charAt(i-1);
				char proximo = formula.charAt(i+1);
				if(proximo == ')' || proximo == '\u2227' || proximo == '\u2228' || proximo == '\u2192' || proximo == '\u2194' || proximo == '\u223c')//Operador sem operando à direita
					return false;
				if(anterior == '(' || anterior == '\u2227' || anterior == '\u2228' || anterior == '\u2192' || anterior == '\u2194' || anterior == '\u223c')//Operador sem operando à esquerda
					return false;


			}
			if(atual == '\u223c'){//É operador Unário (negação)
				if(formula.charAt(i-1) != '(')
					return false;
				char proximo = formula.charAt(i+1);
				if(proximo == ')' || proximo == '\u2227' || proximo == '\u2228' || proximo == '\u2192' || proximo == '\u2194' || proximo == '\u223c')//Operador sem operando
					return false;

			}
			
		}


		qtdAtomos = proposicoes.size();
		atomos = new Atomo[qtdAtomos];
		int i = 0;
		for(Character rotulo : proposicoes){
			atomos[i] = new Atomo(rotulo);
			i++;
		}
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

	public void show(){
		System.out.println(formulaPassada);
		for(int i = 0 ; i < qtdAtomos; i++)
			System.out.println(atomos[i]);
		System.out.println("Valor Formula: "+valor+"\n-----------------------------------------------------------");
	}

	public static void main(String args[]){
		String formula;
		formula = "((p"+'\u2227'+"q)"+'\u2228'+"("+'\u223c'+"p))";
		// formula = "("+'\u223c' +"p)";
		// formula = "(p"+'\u2192'+"q)";
		// formula = "(p"+'\u2192'+"p)";
		// formula = "(p "+'\u2194'+" q)";
		// formula = "("+'\u223c'+"("+'\u223c'+"p))";
		// formula = "((p)"+'\u2227'+"q)";
		Formula f = new Formula(formula);
		f.show();
		f.setValorPreposicao('p',true);
		f.avaliarArvore();
		f.show();
	}
}
