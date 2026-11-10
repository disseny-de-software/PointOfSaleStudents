package ui_students;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JTextArea {

  private String currentTable = "-";
  private String currentSale = "-";
  private int currentNumProducts = 0;
  private String currentState = "NO SALE";

  public StatusPanel() {
    setEditable(false);
    setFont(new Font("Monospaced", Font.PLAIN, 12));
    refresh("Application started");
  }

  public void setTable(String table) {
    currentTable = table;
  }

  public void setSale(String sale) {
    currentSale = sale;
  }

  public void setState(String state) {
    currentState = state;
  }

  public void increaseNumProducts() {
    currentNumProducts++;
  }

  public void refresh(String message) {

    setText(
        "CURRENT STATUS\n" +
            "=====================\n\n" +
            "Table : " + currentTable + "\n" +
            "Sale  : " + currentSale + "\n" +
            "Num. products : " + currentNumProducts + "\n" +
            "State : " + currentState + "\n\n" +
            "Message:\n" +
            message + "\n"
    );
  }
}