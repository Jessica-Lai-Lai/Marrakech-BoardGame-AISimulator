package comp1110.ass2.gui;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Arrays;
import java.util.Comparator;

import comp1110.ass2.Tile;
import comp1110.ass2.Player;
import comp1110.ass2.Assam;

public class Viewer extends Application {
    // FIXME Task 5: implement the simple state viewer
    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 700;
    
    public static final int TILE_SIZE = 70;

    public static final Insets GLOBAL_PADDING = new Insets(20);

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField boardTextField;

    /**
     * Draw a placement in the window, removing any previously drawn placements
     * @param state an array of two strings, representing the current game state
     * @author Jinqiao Jiang, Jessica Lai
     */
    void displayState(String state) {
        //read the game string
        // Store Tile object matrix
        Tile[][] boardTiles;
        // Store Players information
        Player[] currentPlayers;
        Assam assam;
        if(state.equals("")) {
            System.out.println("empty input");
            return;
        }else {
            currentPlayers = Player.constructPlayerList(state);
            assam = new Assam(state);
            boardTiles = Tile.constructBoard(state);
        }

        //sort currentPlayers by id
        Arrays.sort(currentPlayers, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o1.getId() - o2.getId();
            }
        });
        //clear previous scene
        root.getChildren().clear();

        //game title node
        Text gameTitle = new Text("Marrakech Viewer");
        gameTitle.setStyle("-fx-font-family: Arial; -fx-font-size: 28px;");
        gameTitle.setFill(Paint.valueOf("#ffffff"));

        //this layout contains the game title
        HBox hbox = new HBox();
        hbox.getChildren().add(gameTitle);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPrefHeight(100);
        //more colors ref:https://bootstrapdoc.com/docs/5.0/customize/color
        //using BootStrap5 colors
        //license: under the MIT license and is copyright 2020 Twitter
        hbox.setStyle("-fx-background-color: rgb(13, 110, 253)");

        //this layout contains the game board
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        displayBoard(boardTiles, assam, grid);

        //this layout contains player information
        VBox vb = new VBox();
        vb.setStyle("-fx-background-color: rgb(110, 168, 254)");
        vb.setPrefWidth(300);
        vb.setPadding(GLOBAL_PADDING);
        vb.setSpacing(20);
        for (Player player : currentPlayers) {
            displayPlayerInformation(vb, player);
        }

        //go back button
        Button btn = new Button("Go back");
        //this layout contains the go back button
        FlowPane flow = new FlowPane();
        flow.getChildren().add(btn);
        flow.setAlignment(Pos.CENTER);
        //click event, use lambda
        btn.setOnAction(actionEvent -> {
            root.getChildren().clear();
            makeControls();
            root.getChildren().add(controls);
        });

        //bor is the main layout of game viewer
        //top display title
        //center display game board
        //right display player information
        //bottom display a button to go back
        BorderPane bor = new BorderPane();
        bor.setPrefWidth(VIEWER_WIDTH);
        bor.setPrefHeight(VIEWER_HEIGHT);
        bor.setStyle("-fx-background-color: rgb(207, 226, 255);");
        bor.setPadding(GLOBAL_PADDING);
        BorderPane.setAlignment(grid, Pos.CENTER);
        bor.setCenter(grid); //game board
        bor.setTop(hbox); // game title
        bor.setRight(vb); // game players
        bor.setBottom(flow); // go back button

        root.getChildren().add(bor);
    }
    static Image tileImage = new Image("file:assets/Tiles.png");
    static ImageView assamImageView = new ImageView(new Image ("file:assets/Assam.png"));
    static Image image1 = new Image("file:assets/Cyan_Carpet.png");
    static Image image2 = new Image("file:assets/Yellow_Carpet.png");
    static Image image3 = new Image("file:assets/Red_Carpet.png");
    static Image image4 = new Image("file:assets/Purple_Carpet.png");

    /**
     * This method will display the game information of the board on a GridPane
     * @param boardTiles the board to be displayed
     * @param assam assam instance in the game
     * @param grid the GridPane to be displayed on
     * @author Jinqiao Jiang
     */
    public static void displayBoard(Tile[][] boardTiles, Assam assam, GridPane grid) {
        assamImageView.setFitWidth(TILE_SIZE);
        assamImageView.setFitHeight(TILE_SIZE);
        for (int i = 0; i < boardTiles.length; i++) {
            for (int j = 0; j < boardTiles[i].length; j++) {
                ImageView tileImageView = new ImageView(tileImage);
                tileImageView.setFitHeight(TILE_SIZE);
                tileImageView.setFitWidth(TILE_SIZE);
                if(boardTiles[i][j].isOccupiedByAssam) {
                    //set Assam facing
                    switch (assam.getDirection()) {
                        //north
                        case ('N') -> assamImageView.setRotate(90);
                        //east
                        case ('E') -> assamImageView.setRotate(0);
                        //south
                        case ('S') -> assamImageView.setRotate(-90);
                        //west
                        case ('W') -> assamImageView.setRotate(180);
                    }
                    Group AssamGroup = new Group();
                    if(boardTiles[i][j].isOccupiedByCarpet) {
                        //tile + carpet + Assam
                        ImageView carpetImageView = new ImageView(image1);
                        carpetImageView.setFitHeight(TILE_SIZE);
                        carpetImageView.setFitWidth(TILE_SIZE);
                        switch (boardTiles[i][j].getOwnerId()) {
                            case ('c') -> carpetImageView.setImage(image1);
                            case ('y') -> carpetImageView.setImage(image2);
                            case ('r') -> carpetImageView.setImage(image3);
                            case ('p') -> carpetImageView.setImage(image4);
                        }
                        AssamGroup.getChildren().addAll(tileImageView, carpetImageView, assamImageView);
                    } else {
                        //tile + Assam
                        AssamGroup.getChildren().addAll(tileImageView, assamImageView);
                    }
                    grid.add(AssamGroup, j, i);
                    continue;
                }
                Group imageGroup = new Group();
                if(boardTiles[i][j].isOccupiedByCarpet) {
                    //tile + carpet
                    ImageView carpetImageView = new ImageView(image1);
                    carpetImageView.setFitHeight(TILE_SIZE);
                    carpetImageView.setFitWidth(TILE_SIZE);
                    switch (boardTiles[i][j].getOwnerId()) {
                        case ('c') -> carpetImageView.setImage(image1);
                        case ('y') -> carpetImageView.setImage(image2);
                        case ('r') -> carpetImageView.setImage(image3);
                        case ('p') -> carpetImageView.setImage(image4);
                    }
                    imageGroup.getChildren().addAll(tileImageView, carpetImageView);
                }else {
                    //tile
                    imageGroup.getChildren().addAll(tileImageView);
                }
                grid.add(imageGroup, j, i);
            }
        }
    }

    /**
     * This method displays player information in a VBox layout,
     * call this method in a for loop to display all players in a same VBox layout
     * @param vb the layout to contain player information
     * @param player the player to be shown
     * @author Jinqiao Jiang
     */
    public static void displayPlayerInformation(VBox vb, Player player) {
        //player id information
        Label label_id = new Label("Player id: ");
        if (player.isComputer()) {
            label_id.setText("Comp. id: ");
        }
        label_id.setStyle("-fx-font-size: 20px");
        label_id.setTextFill(Paint.valueOf("#000000"));

        Text id = new Text(Character.toString(player.getId()));
        id.setStyle("-fx-font-size: 20px");

        //player money information
        Label label_money = new Label("Dirhams:");
        label_money.setStyle("-fx-font-size: 20px");
        label_money.setTextFill(Paint.valueOf("#000000"));

        Text money = new Text(Integer.toString(player.getMoney()));
        money.setStyle("-fx-font-size: 20px");

        //player carpet image
        ImageView icon = new ImageView(player.getImage());
        icon.setFitWidth(50);
        icon.setFitHeight(50);

        //player rugs information
        Label label_rugs = new Label("Rugs Left:");
        label_rugs.setStyle("-fx-font-size: 20px");
        label_rugs.setTextFill(Paint.valueOf("#000000"));

        Text rugs = new Text(Integer.toString(player.getRugs()));
        rugs.setStyle("-fx-font-size: 20px");

        //player inTheGame information
        Text inGame = new Text(player.isInTheGame()? "Playing":"Broken");
        inGame.setStyle("-fx-font-size: 20px");

        //set inner layouts, VBox vb contains one HBox, this HBox contains one GridPane and one VBox
        HBox playerHB = new HBox();
        playerHB.setAlignment(Pos.CENTER_LEFT);
        playerHB.setPrefHeight(50);
        playerHB.setSpacing(50);
        playerHB.setStyle("-fx-border-color: black;-fx-border-width: 2px");

        GridPane playerLeftGrid = new GridPane();
        playerLeftGrid.add(label_id, 0, 0);
        playerLeftGrid.add(id, 1, 0);
        playerLeftGrid.add(label_money, 0, 1);
        playerLeftGrid.add(money, 1, 1);
        playerLeftGrid.add(label_rugs, 0, 2);
        playerLeftGrid.add(rugs, 1, 2);

        VBox playerRightVB = new VBox();
        playerRightVB.setAlignment(Pos.CENTER_RIGHT);
        playerRightVB.getChildren().addAll(icon, inGame);

        playerHB.getChildren().addAll(playerLeftGrid, playerRightVB);
        vb.getChildren().add(playerHB);
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label boardLabel = new Label("Game State:");
        boardTextField = new TextField();
        boardTextField.setPrefWidth(800);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                displayState(boardTextField.getText());
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(boardLabel,
                boardTextField, button);
        hb.setSpacing(10);
        hb.setLayoutX(50);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Marrakech Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls); // Add UI controls to root
        makeControls(); // Initialize UI controls
        primaryStage.setResizable(false);   //no resize
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

//test gameString Pc03015iPy03014iPr03015iPp03015iA63EBy01y01n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00
// Pc03700iPy03200iPp02400iPr02700iA61WBn00y24y24y23y08p19r07r24p24c14y23r03p28r07r24y18c14y03r12p28y21r29r29y11c18c18r28r28r15c19n00c22c22r20c10c30c30y25r25p04p04n00y05p26p26r25n00n00n00