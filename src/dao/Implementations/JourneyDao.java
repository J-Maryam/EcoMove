package dao.Implementations;

import dao.Interfaces.IJourneyDao;
import models.entities.Journey;

import java.util.List;

public class JourneyDao implements IJourneyDao {
    @Override
    public int addJourney(Journey journey) {
        return 0;
    }

    @Override
    public List<Journey> getAllJourneys() {
        return List.of();
    }

    @Override
    public Journey getJourneyById(int id) {
        return null;
    }


    @Override
    public int updateJourney(Journey journey) {
        return 0;
    }

    @Override
    public int deleteJourney(Journey journey) {
        return 0;
    }
}
