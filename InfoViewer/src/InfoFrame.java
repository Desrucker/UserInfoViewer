import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class InfoFrame extends JFrame implements ActionListener {

    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
    private JLabel buttonOutput;
    private JLabel majorOutput;
    private JLabel textOutput;
    private JRadioButton singleButton, marriedButton, separatedButton;
    private JTextField textField;
    private JComboBox comboBox;

    public InfoFrame() {
        // Initialize labels with default values
        textOutput = new JLabel("Dominic Rucker");
        buttonOutput = new JLabel("Single");
        majorOutput = new JLabel("Finance");
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

        // Create panels for radio buttons, text field, and combo box
        JPanel maritalStatusPanel = createRadioButtons();
        JPanel namePanel = createTextField();
        JPanel majorPanel = createComboBox();

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

            private void updateTextOutput() {
                textOutput.setText(textField.getText());
            }
        });

        return nameTextFieldPanel;
    }

    // Create a panel for selecting the user's major
    public JPanel createComboBox() {
        String major[] = {"Computer Science", "Biology", "Nursing"};
        comboBox = new JComboBox<>(major);
        comboBox.addActionListener(this);
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.add(comboBox);
        comboBoxPanel.setBorder(new TitledBorder(new EtchedBorder(), "Major"));
        return comboBoxPanel;
    }

    // Create a panel for selecting the user's marital status
    public JPanel createRadioButtons() {
        singleButton = new JRadioButton("Single");
        singleButton.addActionListener(this);
        singleButton.setSelected(true);
        marriedButton = new JRadioButton("Married");
        marriedButton.addActionListener(this);
        separatedButton = new JRadioButton("Separated");
        separatedButton.addActionListener(this);
        ButtonGroup group = new ButtonGroup();
        group.add(singleButton);
        group.add(marriedButton);
        group.add(separatedButton);
        JPanel maritalStatusPanel = new JPanel();
        maritalStatusPanel.add(singleButton);
        maritalStatusPanel.add(marriedButton);
        maritalStatusPanel.add(separatedButton);
        maritalStatusPanel.setBorder(new TitledBorder(new EtchedBorder(), "Marital Status"));
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