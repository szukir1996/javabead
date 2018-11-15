package elte.hu.eltecom.graph.exceptions;

public class NodeNotFoundException extends Exception{

    public NodeNotFoundException(int userID){
        super("Node with id: " + userID + " is not found!");
    }

}