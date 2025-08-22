import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Para simplicidad; usa UTF-8 en tu consola
        try {
            Path inputPath = obtenerRutaEntrada(args, scanner);

            // 1) Leer alumnos
            List<Student> students = StudentCsvReader.read(inputPath);
            System.out.println("Alumnos cargados: " + students.size());

            // 2) Capturar calificaciones
            GradeCaptureService.captureGrades(students, scanner);

            // 3) Ofrecer generar archivo CSV
            if (GradeCaptureService.allGradesCaptured(students)) {
                if (preguntarSiGenerar(scanner)) {
                    Path outputPath = solicitarRutaSalida(scanner);
                    CsvExporter.export(students, outputPath);
                    System.out.println("Archivo generado: " + outputPath.toAbsolutePath());
                } else {
                    System.out.println("No se generó archivo. Fin.");
                }
            } else {
                System.out.println("No todas las calificaciones fueron capturadas. No se generará archivo.");
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Error de datos: " + e.getMessage());
            System.exit(2);
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
            System.exit(3);
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            System.exit(1);
        } finally {
            scanner.close();
        }
    }

    private static Path obtenerRutaEntrada(String[] args, Scanner scanner) {
        if (args != null && args.length >= 1 && !args[0].isBlank()) {
            return Paths.get(args[0]);
        }
        System.out.print("Ruta del CSV de alumnos (ej. alumnos.csv): ");
        String ruta = scanner.nextLine().trim();
        return Paths.get(ruta);
    }

    private static boolean preguntarSiGenerar(Scanner scanner) {
        while (true) {
            System.out.print("¿Desea generar el archivo CSV de calificaciones? (S/N): ");
            String ans = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
            if (ans.equals("s") || ans.equals("si") || ans.equals("sí")) return true;
            if (ans.equals("n") || ans.equals("no")) return false;
            System.out.println("  Respuesta no válida. Escriba S o N.");
        }
    }

    private static Path solicitarRutaSalida(Scanner scanner) {
        System.out.print("Nombre o ruta de salida (Enter para 'calificaciones.csv'): ");
        String out = scanner.nextLine().trim();
        if (out.isEmpty()) out = "calificaciones.csv";
        return Paths.get(out);
    }
}