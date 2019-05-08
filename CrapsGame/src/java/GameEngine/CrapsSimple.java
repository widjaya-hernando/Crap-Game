

package GameEngine;
import java.util.Random;

/**
 *
 * @author Alex Conway
 * @version 1.0 
 */
public class CrapsSimple {
    
    private int die1,
                die2,
                sum,
                point,
                rolls,
                wins,
                losses,
                gameState = 0,
                winState;
    
    private String winStatus,
                   status;
    
    private static final Random rng = new Random();
    
    //Constructor
    public CrapsSimple() {
        die1 = 0;
        die2 = 0;
        sum = 0;
        point = 0;
        wins = 0;
        losses = 0;
        
    }
    
    //Dice Roll for inside the object
    //Gives each dice a value and calculates a sum
    private void internalRollDice() {
        die1 = 1 + rng.nextInt(6);
        die2 = 1 + rng.nextInt(6);
        
        sum = die1 + die2;
    }
    
    //Roll for a newly started game
    //Returns whether or not the user won (1), lost (0), or must keep playing (2)
    private int firstRoll() {
        internalRollDice();
        
        switch (sum) {
            case 7:
            case 11:
                gameState= 0;
                winState = 1;
                winStatus = "You win.";
                wins++;
                point = 0;
                break;
            case 2:
            case 3:
            case 12:
                gameState = 0;
                winState = 0;
                winStatus = "You lost.";
                losses++;
                point = 0;
                break;
            default:
                gameState = 1;
                winState = 2;
                winStatus = "Roll again.";
                setPointInner();
                break;
        }
        
        return winState;
    }
    
    //If the player must continue playing, use this command
    //Win states are the same as in firstRoll
    private int nextRoll() {
        internalRollDice();
        
        if (sum == point) {
            gameState = 0;
            winState = 1;
            point = 0;
            wins++;
        }
        else if (sum == 7) {
            gameState = 0;
            winState = 0;
            point = 0;
            losses++;
        }
        else {
        }

        return winState;    
    }
    
    //This command is for running the game entirely
    //Any information needed from the object should be accessed through the get methods
    public void rollDice() {
        
        if (gameState == 0) {
                
                winState = firstRoll();
                rolls++;
                switch (winState) {
                    case 1:
                        status = "You win.";
                        break;
                    case 0:
                        status = "You lose.";
                        break;
                    default:
                        status = "Roll again.";
                        break;
                }
           }
        else {
                
                winState = nextRoll();
                rolls++;
                switch (winState) {
                    case 1:
                        status = "You win.";
                        break;
                    case 0:
                        status = "You lose.";
                        break;
                    default:
                        status = "Roll again.";
                        break;
                }
            }
    }
    
    //All get methods are listed below
    public int getDie1() {
        return die1;
    }
    public int getDie2() {
        return die2;
    }
    public int getSum() {
        return sum;
    }
    public int getPoint() {
        return point;
    }
    public int getRolls() {
        return rolls;
    }
    public int getWins(){
        return wins;
    }
    public int getLosses(){
        return losses;
    }
    public void setPointInner() {
        point = die1 + die2;
    }
    public int getGameState() {
        return gameState;
    }
    public int getWinState() {
        return winState;
    }
    public String getWinStatus() {
        return winStatus;
    }
    public String getGameStatus(){
        return status;
    }
}
