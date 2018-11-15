package elte.hu.eltecom;

import elte.hu.eltecom.user.User;
import java.util.Map;
import java.util.HashMap;
import elte.hu.eltecom.graph.Graph;
import elte.hu.eltecom.graph.exceptions.NodeNotFoundException;
import elte.hu.eltecom.user.AdminUser;
import elte.hu.eltecom.user.Language;

public class Manager{

	private final Graph graph;
	Map<Integer, User> users;
	
	public Manager(Graph graph){
            this.graph = graph;
            this.users = new HashMap<>();
	}
	
	public Graph getGraph(){
            return this.graph;
	}
	
	public User createUser(User user){
            users.put(user.getId(), user);
            graph.addNode(user);

            return user;
	}
	
	public User createUser(String userName, Language language){
            User newUser = new User(userName, language);
            return createUser(newUser);
	}
	
	public User createAdminUser(Manager manager, String userName, Language language){
            User newAdmin = new AdminUser(manager, userName, language);
            return createUser(newAdmin);
	}
	
	public User getUserById(int id){
            return users.get(id);
	}
	
	public void linkUsers(User thisUser, User thatUser){
            this.graph.linkNodes(thisUser, thatUser);
	}
	
	public void deleteUser(User user) throws NodeNotFoundException{
            this.graph.removeNode(user);
            this.users.remove(user.getId());
	}

}