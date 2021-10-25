
# Fitbot User Guide

## Introducing _Fitbot_
_Fitbot_ is a **desktop app** that helps university students who are looking to
**keep track of their calorie consumption and calorie output** with the speed and convenience of
**command-line based** tools, especially in times of online school.

_Fitbot_ can be used across all operating systems such as Windows, Mac OS X, Linux and Unix.

If you need an easy and fast way to record your calories, _Fitbot_ is the app for you! 💪💯

## About This User Guide
Welcome to the _Fitbot_ User Guide! \
This guide explains how you can use all the features available on _Fitbot_ and
maximise your user experience.


Throughout this guide, we will be using some special formatting and symbols to bring your attention to certain aspects:

**abc**: Text that has been **bolded** indicates that it is important. \
`abc`: Text with a grey highlight indicates that it is a code that can be typed by you into the command line or shown from _Fitbot_.\
ℹ️: This symbol indicates important information. \
❗: This symbol indicates **important** rules to follow. Make sure you pay extra attention to the information, else _Fitbot_ will fail to execute certain functions! \
💡: This symbol indicates tips and tricks that you can use to make your _Fitbot_ experience even smoother.




## Content Page

[1. Quick Start](#1-quick-start)

[2. Create Profile](#2-create-profile)

[3. Terminology](#3-terminology)

[4. Features](#4-features)
- [4.1. View help: `help`](#41-view-help-help)
- [4.2. View and edit your profile details: `profile`](#42-view-and-edit-your-profile-details-profile)
  - [4.2.1. View profile](#421-view-profile)
  - [4.2.2. Updating your attributes](#422-updating-your-profile-attributes)
- [4.3. Calculate BMI: `bmi`](#43-calculate-bmi-bmi)
- [4.4. Add Items: `add`](#44-add-items-add)
  - [4.4.1. Add a Food Item to Food List](#441-add-a-food-item-to-food-list)
  - [4.4.2. Add an Exercise Item to Exercise List](#442-add-an-exercise-item-to-exercise-list)
  - [4.4.3. Add a Recurring Exercise to the Upcoming Exercise List](#443-add-a-recurring-exercise-to-the-upcoming-exercise-list)
  - [4.4.4. Add a Food Item to Food Bank](#444-add-a-food-item-to-foodbank)
  - [4.4.5. Add an Exercise Item to Exercise Bank](#445-add-an-exercise-item-to-exercise-bank)
- [4.5. View Items: `view`](#45-view-items-view)
  - [4.5.1 View Food List](#451-view-food-list)
  - [4.5.2 View Exercise List](#452-view-exercise-list)
  - [4.5.3 View Upcoming Exercise List](#453-view-upcoming-exercise-list)
- [4.6. Delete Items: `delete`](#46-delete-items-delete)
  - [4.6.1. Delete a Food Item from Food List](#461-delete-a-food-item-from-food-list)
  - [4.6.2. Delete an Exercise Item from Exercise List](#462-delete-an-exercise-item-from-exercise-list)
  - [4.6.3. Delete an Upcoming Exercise from Upcoming Exercise List](#463-delete-an-upcoming-exercise-item-from-upcoming-exercise-list)
  - [4.6.4. Delete a Food Item from Food Bank](#464-delete-a-food-item-from-food-bank)
  - [4.6.5. Delete a Exercise Item from Exercise Bank](#465-delete-an-exercise-item-from-exercise-bank)
- [4.7. Edit Items: `edit`](#47-edit-items-edit)
  - [4.7.1. Edit Upcoming Exercise List](#471-edit-upcoming-exercise-list)
  - [4.7.2. Edit Food Bank](#472-edit-food-bank)
  - [4.7.3. Edit Exercise Bank](#473-edit-exercise-bank)
- [4.8. Calculate net calories: `overview`](#48-calculate-net-calories-overview)
- [4.9. Exit the program: `bye`](#49-exit-the-program-bye)
- [4.10. Saving the data](#410-saving-the-data)
- [4.11. Editing the data file](#411-editing-the-data-file)

[5. FAQ](#5-faq)

[6. Command Summary](#6-command-summary)


## 1. Quick Start

1. Ensure you have Java 11 or above installed in your computer.  If not, install Java 11 [here](https://www.oracle.com/java/technologies/downloads/).
2. Download the latest version of [Fitbot.jar](https://github.com/AY2122S1-CS2113T-F14-2/tp/releases/tag/v1.0) from the cloud.
3. Copy the file to the folder you want to use as the home folder for your _Fitbot_.
4. Go to command prompt and change the directory to the file's location. [Not sure how to do this?](https://www.groovypost.com/howto/open-command-window-terminal-window-specific-folder-windows-mac-linux/)
5. Type `java -jar FitBot.jar` into the command prompt and press enter to start the program.

If successfully loaded, you will see this logo:

![Untitled](./images/StartupLogo.png)

## 2. Create Profile

_Fitbot_ will detect if there is a profile present in the application. If you have not set up a profile,
_Fitbot_ will ask you for your particulars.


Particulars required include name, height, weight, age, gender, calorie goal and activity factor. The application will prompt for your particulars again if your input is incorrect.

After setting up the profile, you can start recording your food and exercises with the commands below.


## 3. Terminology

**_Calorie_** - A unit used to measure the energy of an item. One calorie is the amount of energy required to raise the
temperature of one gram of water by one degree Celsius. On average, a male will require approximately 2500 cal while a
female will require around 2000 cal per day.\
\
***Calorie goal*** - The amount of calorie you wish to consume per day. In our application, calorie goal is defined as the
total calorie intake from all the food consumed per day.

***Net Calorie*** - The difference between calorie
goal and the total calorie burnt from exercising and [body metabolism](https://www.news-medical.net/life-sciences/What-is-Metabolism.aspx).
For your information, we use [basal metabolic rate (BMR)](https://en.wikipedia.org/wiki/Basal_metabolic_rate) to compute
the rate of calorie consumed by body metabolic activity.\
\
***Activity factor*** - A coefficient or value used in the calculation of BMR to measure the level of activity. In our application, we use an integer ranged from 1 to 5 to measure the activity factor:
Little to no exercise (0-1 day of exercises a week)
Lightly active (1-3 days of exercises a week)
Moderately active (3-5 days of exercises a week)
Very active (6-7 days of exercises a week)
Extremely active (when sports is your passion and have a very physical job scope)
\
\
***Body Mass Index (BMI)*** - A measure to evaluate if the body weight is healthy. BMI is computed by using the body
weight(in kg) divided by the square of the body height(in m). \
\
***Item*** - We use the term **item** to represent the item that can be stored in our application. The available item
include **Food** and **exercise** only.\
\
***Upcoming exercise*** - We define the exercise with date after today's date as an upcoming exercise. This kind of exercise
will be handled specifically. It will not be added to the exercise list but will be saved internally in the storage file. More
details of the process can be found at [here](#add-a-recurring-exercise-to-the-upcoming-exercise-list).\
\
***Item Bank*** - An item storage that is capable to store the Food or exercise Item with its respective calorie. This is
to help you to store the calorie intake of a specific Food or the calorie burnt from a specific exercise for future use. More details
of the item bank can be found at [here](#add-a-food-item-to-foodbank).\
\
***Parameters*** - Parameters are values in the command format that _Fitbot_ expects you to provide.


## 4. Features

ℹ️ ***Command Format***

- Commands are not case-sensitive (e.g. `help`, `HELP`,`hElP` are all able to execute the `help` command)

- Words in upper case (e.g. `UPPER_CASE`) are used to signify parameters.

- Parameters in curly brackets `{}` are optional.

  e.g. `bmi {h/HEIGHT_IN_CM w/WEIGHT_IN_KG}` can be `bmi` or `bmi h/150 w/70`

- The order of the parameters do not matter.

  e.g. `add f/potato c/200` or `add c/200 f/potato` both adds a Food Item called "potato" with 200 calories.


❗ Please do not use the characters `/` and `|` in your input other than to specify parameters!

### 4.1 **View help:** `help`

This command shows a list of commands recognised by _Fitbot_ and their usage.

Format: `help`


### 4.2 View and edit your profile details: `profile`

#### 4.2.1 View Profile
Typing `profile` lets you view your name, height, weight, gender, age, calorie goal and activity factor.

Example:

```text
profile 
__________________________________________________________________________________________________________ 
Hello Lisa! This is your profile: 
*====================================== 
	Name                Lisa 
	Height              159cm 
	Weight              50.0kg 
	Gender              F 
	Age                 21 
	Calories goal       1500 cal 
	Activity factor     2 
======================================* 
__________________________________________________________________________________________________________ 
```  

#### 4.2.2 Updating your profile attributes:

Changing your attributes require at least one of these input parameters:
\
`profile {n/NAME} {h/HEIGHT} {w/WEIGHT} {s/GENDER} {a/AGE} {g/CALORIE_GOAL} {x/ACTIVITY_FACTOR}`
\
where:
\
`n/`&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; name
\
`h/`&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; height (cm)
\
`w/`&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; weight (kg)
\
`a/`&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; age
\
`g/`&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; calorie goal
\
`s/`&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; gender (M/F)
\
`x/`&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; activity factor (1: Sedentary - 5: Extra Active)



### 4.3. Calculate BMI: `bmi`

This command calculates your BMI value based on the height and weight values you provide.

It also displays the weight status *(underweight, healthy, overweight, obese)* based on calculated BMI.


Format: `bmi h/HEIGHT_IN_CM w/WEIGHT_IN_KG`

❗ `HEIGHT_IN_CM` and `WEIGHT_IN_KG` must be positive numbers.
\
💡 **Tip:** If you do not provide the values, the BMI will be computed based on your current height and weight as indicated in your profile.



Example:

- `bmi` calculates the BMI value based on your height and weight in your profile.
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


### 4.4. Add Items: `add`

This command adds an Item to a list.

#### 4.4.1. Add a Food Item to Food List

This command is used to add a Food Item consumed within the past 7 days to the Food List.

Format: `add f/ITEM {c/CALORIES} {d/DD-MM-YYYY} {t/HHMM}` adds a Food Item consumed with its respective calories on the given
date (`DD-MM-YYYY`) and time (`HHMM`).

Example:
- `add f/chicken rice c/607 d/21-10-2021 t/1400` adds record of food consumed: chicken rice with 607 calories gained on 21 Oct 2021 1400 to the food list.

```text
add f/chicken rice c/607 d/21-10-2021 t/1400
__________________________________________________________________________________________
A food item has been added:
    chicken rice (607 cal) @ 14:00, 21 Oct 2021
__________________________________________________________________________________________
```
💡  **Tip:** If you do not specify the date and time of the Food Item, it is assumed that the date and time is based on the date and time of input.


#### 4.4.2. Add an Exercise Item to Exercise List

Format: `add e/ITEM {c/CALORIES} {d/DD-MM-YYYY}` adds an Exercise Item with its respective calories burnt on the given date (`DD-MM-YYYY`). 

ℹ️ If `DD-MM-YYYY` is in the future, the Upcoming Exercise Item will be added to the Upcoming Exercise List instead.

Example:
- `add e/hiit c/290 d/21-10-2021` adds record of exercise done: hiit with 290 calories burnt on 21 Oct 2021 to the exercise list.
```text
add e/hiit c/290 d/ d/21-10-2021
__________________________________________________________________________________________
An exercise has been added:
    hiit (290 cal) @ 21 Oct 2021
__________________________________________________________________________________________
```

- `add e/hiit c/290 d/01-01-2041` adds record of exercise done: hiit with 290 calories burnt to the upcoming exercise list.
```text
add e/hiit c/290 d/01-01-2041
__________________________________________________________________________________________
An exercise item for the future has been added:
    hiit (290 cal)
```


💡  **Tip:** Similarly, if the date of the exercise is not provided, the date is assumed to be today.

#### 4.4.3. Add a Recurring Exercise to the Upcoming Exercise List

This command is used to schedule exercises that you do on a regular basis. This can be particularly useful if you have weekly trainings to record!
\
Format: `add r/ITEM c/CALORIES :/START_DATE -/END_DATE @/DAY_OF_THE_WEEK` adds recurring exercise with its respective calories burnt to the upcoming list.\
❗ `START_DATE` and `END_DATE` must be in the future.\
❗ `START_DATE` must be before `END_DATE`.\
❗ `START_DATE` and `END_DATE` must follow `DD-MM-YYYY` format.




#### 4.4.4. Add a Food Item to FoodBank

This command is used to keep a record of a Food Item and its associated calories.

Format: `add fbank/ITEM c/CALORIES`


#### 4.4.5. Add an Exercise Item to Exercise Bank

This command is used to keep a record of an exercise Item and its associated calories.

Format: `add ebank/ITEM c/CALORIES`



💡**Tip:** If you do not provide the calorie of the Food/Exercise Item for the Food List, Exercise List or 
the Upcoming Exercise List, _Fitbot_ will search the Food/Exercise Bank and retrieve the calorie value
from the corresponding Item Bank if the Item is found.
```
add f/chicken rice d/22-10-2021 t/1345
__________________________________________________________________________________________
A food item has been added:
    chicken rice (607 cal) @ 13:45, 22 Oct 2021
__________________________________________________________________________________________
```


### 4.5. **View Items:** `view`

This command is used to view the Items in your lists.

#### 4.5.1. View Food List

This command is used to view all the Food Items and their calories added within 7 days (including today) from the list.

Format: `view f/`



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
#### 4.5.2. View Exercise List: 

This command is used to view all the exercises taken and the calories that are added within the past 7 days (including today) from the list.

Format: `view e/`

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

#### 4.5.3. View Upcoming Exercise List
This command is used to view all the exercises and the calories added that are scheduled to happen in a future date.

Format: `view u/`

```text
view u/
__________________________________________________________________________________________
You have 2 upcoming exercise(s):
    1. hiit (200 cal) (Saturday 01 Jan 2022)
    2. running (300 cal) (Sunday 02 Jan 2022)
__________________________________________________________________________________________
```



### 4.6. Delete Items: `delete`

This command deletes an Item from a list.

#### 4.6.1 Delete a Food Item from Food List

Format: `delete f/LIST_NO. d/DD-MM-YYYY t/HHMM` deletes the *n<sup>th</sup>* Food Item in the Food List which has the date (`DD-MM-YYYY`)
and time (`HHMM`), where *n* is the index of the Food to delete.

❗ `LIST_NO.` must be a positive integer within the range of the number of Items in the list.

Example:
```text
delete f/1 d/22-10-2021 t/1000
__________________________________________________________________________________________________________
A food item has been deleted:
	donut x2 (607 cal) @ 10:00, 22 Oct 2021
Number of food item(s) left: 6
__________________________________________________________________________________________________________
```
💡  **Tip:** If you wish to remove all the Food Items from the list, there is a shortcut command: `delete f/all`.
```
delete f/all
__________________________________________________________________________________________________________
All food items have been removed.
__________________________________________________________________________________________________________
```

#### 4.6.2 Delete an Exercise Item from Exercise List

Format: `delete e/LIST_NO. d/DD-MM-YYYY` deletes the *n<sup>th</sup>* exercise Item in the exercise list which contains the date (`DD-MM-YYYY`),
where *n* is the index of the exercise to delete.


Example:
```
delete e/1 d/24-10-2021
__________________________________________________________________________________________________________
You have removed the exercise:
    hiit (290 cal) @ 24 Oct 2021
Number of exercise item(s) left: 1

__________________________________________________________________________________________
```

💡   **Tip:** If you wish to remove all the Exercise Items from the Exercise List, there is a shortcut command: `delete e/all`.
```
delete e/all
__________________________________________________________________________________________________________
All exercise items have been removed.
__________________________________________________________________________________________________________
```

#### 4.6.3 Delete an Upcoming Exercise Item from Upcoming Exercise List

Format: `delete u/LIST_NO.` deletes the n<sup>th</sup> upcoming exercise Item in the upcoming exercise list, where *n* is the index of the exercise to delete.

Example:
```
delete u/1
__________________________________________________________________________________________
An exercise item for the future has been deleted:
    running (300 cal)
Number of exercise item(s) left: 1
__________________________________________________________________________________________
```
💡   **Tip:** If you wish to remove all the Exercise Items from the Upcoming Exercise List, there is a shortcut command: `delete u/all`.

```
delete u/all
__________________________________________________________________________________________________________
All future exercise items have been removed.
__________________________________________________________________________________________________________
```



#### 4.6.4 Delete a Food Item from Food Bank

This command deletes a Food Item from the Food Bank.


Format: `delete fbank/LIST_NO.` deletes the n<sup>th</sup> Food Item in the Food Bank, where *n* is the index of the Food to delete.



#### 4.6.5. Delete an Exercise Item from Exercise Bank

This command deletes an Exercise Item from the Exercise Bank.


Format: `delete ebank/LIST_NO.` deletes the n<sup>th</sup> Exercise Item in the Exercise Bank, where *n* is the index of the Exercise to delete.

❗ `LIST_NO.` must be a positive integer within the range of the number of Items in the list. 



### 4.7. **Edit Items**: `edit`

This command is used to edit any record of Items that you have previously entered into the Food bank, exercise bank or upcoming exercise list.


#### 4.7.1. Edit Upcoming Exercise List

This command is used to edit Exercise Items in the Upcoming Exercise List.


Format: `edit u/LIST_NO. {n/NEW_NAME} {c/NEW_CALORIES} {d/NEW_DATE}` edits the n<sup>th</sup> item in the Upcoming Exercise List, where *n* is the index of the Exercise to edit.


#### 4.7.2. Edit Food Bank

Format:  `edit fbank/LIST_NO. {n/NEW_NAME} {c/NEW_CALORIES}` edits the n<sup>th</sup> item in the Food Bank, where *n* is the index of the Food to edit.


#### 4.7.3. Edit Exercise Bank

Format: `edit ebank/LIST_NO. {n/NEW_NAME} {c/NEW_CALORIES}` edits the n<sup>th</sup> item in the Exercise Bank, where *n* is the index of the Exercise to edit.

❗ `NEW_DATE` must be a date after today.

❗ `LIST_NO.` must be a positive integer within the range of the number of Items in the list. 

❗ While all the parameters are optional, please specify **at least one** parameter so that _Fitbot_ knows which attribute you would like to update.

```
edit fbank/1 c/50
__________________________________________________________________________________________________________
Food bank item number 1 has been changed to:
  potato (50 cal)
__________________________________________________________________________________________________________
````



### 4.8. **Calculate net calories**: `overview`

This command is used to view the summary of the calories gained from food, calories burnt from exercises as well as
net calories after including BMR.

Format: `overview`

```text
overview
__________________________________________________________________________________________
-*OVERVIEW*-
Hi user, this is your calorie summary for the week.

Food:
You have consumed 0 cal this week from 18-Oct to 24-Oct.
Calorie gained from food (Daily)
18-Oct       0
19-Oct       0
20-Oct       0
21-Oct       0
22-Oct       0
23-Oct       0
24-Oct       0
You have lost 0 cal from exercising for the last 7 days.
Calorie burnt from exercise (Daily)
18-Oct       0
19-Oct       0
20-Oct       0
21-Oct       0
22-Oct       0
23-Oct       0
24-Oct       0

Daily net calories**:
18-Oct :   -927
19-Oct :   -927
20-Oct :   -927
21-Oct :   -927
22-Oct :   -927
23-Oct :   -927
24-Oct :   -927


Number of supper meals this week: 0

 ** The net calorie calculation includes calories gained from food, calories burnt from exercises
  and daily normal activities (using BMR). All calculations uses the latest values updated in profile.
__________________________________________________________________________________________
```


### 4.9. Exit the program: `bye`

This command is used to exit _Fitbot_.

Format: `bye`

```text  
bye  
__________________________________________________________________________________________  
Exiting Fitbot....  
Bye! Hope to see you again soon!!  
__________________________________________________________________________________________  
```  

### 4.10. Saving the data

There is no need to save manually. Any updates made to the data will be automatically stored into the local drive and reloaded when _Fitbot_ is restarted.

### 4.11. Editing the data file

_Fitbot_ data files are saved as .text files `<JAR file location>/data/<text file name>.txt`. \
Advanced users are welcome to update data directly by editing the data files. \

❗ If your changes to the data files format are invalid, _Fitbot_ will skip the wrongly formatted line when it loads in the data.

❗ Ensure that you quit _Fitbot_ if you would like to edit the files. Edits made directly to files when _Fitbot_ is running will not be saved.

### 5. FAQ

**Q:** How do I transfer my data to another computer?\
**A:** Zip the folder with _Fitbot_ and its data files, and transfer to the new computer. Extract the zipped folder onto your new computer and follow steps 1, 4 and 5 in [Quick Start](#1-quick-start) to get your _Fitbot_ running on your new computer.

**Q:** How many profiles can I create?\
**A:** _Fitbot_ only supports 1 profile. If you need to make any changes to your current profile, you can refer to [Updating your profile attributes](#422-updating-your-profile-attributes).

### 6. Command Summary


| Action | Format | Examples |
|---------|----------|-------|
add|`add f/ITEM {c/CALORIE} {d/DD-MM-YYYY} {t/HHMM}` <br>`add e/ITEM {c/CALORIES} {d/DD-MM-YYYY}` <br> `add r/ITEM c/CALORIES :/START_DATE -/END_DATE @/DAY_OF_THE_WEEK` <br>  `add fbank/ITEM c/CALORIES` <br>  `add ebank/ITEM c/CALORIES`| `add f/chicken rice c/607 d/20-10-2021`, <br>`add e/hiit c/290 d/23-10-2021` <br> `add r/handball training c/432 :/24-10-2021 -/11-11-2021 @/13` <br>  `add fbank/chicken rice c/667`<br> `add ebank/5km run c/478`
bmi|`bmi h/HEIGHT_IN_CM w/WEIGHT_IN_KG` <br> `bmi`|`bmi h/170 w/65` , <br> `bmi`
bye|`bye`|
delete|`delete f/LIST_NO. d/DD-MM-YYYY t/HHMM` <br> `delete e/LIST_NO. d/DD-MM-YYYY` <br> `delete f/all` <br> `delete e/all` |`delete f/1`, <br> `delete e/2`, <br> `delete f/all`, <br>  `delete e/all`
edit| `edit u/LIST_NO. {n/NEW_NAME} {c/NEW_CALORIES} {d/NEW_DATE}` <br> `edit fbank/LIST_NO. {n/NEW_NAME} {c/NEW_CALORIES}` <br> `edit ebank/LIST_NO. {n/NEW_NAME} {c/NEW_CALORIES}`| `edit u/1 n/volleyball training c/560 d/24-10-2021` <br> `edit fbank/1 n/2.4km run c/267` <br> `edit ebank/1 n/chicken rice c/800`
help | `help`|
overview|`overview`|
profile|`profile {h/HEIGHT_IN_CM} {w/WEIGHT_IN_KG} {n/NAME} {s/GENDER} {a/AGE} {g/CALORIE_IN_CAL} {x/ACTIVITY_FACTOR}` <br> `profile`|`profile h/170 w/65 n/John a/22 s/M g/500 x/2`, <br> `profile`
view|`view`|`view e/`,  <br> `view f/`, <br>  `view fbank/`, <br> `view ebank/`, <br> `view u/`




