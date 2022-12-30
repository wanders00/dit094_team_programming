package Scenes;
import javafx.scene.media.*;

public class Audio 
{
    static public void play(String path)
    {
        new AudioClip(new Audio().getClass().getResource(path).toExternalForm()).play();
    }
}
