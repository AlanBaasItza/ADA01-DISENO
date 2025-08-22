public class Student {
    private final String matricula;
    private final String primerApellido;
    private final String segundoApellido;
    private final String nombres;
    private Integer calificacion; // null hasta que se capture

    public Student(String matricula, String primerApellido, String segundoApellido, String nombres) {
        this.matricula = matricula;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.nombres = nombres;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public String getNombres() {
        return nombres;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public boolean tieneCalificacion() {
        return calificacion != null;
    }

    public String getNombreCompleto() {
        return String.format("%s %s %s", primerApellido, segundoApellido, nombres).trim();
    }

    @Override
    public String toString() {
        return matricula + " - " + getNombreCompleto() + (tieneCalificacion() ? " (" + calificacion + ")" : "");
    }
}