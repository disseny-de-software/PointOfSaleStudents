package ui_students;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AmountListener implements ActionListener {

  private JTextField textField;

  public AmountListener(JTextField textField) {
    this.textField = textField;
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    System.out.println("Entered amount");
  }

  public double getPaidAmount() {

    try {

      String text = textField.getText();

      text = text.replace("€", "");
      text = text.trim();

      return Double.parseDouble(text);

    } catch (Exception e) {

      return 0.0;
    }
  }

  public void setText(String text) {
    textField.setText(text);
  }
}