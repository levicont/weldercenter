package com.lvg.weldercenter.se.ui.converters

import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import com.lvg.weldercenter.se.ui.repositories.OrganizationDTORepository
import javafx.util.StringConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import static com.lvg.weldercenter.se.ui.converters.ConvertersConstants.NULL_VALUE_PLACEHOLDER

@Component
class OrganizationDTOStringConverter extends StringConverter<OrganizationDTO>{

    @Autowired
    OrganizationDTORepository repository

    @Override
    String toString(OrganizationDTO object) {
        if (object == null) return NULL_VALUE_PLACEHOLDER
        return object.nameProperty().get()
    }

    @Override
    OrganizationDTO fromString(String string) {
        if (string == null || string.trim() == NULL_VALUE_PLACEHOLDER || string.trim().isEmpty())
            return OrganizationDTO.getDefaultOrganizationDTO()

        OrganizationDTO result = null
        repository.getAllDTO().forEach({orgDTO ->
            if (orgDTO.nameProperty().get().toLowerCase() == string.trim().toLowerCase())
                result = orgDTO
        })
        if (result == null){
            result = OrganizationDTO.getDefaultOrganizationDTO()
            result.nameProperty().set(string.trim())
        }
        return result
    }
}
