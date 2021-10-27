
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

[2. Set Up Profile](#2-set-up-profile)

[3. Terminology](#3-terminology)

[4. Features](#4-features)
- [4.1 Customising your profile](#41-customising-your-profile)
  - [4.1.1 Setting up your profile](#412-setting-up-your-profile) (Link back to 2)
  - [4.1.2 Viewing Profile `profile`](#413-viewing-profile-profile)
  - [4.1.3 Updating Profile Attributes](#414-updating-profile-attributes)
  - [4.1.4 Calculating BMI `bmi`](414-calculating-bmi-bmi)
- [4.2 Recording your food consumption](#42-recording-your-food-consumption)
  - [4.2.1 Adding Food Items `add f/`](#421-adding-food-items) (Tip: food bank)
  - [4.2.2 Viewing Food List `view f/`](#422-viewing-food-list-view-f)
  - [4.2.3 Deleting Food Items `delete f/`](#423-deleting-food-items-delete-f)
- [4.3 Recording your exercises](#43-recording-your-exercises)
  - [4.3.1 Adding Exercise Items `add e/`](#431-adding-exercise-items-add-e)  (Tip: exercise bank, upcoming exercise)
  - [4.3.2 Viewing Exercise Items `view e/` `view u/`](#432-viewing-exercise-items-view-e-view-u)
  - [4.3.3 Deleting Exercises `delete e/`](#433-deleting-exercises-delete-e)
- [4.4 Scheduling your exercises](#44-scheduling-your-exercises)
  - [4.4.1 Adding Upcoming Exercise Items `add e/`](#441-adding-upcoming-exercise-items-add-e)
  - [4.4.2 Adding Recurring Exercise Items `add r/`](#442-adding-recurring-exercise-items-add-r)
  - [4.4.3 Viewing Upcoming Exercise List `view u/`](#443-viewing-upcoming-exercise-list-view-u)
  - [4.4.4 Editing Upcoming Exercise Items `edit u/`](#444-editing-upcoming-exercise-items-edit-u)
  - [4.4.5 Deleting Upcoming Exercise Items `delete u/`](#445-deleting-upcoming-exercise-items-delete-u)
- [4.5 Building your Food Bank](#45-building-your-food-bank)
  - [4.5.1 Adding Food Bank Items `add fbank/`](#451-adding-food-bank-item-add-fbank)
  - [4.5.2 Viewing Food Bank `view fbank/`](#452-viewing-food-bank-view-fbank)
  - [4.5.3 Editing Food Bank Items `edit fbank/`](#453-editing-food-bank-items-edit-fbank)
  - [4.5.4 Deleting Food Bank Items `delete fbank/`](#454-deleting-food-bank-items-delete)
- [4.6 Building your Exercise Bank](#46-building-your-exercise-bank)
  - [4.6.1 Adding Exercise Bank Items `add ebank/`](#47-adding-exercise-bank-items-add-ebank)
  - [4.6.2 Viewing Exercise Bank `view ebank/`](#462-viewing-exercise-bank-view-ebank)
  - [4.6.3 Editing Exercise Bank Items `edit ebank/`](#463-editing-exercise-bank-items-edit-ebank)
  - [4.6.4 Deleting Exercise Bank Items `delete ebank/`](#464-deleting-exercise-bank-items-delete-ebank)
- [4.7 Viewing your Calorie Summary `overview`](#47-viewing-your-calorie-summary-overview)
- [4.8 Viewing help `help`](#48-viewing-help-help)
- [4.9 Exiting Program `bye`](#49-exiting-program-bye)

[5. Data Management](#5-data-management)
- [5.1 Saving Data](#51-saving-data)
- [5.2 Editing Data](#52-editing-data)

[6. FAQ](#6-faq)

[7. Command Summary](#7-command-summary)




## 1. Quick Start

1. Ensure you have Java 11 or above installed in your computer.  If not, install Java 11 [here](https://www.oracle.com/java/technologies/downloads/).
2. Download the latest version of [Fitbot.jar](https://github.com/AY2122S1-CS2113T-F14-2/tp/releases/tag/v1.0) from the cloud.
3. Copy the file to the folder you want to use as the home folder for your _Fitbot_.
4. Go to command prompt and change the directory to the file's location. [Not sure how to do this?](https://www.groovypost.com/howto/open-command-window-terminal-window-specific-folder-windows-mac-linux/)
5. Type `java -jar FitBot.jar` into the command prompt and press enter to start the program.

If successfully loaded, you will see this logo:

![Start Up Screen](./images/StartupLogo.png)

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

### 4.1 Customizing your profile:

You can customize your profile with the following steps.

#### 4.1.1 Setting up your profile

Refer to [2. Set Up Profile](#2-set-up-profile) to set up your profile.

#### 4.1.2 Viewing profile: `profile`

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
#### 4.1.3 Updating profile attributes 

Change your profile attributes with the following command: \
`profile {n/NAME} {h/HEIGHT} {w/WEIGHT} {s/GENDER} {a/AGE} {g/CALORIE_GOAL} {x/ACTIVITY_FACTOR}` \
\
where : \
\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; n/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; name \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; h/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; height in cm \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; w/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;weight in kg \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; a/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; age \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; g/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; calorie goal \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; s/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; gender (M or F) \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; x/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; activity factor (1: Sedentary - 5: Extremely Active)

where you will require at least 1 of the optional parameters (in no particular order) to change your attribute!



### 4.2 Recording your food consumption

You can record all the food items consumed within a week into the food list. This allows you
to keep track of your food calorie consumption and gain a better understanding of your diet habit. 

#### 4.2.1 Adding Food Items `add f/`

This command allows you to add a new Food Item to the Food List.

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
💡 **Tip:** If you do not specify the date and time of the Food Item, it is assumed that the date and time is based on the date and time of input.

💡 **Tip:** It is possible to add Food Item without providing the calorie of it. You can do this by saving the corresponding 
Food Item into the Food Bank. More details can be found at [Section 4.5](#45-building-your-food-bank).

❗ The input date `DD-MM-YYYY` must within 7 days before today. For example, if today date is `20-10-2021`, then the input
date must be within `13-10-2021` to `20-10-2021`.

#### 4.2.2 Viewing Food List `view f/`

You may want to view how many calories you have taken in this week. This command will show a list of Food Items and 
their calories added within 7 days (including today) from the list.

Format: `view f/`

Example: 

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

#### 4.2.3 Deleting Food Items `delete f/`

When you mistakenly add a wrong Food Item inside your Food List, there is no need to worry! The command allows you to 
remove a specific Food Item in the Food List.

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

### 4.3 Recording your exercises

Besides Food Items, you may also record Exercises that you have taken into the Exercise List. This will allow you to keep
track of total calories burnt and check whether you have done sufficient exercises. 

#### 4.3.1 Adding Exercise Items `add e/`(Tip: exercise bank, upcoming exercise)

By using this command, you can add any Exercise Items done into the Exercise List.

Format: `add e/ITEM {c/CALORIES} {d/DD-MM-YYYY}` adds an Exercise Item with its respective calories burnt on the given date
(`DD-MM-YYYY`).

ℹ️ If the date `DD-MM-YYYY` provided is in the future, this exercise will be treated as an Upcoming Exercise Item, 
and it will be added to the [Upcoming Exercise List](#441-adding-upcoming-exercise-items-add-e) instead.

💡 **Tip:** Similar to [Food Item](#421-adding-food-items-add-f), if you do not specify the date and time of the Exercise Item, 
it is assumed that the date and time is based on the date and time of input.

💡 **Tip:** It is possible to add Exercise Item without providing the calorie burnt on it. You can do this by saving the corresponding
Exercise Item into the Exercise Bank. More details can be found at [here](#46-building-your-exercise-bank).

❗ The input date `DD-MM-YYYY` must within 7 days before today. For example, if today date is `20-10-2021`, then the input
date must be within `13-10-2021` to `20-10-2021`.

Example:
- `add e/hiit c/290 d/21-10-2021` adds record of exercise done: hiit with 290 calories burnt on 21 Oct 2021 to the exercise list.

```text
add e/hiit c/290 d/ d/21-10-2021
__________________________________________________________________________________________
An exercise has been added:
    hiit (290 cal) @ 21 Oct 2021
__________________________________________________________________________________________
```

#### 4.3.2 Viewing Exercise Items `view e/` `view u/`

It is possible to view and check all the exercises you have added. This command is for you to view all the exercises taken 
and the calories that are added within the past 7 days (including today) from the list.

Format: `view e/`

Example: 

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
ℹ️It is also possible to view all the Upcoming Exercises from the Upcoming Exercise List that you have added before. More
details can be found [here](#443-viewing-upcoming-exercise-list-view-u).

#### 4.3.3 Deleting Exercises `delete e/`

This command allows you to remove any Exercise from the Exercise List just in case you add the wrong Exercise. 

Format: `delete e/LIST_NO. d/DD-MM-YYYY` deletes the *n<sup>th</sup>* exercise Item in the exercise list which contains the date (`DD-MM-YYYY`),
where *n* is the index of the exercise to delete.

Example:
```
delete e/1 d/24-10-2021
__________________________________________________________________________________________________________
You have removed the exercise:
    hiit (290 cal) @ 24 Oct 2021
Number of exercise item(s) left: 1

__________________________________________________________________________________________________________
```

💡   **Tip:** If you wish to remove all the Exercise Items from the Exercise List, there is a shortcut command: `delete e/all`.

```
delete e/all
__________________________________________________________________________________________________________
All exercise items have been removed.
__________________________________________________________________________________________________________
```


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

#### 4.5.2. View Exercise List:

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


#### 4.6.2 Delete an Exercise Item from Exercise List


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
Advanced users are welcome to update data directly by editing the data files. 

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




