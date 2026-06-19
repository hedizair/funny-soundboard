package com.hedizair.soundboard.model.ui;

import java.net.URL;

import javax.swing.JOptionPane;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class AudioPlayer {
    public AudioPlayer() {

    }

    public void play(String filePath) {

        URL url = getClass().getClassLoader().getResource(filePath);
        if (url == null) {
            JOptionPane.showMessageDialog(null, "Fichier de son introuvable pour le chemin : " + filePath);
            return;
        }
        try {
            Player player = new Player(getClass().getClassLoader().getResourceAsStream(filePath));
            player.play();

        } catch (JavaLayerException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Impossible de lire le son : " + e);

        }

    }
}
