import java.time.LocalDate;

public abstract class ObraArte {
    private int idObra;
    private String titulo;
    private String autor;
    private String periodo;
    private double valorEconomico;
    private LocalDate fechaCreacion;
    private LocalDate fechaEntrada;
    private String descripcion;
    private String estado;

    public ObraArte(int idObra, String titulo, String autor, String periodo, double valorEconomico, LocalDate fechaCreacion, LocalDate fechaEntrada, String descripcion, String estado) {
        setIdObra(idObra);
        setTitulo(titulo);
        setAutor(autor);
        setPeriodo(periodo);
        setValorEconomico(valorEconomico);
        setFechaCreacion(fechaCreacion);
        setFechaEntrada(fechaEntrada);
        setDescripcion(descripcion);
        setEstado(estado);
    }
    
    // Getters y Setters -------

    public int getIdObra() {
        return idObra;
    }
    public void setIdObra(int idObra) {
        if (idObra > 0) this.idObra = idObra;
        else throw new IllegalArgumentException("El ID de la obra debe ser un número positivo.");
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.isBlank()) this.titulo = titulo.trim();
        else throw new IllegalArgumentException("El título no puede ser nulo o vacío.");
    }

    public String getAutor () {
        return autor;
    }
    public void setAutor(String autor) {
        if (autor != null && !autor.isBlank()) this.autor = autor.trim();
        else throw new IllegalArgumentException("El autor no puede ser nulo o vacío.");
    }

    public String getPeriodo() {
        return periodo;
    }
    public void setPeriodo(String periodo) {
        if (periodo != null && !periodo.isBlank()) this.periodo = periodo.trim();
        else throw new IllegalArgumentException("El período no puede ser nulo o vacío.");
    }

    public double getValorEconomico() {
        return valorEconomico;
    }
    public void setValorEconomico(double valorEconomico) {
        if (valorEconomico >= 0) this.valorEconomico = valorEconomico;
        else throw new IllegalArgumentException("El valor económico no puede ser negativo.");
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        if (descripcion != null && !descripcion.isBlank()) this.descripcion = descripcion.trim();
        else throw new IllegalArgumentException("La descripción no puede ser nula o vacía.");
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(LocalDate fechaCreacion) {
        if (fechaCreacion != null) this.fechaCreacion = fechaCreacion;
        else throw new IllegalArgumentException("La fecha de creación no puede ser nula.");
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }
    public void setFechaEntrada(LocalDate fechaEntrada) {
        if (fechaEntrada != null) this.fechaEntrada = fechaEntrada;
        else throw new IllegalArgumentException("La fecha de entrada no puede ser nula.");
    }

    public String getEstado () {
        return estado;
    }
    public void setEstado(String estado) {
        if (estado != null && !estado.isBlank()) this.estado = estado.trim();
        else throw new IllegalArgumentException("El estado no puede ser nulo o vacío.");
    }

    // Metodos --------------

    public void cambiarEstado(String nuevoEstado) {
        setEstado(nuevoEstado);
    System.out.println("Obra " + getTitulo() + " ahora está en estado: " + getEstado());
    } 
    
    public boolean necesitaRestauracion() {
        String e = estado.toLowerCase();
        return e.equals("en restauracion") || e.equals("dañado") || e.equals("en mal estado") || e.equals("deteriorada");
    }

    public void ceder(){
        setEstado("cedida");
        System.out.println("Obra " + getTitulo() + " ha sido cedida.");
    }
    
    @Override
    public String toString() {
        return "ObraArte(ID: " + idObra + ", Titulo: " 
        + titulo + ", Autor: " + autor + ", Periodo: " + periodo + ", FechaCreacion: "
        + fechaCreacion + ", FechaEntrada: " + fechaEntrada + ", Descripcion: " 
        + descripcion + ", Estado: " + estado + ")";
    }
}


