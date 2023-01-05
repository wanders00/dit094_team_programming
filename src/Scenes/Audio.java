package Scenes;

import javafx.scene.media.AudioClip;

public class Audio 
{
    static AudioClip ButtonSound = new AudioClip(new Audio().getClass().getResource("audio/ButtonClick.wav").toExternalForm());
    
    static public void ButtonPress()
    {
        ButtonSound.play();
    }

    static public void play(String path)
    {
        new AudioClip(new Audio().getClass().getResource(path).toExternalForm()).play();
    }
}
