public class Ingrediente implements Comparable{
    private String  identificador;
    private String  unidad;
    private double  cantidad;

    public Ingrediente(double c, String uni, String nombre) throws Exception{
        Normalizador.emptyNullWord(nombre);
        if (c==0)
            throw new Exception("cantidad negativa no permitida");
        identificador = nombre;
        unidad = uni;
        cantidad = Math.abs(c);
    }

    public Ingrediente(int c, String nombre) {
        identificador = nombre;
        cantidad = c;
    }

    public int compareTo(Object o) {
        int res = 1;
        if (o instanceof Ingrediente) {
            Ingrediente aux = (Ingrediente)o;
            String id = aux.getNombre();
            if (aux.equals(this))
                return 0;
            if (identificador.equals(id)) {
                try {
                    String u = aux.getUnidad();
                    Normalizador.emptyNullWord(u);
                    if (u.equals(unidad))
                        res =0;
                } catch (Exception e) {
                    if (unidad==null || unidad.isEmpty())
                        res =0;
                }
            }
            if (res==0) 
                cantTotal(aux);
        }
        return res;
    }

    public void cantTotal(Ingrediente i) {
        cantidad += i.getCantidad();
    }

    public String getNombre() {
        return identificador;
    }

    public String getUnidad() {
        return unidad;
    }

    public double getCantidad() {
        return cantidad;
    }

    @Override 
    public String toString() {
        String cadena = "";
        if ((cantidad>1))
            if (!((unidad==null) || unidad.isEmpty()))
                cadena += cantidad +" "+ unidad+"s "+identificador;
            else 
                cadena += (int)(cantidad)+" "+identificador+"s";
        else {
            cadena = cantidad+" ";
            if (unidad==null || unidad.isEmpty())
                cadena += identificador;
            else 
                cadena += unidad + " "+identificador;
        }
        return cadena;
    }
}