package services.Implementations;

import dao.Interfaces.IJourneyDao;
import models.entities.Journey;
import services.Interfaces.IJourneyService;

import java.util.List;

public class JourneyService implements IJourneyService {

    private IJourneyDao iJourneyDao;

    public JourneyService(IJourneyDao iJourneyDao) {
        this.iJourneyDao = iJourneyDao;
    }

    @Override
    public int addJourney(Journey journey) {
        if(journey.getDestinationCity() == journey.getDepartureCity()) {
            throw new IllegalArgumentException("La ville de depart ne doit pas etre la meme ville de destination !");
        }
        return iJourneyDao.addJourney(journey);
    }

    @Override
    public List<Journey> getAllJourneys() {
        return iJourneyDao.getAllJourneys();
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
