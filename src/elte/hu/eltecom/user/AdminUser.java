package elte.hu.eltecom.user;

import elte.hu.eltecom.Manager;
import elte.hu.eltecom.graph.exceptions.NodeNotFoundException;

public class AdminUser extends User{

    private Manager manager;

    public AdminUser(Manager manager, String userName, Language language){
        super(userName, language);
        this.manager = manager;
    }

    public void kickUser(User user) throws NodeNotFoundException{
        manager.deleteUser(user);
    }

    @Override
    public String toString(){
            return this.language.toString() + " " + this.userName;
    }
}