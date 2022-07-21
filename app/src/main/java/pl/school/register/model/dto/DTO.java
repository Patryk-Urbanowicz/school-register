package pl.school.register.model.dto;

import org.modelmapper.ModelMapper;
import pl.school.register.model.dto.mapper.MappingUtils;

public interface DTO {
    default ModelMapper updateModelMapper(ModelMapper mapper, MappingUtils utils){
        return mapper;
    }
}
