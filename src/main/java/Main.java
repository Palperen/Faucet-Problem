import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.ListenableGraph;
import org.jgrapht.alg.flow.EdmondsKarpMFImpl;
import org.jgrapht.alg.flow.MaximumFlowAlgorithmBase;
import org.jgrapht.alg.interfaces.MinimumSTCutAlgorithm;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.graph.WeightedMultigraph;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
public class Main {
    public static int yazdir=0;
    public Main() {
        Window a = new Window();
        a.setVisible(true);
    }
    public static DirectedWeightedMultigraph<String, MyEdge> directedGraph = new DirectedWeightedMultigraph<>(MyEdge.class);
    public static DirectedWeightedMultigraph<String, MyEdge> maxflowgraph = new DirectedWeightedMultigraph<>(MyEdge.class);
    public static DirectedWeightedMultigraph<String,DefaultWeightedEdge> minCutgraph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
    public static void showmaxflow(String baslangic, String hedef){
        JFrame max_flow_frame = new JFrame("Max Flow");
        EdmondsKarpMFImpl<String, MyEdge> ek = new EdmondsKarpMFImpl<>(Main.maxflowgraph);
        Map<MyEdge,Double> map = new HashMap<>();
        map = ek.getMaximumFlow(baslangic, hedef).getFlowMap();
        map.forEach((key, value) -> maxflowgraph.setEdgeWeight(key,value));
        JGraphXAdapter<String, MyEdge> maxadapter = new JGraphXAdapter<String, MyEdge>(maxflowgraph);
        mxIGraphLayout layout = new mxCircleLayout(maxadapter);
        layout.execute(maxadapter.getDefaultParent());
        max_flow_frame.add(new mxGraphComponent(maxadapter));
        max_flow_frame.pack();
        max_flow_frame.setLocationByPlatform(true);
        max_flow_frame.setVisible(true);
    }
    public static void createAndShowGui() {
        JFrame frame = new JFrame("DemoGraph");
        JGraphXAdapter<String, MyEdge> graphAdapter = new JGraphXAdapter<String, MyEdge>(directedGraph);
        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());
        frame.add(new mxGraphComponent(graphAdapter));
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    public static class MyEdge extends DefaultWeightedEdge {
        @Override
        public String toString() {
            return String.valueOf(getWeight());
        }
    }
    public static void main(String[] args) {
        Main main = new Main();
    }
}