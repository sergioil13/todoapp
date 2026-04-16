package init;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dto.CreateTaskRequest;
import dto.CreateUserRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import model.Category;
import model.User;
import model.UserRole;
import repository.CategoryRepository;
import service.TaskService;
import service.UserService;

@Component
@RequiredArgsConstructor
public class DataSeed {

    private final UserService userService;
    private final CategoryRepository categoryRepository;
    private final TaskService taskService;    
    @PostConstruct
    public void init() { 
        List<User> users = insertUsers(); 
        insertCategories()
;    }

  
     
    private List<User> insertUsers() {
    	List<User> result = new ArrayList<>();

    	CreateUserRequest req = CreateUserRequest.builder()
    	        .username("user")
    	        .email("user@user.com")
    	        .password("1234")
    	        .verifyPassword("1234")
    	        .fullname("The user")
    	        .build();

    	User user = userService.registerUser(req);
    	result.add(user);
    	
    	CreateUserRequest req2 = CreateUserRequest.builder()
    	        .username("admin")
    	        .email("admin@openwebinars.net")
    	        .password("1234")
    	        .verifyPassword("1234")
    	        .fullname("Administrador")
    	        .build();

    	User user2 = userService.registerUser(req2);

    	userService.changeRole(user2, UserRole.ADMIN);
    	
    	return result;
    }
    
    private void insertCategories() {categoryRepository.save(Category.builder().title("Main").build());}
    
    
    private void insertTasks(User author) {

        CreateTaskRequest req1 = CreateTaskRequest.builder()
                .title("First task!")
                .description("Lorem ipsum dolor sit amet")
                .tags("tag1,tag2,tag3")
                .build();

        taskService.createTask(req1, author);
        
        CreateTaskRequest req2 = CreateTaskRequest.builder()
                .title("Second task!")
                .description("Lorem ipsum dolor sit amet")
                .tags("tag1,tag2,tag4")
                .build();

        taskService.createTask(req2, author);

    }
}


