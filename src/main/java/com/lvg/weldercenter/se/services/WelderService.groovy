package com.lvg.weldercenter.se.services

import com.lvg.weldercenter.se.models.Welder

interface WelderService extends GenericService<Welder>{
    long count()
}