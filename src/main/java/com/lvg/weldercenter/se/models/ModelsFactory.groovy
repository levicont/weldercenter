package com.lvg.weldercenter.se.models

import com.lvg.weldercenter.se.cfg.R

abstract class ModelsFactory implements R.ModelsConfig{

    static Organization getDefaultOrganization(){
        new Organization(name: DEFAULT_ORGANIZATION_NAME,
                address: DEFAULT_ORGANIZATION_ADDRESS,
                phone: DEFAULT_ORGANIZATION_PHONE)
    }
}
