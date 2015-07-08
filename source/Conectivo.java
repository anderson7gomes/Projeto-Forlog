/**
 * Classe que representa um conectivo, com um caractere identificando o 
 * tipo(ex: V (OU), ~ (NEGACAO))
 */

public abstract class Conectivo {

    private TipoConectivo tipo;
    
    /**
     * Constroi uma constante do tipo TipoConectivo 
     * representando o tipo do conectivo
     * @param tipo constante do tipo TipoConectivo 
     *        representando o tipo do conectivo
     * @see TipoConectivo
     */
    
    public Conectivo(TipoConectivo tipo) {
        setTipo(tipo);
    }
    
    /**
	 * Atualiza o tipo do conectivo
	 * @param tipo constante TipoConectivo representando o tipo
	 */
    
    public void setTipo(TipoConectivo tipo) {
        this.tipo = tipo;
    } 

	/**
	 * Recupera o tipo do conectivo na forma de string
	 * @return representacao em forma de String do TipoConectivo atual
	 */

    public String getTipo() {
        return tipo.toString();
    }
    
    /**
     * Verifica se um caractere pode representar um conectivo
     * @param character caractere a ser verificado
     * @return true, se o caractere representa um dos cinco 
     *         conectivos validos ou false caso contrario
     */
    
    public static boolean isConectivoValido(char character) {
    
    	if ((character == '\u2227') || (character == '\u2228') || 
    			(character == '\u2192') || (character == '\u2194') || 
    			(character == '\u223c')) {
    		return true;		
    	}		
    	
    	return false;
    
    }
    
    /**
     * Verifica se um caractere pode representar um conectivo binario
     * @param character caractere a ser verificado
     * @return true, se o caractere representa um dos quatro 
     *         conectivos binarios validos ou false caso contrario
     */
    
    public static boolean isConectivoBinarioValido(char character) {
    
    	if ((character == '\u2227') || (character == '\u2228') || 
    			(character == '\u2192') || (character == '\u2194')) {
    		return true;		
    	}
    	
    	return false;		
    
    }
    
    /**
     * Verifica se um caractere pode representar um conectivo unario
     * @param character caractere a ser verificado
     * @return true, se o caractere representa o conectivo 
     *         de negacao ou false caso contrario
     */
    
    public static boolean isConectivoUnarioValido(char character) {
    
    	if (character == '\u223c') {
    		return true;
    	}
    	
    	return false;
    
    }
    
    /**
     * Retorna uma String representando o tipo atual do conectivo.
     * @return String representando o tipo atual do conectivo.
     */
    
    public String toString() {
    
        if (getTipo().equals("E")) { 
            return String.valueOf('\u2227');
        }    
        
        if (getTipo().equals("OU")) {
            return String.valueOf('\u2228');
        }
            
        if (getTipo().equals("SE_ENTAO")) {
            return String.valueOf('\u2192' );
        }
            
        if (getTipo().equals("SSE")) {
            return String.valueOf('\u2194');
        }
            
        if (getTipo().equals("NEGACAO")) {
            return String.valueOf('\u223c');
        }
            
        return " ";
        
    }
    
} // fim da classe Conectivo
