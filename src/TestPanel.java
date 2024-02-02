/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
public class TestPanel extends JFrame {

    private final JTextField[] activityScores = new JTextField[5];
    private final JTextField[] labScores = new JTextField[5];
    private final JTextField[] examScores = new JTextField[2];
    private final JLabel gradeLabel = new JLabel();
    private final JLabel activityLabel = new JLabel();
    private final JLabel laboratoryLabel = new JLabel();
    private final JLabel examLabel = new JLabel();
    public TestPanel(){

        setTitle("Grading System");
        setSize(500,500);
        setLayout(new GridLayout(1, 3, 10, 10));
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(15, 15, 15, 15));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(createInputPanel(activityScores, "Activity Score: "));
        add(createInputPanel(labScores, "Lab Score: "));
        add(createInputPanel(examScores, "Exam Score: "));
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        JButton calculateButton = new JButton("Calculate Final Grade");
        calculateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        calculateButton.addActionListener((ActionEvent e) -> {
            calculateFinalGrade();
        });
        JButton actButton = new JButton("Calculate Activity Grade");
        actButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        actButton.addActionListener((ActionEvent e) -> {
            calculateFinalGrade();
        });
        JButton labButton = new JButton("Calculate Laboratory Grade");
        labButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        labButton.addActionListener((ActionEvent e) -> {
            calculateFinalGrade();
        });
        JButton examButton = new JButton("Calculate Exam Grade");
        examButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        examButton.addActionListener((ActionEvent e) -> {
            calculateFinalGrade();
        });
        bottomPanel.add(calculateButton);
        bottomPanel.add(actButton);
        bottomPanel.add(labButton);
        bottomPanel.add(examButton);
        bottomPanel.add(Box.createVerticalStrut(10)); // Spacer
        activityLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        laboratoryLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        examLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        gradeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(gradeLabel);
        bottomPanel.add(activityLabel);
        bottomPanel.add(laboratoryLabel);
        bottomPanel.add(examLabel);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);

    }
    private JPanel createInputPanel(JTextField[] fields, String labelBase) {
        JPanel panel = new JPanel(new GridLayout(fields.length, 2));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(labelBase));
        for (int i = 0; i < fields.length; i++) {
            JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel label = new JLabel((i + 1) + ": ");
            fields[i] = new JTextField(5);
            rowPanel.add(label);
            rowPanel.add(fields[i]);
            panel.add(rowPanel);
        }
        return panel;
    }
    private void calculateFinalGrade() {
            double activitiesAverage = calculateAverage(activityScores);;
            activityLabel.setText(String.format("Activity Grade is: %.2f",activitiesAverage));
            double labsAverage = calculateAverage(labScores);
            laboratoryLabel.setText(String.format("Laboratory Grade is: %.2f",labsAverage));
            double examsAverage = calculateAverage(examScores);
            examLabel.setText(String.format("Exam Grade is: %.2f",examsAverage));
            double finalGrade = (activitiesAverage * 0.3) + (labsAverage * 0.3) + (examsAverage * 0.4);
            gradeLabel.setText(String.format("Final Grade: %.2f", finalGrade));
            if (finalGrade < 70) {
                gradeLabel.setForeground(Color.RED);
            } else {
                gradeLabel.setForeground(Color.BLACK);
            }
    }
    private double calculateAverage(JTextField[] scoreFields) {
        double sum = 0;
        int count = 0;
        for (JTextField field : scoreFields) {
            try {
                var score = field.getText();
                sum += Double.parseDouble(field.getText());
                count++;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for " + field.getName(), "Input Error", JOptionPane.ERROR_MESSAGE);
                return 0;
            }
        }
        return (count > 0) ? (sum / count) : 0;
    }
}

