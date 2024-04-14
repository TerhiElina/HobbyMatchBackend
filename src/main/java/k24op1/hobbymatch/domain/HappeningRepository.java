package k24op1.hobbymatch.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface HappeningRepository extends CrudRepository<Happening, Long> {
    List<Happening> findByHappeningId(Long happeningId);
    //List<Happening> findByGroupId(Long groupId);

}
