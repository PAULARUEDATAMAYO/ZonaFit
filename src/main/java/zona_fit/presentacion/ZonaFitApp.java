package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    private static void zonaFitApp() {
        var salir = false;
        var consola = new Scanner(System.in);

        //creamos objeto de clienteDao

        var clienteDao = new ClienteDAO();
        while (!salir) {
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, opcion, clienteDao);

            } catch (Exception e) {
                System.out.println("Error al ejecutar opciones: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner consola) {
        System.out.print("""
                ***Zona Fit (gym)***
                1. Listar clientes
                2. Buscar clientes
                3. Aregar clientes
                4. Modificar clientes
                5. Eliminar clientes
                6. Salir
                Elige una opcion: \s""");
        return Integer.parseInt(consola.nextLine());

    }

    private static boolean ejecutarOpciones(Scanner consola, int opcion, IClienteDAO clienteDAO) {
        var salir = false;
        switch (opcion) {
            case 1 -> {// listar clientes
                System.out.println("--- Listado de clientes---");
                var clientes = clienteDAO.listarClientes();
                clientes.forEach(System.out::println);
            }
            case 2 -> { //buscar cliente
                System.out.print("introduce el id del cliente a buscar: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente((idCliente));
                var encontrado = clienteDAO.buscarClientePorId(cliente);
                if (encontrado)
                    System.out.println("Cliente encontrado" + cliente);
                else System.out.println("Cliente No encontrado" + cliente);
            }
            case 3 -> {// agregar cliente
                System.out.println("--- Agregar cliente: ---");
                System.out.print("Nombre: ");
                var nomnre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Membresia: ");
                var membresia = Integer.parseInt(consola.nextLine());

                //creamos el objeto cliente (sin el id)
                var cliente = new Cliente(nomnre, apellido, membresia);
                var agregado = clienteDAO.agregarCliente(cliente);
                if (agregado)
                    System.out.println("cliente agregado");
                else
                    System.out.println("Cliente no agregado");
            }
            case 4 -> { //modificar cliente
                System.out.println("Modificar cliente");
                System.out.print("Id cliente: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                System.out.print("nombre: ");
                var nombre = consola.nextLine();
                System.out.print("apellido: ");
                var apellido = consola.nextLine();
                System.out.print("membresia: ");
                var membresia = Integer.parseInt(consola.nextLine());

                //creamos el objeto a modificar
                var cliente = new Cliente(idCliente, nombre, apellido, membresia);
                var modificado = clienteDAO.modificarCliente(cliente);
                if (modificado)
                    System.out.println("Cliente modificado" + cliente);
                else
                    System.out.println("Cliente no modificado" + cliente);
            }
            case 5 -> {// eliminar cliente
                System.out.println("--- Eliminar cliente---");
                System.out.print("Cliente a eliminar id: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var eliminado = clienteDAO.eliminarCliente(cliente);
                if (eliminado)
                    System.out.println("Cliente eliminado" + cliente);
                else
                    System.out.println("Cliente no eliminado" + cliente);
            }
            case 6 -> { // salir
                System.out.println("Hasta pronto.");
                salir = true;
            }
            default -> System.out.println("Opcion no reconocida" + opcion);
        }
        return salir;
    }
}


