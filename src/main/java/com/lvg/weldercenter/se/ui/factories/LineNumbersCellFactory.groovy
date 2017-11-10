package com.lvg.weldercenter.se.ui.factories

import javafx.scene.control.TableCell
import javafx.scene.control.TableColumn
import javafx.util.Callback

class LineNumbersCellFactory<T, E> implements Callback<TableColumn<T, E>, TableCell<T, E>> {

    @Override
    TableCell<T, E> call(TableColumn<T, E> param) {
        return new TableCell<T, E>() {
            @Override
            protected void updateItem(E item, boolean empty) {
                super.updateItem(item, empty)

                if (!empty) {
                    setText(this.getTableRow().getIndex() + 1 + "")
                } else {
                    setText("")
                }
            }
        }
    }
}
