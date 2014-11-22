package com.lvg.weldercenter.ui.control;

import com.lvg.weldercenter.models.WeldMethod;
import com.lvg.weldercenter.services.WeldMethodService;
import com.lvg.weldercenter.services.WelderService;
import com.lvg.weldercenter.services.hibernate.WelderServiceHiberImpl;
import com.lvg.weldercenter.spring.factories.ServiceFactory;
import com.lvg.weldercenter.ui.entities.WelderUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Victor Levchenko LVG Corp. on 14.11.14.
 */
public class WelderController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(WelderController.class);

    private WelderService welderService = ServiceFactory.getWelderService();
    private WeldMethodService weldMethodService = ServiceFactory.getWeldMethodService();

    @FXML
    private TableView<WelderUI> welderTableView;

    @FXML
    private Button btUpdate;

    @FXML
    private TableColumn<WelderUI,Long> id;
    @FXML
    private TableColumn<WelderUI, String> name;
    @FXML
    private TableColumn<WelderUI, String> surname;
    @FXML
    private TableColumn<WelderUI, String> secname;
    @FXML
    private TableColumn<WelderUI, Date> birthday;
    @FXML
    private TableColumn<WelderUI, String> job;
    @FXML
    private MenuButton mbtWeldMethod;
    @FXML
    private TextField txfWeldMethod;
    @FXML
    private TableColumn<WelderUI, ObservableList<String>> weldMethods;

    private ObservableList<String> weldMethodsList;
    private ObservableList<String> txfWeldMethodList;
    private ObservableList<WelderUI> welders;

    public WelderController(){
        weldMethodsList = FXCollections.observableArrayList(getWeldMethods(weldMethodService.getAll()));

        txfWeldMethodList = FXCollections.observableArrayList();

        welders = FXCollections.observableArrayList(getWelders(welderService.getAll()));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillMenuButton(mbtWeldMethod, getWeldMethods(weldMethodService.getAll()));


        id.setCellValueFactory(new PropertyValueFactory<WelderUI, Long>("id"));
        name.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("surname"));
        secname.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("secname"));
        birthday.setCellValueFactory(new PropertyValueFactory<WelderUI, Date>("birthday"));
        job.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("job"));

        weldMethods.setCellValueFactory(new PropertyValueFactory<WelderUI, ObservableList<String>>("weldMethods"));


        welderTableView.setItems(welders);
        welderTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);



    }

    private void updateTextFieldWeldMethodList(){
        txfWeldMethodList.clear();
        for(MenuItem item : mbtWeldMethod.getItems()){
            CheckMenuItem chkItem = (CheckMenuItem)item;
            if(chkItem.isSelected()){
                txfWeldMethodList.add(chkItem.getText());
            }
        }
    }

    private void updateTextFieldWeldMethods(){
        updateTextFieldWeldMethodList();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i<txfWeldMethodList.size(); i++){
            String sufix;
            if(i == (txfWeldMethodList.size()-1)){
                sufix="";
            }else {
                sufix="; ";
            }
            text.append(txfWeldMethodList.get(i)+sufix);

        }
        txfWeldMethod.setText(text.toString());
    }


    private List<WelderUI> getWelders(List<com.lvg.weldercenter.models.Welder> welderList){
        List<WelderUI> list = new ArrayList<WelderUI>();
        for (com.lvg.weldercenter.models.Welder w : welderList){
            WelderUI welder  = new WelderUI(w);
            list.add(welder);
        }
        return list;
    }

    private List<String> getWeldMethods(List<WeldMethod> weldMethodList){
        List<String> list = new ArrayList<String>();
        for(WeldMethod wm : weldMethodList){
            list.add(wm.getName()+" ("+wm.getCode()+")");
        }
        return list;
    }

    private void fillMenuButton(MenuButton mbt, List<String> list){

        for (String title : list){
            CheckMenuItem checkMenuItem = new CheckMenuItem(title);
            mbt.getItems().add(checkMenuItem);
            checkMenuItem.addEventHandler(ActionEvent.ACTION, new TextFieldListUpdater());

        }

    }

    private class TextFieldListUpdater implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            LOGGER.debug("CheckMenuItem is clicked");
            LOGGER.debug("WeldMethodList size is: "+txfWeldMethodList.size());
            System.out.println();
            System.out.println();
            updateTextFieldWeldMethods();
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
