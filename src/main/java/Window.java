import org.jgrapht.ListenableGraph;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jgrapht.ListenableGraph;
import org.jgrapht.alg.flow.EdmondsKarpMFImpl;
import org.jgrapht.alg.interfaces.MinimumSTCutAlgorithm;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import com.mxgraph.layout.*;
import com.mxgraph.swing.*;
import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.ListenableDirectedWeightedGraph;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Window extends JFrame {
    private final Dimension window_dimension = new Dimension(1280,980);
    private final Dimension button_dimension = new Dimension(80,64);
    private final Dimension text_dimension = new Dimension(256,64);
    private JTextField text_field;
    private JButton buttonadd;
    private JTextField text_dugumismi;
    private JButton text_dugumekle;
    private JTextField dugumbaslangic_ekle;
    private JTextField dugumhedef_ekle;
    private JTextField kapasite_ekle;
    private JButton kenar_ekle;
    private JButton graph_goster;
    private JTextField baslangic_text;
    private JTextField hedef_text;
    private JButton max_button;
    private JButton min_button;
    private JFrame graph_window;
    private JLabel sonuc_label;
    private JLabel minCut_sonuc_label;
    private JGraphXAdapter<String, DefaultEdge> jgxAdapter;
    public Window(){
        super("Yazlab");
        initGui();
    }
    private void initGui(){
        JLabel labelM = new JLabel("Dugum ismi:");
        JLabel baslangic_label = new JLabel("Baslangic:");
        JLabel hedef_label = new JLabel("Hedef:");
        JLabel kapasite = new JLabel("Kapasite:");
        JLabel hesapla_baslangic = new JLabel("Baslangic:");
        JLabel hesapla_hedef = new JLabel("Hedef:");
        sonuc_label = new JLabel();
        minCut_sonuc_label = new JLabel();
        graph_window = new JFrame("Graph");
        graph_window.setSize(window_dimension);
        graph_window.setLocationRelativeTo(null);
        setSize(window_dimension);
        setLocationRelativeTo(null);
        text_dugumismi = new JTextField();
        text_dugumekle = new JButton("Dugum ekle");
        dugumbaslangic_ekle = new JTextField();
        dugumhedef_ekle = new JTextField();
        kapasite_ekle = new JTextField();
        kenar_ekle = new JButton("Kenar ekle");
        baslangic_text = new JTextField();
        hedef_text = new JTextField();
        max_button = new JButton("Max Flow");
        min_button = new JButton("Min Cut");
        graph_goster = new JButton("Graph\n Goster");
        labelM.setBounds(20, 100, 150, 30);
        baslangic_label.setBounds(20,200,80,30);
        hedef_label.setBounds(225,200,80,30);
        kapasite.setBounds(425,200,80,30);
        hesapla_baslangic.setBounds(220,500,80,30);
        hesapla_hedef.setBounds(520,500,80,30);
        sonuc_label.setBounds(360,650,300,30);
        minCut_sonuc_label.setBounds(360,700,300,100);
        getContentPane().setBackground(Color.BLUE);
        sonuc_label.setBackground(Color.white);
        sonuc_label.setOpaque(true);
        minCut_sonuc_label.setBackground(Color.white);
        minCut_sonuc_label.setOpaque(true);
        graph_goster.setBounds(900,350,120,120);
        text_dugumismi.setSize(text_dimension);
        text_dugumismi.setBounds(100, 100, 100, 30);
        text_dugumekle.setSize(button_dimension);
        text_dugumekle.setBounds(225,100,120,30);
        dugumbaslangic_ekle.setBounds(100,200,100,30);
        dugumhedef_ekle.setBounds(300,200,100,30);
        kapasite_ekle.setBounds(500,200,100,30);
        kenar_ekle.setBounds(625,200,120,30);
        baslangic_text.setBounds(300,500,100,30);
        hedef_text.setBounds(600,500,100,30);
        min_button.setBounds(350,575,120,30);
        max_button.setBounds(550,575,120,30);
        text_dugumekle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.directedGraph.addVertex(text_dugumismi.getText());
                Main.maxflowgraph.addVertex(text_dugumismi.getText());
                Main.minCutgraph.addVertex(text_dugumismi.getText());
            }
        });
        graph_goster.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.createAndShowGui();
            }
        });
        kenar_ekle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.MyEdge e = new Main.MyEdge();
                Main.directedGraph.addEdge(dugumbaslangic_ekle.getText().toString(), dugumhedef_ekle.getText().toString(), e);
                Main.directedGraph.setEdgeWeight(e, Integer.parseInt(kapasite_ekle.getText().toString()));
                Main.MyEdge ew = new Main.MyEdge();
                Main.maxflowgraph.addEdge(dugumbaslangic_ekle.getText().toString(), dugumhedef_ekle.getText().toString(), ew);
                Main.maxflowgraph.setEdgeWeight(ew, Integer.parseInt(kapasite_ekle.getText().toString()));
                DefaultWeightedEdge em =new DefaultWeightedEdge();
                Main.minCutgraph.addEdge(dugumbaslangic_ekle.getText().toString(), dugumhedef_ekle.getText().toString(), em);
                Main.minCutgraph.setEdgeWeight(em, Integer.parseInt(kapasite_ekle.getText().toString()));
            }
        });
        max_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EdmondsKarpMFImpl<String, Main.MyEdge> ek = new EdmondsKarpMFImpl<>(Main.directedGraph);
                sonuc_label.setText("Max Flow sonucu: " + String.valueOf(ek.calculateMaximumFlow(baslangic_text.getText(),hedef_text.getText())));
                //DirectedWeightedMultigraph<String, Main.MyEdge> maxflow_graph = new DirectedWeightedMultigraph<>(Main.MyEdge.class);
                //ek.getMaximumFlow(baslangic_text.getText(), hedef_text.getText());
                Main.showmaxflow(baslangic_text.getText(), hedef_text.getText());
            }
        });
        min_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MinimumSTCutAlgorithm<String,DefaultWeightedEdge> minCut=new EdmondsKarpMFImpl<String, DefaultWeightedEdge>(Main.minCutgraph);
                minCut.calculateMinCut(baslangic_text.getText(), hedef_text.getText());
                minCut_sonuc_label.setText("Min Cut : " +minCut.getCutEdges());
            }
        });
        add(labelM);
        add(baslangic_label);
        add(hedef_label);
        add(kapasite);
        add(hesapla_baslangic);
        add(hesapla_hedef);
        add(graph_goster);
        add(baslangic_text);
        add(hedef_text);
        add(min_button);
        add(max_button);
        add(text_dugumismi);
        add(text_dugumekle);
        add(dugumbaslangic_ekle);
        add(dugumhedef_ekle);
        add(kapasite_ekle);
        add(kenar_ekle);
        add(sonuc_label);
        add(minCut_sonuc_label);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
