
import java.util.*;
public class Puzzle {
    private String[] whatArr; //all the arrays
    private String[] whoArr;
    private String[] whereArr;
    private String[] whyArr;
    private String[] answerArr;
    private int arrLength;

    public Puzzle(String[][] choiceArr, String[] answerArr) {
        arrLength = choiceArr.length; //length of array
        whatArr = new String[arrLength];
        whoArr = new String[arrLength];
        whereArr = new String[arrLength];
        whyArr = new String[arrLength];
        this.answerArr = answerArr;
        answerArr = new String[arrLength];
        
        for (int i = 0; i < arrLength; i++) {
            for (int j = 0; j < choiceArr[0].length; j++) { //restructure later
                if (j == 0) { // first one qui
                    whoArr[i] = choiceArr[i][j];
                } else if (j == 1) { // second one quoi
                    whatArr[i] = choiceArr[i][j];
                } else if (j == 2) { //third one where
                    whereArr[i] = choiceArr[i][j];
                } else if (j == 3) { //fourth one (if there is) why
                    whyArr[i] = choiceArr[i][j];
                }
            }
        }
    }
    
    public boolean[] checkArr(String[] userInput) { //checks that array given the category as input
        boolean[] boolArr = new boolean[arrLength]; //either 3 or 4 long
        for (int i = 0; i < answerArr.length; i++) { // loops through each correct answer
            if (answerArr[i].equals(userInput[i])) { // if that correct answer is equal to what user input
                boolArr[i] = true; // return true
            } else {
                boolArr[i] = false; //return false
            }
        }
        return boolArr;
    }

    //getter
    public String[] getWho() {
        return whoArr;
    }
    public String[] getWhat() {
        return whatArr;
    }
    public String[] getWhere() {
        return whereArr;
    }
    public String[] getWhy() {
        return whyArr;
    }
    public String[] getAnswers() {
        return answerArr;
    }
}

