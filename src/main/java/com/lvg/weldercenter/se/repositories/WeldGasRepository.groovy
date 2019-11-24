package com.lvg.weldercenter.se.repositories

import com.lvg.weldercenter.se.models.WeldGas
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by Victor Levchenko LVG Corp. on 18.03.19.
 */
interface WeldGasRepository extends JpaRepository<WeldGas, Long>{
    void delete(WeldGas weldGas)
}