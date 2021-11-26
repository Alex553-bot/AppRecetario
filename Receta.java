import java.util.ArrayList;
import java.util.HashMap;

public class Receta {
    private String                      titulo;
    private String                      descripcion;
    private ArrayList<Ingrediente>      ingredientes;
    private ArrayList<String>           pasos;
    public Receta(String nombre) throws Exception {
        Normalizador.emptyNullWord(nombre);
        titulo = nombre;
        ingredientes = new ArrayList<>();
        pasos = new ArrayList<>();
    }

    public Receta(String nombre, String descr) throws Exception {
        Normalizador.emptyNullWord(nombre);
        titulo = nombre;
        descripcion = descr;
        ingredientes = new ArrayList<>();
        pasos = new ArrayList<>();
    }

    public boolean agregarIngrediente(Ingrediente ing) {
        if (verificarIngrediente(ing)) {
            ingredientes.add(ing);
            return true;
        }
        return false;
    }


    public boolean verificarIngrediente(Ingrediente ing) {
        boolean res = (ing!=null);
        int i = 0;
        while ((i<ingredientes.size()) && res) {
            res = !(ingredientes.get(i).equals(ing));
            i++;
        }
        return res;
    }

    public void agregarProceso(String paso) throws Exception {
        int i = 0;
        boolean res = false;
        paso = Normalizador.standardize(paso);
        while (!res && (i<pasos.size())) {
            res = pasos.get(i).equalsIgnoreCase(paso);
            i++;
        }
        if (res) 
            throw new Exception("error al ingresar el paso");
        pasos.add(paso);
    }

    public void agregarProceso1(String paso) throws Exception{
        boolean res = (paso==null);
        int i=0;
        Normalizador normalizador = new Normalizador();
        paso = normalizador.standardize(paso);
        while (!res && i<pasos.size()) {
            res = paso.equals(pasos.get(i));
            i++;
        }
        if (res) {
            throw new Exception("error al ingresar el paso");
        }
        pasos.add(paso);
    }

    public boolean verificarProceso(String paso) {
        try
        {
            Normalizador.emptyNullWord(paso);
            paso = Normalizador.standardize(paso);
            boolean res = true;
            int i=0;
            while ((i<pasos.size()) && res) {
                res = !(pasos.get(i).equals(paso));
                i++;
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean esValida() {
        boolean res = (titulo.length()>4) && (titulo.length()<80);
        res = res && (ingredientes.size()>=2);
        res = res && (pasos.size()>=2);
        return res;
    }

    @Override
    public String toString() {
        String formato = titulo+"\n";
        if (!(descripcion==null || descripcion.isEmpty()))
            formato += descripcion+"\n";
        else 
            formato += "\n";
        formato += "INGREDIENTES:";
        for (Ingrediente i: ingredientes)
            formato += "\n   -"+i.toString();
        formato += "\nPREPARACION:";
        for (int i=0; i<pasos.size();i++) 
            formato += "\n   "+i+".-"+pasos.get(i);
        return formato;
    }
    // edicion de un proceso dado 
    // el uso de los ... en un metodo hace referencia a que 
    // puede haber 0 o mas elementos del objeto 
    // en este caso se utiliza un iterable(no sabemos de que tipo),
    
    public String getTitulo() {
        return titulo;
    }
    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }
    public ArrayList<String> getPasos() {
        return pasos;
    }
}
