package dao.Interfaces;

import models.entities.Journey;

import java.util.List;

public interface IJourneyDao {
    int addJourney(Journey journey);
    List<Journey> getAllJourneys();
    Journey getJourneyById(int id);
    int updateJourney(Journey journey);
    int deleteJourney(Journey journey);
}
