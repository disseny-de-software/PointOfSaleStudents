package ui_students;

import pos_creditcard.PointOfSale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class NewSaleListener implements ActionListener {

  private TableListener listenerCurrentTable;
  private PointOfSale pointOfSale;
  private StatusPanel statusPanel;

  public NewSaleListener(PointOfSale pointOfSale,
                         StatusPanel statusPanel) {

    this.pointOfSale = pointOfSale;
    this.statusPanel = statusPanel;
  }

  public void setListenerCurrentTable(TableListener actionListener) {
    listenerCurrentTable = actionListener;
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {

    if (listenerCurrentTable == null) {

      statusPanel.refresh("Select a table first");
      return;
    }

    if (listenerCurrentTable.hasASale()) {

      int currentSaleId = listenerCurrentTable.getSaleId();

      if (pointOfSale.isSalePaid(currentSaleId)) {

        int saleId = pointOfSale.makeNewSale();

        listenerCurrentTable.setSaleId(saleId);

        statusPanel.setSale(String.valueOf(saleId));
        statusPanel.setState("UNPAID");

        statusPanel.refresh("New sale created");

      } else {

        statusPanel.setSale(String.valueOf(currentSaleId));
        statusPanel.setState("UNPAID");

        statusPanel.refresh(
            "Current sale must be paid first");
      }

    } else {

      int saleId = pointOfSale.makeNewSale();

      listenerCurrentTable.setSaleId(saleId);

      statusPanel.setSale(String.valueOf(saleId));
      statusPanel.setState("UNPAID");

      statusPanel.refresh("New sale created");
    }
  }
}