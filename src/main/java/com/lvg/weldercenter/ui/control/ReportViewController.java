package com.lvg.weldercenter.ui.control;

import com.lvg.weldercenter.models.TotalProtocol;
import com.lvg.weldercenter.spring.factories.ServiceUIFactory;
import com.lvg.weldercenter.ui.entities.*;
import com.lvg.weldercenter.ui.entities.report.JournalReportEntity;
import com.lvg.weldercenter.ui.entities.report.TheoryReportEntity;
import com.lvg.weldercenter.ui.servicesui.PersonalProtocolServiceUI;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.swing.JRViewer;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.*;

/**
 * Created by Victor on 18.05.2015.
 */
public class ReportViewController extends GenericController {
    private final Logger LOGGER = Logger.getLogger(ReportViewController.class);
    private JRViewer reportViewer;
    private final URL TOTAL_PROTOCOL_REPORT_URL = getClass().getResource("/reports/total-protocol-rep.jrxml");
    private final URL THEORY_PROTOCOL_REPORT_URL = getClass().getResource("/reports/total-protocol-theory-rep.jrxml");
    private final URL JOURNAL_REPORT_URL = getClass().getResource("/reports/journal-rep.jrxml");
    private final URL JOURNAL_SUBREPORT_WELDERS_DETAIL_URL = getClass().getResource("/reports/journal-about-students-subrep.jrxml");
    private final URL UNIVERS_FONT_URL = getClass().getResource("/fonts/Univers_Medium.ttf");

    private PersonalProtocolServiceUI personalProtocolServiceUI = ServiceUIFactory.getPersonalProtocolServiceUI();

    @FXML
    BorderPane mainReportViewPane;
    @FXML
    SwingNode swingNode;
    @FXML
    Button btClose;

    private JPanel reportPanel;


    private Collection<TheoryReportEntity> theoryReportEntityList = new ArrayList<TheoryReportEntity>();
    private Collection<JournalReportEntity> journalReportEntities = new ArrayList<JournalReportEntity>();
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

    public void showTheoryProtocolReport(TotalProtocolUI protocolUI){
        initTheoryReportEntityList(protocolUI);
        fillTotalProtocolParameters(protocolUI);
        try {
            JasperReport report = JasperCompileManager.compileReport(THEORY_PROTOCOL_REPORT_URL.getFile());
            JasperPrint print = JasperFillManager.fillReport(report, protocolUI.getParameters(),
                    new JRBeanCollectionDataSource(theoryReportEntityList));
            addReportPrintToPanel(print);
            LOGGER.debug("THEORY PROTOCOL: "+theoryReportEntityList);
            reportPanel.setVisible(true);
        }catch (JRException ex){
            LOGGER.error("SHOW THEORY REPORT VIEW: Could not load report: "+ex.getMessage(),ex);
        }
    }



    private void initTheoryReportEntityList(TotalProtocolUI protocolUI){
        theoryReportEntityList.clear();
        for (WelderUI welderUI: protocolUI.getJournal().getWelders()){
            TheoryReportEntity te = new TheoryReportEntity();
            StringBuilder weldMethodsAll = new StringBuilder();
            te.setWelder(welderUI.getFormatName("SUR-nn-sec"));

            for (String wm : welderUI.getWeldMethods()){
                weldMethodsAll.append(wm+"; ");
            }
            weldMethodsAll.deleteCharAt(weldMethodsAll.lastIndexOf(";"));
            te.setWeldMethods(weldMethodsAll.toString());
            PersonalProtocolUI pp = personalProtocolServiceUI.getPersonalProtocolUIFromDB(protocolUI,welderUI.getSurnameNameSecname());
            if(pp!=null){
                StringBuilder ndtDocsAll = new StringBuilder();
                for (NDTDocumentUI ndtDoc: pp.getNdtDocuments()) {

                    ndtDocsAll.append(ndtDoc.getName()+"; ");
                }
                ndtDocsAll.deleteCharAt(ndtDocsAll.lastIndexOf(";"));
                te.setNdtDocs(ndtDocsAll.toString());
                if(pp.getTheoryTest() != null){
                    te.setNumberTickets(pp.getTheoryTest().getTicketNumber());
                    te.setRating(pp.getTheoryTest().getRating());
                }
            }
            theoryReportEntityList.add(te);
        }
    }

    public void showJournalReport(JournalUI journalUI){
        initJournalReportEntityList(journalUI);
        try{

            JasperReport subReport = JasperCompileManager.compileReport(JOURNAL_SUBREPORT_WELDERS_DETAIL_URL.getFile());
            journalUI.getParameters().put("WELDER_DETAIL_SUBREPORT",subReport);
            journalUI.getParameters().put("WELDER_DETAIL_DATA_SOURCE",new JRBeanCollectionDataSource(journalReportEntities));
            JasperReport report = JasperCompileManager.compileReport(JOURNAL_REPORT_URL.getFile());

            JasperPrint print = JasperFillManager.fillReport(report, journalUI.getParameters(),
                    new JREmptyDataSource());
            addReportPrintToPanel(print);
            reportPanel.setVisible(true);
        }catch (JRException ex){
            LOGGER.error("SHOW JOURNAL REPORT VIEW: Could not load report: "+ex.getMessage(),ex);
        }
    }

    private void initJournalReportEntityList(JournalUI journalUI){
        journalReportEntities.clear();
        for (WelderUI welderUI: journalUI.getWelders()){
            journalReportEntities.add(new JournalReportEntity(journalUI,welderUI));
        }
    }

    private void fillTotalProtocolParameters(TotalProtocolUI totalProtocolUI){
        totalProtocolUI.getParameters().put("UNIVERS_FONT_PATH",UNIVERS_FONT_URL.getFile());
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