package Exeicise1.Exercise2.Task1;
import java.util.Random;


public class GamePlayer {
    private Random rand = new Random();
    
    public Symbol chooseSymbol() {        
        // Random choice
        return Symbol.values()[rand.nextInt(Symbol.values().length)];
    }
}
