package comp1110.ass2.gui;

import comp1110.ass2.*;
import gittest.C;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.UnaryOperator;


public class Game extends Application {

    final Group root = new Group();
    Stage rootStage;
    static final int WINDOW_WIDTH = 1200;
    static final int WINDOW_HEIGHT = 700;
    Scene mainScene = initMainMenuScene();
    Scene creditScene = initCreditScene();
    Scene newGameScene = initNewGameScene();
    Scene gamePlayScene;
    Scene loadGameScene = initLoadGameScene();
    Scene settingsScene = initSettingsScene();
    boolean isSelected = false;

    static Image image1 = new Image("file:assets/Cyan_Carpet.png");
    static Image image2 = new Image("file:assets/Yellow_Carpet.png");
    static Image image3 = new Image("file:assets/Red_Carpet.png");
    static Image image4 = new Image("file:assets/Purple_Carpet.png");

    /**
     *
     * @param PrimaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @author Jinqiao Jiang
     */
    @Override
    public void start(Stage PrimaryStage) throws Exception {
        // FIXME Task 7 and 15
        rootStage = PrimaryStage;
        PrimaryStage.setScene(mainScene);
        PrimaryStage.setTitle("Marrakech Game");
//        PrimaryStage.setResizable(false);   //no resize
        PrimaryStage.setWidth(WINDOW_WIDTH);
        PrimaryStage.setHeight(WINDOW_HEIGHT);
        PrimaryStage.show();
    }

