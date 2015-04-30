public class Teste {

    public static void main(String[] args) {
    
        Atomo a1 = new Atomo('p', true);
        Atomo a2 = new Atomo('q', true);
    
        ConectivoBinario c1 = new ConectivoBinario(TipoConectivo.OU);
        ConectivoUnario c2 = new ConectivoUnario(TipoConectivo.NEGACAO);
        
        No noA1 = new No(a1);
        No noA2 = new No(a2);
        
        No noConectivo1 = new No(c2, noA2);
        No noConectivo2 = new No(c1, noA1, noA2);
        
        No noConectivo3 = new No(c1, noConectivo1, noConectivo2);
        
        System.out.println(noConectivo3.avaliar());
        String formula ="(eVp)";
        System.out.println(formula.substring(1,formula.length()-1));
        String teste = "este é um teste de espaços em branco";
        String teste2 = teste.replace(" ","");
        System.out.println(teste + " " + teste.length());
        System.out.println(teste2 + " " + teste2.length());
    }

} // fim da classe Teste
