package Exeicise1.Exercise2.Task1;


/*
You must develop a set of classes to allow users to play Rock-Paper-Scissors (referred to from now
on as RPS) against a computer opponent. If you are not familiar with the rules of the game, they can
be found at https://en.wikipedia.org/wiki/Rock–paper–scissors.

At a high level, your code should be structured as follows:
    - Two enumerated types, Symbol and GameResult, representing the list of possible symbols that
        can be used in the game and the list of possible outcomes of a single game.
    - A GamePlayer class representing a single (computer) player in the game.
    - A GameMain class containing a main method to run the game.

Symbol and GameResult

GameResult should have three possible values: WIN, LOSE, and DRAW – these represent the three
possible outcomes of a single game of RPS.

The Symbol type represents the three possible symbols in the game: ROCK, PAPER, and SCISSORS. In
addition to those three values, you should also implement an instance method getResult() inside
Symbol that compares the current symbol to another and returns one of the three possible
GameResult values, using the following rules.
    - For any Symbol value s, s.getResult(s) should return GameResult.DRAW
    - Otherwise, the result should be selected based on the following rules:    
        o Scissors defeats Paper
        o Paper defeats Rock
        o Rock defeats Scissors
    - The result should indicate the result for the first symbol; that is, ROCK.getResult(SCISSORS)
        should return GameResult.WIN, while SCISSORS.getResult(ROCK) should return
        GameResult.LOSE.

GamePlayer

This class must represent a computer playing the game of RPSLS. It should provide a single instance
method, getSymbol(), that returns the next symbol to be used in the game. You can use whatever
strategy you want to choose the symbol – the easiest method is to choose at random (see
https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/Random.html for the
documentation of the java.util.Random class). If you want, you can also try adding extra fields and
methods to the class to base the choice on the human's recent choices as in the well-known
strategies listed at
https://en.wikipedia.org/wiki/Rock%E2%80%93paper%E2%80%93scissors#Algorithms.

GameMain

This class should have a single main method that proceeds as follows:
    - Initialise a GamePlayer instance
    - Prompt the user for the number of rounds to play (you can use the java.util.Scanner class to
        read input from the user, documentation for this class is at
        https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/Scanner.html)
    - For each round:
        o Prompt the user for their chosen Symbol
        o Choose a symbol for the computer player using your getSymbol() method
        o Figure out the result of the round using Symbol.getResult(), print the result, and
            keep track of the wins for each player
    - At the end of all rounds, print the total score for each player (computer and human)
 */

public class Game {
    
}
