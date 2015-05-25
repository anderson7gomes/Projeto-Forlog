import java.util.*;

public class Formula {

	private String formulaRaw;
	private No arvore;
	private boolean valor;
	private int qtdAtomos;
	private Atomo atomos[];
	private String formulaPassada;

	public Formula(String formulaRaw) {
	
		this.formulaRaw = formulaRaw;
		formulaPassada = formulaRaw;
		String formula = formulaRaw.replace(" ","").toLowerCase();
		
		if (!isValida(formula)) {
			throw new IllegalArgumentException("A formula não é válida:\n" + 
			        formula);
        }
        
		arvore = criarArvore(formula);
		valor = avaliarArvore();

	}

	private No criarArvore(String formulaComParenteses) {
	
		String formula = formulaComParenteses;
		
		if (formulaComParenteses.charAt(0) == '(') {
			formula = formulaComParenteses.substring(1, 
			        formulaComParenteses.length() - 1);
		}
			
		if(formula.length() == 1) {
		
			Atomo a1 = recuperarAtomo(formula.charAt(0));
			No<Atomo> atomo = new No<>(a1);
			return atomo;

		}
		
		int parenteses = 0, atual = -1;
		No raiz = null;
		
		do {
		
			atual++;
			
			if (formula.charAt(atual) == '(') {
				parenteses++;
			}
				
			if (formula.charAt(atual) == ')') {
				parenteses--;
			}	

		} while (parenteses > 0);
		
		switch (formula.charAt(atual)) {
		
			case '\u223c': //NEGACAO
			
			    ConectivoUnario c2 = new ConectivoUnario(TipoConectivo.NEGACAO);
				raiz = new No<ConectivoUnario>(c2, 
				        criarArvore( formula.substring(atual + 1) ) );
			    break;
			    
			default: //Outros casos (Binário)
			
				atual++;
				
				if (atual >= formula.length()) {
					return criarArvore(formula.substring(0, formula.length()));
				}
					
				ConectivoBinario conectivo = null;
				
				switch(formula.charAt(atual)) {
				
					case '\u2227': //E
					
						conectivo = new ConectivoBinario(TipoConectivo.E);
					    break;

					case '\u2228': //OU
					
						conectivo = new ConectivoBinario(TipoConectivo.OU);
					    break;
					    
					case '\u2192': //SE
					
						conectivo = new ConectivoBinario(TipoConectivo.SE_ENTAO);
					    break;
					    
					case '\u2194': //SSE
					
						conectivo = new ConectivoBinario(TipoConectivo.SSE);
					    break;
					    
					default:
					
						conectivo = new ConectivoBinario(TipoConectivo.OU);
					    break;
				}
				
				No esquerda = criarArvore(formula.substring(0, atual));
				No direita = criarArvore(formula.substring(atual + 1));
				raiz = new No<ConectivoBinario>(conectivo, esquerda, direita);
			    break;
		}
		
		return raiz;
		
	}

	private Atomo recuperarAtomo(char rotulo) {
	
		for (int i = 0; i < qtdAtomos; i++) {
		    if (atomos[i].getRotulo() == rotulo) {
				return atomos[i];
			}	
		}		
				
		return null;

	}

	private boolean isValida(String formula) {
	
		if (formula.charAt(0) != '(' || 
		        formula.charAt(formula.length()-1) != ')') {
			return false;
		}	
		
		int abreParentese = 0, fechaParentese = 0;
		Set<Character> proposicoes = new HashSet<>();

		for (int i = 0; i < formula.length(); i++) {
		
			char atual = formula.charAt(i);
			
			if (atual != ')' && atual != '(' && 
			        (!Atomo.isAtomo(atual)) && 
			        (!Conectivo.isConectivoValido(atual))) { // não for um character válido 
				return false;
			}	

			if (atual == '(') {
			
				abreParentese++;
				
				if (formula.charAt(i + 1) == ')') {
					return false;
				}	

			}
			
			if (atual == ')') {
				fechaParentese++;
			}	
			
			if (fechaParentese > abreParentese) {
				return false;
			}	
			
			if (Atomo.isAtomo(atual)) { //É proposição
			
				proposicoes.add(atual); 
				
				if (proposicoes.size() > 5) {
					return false;
				}
					
				char proximo = formula.charAt(i+1);
				
				if (Atomo.isAtomo(proximo)) { //É proposição
					return false;
				}
					
				if (proximo == '(') {
					return false;
				}	
				
			}
			
			if (Conectivo.isConectivoBinarioValido(atual)) { // É operador binário
			        
				char anterior = formula.charAt(i - 1);
				char proximo = formula.charAt(i + 1);
				
				if (proximo == ')' || Conectivo.isConectivoValido(proximo)) { //Operador sem operando à direita
					return false;
				}
					
				if (anterior == '(' || Conectivo.isConectivoValido(anterior)) { //Operador sem operando à esquerda
					return false;
				}	

			}
			
			if (Conectivo.isConectivoUnarioValido(atual)) { //É operador Unário (negação)
			
				if (formula.charAt(i - 1) != '(') {
					return false;
				}
					
				char proximo = formula.charAt(i + 1);
				
				if (proximo == ')' || Conectivo.isConectivoValido(proximo)) { //Operador sem operando
					return false;
				}	

			}
			
		}

		if (abreParentese != fechaParentese) {
			return false;
		}
			
		qtdAtomos = proposicoes.size();
		atomos = new Atomo[qtdAtomos];

		int i = 0;
		
		for (Character rotulo : proposicoes) {
		
			atomos[i] = new Atomo(rotulo);
			i++;

		}

		return true;

	}

	public boolean getValor() {
	    return valor;
	}
	
	public int getQuantidadeAtomos() {
		return qtdAtomos;
	}

	public char getProposicao(int i) {
	
		if (i >= 0 && i < qtdAtomos) {
			return atomos[i].getRotulo();
		}	
		return ' ';

	}
	
	public boolean getValorProposicao(char rotulo) {
	
		for (int i = 0; i < qtdAtomos; i++) {
		
			if (atomos[i].getRotulo() == rotulo) {
				return atomos[i].getValor();
            }
            
		}
				
		throw new IllegalArgumentException("Átomo não existente");

	}

	public void setValorProposicao(char rotulo, boolean valor) {
	
		for (int i = 0; i < qtdAtomos; i++) {

			if (atomos[i].getRotulo() == rotulo) {
			
				if (atomos[i].getValor() != valor) {
				
					atomos[i].setValor(valor);
					avaliarArvore();

				}
				
				return;
				
			}

		}
			
		throw new IllegalArgumentException("Átomo não existente");	

	}
	
	public void setValorIndex(int i, boolean valor) {
	
		if (i >= 0 && i < qtdAtomos) {
		
			if (atomos[i].getValor() != valor) {	
				atomos[i].setValor(valor);
				avaliarArvore();
			}

		}

	}
	
	public boolean avaliarArvore() {
	
		valor = arvore.avaliar();
		return valor;

	}

	public void show() {
	
		System.out.println(formulaPassada);
		
		for (int i = 0; i < qtdAtomos; i++) {
			System.out.println(atomos[i]);
		}
			
		System.out.println("Valor Formula: " + valor + 
		        "\n-------------------------------" + 
		        "----------------------------");

	}
	
	public String getFormula() {
		return formulaRaw;
	}
	
	public void draw() {
		DrawTree desenho = new DrawTree(this.getFormula(),this.arvore,30);
	}
	
} // fim da classe Formula
