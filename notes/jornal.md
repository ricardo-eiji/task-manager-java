## Create the local folder 
- Go to file explorer app and create a new folder for the project

I created "32 - Java Project" 
- Then inside I created another folder "task-manager"
    - The gitbash I opened in this "task-manager"
    - And this is the file where we'll connect to the GitHub

## Start Git in the new folder
- Open `gitbash` in the file explorer
    - I opened inside "task-manager" folder
- `git init -b main`
- `git status`

Ps: We'll be using one repo for the whole project from the beginning 

## Open VSCode and select the folder 'task-manager'

## Create `.gitignore` 
- Created before synching the repos (Local and Remote)
- Added .env, target/,node_modules/, etc.

## Do the first local commit 
- `git status`
- `git add .`
- `git status`
```bash
git commit -m "Initial commit:
- ignore rules
- README"
```

## Create GitHub Repo and then connect to local Git Repo
- Create the GitHub Repo
    - It's public
    - No README
    - No .gitignore 

- We cannot use username and password to push
    - `ls -la ~/.ssh` or `ls ~/.ssh` (Checking existing SSH keys)
        - We should see 'id_ed25519' and 'id_ed25519.pub'

    - `cat ~/.ssh/id_ed25519.pub` (Copy the value to create SSH key inside GitHub)
    
    - Add it to GitHub → Settings → SSH and GPG keys → New SSH key.

    - `ssh -T git@github.com` (Test SSH authentication)
        - We should see our username 

    - `git remote set-url origin git@github.com:ricardo-eiji/task-manager-java.git` (To change Git remote from HTTPS to SSH)

    - `git remote -v` (Check connection)

    - `git push -u origin main` (I didn't need to insert anything after this command - It worked directly)

- Commands to check the connection of local to remote repo
    - `git status` (Check working directory status)
    - `git branch` (Check local branches we have)
    - `ssh -T git@github.com` (Test GitHub SSH authentication)
    - `git remote -v` (Verify whether you use HTTPS or SSH)
    - `git branch -vv` (Show branch tracking information)
    - `git remote get-url origin` (Get the origin URL)

    - `git remote add origin <URL>` (Add a remote)
        - `git remote add origin https://github.com/ricardo-eiji/task-manager-java.git`
        - After writing this, I need to "set-url" to change

    - `git remote set-url origin <URL>` (Change remote URL - If added before)
        - `git remote set-url origin git@github.com:ricardo-eiji/task-manager-java.git`

    - `git log` and `git log --oneline` (Show commit history)

    - `git diff` (Check unstaged changes)

## Create the folder `java-basics/`
- Where the first version will live
    - Later, we'll move it into Maven project 

## Create the folder `notes/`
- Create the file 'jornal.md'
    - Where I'm taking notes about all the steps

## Setup 

- `java -version`
- `mvn -version`
- `git --version`

- If not installed
    - `sudo apt update`
    - `sudo apt install -y openjdk-17-jdk maven git`

- Commands related to .pub key to GitHub and SSH
    - `ls -la ~/.ssh` or `ls ~/.ssh` (Check if we already have the keys)
    - `ssh-keygen -t ed25519 -C ricardoprograma01@gmail.com` (Generate if we don't have it)
    - `cat ~/.ssh/id_ed25519.pub` (Copy it)
    - Add it to GitHub → Settings → SSH and GPG keys → New SSH key
    - `ssh -T git@github.com` (Check SSH authentication)

## Ask about the situation
- Show the steps I've done in the .md file 
- `tree` (To share the files)

## Ps
- We can change the visibility of the repository
    - From public to private (And the other way around too)


## Inside `java-basics` we'll create 3 files
- Task.java, Methods.java, Task_Manager.java

- Task.java
    - Represents a single task (an object). It defines the task's attribute (id, title, completed)
    - private id, title, completed
    - The constructor takes id and title and starts completed as false
    - Add getters and setters to access or modify the its information 

- Methods.java
    - Contains the business logic (operations) for managing tasks
    - Static methods that take an ArrayList<Task>: addTask, completeTask(id), removeTask(id), findTask(id) (returns null if not found) and findCompletedTasks().

- Task_manager.java
    - Contains the main() method, which is the program's entry point. 
        - It executes the operations defined in Methods.java, creates sample tasks, and prints the final results.

    - a main that adds 5 tasks, completes two, removes one, finds one and prints each remaining task with ✅ or ❌.


- Ps: From here, we can go back to the chat

## Add the code to each file (Beginning of the chat)

- Task.java
```java
public class Task
{
    private int id;
    private String title;
    private boolean completed;

    public Task(int id, String title)
    {
        this.id = id;
        this.title = title;
        this.completed = false;
    }

    public int getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public boolean isCompleted()
    {
        return completed;
    }

    public void setCompleted(boolean completed)
    {
        this.completed = completed;
    }
}
```

- Methods.java
```java
import java.util.ArrayList;
import java.util.Iterator;

public class Methods
{
    public static void addTask(ArrayList<Task> tasks, int id, String title)
    {
        tasks.add(new Task(id, title));
    }

    public static void completeTask(ArrayList<Task> tasks, int id)
    {
        for (Task task : tasks)
        {
            if (task.getId() == id)
            {
                task.setCompleted(true);
                break;
            }
        }
    }

    public static void removeTask(ArrayList<Task> tasks, int id)
    {
        // A plain for-each would throw ConcurrentModificationException here,
        // so we remove through the Iterator.
        Iterator<Task> iterator = tasks.iterator();

        while (iterator.hasNext())
        {
            if (iterator.next().getId() == id)
            {
                iterator.remove();
                break;
            }
        }
    }

    public static Task findTask(ArrayList<Task> tasks, int id)
    {
        for (Task task : tasks)
        {
            if (task.getId() == id)
            {
                return task;
            }
        }

        return null; // not found: the caller must check for null
    }

    public static ArrayList<Task> findCompletedTasks(ArrayList<Task> tasks)
    {
        ArrayList<Task> result = new ArrayList<>();

        for (Task task : tasks)
        {
            if (task.isCompleted())
            {
                result.add(task);
            }
        }

        return result;
    }
}
```

- Main.java
```java
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        ArrayList<Task> tasks = new ArrayList<>();

        Methods.addTask(tasks, 1, "Study Java");
        Methods.addTask(tasks, 2, "Practice SQL");
        Methods.addTask(tasks, 3, "Learn Spring");
        Methods.addTask(tasks, 4, "Search AI");
        Methods.addTask(tasks, 5, "Study Backend");

        Methods.completeTask(tasks, 1);
        Methods.completeTask(tasks, 2);

        Methods.removeTask(tasks, 5);

        Task found = Methods.findTask(tasks, 4);

        if (found != null)
        {
            System.out.println("Found: " + found.getTitle());
        }
        else
        {
            System.out.println("Task not found.");
        }

        System.out.println("----------------------");

        for (Task task : tasks)
        {
            if (task.isCompleted())
            {
                System.out.println(task.getTitle() + " - ✅");
            }
            else
            {
                System.out.println(task.getTitle() + " - ❌");
            }
        }

        System.out.println("----------------------");
        System.out.println("Completed:");

        for (Task task : Methods.findCompletedTasks(tasks))
        {
            System.out.println(task.getTitle());
        }
    }
}
```

## After writing the code, run
- `cd java-basics`
- `javac *.java`
    - It created the '.class' files of each of the 3 files
- `java Main`

Expected output:

```
Found: Search AI
----------------------
Study Java - ✅
Practice SQL - ✅
Learn Spring - ❌
Search AI - ❌
----------------------
Completed:
Study Java
Practice SQL
```

## Commit 
- `cd ..`

- `git add .`

```
commit -m "Update
- Version 1
- Plain java"
```
- `git tag -a stage-01-plain-java -m "Version 1: plain Java"`

- `git push --follow-tags`