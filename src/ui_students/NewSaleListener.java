package ui_students;

import pos_creditcard.PointOfSale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class NewSaleListener implements ActionListener {
  private TableListener listenerCurrentTable;
  private PointOfSale pointOfSale;

  public NewSaleListener(PointOfSale pointOfSale) {
    this.pointOfSale = pointOfSale;
  }

  public void setListenerCurrentTable(TableListener actionListener) {
    listenerCurrentTable = actionListener;
    // knows the id of its current sale, if any
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    System.out.println("Pressed New sale button");
    if (listenerCurrentTable != null) {
      if (listenerCurrentTable.hasASale()) {
        int currentSaleId = listenerCurrentTable.getSaleId();
        if (pointOfSale.isSalePaid(currentSaleId)) {
          int saleId = pointOfSale.makeNewSale();
          listenerCurrentTable.setSaleId(saleId);
        } else {
          System.out.println("Current sale of table "
              + listenerCurrentTable.getTableId()
              + " has not been paid yet");
        }
      } else {
        int saleId = pointOfSale.makeNewSale();
        listenerCurrentTable.setSaleId(saleId);
      }
    }
  }
}
