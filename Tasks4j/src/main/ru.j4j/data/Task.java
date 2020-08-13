package data;

import model.Item;

import java.util.List;

public interface Task {

    void addTask(Item item);

    void doneTaskById(int id, int done);

    int checkTaskById(int id);

    List<Item> findAllTask();

}
