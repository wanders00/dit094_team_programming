package GameLogic;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Scenes.HighScoreScene;

@SuppressWarnings({ "unchecked", "deprecated" })
public class FileHandler {// The file handler class reads and writes the game logic objects into files.
    static public void initializeFile() {// Initialize a new file
        JSONObject obj = new JSONObject();
        obj.put("gameDifficulty", Difficulty.NORMAL.toString());
        obj.put("gameLayout", Layout.ORDINARY.toString());
        obj.put("highScores", new JSONArray());
        obj.put("currentScore", "0");

        try (FileWriter file = new FileWriter("GameData.json")) {
            file.write(obj.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static public JSONObject getJSONObject() {// Method to get and return the Object.
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("GameData.json"));
            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject;
        } catch (IOException | ParseException e) {
            initializeFile();
            return getJSONObject();
            // If it cant find the file, initialize it, then try again.
        }
    }

    static public Difficulty readGameDifficulty() {
        return Difficulty.fromString((String) getJSONObject().get("gameDifficulty"));
    }

    static public Layout readGameLayout() {
        return Layout.fromString((String) getJSONObject().get("gameLayout"));
    }

    static public String[] readHighScores() {// Method that reads the value of the HighScore object
        JSONArray highScores = (JSONArray) getJSONObject().get("highScores");
        String[] output = new String[highScores.size()];

        for (int i = 0; i < highScores.size(); i++) {
            JSONObject curJSONObject = (JSONObject) highScores.get(i);
            String curScore = curJSONObject.get("score").toString();
            String curLayout = curJSONObject.get("layout").toString();
            String curDifficulty = curJSONObject.get("difficulty").toString();
            output[i] = "Score: " + curScore + " - Layout: " + curLayout + " - Difficulty: " + curDifficulty;
        }
        return output;
    }

    static public double readCurrentScore() {// Method that reads the value of the Scoreobject
        return Double.parseDouble((String) getJSONObject().get("currentScore"));
    }

    static public void updateGameDifficulty(Difficulty newDifficulty) {// Method to update a new game Difficulty
        updateValue("gameDifficulty", newDifficulty.toString());
    }

    static public void updateGameLayout(Layout newLayout) {// Method to update a new game Layout
        updateValue("gameLayout", newLayout.toString());
    }

    static public boolean updateHighScores() {// Method to update new High Scores
        double score = readCurrentScore();
        boolean newHighScore = false;
        JSONArray highScores = (JSONArray) getJSONObject().get("highScores");
        JSONObject newScore = new JSONObject();
        newScore.put("score", score);
        newScore.put("layout", readGameLayout().toString());
        newScore.put("difficulty", readGameDifficulty().toString());
        if (highScores.isEmpty()) {
            highScores.add(newScore);
            newHighScore = true;
        } else {
            for (int i = 0; i < highScores.size(); i++) {
                JSONObject curJSONObject = (JSONObject) highScores.get(i);
                Double curScore = Double.parseDouble(curJSONObject.get("score").toString());
                if (score > curScore) {
                    highScores.add(i, newScore);
                    newHighScore = true;
                    break;
                    // If score > curScore, add it in its position and push all the others down,
                    // then break to not infinite loop.
                } else if (i == highScores.size() - 1 && highScores.size() < HighScoreScene.HIGH_SCORES_AMOUNT) {
                    highScores.add(newScore);
                    newHighScore = true; 
                    break;
                }
                // If i = highScores.size() and the previous if statement was not true;
                // add the new score to the last position in the array,
                // then break to not infinite loop.
            }
        }

        while (highScores.size() > HighScoreScene.HIGH_SCORES_AMOUNT) {
            highScores.remove(HighScoreScene.HIGH_SCORES_AMOUNT);
        }
        // To remove any scores that are above the high scores amount (top 10).

        updateValue("highScores", highScores);
        return newHighScore;
    }

    static public void updateCurrentScore(double newCurrentScore) {// Method to update a new current score
        updateValue("currentScore", String.valueOf(newCurrentScore));
    }

    static public void updateValue(Object key, Object value) {// Method that updates the value of the Object Key
        JSONObject jsonObject = getJSONObject();
        jsonObject.put(key, value);

        try (FileWriter file = new FileWriter("GameData.json")) {
            file.write(jsonObject.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
