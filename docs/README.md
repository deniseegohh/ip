## Pepero User Guide ❤️

![Screenshot of Pepero](/docs/Ui.png)

Pepero is **YOUR** friendly task manager that will help you to keep track of <ins>all</ins> your tasks! :)

> "Those who fail to plan, plan to fail."

## Adding todos
Add a simple todo task. \
Usage: `todo <task_description>` \
Example: `todo read book` \
Expected outcome: 
```
✨ Got it! Added this task: [T][ ] read book 
Now you have 1 task to handle 🍫
```


## Adding deadlines
Add a task that must be completed before a specific date and time. \
Usage: `deadline <task_description> /by <ddMMyy HHmm>` \
Example: `deadline return book /by <190925 0900>` \
Expected outcome: 
```
✨ Got it! Added this task: [D][ ] return book (by: 190925 0900)
Now you have 2 tasks to handle 🍫
```

## Adding events
Add an event with a start and end time. \
Usage: `event <task_description> /from <ddMMyy HHmm> /to <ddMMyy HHmm>` \
Example: `event meeting /from 201125 1400 /to 201125 1600` \
Expected outcome: 
```
✨ Got it! Added this task: [E][ ] meeting /from 201125 1400 /to 201125 1600
Now you have 3 tasks to handle 🍫
```

## Listing tasks
List all your current tasks. \
Usage: `list` \
Example: `list` \
Expected outcome: 
```
Here's a look at your tasks:
1.[T][ ] read book
2.[D][ ] return book (by: 190925 0900)
3.[E][ ] meeting /from 201125 1400 /to 201125 1600
```

## Deleting tasks
Delete a task by its index in the list. \
Usage: `delete <task_index>` \
Example: `delete 2` \
Expected outcome: 
```
💔 Task removed:
[D][ ] return book (by: 190925 0900)
Now you have 2 tasks to handle 🍫
```

## Marking tasks as done
Mark a task as completed by its index in the list. \
Usage: `mark <task_index>` \
Example: `mark 1` \
Expected outcome: 
```
✅ Sweet! This task is all done:
[T][X] read book
```

## Marking tasks as undone
Mark a task as not completed by its index in the list. \
Usage: `unmark <task_index>` \
Example: `unmark 1` \
Expected outcome: 
```
🍪 No worries! This task is still pending:
[T][ ] read book
```

## Finding tasks
Find tasks that contain a specific keyword in their description. \
Usage: `find <keyword>` \
Example: `find book` \
Expected outcome: 
```
Here are the matching tasks in your list:
1.[T][ ] read book
```

## Updating tasks
Update the description and/or date/time of a task by its index in the list. \
Usage: `update <task_index> [<new_description>] [/by <new_ddMMyy HHmm>] [/from <new_ddMMyy HHmm>] [/to <new_ddMMyy HHmm>]` \
Example: `update 2 project meeting /to 201125 1700` \
Expected outcome:
```
✨ Nice! I've updated this task:
[E][ ] project meeting /from 201125 1400 /to 201125 1700
```

## Closing the chatbot
Close the Pepero application. \
Usage: `bye` \
Example: `bye` \
Expected outcome: 
```
🍫 Bye bye! Hope your day stays as sweet as Pepero! 🍪
```
### Thank you for using Pepero! ❤️

Credits for images:
- [Pepero.jpg](https://kiasumart.com/wp-content/uploads/2020/02/68.-Pepero-Almond-Chocolate-32G.jpg)
- [pepero_icon.png](https://yt3.googleusercontent.com/ytc/AIdro_lvbYjJlBi58BjdpCvO53R9sbn29fha00jbki1xjE-5Cg=s900-c-k-c0x00ffffff-no-rj)
- [pepero_stick.png](https://e7.pngegg.com/pngimages/315/926/png-clipart-pepero-chocolate-chip-cookie-biscuit-almond-almond-chocolate-gourmet-twig-thumbnail.png)
- [User.png](https://cdn.pixabay.com/photo/2023/02/18/11/00/icon-7797704_1280.png)