package airplane;

import airplane.body.Body;
import airplane.wing.Wing;

import java.util.ArrayList;

public class Airplane {
    private Body body;
    private ArrayList<Wing> wings;

    public Airplane() {
        this.body = new Body();
        this.wings = new ArrayList<>();
        wings.add(new Wing());
        wings.add(new Wing());
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public ArrayList<Wing> getWings() {
        return wings;
    }

    public void setWings(ArrayList<Wing> wings) {
        this.wings = wings;
    }
}
