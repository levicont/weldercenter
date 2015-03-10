package com.lvg.weldercenter.ui.control;

import com.lvg.weldercenter.models.*;
import com.lvg.weldercenter.services.CommissionCertificationService;
import com.lvg.weldercenter.services.JournalService;
import com.lvg.weldercenter.services.PersonalProtocolService;
import com.lvg.weldercenter.services.TotalProtocolService;
import com.lvg.weldercenter.spring.factories.ServiceFactory;
import com.lvg.weldercenter.spring.factories.ServiceUIFactory;
import com.lvg.weldercenter.ui.entities.*;
import com.lvg.weldercenter.ui.servicesui.TotalProtocolServiceUI;
import com.lvg.weldercenter.ui.util.EventFXUtil;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.apache.log4j.Logger;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Victor Levchenko LVG Corp. on 20.01.15.
 */
public class ProtocolController extends GenericController {
    private static final Logger LOGGER = Logger.getLogger(ProtocolController.class);
    private static final String TREE_ROOT_ITEM_NAME = "Общие протоколы";
    private static final String PERSONAL_PROTOCOL_PREFIX_NAME = "Перс. протокол: ";

    private JournalService journalService = ServiceFactory.getJournalService();
    private PersonalProtocolService personalProtocolService = ServiceFactory.getPersonalProtocolService();
    private CommissionCertificationService commissionCertificationService =
            ServiceFactory.getCommissionCertificationService();
    private TotalProtocolService totalProtocolService = ServiceFactory.getTotalProtocolService();
    private TotalProtocolServiceUI totalProtocolServiceUI = ServiceUIFactory.getTotalProtocolServiceUI();

    @FXML
    BorderPane mainProtocolPane;

    @FXML
    TreeView<String> protocolsTreeView;

    //Total protocol components
    @FXML
    Tab tabTotalProtocol;
    @FXML
    Accordion accordionTotalProtocol;
    @FXML
    BorderPane totalProtocolBorderPane;
    @FXML
    Label lbTotalProtocolNumber;
    @FXML
    Label lbTotalProtocolDate;
    @FXML
    TitledPane titlePaneCommissionCert;
    @FXML
    ComboBox<Long> cbIDCommissionCert;
    @FXML
    TextField txfCommissionHead;
    @FXML
    TextField txfCommissionWeldSpecialist;
    @FXML
    TextField txfCommissionNDTSpecialist;
    @FXML
    TextField txfCommissionSafetySpecialist;
    @FXML
    TitledPane titlePaneTotalProtocolWelders;
    @FXML
    TableView<WelderUI> tableViewWelders;
    @FXML
    TableColumn<WelderUI, Long> tcolWelderID;
    @FXML
    TableColumn<WelderUI, String> tcolWelderSurname;
    @FXML
    TableColumn<WelderUI, String> tcolWelderName;
    @FXML
    TableColumn<WelderUI, String> tcolWelderSecname;
    @FXML
    TableColumn<WelderUI, ObservableList<String>> tcolWelderWeldMethod;
    @FXML
    TitledPane titlePaneTotalProtocolPrint;
    @FXML
    Button btPrintTotalProtocol;
    @FXML
    Button btPrintRTProtocol;
    @FXML
    Button btPrintVTProtocol;
    @FXML
    Button btPrintMechProtocol;
    @FXML
    TitledPane titlePaneTotalProtocolJournal;
    @FXML
    Label lbTotalProtocolJournalNumber;
    @FXML
    Label lbTotalProtocolJournalDateBegin;
    @FXML
    Label lbTotalProtocolJournalDateEnd;
    @FXML
    Button btPrintJournal;
    @FXML
    Button btGoToJournal;

