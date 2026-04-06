import java.util.ArrayList;
import java.util.List;


public class Museo {
    private String nombre;
    private String direccion;
    private List<ObraArte> obrasRecibidas;
    private List<Cesion> cesiones;

    public Museo(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.obrasRecibidas = new ArrayList<>();
        this.cesiones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {this.direccion = direccion;}

    public List<ObraArte> getObrasRecibidas() {
        return obrasRecibidas;
    }

    public List<Cesion> getCesiones() {
        return cesiones;
    }

    public void recibirObra(ObraArte obra) {
        obrasRecibidas.add(obra);
    }

    public void delvolverObra(ObraArte obra) {
        if (obrasRecibidas.contains(obra)) {
            obrasRecibidas.remove(obra);
            System.out.println("museo'" + nombre + "' ha devuelto la obra '" + obra.getTitulo() + "' correctamente.");
        }
        else {
            System.out.println("La obra '" + obra.getTitulo() + "' no se encuentra en el museo '" + nombre + "'.");
        }
    }

        public void solicitarCesion(Cesion cesion) {
            cesiones.add(cesion);
            System.out.println("El museo '" + nombre + 
            "' ha solicitado la cesión de la obra '" + cesion.getObra().getTitulo() + "' correctamente.");
        }
    }

    