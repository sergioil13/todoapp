package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.EditTaskRequest;
import lombok.RequiredArgsConstructor;
import model.Category;
import model.Task;
import service.CategoryService;
import service.TaskService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/")
public class TaskAdminController {

    private final TaskService taskService;
    private final CategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.findAll();
    }
    
    @GetMapping({"/", "/list", "/task"})
    public String adminTaskList(Model model) {
        model.addAttribute("taskList", taskService.findAllAdmin());
        return "admin/admin-tasks";
    }
    
    @GetMapping(value={"/", "/list", "/task"}, params = "emptyListError")
    public String adminEmptyList(Model model) { 
        return "admin/admin-tasks"; 
    }
    
    @PostMapping("/task/{id}/del")
    public String adminDeleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return "redirect:/admin/";
    }
    
    @GetMapping("/task/{id}")
    public String adminViewTask(@PathVariable Long id, Model model) {

        Task task = taskService.findById(id);
        EditTaskRequest editTask = EditTaskRequest.of(task);
        model.addAttribute("task", editTask);
        
        return "admin/view-task";
    }
}