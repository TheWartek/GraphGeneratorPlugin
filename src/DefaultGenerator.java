
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.mgrproject.api.Environment;
import pl.mgrproject.api.Graph;
import pl.mgrproject.api.plugins.Generator;


public class DefaultGenerator implements Generator {

    @Override
    public String getName() {
	return "Generator standardowy";
    }

    @Override
    public Graph<?> getGraph(int n) {
	Graph<Integer> g = new Graph<Integer>(n);
	Random r = new Random();
	
	for (int i = 0; i < n; ++i) {
	    g.addVertex(new Point(r.nextInt(Environment.getGraphPanelWidth()), r.nextInt(Environment.getGraphPanelHeight())));
	}
	
	List<Point> vertices = g.getVertices();
	
	return g;
    }

}
