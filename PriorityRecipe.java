import java.io.*;
import java.util.PriorityQueue;

/**
 * Write a description of class Hola here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PriorityRecipe implements Comparable<PriorityRecipe>{
    private int ocurrencia;
    private File receta;
    
    public PriorityRecipe(int ocurrencia, File receta) {
        this.ocurrencia = ocurrencia;
        this.receta = receta;
    }

    public int compareTo(PriorityRecipe otro) {
        int res = 0;
        if (this.ocurrencia > otro.ocurrencia) {
            res = 1;
        }else if(this.ocurrencia < otro.ocurrencia) {
            res = -1;
        }
        return res;
    }

    public File getFile() {
        return receta;
    }
    
    public String toString() {
        String report = "\n";
        try {
            FileReader fReader = new FileReader(receta);
            BufferedReader lector = new BufferedReader(fReader);
            String linea;
            while((linea = lector.readLine()) != null) {
                report += "  "+ linea + "\n";
            }
            lector.close();
            fReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return report;
    }
    
    public int getOcurrencia() {
        return ocurrencia;
    }
    
    public String getTituloIngredientes() {
        String report = "\n";
        try {
            FileReader fReader = new FileReader(receta);
            BufferedReader lector = new BufferedReader(fReader);
            String linea;
            while(!Normalizador.standardize((linea = lector.readLine())).equals("procedimiento")) {
                report += "  "+ linea + "\n";
            }
            lector.close();
            fReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return report;
    }
}
