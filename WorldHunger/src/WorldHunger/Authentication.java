package WorldHunger;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Authentication extends GridPane {
    public Stage window;
    public Stage primaryStage;

    public Scene setLayout() throws IOException {
            primaryStage=window;
            this.setAlignment(Pos.CENTER);
            this.setVgap(20);
            this.setHgap(10);
            this.setPadding(new Insets(10));

            Text welcomeTxt=new Text("HUNGER Z");
            welcomeTxt.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
            this.add(welcomeTxt,0,0);


            Button Donate= new Button("I Want To Donate");
            Donate.setOnAction(e->{
                Mpesa darajaApi = new Mpesa(Constants.DARAJA_CONSUMER_KEY, Constants.DARAJA_CONSUMER_SECRET);

                Date date = new Date();
                Timestamp ts=new Timestamp(date.getTime());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                String timestamp = formatter.format(ts);
                try {
                    String accessToken = darajaApi.authenticate();
                    String stkRaw = "174379" + "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919" + timestamp;
                    byte[] bytes = stkRaw.getBytes("ISO-8859-1");
                    String stkPassword = Base64.getEncoder().encodeToString(bytes);
                    darajaApi.STKPushSimulation("174379",
                            stkPassword,
                            timestamp,
                            "CustomerPayBillOnline",
                            "1",
                            "254701477774",
                            "254701477774",
                            "174379",
                            "http://obscure-bayou-52273.herokuapp.com/api/Mpesa/C2BValidation",
                            "http://obscure-bayou-52273.herokuapp.com/api/Mpesa/C2BValidation",
                            "testref",
                            "hunger games");
                }catch (Exception ex) {
                    ex.printStackTrace();
                }

            });
            Button noDonation=new Button("No thank you");
       noDonation.setStyle("-fx-font: 12 arial; -fx-base: #b6e7c9;");
            noDonation.setOnAction(e->{
                Desktop d = Desktop.getDesktop();
                try {
                    d.browse(new URI("https://www.care.org/work/world-hunger"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                }

            });
            this.add(noDonation,0,2);

            this.add(Donate, 0, 1);

            Button logout= new Button("Logout");
            logout.setOnAction(e->{
               System.out.println("THANK YOU FOR USING OUR APPLICATION");

            });
            this.add(logout,0,3);

        Hyperlink log = new Hyperlink("Go back to login");
        log.setOnAction(e -> {
            Main x = new Main();
            x.start(primaryStage);

        });

return new Scene(this,700,700);

        }
}
