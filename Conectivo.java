public abstract class Conectivo {

    private TipoConectivo tipo;
    
    public Conectivo(TipoConectivo tipo) {
        setTipo(tipo);
    }
    
    public void setTipo(TipoConectivo tipo) {
        this.tipo = tipo;
    } 

    public String getTipo() {
        return tipo.toString();
    }
    
    public static boolean isConectivoValido(char character) {
    
    	if ((character == '\u2227') || (character == '\u2228') || 
    			(character == '\u2192') || (character == '\u2194') || 
    			(character == '\u223c')) {
    		return true;		
    	}		
    	
    	return false;
    
    }
    
    public static boolean isConectivoBinarioValido(char character) {
    
    	if ((character == '\u2227') || (character == '\u2228') || 
    			(character == '\u2192') || (character == '\u2194')) {
    		return true;		
    	}
    	
    	return false;		
    
    }
    
    public static boolean isConectivoUnarioValido(char character) {
    
    	if (character == '\u223c') {
    		return true;
    	}
    	
    	return false;
    
    }

} // fim da classe Conectivo
