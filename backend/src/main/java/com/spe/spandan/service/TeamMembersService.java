package com.spe.spandan.service;

import com.spe.spandan.model.Message;
import com.spe.spandan.model.TeamMembers;
import com.spe.spandan.model.User;
import com.spe.spandan.repository.TeamMembersRepository;
import com.spe.spandan.repository.TeamsRepository;
import com.spe.spandan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;


@Service
public class TeamMembersService {

    @Autowired
    TeamMembersRepository teamMembersRepository;

    @Autowired
    TeamsRepository teamsRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Message> addMember(Map<String, String> requestMap) {

        try{
            if(validateAddMember(requestMap)){
                Integer team_id = teamsRepository.getTeamId(requestMap.get("team"),Integer.parseInt(requestMap.get("sport_id")));
                Integer participant_id = Integer.parseInt(requestMap.get("participant_id"));
                Integer sport_id = Integer.parseInt(requestMap.get("sport_id"));
                TeamMembers tm = teamMembersRepository.getId(sport_id,participant_id);
//                System.out.println(tm.getId());
                if(tm == null){
                    teamMembersRepository.save(createTeamMemberFromMap(requestMap));
                    Message success = new Message("Fixture added Successfully.");
                    return new ResponseEntity<Message>(success, HttpStatus.OK);
                }
                else if(tm.getSport_id() == sport_id && tm.getParticipant_id() == participant_id) {
                    if(tm.getTeam_id() == team_id){
                        Message addFailed2 = new Message("Member already in same team");
                        return new ResponseEntity<Message>(addFailed2, HttpStatus.BAD_REQUEST);
                    }
                    else{
                        Message addFailed2 = new Message("Member already in another team");
                        return new ResponseEntity<Message>(addFailed2, HttpStatus.BAD_REQUEST);
                    }
                }
            }
            else {
                Message addFailed = new Message("Invalid Data");
                return new ResponseEntity<Message>(addFailed, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Message failed = new Message("Something Went Wrong at Fixture Service.");
        return new ResponseEntity<Message>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private TeamMembers createTeamMemberFromMap(Map<String, String> requestMap) {
        TeamMembers t = new TeamMembers();

        t.setTeam_id(teamsRepository.getTeamId(requestMap.get("team"),Integer.parseInt(requestMap.get("sport_id"))));
        t.setParticipant_id(Integer.parseInt(requestMap.get("participant_id")));
        t.setSport_id(Integer.parseInt(requestMap.get("sport_id")));

        return t;
    }

    private boolean validateAddMember(Map<String, String> requestMap) {
        System.out.println(requestMap);
        if(requestMap.containsKey("sport_id") && requestMap.containsKey("team") && requestMap.containsKey("participant_id"))
        {
            return true;
        }
        return false;
    }

    public ResponseEntity<ArrayList<String>> getPlayers(Integer sport_id, String team) {
        try{
            Integer team_id = teamsRepository.getTeamId(team, sport_id);

            ArrayList<Integer> l = teamMembersRepository.getPlayers(sport_id, team_id);
            ArrayList<String> players = new ArrayList<>();
            for(Integer i: l){
                players.add(userRepository.getName(i));
            }
            return new ResponseEntity<ArrayList<String>>(players, HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        ArrayList<String> failed = new ArrayList<>();
        return new ResponseEntity<ArrayList<String>>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ArrayList<User>> getPlayersDetails(Integer sport_id, String team) {
        try{
            Integer team_id = teamsRepository.getTeamId(team,sport_id);

            ArrayList<Integer> l = teamMembersRepository.getPlayers(sport_id, team_id);
            ArrayList<User> players = new ArrayList<>();
            for(Integer i: l){
                System.out.println(userRepository.getUserDetails(i).getUsername());
                players.add(userRepository.getUserDetails(i));
            }
            return new ResponseEntity<ArrayList<User>>(players, HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        ArrayList<User> failed = new ArrayList<>();
        return new ResponseEntity<ArrayList<User>>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
