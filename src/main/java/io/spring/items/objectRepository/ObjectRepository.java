package io.spring.items.objectRepository;

import io.spring.items.models.Objects;
import org.springframework.data.repository.CrudRepository;

public interface ObjectRepository extends CrudRepository<Objects,String> {

}
