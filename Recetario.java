import java.io.*;
import java.util.ArrayList;

public class Recetario {
    private File directorio;
    public Recetario(String path) throws IOException {
        directorio = new File(path+"archivos/recetas/");
        if (!directorio.exists())
            directorio.mkdir();
    }

    public Recetario() {
        directorio = new File("archivos/recetas/");
        if (!directorio.exists())
            directorio.mkdir();
    }

    public boolean registrarReceta(Receta r) {
        if ((r!=null) && validarReceta(r)) {
            File f = GeneradorArchivos.crearReceta(r.getTitulo()
                        ,directorio.getAbsoluteFile());
            GeneradorArchivos.llenarReceta(r.toString(), f);
            return true;
        }
        return false;
    }

    public boolean validarReceta(Receta r) {
        boolean res = (r!=null);
        if (res) {
            String titulo = r.getTitulo();
            ArrayList<Ingrediente> i = r.getIngredientes();
            ArrayList<String> pasos = r.getPasos();
            try {
                Normalizador.emptyNullWord(titulo);
                res = (titulo.length()>4) && (titulo.length()<80);
                res = res && (i.size()>=2);
                res = res && (pasos.size()>=2);

            } catch (Exception e) {
                res = false;
            }
        }
        return res;
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

    public File getDir() {
        return directorio;
    }
}