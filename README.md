Demonstrating the issue with queries in backward chaining example.

This is an implementation of the old backward chaining example using drools 8 rule units.
Apparently accessing a DataStore/SingletonStore before a query in a rule results in a NPE.
<details>
<summary>Stack trace</summary>
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "org.drools.core.reteoo.ObjectTypeNode.getObjectSinkPropagator()" because "queryOtn" is null
at org.drools.core.phreak.RuntimeSegmentUtilities.getQueryLiaNode(RuntimeSegmentUtilities.java:224)
at org.drools.core.phreak.RuntimeSegmentUtilities.getQuerySegmentMemory(RuntimeSegmentUtilities.java:107)
at org.drools.core.phreak.LazyPhreakBuilder.processQueryNode(LazyPhreakBuilder.java:1493)
at org.drools.core.phreak.LazyPhreakBuilder.createSegmentMemory(LazyPhreakBuilder.java:1415)
at org.drools.core.phreak.LazyPhreakBuilder.createSegmentMemory(LazyPhreakBuilder.java:1356)
at org.drools.core.phreak.RuntimeSegmentUtilities.getOrCreateSegmentMemory(RuntimeSegmentUtilities.java:74)
at org.drools.core.common.Memory.getOrCreateSegmentMemory(Memory.java:35)
at org.drools.core.reteoo.BetaMemory.setNodeDirty(BetaMemory.java:147)
at org.drools.core.reteoo.BetaMemory.setNodeDirty(BetaMemory.java:142)
at org.drools.core.reteoo.NotNode.assertObject(NotNode.java:147)
at org.drools.core.reteoo.SingleObjectSinkAdapter.propagateAssertObject(SingleObjectSinkAdapter.java:71)
at org.drools.core.reteoo.AlphaNode.assertObject(AlphaNode.java:122)
at org.drools.core.reteoo.CompositeObjectSinkAdapter.doPropagateAssertObject(CompositeObjectSinkAdapter.java:745)
at org.drools.core.reteoo.CompositeObjectSinkAdapter.propagateAssertObject(CompositeObjectSinkAdapter.java:578)
at org.drools.core.reteoo.ObjectTypeNode.propagateAssert(ObjectTypeNode.java:246)
at org.drools.core.phreak.PropagationEntry$Insert.propagate(PropagationEntry.java:235)
at org.drools.core.phreak.PropagationEntry$Insert.internalExecute(PropagationEntry.java:248)
at org.drools.core.phreak.PropagationEntry.execute(PropagationEntry.java:54)
at org.drools.core.phreak.SynchronizedPropagationList.flush(SynchronizedPropagationList.java:102)
at org.drools.core.phreak.SynchronizedPropagationList.flush(SynchronizedPropagationList.java:97)
at org.drools.core.impl.ActivationsManagerImpl.fireLoop(ActivationsManagerImpl.java:291)
at org.drools.core.impl.ActivationsManagerImpl.fireAllRules(ActivationsManagerImpl.java:277)
at org.drools.ruleunits.impl.sessions.RuleUnitExecutorImpl.fireAllRules(RuleUnitExecutorImpl.java:280)
at org.drools.ruleunits.impl.sessions.RuleUnitExecutorImpl.fireAllRules(RuleUnitExecutorImpl.java:263)
at org.drools.ruleunits.impl.ReteEvaluatorBasedRuleUnitInstance.fire(ReteEvaluatorBasedRuleUnitInstance.java:45)
at tc_tests.TCTestApplication.main(TCTestApplication.java:35)

Execution failed for task ':TCTestApplication.main()'.
</details>


The rule works fine when accessing a DataStore/SingletonStore after the query, not accessing one at all, or accessing a "normal" global.
Global values can be used as query args.

With the old way accessing elements from working memory works fine, e.g. like in the old example:
```
   rule "infer locations"
   when
     $x: String("go")
     queryLocations($x, $y)
     ...
   then
     ...
   end
```

or for querying only with a subset of the WME on one paramater.
```
   rule "infer locations"
   when
     $x: Location(inferred==false)
     queryLocations($x.thing, $y)
     ...
   then
     ...
   end
```


