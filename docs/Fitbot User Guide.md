# Fitbot User Guide

Fitbot is a **desktop app** that helps university students who are looking to **keep track of their calorie consumption and calorie output** with the speed and convenience of **command-line based** tools, especially in times of online school.

## Quick Start

1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest `FitBot_v1.0.jar` from the cloud.
3. Copy the file to the folder you want to use as the home folder for your FitBot.
4. Go to command prompt and change the directory to the file's location.
5. Type `java -jar FitBot_v1.0.jar` into the command prompt and press enter to start the program.

## **Features**

ℹ️  ***Command Format***

- Commands are not case sensitive (e.g `help`, `HELP`,`hElP` are all able to execute the `help` command)
- Words in upper case (e.g`UPPER_CASE`) are known as parameters; values to be provided by users
- Parameters in square brackets are optional.
    
    e.g `bmi [h/HEIGHT_IN_CM w/WEIGHT_IN_KG]`  can be `bmi` or `bmi h/150 w/70`
    

### **Viewing help:** `help`

Shows a list of commands and their usage.

Format: `help`

Example:

```java
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

### **Input/Updates name: `name`**

Inputs/Updates name of user

Format: `name NAME`

Example:

- `name John`

```java
name John
__________________________________________________________________________________________
Your name has been updated!
Hello John!
__________________________________________________________________________________________
```

### **Input/Updates height:** `height`

Inputs/Updates height of user in centimetres

Format: `height HEIGHT_IN_CM`

- Records height of user

❗ `HEIGHT_IN_CM` must be a positive number.

Example:

- `height 170`

```java
height 170
__________________________________________________________________________________________
Your height has been updated.
Your height is 170.0cm.
__________________________________________________________________________________________
```

### **Input/Updates weight:** `weight`

Inputs/Updates weight of user in kilograms.

Format: `weight WEIGHT_IN_KG`

- Records weight of user

❗ `WEIGHT_IN_KG` must be a positive number.

Example:

- `weight 65`

```java
weight 65
__________________________________________________________________________________________
Your weight has been updated.
Your weight is 65.0kg.
__________________________________________________________________________________________
```

### Input net calorie goal: `goal`

Inputs net calorie goal of user in calorie.

Format: `goal CALORIE_IN_CAL`

- Records net calorie goal of user

❗`CALORIE_IN_CAL` must be an integer.

Example:

- `goal 2000`

```java
goal 2000
__________________________________________________________________________________________
Your goal has been set!
Current net calorie goal per day: 2000
__________________________________________________________________________________________
```

💡  **Tip:** Uses `profile` to add name, height, weight and calorie goal at the same time 

Format: `profile n/NAME h/HEIGHT_IN_CM w/WEIGHT_IN_KG g/CALORIE_IN_CAL`  

```java
profile n/John h/170 w/65 g/2000
__________________________________________________________________________________________
Hello John! Your profile has been created!
				Your height is 170.0cm.
        Your weight is 65.0kg.
        Your calories goal is 2000 cal.
__________________________________________________________________________________________
```

### Updates/View the profile details: `profile`

Updates/View the name, height, weight and the calorie goal values.

Format: `profile h/HEIGHT_IN_CM w/WEIGHT_IN_KG n/NAME g/CALORIE_IN_CAL`  `profile`

❗ `HEIGHT_IN_CM` and `WEIGHT_IN_KG` must be positive numbers.

❗`CALORIE_IN_CAL` must be an integer.

Example: 

- `profile n/John Doe w/65 /h170 g/2000` creates a profile with name John Doe of height 171.2cm and 65.3kg with a calorie goal of 2000 calories.
- `profile` displays your profile details.

```bash
profile n/John Doe w/65 h/170 g/2000

__________________________________________________________________________________________________________
Hello John Doe! Your profile has been created!
				Your height is 170 cm.
				Your weight is 65 kg.
				Your calories goal is 2000 cal.
__________________________________________________________________________________________________________
profile
__________________________________________________________________________________________________________
Hello! This is your current profile:
        Your name is John.
        Your height is 171 cm.
        Your weight is 65 kg.
        Your calorie goal is 2000 cal.
