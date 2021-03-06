import javax.swing.*;
import java.awt.Dimension;

public class Card extends JButton {
  private ImageIcon cardFace;
  private ImageIcon backFace;
  private int id;
  private String name;
  private State state;
  
  Card(int id, String name) {
    this.setPreferredSize(new Dimension(120, 120));

    this.id = id;
    this.name = name;
    this.setActionCommand(Integer.toString(id));
    this.state = State.NOT_FLIPPED;
  
    backFace = new ImageIcon("cardBack.png");
    this.setIcon(backFace);
  }

  public void setState(State state) {
    this.state = state;
  }

  public State getState() {
    return this.state;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ImageIcon getCardFace(){
	  return cardFace;
  }

  public void setCardFace(ImageIcon cardFace) {
	  this.cardFace = cardFace;
  }

  @Override
  public String toString() {
    if(state == State.NOT_FLIPPED) {
      return String.format("card id= %d, name= %s, state= NOT_FLIPPED", id, name);
    } else {
      return String.format("card id= %d, name= %s, state= FLIPPED", id, name);
    }
  }

  public void flip() {
    if(this.state == State.NOT_FLIPPED) {
      this.setIcon(cardFace);
      this.state = State.FLIPPED;
    } else {
      this.setIcon(backFace);
      this.state = State.NOT_FLIPPED;
    }
  }

  public enum State {
    NOT_FLIPPED(0), FLIPPED(1);

    State(int state) {
      this.state = state;
    }

    public String toString() {
      if(state == 0) {
        return "NOT_FLIPPED";
      } else {
        return "FLIPPED";
      }
    }

    int state;
  };
}
