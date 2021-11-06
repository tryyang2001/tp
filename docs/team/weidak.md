# Tay Weida - Project Portfolio Page

## Overview

Fitbot is a desktop app that helps university students who are looking to keep track of their calorie consumption and calorie output with the speed and convenience of command-line based tools, especially in times of online school.

### Summary of Contributions
The following items listed below are what I have contributed to the team:

#### **New Feature**: Saving and loading of data from Storage

- What it does: It allows users to load all the data upon startup of the application and save data depending on the command that is being executed. 
- Justification: Having a storage utility is crucial in our app's functionality of calculating the past week of exercise/food consumed,
as well as remembering one's profile attributes that lead to the calculations.
- Highlights: This feature loads and saves all the different data structures we have came up with, including FoodList, ExerciseList, FutureExerciseList, ItemBank, Profile. It can detect an anomaly within the file and handle it in a way that it can be dealt with later on
in another state whereby users can modify their attributes again in case they have accidentally corrupted a particular attribute.

#### **New Feature**: Profile 

- What it does: Contains the user's attributes such as Name, Height, Weight, Age, Gender, Calorie Goal, Activity Factor.
- Justification: These attributes are required in the Harris-Benedict Equation we used to calculate one's Basal Metabolic Rate, which is 
important in determining an accurate calorie budget for the day.
- Highlights: This feature incorporates its individual classes of each attribute to ensure data integrity is preserved and profile is container that 
holds these attributes together.


#### **Code Contributed**:

[Reposense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=weidak&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=weidak&tabRepo=AY2122S1-CS2113T-F14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

#### **Project Management**:

- Aided in setting up the GitHub repo and initializing the project during the early stages of the project
- Helped to find bugs and assign new issues on a consistent basis
- PRs reviewed: 

[comment]: <> (TODO add the PRs reviewed on github prs..)

#### **Enhancements To Existing Features**:

**Creation of DataManager Class**

- After deliberating with the other members, a DataManager class was created to encompass all the different types of Data we have (FoodList, Profile, ExerciseBank etc.)
and ensured all the items are localised in this class. This declutters Main.java and primarily acts as a container for the various items.

#### **Documentation**:

- User Guide: Section 4.2 (Profile commands and its calculation of BMI)
- Developer Guide:
  - Profile Class Diagram (Design)
  - StorageManager Class Diagram (Design)
  - ProfileStorage Class Diagram (Design)
  - Loading of Data on Startup (Implementation)

#### **Community**: