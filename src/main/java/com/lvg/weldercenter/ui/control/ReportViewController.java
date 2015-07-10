package com.lvg.weldercenter.ui.control;

import com.lvg.weldercenter.spring.factories.ServiceUIFactory;
import com.lvg.weldercenter.ui.entities.*;
import com.lvg.weldercenter.ui.entities.report.*;
import com.lvg.weldercenter.ui.servicesui.PersonalProtocolServiceUI;
import com.lvg.weldercenter.ui.util.DateUtil;
import com.lvg.weldercenter.ui.util.TimeTableUtil;
import com.lvg.weldercenter.ui.util.managers.TimeTableUtilManager;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;

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
    private final URL JOURNAL_SUBREPORT_VISIT_TABLE_URL = getClass().getResource("/reports/journal-visit-table-subrep.jrxml");
    private final URL JOURNAL_SUBREPORT_TIME_TABLE_URL = getClass().getResource("/reports/journal-time-table-subrep.jrxml");
    private final URL PERSONAL_PROTOCOL_REPORT_URL = getClass().getResource("/reports/pers-protocol-rep.jrxml");
    private final URL VISUAL_TEST_PROTOCOL_REPORT_URL = getClass().getResource("/reports/visual-test-rep.jrxml");
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
    private Collection<JournalVisitTableReportEntity> journalVisitTableReportEntities  = new ArrayList<JournalVisitTableReportEntity>();
    private Collection<JournalSectionReportEntity> journalSectionReportEntities = new ArrayList<JournalSectionReportEntity>();
    private Collection<PersonalProtocolReportEntity> personalProtocolReportEntities = new ArrayList<PersonalProtocolReportEntity>();
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
        TotalProtocolReportEntity totalProtocolReportEntity = new TotalProtocolReportEntity(protocolUI);
        try {
            JasperReport report = JasperCompileManager.compileReport(TOTAL_PROTOCOL_REPORT_URL.getFile());
            JasperPrint print = JasperFillManager.fillReport(report,
                    totalProtocolReportEntity.getParameters(),new JREmptyDataSource());
            addReportPrintToPanel(print);

            reportPanel.setVisible(true);
        }catch (JRException ex){
            LOGGER.error("SHOW TOTAL PROTOCOL REPORT VIEW: Could not load report: "+ex.getMessage(),ex);
        }
    }

    public void showTheoryProtocolReport(TotalProtocolUI protocolUI){
        initTheoryReportEntityList(protocolUI);
        TotalProtocolReportEntity totalProtocolReportEntity = new TotalProtocolReportEntity(protocolUI);
        totalProtocolReportEntity.getParameters().put("UNIVERS_FONT_PATH",UNIVERS_FONT_URL.getFile());
        try {
            JasperReport report = JasperCompileManager.compileReport(THEORY_PROTOCOL_REPORT_URL.getFile());
            JasperPrint print = JasperFillManager.fillReport(report, totalProtocolReportEntity.getParameters(),
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
            if (weldMethodsAll.length()>0)
                weldMethodsAll.deleteCharAt(weldMethodsAll.lastIndexOf(";"));
            te.setWeldMethods(weldMethodsAll.toString());
            PersonalProtocolUI pp = personalProtocolServiceUI.getPersonalProtocolUIFromDB(protocolUI,welderUI.getSurnameNameSecname());
            if(pp!=null){
                StringBuilder ndtDocsAll = new StringBuilder();
                for (NDTDocumentUI ndtDoc: pp.getNdtDocuments()) {

                    ndtDocsAll.append(ndtDoc.getName()+"; ");
                }
                if (ndtDocsAll.length()>0)
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
        initJournalVisitTableReportEntityList(journalUI);
        initJournalSectionReportEntityList(journalUI);
        JournalReportEntity journalReportEntity = journalReportEntities.iterator().next();
        try{

            JasperReport subReport = JasperCompileManager.compileReport(JOURNAL_SUBREPORT_WELDERS_DETAIL_URL.getFile());
            journalReportEntity.getParameters().put("WELDER_DETAIL_SUBREPORT",subReport);
            journalReportEntity.getParameters().put("WELDER_DETAIL_DATA_SOURCE",new JRBeanCollectionDataSource(journalReportEntities));

            JasperReport subReportVisitTable = JasperCompileManager.compileReport(JOURNAL_SUBREPORT_VISIT_TABLE_URL.getFile());
            journalReportEntity.getParameters().put("VISIT_TABLE_SUBREPORT",subReportVisitTable);
            journalReportEntity.getParameters().put("VISIT_TABLE_DATA_SOURCE",
                    new JRBeanCollectionDataSource(journalVisitTableReportEntities));

            JasperReport subReportTimeTable = JasperCompileManager.compileReport(JOURNAL_SUBREPORT_TIME_TABLE_URL.getFile());

            journalReportEntity.getParameters().put("TIME_TABLE_SUBREPORT", subReportTimeTable);
            journalReportEntity.getParameters().put("TIME_TABLE_DATA_SOURCE",
                    new JRBeanCollectionDataSource(journalSectionReportEntities));
            journalReportEntity.getParameters().put("PARAMETERS_MAP", journalReportEntity.getParameters());
            JasperReport report = JasperCompileManager.compileReport(JOURNAL_REPORT_URL.getFile());

            JasperPrint print = JasperFillManager.fillReport(report, journalReportEntity.getParameters(),
                    new JREmptyDataSource());
            addReportPrintToPanel(print);
            reportPanel.setVisible(true);
        }catch (JRException ex){
            LOGGER.error("SHOW JOURNAL REPORT VIEW: Could not load report: "+ex.getMessage(),ex);
        }
    }

    public void showPersonalProtocolReport(PersonalProtocolUI personalProtocolUI, TotalProtocolUI totalProtocolUI){
        PersonalProtocolReportEntity protocolReportEntity = new PersonalProtocolReportEntity(personalProtocolUI,totalProtocolUI);
        try {
            JasperReport report = JasperCompileManager.compileReport(PERSONAL_PROTOCOL_REPORT_URL.getFile());
            JasperPrint print = JasperFillManager.fillReport(report, protocolReportEntity.getParameters(),
                    new JREmptyDataSource());
            addReportPrintToPanel(print);
            reportPanel.setVisible(true);
        }catch(JRException ex){
            LOGGER.error("SHOW PERSONAL PROTOCOL REPORT VIEW: Could not load report: "+ex.getMessage(),ex);
        }
    }

    public void showVisualTestProtocolReport(TotalProtocolUI totalProtocolUI){
        initPersonalProtocolReportEntityList(totalProtocolUI);
        TotalProtocolReportEntity totalProtocolReportEntity = new TotalProtocolReportEntity(totalProtocolUI);

        try {
            JasperReport report = JasperCompileManager.compileReport(VISUAL_TEST_PROTOCOL_REPORT_URL.getFile());
            JasperPrint print = JasperFillManager.fillReport(report, totalProtocolReportEntity.getParameters(),
                    new JRBeanCollectionDataSource(personalProtocolReportEntities));
            addReportPrintToPanel(print);
            reportPanel.setVisible(true);
        }catch(JRException ex){
            LOGGER.error("SHOW VISUAL TEST PROTOCOL REPORT VIEW: Could not load report: "+ex.getMessage(),ex);
        }
    }

    private void initPersonalProtocolReportEntityList(TotalProtocolUI totalProtocolUI){
        if (totalProtocolUI==null || totalProtocolUI.getJournal().getWelders()==null)
            return;
        personalProtocolReportEntities.clear();
        List<WelderUI> welders = totalProtocolUI.getJournal().getWelders();

        for (WelderUI welderUI: welders){
            PersonalProtocolUI pp = personalProtocolServiceUI.getPersonalProtocolUIFromDB(totalProtocolUI,welderUI.getSurnameNameSecname());
            PersonalProtocolReportEntity ppReport = new PersonalProtocolReportEntity(pp, totalProtocolUI);
            personalProtocolReportEntities.add(ppReport);
        }

    }


    private void initJournalSectionReportEntityList(JournalUI journalUI){
        if (journalUI==null)
            return;
        journalSectionReportEntities.clear();
        CurriculumUI curriculumUI = journalUI.getCurriculumUIobject();
        java.util.List<SectionUI> sections = curriculumUI.getSections();
        for (SectionUI sectionUI: sections){
            journalSectionReportEntities.add(new JournalSectionReportEntity(sectionUI, journalUI));
        }

    }

    private void initJournalVisitTableReportEntityList(JournalUI journalUI){
        journalVisitTableReportEntities.clear();
        for (WelderUI welderUI : journalUI.getWelders()){
            journalVisitTableReportEntities.add(new JournalVisitTableReportEntity(journalUI, welderUI));
        }
    }
    private void initJournalReportEntityList(JournalUI journalUI){
        journalReportEntities.clear();
        for (WelderUI welderUI: journalUI.getWelders()){
            journalReportEntities.add(new JournalReportEntity(journalUI,welderUI));
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
