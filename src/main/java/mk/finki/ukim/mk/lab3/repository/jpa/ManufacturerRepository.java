package mk.finki.ukim.mk.lab3.repository.jpa;

import mk.finki.ukim.mk.lab3.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long> {
}
