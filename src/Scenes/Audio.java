package Scenes;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Audio 
{
    static private MediaPlayer MainTheme = new MediaPlayer(new Media(new Audio().getClass().getResource("audio/8bit_beat.mp3").toExternalForm()));
    static private AudioClip ButtonSound = new AudioClip(new Audio().getClass().getResource("audio/button.mp3").toExternalForm());
    static private AudioClip EatSound = new AudioClip(new Audio().getClass().getResource("audio/Eat.mp3").toExternalForm());
    static private AudioClip DeathSound = new AudioClip(new Audio().getClass().getResource("audio/DoubleSound.mp3").toExternalForm());
    
    static public void ButtonPress()
    {
        ButtonSound.play();
    }
    static public void EatSound()
    {
        EatSound.play();
    }
    static public void DeathSound()
    {
        DeathSound.play();
    }
    static public void PlayMainTheme()
    {
        MainTheme.setOnEndOfMedia(new Runnable() {
            public void run() {
                MainTheme.seek(Duration.ZERO);
            }
        });
        MainTheme.play();
    }
    static public void setMusicVolume(double target)
    {
        MainTheme.setVolume(target);
    }
}
