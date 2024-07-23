package mk.finki.ukim.mk.lab3.repository.jpa;

import mk.finki.ukim.mk.lab3.model.Balloon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BalloonRepository extends JpaRepository<Balloon,Long> {
    List<Balloon> findAllByNameOrDescription(String name,String description);


}
