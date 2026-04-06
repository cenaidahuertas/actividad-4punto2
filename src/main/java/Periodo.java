public enum Periodo {
    RENACIMIENTO("Renacimiento"),
    BARROCO("Barroco"),
    NEOCLASICISMO("Neoclasicismo"),
    ROMANTICISMO("Romanticismo"),
    REALISMO("Realismo"),
    IMPRESIONISMO("Impresionismo"),
    MODERNO_CONTEMPORANEO("Moderno-Contemporáneo");

    private final String valor;

    Periodo(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}
