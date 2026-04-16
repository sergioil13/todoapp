package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import model.Tag;
import repository.TagRepository;

@Service
@RequiredArgsConstructor
public class TagService {

	private final TagRepository tagRepository;

	public List<Tag> saveOrGet(List<String> tags){
		
		List<Tag> result =new ArrayList<>();
		
		tags.forEach(tag->{
			
			Optional<Tag> val=tagRepository.findByText(tag);
			
			result.add(val.orElseGet(() -> Tag.builder().text(tag).build()));		
			});
		
		return result;
	}
	
	
}
