package ui_students;

import pos_creditcard.PointOfSale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrintReceiptListener implements ActionListener {

  private TableListener listenerCurrentTable;
  private PointOfSale pointOfSale;
  private StatusPanel statusPanel;

  public PrintReceiptListener(PointOfSale pointOfSale,
                              StatusPanel statusPanel) {

    this.pointOfSale = pointOfSale;
    this.statusPanel = statusPanel;
  }

  public void setListenerCurrentTable(
      TableListener actionListener) {

    listenerCurrentTable = actionListener;
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {

    if (listenerCurrentTable != null) {

      if (listenerCurrentTable.hasASale()) {

        int id = listenerCurrentTable.getSaleId();

        pointOfSale.printReceiptOfSale(id);

        statusPanel.refresh("Receipt printed");

      } else {

        statusPanel.refresh(
            "Current table has no sale");
      }
    }
  }
}