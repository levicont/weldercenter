package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.Journal;
import com.lvg.weldercenter.models.Topic;
import com.lvg.weldercenter.ui.util.DateUtil;
import javafx.beans.property.*;

import java.util.Date;

/**
 * Created by Victor Levchenko LVG Corp. on 16.12.14.
 */
public class TopicUI extends GenericEntityUI {

    private final SimpleDoubleProperty timeLong = new SimpleDoubleProperty();
    private final SimpleIntegerProperty order = new SimpleIntegerProperty();
    private final SimpleStringProperty title = new SimpleStringProperty();
    private final SimpleStringProperty decription = new SimpleStringProperty();
    private final SimpleObjectProperty<Date> date = new SimpleObjectProperty<Date>();
    private final SimpleStringProperty dateFormat = new SimpleStringProperty();
    private final SimpleObjectProperty<JournalUI> journal = new SimpleObjectProperty<JournalUI>();


    public TopicUI(){
        this.id.set(0);
        this.timeLong.set(0);
        this.order.set(0);
        this.title.set("");
        this.decription.set("");
        this.date.set(DateUtil.getDate(DateUtil.DEFAULT_DATE));
        this.dateFormat.set(DateUtil.format(date.get()));
        this.journal.set(new JournalUI());
    }

    public TopicUI(Topic topic){
        this.id.set(topic.getTopicId());
        this.timeLong.set(topic.getTimelong());
        this.order.set(topic.getOrderIndex());
        this.title.set(topic.getTitle());
        this.decription.set(topic.getDescription());
        this.date.set(DateUtil.getDate(DateUtil.DEFAULT_DATE));
        this.dateFormat.set(DateUtil.format(date.get()));
        this.journal.set(new JournalUI());
    }

    public TopicUI(Topic topic , Journal journal){
        this(topic);
        this.date.set(journal.getDateBegin());
        this.dateFormat.set(DateUtil.format(date.get()));
        if (journal != null)
            this.journal.set(new JournalUI(journal));
    }

    public double getTimeLong() {
        return timeLong.get();
    }

    public SimpleDoubleProperty timeLongProperty() {
        return timeLong;
    }

    public void setTimeLong(double timeLong) {
        this.timeLong.set(timeLong);
    }

    public int getOrder() {
        return order.get();
    }

    public SimpleIntegerProperty orderProperty() {
        return order;
    }

    public void setOrder(int order) {
        this.order.set(order);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDecription() {
        return decription.get();
    }

    public SimpleStringProperty decriptionProperty() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription.set(decription);
    }

    public Date getDate() {
        return date.get();
    }

    public SimpleObjectProperty<Date> dateProperty() {
        return date;
    }

    public void setDate(Date date) {
        this.date.set(date);
    }

    public String getDateFormat() {
        return dateFormat.get();
    }

    public SimpleStringProperty dateFormatProperty() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat.set(dateFormat);
    }

    public JournalUI getJournal() {
        return journal.get();
    }

    public SimpleObjectProperty<JournalUI> journalProperty() {
        return journal;
    }

    public void setJournal(JournalUI journal) {
        this.journal.set(journal);
    }
}