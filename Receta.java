import java.util.ArrayList;
import java.util.HashMap;

public class Receta {
    private String                      titulo;
    private String                      descripcion;
    private ArrayList<Ingrediente>      ingredientes;
    private HashMap<Integer, String>    pasos;
    public Receta(String nombre) throws Exception {
        if ((nombre.isEmpty()) || (nombre==null)) {
            throw new Exception("nombre invalido");
        }
        titulo = nombre;
        ingredientes = new ArrayList<>();
        pasos = new HashMap();
    }
    
    public Receta(String nombre, String descr) {
        titulo = nombre;
        descripcion = descr;
    }
    
    public boolean agregarIngrediente(Ingrediente ing) {
        boolean res = (ing!=null);
        int i= 0;
        while (res && i<ingredientes.size()) {
            res = !(ing.equals(ingredientes.get(i)));
            i++;
        }
        if (res) {
            ingredientes.add(ing);
        }
        return res;
    }

    public void agregarProceso(int k, String proc) 
    throws Exception 
    {
        if(pasos.containsKey(k))
            throw new Exception("error al ingresar el proceso");
        pasos.put(k, proc);
    }
}