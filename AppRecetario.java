import tools.*;
import clases.*;
import java.util.Scanner;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;

/**
 * Write a description of class AppRecetario here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AppRecetario {
    private Recetario recetario;
    private Buscador buscador;
    private Scanner lector;
    private Receta newReceta;
    private Icon iconoBienvenida;

    private final Toolkit myScreen = Toolkit.getDefaultToolkit();
    
    public AppRecetario() {
        recetario = new Recetario();
        buscador  = new Buscador("./archivos/recetas/");
        lector    = new Scanner(System.in);
        iconoBienvenida = new ImageIcon(myScreen.getImage("./assets/icon2.png").getScaledInstance(50, 50, Image.SCALE_SMOOTH));
    }
    
    public void start() {
        int option = -1;
        boolean fin = false;
        Object[] ops = new Object[1];
        ops[0] = new JButton("Enter");
        JOptionPane.showMessageDialog(null, "   BIENVENIDO :)", "CookBook" , JOptionPane.DEFAULT_OPTION, iconoBienvenida);
        while(option != 4) {
            showMenu();
            System.out.print("Opcion: ");
            try{
                option = lector.nextInt();
                showOption(option);
            }catch(java.util.InputMismatchException e) {
                System.out.println("Error, digite un numero...!");
                option = -1;
                lector.nextLine();
            }catch(Exception ex){
                System.out.println("Error..." + ex.getMessage());
                option = -1;
                lector.nextLine();
            }
        }
    }
    
    private void showMenu() {
        System.out.println("\nCookBook\n" + 
                            " 1. Buscar Recetas\n" +
                            " 2. Agregar Receta\n" +
                            " 3. Ver todas las recetas\n" +
                            " 4. Salir");
    }
    
    private void showOption(int option) {
        switch(option) {
            case 1: shearchMenu();
            break;
            case 2: addMenu();
            break;
            case 3: 
                clearConsole();
                String cantidadRecetas = "|   Total Recetas: "+buscador.mostrarTodo().size() + "   |";
                String margen = "|";
                for(int i = 0; i  < cantidadRecetas.length() - 2; i++) {
                    margen += "*";
                }
                margen += "|";
                System.out.println(margen);
                System.out.println(cantidadRecetas);
                System.out.println(margen);
            break;
            case 4: JOptionPane.showMessageDialog(null, "   HASTA LA PROXIMA :)", "CookBook" , JOptionPane.DEFAULT_OPTION, iconoBienvenida);
            break;
            default:
            System.out.println("Opcion no valida...");
        }
    }
    
    private void shearchMenu() {
        clearConsole();
        int option = -1;
        while(option != 5) {
            System.out.println("\nCookBook\n" + 
                                " 1. Buscar Receta por nombre\n" + 
                                " 2. Buscar Receta por ingredientes\n" + 
                                " 3. Buscar Receta sin ingredientes\n" +
                                " 4. Buscar Receta con ingrediente especifico\n" +
                                " 5. Atras");
            System.out.print("Opcion: ");
            try{
                option = lector.nextInt();
                option = actShearch(option);
            }catch(java.util.InputMismatchException e) {
                System.out.println("Error, digite un numero...!");
                option = -1;
                lector.nextLine();
            }catch(Exception ex){
                System.out.println("Error..." + ex.getMessage());
                option = -1;
                lector.nextLine();
            }
        }
    }
    
    private void pedir(int type) {
        int i;
        String cad;
        ArrayList<String> ingredientes;
        String[] ings;
        cad = "";
        i = 1;
        ingredientes = new ArrayList<String>();
        clearConsole();
        try{
            while(i != -1) {
                System.out.print("Ingrese un ingrediente, o un numero para proceder con la busqueda: "+i+" ");
                cad = lector.nextLine();
                if(!Normalizador.matched(cad)){
                    ingredientes.add(cad);
                }else{
                    i = -2;
                }
                i++;
            }
            ings = new String[ingredientes.size()];
            i = 0;
            for(String item : ingredientes) {
                ings[i] = item;
                i++;
            }
            if (type == 1) {
                buscador.buscarRecetasPorIngredientes(ings);
            }else {
                buscador.buscarRecetaSinIngredientes(ings);
            }
        }catch (Exception e){
            System.out.println("Error..." + e.getMessage());
        }    
    }
    
    private int actShearch(int option) {
        clearConsole();
        lector.nextLine();
        int res = -1;
        res = option;
        String cad;
        switch(option) {
            case 1: 
                System.out.print("Ingrese el nombre de la receta: ");
                cad = lector.nextLine();
                try{
                    buscador.buscarReceta(cad);
                }catch (Exception e){
                    System.out.println("Error..." + e.getMessage());
                }
            break;
            
            case 2:
                pedir(1);
            break;
            
            case 3:
                pedir(2);
            break;
            
            case 4:
                cad = "";
                try{
                    System.out.print("Ingrese la receta: ");
                    cad = lector.nextLine();
                    buscador.recetasConIngredienteEspecifico(cad);
                }catch (Exception e){
                    System.out.println("Error..." + e.getMessage());
                }
            break;
            
            case 5:
                res = 5;
            break;
            
            default: System.out.println("Opcion no valida");
        }
        return res;
    }
    
    private void addMenu() {
        lector.nextLine();
        String cad = "";
        String guardar = "";
        while(!guardar.equalsIgnoreCase("guardar")) {
            createRecipe();
            ingresarIngredientes();
            ingresarPreparacion();
            try{
                int random = 1;
                while(random != 6) {
                    Thread.sleep(500);
                    System.out.print(".");
                    random++;
                }                   
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                if(recetario.registrarReceta(newReceta)) {
                    System.out.println("\nReceta Guardada...");
                }else{
                    System.out.println("\nLa Receta no se pudo Guardar...");
                }
                Thread.sleep(1000);
            }catch(Exception e) {
                System.out.println("Error..." + e.getMessage());
            }finally{
                buscador  = new Buscador("./archivos/recetas/");
                guardar   = "guardar";
            }                
        }
    }
    
    private void createRecipe() {
        try{
            System.out.print("Nombre de la receta: ");
            String name = lector.nextLine();
            System.out.print("Descripcion de la receta: ");
            String desc = lector.nextLine();
            newReceta = new Receta(name, desc);
        }catch(Exception e) {
            System.out.println("Error..." + e.getMessage());
        }
    }
    
    private void ingresarIngredientes() {
        try{
            int salir = 0;
            Ingrediente ing;
            while(salir != 2) {
                try{
                    clearConsole();
                    System.out.println("CREANDO INGREDIENTE");
                    System.out.print("Ingrese el ingrediente: ");
                    String name = lector.nextLine();
                    System.out.print("Ingrese la cantidad: ");
                    double cant = Double.parseDouble(lector.nextLine());
                    System.out.print("Ingrese la unidad o un punto si no la requiere: ");
                    String ud = lector.nextLine();
                    if(ud.equals(".")) {
                        ing = new Ingrediente((int)cant, name);
                    }else{
                        ing = new Ingrediente(cant, ud, name);
                    }
                    newReceta.agregarIngrediente(ing);
                    salir = repetir("ingrediente", "Continuar", salir);
                }catch (java.util.InputMismatchException e) {
                    System.out.println("Error..." + e.getMessage() + "\nPresione Enter para volver a ingresar");
                    lector.nextLine();
                }catch (Exception ex) {
                    System.out.println("Error..." + ex.getMessage() + "\nPresione Enter para volver a ingresar");
                    lector.nextLine();
                }
                
            }
        }catch(Exception e) {
            System.out.println("Error..." + e.getMessage());
        }
    }
    
    private void ingresarPreparacion() {
        try{
            int salir = 0;
            while(salir != 2) {
                clearConsole();
                System.out.println("CREANDO PROCEDIMIENTO");
                System.out.print("Ingrese el procedimiento: ");
                String proc = lector.nextLine();
                newReceta.agregarProceso(proc);
                salir = repetir("procedimiento", "Finalizar y Guardar", salir);
            }            
        }catch(Exception e) {
            System.out.println("Error..." + e.getMessage());
        }
    }

    private int repetir(String name, String message, int salir) {
        do {
            clearConsole();
            System.out.println("¿Quiere ingresar otro "+name+"?\n" +
                                " 1. SI\n" + 
                                " 2. " + message);
            System.out.print("Opcion: ");
            try{
                salir = lector.nextInt();
                if (salir != 1 && salir != 2) {
                    System.out.println("Opcion no valida...");
                    Thread.sleep(1000);
                }
            }catch(java.util.InputMismatchException e) {
                    System.out.println("Error, digite un numero...!");
                    salir = 0;
                    lector.nextLine();
            }catch(Exception ex) {
                    System.out.println("Error..." + ex.getMessage());
                    salir = 0;
                    lector.nextLine();
            }
        }while (salir != 1 && salir != 2);
        lector.nextLine();
        return salir;
    }
    
    private void clearConsole() {
        try{
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else{
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        AppRecetario app = new AppRecetario();
        app.start();
    }
}
