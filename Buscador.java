import java.io.*;
import java.util.*;
import visual.*;
import javax.swing.JOptionPane;

public class Buscador {
    private File main_file;
    private File[] listaArchivos;
    
    public Buscador(String absoluteRoute) {
        main_file = new File(absoluteRoute);
        listaArchivos = main_file.listFiles();
    }
    
    public int contarIngredientesContenidos(File receta, String[] ingredientes) {
        int contador = 0;
        try{       
            FileReader file       = new FileReader(receta);
            BufferedReader lector = new BufferedReader(file);
            String linea = lector.readLine();
            
            while (!Normalizador.standardize(linea).equals("ingredientes")) {
                linea = lector.readLine();
            }
            
            linea = lector.readLine();
            
            while (!Normalizador.standardize(linea).equals("preparacion")) {
                contador += contar(linea, ingredientes);
                linea = lector.readLine();
            }
            
            lector.close();
            file.close();
        }catch(IOException io) {
            io.printStackTrace();
        }
        return contador;
    }
    
    private int contar(String linea, String[] ingredientes) {
        int contador = 0;
        for(String ingrediente: ingredientes) {
            if(Normalizador.standardize(linea).contains(Normalizador.standardize(ingrediente))) {
                contador++;
            }
        }
        return contador;
    }
    
    public int contarIngredientesContenidos(File receta, String ingrediente) {
        String[] arr = {ingrediente};
        return contarIngredientesContenidos(receta, arr);
    }
    
    //**25.11.2021
    /**
     * @param nombre debe tener la siguiente estructura; "NombreReceta" 
     */
    public File buscarReceta(String nombre) throws Exception {
        if (nombre.isEmpty()) {
            throw new Exception("String is empty... ");
        }
        nombre = Normalizador.deleteSpaces(nombre);
        for(File file: listaArchivos) {
            if (file.getName().equals(Normalizador.standardize(nombre + ".txt"))) {
                //new FrameBuscador(file);
                return file;    
            }
        }
        return null;
    }
    
    //**
    /**
     * @param recibe uno o mas ingredientes
     * @return retorna un archivo (File) o null si no encuentra la receta
     */
    public File buscarRecetaPorIngredientes(String... ingredientes) throws Exception {
        ingredientes = Normalizador.deleteVoid(ingredientes);
        for (File file: listaArchivos) {
            if(contarIngredientesContenidos(file, ingredientes) == ingredientes.length) {
                //new FrameBuscador(file);
                return file;
            }
        }
        return null;
    }
    
    /**
     * @param recibe uno a mas ingredientes
     * @return retorna una cola de prioridad de recetas
     */
    public PriorityQueue<PriorityRecipe> buscarRecetasPorIngredientes(String... ingredientes) throws Exception {   
        ingredientes = Normalizador.deleteVoid(ingredientes);
        PriorityQueue<PriorityRecipe> recetas = new PriorityQueue<PriorityRecipe>(Collections.reverseOrder());
        for (File file: listaArchivos) {
            int ocurrencia = contarIngredientesContenidos(file, ingredientes);
            if (ocurrencia > 0) {
                PriorityRecipe receta = new PriorityRecipe(ocurrencia, file);
                recetas.add(receta);
            }
        }       
        //new FrameBuscador(recetas);
        return recetas;
    }
    
    
    //23.11.2021
    /**
     * @param recibe uno o mas ingredientes
     * @return retorna una lista con los archivos encontrados
     */
    public ArrayList<File> buscarRecetaSinIngredientes(String... ingredientes) {
        ArrayList<File> recetas =  new ArrayList<File>();
        for (File file: listaArchivos) {
            if (contarIngredientesContenidos(file, ingredientes) == 0) {
                recetas.add(file);
            }
        }
        //new FrameBuscador(recetas);
        return recetas;
    }
    
    /**
     * @param debe tener la siguiente esructura; "Torta sin chocolate amargo"
     * @return retorna una lista de archivos
     */
    public ArrayList<File> recetasSinIngredienteEspecifico(String ingredientes) throws Exception {
        return obtenerRecetas(ingredientes, true);
    }
    
    
    /**
     * @param debe tener la siguiente esructura; "Torta con chocolate amargo"
     * @return retorna una lista de archivos
     */
    public ArrayList<File> recetasConIngredienteEspecifico(String ingredientes) throws Exception {
        return obtenerRecetas(ingredientes, false);
    }
    
    private ArrayList<File> obtenerRecetas(String ingredientes, boolean tipo) throws Exception {
        ArrayList<File> recetas = new ArrayList<File>();
        String[] array = ingredientes.split(" ");
        
        if (array.length < 3 || array.length > 4) {
            //JOptionPane.showMessageDialog(null, "EL SISTEMA FALLO EXITOSAMENTE...");
            throw new Exception("Insufficient Array Size ...");
        }
        
        array = Normalizador.standardizeArray(array);
        
        for (File file: listaArchivos) {
            if (file.getName().contains(array[0])) {
                String ingrediente = (array.length > 3) ? array[2] + array[3] : array[2];
                if(tipo){
                    if(contarIngredientesContenidos(file, ingrediente) == 0) {
                        recetas.add(file);
                    }
                }else{
                    if(contarIngredientesContenidos(file, ingrediente) > 0) {
                        recetas.add(file);
                    }
                }
            }
        }
        //new FrameBuscador(recetas);
        return recetas;
    }
    
    /**
     * @param recibe un nombre de receta
     * @return retorna una lista de archivos que hacen referencia al nombre ingresado
     */
    public ArrayList<File> sugerirRecetas(String receta) throws Exception{
        String[] array = receta.split(" ");
        array = Normalizador.standardizeArray(array);
        ArrayList<File> recetas = new ArrayList<File>();
        array[0] = Normalizador.deletePlural(array[0]);
        for(File file: listaArchivos) {
            if(!file.getName().equals(Normalizador.deleteSpaces(receta) + ".txt") 
                    && file.getName().contains(array[0])) {
                recetas.add(file);
            }
        }
        //new FrameBuscador(recetas);
        return recetas;
    }
}