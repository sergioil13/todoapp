package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import model.UserRole;
import service.UserService;

@Controller
@RequiredArgsConstructor
public class UserAdminController {

    private final UserService userService;

    /*Aqui da un error que no consigo arreglar
     * 
     * 
     * @GetMapping("/admin/user")
    public String listUsers(Model model) {
        model.addAttribute("userList", 
            userService.findAll().stream().map(UserResponse::of).toList());
        
        return "admin/admin-users";
    }*/
    
    @GetMapping("/admin/user/{id}/promote")
    public String makeAdmin(@PathVariable Long id, Model model) {
        userService.changeRole(id, UserRole.ADMIN);
        return "redirect:/admin/user";
    }
}