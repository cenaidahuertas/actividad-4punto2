import java.time.LocalDate;

public class Escultura extends ObraArte {
    private String material;
    private String estilo;

    public Escultura(int idObra, String titulo, String autor,
        String periodo, double valorEconomico, 
        LocalDate fechaCreacion, LocalDate fechaEntrada, 
        String descripcion, String estado, String material, String estilo) 
    {
        super(idObra, titulo, autor, periodo, valorEconomico, fechaCreacion, 
            fechaEntrada, descripcion, estado);
            this.material = material;
            this.estilo = estilo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    @Override
    public String toString() {
        return "Escultura: " + getIdObra() + " | Título: " + getTitulo() + " | Autor: " + getAutor() +
        " | Material: " + material + " | Estilo: " + estilo + " | Estado: " + getEstado();
    }
}

