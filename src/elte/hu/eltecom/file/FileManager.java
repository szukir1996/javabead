package elte.hu.eltecom.file;

import java.io.File;
import elte.hu.eltecom.graph.GraphFactory;
import elte.hu.eltecom.graph.LimitedGraph;
import elte.hu.eltecom.Manager;
import elte.hu.eltecom.user.Language;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileManager{
    
    static private int thisUserId = 0;

    public static Manager readGraph(String filePath) throws FileNotFoundException, IOException{
        File file = new File(filePath + "input.txt");
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            int graphSize = Integer.parseInt(br.readLine());
            Manager manager = new Manager(GraphFactory.initGraph(graphSize));
            String line;
            for(line = br.readLine(); line != null; line = br.readLine()){
                if(line.contains("ENG") || line.contains("HUN") || line.contains("GER")){ 
                    String[] splittedLine = line.split(" ", 2);
                    if(splittedLine[1].charAt(0) == '#'){
                        manager.createAdminUser(manager, splittedLine[1], Language.valueOf(splittedLine[0]));
                    }else{
                        manager.createUser(splittedLine[1], Language.valueOf(splittedLine[0]));
                    }
                }else{
                    String[] splittedLine = line.split(" ");
                    for(int i=0;i<graphSize;i++){
                        if(splittedLine[i].equals("true")){
                            manager.linkUsers(manager.getUserById(thisUserId), manager.getUserById(i));
                        }
                    }
                    thisUserId++;
                }
            }
            br.close();
            return manager;
        }catch ( FileNotFoundException e ) {
                System.err.println("File does not exist.");
        }catch ( IOException e ) {
                System.err.println("An IO error occurred.");
        }
        return null;
    }
    
    public static void writeGraph(Manager manager, String filePath) throws FileNotFoundException{
        File file = new File(filePath + "output.txt");
        try(PrintWriter pw = new PrintWriter(file)){
            int nodeNumber = manager.getGraph().getNodeNumber();
            pw.println(nodeNumber);
            LimitedGraph limitedGraph = (LimitedGraph) manager.getGraph();
            pw.println(limitedGraph.print());
            pw.close();
        }catch(FileNotFoundException e){
            System.err.println("File cannot be opened.");
        }
    }
}