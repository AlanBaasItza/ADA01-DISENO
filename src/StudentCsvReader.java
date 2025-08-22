import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class StudentCsvReader {

    private StudentCsvReader() { }

    public static List<Student> read(Path path) throws IOException {
        List<Student> students = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;
            boolean headerChecked = false;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (line.isBlank()) continue;

                // Detecta y omite encabezado si la primera columna es "matricula" (con o sin acento)
                if (!headerChecked) {
                    headerChecked = true;
                    String[] firstTokens = splitCsvLine(line);
                    if (firstTokens.length == 4) {
                        String c0 = firstTokens[0].trim().toLowerCase();
                        if ("matricula".equals(c0) || "matrícula".equals(c0)) {
                            // Es encabezado, pasa a la siguiente línea
                            continue;
                        }
                    }
                }

                String[] tokens = splitCsvLine(line);
                if (tokens.length != 4) {
                    throw new IllegalArgumentException("Formato CSV inválido en la línea " + lineNumber +
                            ": se esperaban 4 columnas separadas por coma.");
                }

                String matricula = tokens[0].trim();
                String primerApellido = tokens[1].trim();
                String segundoApellido = tokens[2].trim();
                String nombres = tokens[3].trim();

                if (matricula.isEmpty() || primerApellido.isEmpty() || segundoApellido.isEmpty() || nombres.isEmpty()) {
                    throw new IllegalArgumentException("Campos vacíos en la línea " + lineNumber + ".");
                }

                students.add(new Student(matricula, primerApellido, segundoApellido, nombres));
            }
        }

        if (students.isEmpty()) {
            throw new IllegalArgumentException("El archivo no contiene registros de alumnos.");
        }

        return students;
    }

    // Simple split por coma. Asume que no hay comas dentro de los campos.
    private static String[] splitCsvLine(String line) {
        return line.split(",", -1); // -1 para conservar vacíos si los hubiera
    }
}