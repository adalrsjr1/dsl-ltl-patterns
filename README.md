# DSL for LTL properties

This project aims to facilitate writing LTL properties by abstracting the classical LTL notation.

The DSL created is based on [spec
patterns](http://patterns.projects.cs.ksu.edu/documentation/patterns/ltl.shtml)
project (or [web archive](https://web.archive.org/web/20190314161601/http://patterns.projects.cs.ksu.edu/documentation/patterns/ltl.shtml).

You can find the usage examples  in [tests
folder](https://github.com/adalrsjr1/dsl-ltl-patterns/tree/master/src/test/java/github/com/adalrjsr1/ltl).

## Example

``` Groovy
void testBoundedContext() {
  assert new PropertyParser().create {
                              boundedExistence "P"
                              occurs 2
                              justAfter "Q"
  }.toString() == "<>Q -> (!Q U (Q & (!P W (P W (!P W (P W []!P))))))"
}
```

## Labeled Transition System (LTS)

This project also includes a framework to transform the DSLs into an automaton to execute check trances.

The implementation is based on [Spot](spot.lrde.epita.fr) and requires its installation as the dependency of the project.

Internally, the framework compiles the DSL into LTL and then transform it to [HOAF format](https://github.com/adl/hoaf). The Spot parses this data structure and creates an automaton. The automata move according to the inputs. More  details about the automata creation [here](https://github.com/adalrsjr1/dsl-ltl-patterns/blob/master/src/main/java/github/com/adalrsjr1/ltl/core/LabeledTransitionSystemFactory.groovy)

The factory can be updated to create Büchi automata to check messages on the fly, as a monitor, or logged.

You can change this mode by updating the factory  to use the statement `ltl2tgba --monitor`.
