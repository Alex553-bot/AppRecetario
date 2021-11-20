import java.io.*;
import java.util.*;

public class Receta {
    private String nombre;
    private String descripcion;
    private ArrayList<Ingrediente> ingredientes;
    private HashMap<Integer, String> pasos;

    public Receta(String nombre) throws Exception {
        if (nombre==null || nombre.isEmpty()) {
            throw new Exception("nombre invalido");
        }
        this.nombre = nombre;
    }
}