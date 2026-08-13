package ui_students;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class TableListener implements ActionListener {
  private JButton table;
  private int saleId;
  private boolean hasASale = false;

  private NewSaleListener newSaleListener;
  private PayListener payListener;
  private PrintReceiptListener printReceiptListener;
  private ArrayList<ProductListener> productListeners;

  public TableListener(JButton table,
                       NewSaleListener newSaleListener,
                       PayListener payListener,
                       PrintReceiptListener printReceiptListener,
                       ArrayList<ProductListener> productListeners) {
    this.table = table;
    this.newSaleListener =newSaleListener;
    this.payListener = payListener;
    this.printReceiptListener = printReceiptListener;
    this.productListeners = productListeners;
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    System.out.println("Pressed table " + table.getLabel() + " button");
    // tell other buttons (ie, their listeners) that this table has been selected,
    // and therefore the current sale is it the sale yet to be paid associated to
    // it, if any.
    // The id of this current sale is in the action listener of this table button.
    newSaleListener.setListenerCurrentTable(this);
    printReceiptListener.setListenerCurrentTable(this);
    payListener.setListenerCurrentTable(this);
    for (ProductListener productListener : productListeners) {
      productListener.setListenerCurrentTable(this);
    }
  }

  public int getSaleId() {
    return saleId;
  }

  public void setSaleId(int saleId) {
    this.saleId = saleId;
    hasASale = true;
  }

  public boolean hasASale() {
    return hasASale;
  }

  public void clearSale() {
    hasASale = false;
  }

  public String getTableId() {
    return table.getText();
  }
}
