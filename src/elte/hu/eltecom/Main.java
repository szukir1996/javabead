package elte.hu.eltecom;

import elte.hu.eltecom.file.FileManager;
import elte.hu.eltecom.user.User;
import elte.hu.eltecom.user.AdminUser;
import elte.hu.eltecom.user.Language;
import elte.hu.eltecom.graph.exceptions.NodeNotFoundException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main{

    public static void main(String[] args) throws FileNotFoundException, NodeNotFoundException, IOException{
        FileManager fileManager = new FileManager();
        if(!(args[0].equals(""))){
            Manager manager = fileManager.readGraph(args[0]);
            User user = manager.getUserById(3);
            if(user instanceof AdminUser){
                AdminUser adminUser = (AdminUser) user;
                adminUser.kickUser(manager.getUserById(1));
                adminUser.kickUser(manager.getUserById(2));
//                User testUser = manager.getUserById(2);
                User newUser = manager.createUser("Grabowski", Language.HUN);
                manager.linkUsers(newUser, adminUser);
            }
            fileManager.writeGraph(manager, args[0]);
        }
    }
}