    //Personal protocol components
    @FXML
    Tab tabPersonalProtocol;
    @FXML
    TextField txfPersonalProtocolNumber;
    @FXML
    DatePicker dpPersonalProtocolDate;
    @FXML
    Label lbWelderFullName;
    @FXML
    TitledPane titlePanePersProtocolWeldPattern;
    @FXML
    TableView<WeldPatternUI> tableViewWeldPatterns;
    @FXML
    TableColumn<WeldPatternUI, Long> tcolWeldPatternID;
    @FXML
    TableColumn<WeldPatternUI, String> tcolWeldPatternType;
    @FXML
    TableColumn<WeldPatternUI, String> tcolWeldPatternSize;
    @FXML
    TableColumn<WeldPatternUI, String> tcolWeldPatternWeldMethod;
    @FXML
    Button btAddWeldPattern;
    @FXML
    Button btEditWeldPattern;
    @FXML
    Button btDeleteWeldPattern;
    @FXML
    TitledPane titlePanePersProtocolTheoryTest;
    @FXML
    TextField  txfTheoryTestTicketNumber;
    @FXML
    ComboBox<String> cbTheoryTestRating;
    @FXML
    TitledPane titlePaneNDTDocuments;
    @FXML
    ListView<String> listViewAttestDocs;
    @FXML
    Button btRemoveAllDocs;
    @FXML
    Button btRemoveSingleDoc;
    @FXML
    ListView<String> listViewAllDocs;
    @FXML
    Button btAddAllDocsToProtocol;
    @FXML
    Button btAddSingleDocToProtocol;
    @FXML
    TitledPane titlePaneResolutionCert;
    @FXML
    TextArea textAreaResolutionCert;
    @FXML
    Button btCleanResolutionText;

    //Weld pattern components
    @FXML
    Tab tabWeldPattern;
    @FXML
    TitledPane titlePaneWeldPatternOption;
    @FXML
    ComboBox<String> cbWeldPatternDetail;
    @FXML
    ComboBox<String> cbWeldPatternDiameter;
    @FXML
    ComboBox<String> cbWeldPatternThickness;
    @FXML
    TextField txfWeldPatternMark;
    @FXML
    CheckBox chkWeldPatternHeating;
    @FXML
    CheckBox chkWeldPatternHeatTreatment;
    @FXML
    ComboBox<String> cbWeldPatternSteelType;
    @FXML
    TextField txfWeldPatternWeldPosition;
    @FXML
    ChoiceBox<String> choiceBoxWeldPatternWeldPosition;
    @FXML
    ComboBox<String> cbWeldPatternWeldMethod;
    @FXML
    CheckBox chkWeldPatternElectrode;
    @FXML
    ComboBox<String> cbWeldPatternElectrode;
    @FXML
    CheckBox chkWeldPatternWeldWire;
    @FXML
    ComboBox<String> cbWeldPatternWeldWire;
    @FXML
    CheckBox chkWeldPatternWeldGas;
    @FXML
    ComboBox<String> cbWeldPatternWeldGas;
    @FXML
    TitledPane titlePaneWeldPatternNDT;
    @FXML
    CheckBox chkWeldPatternVT;
    @FXML
    TextField txfWeldPatternVTNumber;
    @FXML
    DatePicker dpWeldPatternVTDate;
    @FXML
    TextArea textAreaWeldPatternVTDefects;
    @FXML
    ComboBox<String> cbWeldPatternVTEvaluation;
    @FXML
    CheckBox chkWeldPatternRT;
    @FXML
    TextField txfWeldPatternRTNumber;
    @FXML
    DatePicker dpWeldPatternRTDate;
    @FXML
    TextField txfWeldPatternRTSensitivity;
    @FXML
    TextArea textAreaWeldPatternRTDefects;
    @FXML
    ComboBox<String> cbWeldPatternRTEvaluation;
    @FXML
    CheckBox chkWeldPatternMech;
    @FXML
    TextField txfWeldPatternMechNumber;
    @FXML
    DatePicker dpWeldPatternMechDate;
    @FXML
    TextField txfWeldPatternMechAngle;
    @FXML
    ComboBox<String> cbWeldPatternMechEvaluation;
    @FXML
    Button btWeldPatternSave;
    @FXML
    Button btWeldPatternCancel;



