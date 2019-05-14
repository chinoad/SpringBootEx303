package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    TaskRepository taskRepository;

    @RequestMapping("/")
    public String listTasks(Model model){
        model.addAttribute("todos", taskRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String taskForm(Model model){
        model.addAttribute("todo", new ToDo());
        return "taskForm";
    }

    @PostMapping("/process")
    public String processForm(@Valid ToDo todo, BindingResult result){
        if(result.hasErrors()){
            return "taskForm";
        }
        taskRepository.save(todo);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showTask(@PathVariable("id") long id, Model model){
        model.addAttribute("todo", taskRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateTask(@PathVariable("id") long id, Model model){
model.addAttribute("todo", taskRepository.findById(id).get());
return "taskForm";
    }

    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id){
        taskRepository.deleteById(id);
        return "redirect:/";
    }
}
