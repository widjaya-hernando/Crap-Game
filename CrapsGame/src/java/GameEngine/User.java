package GameEngine;

/**
 *
 * @author Sarah Lucabaugh
 */
public class User {
    private String firstName;
    private String lastName;
    private int wins;
    private int losses;
    private int userID;
    
    public User (){
        firstName = null;
        lastName = null;
        wins = 0;
        losses = 0;
    }
    
    public User(String fName, String lName){
        this.firstName = fName;
        this.lastName = lName;
        wins = 0;
        losses = 0;
    }
    
    public void setFirst(String fName){
        this.firstName = fName;
    }
    
    public void setLast(String lName){
        this.lastName = lName;
    }
    
    public void setWins(int gamesWon){
        wins = gamesWon;
    }
    
    public void setLosses(int gamesLost){
        losses = gamesLost;
    }
    
    public String getFirst(){
        return firstName;
    }
    
    public String getLast(){
        return lastName;
    }
    
    public int getWins(){
        return wins;
    }
    
    public int getLosses(){
        return losses;
    }
    
    public int getID(){
        return userID;
    }
}
