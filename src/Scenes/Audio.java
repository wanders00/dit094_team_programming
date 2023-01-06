package Scenes;

import GameLogic.FileHandler;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Audio {
    // Loading all audio files
    private static MediaPlayer MainTheme = new MediaPlayer(
            new Media(new Audio().getClass().getResource("audio/8bit_beat.mp3").toExternalForm()));
    private static AudioClip ButtonSound = new AudioClip(
            new Audio().getClass().getResource("audio/button.mp3").toExternalForm());
    private static AudioClip EatSound = new AudioClip(
            new Audio().getClass().getResource("audio/Eat.mp3").toExternalForm());
    private static AudioClip DeathSound = new AudioClip(
            new Audio().getClass().getResource("audio/DoubleSound.mp3").toExternalForm());
    // Sound playback functions

    public static void ButtonPress() {
        if (FileHandler.readFXOnOff()) {
            ButtonSound.play(FileHandler.readFXVolume());
        }
    }

    public static void EatSound() {
        if (FileHandler.readFXOnOff()) {
            EatSound.play(FileHandler.readFXVolume());
        }
    }

    public static void DeathSound() {
        if (FileHandler.readFXOnOff()) {
            DeathSound.play(FileHandler.readFXVolume());
        }
    }

    public static void PlayMainTheme() {
        MainTheme.setOnEndOfMedia(new Runnable() {
            public void run() {
                MainTheme.seek(Duration.ZERO);
            }
        });
        MainTheme.play();
        updateMusicVolume();
    }

    public static void updateMusicVolume() {
        MainTheme.setVolume(FileHandler.readMusicVolume());
    }

    public static void switchMusicOnOff() {
        MainTheme.setMute(!FileHandler.readMusicOnOff());
    }

}
