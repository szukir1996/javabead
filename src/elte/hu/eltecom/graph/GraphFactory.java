package elte.hu.eltecom.graph;

public class GraphFactory{
	
    private GraphFactory(){
    }

    public static Graph initGraph(){
        return new UnlimitedGraph();
    }

    public static Graph initGraph(int graphSize){
        if(graphSize >= 1){
            return new LimitedGraph(graphSize);
        }

        return null;
    }
	
}