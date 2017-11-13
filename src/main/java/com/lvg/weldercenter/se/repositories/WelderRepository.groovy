package com.lvg.weldercenter.se.repositories

import com.lvg.weldercenter.se.models.Welder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface WelderRepository extends JpaRepository<Welder, Long>{
    void delete(Welder welder)

    @Query(value = '''Select w.id, w.name, w.surname, w.secondName, w.birthday, w.organization.name
                        from Welder w''')
    List<Object[]> getWeldersForTableView()

    @Query(value = '''Select w from Welder w left join fetch w.organization where w.id = :id''')
    Welder getFull(@Param('id')Long id)
}
