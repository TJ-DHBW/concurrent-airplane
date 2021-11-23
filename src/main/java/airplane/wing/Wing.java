package airplane.wing;

import java.util.ArrayList;
import java.util.Arrays;

public class Wing {
    private final ArrayList<Flap> flaps = new ArrayList<>();

    public Wing(Flap[] flaps) {
        this.flaps.addAll(Arrays.asList(flaps));
    }

    public ArrayList<Flap> getFlaps() {
        return flaps;
    }
}
