package com.group3.backend.controller;

import com.group3.backend.model.Course;
import com.group3.backend.model.Milestone;
import com.group3.backend.model.Task;
import com.group3.backend.repository.CourseRepository;
import com.group3.backend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    private TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    /**
     * reachabilityTest()
     * return a String with a successful message if backend reachable
     * @return String "Test successful"
     */
    @GetMapping("/ping")
    public String ping(){
        return "reachable";}

    @GetMapping("/get")
    public List<Task> getAllTasks(){
        List<Task> taskList = taskRepository.findAll();
        return taskList;
    }

    @GetMapping("/get/{id}")
    public Task getTaskByNumber(@PathVariable(value = "id") int id){
        Task ts = taskRepository.findById(id);
        return ts;
    }

    @PutMapping("/post")
    public ResponseEntity<Course> createCourse(@RequestBody Task task){
        Task tsk = new Task(task.getDescription(), task.getStudent());
        taskRepository.save(tsk);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public Task deleteTask(@PathVariable(value = "id") int id){
        Task task = taskRepository.findById(id);
        taskRepository.delete(task);
        return task;
    }
}
