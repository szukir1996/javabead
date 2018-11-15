package elte.hu.eltecom.graph;

import elte.hu.eltecom.user.User;
import java.util.ArrayList;
import java.util.Collection;

public class Node {

	private User value;
	private Collection<Node> neighbors;
	
	public Node(User user){
            this.value = user;
            this.neighbors = new ArrayList<>();
	}
	
	public User getValue(){
            return this.value;
	}
	
	public void setValue(User newValue){
            this.value = newValue;
	}
	
	public void addNeighbor(Node neighborNode){
            this.neighbors.add(neighborNode);
	}
	
	public void removeNeighbor(Node neighborNode){
            this.neighbors.remove(neighborNode);
	}

}