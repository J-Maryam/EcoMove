package services.Implementations;

import models.entities.Journey;
import services.Interfaces.IJourneyService;

import java.util.List;

public class JourneyService implements IJourneyService {
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
    public int deleteJourney(int id) {
        return 0;
    }
}
