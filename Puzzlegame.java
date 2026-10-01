package com.javahub.games1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class PuzzleGame implements ActionListener {

    private JFrame frame;
    private JButton jb1, jb2, jb3, jb4, jb5, jb6, jb7, jb8, jb9;
    private JButton[] all;               
    private JButton newGameButton;
    private JLabel movesLabel;
    private int moves = 0;

    public PuzzleGame() {

        frame = new JFrame("8 Number Puzzle");
        frame.setLayout(new BorderLayout());

       
        movesLabel = new JLabel("Moves: 0", JLabel.CENTER);
        movesLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(movesLabel, BorderLayout.NORTH);

        
        JPanel gridPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        gridPanel.add(jb1 = new JButton());
        gridPanel.add(jb2 = new JButton());
        gridPanel.add(jb3 = new JButton());
        gridPanel.add(jb4 = new JButton());
        gridPanel.add(jb5 = new JButton());
        gridPanel.add(jb6 = new JButton());
        gridPanel.add(jb7 = new JButton());
        gridPanel.add(jb8 = new JButton());
        gridPanel.add(jb9 = new JButton());
        frame.add(gridPanel, BorderLayout.CENTER);

        all = new JButton[]{jb1, jb2, jb3, jb4, jb5, jb6, jb7, jb8, jb9};
        for (JButton b : all) {
            b.setFont(new Font("Arial", Font.BOLD, 36));
            b.setOpaque(true);
            b.addActionListener(this);
        }

        
        newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font("Arial", Font.BOLD, 16));
        newGameButton.addActionListener(this);
        frame.add(newGameButton, BorderLayout.SOUTH);

        newGame();

        frame.setSize(350, 450);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

   

    // Button khali hai ya nahi?
    private boolean isEmpty(JButton b) {
        return b.getText().equals("");
    }

    // "from" ka number "to" mein bhejo, "from" ko khali karo, moves +1
    private void move(JButton from, JButton to) {
        to.setText(from.getText());
        from.setText("");
        moves++;
    }

    
    private void pressButton(JButton b) {

        if (b == jb1) {
            if (isEmpty(jb2)) {
                move(jb1, jb2);
            } else if (isEmpty(jb4)) {
                move(jb1, jb4);
            }
        }

        if (b == jb2) {
            if (isEmpty(jb1)) {
                move(jb2, jb1);
            } else if (isEmpty(jb3)) {
                move(jb2, jb3);
            } else if (isEmpty(jb5)) {
                move(jb2, jb5);
            }
        }

        if (b == jb3) {
            if (isEmpty(jb2)) {
                move(jb3, jb2);
            } else if (isEmpty(jb6)) {
                move(jb3, jb6);
            }
        }

        if (b == jb4) {
            if (isEmpty(jb1)) {
                move(jb4, jb1);
            } else if (isEmpty(jb5)) {
                move(jb4, jb5);
            } else if (isEmpty(jb7)) {
                move(jb4, jb7);
            }
        }

        if (b == jb5) {
            if (isEmpty(jb2)) {
                move(jb5, jb2);
            } else if (isEmpty(jb4)) {
                move(jb5, jb4);
            } else if (isEmpty(jb6)) {
                move(jb5, jb6);
            } else if (isEmpty(jb8)) {
                move(jb5, jb8);
            }
        }

        if (b == jb6) {
            if (isEmpty(jb3)) {
                move(jb6, jb3);
            } else if (isEmpty(jb5)) {
                move(jb6, jb5);
            } else if (isEmpty(jb9)) {
                move(jb6, jb9);
            }
        }

        if (b == jb7) {
            if (isEmpty(jb4)) {
                move(jb7, jb4);
            } else if (isEmpty(jb8)) {
                move(jb7, jb8);
            }
        }

        if (b == jb8) {
            if (isEmpty(jb5)) {
                move(jb8, jb5);
            } else if (isEmpty(jb7)) {
                move(jb8, jb7);
            } else if (isEmpty(jb9)) {
                move(jb8, jb9);
            }
        }

        if (b == jb9) {
            if (isEmpty(jb6)) {
                move(jb9, jb6);
            } else if (isEmpty(jb8)) {
                move(jb9, jb8);
            }
        }
    }

    
    private void newGame() {

        // Pehle solved board: 1 2 3 4 5 6 7 8 _
        for (int i = 0; i < 8; i++) {
            all[i].setText(String.valueOf(i + 1));
        }
        jb9.setText("");

       
        Random random = new Random();
        do {
            for (int i = 0; i < 300; i++) {
                pressButton(all[random.nextInt(9)]);
            }
        } while (isSolved());

        moves = 0;
        movesLabel.setText("Moves: 0");
        updateColors();
    }

    
    private boolean isSolved() {
        for (int i = 0; i < 8; i++) {
            if (!all[i].getText().equals(String.valueOf(i + 1))) {
                return false;
            }
        }
        return true;   
    }

   
    private void updateColors() {
        Color green = new Color(144, 238, 144);
        for (int i = 0; i < 9; i++) {
            if (isEmpty(all[i])) {
                all[i].setBackground(Color.LIGHT_GRAY);
            } else if (i < 8 && all[i].getText().equals(String.valueOf(i + 1))) {
                all[i].setBackground(green);
            } else {
                all[i].setBackground(Color.WHITE);
            }
        }
    }

    
    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == newGameButton) {
            newGame();
            return;
        }

        pressButton((JButton) ae.getSource());
        movesLabel.setText("Moves: " + moves);
        updateColors();

        if (isSolved()) {
            JOptionPane.showMessageDialog(frame, "You won in " + moves + " moves!");
            newGame();
        }
    }

    public static void main(String[] args) {
        new PuzzleGame();
    }
}