package com.spe.spandan.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "team_members")
@XmlRootElement(name = "team_members")
@JsonIgnoreProperties(value = {"HibernateLazyInitializer","handler"})
public class TeamMembers {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "team_members_seq")
    @SequenceGenerator(name = "team_members_seq", sequenceName = "team_members_seq", initialValue = 1, allocationSize=1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "team_id")
    private Integer team_id;

    @Column(name = "participant_id")
    private Integer participant_id;

    @Column(name = "sport_id")
    private  Integer sport_id;

    public TeamMembers(Integer team_id, Integer participant_id, Integer sport_id) {
        this.team_id = team_id;
        this.participant_id = participant_id;
        this.sport_id = sport_id;
    }

    public TeamMembers() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }

    public Integer getParticipant_id() {
        return participant_id;
    }

    public void setParticipant_id(Integer participant_id) {
        this.participant_id = participant_id;
    }

    public Integer getSport_id() {
        return sport_id;
    }

    public void setSport_id(Integer sport_id) {
        this.sport_id = sport_id;
    }
}
