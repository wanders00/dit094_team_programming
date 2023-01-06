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
public abstract class FileHandler {
    public static void initializeFile() {// Initialize a new file
        JSONObject obj = new JSONObject();
        obj.put("gameDifficulty", Difficulty.NORMAL.toString());
        obj.put("gameLayout", Layout.ORDINARY.toString());
        obj.put("highScores", new JSONArray());
        obj.put("currentScore", "0");
        obj.put("musicOnOff", "true");
        obj.put("fxOnOff", "true");
        obj.put("musicVolume", ".5"); // 0-100% where 1 = 100%
        obj.put("fxVolume", ".5"); // 0-100% where 1 = 100%

        try (FileWriter file = new FileWriter("GameData.json")) {
            file.write(obj.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static JSONObject getJSONObject() {
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

    public static Difficulty readGameDifficulty() {
        return Difficulty.fromString((String) getJSONObject().get("gameDifficulty"));
    }

    public static Layout readGameLayout() {
        return Layout.fromString((String) getJSONObject().get("gameLayout"));
    }

    public static String[] readHighScores() {
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

    public static double readCurrentScore() {
        return Double.parseDouble((String) getJSONObject().get("currentScore"));
    }

    public static boolean readMusicOnOff() {
        return Boolean.parseBoolean((String) getJSONObject().get("musicOnOff"));
    }

    public static boolean readFXOnOff() {
        return Boolean.parseBoolean((String) getJSONObject().get("fxOnOff"));
    }

    public static double readMusicVolume() {
        return Double.parseDouble((String) getJSONObject().get("musicVolume"));
    }

    public static double readFXVolume() {
        return Double.parseDouble((String) getJSONObject().get("fxVolume"));
    }

    public static void switchMusicOnOff() {
        updateValue("musicOnOff", Boolean.toString(!readMusicOnOff()));
    }

    public static void switchFXOnOff() {
        updateValue("fxOnOff", Boolean.toString(!readFXOnOff()));
    }

    public static void updateMusicVolume(double newMusicVolume) {
        updateValue("musicVolume", String.valueOf(newMusicVolume));
    }

    public static void updateFXVolume(double newFXVolume) {
        updateValue("fxVolume", String.valueOf(newFXVolume));
    }

    public static void updateGameDifficulty(Difficulty newDifficulty) {
        updateValue("gameDifficulty", newDifficulty.toString());
    }

    public static void updateGameLayout(Layout newLayout) {
        updateValue("gameLayout", newLayout.toString());
    }

    public static boolean updateHighScores() {
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

    public static void updateCurrentScore(double newCurrentScore) {
        updateValue("currentScore", String.valueOf(newCurrentScore));
    }

    public static void updateValue(Object key, Object value) {
        // Updates the specified key with the new value.
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
