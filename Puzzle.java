
import java.util.*;
public class Puzzle {
    private String[] whatArr; //all the arrays
    private String[] whoArr;
    private String[] whereArr;
    private String[] whyArr;
    private String[] answerArr;
    private int arrLength;

    public Puzzle(String[][] choiceArr, String[] answerArr) {
        String string = "Faire votre choix";    
        arrLength = choiceArr.length + 1; //length of array
        whatArr = new String[arrLength];
        whoArr = new String[arrLength];
        whereArr = new String[arrLength];
        whyArr = new String[arrLength];
        this.answerArr = answerArr;
        answerArr = new String[arrLength];
        
        String[][] arrOfArr = new String[][]{whatArr, whoArr, whereArr, whyArr};

        for (int i = 0; i < arrOfArr.length; i++) {
            arrOfArr[i][0] = string; //first choice in each is 0
        }

        for (int i = 0; i < arrLength - 1; i++) { //
            whoArr[i+1] = choiceArr[i][0];
            whatArr[i+1] = choiceArr[i][1];
            whereArr[i+1] = choiceArr[i][2];
            if (choiceArr[i].length == 4) {
                whyArr[i+1] = choiceArr[i][3];
            }
        }
    }
    
    public boolean[] checkArr(String[] userInput) { //checks that array given the category as input
        boolean[] boolArr = new boolean[arrLength-1]; //either 3 or 4 long
        for (int i = 0; i < answerArr.length; i++) { // loops through each correct answer
            boolArr[i] = answerArr[i].equals(userInput[i]);
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

