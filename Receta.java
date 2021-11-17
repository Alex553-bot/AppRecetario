import java.util.ArrayList;
import java.util.HashMap;

public class Receta {
    private String                      titulo;
    private String                      descripcion;
    private ArrayList<Ingrediente>      ingredientes;
    private HashMap<Integer, String>    pasos;
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

    public void agregarProceso(int k, String proc) 
    throws Exception 
    {
        if(pasos.containsKey(k))
            throw new Exception("hubo un error con el proceso");
        pasos.put(k, proc);
    }
}
