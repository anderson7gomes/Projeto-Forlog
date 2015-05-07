public class Teste {

    public static void main(String[] args) {
    
        String formulaString;
    
        //formulaString = String.format("((%c %c %c) %c (%c %c %c))", 
                //'p', '\u2192', 'q', '\u2228', 'a', '\u2227', 'b');
        
        formulaString = String.format("(((%c %c %c) %c %c) %c %c)", 
                'p', '\u2227', 'q', '\u2192', 'r', '\u2194', 's');
        
        Formula formula = new Formula(formulaString);
        
        formula.setValorProposicao('p', false);
        formula.setValorProposicao('q', false);
        formula.setValorProposicao('r', false);
        formula.setValorProposicao('s', false);
        
        formula.show();
        
    }

} // fim da classe Teste

// (((p .e. q) -> r) <-> s)
