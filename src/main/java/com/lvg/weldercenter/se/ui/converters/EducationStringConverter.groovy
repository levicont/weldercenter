package com.lvg.weldercenter.se.ui.converters

import com.lvg.weldercenter.se.models.Education
import com.lvg.weldercenter.se.ui.dto.EducationDTO
import javafx.util.StringConverter
import org.springframework.stereotype.Component

@Component
class EducationStringConverter extends StringConverter<EducationDTO>{

    @Override
    String toString(EducationDTO educationDTO) {
        return educationDTO.type
    }

    @Override
    EducationDTO fromString(String string) {
        EducationDTO result = new EducationDTO(new Education())
        result.type = string
        return result
    }
}
