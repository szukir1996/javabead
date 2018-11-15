package elte.hu.eltecom.user;

public class User{

	private static int userCounter = 0;
	private final int id;
	protected String userName;
	protected Language language;
	
	public User(String userName, Language language){
            this.id = userCounter;
            this.userName = userName;
            this.language = language;
            userCounter++;
	}
	
	public int getId(){
            return this.id;
	}
	
	public String getUserName(){
            return this.userName;
	}
	
	public void setUserName(String userName){
            this.userName = userName;
	}
	
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final User other = (User) obj;
            if (this.id != other.id) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 13 * hash + this.id;
            return hash;
        }
        
        @Override
	public String toString(){
            return this.language.toString() + " " + this.userName;

	}

}