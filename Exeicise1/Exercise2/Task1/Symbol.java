package Exeicise1.Exercise2.Task1;

public enum Symbol {
    ROCK, PAPER, SCISSORS;
    
    public GameResult getResult (Symbol s) {
        if (this == s) {
            return GameResult.DRAW;
        }
        
        switch (this) {
            case ROCK:
            
            return (s == SCISSORS) ? GameResult.WIN : GameResult.LOSE;
            
            case PAPER:
            
            return (s == ROCK) ? GameResult.WIN : GameResult.LOSE;
            
            case SCISSORS:
            return (s == PAPER) ? GameResult.WIN : GameResult.LOSE;
            
            default:
            return null;
        }
    }
}

