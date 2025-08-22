import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class CsvExporter {

    private static final String ASIGNATURA = "Diseno de Software";

    private CsvExporter() { }

    public static Path export(List<Student> students, Path outputPath) throws IOException {
        // Verificación de completitud
        for (Student s : students) {
            if (!s.tieneCalificacion()) {
                throw new IllegalStateException("No se pueden exportar calificaciones: faltan valores por capturar.");
            }
        }

        try (BufferedWriter bw = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8)) {
           
            for (Student s : students) {
                String row = s.getMatricula() + "," + ASIGNATURA + "," + s.getCalificacion();
                bw.write(row);
                bw.newLine();
            }
        } catch (UncheckedIOException e) {
            throw e.getCause();
        }

        return outputPath;
    }
}