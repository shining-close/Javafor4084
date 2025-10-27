package Java.Exercise2.Task1;
import java.util.Scanner;


public class GameMain {
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        System.out.println("How many rounds?");
        int rounds = in.nextInt();
        
        GamePlayer player = new GamePlayer();
        int userWins = 0, computerWins = 0;
        
        for (int i = 0; i < rounds; i++) {
            System.out.println("Enter your choice:");
            Symbol userChoice = Symbol.valueOf(in.next().toUpperCase());
            Symbol computerChoice = player.chooseSymbol();
            System.out.println("User chose " + userChoice + ",computer chose " + computerChoice);
            
            switch (userChoice.getResult(computerChoice)) {
                
                case WIN:
                System.out.println("User wins!");
                userWins++;
                break;
                
                case LOSE:
                System.out.println("System wins!");
                computerWins++;
                break;
                
                case DRAW:
                System.out.println("Draw!");
                break;
            }
        }
        
        System.out.println("User wins " + userWins + ", computer wins " + computerWins);
        in.close();
    }
}