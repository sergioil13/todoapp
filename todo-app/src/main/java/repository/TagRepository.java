package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

	Optional<Tag> findByText(String text);
}
