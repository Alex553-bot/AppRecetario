import java.util.ArrayList;

public class Receta {
    private String                  titulo;
    private String                  descripcion;
    private ArrayList<Ingrediente>  ingredientes;
    public Receta(String nombre) {
        titulo = nombre;
        
    }
    public Receta(String nombre, String descr) {
        titulo = nombre;
        descripcion = descr;
    }
    
    public boolean agregarIngrediente(Ingrediente ing) {
        for (int i=0; i<ingredientes.size();i++) {
            ing.equals(ingredientes.get(i));
        }
        return true;
    }
}
