package ui_students;

import pos_creditcard.PointOfSale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductListener implements ActionListener {

  private String productName;
  private TableListener listenerCurrentTable;
  private PointOfSale pointOfSale;
  private StatusPanel statusPanel;

  public ProductListener(String productName,
                         PointOfSale pointOfSale,
                         StatusPanel statusPanel) {

    this.productName = productName;
    this.pointOfSale = pointOfSale;
    this.statusPanel = statusPanel;
  }

  public void setListenerCurrentTable(TableListener tableListener) {
    listenerCurrentTable = tableListener;
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {

    if (listenerCurrentTable != null) {

      if (listenerCurrentTable.hasASale()) {

        int idSale = listenerCurrentTable.getSaleId();

        pointOfSale.addLineItemToSale(
            idSale,
            productName,
            1);

        statusPanel.increaseNumProducts();

        statusPanel.refresh(
            "Added product: " + productName);

      } else {

        statusPanel.refresh(
            "Open a sale before adding products");
      }
    }
  }
}