    /**
     * this method will create the main menu Scene
     * @return the main menu Scene
     * @author Jinqiao Jiang
     */
    Scene initMainMenuScene() {
        BorderPane container = new ContainerLayout();
        HBox titleLayout = new TitleLayout("MARRAKECH");
        DescriptionLayout descriptionLayout = new DescriptionLayout("Welcome to Marrakech game");
        DescriptionLayout newGameBtnDescription = new DescriptionLayout("New game");
        DescriptionLayout loadBtnDescription = new DescriptionLayout("Load game from directory");
        DescriptionLayout settingsBtnDescription = new DescriptionLayout("Game settings");
        DescriptionLayout creditBtnDescription = new DescriptionLayout("Credit information");
        DescriptionLayout quitBtnDescription = new DescriptionLayout("Quit game");
        Button newGameBtn = new Button("NEW GAME");
        Button loadBtn = new Button("LOAD GAME");
        Button settingsBtn = new Button("SETTINGS");
        Button creditBtn = new Button("CREDIT");
        Button quitBtn = new Button("QUIT");

        newGameBtn.setOnMouseEntered(event -> container.setBottom(newGameBtnDescription));
        newGameBtn.setOnAction(event -> {
            rootStage.setScene(newGameScene);
            rootStage.setFullScreen(isSelected);});
        newGameBtn.setOnMouseExited(event -> container.setBottom(descriptionLayout));

        loadBtn.setOnMouseEntered(event -> container.setBottom(loadBtnDescription));
        loadBtn.setOnAction(event -> {
            rootStage.setScene(loadGameScene);
            rootStage.setFullScreen(isSelected);
        });
        loadBtn.setOnMouseExited(event -> container.setBottom(descriptionLayout));

        settingsBtn.setOnMouseEntered(event -> container.setBottom(settingsBtnDescription));
        settingsBtn.setOnAction(event -> {
            rootStage.setScene(settingsScene);
            rootStage.setFullScreen(isSelected);
        });
        settingsBtn.setOnMouseExited(event -> container.setBottom(descriptionLayout));

        creditBtn.setOnMouseEntered(event -> container.setBottom(creditBtnDescription));
        creditBtn.setOnAction(event -> {
            rootStage.setScene(creditScene);
            rootStage.setFullScreen(isSelected);
        });
        creditBtn.setOnMouseExited(event -> container.setBottom(descriptionLayout));

        quitBtn.setOnMouseEntered(event -> container.setBottom(quitBtnDescription));
        quitBtn.setOnAction(event -> rootStage.close());
        quitBtn.setOnMouseExited(event -> container.setBottom(descriptionLayout));

        VBox mainMenuBtnLayout = new MainMenuBtnLayout(newGameBtn, loadBtn, settingsBtn, creditBtn, quitBtn);

        container.setTop(titleLayout);
        container.setCenter(mainMenuBtnLayout);
        container.setBottom(descriptionLayout);
        return new Scene(container);
    }
    /**
     * this method will create the credit Scene
     * @return the credit Scene
     * @author Jinqiao Jiang
     */
    Scene initCreditScene() {
        BorderPane container = new ContainerLayout();
        HBox titleLayout = new TitleLayout("Credit");

        VBox content = new VBox();
        content.setAlignment(Pos.CENTER);
        Text text = new Text("This game is developed by mon10b5 group: Jinqiao Jiang, Aaron Nathan Vas, and Jessica Lai");
        Button backBtn = new Button("Back");
        backBtn.setOnAction(event -> {
            rootStage.setScene(mainScene);
            rootStage.setFullScreen(isSelected);
        });
        content.getChildren().addAll(text, backBtn);
        content.setSpacing(20);
        container.getChildren().clear();
        container.setTop(titleLayout);
        container.setCenter(content);
        container.setBottom(new DescriptionLayout("Credit information"));
        return new Scene(container);
    }
    /**
     * this method will create the new game Scene
     * @return the new game Scene
     * @author Jinqiao Jiang
     */
    Scene initNewGameScene() {
        //container
        BorderPane container = new ContainerLayout();

        //title
        HBox titleLayout = new TitleLayout("New Game");
        //center
        VBox center = new VBox();
        center.setStyle("-fx-background-color:" + MyColor.YELLOW_300 + ";-fx-background-radius: 10px;");
        BorderPane.setMargin(center, new Insets(100,200,100,200));
        center.setAlignment(Pos.CENTER);
        GridPane gridPane = new GridPane();
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);
        Label label1 = new Label("# of human players: ");
        TextField textField1 = new TextField();
        textField1.setText("4");
        //restrict input format
        textField1.setTextFormatter(new TextFormatter<String>(new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change change) {
                if (change.getText().matches("^[1-4]*$")){//restriction, using regular expression
                    return  change;
                }
                return null;
            }
        }));



        Label label2 = new Label("# of computer players:");
        TextField textField2 = new TextField();
        textField2.setText("0");
        textField2.setDisable(true);
        //use listener to change text in textField2 automatically
        textField1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.length() > 1) {
                    textField1.setText(oldValue);
                    textField2.setText(String.valueOf(4 - Integer.parseInt(oldValue)));
                } else {
                    textField2.setText(String.valueOf(4 - Integer.parseInt(newValue)));
                }
            }
        });

        HBox TwoButtons = new HBox();
        Label label3 = new Label("enable advanced computer AI");
        CheckBox checkBox = new CheckBox();

        gridPane.add(label1, 0 ,0);
        gridPane.add(textField1, 1, 0);
        gridPane.add(label2, 0, 1);
        gridPane.add(textField2, 1, 1);
        gridPane.add(label3, 0, 2);
        gridPane.add(checkBox, 1, 2);


        VBox bottomLayout = new VBox();

        TwoButtons.setAlignment(Pos.CENTER);

        Button backBtn = new Button("Back");
        backBtn.setOnAction(event -> {
            rootStage.setScene(mainScene);
            rootStage.setFullScreen(isSelected);
        });
        Button startBtn = new Button("Start");
        startBtn.setOnAction(event -> {
            String players = textField1.getText();
            String computers = textField2.getText();
            boolean isAdvancedAIChecked = checkBox.isSelected();
            String gameString = initGamePlayString();
            gamePlayScene = initGamePlayScene(gameString, 4, Integer.parseInt(computers), isAdvancedAIChecked);
            rootStage.setScene(gamePlayScene);
            rootStage.setFullScreen(isSelected);
        });

        TwoButtons.getChildren().addAll(backBtn, startBtn);
        HBox.setMargin(backBtn, new Insets(0,200,0,0));
        center.getChildren().addAll(gridPane, TwoButtons);

        //bottom
        DescriptionLayout descriptionLayout = new DescriptionLayout("New game settings");
        bottomLayout.getChildren().add(descriptionLayout);

        container.setTop(titleLayout);
        container.setCenter(center);
        container.setBottom(bottomLayout);
        return new Scene(container);
    }
    /**
     * this method will create the load game Scene
     * @return the load game Scene
     * @author Jinqiao Jiang
     */
    Scene initLoadGameScene() {
        BorderPane container = new ContainerLayout();
        HBox titleLayout = new TitleLayout("Load Game");

        Label boardLabel = new Label("Game State:");
        TextField boardTextField = new TextField();
        boardTextField.setPrefWidth(800);
        Button button = new Button("Load");
        Button backBtn = new Button("Back");
        backBtn.setOnAction(event -> {
            rootStage.setScene(mainScene);
            rootStage.setFullScreen(isSelected);
        });
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                gamePlayScene = initGamePlayScene(boardTextField.getText(), 4, 0, false);
                rootStage.setScene(gamePlayScene);
                rootStage.setFullScreen(isSelected);
            }
        });
        VBox center = new VBox();
        center.setStyle("-fx-background-color:" + MyColor.YELLOW_300 + ";-fx-background-radius: 10px;");
        BorderPane.setMargin(center, new Insets(100,50,100,50));
        center.setAlignment(Pos.CENTER);
        center.setSpacing(20);

        HBox hb = new HBox();
        hb.getChildren().addAll(boardLabel, boardTextField, button);
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);

        center.getChildren().addAll(hb, backBtn);

        container.setTop(titleLayout);
        container.setCenter(center);
        container.setBottom(new DescriptionLayout("input a game string in the text field to load the game"));
        return new Scene(container);
    }
    /**
     * this method will create the settings Scene
     * @return the settings Scene
     * @author Jinqiao Jiang
     */
    Scene initSettingsScene() {
        BorderPane container = new ContainerLayout();
        HBox titleLayout = new TitleLayout("Settings");

        Label Label = new Label("FullScreen");
        CheckBox checkBox = new CheckBox();
        checkBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (checkBox.isSelected()) {
                    isSelected = true;
                    rootStage.setFullScreen(true);
                } else {
                    isSelected = false;
                    rootStage.setFullScreen(false);
                }
            }
        });
        Button backBtn = new Button("Back");
        backBtn.setOnAction(event -> {
            rootStage.setScene(mainScene);
            rootStage.setFullScreen(isSelected);
        });

        VBox center = new VBox();
        center.setStyle("-fx-background-color:" + MyColor.YELLOW_300 + ";-fx-background-radius: 10px;");
        BorderPane.setMargin(center, new Insets(100,200,100,200));
        center.setAlignment(Pos.CENTER);
        center.setSpacing(20);

        HBox hb = new HBox();
        hb.getChildren().addAll(Label, checkBox);
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);

        center.getChildren().addAll(hb, backBtn);

        container.setTop(titleLayout);
        container.setCenter(center);
        container.setBottom(new DescriptionLayout("change game settings"));
        return new Scene(container);
    }
    /**
     * this method will create the game play Scene
     * @return the game play Scene
     * @author Jinqiao Jiang
     */
    Scene initGamePlayScene(String gameString, int numberOfPlayers, int computers, boolean isAdvancedAIChecked) {
        BorderPane container = new ContainerLayout();

        HBox titleLayout = new TitleLayout("MARRAKECH");
        DescriptionLayout descriptionLayout = new DescriptionLayout("Game playing, having fun! (Hint: You can click a tile to quickly input rotation degree & rug position)");
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        //left layout, display players information
        VBox left = new VBox();
        left.setPrefWidth(300);
        left.setStyle("-fx-background-color:" + MyColor.YELLOW_300 + ";-fx-background-radius: 10px;");
        left.setSpacing(20);
        //center layout, display board
        GridPane center = new GridPane();
        center.setPrefWidth(600);
        center.setStyle("-fx-background-color:" + MyColor.YELLOW_300 + ";-fx-background-radius: 10px;");
        center.setAlignment(Pos.CENTER);
        //right layout, display game processing information
        VBox right = new VBox();
        right.setStyle("-fx-background-color:" + MyColor.YELLOW_300 + ";-fx-background-radius: 10px;");
        right.setAlignment(Pos.CENTER);
        right.setPrefWidth(300);
        right.setSpacing(20);


        //game information
        String[] currentGameString = {gameString};
        Player[][] players = {Player.constructPlayerList(gameString, computers)};
//        assign computer boolean
        switch (computers) {
            case(3): players[0][1].setComputer(true);
            case(2): players[0][2].setComputer(true);
            case(1): players[0][3].setComputer(true);
        }
        Assam[] assam = {new Assam(gameString)};
        Tile[][][] boardTiles = {Tile.constructBoard(gameString)};


        //specify current die number
        int[] dieNumber = {0};
        //specify current player in the turn
        int[] currentPlayerIndex = {0};
        //player carpet image
        ImageView icon = new ImageView(image1);
        icon.setFitWidth(50);
        icon.setFitHeight(50);
        HBox current = new HBox();
        current.getChildren().addAll(new Label("Current: "), icon);
        current.setAlignment(Pos.CENTER);
        right.getChildren().add(current);

        //input text field
        Label inputLabel = new Label("Rotation:");
        TextField input = new TextField();
        input.setPrefWidth(50);
        right.getChildren().addAll(inputLabel, input);

        displayGame(players, assam, boardTiles, left, center, input, false, true);

        Button rotateAssamBtn = new ActionButton("Rotate");
        Button rollDieBtn = new ActionButton("Roll die");
        Button moveBtn = new ActionButton("Move");
        Button makePaymentBtn = new ActionButton("Make Payment");
        Button placeRugBtn = new ActionButton("Place rug");
        Button nextTurnBtn = new ActionButton("Next Turn");
        //actions
        rotateAssamBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //create current player instance
                Player currentPlayerInTurn = players[0][currentPlayerIndex[0]];
                //find the next in the game player
                while (!currentPlayerInTurn.isInTheGame()) {
                    currentPlayerIndex[0] = (currentPlayerIndex[0] + 1) % numberOfPlayers;
                }
                currentPlayerInTurn = players[0][currentPlayerIndex[0]];
                //check whether game is over
//                if (Marrakech.isGameOver(currentGameString[0])) {
//                    char winner = Marrakech.getWinner(currentGameString[0]);
//                    rootStage.setScene(winnerScene(winner, players[0]));
//                }
                //check input
                if (input.getText().equals("")) {
                    input.setText("0");
                }
                //select assam rotation
                if (currentPlayerInTurn.isComputer()) {
                    if (isAdvancedAIChecked) {
                        String[] allRotations = {"0", "90", "-90"};
                        Random random = new Random();
                        input.setText(allRotations[random.nextInt(3)]);
                    } else {
                        input.setText("90");
                    }

                }
                //write game string and display
                String newAssamString = Marrakech.rotateAssam(assam[0].toString(), Integer.parseInt(input.getText()));
                String newGameString = Player.getPlayerString(currentGameString[0]) + newAssamString + Board.getBoardString(currentGameString[0]);
                currentGameString[0] = newGameString;
                players[0] = Player.constructPlayerList(newGameString, computers);
                assam[0] = new Assam(newGameString);
                boardTiles[0] = Tile.constructBoard(newGameString);
                displayGame(players, assam, boardTiles, left, center, input, false, false);
                inputLabel.setText("");
                input.setDisable(true);
                input.setText("");
                if (!currentPlayerInTurn.isComputer()) {
                    right.getChildren().remove(rotateAssamBtn);
                    right.getChildren().add(rollDieBtn);
                }
            }
        });
        rollDieBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //roll die action
                //create current player instance
                Player currentPlayerInTurn = players[0][currentPlayerIndex[0]];
                dieNumber[0] = Marrakech.rollDie();
                inputLabel.setText("Number of die:");
                input.setText(String.valueOf(dieNumber[0]));
                if (!currentPlayerInTurn.isComputer()) {
                    right.getChildren().remove(rollDieBtn);
                    right.getChildren().add(moveBtn);
                }
            }
        });
        moveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //create current player instance
                Player currentPlayerInTurn = players[0][currentPlayerIndex[0]];
                //move action
                //write new assam string
                String newAssamString = Marrakech.moveAssam(assam[0].toString(), Integer.parseInt(input.getText()));
                //write game string and display
                String newGameString = Player.getPlayerString(currentGameString[0]) + newAssamString + Board.getBoardString(currentGameString[0]);
                currentGameString[0] = newGameString;
                players[0] = Player.constructPlayerList(newGameString, computers);
                assam[0] = new Assam(newGameString);
                boardTiles[0] = Tile.constructBoard(newGameString);
                displayGame(players, assam, boardTiles, left, center, input, true, false);

                //get the tile that assam is standing
                int assamX = assam[0].getPosition().getX();
                int assamY = assam[0].getPosition().getY();
                Tile assamStandingTile = boardTiles[0][assamX][assamY];
                inputLabel.setText("");
                right.getChildren().remove(moveBtn);
                //if assam stand on a carpet and is not the player in turn
                if (assamStandingTile.isOccupiedByCarpet && assamStandingTile.getOwnerId() != currentPlayerInTurn.getId()) {
                    inputLabel.setText("Payment Amount:");
                    input.setText("");
                    if (!currentPlayerInTurn.isComputer()) {
                        right.getChildren().add(makePaymentBtn);
                    }
                } else {
                    input.setDisable(false);
                    String currentPlayerId = String.valueOf(currentPlayerInTurn.getId());
                    String carpetId = String.valueOf(currentPlayerInTurn.getRugs() + 10);
                    inputLabel.setText("rug ID:");
                    input.setText(currentPlayerId + carpetId);
                    if (!currentPlayerInTurn.isComputer()) {
                        right.getChildren().add(placeRugBtn);
                    }
                }
            }
        });
        makePaymentBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //make payment action
                //create current player instance
                Player currentPlayerInTurn = players[0][currentPlayerIndex[0]];

                int payment = Marrakech.getPaymentAmount(currentGameString[0]);

                //get the tile that assam is standing
                int assamX = assam[0].getPosition().getX();
                int assamY = assam[0].getPosition().getY();
                Tile assamStandingTile = boardTiles[0][assamX][assamY];

                //check if payment is larger than the current player money
                if (payment > currentPlayerInTurn.getMoney()) {
                    Player.getPlayerInList(players[0], assamStandingTile.getOwnerId()).addMoney(currentPlayerInTurn.getMoney());
                    currentPlayerInTurn.setMoney(0);
                    currentPlayerInTurn.setInTheGame(false);
                } else {
                    currentPlayerInTurn.minusMoney(payment);
                    Player.getPlayerInList(players[0], assamStandingTile.getOwnerId()).addMoney(payment);
                }
                StringBuilder newPlayerString = new StringBuilder();
                for (Player player : players[0]) {
                    newPlayerString.append(player.toString());
                }
                //write game string and display
                String newGameString = newPlayerString + Assam.getAssamString(currentGameString[0]) + Board.getBoardString(currentGameString[0]);
                currentGameString[0] = newGameString;
                players[0] = Player.constructPlayerList(newGameString, computers);
                assam[0] = new Assam(newGameString);
                boardTiles[0] = Tile.constructBoard(newGameString);
                //display game in left and center layout
                displayGame(players, assam, boardTiles, left, center, input, true, false);
                //make input text field available for input
                input.setDisable(false);
                String currentPlayerId = String.valueOf(currentPlayerInTurn.getId());
                String carpetId = String.valueOf(currentPlayerInTurn.getRugs() + 10);
                inputLabel.setText("rug ID:");
                input.setText(currentPlayerId + carpetId);
                if (!currentPlayerInTurn.isComputer()) {
                    right.getChildren().remove(makePaymentBtn);
                    right.getChildren().add(placeRugBtn);
                }
            }
        });
        placeRugBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //place rug action
                //create current player instance
                Player currentPlayerInTurn = players[0][currentPlayerIndex[0]];
                if (currentPlayerInTurn.isComputer()) {
                    char id = currentPlayerInTurn.getId();
                    int carpetId = currentPlayerInTurn.getRugs() + 10;
                    String first = String.valueOf(id) + carpetId;
                    int assamX = assam[0].getPosition().getX();
                    int assamY = assam[0].getPosition().getY();
                    //find all possible placement
                    ArrayList<String> allPossibleCarpets = new ArrayList<>();
                    String[] allPotentialCarpets = new String[12];
                    allPotentialCarpets[0] = first + new Coordinates(assamX - 1, assamY) + new Coordinates(assamX - 1, assamY + 1);
                    allPotentialCarpets[1] = first + new Coordinates(assamX - 1, assamY) + new Coordinates(assamX - 2, assamY);
                    allPotentialCarpets[2] = first + new Coordinates(assamX - 1, assamY) + new Coordinates(assamX - 1, assamY - 1);
                    allPotentialCarpets[3] = first + new Coordinates(assamX, assamY + 1) + new Coordinates(assamX, assamY + 2);
                    allPotentialCarpets[4] = first + new Coordinates(assamX, assamY + 1) + new Coordinates(assamX - 1, assamY + 1);
                    allPotentialCarpets[5] = first + new Coordinates(assamX, assamY + 1) + new Coordinates(assamX + 1, assamY + 1);
                    allPotentialCarpets[6] = first + new Coordinates(assamX + 1, assamY) + new Coordinates(assamX + 1, assamY + 1);
                    allPotentialCarpets[7] = first + new Coordinates(assamX + 1, assamY) + new Coordinates(assamX + 1, assamY - 1);
                    allPotentialCarpets[8] = first + new Coordinates(assamX + 1, assamY) + new Coordinates(assamX + 2, assamY);
                    allPotentialCarpets[9] = first + new Coordinates(assamX, assamY - 1) + new Coordinates(assamX, assamY - 2);
                    allPotentialCarpets[10] = first + new Coordinates(assamX, assamY - 1) + new Coordinates(assamX - 1, assamY - 1);
                    allPotentialCarpets[11] = first + new Coordinates(assamX, assamY - 1) + new Coordinates(assamX + 1, assamY - 1);
                    for (String carpetString : allPotentialCarpets) {
                        //check valid for rug string and placement
                        if (Marrakech.isRugValid(currentGameString[0], carpetString) && Marrakech.isPlacementValid(currentGameString[0], carpetString)) {
                            allPossibleCarpets.add(carpetString);
                        }
                    }
                    if (isAdvancedAIChecked) {
                        //get a random carpet string
                        Random random = new Random();
                        int index = random.nextInt(allPossibleCarpets.size());
                        String carpetString = allPossibleCarpets.get(index);
                        input.setText(carpetString);
                    } else {
                        input.setText(allPossibleCarpets.get(0));
                    }

                }
                //if placement is valid
                if (Marrakech.isRugValid(currentGameString[0], input.getText())) {
                    if (Marrakech.isPlacementValid(currentGameString[0], input.getText())) {
                        //write game string and display
                        String newGameString = Marrakech.makePlacement(currentGameString[0], input.getText());
                        currentGameString[0] = newGameString;
                        players[0] = Player.constructPlayerList(newGameString, computers);
                        assam[0] = new Assam(newGameString);
                        boardTiles[0] = Tile.constructBoard(newGameString);
                        //display game in left and center layout
                        displayGame(players, assam, boardTiles, left, center, input, false, true);
                        inputLabel.setText("");
                        input.setText("");
                        if (!currentPlayerInTurn.isComputer()) {
                            right.getChildren().remove(placeRugBtn);
                            right.getChildren().add(nextTurnBtn);
                        }
                    }
                }

            }
        });
        nextTurnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //next turn action
                //check whether game is over
                if (Marrakech.isGameOver(currentGameString[0])) {
                    char winner = Marrakech.getWinner(currentGameString[0]);
                    rootStage.setScene(winnerScene(winner, players[0]));
                    return;
                }
                //create current player instance
                Player currentPlayerInTurn = players[0][currentPlayerIndex[0]];
                currentPlayerIndex[0] = (currentPlayerIndex[0] + 1) % numberOfPlayers;
                //create next player instance
                Player nextPlayer = players[0][currentPlayerIndex[0]];
                int loopTimes = 0;
                //while next player is not in the game
                while (!nextPlayer.isInTheGame()) {
                    if (loopTimes > 5) {
                        //all players are not in the game
                        char winner = Marrakech.getWinner(currentGameString[0]);
                        rootStage.setScene(winnerScene(winner, players[0]));
                        return;
                    }
                    //find next of next player
                    currentPlayerIndex[0] = (currentPlayerIndex[0] + 1) % numberOfPlayers;
                    nextPlayer = players[0][currentPlayerIndex[0]];
                    loopTimes++;
                }
                //update current player information
                switch (nextPlayer.getId()) {
                    case ('c') -> icon.setImage(image1);
                    case ('y') -> icon.setImage(image2);
                    case ('r') -> icon.setImage(image3);
                    case ('p') -> icon.setImage(image4);
                }
                //next player is in the game
                if (nextPlayer.isComputer()) {
                    //if next player is a computer, then automatically fire those buttons
                    rotateAssamBtn.fire();
                    rollDieBtn.fire();
                    moveBtn.fire();
                    //determine next button to fire is make payment or place a rug
                    //get the tile that assam is standing
                    int assamX = assam[0].getPosition().getX();
                    int assamY = assam[0].getPosition().getY();
                    Tile assamStandingTile = boardTiles[0][assamX][assamY];
                    //if assam stand on a carpet and is not the player in turn
                    if (assamStandingTile.isOccupiedByCarpet && assamStandingTile.getOwnerId() != currentPlayerInTurn.getId()) {
                        makePaymentBtn.fire();
                        placeRugBtn.fire();
                    } else {
                        placeRugBtn.fire();
                    }
                } else {
                    inputLabel.setText("Rotation: ");
                    right.getChildren().remove(nextTurnBtn);
                    right.getChildren().add(rotateAssamBtn);
                }
            }
        });

        if (Marrakech.isGameOver(currentGameString[0])) {
            input.setVisible(false);
            inputLabel.setText("");
            right.getChildren().add(nextTurnBtn);
        } else {
            right.getChildren().add(rotateAssamBtn);
        }


        hbox.getChildren().addAll(left, center, right);
        HBox.setMargin(left, new Insets(20,20,20,20));
        HBox.setMargin(right, new Insets(20,20,20,20));
        HBox.setMargin(center, new Insets(20,0,20,0));

        container.setTop(titleLayout);
        container.setCenter(hbox);
        container.setBottom(descriptionLayout);

        Scene scene = new Scene(container);
        //esc menu
        KeyCombination kc = new KeyCodeCombination(KeyCode.ESCAPE);
        scene.getAccelerators().put(kc, new Runnable() {
            @Override
            public void run() {
                System.out.println("run method");
                //call method in click event
                Stage stage = new Stage();
                //set modality
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(rootStage);

                VBox vb = new VBox();
                vb.setStyle("-fx-font-size: 18px;");
                vb.setSpacing(80);
                vb.setAlignment(Pos.CENTER);
                Text text = new Text("Are you sure to quit the game?");
                Button yes = new Button("Yes");
                yes.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        rootStage.close();
                    }
                });
                yes.setPrefWidth(80);
                Button no = new Button("No");
                no.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stage.close();
                    }
                });
                no.setPrefWidth(80);
                HBox hb = new HBox();
                hb.setAlignment(Pos.CENTER);
                hb.getChildren().addAll(no, yes);
                HBox.setMargin(no, new Insets(0,50,0,0));
                vb.getChildren().addAll(text, hb);
                Scene scene = new Scene(vb);
                stage.setScene(scene);
                stage.setTitle("WARNING!");
                stage.setHeight(300);
                stage.setWidth(300);
                stage.setX(800);
                stage.setY(300);
                stage.show();
                System.out.println(Thread.currentThread().getName());
            }
        });
        return scene;
    }
    /**
     * this method will create the winner Scene
     * @return the winner Scene
     * @author Jinqiao Jiang
     */
    Scene winnerScene(char id, Player[] players) {
        BorderPane container = new ContainerLayout();
        DescriptionLayout descriptionLayout = new DescriptionLayout("Winner Scene");
        HBox titleLayout1 = new TitleLayout("Congratulations!");
        HBox titleLayout2 = new TitleLayout("Ohh no!");
        Text winnerText = new Text("You are victorious, player " + id);
        Text failText = new Text("Computer won");
        if (Player.getPlayerInList(players, id).isComputer()) {
            container.setTop(titleLayout2);
            container.setCenter(failText);
        } else {
            container.setTop(titleLayout1);
            container.setCenter(winnerText);
        }
        container.setBottom(descriptionLayout);
        return new Scene(container);
    }

    /**
     * This method will display all the game information on different Javafx Layouts,
     * particularly used in game play scene
     * @param players players of current game
     * @param assam assam of current game
     * @param boardTiles board of current game
     * @param left left layout of game play scene
     * @param center center layout of game play scene
     * @param input input text field in the right layout of game play scene
     * @param isPlacementPhase a boolean determining whether this method is called in the placement phase
     * @param isRotationPhase a boolean determining whether this method is called in the rotation phase
     * @author Jinqiao Jiang
     */
    private void displayGame(Player[][] players, Assam[] assam, Tile[][][] boardTiles, VBox left, GridPane center, TextField input, boolean isPlacementPhase, boolean isRotationPhase) {
        //display game in left and center layout
        //display player information
        left.getChildren().clear();
        for (Player player : players[0]) {
            Viewer.displayPlayerInformation(left, player);
        }
        //display board
        Viewer.displayBoard(boardTiles[0], assam[0], center);
        if (isRotationPhase) {
            //get assam three neighbor node
            ArrayList<Node> assamNeighbors = new ArrayList<>();
            Node assamLeftNode;
            Node assamRightNode;
            Node assamTopNode;
            Node assamBottomNode;
            for (Node node: center.getChildren()) {
                if (GridPane.getRowIndex(node) == assam[0].getPosition().getX() && GridPane.getColumnIndex(node) == assam[0].getPosition().getY() - 1) {
                    assamLeftNode = node;
                    assamNeighbors.add(node);
                    node.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            input.setText("-90");
                        }
                    });
                }
                if (GridPane.getRowIndex(node) == assam[0].getPosition().getX() && GridPane.getColumnIndex(node) == assam[0].getPosition().getY() + 1) {
                    assamRightNode = node;
                    assamNeighbors.add(node);
                    node.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            input.setText("90");
                        }
                    });
                }
                if (GridPane.getRowIndex(node) == assam[0].getPosition().getX() - 1 && GridPane.getColumnIndex(node) == assam[0].getPosition().getY()) {
                    assamTopNode = node;
                    assamNeighbors.add(node);
                    node.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            input.setText("0");
                        }
                    });
                }
                if (GridPane.getRowIndex(node) == assam[0].getPosition().getX() + 1 && GridPane.getColumnIndex(node) == assam[0].getPosition().getY()) {
                    assamBottomNode = node;
                    assamNeighbors.add(node);
                    node.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            input.setText("180");
                        }
                    });
                }
            }
        }
        if (isPlacementPhase) {
            for (Node node: center.getChildren()) {
                node.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String newText = input.getText() + GridPane.getRowIndex(node) + GridPane.getColumnIndex(node);
                        input.setText(newText);
                    }
                });
            }
        }

    }

    /**
     * This method inits the gameString with empty board and 4 players
     * @return a gameString
     * @author Jinqiao Jiang
     */
    String initGamePlayString() {
        //init gameString
        StringBuilder gameString = new StringBuilder();
        //init playerStringList consist of 4 initial playerString
        String[] playerStringList = Player.initPlayerStringList();
        //append playerString to gameString according to input number of players
        for (int i = 0; i < 4; i++) {
            gameString.append(playerStringList[i]);
        }
        //init assamString
        String assamString = Assam.initAssamString();
        //append assamString
        gameString.append(assamString);
        //init boardString
        String boardString = Board.initBoardString();
        //append boardString
        gameString.append(boardString);
        return gameString.toString();
    }

    /**
     * predefined layouts that styles are applied
     * @author Jinqiao Jiang
     */
    static class ContainerLayout extends BorderPane {
        ContainerLayout() {
            this.setStyle("-fx-background-color: " + MyColor.BLUE_300 +
                    "; -fx-font-size: 18px;");
        }
    }
    static class TitleLayout extends HBox {
        TitleLayout(String title) {
            this.setPrefHeight(100);
            this.setAlignment(Pos.CENTER);
            this.setStyle("-fx-background-color: " + MyColor.BLUE_400);
            HBox.setMargin(this, new Insets(20, 20, 20, 20));
            Text Title = new Text(title);
            Title.setStyle("-fx-font-size: 28px;");
            this.getChildren().add(Title);
        }
    }
    static class MainMenuBtnLayout extends VBox {
        MainMenuBtnLayout(Button... buttons) {
            this.setAlignment(Pos.CENTER);
            this.setSpacing(20);
            this.getChildren().addAll(buttons);
        }
    }
    static class DescriptionLayout extends HBox {
        DescriptionLayout(String description) {
            this.setPrefHeight(30);
            this.setAlignment(Pos.CENTER_LEFT);
            this.setStyle("-fx-background-color: " + MyColor.BLUE_400);
            Text text = new Text(description);
            text.setStyle("-fx-font-size: 22px;");
            this.getChildren().add(text);
        }
    }
    static class ActionButton extends Button {
        ActionButton(String text) {
            super(text);
            this.setStyle("-fx-background-radius: 5px;");
            this.setPrefWidth(220);
            this.setPrefHeight(60);
        }
    }
}
