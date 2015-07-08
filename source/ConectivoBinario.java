public class ConectivoBinario extends Conectivo {

	/**
     * Constroi uma constante do tipo TipoConectivo 
     * representando o tipo do conectivo
     * @param tipo constante do tipo TipoConectivo 
     *        representando o tipo do conectivo binario
     * @see TipoConectivo
     */

    public ConectivoBinario(TipoConectivo tipo) {

        super(tipo);
   
        setTipo(tipo);

    } // fim do construtor ConectivoBinario
    
    /**
     * {@inheritDoc}
     */
    
    @Override
    public void setTipo(TipoConectivo tipo) {
    
        if (tipo.toString().equals("NEGACAO")) {
            throw new IllegalArgumentException(
                    "NEGACAO não é um conectivo binário");
        }
        
        super.setTipo(tipo);
    
    } // fim do método setTipo
   
    /**
     * Aceita dois valores booleanos e aplica a tabela verdade nos valores
     * de acordo com o tipo atual do conectivo binario
     * @param p operando logico
     * @param q operando logico
     * @return valor logico resultado a aplicacao 
     *         do conectivo nos dois operandos
     */
   
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
