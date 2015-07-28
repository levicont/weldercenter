package com.lvg.weldercenter.ui.control;

import com.lvg.weldercenter.ui.entities.OrganizationUI;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Victor on 28.07.2015.
 */
public class PropertiesController extends GenericController {
    private final Logger LOGGER = Logger.getLogger(PropertiesController.class);

    @FXML
    BorderPane mainPropertiesPane;

    @FXML
    TabPane tabPaneAllProperties;

    //Organization tab
    @FXML
    Tab tabOrganizations;
    @FXML
    TableView<OrganizationUI> organizationsTableView;
    @FXML
    TableColumn<OrganizationUI, Long> tblColumnOrgId;
    @FXML
    TableColumn<OrganizationUI, String> tblColumnOrgName;
    @FXML
    TableColumn<OrganizationUI, String> tblColumnOrgAdress;
    @FXML
    TableColumn<OrganizationUI, String> tblColumnOrgPhone;
    @FXML
    TextField tfxOrganizationName;
    @FXML
    TextField tfxOrganizationAdress;
    @FXML
    TextField tfxOrganizationPhone;
    @FXML
    TextField txfSearchOrganization;






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("INITIALIZING Properties Pane");
    }

    @FXML
    private void closePropertiesPane(){
        if(mainPropertiesPane.isVisible()){
            mainPropertiesPane.setVisible(false);
            getControllerManager().getMainFrameController().showLogo();
        }
    }
}
