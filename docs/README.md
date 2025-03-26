# This is a chatbot known as Allmind

## What is Allmind?

Allmind is a simple chatbot that is here to help you keep track of tasks.

Tasks have 3 types,todo, deadline and event.todo just tracks the name of the task and its marked status.deadline has those but also adds on a by date and time which states when the task has to be done by.
Lastly,event has the same stuff as todo but also adds on a from date and time to a to date and time.

Tasks can be added to or removed from the list,these same tasks can also be marked or unmarked in order to indicate whether the task is done or not done respectively.Every time a task is added,deleted 
or has its mark status changed,the task is added,removed or has its mark status changed in the text file that stores the task list.There is also a find function that will give all tasks that contains the given input in order.


# Commands

## list

This command will print out all the tasks that are currently added to the task list already.

## todo taskName

This command will add the taskName as a new todo Task at the end of the current taskList.

## deadline taskName /by <dd/mm/yyyy> hhmm

This command will add the taskName as a new deadline task at the end of the current taskList./by needs to be placed after the name of the task to tell the code that what appears afterwards is a date and time for the deadline.
The date and time has to be entered in the format of day/month/year and time in 24hr format.Importantly,the day and month have to be given in 2 digits.e.g. if the month is 3,it has to be entered as 03.

## event taskName /from <dd/mm/yyyy> hhmm /to <dd/mm/yyyy> hhmm

This command will add the taskName as a new event task at the end of the current tasklist./from and /to have to be added to tell the code that what comes after will be date and time.The format the date and time has to be entered in is
like for deadline.

## mark num of task

This command will set the mark status of the task at the position of the number given to marked.

## unmark num of task

This command will set the mark status of the task at the position of the number give to uinmarked.

## delete num of task

This command will delete the task at the position of the number given.All other tasks behind will be shifted up to cover the gap.

## find text to find

This comman will print out all the tasks that contain the text given in the order that they are present in the list.



# micellaneous notes and warnings
**Take Note**  If a command is given that is not among those listed above,am error message will be given.
Prerequisites: JDK 17, update Intellij to the most recent version.

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.
