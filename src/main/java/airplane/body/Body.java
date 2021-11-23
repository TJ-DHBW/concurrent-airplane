package airplane.body;

import java.util.ArrayList;
import java.util.Arrays;

public class Body {
    private Cabin cabin;
    private CentralUnit centralUnit;
    private ArrayList<AntiCollisionLight> antiCollisionLights = new ArrayList<>();
    private ArrayList<Engine> engines = new ArrayList<>();
    private ArrayList<EntryDoor> entryDoors = new ArrayList<>();
    private ArrayList<LandingGear> landingGears = new ArrayList<>();

    public Body(Cabin cabin, CentralUnit centralUnit, AntiCollisionLight[] antiCollisionLights, EntryDoor[] entryDoors, Engine[] engines, LandingGear[] landingGears) {
        this.cabin = cabin;
        this.centralUnit = centralUnit;
        this.antiCollisionLights.addAll(Arrays.asList(antiCollisionLights));
        this.entryDoors.addAll(Arrays.asList(entryDoors));
        this.engines.addAll(Arrays.asList(engines));
        this.landingGears.addAll(Arrays.asList(landingGears));
    }


    public Cabin getCabin() {
        return cabin;
    }

    public void setCabin(Cabin cabin) {
        this.cabin = cabin;
    }

    public CentralUnit getCentralUnit() {
        return centralUnit;
    }

    public void setCentralUnit(CentralUnit centralUnit) {
        this.centralUnit = centralUnit;
    }

    public ArrayList<AntiCollisionLight> getAntiCollisionLights() {
        return antiCollisionLights;
    }

    public ArrayList<Engine> getEngines() {
        return engines;
    }

    public ArrayList<EntryDoor> getEntryDoors() {
        return entryDoors;
    }

    public void setAntiCollisionLights(ArrayList<AntiCollisionLight> antiCollisionLights) {
        this.antiCollisionLights = antiCollisionLights;
    }

    public ArrayList<LandingGear> getLandingGears() {
        return landingGears;
    }
}
