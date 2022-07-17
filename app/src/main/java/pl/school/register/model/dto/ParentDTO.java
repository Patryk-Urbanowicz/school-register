package pl.school.register.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.school.register.model.Parent;

import java.util.*;

@Data
@NoArgsConstructor
public class ParentDTO extends AccountDTO{
    private Set<Long> childrenIds = new HashSet<>();

    public ParentDTO(Parent parent) {
        super(parent);
        parent.getChildren().forEach(child -> childrenIds.add(child.getId()));
    }
}
