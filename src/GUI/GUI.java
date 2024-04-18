package GUI;

import Control.Control;

import java.util.Scanner;

public class GUI {

    private Control control;

    public GUI(Control control) {
        this.control = control;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Agregar diseñador");
            System.out.println("2. Eliminar diseñador");
            System.out.println("3. Consultar diseñadores");
            System.out.println("4. Modificar diseñador");
            System.out.println("5. Agregar marca");
            System.out.println("6. Eliminar marca");
            System.out.println("7. Consultar marcas");
            System.out.println("8. Modificar marca");
            System.out.println("9. Agregar auto");
            System.out.println("10. Eliminar auto");
            System.out.println("11. Consultar autos");
            System.out.println("12. Modificar auto");
            System.out.println("13. Salir");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    control.agregarDiseñador();
                    break;
                case 2:
                    control.eliminarDiseñador();
                    break;
                case 3:
                    control.consultarDiseñadores();
                    break;
                case 4:
                    control.modificarDiseñador();
                    break;
                case 5:
                    control.agregarMarca();
                    break;
                case 6:
                    control.eliminarMarca();
                    break;
                case 7:
                    control.consultarMarcas();
                    break;
                case 8:
                    control.modificarMarca();
                    break;
                case 9:
                    control.agregarAuto();
                    break;
                case 10:
                    control.eliminarAuto();
                    break;
                case 11:
                    control.consultarAutos();
                    break;
                case 12:
                    control.modificarAuto();
                    break;
                case 13:
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 13);
    }
}