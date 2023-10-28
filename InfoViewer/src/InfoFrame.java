// authors Dominic Rucker

// Import necessary libraries for creating a graphical user interface
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

// Define a class named InfoFrame that extends JFrame and implements ActionListener
public class InfoFrame extends JFrame implements ActionListener {

    // Define constants for the frame dimensions
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;

    // Declare various GUI components as instance variables
    private JLabel buttonOutput; // Label to display the selected marital status
    private JLabel majorOutput;  // Label to display the selected major
    private JLabel textOutput;   // Label to display the entered name
    private JRadioButton singleButton, marriedButton, separatedButton; // Radio buttons for marital status
    private JTextField textField; // Text field for entering the name
    private JComboBox comboBox;  // Combo box for selecting a major

    // Constructor for the InfoFrame class
    public InfoFrame() {
        // Initialize labels with default values
        textOutput = new JLabel("Name");
        buttonOutput = new JLabel("Marital Status");
        majorOutput = new JLabel("Major");
        JLabel userInfo = new JLabel("User Information:");

        // Create the control panel containing user input components
        createControlPanel();

        // Create a panel for labels and arrange them vertically
        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));

        labelsPanel.add(userInfo);
        labelsPanel.add(textOutput);
        labelsPanel.add(majorOutput);
        labelsPanel.add(buttonOutput);

        // Add the labels panel to the frame's west side and set the frame size
        add(labelsPanel, BorderLayout.WEST);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    // Create the control panel containing user input components
    private void createControlPanel() {
        JPanel controlPanel = new JPanel(new GridLayout(3, 1));

        // Create panels for name text field, major combo box, and marital status radio buttons
        JPanel namePanel = createTextField();
        JPanel majorPanel = createComboBox();
        JPanel maritalStatusPanel = createRadioButtons();

        // Add these panels to the control panel
        controlPanel.add(namePanel);
        controlPanel.add(majorPanel);
        controlPanel.add(maritalStatusPanel);

        // Add the control panel to the frame's south side
        add(controlPanel, BorderLayout.SOUTH);
    }

    // Create a panel for entering the user's name
    public JPanel createTextField() {
        JPanel nameTextFieldPanel = new JPanel();
        JLabel nameLabel = new JLabel("Your Name: ");
        nameTextFieldPanel.add(nameLabel);
        textField = new JTextField(20);
        nameTextFieldPanel.add(textField);
        nameTextFieldPanel.setBorder(new TitledBorder(new EtchedBorder(), "Name"));

        // Add a DocumentListener to the textField to update the textOutput label
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTextOutput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTextOutput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTextOutput();
            }

            // Method to update the textOutput label based on text input
            private void updateTextOutput() {
                String text = textField.getText().trim();
                if (text.isEmpty()) {
                    textOutput.setText("Name");
                } else {
                    textOutput.setText(text);
                }
            }
        });
        return nameTextFieldPanel;
    }

    // Create a panel for selecting the user's major
    public JPanel createComboBox() {
        String major[] = {"Biology", "Computer Science", "Nursing"};
        comboBox = new JComboBox<>(major);
        comboBox.setSelectedIndex(-1); // Set initial selection to none
        comboBox.addActionListener(this);
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.add(comboBox);
        comboBoxPanel.setBorder(new TitledBorder(new EtchedBorder(), "Major"));
        return comboBoxPanel;
    }

    // Create a panel for selecting the user's marital status
    public JPanel createRadioButtons() {
        // Create JRadioButtons for the "Single," "Married," and "Separated" options
        singleButton = new JRadioButton("Single");
        singleButton.addActionListener(this);

        marriedButton = new JRadioButton("Married");
        marriedButton.addActionListener(this);

        separatedButton = new JRadioButton("Separated");
        separatedButton.addActionListener(this);

        // Create a ButtonGroup to ensure that only one radio button can be selected at a time
        ButtonGroup group = new ButtonGroup();
        group.add(singleButton);
        group.add(marriedButton);
        group.add(separatedButton);

        // Create a JPanel to contain the radio buttons with a titled border
        JPanel maritalStatusPanel = new JPanel();
        maritalStatusPanel.add(singleButton);
        maritalStatusPanel.add(marriedButton);
        maritalStatusPanel.add(separatedButton);
        maritalStatusPanel.setBorder(new TitledBorder(new EtchedBorder(), "Marital Status"));

        // Return the JPanel containing the radio buttons with the "Marital Status" titled border
        return maritalStatusPanel;
    }

    // Handle action events (e.g., radio button and combo box selection)
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == singleButton) {
            buttonOutput.setText("Single");
        }
        if (e.getSource() == marriedButton) {
            buttonOutput.setText("Married");
        }
        if (e.getSource() == separatedButton) {
            buttonOutput.setText("Separated");
        }
        if (e.getSource() == comboBox) {
            String selectedMajor = (String) comboBox.getSelectedItem();
            majorOutput.setText(selectedMajor);
        }
    }
}
