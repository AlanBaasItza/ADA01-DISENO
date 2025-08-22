# ADA01-DISENO
## Compilación
1) Crear carpetas de salida (si no existen):
   - Windows (PowerShell):<br>
     mkdir build, dist
   - Linux/Mac:<br>
     mkdir -p build dist

2) Compilar:
   javac -d build src/*.java

## Empaquetado (crear JAR ejecutable)
Utilizar el comando:
jar --create --file dist/programa.jar --main-class App -C build .

## Ejecución
- Pasando la ruta del CSV como argumento:
  java -jar dist/programa.jar alumnos.csv

- Si no pasas ruta, el programa te la pedirá por consola.

