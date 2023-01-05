package GameLogic;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import GameLogic.Game.Layout;
import Scenes.HighScoreScene;

@SuppressWarnings({ "unchecked", "deprecated" })
public class FileHandler {//The file handler class reads and writes the game logic objects into files.
    static public void initializeFile() {//Initialize a new file
        JSONObject obj = new JSONObject();
        obj.put("gameDifficulty", "NORMAL");
        obj.put("gameLayout", "ORDINARY");
        obj.put("highScores", new JSONArray());
        obj.put("currentScore", "0");

        try (FileWriter file = new FileWriter("GameData.json")) {
            file.write(obj.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static public JSONObject getJSONObject() {//Method to get and return the Object.
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("GameData.json"));
            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject;
        } catch (IOException | ParseException e) {
            initializeFile();
            return getJSONObject();
        }
    }

    static public Difficulty readGameDifficulty() {//Method that reads the value of the Difficulty object
        return Difficulty.valueOf((String) getJSONObject().get("gameDifficulty"));
    }

    static public Layout readGameLayout() {//Method that reads the value of the Game layout object
        return Layout.valueOf((String) getJSONObject().get("gameLayout"));
    }

    static public String[] readHighScores() {//Method that reads the value of the HighScore object
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

    static public double readCurrentScore() {//Method that reads the value of the Scoreobject
        return Double.parseDouble((String) getJSONObject().get("currentScore"));
    }

    static public void updateGameDifficulty(Difficulty newDifficulty) {//Method to update a new game Difficulty
        updateValue("gameDifficulty", newDifficulty.toString());
    }

    static public void updateGameLayout(Layout newLayout) {//Method to update a new game Layout
        updateValue("gameLayout", newLayout.toString());
    }

    static public void updateHighScores(double score) {//Method to update new High SCores
        JSONArray highScores = (JSONArray) getJSONObject().get("highScores");
        if (highScores.isEmpty()) {
            JSONObject newScore = new JSONObject();
            newScore.put("score", score);
            newScore.put("layout", readGameLayout().toString());
            newScore.put("difficulty", readGameDifficulty().toString());
            highScores.add(newScore);
            updateValue("highScores", highScores);
        } else {
            JSONObject newScore = new JSONObject();
            for (int i = 0; i < highScores.size(); i++) {
                JSONObject curJSONObject = (JSONObject) highScores.get(i);
                Double curScore = Double.parseDouble(curJSONObject.get("score").toString());
                if (score > curScore) { // Can most likely make this part better
                    JSONObject lastObject = (JSONObject) highScores.get(highScores.size() - 1);
                    for (int j = highScores.size() - 1; j > i; j--) {
                        highScores.set(j, highScores.get(j - 1));
                    }
                    highScores.add(lastObject);
                    newScore.put("score", score);
                    newScore.put("layout", readGameLayout().toString());
                    newScore.put("difficulty", readGameDifficulty().toString());
                    highScores.set(i, newScore);
                    break;
                } else if (i == highScores.size() - 1) {
                    newScore.put("score", score);
                    newScore.put("layout", readGameLayout().toString());
                    newScore.put("difficulty", readGameDifficulty().toString());
                    highScores.add(newScore);
                    break;
                }
            }
        }

        while (highScores.size() > HighScoreScene.HIGH_SCORES_AMOUNT) {
            highScores.remove(HighScoreScene.HIGH_SCORES_AMOUNT);
        }

        updateValue("highScores", highScores);
    }

    static public void updateCurrentScore(double newCurrentScore) {//Method to update a new current score
        updateValue("currentScore", String.valueOf(newCurrentScore));
    }

    static public void updateValue(Object key, Object value) {//Method that updates the value of the Object Key
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
