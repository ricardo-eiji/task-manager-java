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