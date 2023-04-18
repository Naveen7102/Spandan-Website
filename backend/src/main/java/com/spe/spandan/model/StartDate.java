package com.spe.spandan.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.sql.Date;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "start_date")
@XmlRootElement(name = "start_date")
@JsonIgnoreProperties(value = {"HibernateLazyInitializer","handler"})
public class StartDate {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "start_date_seq")
    @SequenceGenerator(name = "start_date_seq", sequenceName = "start_date_seq", initialValue = 1, allocationSize=1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private Date date;

    public StartDate(Date date) {
        this.date = date;
    }

    public StartDate() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
