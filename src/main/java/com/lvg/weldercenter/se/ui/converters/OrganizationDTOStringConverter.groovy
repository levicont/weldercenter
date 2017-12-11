package com.lvg.weldercenter.se.ui.converters

import com.lvg.weldercenter.se.models.Organization
import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import com.lvg.weldercenter.se.ui.repositories.OrganizationDTORepository
import javafx.util.StringConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import static com.lvg.weldercenter.se.ui.converters.ConvertersConstants.*

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
        if (string == null || string == NULL_VALUE_PLACEHOLDER) return null
        OrganizationDTO result = null
        repository.allDTO.get().forEach({orgDTO ->
            if (orgDTO.name == string)
                result = orgDTO
        })
        if (result == null){
            Organization organization = new Organization(name: string)
            result = new OrganizationDTO(organization)
        }
        return result
    }
}
