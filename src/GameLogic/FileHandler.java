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
public class FileHandler {
    static public void initializeFile() {
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

    static public JSONObject getJSONObject() {
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

    static public String[] readHighScores() {
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

    static public double readCurrentScore() {
        return Double.parseDouble((String) getJSONObject().get("currentScore"));
    }

    static public void updateGameDifficulty(Difficulty newDifficulty) {
        updateValue("gameDifficulty", newDifficulty.toString());
    }

    static public void updateGameLayout(Layout newLayout) {
        updateValue("gameLayout", newLayout.toString());
    }

    static public void updateHighScores(double score) {
        JSONArray highScores = (JSONArray) getJSONObject().get("highScores");
        JSONObject newScore = new JSONObject();
        newScore.put("score", score);
        newScore.put("layout", readGameLayout().toString());
        newScore.put("difficulty", readGameDifficulty().toString());
        if (highScores.isEmpty()) {
            highScores.add(newScore);
        } else {
            for (int i = 0; i < highScores.size(); i++) {
                JSONObject curJSONObject = (JSONObject) highScores.get(i);
                Double curScore = Double.parseDouble(curJSONObject.get("score").toString());
                if (score > curScore) {
                    highScores.add(i, newScore);
                    break;
                    // If score > curScore, add it in its position and push all the others down,
                    // then break to not infinite loop.
                } else if (i == highScores.size() - 1) {
                    highScores.add(newScore);
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
    }

    static public void updateCurrentScore(double newCurrentScore) {
        updateValue("currentScore", String.valueOf(newCurrentScore));
    }

    static public void updateValue(Object key, Object value) {
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
