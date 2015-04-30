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
    public String toString(){
        if(getTipo().equals("E"))
         return String.valueOf('\u2227');
        if(getTipo().equals("OU"))
         return String.valueOf('\u2228');
        if(getTipo().equals("SE_ENTAO"))
         return String.valueOf('\u2192' );
        if(getTipo().equals("SSE"))
         return String.valueOf('\u2194');
        if(getTipo().equals("NEGACAO"))
         return String.valueOf('\u223c');
        return " ";
    }
} // fim da classe Conectivo
