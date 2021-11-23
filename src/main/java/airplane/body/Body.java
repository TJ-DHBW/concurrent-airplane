package airplane.body;

import java.util.ArrayList;

public class Body {
    private Cabin cabin;
    private CentralUnit centralUnit;
    private ArrayList<AntiCollisionLight> antiCollisionLights;

    public Body() {
        cabin = new Cabin.CabinBuilder().setBusinessClass().setPremiumEconomyClass().setEconomyClass().createCabin();
        centralUnit = new CentralUnit();
        antiCollisionLights = new ArrayList<>();
        antiCollisionLights.add(new AntiCollisionLight());
        antiCollisionLights.add(new AntiCollisionLight());
    }
}
