package gukjin.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Child {

    @Id @GeneratedValue private int id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parent getParent() {
        return parent;
    }

    public void addParent(Parent parent){
        parent.getChildList().add(this);
        setParent(parent);
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
