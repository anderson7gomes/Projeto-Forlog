public class Teste {

    public static void main(String[] args) {
    
        Atomo p = new Atomo('p');
        Atomo q = new Atomo('q');
        
        No<Atomo> noP = new No<Atomo>(p);
        No<Atomo> noQ = new No<Atomo>(q);
        
        Conectivo c = new ConectivoBinario(TipoConectivo.OU);
    
        No<Conectivo> no = new No<Conectivo>(c, noP, noQ);
        
        System.out.println(no.avaliar());
        
        p.setValor(true);
        
        System.out.println(no.avaliar());
        
        Formula formula = new Formula(
                String.format("(%c %c %c)", 'p', '\u2228', 'q'));
        
        formula.show();
        
    }

} // fim da classe Teste

// (((p .e. q) -> r) <-> s)
