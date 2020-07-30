package WorldHunger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main extends Application {
    Stage window;
   private Authentication authentication;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
//        window.setTitle("Login Form Window");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));

        Text welcomeTxt = new Text("HUNGER Z");
        welcomeTxt.setFont(Font.font("Tahoma", FontWeight.LIGHT, 20));
        grid.add(welcomeTxt, 0, 0);

        Label lblUser = new Label("Username");
        grid.add(lblUser, 0, 1);

        TextField txtUser = new TextField();
        txtUser.setPromptText("Username");
        grid.add(txtUser, 1, 1);

        Label lblPassword = new Label("Password");
        grid.add(lblPassword, 0, 2);

        PasswordField pwBox = new PasswordField();
        pwBox.setPromptText("Password");
        grid.add(pwBox, 1, 2);

        Button loginBtn = new Button("Login");

        loginBtn.setOnAction(e -> {

            PreparedStatement ps;
            String uname = lblUser.getText();
            String pass = pwBox.getText();
            String tTry="t";

            String query = "SELECT * FROM user_details WHERE username ='" + uname + "' AND password ='" + pass + "'";


                try {
                    ps = DataConnection.getConnection().prepareStatement(query);
                    ResultSet rs = ps.executeQuery();
                    rs.next();
                } catch (SQLException ex) {
                    Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);

                }
            if ( !lblUser.getText().trim().isEmpty()&&!pwBox.getText().trim().isEmpty()) {
                this.authentication = new Authentication();
                try {
                    window.setScene(authentication.setLayout());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        grid.add(loginBtn, 1, 3);

        //hyperlink to go to sign up
        Hyperlink signup = new Hyperlink("Don't have an account?");
        signup.setOnAction(e -> {

            Signup x = new Signup();
            x.start(primaryStage);

        });
        grid.add(signup, 0, 3);

        Scene scene = new Scene(grid, 500, 500);
        window.setScene(scene);
        window.show();
    }

    private void showView(AppViews view, String extra) {
        switch (view) {
            case Authentication:
                new Authentication();
                break;


        }

    }

    public  class Signup extends Application {
        Stage window;
        private PreparedStatement pst;
        private Connection con;
        private Scene Scene;

        @Override
        public void start(Stage primaryStage) {
            window = primaryStage;
            window.setTitle("Signup Form Window");

            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setVgap(10);
            grid.setHgap(10);
            grid.setPadding(new Insets(10));

            Text welcomeTxt = new Text("Signup Please");
            welcomeTxt.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
            grid.add(welcomeTxt, 0, 0);

            Label lblUser = new Label("Username");
            grid.add(lblUser, 0, 1);

            TextField txtUser = new TextField();
            txtUser.setPromptText("Username");
            grid.add(txtUser, 1, 1);


            Label lblFname = new Label("First Name");
            grid.add(lblFname, 0, 2);

            TextField txtFname = new TextField();
            txtUser.setPromptText("First Name");
            grid.add(txtFname, 1, 2);


            Label lblLname = new Label("Last name");
            grid.add(lblLname, 0, 3);

            TextField txtLname = new TextField();
            txtUser.setPromptText("Last Name");
            grid.add(txtLname, 1, 3);

            Label lblEmail = new Label("Email");
            grid.add(lblEmail, 0, 4);

            TextField txtEmail = new TextField();
            txtEmail.setPromptText("Email");
            grid.add(txtEmail, 1, 4);

            Label lblPassword = new Label("Password");
            grid.add(lblPassword, 0, 5);

            PasswordField pwBox = new PasswordField();
            pwBox.setPromptText("Password");
            grid.add(pwBox, 1, 5);


            Button SignupBtn = new Button("Signup");
            SignupBtn.setOnAction((ActionEvent event) -> {
                String username=txtUser.getText();
                String fname=txtFname.getText();
                String lname=txtLname.getText();
                String email=txtEmail.getText();
                String pass=pwBox.getText();

                PreparedStatement ps;
                String query="INSERT INTO 'user_details'('username','first_name','last_name','email','password') VALUES(?,?,?,?,?)";

                try {


                        ps = DataConnection.getConnection().prepareStatement(query);

                        ps.setString(1, username);
                        ps.setString(2, fname);
                        ps.setString(3, lname);
                        ps.setString(4, email);
                        ps.setString(5, pass);


                    } catch (SQLException ex) {
                        Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        if(!txtUser.getText().trim().isEmpty()&&!txtFname.getText().trim().isEmpty()&&!txtLname.getText().trim().isEmpty()&&!txtEmail.getText().trim().isEmpty()&&!pwBox.getText().trim().isEmpty()) {

                            SignupBtn.setOnAction(e -> {
                                authentication = new Authentication();
                                try {
                                    window.setScene(authentication.setLayout());
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            });
                        }
            });


            grid.add(SignupBtn, 1, 6);

            Hyperlink Main = new Hyperlink("Go back to login");
            Main.setOnAction(e -> {

                        Main x = new Main();
                        x.start(primaryStage);

                    });
            grid.add(Main,0,6);

            Scene scene = new Scene(grid, 500, 500);
            window.setScene(scene);
            window.show();
        }


        }
    }






