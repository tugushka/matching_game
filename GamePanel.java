import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.*;

import java.awt.*;

public class GamePanel extends JPanel{
  static int rows = 4;
  static int columns = 5;
  static int totalCards = rows*columns;
  
  AtomicBoolean mismatchCardsWaiting;

  Card[] cards;
  Card lastFlippedCard;
  int pairsLeftCount;

  JButton resetButton;
  JLabel scoreLabel;
  JPanel boardPanel;
  JPanel sidePanel;

  GamePanel() {
    this.setSize(800, 600);
    this.setBackground(Color.LIGHT_GRAY);

    // This variable is used to block the input from the user when mismatched
    // pair is clicked.
    mismatchCardsWaiting = new AtomicBoolean();

    boardPanel = new JPanel();
    boardPanel.setLayout(new GridLayout(rows, columns));
    addCards();
    
    resetButton = new JButton("New game");
    resetButton.addActionListener(l -> {
      resetGame();
    });

    scoreLabel = new JLabel();
    
    sidePanel = new JPanel();
    sidePanel.add(resetButton);
    sidePanel.add(scoreLabel);
    sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

    this.add(boardPanel);
    this.add(sidePanel);

    this.setLayout(new FlowLayout());

    resetGame();
  }

  private void addCards() {
    cards = new Card[totalCards];
    for(int i = 0 ; i < totalCards ; i++){
      // Cards will get name when the game is reset
      cards[i] = new Card(i, "");

      // This is the callback function for clicking
      cards[i].addActionListener(e -> {
        int cardID = Integer.parseInt(e.getActionCommand());
        onCardClick(cards[cardID]);
      });

      boardPanel.add(cards[i]);
    }
  }

  private void onCardClick(Card clickedCard) {
    // Block any click input from user when we are waiting for mismatched pair
    // to flip
    if(mismatchCardsWaiting.get() == true) {
      return;
    }

    // We should only flip the card when it's in NOT_FLIPPED state
    if(clickedCard.getState() == Card.State.NOT_FLIPPED) {
      System.out.println(clickedCard);
      clickedCard.flip();
      if(lastFlippedCard == null) {
        // This is the case where first card is clicked
        // i.e, there's no last flipped card  
        lastFlippedCard = clickedCard;
      } else {
        if(clickedCard.getName().equals(lastFlippedCard.getName())) {
          // If the pair matches, then decrement the pairsLeftCount
          // and update the score label
          pairsLeftCount--;
          if(pairsLeftCount > 0) {
            scoreLabel.setText(String.format("%d pairs left", pairsLeftCount));
          } else {
            onFinish();
          }

          lastFlippedCard = null;
        } else {
          // If the pair doesn't match, then flip both cards after 1.5 sec
          mismatchCardsWaiting.set(true);
          new Thread( () -> {
            try{
              Thread.sleep(1500);
              lastFlippedCard.flip();
              clickedCard.flip();
              lastFlippedCard = null;
              mismatchCardsWaiting.set(false);
            } catch (InterruptedException ex) {
            }
          }).start();
        }
      }
    }
  }

  private void resetGame() {
    mismatchCardsWaiting.set(false);
    for(int i = 0 ; i < totalCards ; i++) {
      if(cards[i].getState() == Card.State.FLIPPED) {
        cards[i].flip();
      }
    }

    lastFlippedCard = null;
    pairsLeftCount = totalCards / 2;

    ArrayList<Integer> cardPairIDs = new ArrayList<Integer>();
    for(int i = 0 ; i < totalCards ; i++) {
      cardPairIDs.add(i/2);
    }
    Collections.shuffle(cardPairIDs);
    for(int i = 0 ; i < totalCards ; i++) {
      cards[i].setName(Integer.toString(cardPairIDs.get(i)));
    }

    scoreLabel.setText(String.format("%d pairs left", pairsLeftCount));

    System.out.println("New game");
    for(int i = 0 ; i < rows ; i++) {
      for(int j = 0 ; j < columns ; j++) {
        System.out.printf("%d ", cardPairIDs.get(i*columns+j));
      }
      System.out.println();
    }
  }

  private void onFinish() {
    scoreLabel.setText(String.format("You win"));
  }
}
