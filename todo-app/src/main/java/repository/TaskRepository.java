package repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import model.Category;
import model.Task;
import model.User;

public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByAuthor(User user, Sort sort);
	List<Task> findByCategory(Category category);
}
