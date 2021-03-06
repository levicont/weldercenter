package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.Curriculum;
import com.lvg.weldercenter.models.Section;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 25.01.15.
 */
public class CurriculumUI extends GenericEntityUI {
    private final SimpleStringProperty title = new SimpleStringProperty();
    private final SimpleStringProperty description = new SimpleStringProperty();

    private final SimpleListProperty<SectionUI> sections = new SimpleListProperty<SectionUI>();

    public CurriculumUI() {
        this.id.set(0);
        this.title.set("");
        this.description.set("");
        this.sections.set(FXCollections.observableArrayList(new ArrayList<SectionUI>()));
    }

    public CurriculumUI(Curriculum curriculum) {
        this.id.set(curriculum.getCurriculumId());
        this.title.set(curriculum.getTitle());
        this.description.set(curriculum.getDescription());
        this.sections.set(FXCollections.observableArrayList(getSectionUIList(curriculum.getSections())));
    }

    private List<SectionUI> getSectionUIList(List<Section> dbSectionList){
        List<SectionUI> result = new ArrayList<SectionUI>();
        if (dbSectionList==null)
            return result;
        for(Section s : dbSectionList){
            SectionUI sectionUI = new SectionUI(s);
            result.add(sectionUI);
        }
        return result;
    }

    //Getters and Setters

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public ObservableList<SectionUI> getSections() {
        return sections.get();
    }

    public SimpleListProperty<SectionUI> sectionsProperty() {
        return sections;
    }

    public void setSections(ObservableList<SectionUI> sections) {
        this.sections.set(sections);
    }

    @Override
    public String toString() {
        return getTitle();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CurriculumUI that = (CurriculumUI) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
