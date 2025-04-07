
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
        String[][] arrOfArr = {
            whatArr, whoArr, whereArr, whyArr};
        for (int i = 0; i < arrOfArr.length; i++) { // loops through each array and set them to arrLength size
            arrOfArr[i] = new String[arrLength];
        }

        for (int i =0; i < arrLength; i++) {
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
    public boolean checkArr(String category) { //checks that array given the category as input
        if (category.equals("Who")) {
            return check(whoArr, 1);
        } else if (category.equals("What")) {
            return check(whatArr, 2);
        } else if (category.equals("Where")) {
            return check(whereArr, 3);
        } else if (category.equals("Why")) {
            return check(whyArr, 4);
        }
        return false;
    }
 

    private boolean check(String[] catArr, int num) {
        for (int i = 0; i < catArr.length; i++) {
            if (catArr[i].equals(answerArr[num])) { //loops through each value in category arr
                return true; //checks if equal to the correct answer
            }
        }
        return false;
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

