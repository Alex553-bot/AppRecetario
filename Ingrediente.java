
public class Ingrediente {
    private String  identificador;
    private String  unidad;
    private int     cantidad;
    public Ingrediente(int c, String nombre, String uni) {
        identificador = nombre;
        unidad = uni;
        cantidad = c;
    }
}
