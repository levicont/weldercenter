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
    @FXML
    Button btAdd;
    @FXML
    Button btEdit;
    @FXML
    Button btDelete;
    @FXML
    Button btSave;


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

    //WeldPatterns tab
    @FXML
    Tab tabWeldPatterns;

    @FXML
    TitledPane titlePaneWeldPatternsTypes;
    @FXML
    ListView<String> listViewWeldPatternsTypes;
    @FXML
    TextField txfWeldPatternTypeCode;
    @FXML
    TextField txfWeldPatternTypeName;
    @FXML
    Button btAddWeldPatternType;
    @FXML
    Button btEditWeldPatternType;
    @FXML
    Button btDeleteWeldPatternType;

    @FXML
    TitledPane titlePaneDiameters;
    @FXML
    ListView<String> listViewDiameters;
    @FXML
    TextField txfDiameter;
    @FXML
    Button btAddDiameter;
    @FXML
    Button btEditDiameter;
    @FXML
    Button btDeleteDiameter;

    @FXML
    TitledPane titlePaneThickness;
    @FXML
    ListView<String> listViewThickness;
    @FXML
    TextField txfThickness;

    @FXML
    TitledPane titlePaneSteelTypes;
    @FXML
    ListView<String> listViewSteelTypes;
    @FXML
    TextField txfSteelType;
    @FXML
    ComboBox<String> cbSteelTypeGroup;

    @FXML
    TitledPane titlePaneSteelGroups;
    @FXML
    ListView<String> listViewSteelGroups;
    @FXML
    TextField txfSteelGroup;
    @FXML
    TextArea txtAreaSteelGroupDecription;












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
