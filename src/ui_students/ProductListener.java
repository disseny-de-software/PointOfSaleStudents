package ui_students;

import pos_creditcard.PointOfSale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ProductListener implements ActionListener {
  private String productName;
  private TableListener listenerCurrentTable;
  private PointOfSale pointOfSale;

  public ProductListener(String productName, PointOfSale pointOfSale) {
    this.productName = productName;
    this.pointOfSale = pointOfSale;
  }

  public void setListenerCurrentTable(TableListener tableListener) {
    listenerCurrentTable = tableListener;
    // knows the id of its current sale, if any
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    System.out.println("Pressed product " + productName + " button");
    if (listenerCurrentTable != null) {
      if (listenerCurrentTable.hasASale()) {
        int idSale = listenerCurrentTable.getSaleId();
        pointOfSale.addLineItemToSale(idSale, productName, 1);
      } else {
        System.out.println("Table " + listenerCurrentTable.getTableId() + " has no sale yet");
      }
    }
  }
}
