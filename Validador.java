import java.util.ArrayList;

public class Validador {
    public static boolean validarReceta(Receta r) {
        boolean res = (r!=null);
        if (res) {
            String titulo = r.getTitulo();
            ArrayList<Ingrediente> i = r.getIngredientes();
            ArrayList<String> pasos = r.getPasos();
            try {
                emptyNullWord(titulo);
                if (!res) {
                    res = (titulo.length()>4) && (titulo.length()<80);
                    res = res && (i.size()>=2);
                    res = res && (pasos.size()>=2);
                }
            } catch (Exception e) {
                res = false;
            }
        }
        return res;
    }

    public static void emptyNullWord(String word) throws Exception {
        if (((word==null) || word.isEmpty())) {
            throw new Exception("void argument");
        }
    }

    public static boolean matched(String word) {
        try {
            emptyNullWord(word);
            return word.matches("[+-]?\\d*(\\.\\d+)?");
        } catch (Exception e) {}
        return false;
    }

    public static boolean validarIngrediente(Ingrediente ing) throws Exception {
        boolean res = (ing!=null);
        if (res)
            emptyNullWord(ing.getNombre());
        res = res && (ing.getCantidad()!=0);
        return res;
    }
}