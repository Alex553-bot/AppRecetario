package visual;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.text.*;
import java.io.*;

import javax.swing.*;

import tools.*;


public class LaminaBuscador extends JPanel{
    private PriorityQueue<PriorityRecipe> recetas;
    private ArrayList<PriorityRecipe> listaRecetas;
    private FrameBuscador frame;
    /**
    * n -> numero de recetas encontradas
    * (int)(Math.ceil(n/2.0)*200);
    */
    public LaminaBuscador(FrameBuscador frame) {
        setBackground(Color.WHITE);
        this.recetas = new PriorityQueue<PriorityRecipe>();
        this.listaRecetas = new ArrayList<PriorityRecipe>();
        this.frame = frame;
    }
    
    public void setQueue(PriorityQueue<PriorityRecipe> recetas) {
        this.recetas = recetas;
    }

    public void write() {
        PriorityQueue<PriorityRecipe> aux = new PriorityQueue<PriorityRecipe>(Collections.reverseOrder());
        int width,height;
        setPreferredSize(new Dimension(460,recetas.size()*210));
        width = 250;
        height = 200;
        if(recetas.size() == 1) {
            height = 300;
        }
        int posicion = 0;
        while(!recetas.isEmpty()) {
            PriorityRecipe r = recetas.poll();
            aux.add(r);
            listaRecetas.add(r);
            String receta = r.getTituloIngredientes();
            
            JTextArea label = new JTextArea(receta);
            label.setEditable(false);
            label.addMouseListener(new Oyente(label, posicion));
            JScrollPane scroll = new JScrollPane();
            scroll.setViewportView(label);
            scroll.setPreferredSize(new Dimension(width, height));

            posicion = posicion + 1;
            add(scroll);
        }
        recetas = aux;
        if (recetas.isEmpty()) {
            JLabel mensaje = new JLabel("NOT RECIPES FOUND...", SwingConstants.CENTER);
            mensaje.setFont(new Font("Arial", Font.BOLD, 20));
            mensaje.setPreferredSize(new Dimension(450, 50));
            JLabel mensaje2 = new JLabel("TRY ANOTHER OPTION :)", SwingConstants.CENTER);
            mensaje2.setFont(new Font("Arial", Font.BOLD, 20));
            mensaje2.setPreferredSize(new Dimension(450, 50));
            String r = new File("").getAbsolutePath();
            Toolkit screen = Toolkit.getDefaultToolkit();
            Image img = screen.getImage(r + "/assets/chefException.png");
            Icon icono = new ImageIcon(img.getScaledInstance(400, 290, Image.SCALE_SMOOTH));
            JLabel ic = new JLabel(icono);
            add(mensaje);add(mensaje2);add(ic);
        }
    }
    
    private class Oyente implements MouseListener {
        private Component c;
        private int posReceta;
        
        public Oyente(Component c, int posReceta) {
            this.c = c;
            this.posReceta = posReceta;
        }
        
        public void mouseClicked(MouseEvent e){
            c.setBackground(Color.WHITE);
            frame.cargarReceta(listaRecetas.get(posReceta));
        }
        public void mouseEntered(MouseEvent e){
            c.setCursor(new Cursor(12));
            c.setBackground(new Color(192, 192, 192));
        }
        public void mouseExited(MouseEvent e){
            c.setBackground(Color.WHITE);
        }
        public void mousePressed(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
    }
}