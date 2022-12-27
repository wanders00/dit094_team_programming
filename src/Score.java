import java.util.ArrayList;

public class Score {
    private static ArrayList<Double> scoreList= new ArrayList<>();
    private double score;
    public Score(double score){ //could add name or date
        scoreList.add(score);
        sortHighScores();

    }
    public void sortHighScores(){
        for(int i = 0; i< scoreList.size(); i++){
            System.out.println(scoreList.get(i));
        }
    }

}
