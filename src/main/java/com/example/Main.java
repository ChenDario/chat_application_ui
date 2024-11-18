package com.example;

import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        //SwingUtilities.invokeLater(ChatApplicationUI::new);

        try {
            // Imposta il tema Nimbus
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Impossibile caricare il tema Nimbus.");
        }

        // Avvio dell'applicazione
        new ChatApplicationUI();

    }
}