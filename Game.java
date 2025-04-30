import java.util.*;
import java.io.*;
public class Game {
    private Puzzle[] puzzleArr; //stores every puzzle in numerical (top to bottom) order
    //private int puzzleNum; probably not really needed
    public Game(){
        puzzleArr = new Puzzle[100];
        //(choiceArr) reads file -> splits TSV into arr -> makes 2Darr of tsv, passed
        //(answerArr) above excluding the 2Darr step 
        try{ 
            File fileA = new File("answerChoices.tsv");
            Scanner inputA = new Scanner(fileA);
            File fileB = new File("trueAnswers.tsv");
            Scanner inputB = new Scanner(fileB);
            for (int i = 0; i < 100; i++){
                String temp;
                String[][] choiceArr; //stores (ordered) question choices, passed to Puzzle objects
                String[] answerArr; //above but w/ question answers
                if (i < 50){
                    choiceArr = new String[3][3];
                }else{
                    choiceArr = new String[4][4];
                }

                for (int j = 0; j < choiceArr.length; j++){
                    temp = inputA.nextLine();
                    choiceArr[j] = temp.split("\t");
                 }
                temp = inputB.nextLine();
                answerArr = temp.split("\t");
                puzzleArr[i] = new Puzzle(choiceArr, answerArr);
            }
        }catch (FileNotFoundException e){
			e.printStackTrace();
		}
    }    
    //This method requires input from the user interface
    //Checks user input -> returns boolean[] w/ each index representing correctness of user answer for each field
    public boolean[] checkPuzzle(int puzzleNum, String[] userAnswers){
        return puzzleArr[puzzleNum-1].checkArr(userAnswers);
    }
    //testing method
    public void printArr(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println("answerArr: " + arr[i]);
        }
    }
    //testing method
    public void printArr(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.println("choiceArr: " + arr[i][j]);
            }
        }
    }
    public Puzzle getPuzzle(int puzzleNum) {
        return puzzleArr[puzzleNum-1];
    }

}
