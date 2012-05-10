
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

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
	
	for (int i = 0; i < n; ++i) {
	    g.addVertex(new Point(Environment.getGraphPanelWidth(), Environment.getGraphPanelHeight()));
	}
	
	List<Point> vertices = g.getVertices();
	
	return g;
    }

}
