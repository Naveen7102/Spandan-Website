package com.spe.spandan.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.sql.Blob;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "rules")
@XmlRootElement(name = "rules")
@JsonIgnoreProperties(value = {"HibernateLazyInitializer","handler"})
public class Rules {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "rules_seq")
    @SequenceGenerator(name = "rules_seq", sequenceName = "rules_seq", initialValue = 1, allocationSize=1)
    @Column(name = "id")
    private Integer id;

    @Lob
    @Column(name = "rule")
    private byte[] rule;


    @Column(name = "sport_id")
    private Integer sport_id;

    public Rules(byte[] rule, Integer sport_id) {
        this.rule = rule;
        this.sport_id = sport_id;
    }

    public Rules() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getRule() {
        return rule;
    }

    public void setRule(byte[] rule) {
        this.rule = rule;
    }

    public Integer getSport_id() {
        return sport_id;
    }

    public void setSport_id(Integer sport_id) {
        this.sport_id = sport_id;
    }
}
