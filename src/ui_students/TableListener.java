package ui_students;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class TableListener implements ActionListener {

  private static JButton selectedButton = null;

  private JButton table;

  private int saleId;

  private boolean hasASale = false;

  private NewSaleListener newSaleListener;

  private PayListener payListener;

  private PrintReceiptListener printReceiptListener;

  private ArrayList<ProductListener> productListeners;

  private StatusPanel statusPanel;

  public TableListener(
      JButton table,
      NewSaleListener newSaleListener,
      PayListener payListener,
      PrintReceiptListener printReceiptListener,
      ArrayList<ProductListener> productListeners,
      StatusPanel statusPanel) {

    this.table = table;
    this.newSaleListener = newSaleListener;
    this.payListener = payListener;
    this.printReceiptListener =
        printReceiptListener;
    this.productListeners =
        productListeners;
    this.statusPanel = statusPanel;
  }

  @Override
  public void actionPerformed(
      ActionEvent actionEvent) {

    if (selectedButton != null) {
      selectedButton.setBackground(null);
    }

    table.setBackground(Color.GREEN);

    selectedButton = table;

    newSaleListener.setListenerCurrentTable(this);

    printReceiptListener
        .setListenerCurrentTable(this);

    payListener
        .setListenerCurrentTable(this);

    for (ProductListener productListener :
        productListeners) {

      productListener
          .setListenerCurrentTable(this);
    }

    statusPanel.setTable(getTableId());

    if (hasASale) {

      statusPanel.setSale(
          String.valueOf(saleId));

      statusPanel.setState("UNPAID");

    } else {

      statusPanel.setSale("-");
      statusPanel.setState("NO SALE");
    }

    statusPanel.refresh("Table selected");
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