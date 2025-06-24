# Marrakech Board Game

## Project Overview

This is a Marrakech board game developed using Java and JavaFX. Marrakech is a 2-4 player strategy game where players compete in a crowded Marrakech souk by placing rugs to attract the attention of the market owner, Assam, and ultimately achieve the highest score.

## Game Features

- 🎮 **Complete Game Experience**: Implements all core rules of the Marrakech game
- 🖥️ **Modern Interface**: Intuitive graphical user interface built with JavaFX
- 🤖 **AI Opponents**: Support for playing against computer-controlled players
- 🎲 **Special Dice Mechanism**: Non-standard dice with higher probability for 2 and 3
- 💰 **Economic System**: Complete money and payment mechanism
- 🏆 **Scoring System**: Comprehensive scoring based on money and visible rug count

## Game Rules

### Basic Setup
- **Board**: 7x7 square board
- **Players**: 2-4 players
- **Initial Resources**: Each player starts with 30 dirhams and 15 rugs
- **Game Objective**: Achieve the highest score (dirhams + visible rug count)

### Game Flow
Each player's turn consists of three phases:

1. **Rotation Phase**: Optionally rotate Assam 90 degrees (left or right)
2. **Movement Phase**:
   - Roll the special die (1-4 faces, with 2 and 3 being more likely)
   - Move Assam in the current direction by the number of spaces shown
   - Pay fees if Assam lands on another player's rug
3. **Placement Phase**: Place a rug on the board

### Rug Placement Rules
- Rugs must be adjacent to Assam's current position
- Cannot completely cover other rugs
- Rug size is 2x1 (covers two adjacent squares)

### Payment Mechanism
When Assam lands on another player's rug, a payment is required. The payment equals the size of the connected region of rugs of the same color.

### Game End
The game ends when any player enters the rotation phase but has no rugs remaining.

## Installation and Running

### System Requirements
- Java 17 or higher
- JavaFX runtime environment

### Installation Steps

1. **Clone the project**
   ```bash
   git clone [project-url]
   cd comp1110-ass2-main
   ```

2. **Compile the project**
   ```bash
   javac -cp "lib/*" src/comp1110/ass2/*.java src/comp1110/ass2/gui/*.java
   ```

3. **Run the game**
   ```bash
   java -cp "lib/*:src" comp1110.ass2.gui.Game
   ```

### Running with JAR file
```bash
java -jar comp1110-ass2.jar
```

## Game Interface Guide

### Main Menu
- **New Game**: Start a new game
- **Load Game**: Load a saved game from file
- **Settings**: Adjust game parameters
- **About**: View developer information

### Game Interface
- **Left Panel**: Display current player information and action buttons
- **Center Area**: 7x7 game board
- **Right Panel**: Display all player status
- **Bottom Area**: Game control buttons

### Operation Instructions
- **Rotate Assam**: Click rotation buttons to change Assam's direction
- **Roll Die**: Click the die button to move
- **Place Rug**: Click adjacent positions on the board to place rugs
- **Skip Turn**: Skip current turn when no actions are possible

## Project Structure

```
comp1110-ass2-main/
├── src/comp1110/ass2/          # Core game logic
│   ├── Marrakech.java         # Main game class
│   ├── Board.java             # Board management
│   ├── Player.java            # Player class
│   ├── Assam.java             # Assam character
│   ├── Tile.java              # Board tiles
│   ├── Carpet.java            # Rug class
│   ├── Coordinates.java       # Coordinate class
│   └── gui/                   # Graphical interface
│       ├── Game.java          # Main game interface
│       └── Viewer.java        # State viewer
├── assets/                    # Game resources
│   ├── Rug images
│   ├── Assam images
│   └── Board backgrounds
├── tests/                     # Unit tests
├── myTests/                   # Custom tests
└── admin/                     # Project documentation
```

## Core Features

### Implemented Features
- ✅ Complete game rule implementation
- ✅ Graphical user interface
- ✅ Human vs AI gameplay support
- ✅ Game state save and load
- ✅ Complete test suite
- ✅ Special dice mechanism
- ✅ Payment and scoring system

### Technical Features
- **Object-Oriented Design**: Clear class structure and responsibility separation
- **Event-Driven Architecture**: Responsive user interface
- **State Management**: Complete game state tracking
- **Error Handling**: Robust error checking and recovery mechanisms

## Development Team

- **Jinqiao Jiang** (u7757596) - Core game logic and GUI development
- **Aaron Nathan Vas** (u7706815) - Game rule implementation and testing
- **Jessica Lai** (u7741198) - User interface and user experience design

## Testing

The project includes a complete test suite:

```bash
# Run all tests
java -cp "lib/*:src" org.junit.runner.JUnitCore comp1110.ass2.MarrakechTest

# Run custom tests
java -cp "lib/*:src" org.junit.runner.JUnitCore comp1110.ass2.myTests.*
```

## Contributing

1. Fork the project
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is an academic project and follows ANU's academic integrity policy.

## Acknowledgments

- Thanks to the ANU COMP1110 course team for providing the project framework
- Thanks to all test data providers
- Thanks to the JavaFX community for technical support


---

**Enjoy the exciting Marrakech gaming experience!** 🎮✨
