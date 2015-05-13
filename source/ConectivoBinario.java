public class ConectivoBinario extends Conectivo {

    public ConectivoBinario(TipoConectivo tipo) {

        super(tipo);
   
        setTipo(tipo);

    } // fim do construtor ConectivoBinario
    
    @Override
    public void setTipo(TipoConectivo tipo) {
    
        if (tipo.toString().equals("NEGACAO")) {
            throw new IllegalArgumentException(
                    "NEGACAO não é um conectivo binário");
        }
        
        super.setTipo(tipo);
    
    } // fim do método setTipo
   
    public boolean aplicar(boolean p, boolean q) {
            
        boolean resultado = false;    
            
        if (getTipo().equals("E")) {
            resultado = p && q;
        } else if (getTipo().equals("OU")) {
            resultado = p || q;
        } else if (getTipo().equals("SE_ENTAO")) {
            
            if (p && !q) {
                resultado = false;
            } else {
                resultado = true;
            }
            
        } else if (getTipo().equals("SSE")) {
        
            if (p == !q) {
                resultado = false;
            } else {
                resultado = true;
            }
        
        }
        
        return resultado;   
    
    } // fim do método aplicar

} // fim da classe ConectivoBinario
