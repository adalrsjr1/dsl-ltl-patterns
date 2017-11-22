# DSL for LTL properties

This project aims to facilitate writing LTL properties by abstracting the
classical LTL notation.

The DSL created is based on the [spec
patterns](http://patterns.projects.cs.ksu.edu/documentation/patterns/ltl.shtml)
project.

The usage examples can be found on [tests
folder](https://github.com/adalrsjr1/dsl-ltl-patterns/blob/master/src/test/java/github/com/adalrjsr1/ltl/specpatterns/TestSpecpattern.groovy) 

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

This project also includes a framework to transform the DSLs into an automata to
execute check trances.

The implementation is based on [Spot](spot.lrde.epita.fr), and requires its
installation as dependency of the project.

Internally, the framework compile the DSL into LTL, and then transform it to
[HOAF format](https://github.com/adl/hoaf). The Spot parses this data structure
and creates an automata. The automata moves acording to the inputs. The details
about the automata creation can be found
[here](https://github.com/adalrsjr1/dsl-ltl-patterns/blob/master/src/main/java/github/com/adalrsjr1/ltl/core/LabeledTransitionSystemFactory.groovy)

The factory can be updated to create Büchi automatas to check messages offline
or online, as a monitor.

To change this creation it is necessary to update the factory and change the
creation command `ltl2tgba --monitor`.
