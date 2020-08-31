/**
 * <h1>The Video Library Rentals System</h2>
 * @author Dean Munywoki
* @version 1.0
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;

public class Customers extends Application{

    /**
     * <p>Declaring some global DB variables where
     * conn is the Connection variable, url is the database link, user is the username
     * and pass is the password.</p> 
     */
    Connection conn = null; //Connection variable
    String url = "jdbc:mysql://localhost/movierentals";  //Database link
    String user = "root";   //Username
    String pass = "";   //Password

    /**
     * <p>This method is used to display the entire GUI on a stage</P>
     */
    @Override
    public void start(Stage stage) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ce) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }
        /**
         * <p>Label for Customer ID</P>
         */

        Text label_1 = new Text ("Customer ID:");
        Text label_2 = new Text ("Customer name:");
        Text label_3 = new Text ("Customer phone number:");
        //step: create Text Fields
        TextField textField1 = new TextField();

        TextField textField2 = new TextField();
        TextField textField3 = new TextField();


        ComboBox comboBox = new ComboBox();

        /**
         * <p>Save Button</p>
         */
        Button saveButton = new Button("Save customer");

        /**
         * <p>Remove Button</p>
         */
        Button removeButton = new Button("Remove customer");

        /**
         * <p>Save Button Event Handler</p>
         */
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                System.out.println("Data successfully entered into the database");  //Notification Message
                try {
                    String sql = "INSERT INTO customers"+"(customer_id,customer_name,customer_phone_number,isactive) VALUES(?,?,?,?)";   //INSERT statement
                    final PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1,textField1.getText());
                    stmt.setString(1,textField2.getText());
                    stmt.setInt(1, Integer.parseInt(textField3.getText()));
                    stmt.setInt(2,1);
                    stmt.executeUpdate();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        };
        saveButton.addEventHandler(MouseEvent.MOUSE_CLICKED,eventHandler);

        /**
         * <p>Grid Pane</p>
         */
        GridPane gridPane = new GridPane();

        /**
         * <p>Size of the Pane</p>
         */
        gridPane.setMinSize(550, 350);

        /**
         * <p>Padding setting</p>
         */
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        /**
         * <p>Vertical Gaps between the Columns Setting</p>
         */
        gridPane.setVgap(5);

        /**
         * <p>Horizontal Gaps between the Columns Setting</p>
         */
        gridPane.setHgap(5);

        /**
         * <p>Grid alignment setting</p>
         */
        gridPane.setAlignment(Pos.CENTER);

        /**
         * <p>Arranging the label_1 Node in the grid</p>
         */
        gridPane.add(label_1, 0, 0);

        /**
         * <p>Arranging the saveButton node in the grid</p>
         */
        gridPane.add(saveButton, 1, 1);

        /**
         * <p>Arranging the label_2 node in the grid</p>
         */
        gridPane.add(label_2,0,2);

        /**
         * <p>Arranging the comboBox node in the grid</p>
         */
        gridPane.add(comboBox,1,2);

        /**
         * <p>Arranging the removeButton in the grid</p>
         */
        gridPane.add(removeButton, 1, 3);

        /**
         * <p>saveButton node style</p>
         */
        saveButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");

        /**
         * <p>saveButton node size</p>
         */
        saveButton.setMinSize(300, 30);

        /**
         * <p>removeButton node style</p>
         */
        removeButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");

        /**
         * <p>removeButton node size</p>
         */
        removeButton.setMinSize(300, 30);

        /**
         * <p>comboBox node size</p>
         */
        comboBox.setMinSize(300, 30);

        /**
         * <p>label_1 node style</p>
         */
        label_1.setStyle("-fx-font: normal bold 20px 'serif' ");

        /**
         * <p>label_2 node style</p>
         */
        label_2.setStyle("-fx-font: normal bold 20px 'serif' ");

        /**
         * <p>gridPane node</p> 
         */
        gridPane.setStyle("-fx-background-color: BEIGE;");

        /**
         * <p>Scene object</p>
         */
        Scene scene = new Scene(gridPane);

        /**
         * <p>Stage Title</p>
         */
        stage.setTitle("Customer Registration Page");

        /**
         * <p>Stage scene</p>
         */
        stage.setScene(scene);

        /**
         * <p>Display the contents of the stage</p>
         */
        stage.show();
    }
    /**
     * <p>Launching the application</p>
     * @param args
     */
    public static void main(String[] args){
        launch(args);
    }

}