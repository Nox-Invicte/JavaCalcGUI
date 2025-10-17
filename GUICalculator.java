import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Simple Event-Driven Calculator
 * Basic Java Swing GUI demonstrating event-driven programming
 */
public class GUICalculator extends JFrame implements ActionListener {
    
    // GUI Components
    private JTextField display;
    private JButton[] buttons;
    
    // Calculator variables
    private double num1 = 0;
    private double num2 = 0;
    private char operator = ' ';
    
    // Constructor
    public GUICalculator() {
        createGUI();
        this.setTitle("Simple Calculator");
        this.setSize(300, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    private void createGUI() {
        // Create display
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        
        // Create buttons
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };
        
        buttons = new JButton[16];
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
        
        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 18));
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
        }
        
        // Layout
        this.setLayout(new BorderLayout());
        this.add(display, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if (command.equals("C")) {
            // Clear
            display.setText("0");
            num1 = 0;
            num2 = 0;
            operator = ' ';
        } else if (command.equals("=")) {
            // Calculate
            num2 = Double.parseDouble(display.getText());
            double result = 0;
            
            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': 
                    if (num2 != 0) result = num1 / num2;
                    else { display.setText("Error"); return; }
                    break;
            }
            
            display.setText(String.valueOf(result));
            operator = ' ';
        } else if (command.equals("+") || command.equals("-") || 
                   command.equals("*") || command.equals("/")) {
            // Operator
            num1 = Double.parseDouble(display.getText());
            operator = command.charAt(0);
            display.setText("0");
        } else {
            // Number
            if (display.getText().equals("0")) {
                display.setText(command);
            } else {
                display.setText(display.getText() + command);
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Simple Event-Driven Calculator ===");
        System.out.println("Click buttons to perform calculations!");
        
        SwingUtilities.invokeLater(() -> new GUICalculator());
    }
}