package com.m.dailytodo.Model;

public class TaskItem {

    public String task_name;
    public String priority_name;

    public TaskItem() {
    }

    public TaskItem(String task_name, String priority_name) {
        this.task_name = task_name;
        this.priority_name = priority_name;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getPriority_name() {
        return priority_name;
    }

    public void setPriority_name(String priority_name) {
        this.priority_name = priority_name;
    }
}
