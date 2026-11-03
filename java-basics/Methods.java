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