__________________________________________________________________________________________________________
```

### **BMI calculator:** `bmi`

Calculates the BMI value based on the user's input height and weight. If the user does not provide the values, the BMI will be computed based on the user's current height and weight in the profile. It also outputs the weight status *(underweight, healthy, overweight, obese)* based on calculated BMI.

Format: `BMI h/HEIGHT_IN_CM w/WEIGHT_IN_KG`  

❗ `HEIGHT_IN_CM` and `WEIGHT_IN_KG` must be positive numbers.

Example:

- `bmi` calculates the BMI value based on the user's height and weight in his or her profile
- `bmi h/170 w/65` calculates the BMI value based on height 170cm and weight 65cm

```java
bmi h/170 w/65
__________________________________________________________________________________________
Your BMI value is 22.5 (Healthy)
__________________________________________________________________________________________
```

### **Adding** **Exercise and Food Items:** `add`

Adds food or exercise record to the current list.

Format:

- `add f/ITEM c/CALORIES` adds a food item consumed with its respective calories
- `add e/ITEM c/CALORIES` adds an exercise with its respective calories burnt

Examples: 

- `add f/chicken rice c/607` adds record of food consumed: chicken rice with 607 calories gained

```java
add f/chicken rice c/607
__________________________________________________________________________________________
A food item has been added:
				chicken rice (607 cal)
__________________________________________________________________________________________
```

- `add e/hiit c/290` adds record of exercise done: hiit with 290 calories burnt

```java
add e/hiit c/290
__________________________________________________________________________________________
An exercise has been added:
	      hiit (290 cal)
__________________________________________________________________________________________
```

### **View** **Exercise and Food Items:** `view`

Views all the food or exercises added.

Format:

- `view f/` views all the food and the calories added to the list

```bash
view f/
__________________________________________________________________________________________
You have consumed 4 food item(s):
	      1. chicken rice (607 cal)
	      2. yong tau foo (536 cal)
	      3. mcspicy alacarte (528 cal)
	      4. char kway teow (744 cal)
Total calories consumed: 2415
__________________________________________________________________________________________
```

- `view e/` views all the exercises and the calories added to list

```bash
view e/
__________________________________________________________________________________________
You have done 2 exercise(s):
	      1. hiit (290 cal)
        2. biking (500 cal)
Total calories burnt: 790
__________________________________________________________________________________________
```

- `view` views all food and exercises in the list and their respective calories

```bash
view
__________________________________________________________________________________________
You have consumed 4 food item(s):
	      1. chicken rice (607 cal)
	      2. yong tau foo (536 cal)
      	3. mcspicy alacarte (528 cal)
      	4. char kway teow (744 cal)
Total calories consumed: 2415

You have done 2 exercise(s):
	      1. hiit (290 cal)
        2. biking (500 cal)
Total calories burnt: 790
__________________________________________________________________________________________
```

### **Delete** **Exercise and Food Items:** `delete`

Deletes entry of food or exercise added.

Format: 

`delete f/LIST_NO.` deletes the $n^{th}$ item in the food list 

`delete e/LIST_NO.` deletes the $n^{th}$ item in the exercise list  

`delete f/all` deletes all the food items in the food list

`delete e/all` deletes all the exercise items in the exercise list

❗ `LIST_NO.` must be a **positive integer** within the range of the number of items in the list  

```bash
view 
__________________________________________________________________________________________
You have consumed 4 food item(s):
	      1. chicken rice (607 cal)
	      2. yong tau foo (536 cal)
	      3. mcspicy alacarte (528 cal)
      	4. char kway teow (744 cal)
Total calories consumed: 2415

You have done 2 exercise(s):
      	1. hiit (290 cal)
        2. biking (500 cal)
Total calories burnt: 790
__________________________________________________________________________________________

delete f/2
__________________________________________________________________________________________
A food item has been deleted:
	      yong tau foo (536 cal)
        Number of food item(s) left: 3
__________________________________________________________________________________________
delete e/1
__________________________________________________________________________________________
You have removed the exercise:
	      hiit (290 cal)
Number of exercise item(s) left: 1
__________________________________________________________________________________________
view 
__________________________________________________________________________________________
You have consumed 3 food item(s):
      	1. chicken rice (607 cal)
      	2. mcspicy alacarte (528 cal)
      	3. char kway teow (744 cal)
Total calories consumed: 1879

You have done 1 exercise(s):
        1. biking (500 cal)
Total calories burnt: 500
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

```bash
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

Exits the program and save results from food and exercise items to the text file.

Format: `bye`

```bash
bye
__________________________________________________________________________________________
Exiting Fitbot....
Bye! Hope to see you again soon!!
__________________________________________________________________________________________
```

### Command Summary

[ ](https://www.notion.so/1dc3051568c540aa87886dbb5e3ae34f)