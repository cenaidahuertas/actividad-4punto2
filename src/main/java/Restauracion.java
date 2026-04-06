import java.time.LocalDate;

public class Restauracion {
    private ObraArte obra;
    private String tipo;
    private String motivo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;

    public Restauracion(ObraArte obra, String tipo, String motivo) {
        this.obra = obra;
        this.tipo = tipo;
        this.motivo = motivo;
        this.fechaInicio = null;
        this.fechaFin = null;
        this.estado = "Pendiente";
        }
    
        public ObraArte getObra() {
            return obra;
        }

        public String getTipo() {
            return tipo;
        }   

        public String getMotivo() {
            return motivo;
        }
        public LocalDate getFechaInicio() {
            return fechaInicio;
        }

        public LocalDate getFechaFin() {
            return fechaFin;
        }

        public String getEstado() {
            return estado;
        }
        public void setEstado(String estado) {
            this.estado = estado;
        }

        public void iniciarRestauracion() {
            if (!estado.equals("Pendiente")) {
                System.out.println("La restauración ya ha sido iniciada. Estado actual: " + estado + ".");
                return;
            }
            this.fechaInicio = LocalDate.now();
            this.estado = "En Proceso";
            System.out.println("Restauración iniciada para la obra: " + obra.getTitulo() + ". Fecha de inicio: " + fechaInicio + ".");
        }


        public void finalizarRestauracion() {
            if (!estado.equals("En Proceso")) {
                System.out.println("La restauración no se puede finalizar. Estado actual: " + estado + ".");
                return;
            }
            this.fechaFin = LocalDate.now();
            this.estado = "Finalizada";
            System.out.println("Restauración finalizada para la obra: " + obra.getTitulo() + ". Fecha de finalización: " + fechaFin + ".");
        }

    @Override
    public String toString() {
        return "Restauracion{" +
                "obra=" + obra.getTitulo() +
                ", tipo='" + tipo + '\'' +
                ", motivo='" + motivo + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", estado='" + estado + '\'' +
                '}';
    }
}   