import java.time.LocalDate;

public class Objeto extends ObraArte {

    public Objeto(int idObra, String titulo, String autor,
        String periodo, double valorEconomico, 
        LocalDate fechaCreacion, LocalDate fechaEntrada, 
        String descripcion, String estado) 
    {
        super(idObra, titulo, autor, periodo, valorEconomico, fechaCreacion, 
            fechaEntrada, descripcion, estado);
    }

    @Override
    public String toString() {
        return "Objeto: " + getTitulo() + " | Autor: " + getAutor() +
        " | Período: " + getPeriodo() + " | Descripción: " + getDescripcion() + " | Estado: " + getEstado();
    }
}

