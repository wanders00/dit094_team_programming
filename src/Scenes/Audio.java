package Scenes;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Audio 
{
    //Loading all audio files
    static private MediaPlayer MainTheme = new MediaPlayer(new Media(new Audio().getClass().getResource("audio/8bit_beat.mp3").toExternalForm()));
    static private AudioClip ButtonSound = new AudioClip(new Audio().getClass().getResource("audio/button.mp3").toExternalForm());
    static private AudioClip EatSound = new AudioClip(new Audio().getClass().getResource("audio/Eat.mp3").toExternalForm());
    static private AudioClip DeathSound = new AudioClip(new Audio().getClass().getResource("audio/DoubleSound.mp3").toExternalForm());
    //Volume variables
    static double SoundVolume = 1.0;//swap to value from file
    static double MusicVolume = 1.0;//swap to value from file
    //Sound playback functions
    static public void ButtonPress()
    {
        ButtonSound.play(SoundVolume);
    }
    static public void EatSound()
    {
        EatSound.play(SoundVolume);
    }
    static public void DeathSound()
    {
        DeathSound.play(SoundVolume);
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
    //Volume setters
    static public void setMusicVolume(double target)
    {
        MusicVolume = target;
        MainTheme.setVolume(MusicVolume);
    }
    static public void setMusicVolume(int target)
    {
        setMusicVolume(((double)target)/100.0);
    }
    static public void setSoundVolume(double target)
    {
        SoundVolume = target;
    }
    static public void setSoundVolume(int target)
    {
        setSoundVolume(((double)target)/100.0);
    }
    static public void switchSoundVolume()
    {
        SoundVolume=1-SoundVolume;
    }
    static public void switchMusicVolume()
    {
        setMusicVolume(1-MusicVolume);
    }
}
