package tc_tests;

import org.drools.ruleunits.api.SingletonStore;
import tc_tests.facts.Location;
import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;

public class LocationUnit implements RuleUnitData {

    private final DataStore<Location> locations;
    private final SingletonStore<String> go;

    private String testGlobal = "computer";

    public LocationUnit() {
        this(DataSource.createStore(), DataSource.createSingleton());
    }
    public LocationUnit(DataStore<Location> locations, SingletonStore<String> go) {

        this.locations = locations;
        this.go = go;
    }

    public DataStore<Location> getLocations() {
        return locations;
    }

    public SingletonStore<String> getGo() {
        return go;
    }

    public String getTestGlobal() {
        return testGlobal;
    }

}