    private TotalProtocolUI selectedTotalProtocolUI = null;

    private ObservableList<TreeItem<String>> totalProtocols = FXCollections.observableArrayList();
    private ObservableList<TotalProtocolUI> cachedTotalProtocols = FXCollections.observableArrayList();
    private ObservableList<WelderUI> weldersUIinTotalProtocol = FXCollections.observableArrayList();
    private ObservableList<Long> commissionIDList = FXCollections.observableArrayList();
    private ObservableList<CommissionCertificationUI> commissionCertificationUIList =
            FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("INITIALIZING ProtocolPane");
        initProtocolsTreeView();
        initWeldPatternTab();
        initTotalProtocolTab();
    }


    private void initProtocolsTreeView(){
        initTotalProtocols();
        TreeItem<String> rootItem = new TreeItem<String>(TREE_ROOT_ITEM_NAME);
        rootItem.getChildren().clear();
        rootItem.getChildren().addAll(totalProtocols);
        protocolsTreeView.setRoot(rootItem);
        protocolsTreeView.addEventHandler(MouseEvent.MOUSE_CLICKED, new ListViewHandler());
        protocolsTreeView.addEventHandler(KeyEvent.KEY_RELEASED, new ListViewHandler());
    }

    private void initTotalProtocols(){
        totalProtocols.clear();
        cachedTotalProtocols.clear();
        List<Journal> journalsDb = journalService.getAll();
        List<PersonalProtocol> personalProtocolsDB = personalProtocolService.getAll();
        List<TotalProtocol> totalProtocolList  = totalProtocolService.getAll();
        List<JournalUI> journalUIList = new ArrayList<JournalUI>();
        List<PersonalProtocolUI> protocolUIFromDB = new ArrayList<PersonalProtocolUI>();

        for (PersonalProtocol pp : personalProtocolsDB){
            if(pp.getJournal()!=null){
                journalUIList.add(new JournalUI(pp.getJournal()));
            }
        }

        for (TotalProtocol tp : totalProtocolList){
            TotalProtocolUI totalProtocolUI = new TotalProtocolUI(tp);
            JournalUI journalUI = totalProtocolUI.getJournal();
            TreeItem<String> treeItem = new TreeItem<String>(totalProtocolUI.toString());
            cachedTotalProtocols.add(totalProtocolUI);
            for (int i = 0; i<journalUI.getWelders().size();i++){
                //TODO load protocols from db
                PersonalProtocolUI pp;
                if(getWelderProtocolFromDB(journalUI.getWelders().get(i),journalUI)!=null){
                    pp = getWelderProtocolFromDB(journalUI.getWelders().get(i),journalUI);
                    treeItem.getChildren().add(new TreeItem<String>(PERSONAL_PROTOCOL_PREFIX_NAME
                            +"(БД): " + pp.toString()));
                }else {
                    pp = new PersonalProtocolUI(journalUI.getWelders().get(i), journalUI);
                    treeItem.getChildren().add(new TreeItem<String>(PERSONAL_PROTOCOL_PREFIX_NAME
                            +pp.toString()));
                }
            }
            totalProtocols.add(treeItem);
        }

    }
    private void initTotalProtocolTab(){
        accordionTotalProtocol.setExpandedPane(titlePaneTotalProtocolWelders);
        initTotalProtocolWeldersTableView();
        initCommissionComboBox();
        //totalProtocolBorderPane.setDisable(true);
    }

    private void initTotalProtocolWeldersTableView(){
        tcolWelderID.setCellValueFactory(new PropertyValueFactory<WelderUI, Long>("id"));
        tcolWelderName.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("name"));
        tcolWelderSurname.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("surname"));
        tcolWelderSecname.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("secname"));
        tcolWelderWeldMethod.setCellValueFactory(new PropertyValueFactory<WelderUI, ObservableList<String>>("weldMethods"));
        tableViewWelders.setItems(weldersUIinTotalProtocol);
        tableViewWelders.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private void initWeldersUIinTotalProtocol(TotalProtocolUI selectedTotalProtocolUI){
        weldersUIinTotalProtocol.clear();
        weldersUIinTotalProtocol.addAll(selectedTotalProtocolUI.getJournal().getWelders());
    }

    private void initCommissionComboBox(){
        initCommissionCertificationUIList();
        cbIDCommissionCert.setItems(commissionIDList);
        cbIDCommissionCert.valueProperty().addListener(new ComboBoxCommissionHandler());
    }

    private void initCommissionTitlePane(TotalProtocolUI selectedTotalProtocolUI){
        CommissionCertificationUI currentCommission = selectedTotalProtocolUI.getCommissionCertification();

        if(currentCommission.getId() != 0){
            cbIDCommissionCert.getSelectionModel().select(currentCommission.getId());
            txfCommissionHead.setText(currentCommission.getHead().getFullTeacherName());
            txfCommissionNDTSpecialist.setText(currentCommission.getNdtSpecialist().getFullTeacherName());
            txfCommissionWeldSpecialist.setText(currentCommission.getWeldSpecialist().getFullTeacherName());
            txfCommissionSafetySpecialist.setText(currentCommission.getSafetySpecialist().getFullTeacherName());
        }else{
            clearCommissionTitlePane();
        }
    }

    private void initJournalTitledPane(TotalProtocolUI selectedTotalProtocolUI){
        JournalUI journalUI = selectedTotalProtocolUI.getJournal();
        lbTotalProtocolJournalNumber.setText(journalUI.getNumber());
        lbTotalProtocolJournalDateBegin.setText(journalUI.getDateBeginFormat());
        lbTotalProtocolJournalDateEnd.setText(journalUI.getDateEndFormat());
    }

    private void clearCommissionTitlePane(){
        cbIDCommissionCert.getSelectionModel().clearSelection();
        txfCommissionHead.setText("");
        txfCommissionSafetySpecialist.setText("");
        txfCommissionNDTSpecialist.setText("");
        txfCommissionWeldSpecialist.setText("");
    }

    private void initCommissionCertificationUIList(){
        commissionCertificationUIList.clear();
        for (CommissionCertification cc : commissionCertificationService.getAll()){
            commissionCertificationUIList.add(new CommissionCertificationUI(cc));
            commissionIDList.add(cc.getCommissionCertificationId());
        }
    }

    private void initWeldPatternTab(){
        tabWeldPattern.setDisable(true);
        tabWeldPattern.setClosable(true);
    }



    private PersonalProtocolUI getWelderProtocolFromDB(WelderUI welderUI, JournalUI journalUI){
        List<PersonalProtocol> personalProtocols = personalProtocolService.getAll();
        for (PersonalProtocol pp : personalProtocols){
            if (pp.getJournal()!= null){
                JournalUI journalUIDB = new JournalUI(pp.getJournal());
                if(pp.getWelder()!=null){
                   WelderUI welderUIDB = new WelderUI(pp.getWelder());
                    if(journalUIDB.equals(journalUI) && welderUIDB.equals(welderUI)){
                        return new PersonalProtocolUI(pp);
                    }
                }
            }
        }
        return null;
    }

    private void showSelectedTotalProtocol(TotalProtocolUI selectedTotalProtocol){
        lbTotalProtocolNumber.setText(selectedTotalProtocol.getNumber());
        lbTotalProtocolDate.setText(selectedTotalProtocol.getDateCertFormat());
        initWeldersUIinTotalProtocol(selectedTotalProtocol);
        initCommissionTitlePane(selectedTotalProtocol);
        initJournalTitledPane(selectedTotalProtocol);

    }

    @FXML
    private void closeProtocolPane(){
        if(mainProtocolPane.isVisible()){
            mainProtocolPane.setVisible(false);
            getControllerManager().getMainFrameController().showLogo();
        }
    }

    @FXML
    private void refreshProtocolsTreeView(){
        initProtocolsTreeView();
    }

    @FXML
    private void gotoSelectedJournal(){
        if(selectedTotalProtocolUI!=null){
            JournalUI journalUI = selectedTotalProtocolUI.getJournal();
            closeProtocolPane();
            getControllerManager().showJournalPane();
            getControllerManager().getJournalController().journalTableView.getSelectionModel().clearSelection();
            getControllerManager().getJournalController().journalTableView.getSelectionModel().select(journalUI);
            getControllerManager().getJournalController().journalTableView.requestFocus();
        }
    }

    private class ComboBoxCommissionHandler implements InvalidationListener{

        @Override
        public void invalidated(Observable observable) {
            LOGGER.debug("COMBO BOX COMMISSION HANDLER: invalidated");
            Long selectedID = cbIDCommissionCert.getSelectionModel().getSelectedItem();
            if(selectedID !=null){
                for(CommissionCertificationUI cc : commissionCertificationUIList){
                    if(selectedID == cc.getId()){
                        selectedTotalProtocolUI.setCommissionCertification(cc);
                        initCommissionTitlePane(selectedTotalProtocolUI);
                        return;
                    }
                }
            }else {
                clearCommissionTitlePane();
            }
        }

    }

    private class ListViewHandler implements EventHandler<Event> {

        private void doSelectProtocol(){

            TreeItem<String> protocolItem = protocolsTreeView.getSelectionModel().getSelectedItem();
            if (protocolItem==null){
                LOGGER.debug("TREE LIST VIEW HANDLER: No one tree item is selected");
                return;
            }
            if(protocolItem.getValue().equals(TREE_ROOT_ITEM_NAME)){
                selectedTotalProtocolUI = null;
                LOGGER.debug("TREE LIST VIEW HANDLER: No one protocol is selected");
                return;
            }
            if(protocolItem.getValue().contains(PERSONAL_PROTOCOL_PREFIX_NAME)){
                TreeItem<String> totalProtocolItem = protocolItem.getParent();
                selectedTotalProtocolUI =
                        totalProtocolServiceUI.getTotalProtocolUIByToStringMethod(totalProtocolItem.getValue(),cachedTotalProtocols);
                showSelectedTotalProtocol(selectedTotalProtocolUI);
                LOGGER.debug("TREE LIST VIEW HANDLER: The personal protocol is selected");
                return;
            }
            selectedTotalProtocolUI =
                    totalProtocolServiceUI.getTotalProtocolUIByToStringMethod(protocolItem.getValue(), cachedTotalProtocols);
            if(selectedTotalProtocolUI != null){
                LOGGER.debug("TREE LIST VIEW HANDLER: Selected protocol is: \n"+"id = "+selectedTotalProtocolUI.getId()+
                        "; "+selectedTotalProtocolUI+"\n");
                showSelectedTotalProtocol(selectedTotalProtocolUI);
                //if(!totalProtocolBorderPane.isDisabled())
                //    totalProtocolBorderPane.setDisable(true);
            }
        }

        @Override
        public void handle(Event event) {
            if (event.getClass().equals(KeyEvent.class)){
                KeyEvent keyEvent = (KeyEvent)event;
                if(keyEvent.getCode().equals(KeyCode.DOWN) || keyEvent.getCode().equals(KeyCode.UP)){
                    LOGGER.debug("TREE LIST VIEW HANDLER: Key typed on protocols list");
                    doSelectProtocol();
                    return;
                }
                return;
            }
            LOGGER.debug("TREE LIST VIEW HANDLER: Mouse clicked on protocols list");
            doSelectProtocol();



        }
    }
}
