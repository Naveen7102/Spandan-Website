package com.spe.spandan.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "teams")
@XmlRootElement(name = "teams")
@JsonIgnoreProperties(value = {"HibernateLazyInitializer","handler"})
public class Teams {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "teams_seq")
    @SequenceGenerator(name = "teams_seq", sequenceName = "teams_seq", initialValue = 1, allocationSize=1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    public Teams(String name) {
        this.name = name;
    }

    public Teams() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
