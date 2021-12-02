
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.AbstractCollection;

public class FrameBuscador extends JFrame{
    private JPanel main_panel;
    private LaminaBuscador shearch_panel;
    private AbstractCollection<PriorityRecipe> recetas;
    private JPanel title;
    private JScrollPane scroll;
    
    private final Toolkit myScreen = Toolkit.getDefaultToolkit();
    private final Icon titleIcon = new ImageIcon(myScreen.getImage(new File("").getAbsoluteFile()+"/assets/title2.png").getScaledInstance(200, 60, Image.SCALE_SMOOTH));
    
    private static Buscador buscador;
    
    public FrameBuscador(PriorityQueue<PriorityRecipe> recetas) {
        this.recetas = recetas;
        
        config();
    }
    
    public FrameBuscador(File receta) {
        PriorityRecipe r = new PriorityRecipe(0, receta);
        recetas = new PriorityQueue<PriorityRecipe>();
        recetas.add(r);
        
        config();
    }
    
    public FrameBuscador(ArrayList<File> recetas) {
        this.recetas = new PriorityQueue<PriorityRecipe>();
        for(File receta: recetas) {
            this.recetas.add(new PriorityRecipe(0, receta));
        }
        
        config();
    }
    
    private void config() {
        setBounds(0, 0, 500, 500);
        setIconImage(myScreen.getImage(new File("").getAbsoluteFile()+"/assets/icon2.png"));
        setTitle("CookBook");
        
        main_panel = new JPanel();
        main_panel.setLayout(new BorderLayout());
        shearch_panel = new LaminaBuscador(FrameBuscador.this);
        
        add(main_panel);  
        cargarRecetas();
        
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void cargarRecetas() {
        main_panel.removeAll();
        
        shearch_panel.setQueue((PriorityQueue<PriorityRecipe>)recetas);
        shearch_panel.write();
        
        title = new JPanel();
        title.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel titleLabel = new JLabel(titleIcon);
        title.add(titleLabel, gbc);
        title.setBackground(Color.WHITE);
        title.setPreferredSize(new Dimension(500, 60));
        
        scroll = new JScrollPane();
        scroll.setViewportView(shearch_panel);
        scroll.setPreferredSize(new Dimension(500, 500));
        
        main_panel.add(scroll, BorderLayout.CENTER);
        
        main_panel.add(title, BorderLayout.NORTH);
        
        main_panel.updateUI();
    }
    
    public void recargarRecetas() {
        main_panel.removeAll();
        
        main_panel.add(scroll, BorderLayout.CENTER);
        
        main_panel.add(title, BorderLayout.NORTH);
        
        main_panel.updateUI();
    }
    
    public void cargarReceta(PriorityRecipe receta) {
        main_panel.removeAll();
        
        
        JTextArea areaReceta = new JTextArea(receta.toString());
        areaReceta.setEditable(false);
        JScrollPane scroll   = new JScrollPane();
        scroll.setViewportView(areaReceta);
        scroll.setPreferredSize(new Dimension(450, 450));
        
        main_panel.add(scroll, BorderLayout.CENTER);
        
        JButton atras = new JButton("ATRAS");
        atras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                recargarRecetas();
            }
        });
        main_panel.add(atras, BorderLayout.SOUTH);
        
        main_panel.updateUI();
    }
    
    public static void main(String[] a) {
        
            Thread hilo = new Thread(){
                public void run() {
                    try{
                        new FrameBuscador(buscador.buscarRecetasPorIngredientes("aceite"));
                    }catch(Exception e){}
                }
            };
            hilo.start();
        
    }
}