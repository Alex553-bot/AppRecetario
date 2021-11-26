import java.util.ArrayList;

public class Validador {
    public boolean validarReceta(Receta r) {
        boolean res = (r!=null);
        if (res) {
            String titulo = r.getTitulo();
            ArrayList<Ingrediente> i = r.getIngredientes();
            ArrayList<String> pasos = r.getPasos();
            res = (titulo.length()>4) && (titulo.length()<80);
            res = res && (i.size()>=2);
            res = res && (pasos.size()>=2);
        }
        return res;
    }
}