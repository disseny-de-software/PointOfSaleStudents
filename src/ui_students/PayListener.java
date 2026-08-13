package ui_students;

import pos_creditcard.PointOfSale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PayListener implements ActionListener {
  private TableListener listenerCurrentTable;
  private AmountListener amountListener;
  private PointOfSale pointOfSale;

  public PayListener(PointOfSale pointOfSale, AmountListener amountListener) {
    this.pointOfSale = pointOfSale;
    this.amountListener = amountListener; // has the amount paid for the sale of current table
  }

  public void setListenerCurrentTable(TableListener tableListener) {
    listenerCurrentTable = tableListener;
    // knows the id of its current sale, if any
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    System.out.println("Pressed Pay button");
    if (listenerCurrentTable != null) {
      if (listenerCurrentTable.hasASale()) {
        int idSale = listenerCurrentTable.getSaleId();
        double paidAmount = amountListener.getPaidAmount();
        if (!pointOfSale.isSalePaid(idSale) && (paidAmount > 0)) {
          pointOfSale.payOneSaleCash(idSale, paidAmount);

          pointOfSale.printPayment(idSale);
          //paidAmount = 0;
          amountListener.setText("0.0");
          listenerCurrentTable.clearSale();
        } else {
          System.out.println("Sale of table " + listenerCurrentTable.getTableId()
              + " has already been paid");
        }
      }
    }
  }
}
