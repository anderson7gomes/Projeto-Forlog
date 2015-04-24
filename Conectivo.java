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

} // fim da classe Conectivo
