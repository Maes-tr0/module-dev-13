package ua.maestr0.service;

import ua.maestr0.dao.PlanetDAO;
import ua.maestr0.model.Planet;

public class PlanetService extends EntityService<Planet, String> {
    public PlanetService() {
        super(new PlanetDAO());
    }
}
