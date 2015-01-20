package com.lvg.weldercenter.ui.entities;

import javafx.beans.property.SimpleLongProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 15.12.14.
 */
abstract public class GenericEntityUI {

    protected SimpleLongProperty id = new SimpleLongProperty();

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenericEntityUI that = (GenericEntityUI) o;

        if (this.getId()!=that.getId()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
