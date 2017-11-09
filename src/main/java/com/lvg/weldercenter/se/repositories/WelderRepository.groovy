package com.lvg.weldercenter.se.repositories

import com.lvg.weldercenter.se.models.Welder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface WelderRepository extends JpaRepository<Welder, Long>{
    void delete(Welder welder)

    @Query(value = '''Select w.id, w.name, w.surname, w.secondName, w.birthday, w.organization.name
                        from Welder w''')
    List<Object[]> getWeldersForTableView()
}
