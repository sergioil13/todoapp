package model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Task {

	@Id 
	@GeneratedValue
	private Long id;
	
	
	private String title;
	@Lob
	private String description;
	
	private boolean completed;
	
	@Builder.Default
	private LocalDateTime createdAt=LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(foreignKey= @ForeignKey(name="Fk_task_category"))
	private Category category;
	
	@ManyToOne
	@JoinColumn(foreignKey= @ForeignKey(name="Fk_task_user"))
	private User author;
	
	 @ManyToMany(fetch =FetchType.EAGER)
	 @JoinTable(name="task_tag")
	 @Builder.Default
	 @Setter(AccessLevel.NONE)
	 private Set<Tag>tags=new HashSet<>();

	@Override
	public int hashCode() {
		return Objects.hash(completed, createdAt, description, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return completed == other.completed && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id);
	}
	
	
	
	
}
