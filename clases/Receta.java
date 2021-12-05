package clases;

import java.util.ArrayList;
import tools.Normalizador;

public class Receta {
    private String                      titulo;
    private String                      descripcion;
    private ArrayList<Ingrediente>      ingredientes;
    private ArrayList<String>           pasos;

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
            res = (ingredientes.get(i).compareTo(ing)!=0);
            i++;
        }
        return res;
    }

    public void agregarProceso(String paso) throws Exception{
        if (!verificarProceso(paso)) {
            throw new Exception("error al ingresar el paso");
        }
        pasos.add(paso);
    }

    public boolean verificarProceso(String paso) {
        try {
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

    @Override
    public String toString() {
        String formato = titulo+"\n";
        if (!(descripcion==null || descripcion.isEmpty()))
            formato += descripcion+"\n";
        else 
            formato += "\n";
        formato += "INGREDIENTES";
        for (Ingrediente i: ingredientes)
            formato += "\n   - "+i.toString();
        formato += "\nPREPARACION";
        for (int i=0; i<pasos.size();i++) 
            formato += "\n   "+(i+1)+".- "+pasos.get(i);
        return formato;
    }
    // edicion de un proceso dado 
    // el uso de los ... en un metodo hace referencia a que 
    // puede haber 0 o mas elementos del objeto 
    // en este caso se utiliza un iterable(no sabemos de que tipo),

    public void setIngredientes(ArrayList<Ingrediente> lista) {
        if (lista!=null)
            ingredientes = lista;
    }

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
