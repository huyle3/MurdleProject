import java.util.*;
import java.io.*;
public class Game {
    private Puzzle[] puzzleArr; //stores every puzzle in numerical (top to bottom) order
    //private int puzzleNum; probably not really needed

    public Game(){
        puzzleArr = new Puzzle[100];
        File fileA = new File("answerChoices.csv");
        Scanner inputA = new Scanner(fileA);
        File fileB = new File("trueAnswers.csv");
        Scanner inputB = new Scanner(fileB);
   
        //(choiceArr) reads file -> splits CSV into arr -> makes 2Darr of csv, passed
        //(answerArr) above excluding the 2Darr step 
        for (int i = 0; i < 100; i++){
            String temp;
            String[][] choiceArr; //stores (ordered) question choices, passed to Puzzle objects
            String[] answerArr; //above but w/ question answers
            if (i < 50){
                choiceArr = new String[3][];
                answerArr = new String[3];
                for (int j = 0; j < 3; j++){
                    temp = inputA.next();
                    choiceArr[j] = temp.split(",");
                }
            }else{
                choiceArr = new String[4][];
                for (int j = 0; j < 4; j++){
                    temp = inputA.next();
                    choiceArr[j] = temp.split(",");
                }
            }
            temp = inputB.next();
            answerArr = temp.split(",");
            puzzleArr[i] = new Puzzle(choiceArr, answerArr);
        }
    }    

    //This method requires input from the user interface
    public int getPuzzleNum(){
        return 0;
    }
}
