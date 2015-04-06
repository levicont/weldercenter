package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.NDTDocument;
import com.lvg.weldercenter.services.NDTDocumentService;
import com.lvg.weldercenter.ui.entities.NDTDocumentUI;
import com.lvg.weldercenter.ui.servicesui.NDTDocumentServiceUI;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 06.04.2015.
 */
public class NDTDocumentUIManager implements NDTDocumentServiceUI{
    @Autowired
    private NDTDocumentService ndtDocumentService;

    @Override
    public NDTDocument getNDTDocumentFromUIModel(NDTDocumentUI ndtDocumentUI) {
        if (ndtDocumentUI == null) {
            return null;
        }
        NDTDocument ndtd = ndtDocumentService.get(ndtDocumentUI.getId());
        if (ndtd != null){
            updateNDTDOcumentFromUIModel(ndtd, ndtDocumentUI);
        }else {
            ndtd = new NDTDocument();
            updateNDTDOcumentFromUIModel(ndtd, ndtDocumentUI);
        }
        return ndtd;
    }

    @Override
    public List<NDTDocument> getNDTDocumentListFromObsList(ObservableList<NDTDocumentUI> obsList) {
        if (obsList == null) {
            return null;
        }
        List<NDTDocument> docsList = new ArrayList<NDTDocument>();
        for(NDTDocumentUI ndtUI : obsList){
            if (getNDTDocumentFromUIModel(ndtUI) != null){
                docsList.add(getNDTDocumentFromUIModel(ndtUI));
            }
        }
        return docsList;
    }

    private void  updateNDTDOcumentFromUIModel(NDTDocument updNDTDocument, NDTDocumentUI modelUI){
        updNDTDocument.setName(modelUI.getName());
        updNDTDocument.setFullName(modelUI.getFullName());
    }
}
