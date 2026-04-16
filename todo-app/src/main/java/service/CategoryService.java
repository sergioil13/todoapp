package service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import model.Category;
import repository.CategoryRepository;
@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final TaskService taskService;
	
	public List<Category> findAll(){
		
		return categoryRepository.findAll(Sort.by("title").ascending());
	}
	
	public void deleteById(Long id) {
	    if (id != 1L) {
	        Category oldCategory = categoryRepository.getReferenceById(id);
	        Category mainCategory = categoryRepository.getReferenceById(1L);
	        taskService.updateCategory(oldCategory, mainCategory);
	        categoryRepository.deleteById(id);
	    }
	}

	public Category save(Category category) { 
	    return categoryRepository.save(category); 
	}
}
