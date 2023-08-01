package tc_tests;


import tc_tests.facts.Location;
import org.drools.ruleunits.api.DataHandle;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;

import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.kie.api.runtime.rule.Variable;

public class TCTestApplication {

	public static void main(String[] args) {

		LocationUnit locationUnit = new LocationUnit();
		RuleUnitInstance<LocationUnit> instance =
				RuleUnitProvider.get().createRuleUnitInstance(locationUnit);

		// Inserting initial combination from examples.
		DataHandle office_fh = locationUnit.getLocations().add( new Location("office", "house") );
		locationUnit.getLocations().add( new Location("kitchen", "house") );
		locationUnit.getLocations().add( new Location("knife", "kitchen") );
		locationUnit.getLocations().add( new Location("cheese", "kitchen") );
		// Get facthandle to modify later
		DataHandle desk_fh = locationUnit.getLocations().add( new Location("desk", "office") );
		locationUnit.getLocations().add( new Location("chair", "office") );
		// Get facthandle to modify later
		DataHandle computer_fh = locationUnit.getLocations().add( new Location("computer", "desk") );
		locationUnit.getLocations().add( new Location("drawer", "desk") );

		System.out.println("First inference\n--------");
		locationUnit.getGo().set("go1");
		instance.fire();

		System.out.println("\nQuery results\n--------");
		// Get all in relations inserted and inferred facts
		QueryResults results = instance.executeQuery("getLocations", new Object[]{Variable.v});
		for(QueryResultsRow row : results)
		{
			System.out.println(row.get("$x"));
		}
	}
}
