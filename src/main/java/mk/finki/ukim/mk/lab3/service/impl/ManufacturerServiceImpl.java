package mk.finki.ukim.mk.lab3.service.impl;

import mk.finki.ukim.mk.lab3.model.Manufacturer;
import mk.finki.ukim.mk.lab3.repository.jpa.ManufacturerRepository;
import mk.finki.ukim.mk.lab3.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;
    private final Random random = new Random();

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> listAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer find(Long id) {
        return manufacturerRepository.findById(id).orElseThrow();
    }

    @Override
    public Manufacturer save(String m2, String m21) {
       return manufacturerRepository.save(new Manufacturer(random.nextLong(), m2,m21,"sth"));
    }
}
