package com.lvg.weldercenter.ui.control;

import com.lvg.weldercenter.ui.entities.TotalProtocolUI;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.swing.JRViewer;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Victor on 18.05.2015.
 */
public class ReportViewController extends GenericController {
    private final Logger LOGGER = Logger.getLogger(ReportViewController.class);
    private JRViewer reportViewer;
    private final URL TOTAL_PROTOCOL_REPORT_URL = getClass().getResource("/reports/total-protocol-rep.jrxml");

    @FXML
    BorderPane mainReportViewPane;
    @FXML
    SwingNode swingNode;
    @FXML
    Button btClose;

    private JPanel reportPanel;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("INITIALIZING Report Pane");
        init();
    }

    private void init(){
        reportPanel = new JPanel();

        swingNode.setContent(reportPanel);
        reportPanel.setLayout(new BorderLayout());

    }

    public void showTotalProtocolReport(TotalProtocolUI protocolUI){
        try {
            JasperReport report = JasperCompileManager.compileReport(TOTAL_PROTOCOL_REPORT_URL.getFile());
            JasperPrint print = JasperFillManager.fillReport(report, protocolUI.getParameters(),new JREmptyDataSource());
            addReportPrintToPanel(print);
            reportPanel.setVisible(true);
        }catch (JRException ex){
            LOGGER.error("SHOW TOTAL PROTOCOL REPORT VIEW: Could not load report: "+ex.getMessage(),ex);
        }
    }

    private void addReportPrintToPanel(JasperPrint print){
        reportViewer = new JRViewer(print);
        reportViewer.setFitPageZoomRatio();
        for (int i=0; i<reportPanel.getComponents().length; i++){
            reportPanel.remove(i);
        }
        reportPanel.add(reportViewer, BorderLayout.CENTER);
    }

    @FXML
    private void closeReportPane(){
        if(mainReportViewPane.isVisible()){
            mainReportViewPane.setVisible(false);
            getControllerManager().getMainFrameController().showLogo();
        }
    }

    @FXML
    private void showSimpleReportView(){
        try {
            JasperReport report = JasperCompileManager.compileReport(TOTAL_PROTOCOL_REPORT_URL.getFile());
            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<String, Object>(),new JREmptyDataSource());
            addReportPrintToPanel(print);
            reportPanel.setVisible(true);
        }catch (JRException ex){
            LOGGER.error("SHOW SIMPLE REPORT VIEW: Could not load report: "+ex.getMessage(),ex);
        }
    }



}
