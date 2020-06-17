
package project3simse;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

/*
The GUI class first establishes a method to create the GUI that is instantized in the Main method.
Next the action performed method establishes what should happen when the Perform Sort button is pressed.
More on that below.
*/

public class GUI extends JPanel implements ActionListener {

    private JTextField input;
    private JTextField output;
    private int sortOrder;
    private String numericType;

    public GUI() {

    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    JPanel originalListPanel = new JPanel();
    originalListPanel.add(new JLabel("Original List"));
    input = new JTextField(20);
    originalListPanel.add(input);
    add(originalListPanel);
    add(Box.createVerticalStrut(15));


    JPanel sortedListPanel = new JPanel();
    sortedListPanel.add(new JLabel("Sorted List"));
    output = new JTextField(20);
    output.setEditable(false);
    sortedListPanel.add(output);
    add(sortedListPanel);
    add(Box.createVerticalStrut(15));


    JPanel buttonPanel = new JPanel();
    JButton performSort = new JButton("Perform Sort");
    buttonPanel.add(performSort);
    add(buttonPanel);
    add(Box.createVerticalStrut(15));
    performSort.addActionListener(this);


    JPanel radioButtonsPanel = new JPanel();
    radioButtonsPanel.setLayout(new BoxLayout(radioButtonsPanel, BoxLayout.X_AXIS));
    radioButtonsPanel.add(createTitledPanel("Sort Order", new JRadioButton("Ascending", true), new JRadioButton("Descending")));
    this.sortOrder = 1;
    radioButtonsPanel.add(createTitledPanel("Numeric Type", new JRadioButton("Integer", true), new JRadioButton("Fraction")));
    this.numericType = "Integer";
    add(radioButtonsPanel);

    
    }

    private JPanel createTitledPanel(String title, JRadioButton buttonOne, JRadioButton buttonTwo) {

        JPanel TitlePanel = new JPanel();
        TitlePanel.setLayout(new BoxLayout(TitlePanel, BoxLayout.Y_AXIS));
        TitlePanel.setMaximumSize(new Dimension(200, 100));
        TitlePanel.setBorder(BorderFactory.createTitledBorder(title));
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonOne.addActionListener(this);
        TitlePanel.add(buttonOne);
        buttonGroup.add(buttonOne);
        buttonTwo.addActionListener(this);
        TitlePanel.add(buttonTwo);
        buttonGroup.add(buttonTwo);
        
        return TitlePanel;
        
    }
    
    /*
    In the action performed method, the status of the JRadioButtons is used to determine what type 
    of variable to pass into the BinarySearchTree class. If an Integer is passed the standard compareTo
    method can be used to sort the Integers to the correct position in the tree. This sorting does not balance the tree
    as it inserts new Integers (or fractions) which means the very first input from the user will be the 
    root node and all others will be sorted accordingly. This is acceptable for a program of this type where
    the input is not expected to be long, however the big O notation of searching the tree would not be great
    if the input was much longer than it is. If fraction is selected then a variable type defined by the 
    Fraction class is used to create the fractionTree, then Fraction variables are passed into the binary search 
    tree class to sort the fractions. This method also catches non-numeric inputs and outputs an error message.
    */
    
    @Override
    public void actionPerformed(ActionEvent mathAction) {
        String operators = mathAction.getActionCommand();
        if (operators.equals("Ascending"))
            sortOrder = 1;
        else if (operators.equals("Descending"))
            sortOrder = -1;
        else if (operators.equals("Integer") || operators.equals("Fraction"))
            numericType = operators;
        else if (operators.equals("Perform Sort")) {
            if (!input.getText().trim().equals("")) {
                try {
                    if (numericType.equals("Integer")) {
                        BinarySearchTree<Integer> integerTree = new BinarySearchTree<Integer>();
                        String[] inputNum = input.getText().trim().split("\\s+");
                        for (String num : inputNum) {
                              integerTree.insert(Integer.parseInt(num));
                        }
                        output.setText(integerTree.sort(this.sortOrder));
                    } else if (numericType.equals("Fraction")) {
                        BinarySearchTree<Fraction> fractionTree = new BinarySearchTree<Fraction>();
                        String[] inputNum = input.getText().trim().split("\\s+");
                        for (String num : inputNum) {
                              fractionTree.insert(new Fraction(num));
                        }
                        output.setText(fractionTree.sort(this.sortOrder));
                    }
                } catch (NumberFormatException e) {
                    final JPanel panel = new JPanel();
                    JOptionPane.showMessageDialog(panel, "Non-Numeric Input", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
}
      
}
