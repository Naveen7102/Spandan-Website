package com.spe.spandan.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "fixtures")
@XmlRootElement(name = "fixtures")
@JsonIgnoreProperties(value = {"HibernateLazyInitializer","handler"})
public class Fixtures {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "fixtures_seq")
    @SequenceGenerator(name = "fixtures_seq", sequenceName = "fixtures_seq", initialValue = 1, allocationSize=1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "sport_id")
    private  Integer sport_id;

    @Column(name = "team1_id")
    private  Integer team1_id;

    @Column(name = "team2_id")
    private Integer team2_id;

    @Column(name = "winner")
    private Integer winner;

    @Column(name = "result")
    private String result;

    @Column(name = "time")
    private String time;

    public Fixtures(Integer sport_id, Integer team1_id, Integer team2_id, Integer winner, String result, String time) {
        this.sport_id = sport_id;
        this.team1_id = team1_id;
        this.team2_id = team2_id;
        this.winner = winner;
        this.result = result;
        this.time = time;
    }

    public Fixtures() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSport_id() {
        return sport_id;
    }

    public void setSport_id(Integer sport_id) {
        this.sport_id = sport_id;
    }

    public Integer getTeam1_id() {
        return team1_id;
    }

    public void setTeam1_id(Integer team1_id) {
        this.team1_id = team1_id;
    }

    public Integer getTeam2_id() {
        return team2_id;
    }

    public void setTeam2_id(Integer team2_id) {
        this.team2_id = team2_id;
    }

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
