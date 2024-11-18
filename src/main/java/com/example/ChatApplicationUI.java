package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class ChatApplicationUI {
    private JFrame frame;
    private JTextField usernameField;
    private DefaultListModel<String> chatListModel;
    private JTextArea chatHistory;
    private HashMap<String, String> messages;

    public ChatApplicationUI() {
        createLoginWindow();
    }

    private void createLoginWindow() {
        // Creazione della finestra di login
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new FlowLayout());

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);
        JButton loginButton = new JButton("Login");

        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(loginButton);

        // Azione del bottone di login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                if (!username.isEmpty()) {
                    createMainChatWindow(username);
                } else {
                    JOptionPane.showMessageDialog(frame, "Inserisci un username!");
                }
            }
        });

        frame.setVisible(true);
    }

    private void createMainChatWindow(String username) {
        // Chiudi la finestra di login
        frame.dispose();

        // Creazione della finestra principale della chat
        frame = new JFrame("Chat App - " + username);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // SplitPane per dividere la lista delle chat e l'area della cronologia
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(250);

        // Pannello sinistro: Lista delle chat
        chatListModel = new DefaultListModel<>();
        chatListModel.addElement("Chat 1");
        chatListModel.addElement("Chat 2");
        chatListModel.addElement("Chat 3");

        JList<String> chatList = new JList<>(chatListModel);
        chatList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        chatList.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane chatListScrollPane = new JScrollPane(chatList);

        // Pannello destro: Cronologia dei messaggi
        chatHistory = new JTextArea();
        chatHistory.setEditable(false);
        chatHistory.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane chatHistoryScrollPane = new JScrollPane(chatHistory);

        // Mock dei messaggi
        messages = new HashMap<>();
        messages.put("Chat 1", "Messaggio 1\nMessaggio 2\nMessaggio 3");
        messages.put("Chat 2", "Benvenuto nella Chat 2!");
        messages.put("Chat 3", "Non ci sono messaggi in questa chat.");

        // Evento: aggiorna la cronologia dei messaggi
        chatList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String selectedChat = chatList.getSelectedValue();
                if (selectedChat != null && messages.containsKey(selectedChat)) {
                    chatHistory.setText(messages.get(selectedChat));
                }
            }
        });

        // Aggiungi componenti al JSplitPane
        splitPane.setLeftComponent(chatListScrollPane);
        splitPane.setRightComponent(chatHistoryScrollPane);

        // Aggiungi il JSplitPane alla finestra principale
        frame.add(splitPane);

        // Mostra la finestra
        frame.setVisible(true);
    }
}

