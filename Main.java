
package project3simse;

import javax.swing.JFrame;

/*
The main class creates a JFrame and instantizes the GUI
which includes all functional operations of the code
*/

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Binary Search Tree Sort");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(390,300);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(new GUI());
        frame.setVisible(true);
      }
}
