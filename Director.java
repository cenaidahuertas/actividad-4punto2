import java.util.ArrayList;
import java.util.List;

public class Director extends Usuario {
    private List<Cesion> cesiones;

    public Director(int IdUsuario, String nombre, String apellido, String email, String contraseña) {
        super(IdUsuario, nombre, apellido, email, contraseña);
        this.cesiones = new ArrayList<>();
    }

    public void gestionarCesion(Cesion cesion) {
        // Lógica para gestionar la cesión
        cesiones.add(cesion);
        System.out.println("Cesión de " + cesion.getObra().getTitulo() + " gestionada correctamente.");
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Cesion cesion : cesiones) {
            total += cesion.getObra().getValorEconomico();
        }
        System.out.println("Valor total de las cesiones: " + total);
        return total;
    }
    
    public void verCartalogo() {
        this.catalogo.listarObras();
    }
    
    public List<Cesion> verCeseionesActivas() {
        List<Cesion> cesionesActivas = new ArrayList<>();
        for (Cesion cesion : cesiones) {
            if ("activa".equals(cesion.getEstado())) cesionesActivas.add(cesion);
        }
        if (cesionesActivas.isEmpty()) {
            System.out.println("No hay cesiones activas.");
        } else {
            System.out.println("Cesiones activas (" + cesionesActivas.size() + "):");
            for (Cesion c : cesionesActivas) {
                System.out.println("- Obra: " + c.getObra().getTitulo() + " | Museo: " + c.getMuseo().getNombre());
            }
        }
        return cesionesActivas;
    }

    @Override
    public String toString() {
        return "Director{" + getNombre() + " " + getApellido() + ", email=" + getEmail() + "}";
    }

}


