package com.lvg.weldercenter.se.test.services

import com.lvg.weldercenter.se.models.NDTDocument
import com.lvg.weldercenter.se.services.NDTDocumentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

@Transactional
class NDTDocumentServiceTest extends GenericServiceTest{

    @Autowired
    NDTDocumentService ndtDocumentService

    @Override
    void saveTest() {
        def NDT_DOCUMENT_ID
        def ndtDocument = getNDTDocument()
        ndtDocument = ndtDocumentService.save(ndtDocument)
        NDT_DOCUMENT_ID = ndtDocument.id
        assert NDT_DOCUMENT_ID != null

        NDTDocument updNDTDocument = ndtDocumentService.get(NDT_DOCUMENT_ID)
        assert updNDTDocument != null
        assert updNDTDocument instanceof NDTDocument
        updNDTDocument.code = 'НПАОП'
        ndtDocumentService.save(updNDTDocument)

        NDTDocument chkNDTDocument = ndtDocumentService.get(NDT_DOCUMENT_ID)
        assert chkNDTDocument != null
        assert chkNDTDocument instanceof NDTDocument
        assert chkNDTDocument.code == 'НПАОП'
    }

    @Override
    void deleteTest() {
        def NDT_DOCUMENT_ID
        def ndtDocument = getNDTDocument()
        ndtDocument = ndtDocumentService.save(ndtDocument)
        NDT_DOCUMENT_ID = ndtDocument.id
        assert NDT_DOCUMENT_ID != null

        NDTDocument delNDTDocument = ndtDocumentService.get(NDT_DOCUMENT_ID)
        assert delNDTDocument != null
        assert delNDTDocument instanceof NDTDocument
        ndtDocumentService.delete(delNDTDocument)

        NDTDocument chkNDTDocument = ndtDocumentService.get(NDT_DOCUMENT_ID)
        assert chkNDTDocument == null
    }

    @Override
    void getTest() {
        def NDT_DOCUMENT_ID
        def ndtDocument = getNDTDocument()
        ndtDocument = ndtDocumentService.save(ndtDocument)
        NDT_DOCUMENT_ID = ndtDocument.id
        assert NDT_DOCUMENT_ID != null

        NDTDocument chkNDTDocument = ndtDocumentService.get(NDT_DOCUMENT_ID)
        assert chkNDTDocument != null
        assert chkNDTDocument instanceof NDTDocument
    }

    @Override
    void getAllTest() {
        def NDT_DOCUMENT_ID
        def ndtDocument = getNDTDocument()
        ndtDocument = ndtDocumentService.save(ndtDocument)
        NDT_DOCUMENT_ID = ndtDocument.id
        assert NDT_DOCUMENT_ID != null

        def list = ndtDocumentService.getAll()
        assert list != null
        assert list instanceof List
        assert list.size() == 1
    }
}
