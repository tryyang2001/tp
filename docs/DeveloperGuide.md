# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

### Architecture

![image](https://user-images.githubusercontent.com/69421979/138269962-93eeb36e-1594-479e-9ffa-f69ea4f372fe.png)


### Data Component (ItemBank and Item)

![image](https://user-images.githubusercontent.com/69421979/138306373-3d41a51e-5ab1-470b-b199-7046c49b12f6.png)

The `Data` component is responsible to perform operations such as data modificationand query in the code. It receives the commands from the `Logic` component, execute the 
correct operations, and finally return the command result back to `Logic` component.\
\
Above is a high-level **_class diagram_** for the `ItemBank` and `Item` classes in `Data` component. Note that since `Main` and `Logic` components have accessed to some classes
in `Data` component, they form **_dependencies_** with those classes.
The main purpose of having `ItemBank` and `Item` classes is to allow user to perform writing, reading, editing and deleting operations in the program.\

#### ItemBank class
`ItemBank` is the **_highest superclass_** that contains one attribute called `internalItems` which is an _array list_ of `item`.\
`ItemList` being the **_subclass_** of `ItemBank` and **_superclass_** of `FoodList` and `ExerciseList`, which inherits all the methods available from `ItemBank`, with additional methods that form a dependency on `Item` class.\
`FoodList` and `ExerciseList` are **_subclasses_** that inherit all the methods available from `ItemList`, while each of them also contains more methods that form a dependency
on `Food` class and `Exercise` class respectively.

#### Item class
An `Item` class contains two attributes, `name` which represents the name of the item, and `calories` which represents the calorie intake/burnt from the item.\
`Food` and `Exercise` are the only two **_subclasses_** inherit the `Item` class. \
`Food` class has two extra attributes called `dateTime` and `timePeriod`, the former stores the consumed food date and time, while the latter compute the time period 
(only value such as **`Morning`, `Afternoon`, `Evening`** and **`Night`** as shown in the enumeration class `TimePeriod`) of the food consumed time. Note that the `timePeriod` 
value must present when a `Food` object is created.\
`Exercise` class has one extra attribute called `date` which stores the date of the exercise taken.\
\
Classes such as `ItemList` and `Item` are _**abstract class**_, because they do not add meaningful value to the user if one tries to create them.

### Logic Component
The `Logic` component is responsible for making sense of user input.

Below is a high level class diagram of the `Logic` component, which shows how it interacts with other components 
like `Main` and `Data`.


<img src="images/LogicClassDiagram.png" width = "600" /> 


Here is a more detailed class diagram of the `Logic` component.

<img src ="images/ParserClassDiagram" width = "600" />

Taking a closer look into the parsing process, the `ParserManager` actually does not do most of the parsing itself.
(To be completed)


The sequence diagram below models the interactions between the different classes within the `Logic` component and 
illustrates how a user input `add f/potato c/20` is parsed and process to execute the appropriate actions.

<img src="images/LogicSequenceDiagram.png" width = "600" />






## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary
**_dependency_** : In UML diagram, dependency is a directed relationship which is used to show that some elements or a set of elements requires, needs or depends on other model elements for specification or implementation.\
**_superclass_** : A class from which other classes inherit its code. The class that inherits its code will be able to access some/all functionalities from the superclass.\
**_subclass_**   : A class that inherits code from the other classes. Such class will be able to access some/all functionalities from its superclass, but not vice versa.\
**_abstract class_** : A class that cannot be created using constructor. Usually such class is a superclass, and it does not give meaningful value if one tries to construct it.\
(more coming in the future...)
## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
