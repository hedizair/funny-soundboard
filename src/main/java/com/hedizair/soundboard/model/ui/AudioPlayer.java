package com.hedizair.soundboard.model.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

public class AudioPlayer {

    private Clip currentClip;
    private List<AudioPlayerListener> listeners = new ArrayList<>();
    private int volume = 50;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public AudioPlayer() {

    }

    public void play(String filePath) {
        executor.submit(() -> {
            URL url = getClass().getClassLoader().getResource(filePath);
            if (url == null) {
                JOptionPane.showMessageDialog(null, "Fichier introuvable : " + filePath);
                return;
            }
            try {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);

                currentClip = clip;
                applyVolume();

                clip.addLineListener(event -> {
                    if (event.getType() == javax.sound.sampled.LineEvent.Type.STOP) {
                        emit(AudioPlayerListener::onSoundFinished);
                        clip.close();
                    }
                });

                emit(AudioPlayerListener::onSoundStart);
                clip.start();

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                JOptionPane.showMessageDialog(null, "Impossible de lire le son : " + e.getMessage());
            }
        });
    }

    public void stop() { // ! Je laisse les logs parce que ça marche mieux avec lol
        System.out.println("Start stopping");

        if (currentClip == null) {
            JOptionPane.showMessageDialog(null, "Aucun son en cours");
            return;
        }
        System.out.println("stopping 1");

        currentClip.stop();
        System.out.println("stopping 2");

        currentClip.close();
        System.out.println("stopping");

        currentClip = null;
        System.out.println("End stopping");

    }

    public void addListener(AudioPlayerListener listener) {
        listeners.add(listener);
    }

    public void setVolume(int percent) {
        if (percent == this.volume) {
            return;
        }
        this.volume = percent;
        applyVolume();
    }

    private void applyVolume() {
        if (currentClip == null || !currentClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            return;
        }
        FloatControl control = (FloatControl) currentClip.getControl(FloatControl.Type.MASTER_GAIN);

        if (volume == 0) {
            control.setValue(control.getMinimum());
        } else {
            float dB = (float) (Math.log10(volume / 100.0) * 20);
            dB = Math.max(dB, control.getMinimum());
            control.setValue(dB);
        }
    }

    private void emit(Consumer<AudioPlayerListener> action) {
        for (AudioPlayerListener listener : listeners) {
            action.accept(listener);
        }
    }

}
