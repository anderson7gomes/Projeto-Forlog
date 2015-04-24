public class ConectivoUnario extends Conectivo {

    public ConectivoUnario(TipoConectivo tipo) {

        super(tipo);
    
        setTipo(tipo);

    }
    
    @Override
    public void setTipo(TipoConectivo tipo) {
    
        if (!tipo.toString().equals("NEGACAO")) {
            throw new IllegalArgumentException(
                    "Conectivo unário deve ser NEGACAO");
        }
        
        super.setTipo(tipo);
    
    } // fim do método setTipo

    public boolean aplicar(boolean p) {
        return !p;
    } // fim do método aplicar

} // fim da classe ConectivoUnario
