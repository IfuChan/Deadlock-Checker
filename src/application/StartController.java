package application;

import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class StartController {
	private String[] num= {"1","2","3","4","5"};
	
	public String NoOfRes;
	public String NoOfProc;
	
	@FXML
    private Label labelproc;
	
	@FXML
    private Button btn1;

    @FXML
    private ChoiceBox<String> ResMenu;

    @FXML
    private ChoiceBox<String> ProcMenu;
    
    @FXML
    private void initialize() {
    	ResMenu.setValue("1");
    	ProcMenu.setValue("1");
    	ResMenu.getItems().addAll(num);
    	ProcMenu.getItems().addAll(num);
    	
    }
    
    /*
     * to change one scene to another
     * Parent root2=FXMLLoader.load(getClass().getResource("Sec.fxml"));
    	Scene scene2=new Scene(root2);
    	Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(scene2);
    	window.show();
     */

    @FXML
    void submit1(ActionEvent event) throws IOException {
    	NoOfRes=ResMenu.getValue();
    	NoOfProc=ProcMenu.getValue();
    	//send data to second scene and open second scene.
    	FXMLLoader loader= new FXMLLoader();
    	loader.setLocation(getClass().getResource("Sec.fxml"));
    	Parent start=loader.load();
    	Scene scene2=new Scene(start);
    	SecController obj=loader.getController();
    	obj.initData(NoOfRes, NoOfProc);
    	Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(scene2);
    	window.show();
    	
    	
    }
    
    
}
