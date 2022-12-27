import java.util.ArrayList;

public class Score {
    private ArrayList<Double> scoreList= new ArrayList<>();
    private double score;
    public Score(double score){ //could add name or date
        scoreList.add(score);
        sortHighScores();

    }
    public void sortHighScores(){
        scoreList.stream().sorted();
        System.out.println(scoreList);
    }

}
