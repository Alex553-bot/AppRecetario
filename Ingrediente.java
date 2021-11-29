public class Ingrediente {
    private String  identificador;
    private String  unidad;
    private double  cantidad;
    public Ingrediente(double c, String uni, String nombre) 
    throws Exception
    {
        if(c==0)
            throw new Exception("cantidad negativa no permitida");
        identificador = nombre;
        unidad = uni;
        cantidad = Math.abs(c);
    }

    public Ingrediente(int c, String nombre) {
        cantidad = c;
        identificador = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Ingrediente) {
            Ingrediente i = (Ingrediente)o;
            boolean aux = (identificador.equals((i.getNombre())));
            if (aux && (unidad.equals(i.getUnidad())))
                absorverIngrediente(i);
            return aux;
        }
        return false;
    }

    private void absorverIngrediente(Ingrediente other) {
        cantidad += other.getCantidad();
    }

    public String getNombre() {
        return identificador;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getUnidad() {
        return unidad;
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