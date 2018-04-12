package com.lvg.weldercenter.se.ui.dto

import org.apache.log4j.Logger

abstract class ValidatorDTO implements DTOConstants{
    private static final Logger LOGGER = Logger.getLogger(ValidatorDTO.class)

    static boolean isValidOrganizationDTO(OrganizationDTO organizationDTO){
        if (organizationDTO.nameProperty().get() == null || organizationDTO.nameProperty().get().isEmpty()){
            LOGGER.warn("OrganizationDTO has invalid name: ${organizationDTO.nameProperty().get()}")
            return false
        }

        return true
    }

}
