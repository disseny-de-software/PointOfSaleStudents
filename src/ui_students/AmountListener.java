package ui_students;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AmountListener implements ActionListener {
  private JTextField textField;
  private double paidAmount;

  public AmountListener(JTextField textField) {
    this.textField = textField;
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    System.out.println("Entered amount");
    paidAmount = Double.parseDouble(textField.getText());
  }

  public double getPaidAmount() {
    return paidAmount;
  }

  public void setText(String text) {
    textField.setText(text);
  }
}
