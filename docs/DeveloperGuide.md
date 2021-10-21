# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

### Architecture

[Architecture Diagram]
(will upload later by ry)\
For description of architecture diagram, need people to do it, if not then I will do at ard 10 pm :)\

### Data Component (ItemBank and Item)

[ItemBankAndItemClassDiagram]
(will upload later by ry)

The main purpose of having `ItemBank` and `Item` classes is to allow user to perform writing, reading, editing and deleting operations in the program.\
`ItemBank` is the **highest superclass** that contains one attribute called `internalItems` which is an array list of item.\
`ItemList` being the subclass of `ItemBank`, which inherits all the methods available from `ItemBank`, with additional methods that form a dependency on `Item` class.\
`FoodList` and `ExerciseList` are subclasses that inherit all the methods available from `ItemList`, while each of them also contains more methods that form a dependency
on `Food` class and `Exercise` class respectively.\
An `Item` class contains two attributes, `name` which represents the name of the item, and `calories` which represents the calorie intake/burnt from the item.\
`Food` and `Exercise` are the only two subclasses inherit the `Item` class. \
`Food` class has two extra attributes called `dateTime` and `timePeriod`, the former stores 
the consumed food date and time, while the latter compute the time period (only value such as `Morning`, `Afternoon`, `Evening` and `Night` as shown in the enumeration class `TimePeriod`) of the food consumed time. Note that the `timePeriod` 
value must present when a `Food` object is created.\
`Exercise` class has one extra attribute called `date` which stores the date of the exercise taken.\
Classes such as `ItemList` and `Item` are set as **abstract class**, because they do not add meaningful value to the user when one tried to create them.
##Xingjie rmb to add future exercise list here...^^

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

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
