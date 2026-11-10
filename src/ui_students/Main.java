package ui_students;

import pos_creditcard.PointOfSale;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
  private static final int BUTTON_WIDTH = 100;
  private static final int BUTTON_HEIGHT = 100;
  private static final int X0 = 40;
  private static final int Y0 = 60;
  private static final int VERTICAL_SEPARATION = 30;
  private static final int HORIZONTAL_SEPARATION = 60;
  private static final int FRAME_WIDTH = 1150;
  private static final int FRAME_HEIGHT = 650;
  private static final int SCROLL_PANEL_WIDTH = 260;
  private static final int SCROLL_PANEL_HEIGHT = 240;
  private static final int LABEL_AMOUNT_WIDTH = 150;
  private static final int LABEL_AMOUNT_HEIGHT = 20;
  private static final int TEXT_AMOUNT_SEPARATION = 25;
  private static final int TEXT_AMOUNT_WIDTH = 120;
  private static final int NUM_ROWS_PRODUCTS = 5;
  private static final int NUM_COLS_PRODUCTS = 2;

  private static final int NUM_ROWS_TABLES = 4;
  private static final int NUM_COLS_TABLES = 2;


  private static int[] position(int row, int col) {
    int[] pos = new int[2];
    pos[0] = X0 + col * (BUTTON_WIDTH + HORIZONTAL_SEPARATION);
    pos[1] = Y0 + row * (BUTTON_HEIGHT + VERTICAL_SEPARATION);
    return pos;
  }

  private static JButton makeButton(int row, int col, String label) {
    JButton button = new JButton(label);
    int[] pos = position(row, col);
    button.setBounds(pos[0], pos[1], BUTTON_WIDTH, BUTTON_HEIGHT);
    return button;
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Point of Sale");
    frame.setLayout(null);
    PointOfSale pointOfSale = new PointOfSale();

    //
    // Status panel
    //
    StatusPanel statusPanel =
        new StatusPanel();

    JScrollPane scrollPane =
        new JScrollPane(statusPanel);

    int[] posStatus = position(0, 5);

    scrollPane.setBounds(
        posStatus[0],
        posStatus[1],
        SCROLL_PANEL_WIDTH,
        SCROLL_PANEL_HEIGHT);

    frame.add(scrollPane);

    // Amount field
    String euroSymbol = "\u20AC";
    JLabel labelAmount = new JLabel("Amount paid");
    int[] posAmount = position(2, 5);
    labelAmount.setBounds(
        posAmount[0],
        posAmount[1],
        LABEL_AMOUNT_WIDTH,
        LABEL_AMOUNT_HEIGHT);

    frame.add(labelAmount);

    JTextField textAmount = new JTextField();
    textAmount.setText("0.0 " + euroSymbol);

    textAmount.setBounds(
        posAmount[0],
        posAmount[1] + TEXT_AMOUNT_SEPARATION,
        TEXT_AMOUNT_WIDTH,
        BUTTON_HEIGHT / 2);

    AmountListener amountListener =
        new AmountListener(textAmount);

    textAmount.addActionListener(amountListener);

    frame.add(textAmount);

    //
    // Command buttons
    //

    JButton buttonNewSale =
        makeButton(0, 2, "New sale");

    buttonNewSale.setBackground(Color.CYAN);

    NewSaleListener newSaleListener =
        new NewSaleListener(
            pointOfSale,
            statusPanel);

    buttonNewSale.addActionListener(
        newSaleListener);

    frame.add(buttonNewSale);

    JButton buttonReceipt =
        makeButton(1, 2, "Receipt");

    buttonReceipt.setBackground(Color.YELLOW);

    PrintReceiptListener printReceiptListener =
        new PrintReceiptListener(
            pointOfSale,
            statusPanel);

    buttonReceipt.addActionListener(
        printReceiptListener);

    frame.add(buttonReceipt);

    JButton buttonPay =
        makeButton(2, 2, "Pay");

    buttonPay.setBackground(Color.ORANGE);

    PayListener payListener =
        new PayListener(
            pointOfSale,
            amountListener,
            statusPanel);

    buttonPay.addActionListener(payListener);

    frame.add(buttonPay);

    //
    // Product buttons
    //

    ArrayList<String> productNames =
        pointOfSale.getProductNames();

    ArrayList<ProductListener> productListeners =
        new ArrayList<>();

    int numProducts = productNames.size();

    assert numProducts <=
        NUM_ROWS_PRODUCTS * NUM_COLS_PRODUCTS;

    int nProduct = 0;

    for (int row = 0; row < NUM_ROWS_PRODUCTS; row++) {

      for (int col = 0; col < NUM_COLS_PRODUCTS; col++) {

        if (nProduct < numProducts) {

          String productName =
              productNames.get(nProduct);

          JButton buttonProduct =
              makeButton(row, col + 3, "");

          ImageIcon icon =
              new ImageIcon(
                  "./images/" +
                      productName +
                      ".png");

          if (icon.getIconWidth() > 0) {
            buttonProduct.setIcon(icon);
          } else {
            buttonProduct.setText(productName);
          }

          ProductListener productListener =
              new ProductListener(
                  productName,
                  pointOfSale,
                  statusPanel);

          buttonProduct.addActionListener(
              productListener);

          productListeners.add(
              productListener);

          frame.add(buttonProduct);

          nProduct++;
        }
      }
    }

    //
    // Table buttons
    //

    int numTables = 6;
    assert numTables <=
        NUM_ROWS_TABLES * NUM_COLS_TABLES;

    int nTable = 0;

    for (int row = 0; row < NUM_ROWS_TABLES; row++) {

      for (int col = 0; col < NUM_COLS_TABLES; col++) {

        if (nTable < numTables) {

          String idTable =
              "T" + (row + 1) + (col + 1);

          JButton table =
              makeButton(
                  row,
                  col,
                  idTable);

          TableListener tableListener =
              new TableListener(
                  table,
                  newSaleListener,
                  payListener,
                  printReceiptListener,
                  productListeners,
                  statusPanel);

          table.addActionListener(
              tableListener);

          frame.add(table);

          nTable++;
        }
      }
    }

    //
    // Frame
    //

    frame.setSize(
        FRAME_WIDTH,
        FRAME_HEIGHT);

    frame.setLocationRelativeTo(null);

    frame.setDefaultCloseOperation(
        JFrame.EXIT_ON_CLOSE);

    frame.setVisible(true);
  }
}