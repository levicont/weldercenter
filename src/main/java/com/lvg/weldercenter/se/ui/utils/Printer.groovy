package com.lvg.weldercenter.se.ui.utils


import com.lvg.weldercenter.se.ui.dto.ModelDTO
import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import org.apache.log4j.Logger


class Printer {
    private static final Logger LOGGER = Logger.getLogger(Printer.class)

    static void logDTO(Class<?> modelClass, ModelDTO modelDTO){
        String classString = "Class:\t ${modelClass.simpleName}\n"

        if (modelClass == WelderDTO.class) LOGGER.debug("${classString} ${printWelderDTO((WelderDTO)modelDTO)}")
        if (modelClass == WelderTableViewDTO.class) LOGGER.debug("${classString} ${printWelderTableViewDTO((WelderTableViewDTO)modelDTO)}")
        if (modelClass == OrganizationDTO.class) LOGGER.debug("${classString} ${printOrganizationDTO((OrganizationDTO)modelDTO)}")
    }

    private static String printWelderDTO(WelderDTO welderDTO){
        if (welderDTO == null)
            return "${welderDTO}"
        return "WelderDTO:\n " +
                "\tid:             ${welderDTO.id}\n" +
                "\tname:           ${welderDTO.name}\n" +
                "\tsecondName:     ${welderDTO.secondName}\n" +
                "\tsurname:        ${welderDTO.surname}\n" +
                "\tbirthday:       ${welderDTO.birthdayFormat}\n" +
                "\taddress:        ${welderDTO.address}\n" +
                "\tdateBegin:      ${welderDTO.dateBeginFormat}\n" +
                "\tdocumentNumber: ${welderDTO.documentNumber}\n" +
                "\teducation:      ${welderDTO.education}\n" +
                "\tqualification:  ${welderDTO.qualification}\n" +
                "\tjob:            ${welderDTO.job}\n" +
                "organization:     ${printOrganizationDTO(welderDTO.organizationDTO)}\n"
    }

    private static String printWelderTableViewDTO(WelderTableViewDTO welderTableViewDTO){
        if (welderTableViewDTO == null)
            return "${welderTableViewDTO}"
        return "WelderTableViewDTO:\n " +
                "\tid:               ${welderTableViewDTO.id}\n" +
                "\tname:             ${welderTableViewDTO.name}\n" +
                "\tsecondName:       ${welderTableViewDTO.secondName}\n" +
                "\tsurname:          ${welderTableViewDTO.surname}\n" +
                "\tbirthday:         ${welderTableViewDTO.birthday}\n" +
                "\torganizationName: ${welderTableViewDTO.organization}\n"
    }

    private static String printOrganizationDTO(OrganizationDTO organizationDTO){
        if (organizationDTO == null)
            return "${organizationDTO}"
        return "OrganizationDTO:\n " +
                "\tid:             ${organizationDTO.id}\n" +
                "\tname:           ${organizationDTO.name}\n" +
                "\tsecondName:     ${organizationDTO.address}\n" +
                "\tsurname:        ${organizationDTO.phone}\n"

    }
}
