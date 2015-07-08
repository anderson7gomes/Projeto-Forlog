/**
 * Enum que mantem as constantes validas que representam os tipos de conectivos
 * utilizados na aplicacao
 */

public enum TipoConectivo {

	/**
	 * E logico, binario
	 */

    E, 
    
    /**
	 * OU logico, binario
	 */
    
    OU, 
    
    /**
	 * NEGACAO, unario
	 */
    
    NEGACAO, 
    
    /**
	 * Se Entao, binario
	 */
    
    SE_ENTAO, 
    
    /**
	 * Se e somente se, binario
	 */
    
    SSE;

} // fim da enum TipoConectivo
