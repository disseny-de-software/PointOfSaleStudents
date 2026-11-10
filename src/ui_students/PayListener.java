package ui_students;

import pos_creditcard.PointOfSale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayListener implements ActionListener {

  private TableListener listenerCurrentTable;
  private AmountListener amountListener;
  private PointOfSale pointOfSale;
  private StatusPanel statusPanel;

  public PayListener(PointOfSale pointOfSale,
                     AmountListener amountListener,
                     StatusPanel statusPanel) {

    this.pointOfSale = pointOfSale;
    this.amountListener = amountListener;
    this.statusPanel = statusPanel;
  }

  public void setListenerCurrentTable(
      TableListener tableListener) {

    listenerCurrentTable = tableListener;
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {

    if (listenerCurrentTable != null) {

      if (listenerCurrentTable.hasASale()) {

        int idSale =
            listenerCurrentTable.getSaleId();

        double paidAmount =
            amountListener.getPaidAmount();

        if (paidAmount <= 0) {

          statusPanel.refresh(
              "Invalid payment amount");

          return;
        }

        if (!pointOfSale.isAmountHandedEnough(idSale, paidAmount)) {

          statusPanel.refresh("Amount is not enough");
          return;
        }

        if (!pointOfSale.saleHasProducts(idSale)) {

          statusPanel.refresh("No ordered products yet");
          return;
        }

        if (!pointOfSale.isSalePaid(idSale)) {

          pointOfSale.payOneSaleCash(
              idSale,
              paidAmount);

          pointOfSale.printPayment(idSale);

          amountListener.setText("0.0 €");

          statusPanel.setState("PAID");

          statusPanel.refresh(
              "Sale paid");

          listenerCurrentTable.clearSale();

        } else {

          statusPanel.refresh(
              "Sale already paid");
        }
      } else {
        statusPanel.refresh(
            "Current table has no sale");
      }
    }
  }
}