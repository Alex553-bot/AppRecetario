import java.io.*;

public class Recetario {
    private File directorio;
    public Recetario() {
        directorio = new File("Recetario/");
    }
    
    public boolean registrarReceta(Receta r) {
        boolean res = (r!=null);
        return res;
    }

    public File crearReceta(String nombre) {
        File recetaNueva = null;
        try {
            // el archivo esta dentro en la ubicacion relativa(en el proyecto)
            recetaNueva = new File("Recetario/"+nombre);
            recetaNueva.createNewFile();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return recetaNueva;
    }
    
    public boolean verificarReceta(Receta r) {
        return true;
    }
    
    public void escribirReceta(File f, Receta r) {
        try {
            FileWriter fr = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fr);
            bw.write(r.toString());
            bw.close();
            fr.close();
        } catch (IOException ioe) {}
    }
}