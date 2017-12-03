package com.lvg.weldercenter.se.ui.controllers

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Component

import static com.lvg.weldercenter.se.ui.controllers.PaneType.*

@Component
class FXMLLoaderProvider {
    static
    final String MAIN_FRAME_FXML_PATH = '/fxml/se/main-frame-pane.fxml'

    static
    final String WELDERS_PANE_FXML_PATH = '/fxml/se/welder-pane.fxml'

    static
    final String JOURNAL_PANE_FXML_PATH = '/fxml/se/journal-pane.fxml'

    static
    final String PROTOCOL_PANE_FXML_PATH = '/fxml/se/protocol-pane.fxml'

    static
    final String REPORT_VIEW_PANE_FXML_PATH = '/fxml/se/report-view-pane.fxml'

    static
    final String PROPERTIES_PANE_FXML_PATH = '/fxml/se/properties-pane.fxml'

    private static final  fxmlLocations = [(MAIN_FRAME_PANE):MAIN_FRAME_FXML_PATH,
                                           (WELDER_PANE)    : WELDERS_PANE_FXML_PATH,
                                           (JOURNAL_PANE): JOURNAL_PANE_FXML_PATH,
                                           (PROTOCOL_PANE): PROTOCOL_PANE_FXML_PATH,
                                           (PROPERTIES_PANE): PROPERTIES_PANE_FXML_PATH,
                                           (REPORT_PANE):REPORT_VIEW_PANE_FXML_PATH]

    private final Map<PaneType, Parent> paneCash = [:]



    @Autowired
    private ConfigurableApplicationContext ctx

    FXMLLoader getFXMLLoader(String path) {
        FXMLLoader loader = new FXMLLoader()
        loader.setControllerFactory({ e -> ctx.getBean(e) })
        loader.setLocation(getClass().getResource(path))
        return loader
    }

    Parent loadParent(PaneType paneType, boolean usingCash){
        if (usingCash)
            return getParentPaneWithCash(paneType)
        return getParentPaneWithoutCash(paneType)
    }

    private Parent getParentPaneWithCash(PaneType paneType){
        if(paneCash.containsKey(paneType)){
            return paneCash.get(paneType)
        }else {
            Parent result = getFXMLLoader(fxmlLocations.get(paneType)).load()
            paneCash.put(paneType, result)
            return result
        }
    }

    private Parent getParentPaneWithoutCash(PaneType paneType){
        return getFXMLLoader(fxmlLocations.get(paneType)).load()
    }

}
