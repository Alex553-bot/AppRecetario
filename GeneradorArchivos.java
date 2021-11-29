import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class GeneradorArchivos {
    public static File crearReceta(String titulo, File carpeta) { 
        File archivoReceta = null;
        if (carpeta.isDirectory()) {
            titulo = titulo.replaceAll("\\s+", "_");
            archivoReceta = new File(carpeta.getAbsolutePath()+"/"+titulo+".txt");
            try {
                archivoReceta.createNewFile();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return archivoReceta;
    }

    public static void llenarReceta(Receta r, File fichero) {
        try {
            FileWriter fr = new FileWriter(fichero, false);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(r.toString());
            br.close();
            fr.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void llenarReceta1(Receta r, File fichero) {
        if (Validador.validarReceta(r))
            try {
                FileWriter fr = new FileWriter(fichero, false);
                BufferedWriter br = new BufferedWriter(fr);
                br.write(r.toString());
                br.close();
                fr.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
    }
}