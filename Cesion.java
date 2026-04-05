import java.time.LocalDate;

public class Cesion {
    private ObraArte obra;
    private Museo museo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double importe;
    private String estado;

    public Cesion(ObraArte obra, Museo museo, LocalDate fechaInicio, LocalDate fechaFin, double importe, String estado) {
        setObra(obra);
        setMuseo(museo);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setImporte(importe);
        this.estado = "En Curso";
    }

    public ObraArte getObra() {
        return obra;
    }
    public void setObra(ObraArte obra) {
        if (obra != null) {
            this.obra = obra;
        } else {
            throw new IllegalArgumentException("la cesion debe estar asociada a una obra de arte válida.");
        }
    }

    public Museo getMuseo() {
        return museo;
    }
    public void setMuseo(Museo museo) {
        if (museo != null) this.museo = museo;
        else throw new IllegalArgumentException("la cesion debe estar asociada a un museo válido.");
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(LocalDate fechaInicio) {
        if (fechaInicio != null) {
            this.fechaInicio = fechaInicio;
        } else {
            throw new IllegalArgumentException("la cesion debe tener una fecha de inicio válida.");
        }
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(LocalDate fechaFin) {
        if (fechaFin != null) {
            this.fechaFin = fechaFin;
        } else throw new IllegalArgumentException("la cesion debe tener una fecha de fin válida.");
    }

    public double getImporte() {
        return importe;
    }
    public void setImporte(double importe) {
        if (importe >= 0) {
            this.importe = importe;
        } else throw new IllegalArgumentException("la cesion debe tener un importe válido.");
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;       
    }

    public void iniciarCesion() {
        this.estado = "activa";
        obra.setEstado("En Cesión");
        System.out.println("La cesión de la obra " + obra.getTitulo() + " al museo " + museo.getNombre() + " ha comenzado.");
    }

    public void finalizarCesion() {
        this.estado = "finalizada";
        obra.setEstado("Disponible");
        System.out.println("La cesión de la obra " + obra.getTitulo() + " al museo " + museo.getNombre() + " ha finalizado.");
    }

    @Override
    public String toString() {
        return "Cesion{" +
                "obra=" + obra.getTitulo() +
                ", museo=" + museo.getNombre() +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", importe=" + importe +
                ", estado='" + estado + '\'' +
                '}';
    }
}


