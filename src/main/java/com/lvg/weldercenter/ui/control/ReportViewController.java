package com.lvg.weldercenter.ui.control;

import com.lvg.weldercenter.spring.factories.ServiceUIFactory;
import com.lvg.weldercenter.ui.entities.*;
import com.lvg.weldercenter.ui.entities.report.*;
import com.lvg.weldercenter.ui.servicesui.PersonalProtocolServiceUI;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
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
    private final URL TOTAL_PROTOCOL_REPORT_URL = ClassLoader.getSystemClassLoader().getResource("reports/total-protocol-rep.jrxml");
    private final URL THEORY_PROTOCOL_REPORT_URL = ClassLoader.getSystemClassLoader().getResource("reports/total-protocol-theory-rep.jrxml");
    private final URL JOURNAL_REPORT_URL = ClassLoader.getSystemClassLoader().getResource("reports/journal-rep.jrxml");
    private final URL JOURNAL_SUBREPORT_WELDERS_DETAIL_URL =
            ClassLoader.getSystemClassLoader().getResource("reports/journal-about-students-subrep.jrxml");
    private final URL JOURNAL_SUBREPORT_VISIT_TABLE_URL =
            ClassLoader.getSystemClassLoader().getResource("reports/journal-visit-table-subrep.jrxml");
    private final URL JOURNAL_SUBREPORT_TIME_TABLE_URL =
            ClassLoader.getSystemClassLoader().getResource("reports/journal-time-table-subrep.jrxml");
    private final URL PERSONAL_PROTOCOL_REPORT_URL =
            ClassLoader.getSystemClassLoader().getResource("reports/pers-protocol-rep.jrxml");
    private final URL VISUAL_TEST_PROTOCOL_REPORT_URL =
            ClassLoader.getSystemClassLoader().getResource("reports/visual-test-rep.jrxml");
    private final URL VISUAL_TEST_PROTOCOL_SUBREPORT_URL =
            ClassLoader.getSystemClassLoader().getResource("reports/visual-test-subrep.jrxml");
    private final URL RADIATION_TEST_PROTOCOL_REPORT_URL =
            ClassLoader.getSystemClassLoader().getResource("reports/radiation-test-rep.jrxml");
    private final URL MECHANICAL_TEST_PROTOCOL_REPORT_URL =
            ClassLoader.getSystemClassLoader().getResource("reports/mech-test-rep.jrxml");
    private final URL UNIVERS_FONT_URL =
            ClassLoader.getSystemClassLoader().getResource("fonts/Univers_Medium.ttf");


    private PersonalProtocolServiceUI personalProtocolServiceUI = ServiceUIFactory.getPersonalProtocolServiceUI();

    @FXML
    BorderPane mainReportViewPane;
    @FXML
    SwingNode swingNode;
    @FXML
    Button btClose;
    @FXML
    ProgressBar prgsBarLoader;

    private JPanel reportPanel;

    private JournalUI selectedJournal = null;
    private TotalProtocolUI selectedTotalProtocol = null;
    private PersonalProtocolUI selectedPersonalProtocol = null;


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
        this.selectedTotalProtocol = protocolUI;
        reportPanel.setVisible(false);
        prgsBarLoader.setVisible(true);
        prgsBarLoader.progressProperty().unbind();
        Task<Void> loader = createTotalProtocolReportLoader();
        prgsBarLoader.progressProperty().bind(loader.progressProperty());
        Thread t = new Thread(loader);
        t.setName("TotalProtocolReport Loader Thread");
        t.start();
    }

    private Task<Void> createTotalProtocolReportLoader(){
        return new Task<Void>() {
            JasperPrint print = null;
            @Override
            protected Void call() throws Exception {
                TotalProtocolReportEntity totalProtocolReportEntity =
                        new TotalProtocolReportEntity(selectedTotalProtocol);
                updateProgress(25, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                try {
                    JasperReport report = JasperCompileManager.compileReport(TOTAL_PROTOCOL_REPORT_URL.openStream());
                    updateProgress(65, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                    print = JasperFillManager.fillReport(report,
                            totalProtocolReportEntity.getParameters(),new JREmptyDataSource());
                    updateProgress(90, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                }catch (JRException ex){
                    LOGGER.error("SHOW TOTAL PROTOCOL REPORT VIEW: Could not load report: "+ex.getMessage(),ex);
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        addReportPrintToPanel(print);
                        showReadyReportPanel();
                    }
                });

                return null;
            }
        };

    }

    public void showTheoryProtocolReport(TotalProtocolUI protocolUI){
        this.selectedTotalProtocol = protocolUI;
        reportPanel.setVisible(false);
        prgsBarLoader.setVisible(true);
        prgsBarLoader.progressProperty().unbind();
        Task<Void> loader = createTheoryTestReportLoader();
        prgsBarLoader.progressProperty().bind(loader.progressProperty());
        Thread t = new Thread(loader);
        t.setName("TheoryTestReport Loader Thread");
        t.start();
    }

    private Task<Void> createTheoryTestReportLoader(){
        return new Task<Void>() {
            JasperPrint print = null;
            @Override
            protected Void call() throws Exception {
                initTheoryReportEntityList(selectedTotalProtocol);
                updateProgress(20,constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                TotalProtocolReportEntity totalProtocolReportEntity =
                        new TotalProtocolReportEntity(selectedTotalProtocol);
                updateProgress(25,constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                totalProtocolReportEntity.getParameters().put("UNIVERS_FONT_PATH",UNIVERS_FONT_URL.getFile());
                updateProgress(35,constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                try {
                    JasperReport report = JasperCompileManager.compileReport(THEORY_PROTOCOL_REPORT_URL.openStream());
                    updateProgress(65,constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                    print = JasperFillManager.fillReport(report, totalProtocolReportEntity.getParameters(),
                            new JRBeanCollectionDataSource(theoryReportEntityList));
                    updateProgress(90,constants.GENERIC_MAX_TASK_PROGRESS_VALUE);

                }catch (JRException ex){
                    LOGGER.error("SHOW THEORY REPORT VIEW: Could not load report: "+ex.getMessage(),ex);
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                       addReportPrintToPanel(print);
                       showReadyReportPanel();
                    }
                });

                return null;
            }
        };
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
        this.selectedJournal = journalUI;
        reportPanel.setVisible(false);
        prgsBarLoader.setVisible(true);
        prgsBarLoader.progressProperty().unbind();
        Task<Void> loader = createJournalReportLoader();
        prgsBarLoader.progressProperty().bind(loader.progressProperty());
        Thread t = new Thread(loader);
        t.setName("JournalReport Loader Thread");
        t.start();
    }

    private Task<Void> createJournalReportLoader() {
        return new Task<Void>() {
            JasperPrint print = null;
            @Override
            protected Void call() throws Exception {
                initJournalReportEntityList(selectedJournal);
                updateProgress(10, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                initJournalVisitTableReportEntityList(selectedJournal);
                updateProgress(20, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                initJournalSectionReportEntityList(selectedJournal);
                updateProgress(30, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                JournalReportEntity journalReportEntity = journalReportEntities.iterator().next();
                try {

                    JasperReport subReport =
                            JasperCompileManager.compileReport(JOURNAL_SUBREPORT_WELDERS_DETAIL_URL.openStream());
                    journalReportEntity.getParameters().put("WELDER_DETAIL_SUBREPORT", subReport);
                    journalReportEntity.getParameters().put("WELDER_DETAIL_DATA_SOURCE", new JRBeanCollectionDataSource(journalReportEntities));

                    updateProgress(45, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);

                    JasperReport subReportVisitTable =
                            JasperCompileManager.compileReport(JOURNAL_SUBREPORT_VISIT_TABLE_URL.openStream());
                    journalReportEntity.getParameters().put("VISIT_TABLE_SUBREPORT", subReportVisitTable);
                    journalReportEntity.getParameters().put("VISIT_TABLE_DATA_SOURCE",
                            new JRBeanCollectionDataSource(journalVisitTableReportEntities));

                    updateProgress(55, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);

                    JasperReport subReportTimeTable =
                            JasperCompileManager.compileReport(JOURNAL_SUBREPORT_TIME_TABLE_URL.openStream());

                    journalReportEntity.getParameters().put("TIME_TABLE_SUBREPORT", subReportTimeTable);
                    journalReportEntity.getParameters().put("TIME_TABLE_DATA_SOURCE",
                            new JRBeanCollectionDataSource(journalSectionReportEntities));
                    journalReportEntity.getParameters().put("PARAMETERS_MAP", journalReportEntity.getParameters());

                    updateProgress(70, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);

                    JasperReport report = JasperCompileManager.compileReport(JOURNAL_REPORT_URL.openStream());

                    print = JasperFillManager.fillReport(report, journalReportEntity.getParameters(),
                            new JREmptyDataSource());
                    updateProgress(90, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            addReportPrintToPanel(print);
                            showReadyReportPanel();
                        }
                    });



                }catch (JRException ex){
                    LOGGER.error("SHOW JOURNAL REPORT VIEW: Could not load report: "+ex.getMessage(),ex);
                }

                return null;
            }
        };
    }

    private void showReadyReportPanel(){
        reportPanel.revalidate();
        reportPanel.setVisible(true);
        prgsBarLoader.setVisible(false);
    }

    public void showPersonalProtocolReport(PersonalProtocolUI personalProtocolUI, TotalProtocolUI totalProtocolUI){
        this.selectedTotalProtocol = totalProtocolUI;
        this.selectedPersonalProtocol = personalProtocolUI;

        reportPanel.setVisible(false);
        prgsBarLoader.setVisible(true);
        prgsBarLoader.progressProperty().unbind();
        Task<Void> loader = createPersonalProtocolReportLoader();
        prgsBarLoader.progressProperty().bind(loader.progressProperty());
        Thread t = new Thread(loader);
        t.setName("PersonalProtocolReport Loader Thread");
        t.start();
    }

    private Task<Void> createPersonalProtocolReportLoader(){
        return new Task<Void>() {
            JasperPrint print = null;
            @Override
            protected Void call() throws Exception {
                PersonalProtocolReportEntity protocolReportEntity =
                        new PersonalProtocolReportEntity(selectedPersonalProtocol,selectedTotalProtocol);
                updateProgress(25, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                try {
                    JasperReport report = JasperCompileManager.compileReport(PERSONAL_PROTOCOL_REPORT_URL.openStream());
                    updateProgress(65, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                    print = JasperFillManager.fillReport(report, protocolReportEntity.getParameters(),
                            new JREmptyDataSource());
                    updateProgress(90, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                }catch(JRException ex){
                    LOGGER.error("SHOW PERSONAL PROTOCOL REPORT VIEW: Could not load report: "+ex.getMessage(),ex);
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        addReportPrintToPanel(print);
                        showReadyReportPanel();
                    }
                });
                return null;
            }
        };
    }

    public void showVisualTestProtocolReport(TotalProtocolUI totalProtocolUI){
        this.selectedTotalProtocol = totalProtocolUI;

        reportPanel.setVisible(false);
        prgsBarLoader.setVisible(true);
        prgsBarLoader.progressProperty().unbind();
        Task<Void> loader = createVisualTestReportLoader();
        prgsBarLoader.progressProperty().bind(loader.progressProperty());
        Thread t = new Thread(loader);
        t.setName("VisualTestReport Loader Thread");
        t.start();
    }

    private Task<Void> createVisualTestReportLoader(){
        return  new Task<Void>() {
            JasperPrint print = null;
            @Override
            protected Void call() throws Exception {
                initPersonalProtocolReportEntityList(selectedTotalProtocol);
                updateProgress(25, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                TotalProtocolReportEntity totalProtocolReportEntity =
                        new TotalProtocolReportEntity(selectedTotalProtocol);
                updateProgress(35, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                try {
                    JasperReport subreport = JasperCompileManager.compileReport(VISUAL_TEST_PROTOCOL_SUBREPORT_URL.openStream());
                    totalProtocolReportEntity.getParameters().put("VT_SUBREPORT",subreport);
                    totalProtocolReportEntity.getParameters().put("VT_SUBREPORT_DATASET",
                            new JRBeanCollectionDataSource(personalProtocolReportEntities));
                    JasperReport report = JasperCompileManager.compileReport(VISUAL_TEST_PROTOCOL_REPORT_URL.openStream());
                    updateProgress(65, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                    print = JasperFillManager.fillReport(report, totalProtocolReportEntity.getParameters(),
                            new JREmptyDataSource());
                    updateProgress(90, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);

                }catch(JRException ex){
                    LOGGER.error("SHOW VISUAL TEST PROTOCOL REPORT VIEW: Could not load report: "+ex.getMessage(),ex);
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        addReportPrintToPanel(print);
                        showReadyReportPanel();
                    }
                });
                return null;
            }
        };
    }


    public void showRadiationTestProtocolReport(TotalProtocolUI totalProtocolUI){
        this.selectedTotalProtocol = totalProtocolUI;

        reportPanel.setVisible(false);
        prgsBarLoader.setVisible(true);
        prgsBarLoader.progressProperty().unbind();
        Task<Void> loader = createRadiationTestReportLoader();
        prgsBarLoader.progressProperty().bind(loader.progressProperty());
        Thread t = new Thread(loader);
        t.setName("RadiationTestReport Loader Thread");
        t.start();
    }

    private Task<Void> createRadiationTestReportLoader(){
        return new Task<Void>() {
            JasperPrint print =null;
            @Override
            protected Void call() throws Exception {

                initPersonalProtocolReportEntityList(selectedTotalProtocol);
                updateProgress(25, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                TotalProtocolReportEntity totalProtocolReportEntity =
                        new TotalProtocolReportEntity(selectedTotalProtocol);
                updateProgress(35, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                try {
                    JasperReport report =
                            JasperCompileManager.compileReport(RADIATION_TEST_PROTOCOL_REPORT_URL.openStream());
                    updateProgress(65, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                    print = JasperFillManager.fillReport(report, totalProtocolReportEntity.getParameters(),
                            new JRBeanCollectionDataSource(personalProtocolReportEntities));
                    updateProgress(90, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                }catch(JRException ex){
                    LOGGER.error("SHOW RADIATION TEST PROTOCOL REPORT VIEW: Could not load report: "+ex.getMessage(),ex);
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        addReportPrintToPanel(print);
                        showReadyReportPanel();
                    }
                });
                return null;
            }
        };
    }

    public void showMechanicalTestProtocolReport(TotalProtocolUI totalProtocolUI){
        this.selectedTotalProtocol = totalProtocolUI;

        reportPanel.setVisible(false);
        prgsBarLoader.setVisible(true);
        prgsBarLoader.progressProperty().unbind();
        Task<Void> loader = createMechanicalTestReportLoader();
        prgsBarLoader.progressProperty().bind(loader.progressProperty());
        Thread t = new Thread(loader);
        t.setName("MechanicalTestReport Loader Thread");
        t.start();
    }

    private Task<Void> createMechanicalTestReportLoader(){
        return new Task<Void>() {
            JasperPrint print = null;
            @Override
            protected Void call() throws Exception {
                initPersonalProtocolReportEntityList(selectedTotalProtocol);
                updateProgress(25,constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                initPersonalProtocolReportEntityListForMT(
                        (List<PersonalProtocolReportEntity>) personalProtocolReportEntities);
                updateProgress(35, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                TotalProtocolReportEntity totalProtocolReportEntity =
                        new TotalProtocolReportEntity(selectedTotalProtocol);
                updateProgress(45, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                try {
                    JasperReport report =
                            JasperCompileManager.compileReport(MECHANICAL_TEST_PROTOCOL_REPORT_URL.openStream());
                    updateProgress(65, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);
                    print = JasperFillManager.fillReport(report, totalProtocolReportEntity.getParameters(),
                            new JRBeanCollectionDataSource(personalProtocolReportEntities));
                    updateProgress(90, constants.GENERIC_MAX_TASK_PROGRESS_VALUE);

                }catch(JRException ex){
                    LOGGER.error("SHOW MECHANICAL TEST PROTOCOL REPORT VIEW: Could not load report: "+ex.getMessage(),ex);
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        addReportPrintToPanel(print);
                        showReadyReportPanel();
                    }
                });
                return null;
            }
        };
    }

    private void initPersonalProtocolReportEntityListForMT(List<PersonalProtocolReportEntity> ppList){
        if (ppList == null || ppList.isEmpty())
            return;
        List<PersonalProtocolReportEntity> result = new ArrayList<PersonalProtocolReportEntity>();
        for (PersonalProtocolReportEntity ppReportEntity : ppList){
            ppReportEntity.deleteWeldPatternsWithoutMechTest();
            if(!ppReportEntity.getPatterns().isEmpty()) {
                for (WeldPatternReportEntity wp : ppReportEntity.getPatterns())
                    convertWeldPatternDiameterToWidth(wp);
                result.add(ppReportEntity);
            }
        }
        ppList.clear();
        ppList.addAll(result);
    }

    private void convertWeldPatternDiameterToWidth(WeldPatternReportEntity weldPattern){
        if (weldPattern==null)
            return;
        if (weldPattern.getThickness()== null || weldPattern.getDiameter() == null)
            return;
        Double diameterD;
        try {
            diameterD = Double.parseDouble(weldPattern.getDiameter());
        }catch (NumberFormatException ex){
            LOGGER.error("CONVERT WELD PATTERN DIAMETER TO WIDTH: not correct diameter "+weldPattern.getDiameter(), ex);
            return;
        }
        if (diameterD==0){
            weldPattern.setDiameter(weldPattern.getMtWidth());
        }else {
            weldPattern.setDiameter(formatDiameter(weldPattern.getDiameter()));
            weldPattern.setDiameter(weldPattern.deleteFloatZeroSuffix(weldPattern.getDiameter()));
        }
    }

    private String formatDiameter(String weldPatternDiameter){
        return  "d"+weldPatternDiameter;
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
