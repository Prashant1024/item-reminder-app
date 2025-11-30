package io.spring.items.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity /*Whenever we create instances(object) of this class, JPA converts the data into a table in the
relational database*/
@Table(name = "testObjects")
public class Objects {
    @Id
    private String id;
    private String objectName;

    private String hidePlace;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Objects() {

    }

    public Objects(String id) {
        this.id = id;
    }

    public Objects(String objectName, String hidePlace, String id) {
        this.objectName = objectName;
        this.hidePlace = hidePlace;
        this.id = id;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getHidePlace() {
        return hidePlace;
    }

    public void setHidePlace(String hidePlace) {
        this.hidePlace = hidePlace;
    }

}
