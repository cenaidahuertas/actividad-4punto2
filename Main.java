import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Object[] datos = crearDatosIniciales();
        Museo museo       = (Museo) datos[0];
        Catalogo catalogo = (Catalogo) datos[1];
        Sala sala1        = (Sala) datos[2];
        Sala sala2        = (Sala) datos[3];
        Sala sala3        = (Sala) datos[4];

        List<Usuario> usuarios = new ArrayList<>();

        while (true) {
            System.out.println("\n=== SISTEMA DE GESTIÓN DE MUSEO ===");
            System.out.println("1. Registrar nuevo usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("0. Salir");
            String opcion = scanner.nextLine().trim();

            if (opcion.equals("1")) {
                Usuario usuario = crearUsuario(catalogo);
                usuarios.add(usuario);
                System.out.println("Usuario '" + usuario.getNombre() + " " + usuario.getApellido() + "' registrado exitosamente.");
                pausar();

            } else if (opcion.equals("2")) {
                System.out.print("Ingrese su correo electrónico: ");
                String email = scanner.nextLine();
                System.out.print("Ingrese su contraseña: ");
                String contrasena = scanner.nextLine();
                Usuario usuario = autenticarUsuario(email, contrasena, usuarios);
                if (usuario != null) {
                    if (usuario instanceof Director) {
                        menuDirector((Director) usuario, museo, catalogo);
                    } else if (usuario instanceof RestauradorJefe) {
                        menuRestaurador((RestauradorJefe) usuario, catalogo);
                    } else if (usuario instanceof EncargadoCatalogo) {
                        menuEncargado((EncargadoCatalogo) usuario, catalogo);
                    } else if (usuario instanceof Visitante) {
                        menuVisitante((Visitante) usuario, catalogo, sala1, sala2, sala3);
                    }
                }

            } else if (opcion.equals("0")) {
                System.out.println("Saliendo del sistema. ¡Hasta luego!");
                break;
            } else {
                System.out.println("Opción no válida.");
                pausar();
            }
        }
    }

    // ── Datos iniciales ───────────────────────────────────────────────────────
    static Object[] crearDatosIniciales() {
        Catalogo catalogo = new Catalogo(1, "Catalogo de museo");

        Sala sala1 = new Sala("Sala de Pinturas", "Primer piso");
        Sala sala2 = new Sala("Sala de Esculturas", "Segundo piso");
        Sala sala3 = new Sala("Sala de Objetos", "Tercer piso");

        Cuadro cuadro1 = new Cuadro(
            1, "La Mona Lisa", "Leonardo da Vinci", "Renacimiento",
            850000000, LocalDate.of(1503, 10, 1), LocalDate.of(2020, 1, 1),
            "Retrato de Lisa Gherardini, esposa de un comerciante florentino.",
            "disponible", "Óleo sobre tabla", "Renacentista"
        );

        Escultura escultura1 = new Escultura(
            2, "El Pensador", "Auguste Rodin", "Modernismo",
            12000000, LocalDate.of(1902, 1, 1), LocalDate.of(2021, 6, 15),
            "Escultura de un hombre sentado en profunda reflexion.",
            "disponible", "Bronce", "Modernista"
        );

        Objeto objeto1 = new Objeto(
            3, "Reloj de Sol", "Desconocido", "Antiguo",
            5000, LocalDate.of(1503, 10, 1), LocalDate.of(2020, 1, 1),
            "Reloj de sol antiguo utilizado para medir el tiempo mediante la posición del sol.",
            "deteriorada"
        );

        catalogo.registrarObra(cuadro1);
        catalogo.registrarObra(escultura1);
        catalogo.registrarObra(objeto1);

        sala1.asignarObra(cuadro1);
        sala2.asignarObra(escultura1);
        sala3.asignarObra(objeto1);

        Museo museo = new Museo("Museo de Arte e Historia", "Calle Principal 123, Ciudad");
        museo.recibirObra(cuadro1);
        museo.recibirObra(escultura1);
        museo.recibirObra(objeto1);

        return new Object[]{museo, catalogo, sala1, sala2, sala3};
    }

    // ── Crear usuario ─────────────────────────────────────────────────────────
    static Usuario crearUsuario(Catalogo catalogo) {
        System.out.println("\n=== REGISTRO DEL USUARIO ===");
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese su apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese su correo electrónico: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();

        System.out.println("\nSeleccione su rol:");
        System.out.println("1. Director");
        System.out.println("2. Restaurador Jefe");
        System.out.println("3. Visitante");
        System.out.println("4. Encargado de Catálogo");
        System.out.print("Ingrese el número correspondiente a su rol: ");
        String rol = scanner.nextLine();

        return switch (rol) {
            case "1" -> new Director(1, nombre, apellido, email, contrasena);
            case "2" -> new RestauradorJefe(2, nombre, apellido, email, contrasena);
            case "3" -> new Visitante(3, nombre, apellido, email, contrasena);
            case "4" -> new EncargadoCatalogo(4, nombre, apellido, email, contrasena, catalogo);
            default  -> {
                System.out.println("Rol no reconocido. Creando visitante por defecto.");
                yield new Visitante(4, nombre, apellido, email, contrasena);
            }
        };
    }

    // ── Autenticar usuario ────────────────────────────────────────────────────
    static Usuario autenticarUsuario(String email, String contrasena, List<Usuario> usuarios) {
        for (Usuario u : usuarios) {
            if (u.autenticar(email, contrasena)) {
                System.out.println("Autenticación exitosa. Bienvenido, " + u.getNombre() + " " + u.getApellido() + ".");
                return u;
            }
        }
        System.out.println("Autenticación fallida. Email o contraseña incorrectos.");
        return null;
    }

    // ── Menú Director ─────────────────────────────────────────────────────────
    static void menuDirector(Director usuario, Museo museo, Catalogo catalogo) {
        while (true) {
            System.out.println("\n=== MENU DEL DIRECTOR ===");
            System.out.println("1. Ver catálogo de obras");
            System.out.println("2. Ver valor total en cesión");
            System.out.println("3. Gestionar cesión de una obra");
            System.out.println("4. Ver cesiones activas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine().trim();

            if (opcion.equals("1")) {
                System.out.println("\n=== CATÁLOGO DE OBRAS ===");
                for (ObraArte obra : catalogo.getObras()) {
                    System.out.println("ID: " + obra.getIdObra() + " - Título: " + obra.getTitulo() + " - Autor: " + obra.getAutor());
                }
                pausar();

            } else if (opcion.equals("2")) {
                usuario.calcularValorTotal();
                pausar();

            } else if (opcion.equals("3")) {
                List<ObraArte> obras = catalogo.getObras();
                if (obras.isEmpty()) {
                    System.out.println("No hay obras disponibles para ceder.");
                } else {
                    for (int i = 0; i < obras.size(); i++) {
                        System.out.println((i + 1) + ". " + obras.get(i).getTitulo() + " | Estado: " + obras.get(i).getEstado());
                    }
                    System.out.print("Seleccione el número de la obra: ");
                    String sel = scanner.nextLine().trim();
                    if (sel.matches("\\d+")) {
                        int pos = Integer.parseInt(sel) - 1;
                        if (pos >= 0 && pos < obras.size()) {
                            Cesion cesion = new Cesion(obras.get(pos), museo, LocalDate.now(), LocalDate.of(2026, 12, 31), 5000000, "activa");
                            usuario.gestionarCesion(cesion);
                            cesion.iniciarCesion();
                        } else {
                            System.out.println("Opción inválida.");
                        }
                    } else {
                        System.out.println("Opción inválida.");
                    }
                }

            } else if (opcion.equals("4")) {
                usuario.verCeseionesActivas();
                pausar();

            } else if (opcion.equals("0")) {
                System.out.println("Saliendo del menú del Director.");
                break;
            } else {
                System.out.println("Opción no válida.");
                pausar();
            }
        }
    }

    // ── Menú Restaurador ──────────────────────────────────────────────────────
    static void menuRestaurador(RestauradorJefe usuario, Catalogo catalogo) {
        while (true) {
            System.out.println("\nBienvenido Restaurador: " + usuario.getNombre() + " " + usuario.getApellido());
            System.out.println("1. Ver obras que necesitan restauración");
            System.out.println("2. Iniciar restauración");
            System.out.println("3. Finalizar restauración");
            System.out.println("4. Consultar historial");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            String opcion = scanner.nextLine().trim();

            if (opcion.equals("1")) {
                List<ObraArte> deterioradas = new ArrayList<>();
                for (ObraArte o : catalogo.getObras()) {
                    if ("deteriorada".equals(o.getEstado())) deterioradas.add(o);
                }
                if (deterioradas.isEmpty()) System.out.println("No hay obras que necesiten restauración.");
                else {
                    System.out.println("Obras que necesitan restauración:");
                    for (ObraArte o : deterioradas) System.out.println("  - " + o.getTitulo());
                }
                pausar();

            } else if (opcion.equals("2")) {
                List<ObraArte> deterioradas = new ArrayList<>();
                for (ObraArte o : catalogo.getObras()) {
                    if ("deteriorada".equals(o.getEstado())) deterioradas.add(o);
                }
                if (deterioradas.isEmpty()) { System.out.println("No hay obras que necesiten restauración."); pausar(); continue; }
                for (int i = 0; i < deterioradas.size(); i++) {
                    System.out.println((i + 1) + ". " + deterioradas.get(i).getTitulo());
                }
                System.out.print("Seleccione el número de la obra: ");
                String sel = scanner.nextLine().trim();
                if (sel.matches("\\d+")) {
                    int pos = Integer.parseInt(sel) - 1;
                    if (pos >= 0 && pos < deterioradas.size()) {
                        Restauracion r = new Restauracion(deterioradas.get(pos), "limpieza", "Restauracion preventiva");
                        usuario.iniciarRestauracion(r);
                    } else System.out.println("Opción inválida.");
                } else System.out.println("Opción inválida.");
                pausar();

            } else if (opcion.equals("3")) {
                List<ObraArte> enRest = new ArrayList<>();
                for (ObraArte o : catalogo.getObras()) {
                    if ("en restauracion".equals(o.getEstado())) enRest.add(o);
                }
                if (enRest.isEmpty()) { System.out.println("No hay obras en restauración."); pausar(); continue; }
                for (int i = 0; i < enRest.size(); i++) System.out.println((i + 1) + ". " + enRest.get(i).getTitulo());
                System.out.print("Seleccione el número de la obra: ");
                String sel = scanner.nextLine().trim();
                if (sel.matches("\\d+")) {
                    int pos = Integer.parseInt(sel) - 1;
                    if (pos >= 0 && pos < enRest.size()) {
                        ObraArte obra = enRest.get(pos);
                        Restauracion restauracion = null;
                        for (Restauracion r : usuario.getRestauraciones()) {
                            if (r.getObra() == obra && "en proceso".equals(r.getEstado())) {
                                restauracion = r; break;
                            }
                        }
                        if (restauracion != null) usuario.finalizarRestauracion(restauracion);
                        else System.out.println("No se encontró restauración en curso para esa obra.");
                    } else System.out.println("Opción inválida.");
                } else System.out.println("Opción inválida.");
                pausar();

            } else if (opcion.equals("4")) {
                usuario.consultarHistorial();
                pausar();

            } else if (opcion.equals("0")) {
                System.out.println("Saliendo del menú del Restaurador Jefe."); break;
            } else { System.out.println("Opción no válida."); pausar(); }
        }
    }

    // ── Menú Encargado ────────────────────────────────────────────────────────
    static void menuEncargado(EncargadoCatalogo usuario, Catalogo catalogo) {
        while (true) {
            System.out.println("\nBienvenido Encargado: " + usuario.getNombre() + " " + usuario.getApellido());
            System.out.println("1. Listar obras del catálogo");
            System.out.println("2. Registrar nueva obra");
            System.out.println("3. Editar obra");
            System.out.println("4. Eliminar obra");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            String opcion = scanner.nextLine().trim();

            if (opcion.equals("1")) {
                catalogo.listarObras(); pausar();

            } else if (opcion.equals("2")) {
                System.out.print("Título: "); String titulo = scanner.nextLine();
                System.out.print("Autor: "); String autor = scanner.nextLine();
                System.out.print("Período: "); String periodo = scanner.nextLine();
                System.out.print("Descripción: "); String desc = scanner.nextLine();
                Cuadro nueva = new Cuadro(
                    catalogo.getObras().size() + 1, titulo, autor, periodo,
                    0, LocalDate.now(), LocalDate.now(), desc,
                    "disponible", "No especificada", "No especificado"
                );
                usuario.registrarObra(nueva); pausar();

            } else if (opcion.equals("3")) {
                if (catalogo.getObras().isEmpty()) { System.out.println("No hay obras para editar."); pausar(); continue; }
                List<ObraArte> obras = catalogo.getObras();
                for (int i = 0; i < obras.size(); i++) System.out.println((i + 1) + ". " + obras.get(i).getTitulo());
                System.out.print("Seleccione la obra: ");
                String sel = scanner.nextLine().trim();
                if (sel.matches("\\d+")) {
                    int pos = Integer.parseInt(sel) - 1;
                    if (pos >= 0 && pos < obras.size()) {
                        ObraArte obra = obras.get(pos);
                        System.out.print("Nuevo título (Enter para no cambiar): "); String t = scanner.nextLine().trim();
                        System.out.print("Nuevo autor (Enter para no cambiar): "); String a = scanner.nextLine().trim();
                        System.out.print("Nuevo estado (Enter para no cambiar): "); String e = scanner.nextLine().trim();
                        usuario.editarObra(obra.getIdObra(), t.isEmpty() ? null : t, a.isEmpty() ? null : a, null, e.isEmpty() ? null : e);
                    } else System.out.println("Opción inválida.");
                } else System.out.println("Opción inválida.");
                pausar();

            } else if (opcion.equals("4")) {
                if (catalogo.getObras().isEmpty()) { System.out.println("No hay obras para eliminar."); pausar(); continue; }
                List<ObraArte> obras = catalogo.getObras();
                for (int i = 0; i < obras.size(); i++) System.out.println((i + 1) + ". " + obras.get(i).getTitulo());
                System.out.print("Seleccione la obra a eliminar: ");
                String sel = scanner.nextLine().trim();
                if (sel.matches("\\d+")) {
                    int pos = Integer.parseInt(sel) - 1;
                    if (pos >= 0 && pos < obras.size()) {
                        ObraArte obra = obras.get(pos);
                        obras.remove(obra);
                        System.out.println("Obra '" + obra.getTitulo() + "' eliminada correctamente.");
                    } else System.out.println("Opción inválida.");
                } else System.out.println("Opción inválida.");
                pausar();

            } else if (opcion.equals("0")) {
                System.out.println("Saliendo del menú del Encargado."); break;
            } else { System.out.println("Opción no válida."); pausar(); }
        }
    }

    // ── Menú Visitante ────────────────────────────────────────────────────────
    static void menuVisitante(Visitante usuario, Catalogo catalogo, Sala sala1, Sala sala2, Sala sala3) {
        List<Sala> salas = List.of(sala1, sala2, sala3);
        while (true) {
            System.out.println("\nBienvenido Visitante: " + usuario.getNombre() + " " + usuario.getApellido());
            System.out.println("1. Ver obras disponibles");
            System.out.println("2. Consultar información de una obra");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            String opcion = scanner.nextLine().trim();

            if (opcion.equals("1")) {
                List<ObraArte> obras = catalogo.getObras();
                if (obras.isEmpty()) System.out.println("No hay obras disponibles.");
                else { System.out.println("Obras disponibles:"); obras.forEach(o -> System.out.println("  - " + o.getTitulo())); }
                pausar();

            } else if (opcion.equals("2")) {
                List<ObraArte> obras = catalogo.getObras();
                if (obras.isEmpty()) { System.out.println("No hay obras para consultar."); pausar(); continue; }
                for (int i = 0; i < obras.size(); i++) System.out.println((i + 1) + ". " + obras.get(i).getTitulo());
                System.out.print("Seleccione la obra: ");
                String sel = scanner.nextLine().trim();
                if (sel.matches("\\d+")) {
                    int pos = Integer.parseInt(sel) - 1;
                    if (pos >= 0 && pos < obras.size()) {
                        ObraArte obra = obras.get(pos);
                        System.out.println("Información de '" + obra.getTitulo() + "':");
                        System.out.println("  Autor: " + obra.getAutor());
                        System.out.println("  Periodo: " + obra.getPeriodo());
                        System.out.println("  Año de creación: " + obra.getFechaCreacion());
                        for (Sala sala : salas) {
                            if (sala.getObras().contains(obra)) System.out.println("  Sala: " + sala.getNombre());
                        }
                    } else System.out.println("Opción inválida.");
                } else System.out.println("Opción inválida.");
                pausar();

            } else if (opcion.equals("0")) {
                System.out.println("Saliendo del menú del Visitante."); break;
            } else { System.out.println("Opción no válida."); pausar(); }
        }
    }

    static void pausar() {
        System.out.print("Presione Enter para continuar...");
        scanner.nextLine();
    }
}
