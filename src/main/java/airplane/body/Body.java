package airplane.body;

import java.util.ArrayList;

public class Body {
    private Cabin cabin;
    private CentralUnit centralUnit;
    private ArrayList<AntiCollisionLight> antiCollisionLights;
    private ArrayList<Engine> engines;
    private ArrayList<EntryDoor> entryDoors;

    public Body() {
        cabin = new Cabin.CabinBuilder().setRows().createCabin();
        centralUnit = new CentralUnit();
        antiCollisionLights = new ArrayList<>();
        antiCollisionLights.add(new AntiCollisionLight());
        antiCollisionLights.add(new AntiCollisionLight());

        entryDoors = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            entryDoors.add(new EntryDoor(i + 1));
        }

        engines = new ArrayList<>();
        engines.add(new Engine(1));
        engines.add(new Engine(2));
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
}
