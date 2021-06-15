package at.struct.openjpatest.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "OTHER_NAME")
    private String name;

    /**
     * This is a Boolean object which might be null
     */
    @Column(nullable = true)
    private Boolean active;

    /**
     * this is a native boolean datatype which obviously cannot be null.
     */
    @Column(nullable = false)
    private boolean specialCustomer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public boolean isSpecialCustomer() {
        return specialCustomer;
    }

    public void setSpecialCustomer(boolean specialCustomer) {
        this.specialCustomer = specialCustomer;
    }
}
