package com.lvg.weldercenter.se.ui.converters

import com.lvg.weldercenter.se.models.OrganizationEmbedded
import com.lvg.weldercenter.se.ui.dto.DTOConstants
import com.lvg.weldercenter.se.ui.repositories.OrganizationDTORepository
import javafx.util.StringConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import static com.lvg.weldercenter.se.ui.converters.ConvertersConstants.NULL_VALUE_PLACEHOLDER

@Component
class OrganizationEmbeddedStringConverter extends StringConverter<OrganizationEmbedded>{

    @Autowired
    OrganizationDTORepository repository

    @Override
    String toString(OrganizationEmbedded object) {
        if (object == null) return NULL_VALUE_PLACEHOLDER
        return object.name
    }

    @Override
    OrganizationEmbedded fromString(String string) {
        if (string == null || string.trim() == NULL_VALUE_PLACEHOLDER || string.trim().isEmpty())
            return DTOConstants.DEFAULT_ORGANIZATION_PLACEHOLDER

        OrganizationEmbedded result
        repository.getAllOrganizationDTOFromDB().forEach({orgDTO ->
            if (orgDTO.nameProperty().get().toLowerCase() == string.trim().toLowerCase())
                result = new OrganizationEmbedded(name:orgDTO.name, phone: orgDTO.phone, address: orgDTO.address)
        })
        if (result == null){
            result = DTOConstants.DEFAULT_ORGANIZATION_PLACEHOLDER
            result.name = string.trim()
        }
        return result
    }
}
