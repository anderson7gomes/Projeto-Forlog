public class ConectivoUnario extends Conectivo {

	/**
     * Constroi uma constante do tipo TipoConectivo 
     * representando o tipo do conectivo
     * @param tipo constante do tipo TipoConectivo 
     *        representando o tipo do conectivo unario
     * @see TipoConectivo
     */

    public ConectivoUnario(TipoConectivo tipo) {

        super(tipo);
    
        setTipo(tipo);

    }
    
    /**
     * {@inheritDoc}
     */
    
    @Override
    public void setTipo(TipoConectivo tipo) {
    
        if (!tipo.toString().equals("NEGACAO")) {
            throw new IllegalArgumentException(
                    "Conectivo unário deve ser NEGACAO");
        }
        
        super.setTipo(tipo);
    
    } // fim do método setTipo

	/**
     * Aceita um valor booleano e inverte o valor logico
     * @param p operando logico
     * @return valor logico invertido, resultado da aplicacao da negacao
     */

    public boolean aplicar(boolean p) {
        return !p;
    } // fim do método aplicar

} // fim da classe ConectivoUnario
