
import java.awt.Point;
import java.util.List;
import java.util.Random;

import pl.mgrproject.api.Edge;
import pl.mgrproject.api.Environment;
import pl.mgrproject.api.Graph;
import pl.mgrproject.api.plugins.Generator;


public class DefaultGenerator implements Generator {
    public Graph<Integer> graph;

    @Override
    public String getName() {
	return "Generator standardowy";
    }

    @Override
    public void generate(int n) {
	graph = new Graph<Integer>(n);
	Random r = new Random();
	int density = 5; //20%
	int maxEdgeWeight = 100;
	
	for (int i = 0; i < n; ++i) {
	    graph.addVertex(new Point(r.nextInt(Environment.getGraphPanelWidth()), r.nextInt(Environment.getGraphPanelHeight())));
	}
	
	List<Point> vertices = graph.getVertices();
	
	for (int i = 0; i < vertices.size(); ++i) {
	    for (int j = 0; j < vertices.size(); ++j) {
		//w drugim warunku sprawdzamy czy istnieje krawedz skierowana w druga strone
		if (i == j || graph.getEdges().contains(new Edge<Integer>(j, i))) continue;
		if (r.nextInt(100) < density) {
		    graph.addEdge(new Edge<Integer>(i, j, r.nextInt(maxEdgeWeight)+1));
		}
	    }
	}
	
	//sprawdzanie czy kazdy wierzcholek ma przynajmniej jedna krawedz wychodzaca i wchodzaca.
	//Jesli nie to odpowiednia krawedz jest tworzona losowo.
	List<Edge<Integer>> edges = graph.getEdges();
	for (int i = 0; i < vertices.size(); ++i) {
	    boolean out_exists = false;
	    boolean in_exists  = false;
	    for (Edge<Integer> e : edges) {
		if (e.first == i) out_exists = true;
		if (e.last  == i) in_exists  = true;
	    }
	    if (!out_exists) {
		int target = 0;
		do {
		    target = r.nextInt(vertices.size());
		} while(target == i);
		graph.addEdge(new Edge<Integer>(i, target, r.nextInt(maxEdgeWeight)+1));
	    }
	    if (!in_exists) {
		int source = 0;
		do {
		    source = r.nextInt(vertices.size());
		} while(source == i);
		graph.addEdge(new Edge<Integer>(source, i, r.nextInt(maxEdgeWeight)+1));
	    }
	}
    }

    @Override
    public Graph<?> getGraph() {
	return graph;
    }
    
}
