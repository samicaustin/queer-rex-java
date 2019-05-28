package ly.generalassemb;

// This will be AUTO IMPLEMENTED by Spring into a Bean called postRepository
// CRUD refers Create, Read, Update, Delete

import org.springframework.data.repository.CrudRepository;

public interface RecRepository extends CrudRepository<Rec, Long> {

}