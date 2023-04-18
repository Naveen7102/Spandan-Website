package com.spe.spandan.service;

import com.spe.spandan.model.FixtureList;
import com.spe.spandan.model.FixtureReturn;
import com.spe.spandan.model.Fixtures;
import com.spe.spandan.model.Message;
import com.spe.spandan.repository.FixturesRepository;
import com.spe.spandan.repository.SportsRepository;
import com.spe.spandan.repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class FixturesService {
    @Autowired
    FixturesRepository fixturesRepository;

    @Autowired
    SportsRepository sportsRepository;

    @Autowired
    TeamsRepository teamsRepository;


    public ResponseEntity<Message> addFixture(Map<String, String> requestMap) {
        Message success = new Message("Fixture added Successfully.");
        Message addFailed = new Message("Invalid Data");
        Message failed = new Message("Something Went Wrong at Fixture Service.");
        try{
            if(validateAddFixture(requestMap)){
                fixturesRepository.save(createFixtureFromMap(requestMap));
                return new ResponseEntity<Message>(success, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<Message>(addFailed, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<Message>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Fixtures createFixtureFromMap(Map<String, String> requestMap) {
        Fixtures f = new Fixtures();

        f.setSport_id(Integer.parseInt(requestMap.get("sport_id")));
        f.setResult("-");
        f.setTime(Timestamp.valueOf(requestMap.get("time")));
        f.setTeam1_id(Integer.parseInt(requestMap.get("team1_id")));
        f.setTeam2_id(Integer.parseInt(requestMap.get("team2_id")));
        f.setWinner(-1);

        return f;
    }

    private boolean validateAddFixture(Map<String, String> requestMap) {
        System.out.println(requestMap);
        if(requestMap.containsKey("sport_id") && requestMap.containsKey("team1_id") && requestMap.containsKey("team2_id") && requestMap.containsKey("time"))
        {
            return true;
        }
        return false;
    }

    public ResponseEntity<FixtureList> getFixtures(String sport) {
        try {
            Integer sport_id = sportsRepository.getSportId(sport);
            List<Fixtures> fixtures = fixturesRepository.getFixturesbySport(sport_id);
            List<FixtureReturn> fList = new ArrayList<>();
            for (Fixtures f : fixtures) {
                FixtureReturn fr = new FixtureReturn();
                fr.setTime(f.getTime());
                fr.setResult(f.getResult());
                fr.setSport(sport);
                fr.setTeam1(teamsRepository.getTeamName(f.getTeam1_id()));
                fr.setTeam2(teamsRepository.getTeamName(f.getTeam2_id()));
                if(f.getWinner() == -1)
                {
                    fr.setWinner("-");
                }
                else {
                    if(f.getWinner() == f.getTeam1_id()){
                        fr.setWinner(fr.getTeam1());
                    }
                    else {
                        fr.setWinner(fr.getTeam2());
                    }
                }

                fList.add(fr);
            }
            FixtureList fl = new FixtureList(fList);
            return new ResponseEntity<FixtureList>(fl, HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        FixtureList fl = new FixtureList();
        return new ResponseEntity<FixtureList>(fl, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Transactional
    public ResponseEntity<Message> updateResult(Map<String, String> requestMap) {
        Message success = new Message("Fixture updated Successfully.");
        Message failed = new Message("Something Went Wrong at Fixture Service.");
        try{
            Integer id = Integer.parseInt(requestMap.get("id"));
            String winner = requestMap.get("winner");
            String result = requestMap.get("result");
            Integer winner_id = teamsRepository.getTeamId(winner);
            fixturesRepository.updateResult(result,winner_id,id);
            return new ResponseEntity<Message>(success, HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<Message>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
