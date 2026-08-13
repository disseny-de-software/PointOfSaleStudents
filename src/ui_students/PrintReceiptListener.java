package ui_students;

import pos_creditcard.PointOfSale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrintReceiptListener implements ActionListener {
  private TableListener listenerCurrentTable;
  private PointOfSale pointOfSale;

  public PrintReceiptListener(PointOfSale pointOfSale) {
    //this.mediator = mediator;
    this.pointOfSale = pointOfSale;
  }

  public void setListenerCurrentTable(TableListener actionListener) {
    listenerCurrentTable = actionListener;
    // knows the id of its current sale, if any
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    System.out.println("Pressed Receipt button");
    if (listenerCurrentTable != null) {
      if (listenerCurrentTable.hasASale()) {
        int id = listenerCurrentTable.getSaleId();
        pointOfSale.printReceiptOfSale(id);
      } else {
        System.out.println("Table " + listenerCurrentTable.getTableId() + " has no sale yet");
      }
    }
  }
}