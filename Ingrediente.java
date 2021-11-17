
public class Ingrediente {
    private String  identificador;
    private String  unidad;
    private int     cantidad;
    public Ingrediente(int c, String nombre, String uni) {
        identificador = nombre;
        unidad = uni;
        cantidad = c;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Ingrediente) {
            return (identificador
                .equals(((Ingrediente)o).getNombre()));
        }
        return false;
    }

    public String getNombre() {
        return identificador;
    }
}
