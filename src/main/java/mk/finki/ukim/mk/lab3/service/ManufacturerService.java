package mk.finki.ukim.mk.lab3.service;

import mk.finki.ukim.mk.lab3.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    List<Manufacturer> listAll();
    Manufacturer find(Long id);

    Manufacturer save(String m2, String m21);
}
