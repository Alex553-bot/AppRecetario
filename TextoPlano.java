import java.util.ArrayList;
import java.io.*;
import java.util.List;

class TextoPlano {
    private FileReader        reader;
    private FileWriter        writer;
    private BufferedReader    bReader;
    private PrintWriter       pWriter;
    private File              file;

    /*public ArrayList<String[]> read(String path) {
        ArrayList<String[]> lista = new ArrayList<>();

        try {
            file = new File(path);
            reader = new FileReader(file);
            bReader = new BufferedReader(reader);
            String linea;
            String aux[];
            linea=bReader.readLine();
            linea = bReader.readLine();
            while (linea!=null) {
                if(isCSV(path))     aux = linea.split(",");
                else                aux = linea.split("\t");
                linea = bReader.readLine();
                lista.add(aux);
            } 
        } catch (FileNotFoundException e) {
            System.out.println("no existe el archivo");
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            cerrar();
        }
        return lista;
    }*/
    // problema al escribir debe ser serializado?, si es un objeto no creado
    // al ser un problema de que solo podemos usar caracteres dentro del archivo de texto plano entonces tendra que ser String
    public void write(String path, ArrayList<String[]> d, boolean append, String[] eti) {
        file = new File(path);
        try {
            writer = new FileWriter(path, append);
            pWriter = new PrintWriter(writer);
            String aux, ext;
            aux = "N";
            ext = "\t";
            for (int i=0; i<eti.length;i++) {
                aux +=ext+eti[i];
            }
            pWriter.println(aux);
            for (int i = 0; i<d.size(); i++) {
                aux="";
                for (int j = 0; j<d.get(i).length; j++) {
                    aux += ext +d.get(i)[j].toString(); 
                }
                pWriter.println(i+aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
    }

    public void add(String p, ArrayList<String[]> d) {
        file = new File(p);
        try{
            writer = new FileWriter(p, true);
            pWriter = new PrintWriter(writer);
            String aux, ext;
            aux = "";
            ext = "\t";
            for (int i = 0; i<d.size(); i++) {
                for (int j = 0; j<d.get(i).length; j++) {
                    aux += ext +d.get(i)[j].toString(); 
                }
                pWriter.println(i+ext+d.get(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cerrar() {
        try{
            if(writer!=null)     writer.close();
            if(reader!=null)     reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}