# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}




Main class is the component that interacts with all the necessary classes. 
The Main class consists of the few components as shown below:
- Ui The interaction between user and application
- Parser Parse commands and execute them respectively
- Data allow users to perform CRUD operations on the data in the application
- Storage stores all data in the application. Saves a copy of data in relevant files.
Data will be retrieved from storage upon starting of application.

Upon launching of application:
- The application will check if there are files that are already stored in the respective folder.
If there is such files, the contents of the files will be loaded to the data section of the applicaton.
Instances of profile, data(e.g. FoodList, ExerciseList, FutureExerciseList, ItemBank) and storage will be created


Upon exiting of application:
- The application will save all data into the files created. All instance of components will be cleared automatically.


Interaction between the classes could be shown by the uml sequence diagram below.

<img src ="images/architecture.png" width = "600" />

When there is an input, the Ui class will retrieve the information from the user. 
The information will be parsed by the `Parser` and then upon checking its validity, 
it will be saved into the `Data` and `Storage` class.

###Ui Component
The 'Ui' component interacts with the user. It reads in input from the user and prints messages on the console.
Below shows a class diagram of how `Ui` component interacts with the rest of the application.

<insert image here>



###Logic Component
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

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
