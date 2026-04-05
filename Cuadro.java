import java.time.LocalDate;

public class Cuadro extends ObraArte {
    private String tecnica;
    private String estilo;

    public Cuadro(int idObra, String titulo, String autor,
        String periodo, double valorEconomico, 
        LocalDate fechaCreacion, LocalDate fechaEntrada, 
        String descripcion, String estado, String tecnica, String estilo) 
    {
        super(idObra, titulo, autor, periodo, valorEconomico, fechaCreacion, 
            fechaEntrada, descripcion, estado);
            this.tecnica = tecnica;
            this.estilo = estilo;
    }

    public String getTecnica() {
        return tecnica;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    @Override
    public String toString() {
        return "Cuadro: " + getTitulo() + " | Autor: " + getAutor() +
        " | Técnica: " + tecnica + " | Estilo: " + estilo + " | Estado: " + getEstado();
    }
}

