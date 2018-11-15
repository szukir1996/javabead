package elte.hu.eltecom.graph;

import elte.hu.eltecom.graph.exceptions.NodeNotFoundException;
import elte.hu.eltecom.user.User;
import java.util.List;
import java.util.LinkedList;

public class UnlimitedGraph implements Graph{

    private final List<Node> nodes;

    UnlimitedGraph(){
        this.nodes = new LinkedList<>();
    }

    private Node findNode(User user){
        for(int i=0;i<nodes.size();i++){
            if(nodes.get(i).getValue().equals(user)){
                return nodes.get(i);
            }
        }	

        return null;
    }

    @Override
    public void addNode(User user){
        this.nodes.add(new Node(user));
    }

    @Override
    public void removeNode(User user) throws NodeNotFoundException{
        Node node = findNode(user);
        if(node != null){
            this.nodes.remove(node);
        }else{
            throw new NodeNotFoundException(user.getId());
        }

        for(int i=0;i<nodes.size();i++){
            nodes.get(i).removeNeighbor(node);
        }
    }

    @Override
    public void linkNodes(User thisUser, User thatUser){
        Node thisNode = findNode(thisUser);
        Node thatNode = findNode(thatUser);
        if(thisNode != null &&  thatNode != null){
            thisNode.addNeighbor(thatNode);
        }
    }

    @Override
    public int getNodeNumber(){
        return this.nodes.size();
    }
}