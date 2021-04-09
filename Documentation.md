# MatchingGame.java
This is where the starting point of the application resides

**Methods:***
| Type | Name | Info |
| ---- | ---- | ---- |
| public static void | main(String args[]) | Starts a frame with GamePanel |

------------------

# GamePanel.java
Game panel for the matching game. It contains the cards, stopwatch, reset button, and score label

**Data fields:**
| Type | Name | Info |
| ---- | ---- | ---- |
| AtomicBoolean | mismatchCardsWaiting | This variable is used for blocking user card clicks when mismatched pair is clicked |
| boolean | gameStarted | Indicates whether if game has started |
| Card[] | cards | All the cards on the board |
| Card | lastFlippedCard | Last flipped card |
| int | pairsLeftCount | How many pairs are left |
| JButton | resetButton | New game button. Resets the game |
| JLabel | scoreLabel | Label for how many pairs left |
| JPanel | boardPanel | Board panel for the cards |
| JPanel | sidePanel | Side panel for new game button, stopwatch, and score label |
| ImageIcon[] | faces | Face images for the cards |
| StopwatchPanel | stopwatchPanel | Stopwatch for the game |

**Methods:**
| Type | Name | Info |
| ---- | ---- | ---- |
| constructor  | GamePanel() | Instantiates neccessary components, loads card faces, adds components into the panel, and resets the game |
| private void | addCards() | Instantiates cards[] and adds them into the boardPanel. Adds action listener to call onCardClick for every card |
| private void | onCardClick() | Main game logic. Flips the given clicked card. Checks if there's match with the last clicked card |
| private void | resetGame() | Resets the game. Shuffles the cards |
| private void | onFinish() | Displays "you win" message. Stops the stopwatch |

------------------

# Card.java
Class for the cards. It extends JButton and shows the front and back face images.

**Data fields:**
| Type | Name | Info |
| ---- | ---- | ---- |
| ImageIcon | cardFace | Image of the front face |
| ImageIcon | backFace | Image of the back face |
| int | id | Id for this card |
| String | name | Name for this card |
| State | state | State for this card |

**Methods:**
| Type | Name | Info |
| ---- | ---- | ---- |
| constructor | Card(int id, String name) | Sets the id and name of the card. Sets the state as NOT_FLIPPED. Also, it loads the back face image. |

| public String | toString() | toString method for the card|
| public void | flip() | Flips the card by alternating front and back face images |

------------------

# StopwatchPanel.java
Stopwach panel for the game.

**Data fields:**
| Type | Name | Info |
| ---- | ---- | ---- |
| JLabel | timeLabel | Time label |
| int | elapsedTime | Elapsed time since the start of the stopwatch |
| Timer | timer | Timer for the stopwatch

**Methods:**
| Type | Name | Info |
| ---- | ---- | ---- |
| constructor | StopwatchPanel() | Instantiates neccessary components |
| private void | updateTimeLabel() | Updates the timeLabel according to how much time has elapsed |
| public void | start() | Starts the timer |
| public void | stop() | Stops the timer |
| public void | reset() | Resets the timer |
------------------