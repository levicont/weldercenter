package com.lvg.weldercenter.se.ui.controllers

import javafx.fxml.FXMLLoader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Component

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


    @Autowired
    private ConfigurableApplicationContext ctx

    FXMLLoader getFXMLLoader(String path) {
        FXMLLoader loader = new FXMLLoader()
        loader.setControllerFactory({ e -> ctx.getBean(e) })
        loader.setLocation(getClass().getResource(path))
        return loader
    }
}
