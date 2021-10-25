# Fitbot User Guide

*Fitbot* is a **desktop app** that helps university students who are looking to **keep track of their calorie consumption and calorie output** with the speed and convenience of **command-line based** tools, especially in times of online school.

[Quick Start](#quick-start)

[Terminology](#terminology)

[Features](#features)

- [View help: `help`](#view-help-help)
- [Update name: `name`](#update-name-name)
- [Update height: `height`](#update-height-height)
- [Update weight: `weight`](#update-weight-weight)
- [Update net calorie goal: `goal`](#update-net-calorie-goal-goal)
- [Update/View the profile details: `profile`](#updateview-the-profile-details-profile)
- [BMI calculator: `bmi`](#bmi-calculator-bmi)
- [Add Exercise and Food Items: `add`](#add-exercise-and-food-items-add)
- [View Exercise and Food Items: `view`](#view-exercise-and-food-items-view)
- [Delete Exercise and Food Items: `delete`](#delete-exercise-and-food-items-delete)
- [Calculate net calories: `overview`](#calculate-net-calories-overview)
- [Exit the program: `bye`](#exit-the-program-bye)
- [Saving the data](#saving-the-data)

[Command Summary](#command-summary)

## Quick Start

1. Ensure you have Java 11 or above installed in your computer.  If not, install Java 11 [here](https://www.oracle.com/java/technologies/downloads/).
2. Download the latest version of [Fitbot.jar](https://github.com/AY2122S1-CS2113T-F14-2/tp/releases/tag/v1.0) from the cloud.
3. Copy the file to the folder you want to use as the home folder for your *FitBot*.
4. Go to command prompt and change the directory to the file's location.
5. Type `java -jar FitBot_v1.0.jar` into the command prompt and press enter to start the program.

If successfully loaded, you will see this screen:

![Untitled](https://user-images.githubusercontent.com/69421979/137489537-ef18d425-95ae-4e41-b5d6-752e665728b9.png)

## **Terminology**

**_Calorie_** - A unit used to measure the energy of an item. One calorie is the amount of energy required to raise the
temperature of one gram of water by one degree Celsius. On average, a male will require approximately 2500 cal while a
female will require around 2000 cal per day.\
\
***Calorie goal*** - The amount of calorie you wish to consume per day. In our application, calorie goal is defined as the
total calorie intake from all the food consumed per day. In addition, net calorie will mean the difference between calorie 
goal and the total calorie burnt from exercising and [body metabolism](https://www.news-medical.net/life-sciences/What-is-Metabolism.aspx). 
For your information, we use [basal metabolic rate (BMR)](https://en.wikipedia.org/wiki/Basal_metabolic_rate) to compute 
the rate of calorie consumed by body metabolic activity.\
\
***Activity factor*** - A coefficient or value used in the calculation of BMR to measure the level of activity. In our 
application, we use an integer ranged from 1 to 5 to measure the activity factor, with 1 being sedentary (little or
no exercise) and 5 being very active in playing sports and exercising. \
\
***Body Mass Index (BMI)*** - A measure to evaluate if the body weight is healthy. BMI is computed by using the body 
weight(in kg) divided by the square of the body height(in m). \
\
***Item*** - We use the term **item** to represent the item that can be stored in our application. The available item 
include **food** and **exercise** only.\
\
***Upcoming exercise*** - We define the exercise with date after today's date as an upcoming exercise. This kind of exercise 
will be handled specifically. It will not be added to the exercise list but will be saved internally in the storage file. More 
details of the process can be found at [here](linkToAddFutureExerciseOrDG).\
\
***Item Bank*** - An item storage that is capable to store the food or exercise item with its respective calorie. This is 
to help the user to memorize the calorie intake of a specific food or the calorie burnt of a specific exercise. More details 
of the item bank can be found at [here](linkToAddBank).


## **Features**

ℹ️  ***Command Format***

- Commands are not case sensitive (e.g. `help`, `HELP`,`hElP` are all able to execute the `help` command)
- Words in upper case (e.g. `UPPER_CASE`) are known as parameters; values to be provided by users
- Parameters in square brackets are optional.

  e.g. `bmi [h/HEIGHT_IN_CM w/WEIGHT_IN_KG]`  can be `bmi` or `bmi h/150 w/70`

- The order of the parameters do not matter.

  e.g. `add f/potato c/200` or `add c/200 f/potato` both adds a food item called "potato" with 200 calories.


❗ Please do not use the characters `/` and `|` in your input!

### **View help:** `help`

Shows a list of commands and their usage.

Format: `help`

Example:

```text
help
__________________________________________________________________________________________________________
These are the available commands:
Welcome to the help page.
Below are the commands to get you started.
More details could be found on: 
https://tinyurl.com/fitbotUG

help -- Shows a list of commands and their usage with some examples.

profile -- Inputs height of user in centimetres, weight of user in kg
	      and name of user in test.
goal -- Inputs net calorie goal of user in calorie.
bmi -- Calculates the BMI value based on the user's input height and weight.
add -- Adds food or exercise record to the current list.
view -- Views all the food and/or exercises added.
delete -- Deletes entry of food or exercise added.
bye -- Exits the program and save results from food and exercise items.
__________________________________________________________________________________________________________
```

### **Update name: `name`**

Updates name of user.

Format: `name NAME`

Example:

- `name John`

```text
name John
__________________________________________________________________________________________
Your name has been updated!
Hello John!
__________________________________________________________________________________________
```

### **Update height:** `height`

Updates height of user in centimetres.

Format: `height HEIGHT_IN_CM`

- Records height of user.

❗ `HEIGHT_IN_CM` must be a positive number.

Example:

- `height 170`

```text
height 170
__________________________________________________________________________________________
Your height has been updated.
Your height is 170.0cm.
__________________________________________________________________________________________
```

### **Update weight:** `weight`

Updates weight of user in kilograms.

Format: `weight WEIGHT_IN_KG`

- Records weight of user.

❗ `WEIGHT_IN_KG` must be a positive number.

Example:

- `weight 65`

```text
weight 65
__________________________________________________________________________________________
Your weight has been updated.
Your weight is 65.0kg.
__________________________________________________________________________________________
```

### Update net calorie goal: `goal`

Updates net calorie goal of user.

Format: `goal CALORIE_IN_CAL`

- Records net calorie goal of user.

❗`CALORIE_IN_CAL` must be an integer.

Example:

- `goal 2000`

```text
goal 2000
__________________________________________________________________________________________
Your goal has been set!
Current net calorie goal per day: 2000
__________________________________________________________________________________________
```

💡  **Tip:** Use `profile` to add name, height, weight and calorie goal at the same time.

### Update/View the profile details: `profile`

Updates/Views the name, height, weight and the calorie goal values.

Format:

`profile h/HEIGHT_IN_CM w/WEIGHT_IN_KG n/NAME g/CALORIE_IN_CAL`

`profile`

❗ `HEIGHT_IN_CM` and `WEIGHT_IN_KG` must be positive numbers.

❗`CALORIE_IN_CAL` must be an integer.

Example:

- `profile n/John Doe w/65 /h170 g/2000` creates a profile with name John Doe of height 170cm and 65kg with a calorie goal of 2000 calories.
- `profile` displays your profile details.

```text
profile n/John Doe w/65 h/170 g/2000

__________________________________________________________________________________________________________
Hello John Doe! Your profile has been created!
    Your height is 170.0cm.
    Your weight is 65.0kg.
    Your calories goal is 2000 cal.
__________________________________________________________________________________________________________
profile
__________________________________________________________________________________________________________
Hello! This is your current profile:
    Your name is John.
    Your height is 170.0cm.
    Your weight is 65.0kg.
    Your calorie goal is 2000 cal.
__________________________________________________________________________________________________________
```

### **BMI calculator:** `bmi`

Calculates the BMI value based on the user's input height and weight.

If the user does not provide the values, the BMI will be computed based on the user's current height and weight in the profile.

It also outputs the weight status *(underweight, healthy, overweight, obese)* based on calculated BMI.

Format: `bmi h/HEIGHT_IN_CM w/WEIGHT_IN_KG`

❗ `HEIGHT_IN_CM` and `WEIGHT_IN_KG` must be positive numbers.

Example:

- `bmi` calculates the BMI value based on the user's height and weight in his or her profile.
- `bmi h/170 w/65` calculates the BMI value based on height 170cm and weight 65cm.

```text
bmi h/170 w/65
__________________________________________________________________________________________
Your BMI value is 22.5 (Healthy)
__________________________________________________________________________________________
```

### **Add** **Exercise and Food Items:** `add`

Adds food or exercise record to the current list.

Format:

- `add f/ITEM {c/CALORIES} {d/dd-mm-yyyy} {t/hhmm}` adds a food item consumed with its respective calories on the given 
date (`dd-mm-yyyy`) and time (`hhmm`}. 
- `add e/ITEM {c/CALORIES} {d/dd-mm-yyyy}` adds an exercise with its respective calories burnt on the given date (`dd-mm-yyyy`}.

Examples:

- `add f/chicken rice c/607 d/21-10-2021 t/1400` adds record of food consumed: chicken rice with 607 calories gained.

```text
add f/chicken rice c/607 d/21-10-2021 t/1400
__________________________________________________________________________________________
A food item has been added:
    chicken rice (607 cal) @ 14:00, 21 Oct 2021
__________________________________________________________________________________________
```

- `add e/hiit c/290 ` adds record of exercise done: hiit with 290 calories burnt.

```text
add e/hiit c/290 d/ d/21-10-2021
__________________________________________________________________________________________
An exercise has been added:
    hiit (290 cal) @ 21-10-2021
__________________________________________________________________________________________
```
💡  **Tip:** If you do not specify the date and time of the food item, it is assumed that the date is today and the time
is when you add this food. Similarly, if the date of the exercise is not provided, the date is assumed to be today.\
💡  **Tip:** If you do not provide the calorie of the food/exercise item, Fitbot will search and retrieve the calorie value 
from the corresponding item bank if the item is found. 
```
add f/chicken rice d/22-10-2021 t/1345
__________________________________________________________________________________________
A food item has been added:
    chicken rice (607 cal) @ 13:45, 22 Oct 2021
__________________________________________________________________________________________
```

### **View** **Exercise and Food Items:** `view`

Views all the food or exercises added.

Format:

- `view f/` views all the food items and their calories added within 7 days (include today) to the list.

```text
view f/
__________________________________________________________________________________________________________
Here is a summary of all the food items you have consumed in the past week:
..........................................................................................................
You have consumed 3 food item(s) on Friday (22 Oct 2021):
In the morning:
	1. donut x2 (607 cal) @ 10:00
In the afternoon:
	1. chicken rice (607 cal) @ 14:00
In the evening:
	1. yong tau foo (560 cal) @ 19:20
Total calories consumed in the day: 1774 cal
..........................................................................................................
You have consumed 4 food item(s) on Saturday (23 Oct 2021):
In the morning:
	1. butter bread x2 (418 cal) @ 08:30
In the afternoon:
	1. penang laksa (377 cal) @ 13:00
In the evening:
	1. sliced fish bee hoon (349 cal) @ 18:40
At night:
	1. roti prata x3 (507 cal) @ 23:50
Total calories consumed in the day: 1651 cal
..........................................................................................................
Total number of food consumed in this week: 7
Total calories consumed in this week: 3425 cal
__________________________________________________________________________________________________________
```

- `view e/` views all the exercises taken and the calories that are added within past 7 days (include today) to list.

```text
view e/
__________________________________________________________________________________________________________
 Here is a summary of all the exercises you have done in the past week:
..........................................................................................................
You have done 1 exercise(s) on Friday (22 Oct 2021):
	1. biking (500 cal)
Total calories burnt in the day: 500 cal
..........................................................................................................
You have done 1 exercise(s) on Sunday (24 Oct 2021):
	1. hiit (290 cal)
Total calories burnt in the day: 290 cal
..........................................................................................................
Total exercises done in this week: 2
Total calorie burnt in the week: 790
__________________________________________________________________________________________________________
```

### **Delete** **Exercise and Food Items:** `delete`

Deletes entry of food or exercise added.

Format:

`delete f/LIST_NO. d/dd-mm-yyyy t/hhmm` deletes the *n<sup>th</sup>* food item in the food list which has the date (`dd-mm-yyyy`) 
and time (`hhmm`), where *n* is the index of the food to delete.

`delete e/LIST_NO. d/dd-mm-yyyy` deletes the *n<sup>th</sup>* exercise in the exercise list which contains the date (`dd-mm-yyyy`),
 where *n* is the index of the exercise to delete

`delete f/all` deletes all the food items in the food list.

`delete e/all` deletes all the exercise items in the exercise list.

❗ `LIST_NO.` must be a positive integer within the range of the number of items in the list.

Example:

```text
delete f/1 d/22-10-2021 t/1000
__________________________________________________________________________________________________________
A food item has been deleted:
	donut x2 (607 cal) @ 10:00, 22 Oct 2021
Number of food item(s) left: 6
__________________________________________________________________________________________________________
delete e/1 d/24-10-2021
__________________________________________________________________________________________________________
You have removed the exercise:
    hiit (290 cal) @ 24 Oct 2021
Number of exercise item(s) left: 1
__________________________________________________________________________________________________________
delete f/all
__________________________________________________________________________________________________________
All food items have been removed.
__________________________________________________________________________________________________________
delete e/all
__________________________________________________________________________________________________________
All exercise items have been removed.
__________________________________________________________________________________________________________
```

### **Calculate net calories**: `overview`

Views the difference between the calories consumed and the calories lost (in cal).

Format: `overview`

```text
overview
__________________________________________________________________________________________
Your calorie gained from food is: 2415
Your calorie lost from exercise is: 790
Your net calorie intake is: 1625
Your calorie goal is: 2000
You are 375 cal away from your goal
__________________________________________________________________________________________
```

### Exit the program: `bye`

Exits the program.

Format: `bye`

```text
bye
__________________________________________________________________________________________
Exiting Fitbot....
Bye! Hope to see you again soon!!
__________________________________________________________________________________________
```

### Saving the data

There is no need to save manually. Any updates made to the data will be automatically stored into the local drive and reloaded when *FitBot* is restarted.

### Command Summary

| Action | Format | Examples |
|---------|----------|-------|
add|`add f/ITEM {c/CALORIE} {d/dd-mm-yyyy} {t/hhmm}` <br>`add e/ITEM {c/CALORIES} {d/dd-mm-yyyy}` | `add f/chicken rice c/607 d/20-10-2021`, <br>`add e/hiit c/290 d/23-10-2021`
bmi|`bmi h/HEIGHT_IN_CM w/WEIGHT_IN_KG` `bmi`|`bmi h/170 w/65` , `bmi`
bye|`bye`|
delete|`delete f/LIST_NO. d/dd-mm-yyyy t/hhmm` <br> `delete e/LIST_NO. d/dd-mm-yyyy` <br> `delete f/all` <br> `delete e/all` |`delete f/1`, <br> `delete e/2`, <br> `delete f/all`, <br>  `delete e/all`
goal |`goal CALORIE_IN_CAL` | `goal 2000`
height|`height HEIGHT_IN_CM` | `height 170`
help | `help`|
name|`name NAME`|`height 170`
overview|`overview`|
profile|`profile h/HEIGHT_IN_CM w/WEIGHT_IN_KG n/NAME g/CALORIE_IN_CAL` `profile`|`profile h/170 w/65 n/John g/2000`, `profile`
view|`view`|`view e/`,  `view f/`
weight|`weight WEIGHT_IN_KG`|`weight 65`
