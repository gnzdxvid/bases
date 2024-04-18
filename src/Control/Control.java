package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Control {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/autos?user=root";
    private static final String USER = "root";
    private static final String PASSWORD = "311003";

    public void agregarDiseñador() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido paterno: ");
        String apellidoPaterno = scanner.nextLine();
        System.out.print("Ingrese el apellido materno: ");
        String apellidoMaterno = scanner.nextLine();
        System.out.print("Ingrese la nacionalidad: ");
        String nacionalidad = scanner.nextLine();
        System.out.print("Ingrese el sexo (M/F): ");
        String sexo = scanner.nextLine();
        System.out.print("Ingrese la fecha de nacimiento (YYYY-MM-DD): ");
        String fechaNacimiento = scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO diseñador (nombre, apellido_paterno, apellido_materno, nacionalidad, sexo, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setString(2, apellidoPaterno);
            statement.setString(3, apellidoMaterno);
            statement.setString(4, nacionalidad);
            statement.setString(5, sexo);
            statement.setString(6, fechaNacimiento);
            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("Diseñador insertado correctamente.");
            } else {
                System.out.println("No se pudo insertar el diseñador.");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar el diseñador: " + e.getMessage());
        }
    }

    public void eliminarDiseñador() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del diseñador a eliminar: ");
        int id = scanner.nextInt();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "DELETE FROM diseñador WHERE id_diseñador = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("Diseñador eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún diseñador con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el diseñador: " + e.getMessage());
        }
    }

    public void consultarDiseñadores() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM diseñador";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("ID\tNombre\tApellido Paterno\tApellido Materno\tNacionalidad\tSexo\tFecha de Nacimiento");

            while (resultSet.next()) {
                int id = resultSet.getInt("id_diseñador");
                String nombre = resultSet.getString("nombre");
                String apellidoPaterno = resultSet.getString("apellido_paterno");
                String apellidoMaterno = resultSet.getString("apellido_materno");
                String nacionalidad = resultSet.getString("nacionalidad");
                String sexo = resultSet.getString("sexo");
                String fechaNacimiento = resultSet.getString("fecha_nacimiento");

                System.out.println(id + "\t" + nombre + "\t" + apellidoPaterno + "\t" + apellidoMaterno + "\t" + nacionalidad + "\t" + sexo + "\t" + fechaNacimiento);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar los diseñadores: " + e.getMessage());
        }
    }

    public void modificarDiseñador() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del diseñador a modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de ingresar el ID

        System.out.print("Ingrese el nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el nuevo apellido paterno: ");
        String apellidoPaterno = scanner.nextLine();
        System.out.print("Ingrese el nuevo apellido materno: ");
        String apellidoMaterno = scanner.nextLine();
        System.out.print("Ingrese la nueva nacionalidad: ");
        String nacionalidad = scanner.nextLine();
        System.out.print("Ingrese el nuevo sexo (M/F): ");
        String sexo = scanner.nextLine();
        System.out.print("Ingrese la nueva fecha de nacimiento (YYYY-MM-DD): ");
        String fechaNacimiento = scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "UPDATE diseñador SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, nacionalidad = ?, sexo = ?, fecha_nacimiento = ? WHERE id_diseñador = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setString(2, apellidoPaterno);
            statement.setString(3, apellidoMaterno);
            statement.setString(4, nacionalidad);
            statement.setString(5, sexo);
            statement.setString(6, fechaNacimiento);
            statement.setInt(7, id);
            int filasModificadas = statement.executeUpdate();

            if (filasModificadas > 0) {
                System.out.println("Diseñador modificado correctamente.");
            } else {
                System.out.println("No se encontró ningún diseñador con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar el diseñador: " + e.getMessage());
        }
    }

    public void agregarMarca() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre de la marca: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el año: ");
        int año = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de ingresar el año
        System.out.print("Ingrese el ID del diseñador: ");
        int diseñador = scanner.nextInt();
        System.out.print("Ingrese el precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea después de ingresar el precio
        System.out.print("Ingrese el tipo de combustible: ");
        String combustible = scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO marca (nombre, año, diseñador, precio, combustible) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
           statement.setString(1, nombre);
           statement.setInt(2, año);
           statement.setInt(3, diseñador);
           statement.setDouble(4, precio);
           statement.setString(5, combustible);
           int filasInsertadas = statement.executeUpdate();

           if (filasInsertadas > 0) {
               System.out.println("Marca insertada correctamente.");
           } else {
               System.out.println("No se pudo insertar la marca.");
           }
       } catch (SQLException e) {
           System.out.println("Error al insertar la marca: " + e.getMessage());
       }
   }

   public void eliminarMarca() {
       Scanner scanner = new Scanner(System.in);
       System.out.print("Ingrese el ID de la marca a eliminar: ");
       int id = scanner.nextInt();

       try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
           String sql = "DELETE FROM marca WHERE id_modelo = ?";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setInt(1, id);
           int filasEliminadas = statement.executeUpdate();

           if (filasEliminadas > 0) {
               System.out.println("Marca eliminada correctamente.");
           } else {
               System.out.println("No se encontró ninguna marca con ese ID.");
           }
       } catch (SQLException e) {
           System.out.println("Error al eliminar la marca: " + e.getMessage());
       }
   }

   public void consultarMarcas() {
       try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
           String sql = "SELECT m.id_modelo, m.nombre, m.año, d.nombre AS diseñador, m.precio, m.combustible " +
                   "FROM marca m " +
                   "JOIN diseñador d ON m.diseñador = d.id_diseñador";
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(sql);

           System.out.println("ID\tNombre\tAño\tDiseñador\tPrecio\tCombustible");

           while (resultSet.next()) {
               int id = resultSet.getInt("id_modelo");
               String nombre = resultSet.getString("nombre");
               int año = resultSet.getInt("año");
               String diseñador = resultSet.getString("diseñador");
               double precio = resultSet.getDouble("precio");
               String combustible = resultSet.getString("combustible");

               System.out.println(id + "\t" + nombre + "\t" + año + "\t" + diseñador + "\t" + precio + "\t" + combustible);
           }
       } catch (SQLException e) {
           System.out.println("Error al consultar las marcas: " + e.getMessage());
       }
   }

   public void modificarMarca() {
       Scanner scanner = new Scanner(System.in);
       System.out.print("Ingrese el ID de la marca a modificar: ");
       int id = scanner.nextInt();
       scanner.nextLine(); // Consumir el salto de línea después de ingresar el ID

       System.out.print("Ingrese el nuevo nombre de la marca: ");
       String nombre = scanner.nextLine();
       System.out.print("Ingrese el nuevo año: ");
       int año = scanner.nextInt();
       scanner.nextLine(); // Consumir el salto de línea después de ingresar el año
       System.out.print("Ingrese el nuevo ID del diseñador: ");
       int diseñador = scanner.nextInt();
       System.out.print("Ingrese el nuevo precio: ");
       double precio = scanner.nextDouble();
       scanner.nextLine(); // Consumir el salto de línea después de ingresar el precio
       System.out.print("Ingrese el nuevo tipo de combustible: ");
       String combustible = scanner.nextLine();

       try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
           String sql = "UPDATE marca SET nombre = ?, año = ?, diseñador = ?, precio = ?, combustible = ? WHERE id_modelo = ?";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setString(1, nombre);
           statement.setInt(2, año);
           statement.setInt(3, diseñador);
           statement.setDouble(4, precio);
           statement.setString(5, combustible);
           statement.setInt(6, id);
           int filasModificadas = statement.executeUpdate();

           if (filasModificadas > 0) {
               System.out.println("Marca modificada correctamente.");
           } else {
               System.out.println("No se encontró ninguna marca con ese ID.");
           }
       } catch (SQLException e) {
           System.out.println("Error al modificar la marca: " + e.getMessage());
       }
   }

   public void agregarAuto() {
       Scanner scanner = new Scanner(System.in);
       System.out.print("Ingrese las placas: ");
       String placas = scanner.nextLine();
       System.out.print("Ingrese el ID de la marca: ");
       int marca = scanner.nextInt();
       scanner.nextLine(); // Consumir el salto de línea después de ingresar el ID
       System.out.print("Ingrese el NIV: ");
       String niv = scanner.nextLine();
       System.out.print("Ingrese el número de llantas: ");
       int llantas = scanner.nextInt();
       System.out.print("Ingrese el número de asientos: ");
       int asientos = scanner.nextInt();
       scanner.nextLine(); // Consumir el salto de línea después de ingresar el número de asientos
       System.out.print("Ingrese el color: ");
       String color = scanner.nextLine();
       System.out.print("Ingrese el tipo de auto (manual o automático): ");
       String tipoAuto = scanner.nextLine();
       System.out.print("Ingrese el número de puertas: ");
       int noPuertas = scanner.nextInt();
       scanner.nextLine(); // Consumir el salto de línea después de ingresar el número de puertas
       System.out.print("Ingrese el tipo de transmisión: ");
       String transmision = scanner.nextLine();
       System.out.print("Ingrese el número de cilindros: ");
       int noCilindros = scanner.nextInt();

       try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
           String sql = "INSERT INTO auto (placas, marca, niv, llantas, asientos, color, tipo_auto, no_puertas, transmision, no_cilindros) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setString(1, placas);
           statement.setInt(2, marca);
           statement.setString(3, niv);
           statement.setInt(4, llantas);
           statement.setInt(5, asientos);
           statement.setString(6, color);
           statement.setString(7, tipoAuto);
           statement.setInt(8, noPuertas);
           statement.setString(9, transmision);
           statement.setInt(10, noCilindros);
           int filasInsertadas = statement.executeUpdate();

           if (filasInsertadas > 0) {
               System.out.println("Auto insertado correctamente.");
           } else {
               System.out.println("No se pudo insertar el auto.");
           }
       } catch (SQLException e) {
           System.out.println("Error al insertar el auto: " + e.getMessage());
       }
   }

   public void eliminarAuto() {
       Scanner scanner = new Scanner(System.in);
       System.out.print("Ingrese las placas del auto a eliminar: ");
       String placas = scanner.nextLine();

       try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
           String sql = "DELETE FROM auto WHERE placas = ?";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setString(1, placas);
           int filasEliminadas = statement.executeUpdate();

           if (filasEliminadas > 0) {
               System.out.println("Auto eliminado correctamente.");
           } else {
               System.out.println("No se encontró ningún auto con esas placas.");
           }
       } catch (SQLException e) {
           System.out.println("Error al eliminar el auto: " + e.getMessage());
       }
   }

   public void consultarAutos() {
       try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
           String sql = "SELECT a.placas, m.nombre AS marca, a.niv, a.llantas, a.asientos, a.color, a.tipo_auto, a.no_puertas, a.transmision, a.no_cilindros " +
                   "FROM auto a " +
                   "JOIN marca m ON a.marca = m.id_modelo";
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(sql);

           System.out.println("Placas\tMarca\tNIV\tLlantas\tAsientos\tColor\tTipo Auto\tPuertas\tTransmisión\tCilindros");

           while (resultSet.next()) {
               String placas = resultSet.getString("placas");
               String marca = resultSet.getString("marca");
               String niv = resultSet.getString("niv");
               int llantas = resultSet.getInt("llantas");
               int asientos = resultSet.getInt("asientos");
               String color = resultSet.getString("color");
               String tipoAuto = resultSet.getString("tipo_auto");
               int noPuertas = resultSet.getInt("no_puertas");
               String transmision = resultSet.getString("transmision");
               int noCilindros = resultSet.getInt("no_cilindros");

               System.out.println(placas + "\t" + marca + "\t" + niv + "\t" + llantas + "\t" + asientos + "\t" + color + "\t" + tipoAuto + "\t" + noPuertas + "\t" + transmision + "\t" + noCilindros);
           }
       } catch (SQLException e) {
           System.out.println("Error al consultar los autos: " + e.getMessage());
       }
   }

   public void modificarAuto() {
       Scanner scanner = new Scanner(System.in);
       System.out.print("Ingrese las placas del auto a modificar: ");
       String placas = scanner.nextLine();
       System.out.print("Ingrese el nuevo ID de la marca: ");
       int marca = scanner.nextInt();
       scanner.nextLine(); // Consumir el salto de línea después de ingresar el ID
       System.out.print("Ingrese el nuevo NIV: ");
       String niv = scanner.nextLine();
       System.out.print("Ingrese el nuevo número de llantas: ");
       int llantas = scanner.nextInt();
       System.out.print("Ingrese el nuevo número de asientos: ");
       int asientos = scanner.nextInt();
       scanner.nextLine(); // Consumir el salto de línea después de ingresar el número de asientos
       System.out.print("Ingrese el nuevo color: ");
       String color = scanner.nextLine();
       System.out.print("Ingrese el nuevo tipo de auto (manual o automático): ");
       String tipoAuto = scanner.nextLine();
       System.out.print("Ingrese el nuevo número de puertas: ");
       int noPuertas = scanner.nextInt();
       scanner.nextLine(); // Consumir el salto de línea después de ingresar el número de puertas
       System.out.print("Ingrese el nuevo tipo de transmisión: ");
       String transmision = scanner.nextLine();
       System.out.print("Ingrese el nuevo número de cilindros: ");
       int noCilindros = scanner.nextInt();

       try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
           String sql = "UPDATE auto SET marca = ?, niv = ?, llantas = ?, asientos = ?, color = ?, tipo_auto = ?, no_puertas = ?, transmision = ?, no_cilindros = ? WHERE placas = ?";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setInt(1, marca);
           statement.setString(2, niv);
           statement.setInt(3, llantas);
           statement.setInt(4, asientos);
           statement.setString(5, color);
           statement.setString(6, tipoAuto);
           statement.setInt(7, noPuertas);
           statement.setString(8, transmision);
           statement.setInt(9, noCilindros);
           statement.setString(10, placas);
           int filasModificadas = statement.executeUpdate();

           if (filasModificadas > 0) {
               System.out.println("Auto modificado correctamente.");
           } else {
               System.out.println("No se encontró ningún auto con esas placas.");
           }
       } catch (SQLException e) {
           System.out.println("Error al modificar el auto: " + e.getMessage());
       }
   }
}