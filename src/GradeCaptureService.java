import java.util.List;
import java.util.Scanner;

public final class GradeCaptureService {

    private GradeCaptureService() { }

    public static void captureGrades(List<Student> students, Scanner scanner) {
        System.out.println("Captura de calificaciones para la asignatura: Diseño de Software");
        System.out.println("Ingrese valores enteros entre 1 y 100. Para cada alumno:");

        for (Student s : students) {
            while (true) {
                System.out.print(s.getMatricula() + " - " + s.getNombreCompleto() + " => calificación: ");
                String input = scanner.nextLine().trim();

                try {
                    int grade = Integer.parseInt(input);
                    if (grade < 1 || grade > 100) {
                        System.out.println("  Error: la calificación debe estar entre 1 y 100.");
                        continue;
                    }
                    s.setCalificacion(grade);
                    break;
                } catch (NumberFormatException ex) {
                    System.out.println("  Error: ingrese un número entero válido (sin decimales).");
                }
            }
        }

        System.out.println("Todas las calificaciones han sido capturadas.");
    }

    public static boolean allGradesCaptured(List<Student> students) {
        for (Student s : students) {
            if (!s.tieneCalificacion()) return false;
        }
        return true;
    }
}