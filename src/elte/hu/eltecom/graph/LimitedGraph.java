package elte.hu.eltecom.graph;

import elte.hu.eltecom.graph.exceptions.NodeNotFoundException;
import elte.hu.eltecom.graph.exceptions.UnsupportedGraphOperation;
import elte.hu.eltecom.user.User;
import java.util.ArrayList;

public class LimitedGraph implements PrintableGraph{

    private final User[] nodes;
    private final boolean[][] edges;

    LimitedGraph(int maxNodes){
        this.nodes = new User[maxNodes];
        this.edges = new boolean[maxNodes][maxNodes];
    }

    private Integer findNode(User user){
        for(int i=0;i<nodes.length;i++){
            if(nodes[i] != null && nodes[i].equals(user)){
                return i;
            }
        }
        return null;
    }

    @Override
    public void addNode(User user) throws UnsupportedGraphOperation{
        boolean isFull = true;
        for(int i=0;i<nodes.length;i++){
            if(nodes[i] == null){
                isFull = false;
                nodes[i] = user;
                break;
            }
        }
        if(isFull){
            throw new UnsupportedGraphOperation("Cannot add more node");
        }
    }

    @Override
    public void removeNode(User user) throws NodeNotFoundException{
            Integer userIndex = findNode(user);
            if(userIndex != null){
                nodes[userIndex] = null;
            }else{
                throw new NodeNotFoundException(userIndex);
            }

            for(int i=0;i<nodes.length;i++){
                    edges[i][userIndex] = false;
                    edges[userIndex][i] = false;
            }
    }

    @Override
    public void linkNodes(User thisUser, User thatUser){
            Integer thisUserIndex = findNode(thisUser);
            Integer thatUserIndex = findNode(thatUser);

            if(thisUserIndex != null && thatUserIndex != null){
                    edges[thisUserIndex][thatUserIndex] = true;
            }
    }

    @Override
    public int getNodeNumber(){
        int amount = 0;
        for(User user : nodes){
            if(user != null){
                amount++;
            }
        }
        return amount;
    }

    @Override
    public String print(){
        StringBuilder str = new StringBuilder();
        ArrayList<Integer> validNodes = new ArrayList<>();
        for (int i=0;i<nodes.length;i++) {
            if (nodes[i] != null) {
                validNodes.add(i);
                str.append(nodes[i].toString()).append("\n");
            }
        }
        for(int i=0;i<validNodes.size();i++){
            for(int j=0;j<validNodes.size();j++){
                str.append(edges[validNodes.get(i)][validNodes.get(j)]).append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }

}