package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.models.NDTDocument;
import com.lvg.weldercenter.ui.entities.NDTDocumentUI;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by Victor on 06.04.2015.
 */
public interface NDTDocumentServiceUI {
    public NDTDocument getNDTDocumentFromUIModel(NDTDocumentUI ndtDocumentUI);
    public List<NDTDocument> getNDTDocumentListFromObsList(ObservableList<NDTDocumentUI> obsList);
}
