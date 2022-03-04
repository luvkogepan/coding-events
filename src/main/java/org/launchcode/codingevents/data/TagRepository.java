package org.launchcode.codingevents.data;

import org.aspectj.apache.bcel.generic.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {
}
