// @authors Xavier Barajas, Ethan Brewer, and Dominic Rucker

import javax.swing.*;

public class InfoViewer {

    public static void main(String[] args) {
        // Create and show the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            InfoFrame frame = new InfoFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("User Information Viewer");
            frame.setVisible(true);
        });
    }
}
