package com.lvg.weldercenter.se.models
import com.lvg.weldercenter.se.converters.LocalDateConverter

import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import java.time.LocalDate

@Entity
class Welder implements Serializable{

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    protected Long id

    @NotNull
    @Size(min = 2, max = 50)
    protected String name

    @NotNull
    @Size(min = 2, max = 50)
    protected String surname

    @NotNull
    @Size(min = 2, max = 50)
    protected String secname

    @Convert(converter = LocalDateConverter.class)
    protected LocalDate birthday

    @Convert(converter = LocalDateConverter.class)
    protected LocalDate dateBegin

    @Size(max = 10)
    protected String docNumber



    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Welder welder = (Welder) o

        if (id != welder.id) return false

        return true
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }
}
