
# Fitbot User Guide

*Fitbot* is a **desktop app** that helps university students who are looking to **keep track of their calorie consumption and calorie output** with the speed and convenience of **command-line based** tools, especially in times of online school.

[Quick Start](#quick-start)

[Features](#features)

- [View help: `help`](#view-help-help)
- [View your profile details: `profile`](#view-the-profile-details-profile)
  - [Updating your name](#update-name)
  - [Updating your height](#update-height)
  - [Updating your weight](#update-weight)
  - [Updating your gender](#update-gender)
  - [Updating your age](#update-age)
  - [Updating your calorie goal](#update-calorie-goal)
  - [Updating your activity level](#update-activity-factor)
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


## **Features**

ℹ️ ***Command Format***

- Commands are not case sensitive (e.g. `help`, `HELP`,`hElP` are all able to execute the `help` command)
- Words in upper case (e.g. `UPPER_CASE`) are known as parameters; values to be provided by users
- Parameters in square brackets are optional.

  e.g. `bmi [h/HEIGHT_IN_CM w/WEIGHT_IN_KG]` can be `bmi` or `bmi h/150 w/70`

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
More details could be found on: https://tinyurl.com/fitbotUG  
  
help -- Shows a list of commands and their usage with some examples.  
  
profile -- Inputs height of user in centimetres, weight of user in kg  
 and name of user in test.goal -- Inputs net calorie goal of user in calorie.  
bmi -- Calculates the BMI value based on the user's input height and weight.  
add -- Adds food or exercise record to the current list.  
view -- Views all the food and/or exercises added.  
delete -- Deletes entry of food or exercise added.  
bye -- Exits the program and save results from food and exercise items.  
__________________________________________________________________________________________________________  
```  

### View the profile details: `profile`

Typing `profile` lets you view your name, height, weight, gender, age, calorie goal and activity factor.

Example:

```text
profile 
__________________________________________________________________________________________________________ 
Hello hi! This is your profile: 
*====================================== 
	Name 				Lisa 
	Height 				159cm 
	Weight 				50.0kg 
	Gender 				F 
	Age 				21 
	Calories goal 		1500 cal 
	Activity factor 	2 
======================================* 
__________________________________________________________________________________________________________ 
```  

#### **Update name**

Change your name with `profile n/NAME`



#### **Update height:**

Change your height with `profile h/HEIGHT_IN_CM`.

❗ `HEIGHT_IN_CM` must be a positive number.




#### **Update weight:**

Change your weight with `profile w/WEIGHT_IN_KG`.

❗ `WEIGHT_IN_KG` must be a positive number.

#### Update gender:

Change your age with `profile s/GENDER`

❗ `GENDER` must be either `M` or `F` (case insensitive).

#### Update age:

Change your age with `profile a/AGE`

❗ `AGE` must be a positive number.

#### Update calorie goal

Change your calorie goal with `profile g/GOAL_IN_CAL`

#### Update activity factor

Change your activity factor with `profile x/ACTIVITY_FACTOR`

❗ `ACTIVITY_FACTOR` must be a number between 1 and 5.


💡 **Tip:** Use `profile n/NAME h/HEIGHT w/WEIGHT s/GENDER a/AGE g/CALORIE_GOAL x/ACTIVITY_FACTOR` to set all the attributes at once. You need at least one parameter and you can type it in _any_ order you'd like.

_Format:_
- `profile {n/NAME} {h/HEIGHT} {w/WEIGHT} {s/GENDER} {a/AGE} {g/CALORIE_GOAL} {x/ACTIVITY_FACTOR}`


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
bmi
__________________________________________________________________________________________________________
Your BMI value according to your current profile is:
	15.5 (Underweight)
__________________________________________________________________________________________________________

bmi h/170 w/65  
__________________________________________________________________________________________  
The calculated BMI value is 20.1 (Healthy)
__________________________________________________________________________________________  
```  

### **Add** **Exercise and Food Items:** `add`

Adds food or exercise record to the current list.

Format:

- `add f/ITEM c/CALORIES` adds a food item consumed with its respective calories.
- `add e/ITEM c/CALORIES` adds an exercise with its respective calories burnt.

Examples:

- `add f/chicken rice c/607` adds record of food consumed: chicken rice with 607 calories gained.

```text  
add f/chicken rice c/607  
__________________________________________________________________________________________  
A food item has been added:  
 chicken rice (607 cal)__________________________________________________________________________________________  
```  

- `add e/hiit c/290` adds record of exercise done: hiit with 290 calories burnt.

```text  
add e/hiit c/290  
__________________________________________________________________________________________  
An exercise has been added:  
 hiit (290 cal)__________________________________________________________________________________________  
```  

### **View** **Exercise and Food Items:** `view`

Views all the food or exercises added.

Format:

- `view f/` views all the food and the calories added to the list.

```text  
view f/  
__________________________________________________________________________________________  
You have consumed 4 food item(s):  
 1. chicken rice (607 cal) 2. yong tau foo (536 cal) 3. mcspicy alacarte (528 cal) 4. char kway teow (744 cal)Total calories consumed: 2415  
__________________________________________________________________________________________  
```  

- `view e/` views all the exercises and the calories added to list.

```text  
view e/  
__________________________________________________________________________________________  
You have done 2 exercise(s):  
 1. hiit (290 cal) 2. biking (500 cal)Total calories burnt: 790  
__________________________________________________________________________________________  
```  

- `view` views all food and exercises in the list and their respective calories.

```text  
view  
__________________________________________________________________________________________  
You have consumed 4 food item(s):  
 1. chicken rice (607 cal) 2. yong tau foo (536 cal) 3. mcspicy alacarte (528 cal) 4. char kway teow (744 cal)Total calories consumed: 2415  
  
You have done 2 exercise(s):  
 1. hiit (290 cal) 2. biking (500 cal)Total calories burnt: 790  
__________________________________________________________________________________________  
```  

### **Delete** **Exercise and Food Items:** `delete`

Deletes entry of food or exercise added.

Format:

`delete f/LIST_NO.` deletes the n<sup>th</sup> item in the food list.

`delete e/LIST_NO.` deletes the n<sup>th</sup> item in the exercise list.

`delete f/all` deletes all the food items in the food list.

`delete e/all` deletes all the exercise items in the exercise list.

❗ `LIST_NO.` must be a positive integer within the range of the number of items in the list.

```text  
delete f/2  
__________________________________________________________________________________________  
A food item has been deleted:  
 yong tau foo (536 cal) Number of food item(s) left: 3__________________________________________________________________________________________  
delete e/1  
__________________________________________________________________________________________  
You have removed the exercise:  
 hiit (290 cal)Number of exercise item(s) left: 1  
__________________________________________________________________________________________  
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
add|`add f/ITEM c/CALORIE`  `add e/ITEM c/CALORIES` | `add f/chicken rice c/607`, `add e/hiit c/290`
bmi|`bmi h/HEIGHT_IN_CM w/WEIGHT_IN_KG` `bmi`|`bmi h/170 w/65` , `bmi`
bye|`bye`|
delete|`delete f/LIST_NO.`  `delete e/LIST_NO.`  `delete f/all`  `delete e/all` |`delete f/1`, `delete e/2`, `delete f/all`,  `delete e/all`
goal |`goal CALORIE_IN_CAL` | `goal 2000`
height|`height HEIGHT_IN_CM` | `height 170`
help | `help`|
name|`name NAME`|`height 170`
overview|`overview`|
profile|`profile h/HEIGHT_IN_CM w/WEIGHT_IN_KG n/NAME g/CALORIE_IN_CAL` `profile`|`profile h/170 w/65 n/John g/2000`, `profile`
view|`view`|`view e/`,  `view f/`, `view`
weight|`weight WEIGHT_IN_KG`|`weight 65`