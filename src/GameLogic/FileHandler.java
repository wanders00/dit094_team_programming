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
public class FileHandler {
    public void initializeFile() {
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

    public JSONObject getJSONObject() {
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

    public Difficulty readGameDifficulty() {
        return Difficulty.valueOf((String) getJSONObject().get("gameDifficulty"));
    }

    public Layout readGameLayout() {
        return Layout.valueOf((String) getJSONObject().get("gameLayout"));
    }

    public String[] readHighScores() {
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

    public double readCurrentScore() {
        return Double.parseDouble((String) getJSONObject().get("currentScore"));
    }

    public void updateGameDifficulty(Difficulty newDifficulty) {
        updateValue("gameDifficulty", newDifficulty.toString());
    }

    public void updateGameLayout(Layout newLayout) {
        updateValue("gameLayout", newLayout.toString());
    }

    public void updateHighScores(double score) {
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

    public void updateCurrentScore(double newCurrentScore) {
        updateValue("currentScore", String.valueOf(newCurrentScore));
    }

    public void updateValue(Object key, Object value) {
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
