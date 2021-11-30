import java.util.ArrayList;
public class Receta {
    private String                      titulo;
    private ArrayList<Ingrediente>      ingredientes;
    private ArrayList<String>           pasos;
    public Receta(String t) {
        titulo = t;
    }
}