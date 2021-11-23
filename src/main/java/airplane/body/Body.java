package airplane.body;

import java.util.ArrayList;

public class Body {
    private Cabin cabin;
    private CentralUnit centralUnit;
    private ArrayList<AntiCollisionLight> antiCollisionLights;

    public Body() {
        cabin = new Cabin.CabinBuilder().setRows().createCabin();
        centralUnit = new CentralUnit();
        antiCollisionLights = new ArrayList<>();
        antiCollisionLights.add(new AntiCollisionLight());
        antiCollisionLights.add(new AntiCollisionLight());
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

    public void setAntiCollisionLights(ArrayList<AntiCollisionLight> antiCollisionLights) {
        this.antiCollisionLights = antiCollisionLights;
    }
}
