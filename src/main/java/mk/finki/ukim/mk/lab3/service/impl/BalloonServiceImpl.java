package mk.finki.ukim.mk.lab3.service.impl;

import mk.finki.ukim.mk.lab3.model.Balloon;
import mk.finki.ukim.mk.lab3.repository.jpa.BalloonRepository;
import mk.finki.ukim.mk.lab3.repository.jpa.ManufacturerRepository;
import mk.finki.ukim.mk.lab3.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final BalloonRepository balloonRepository;
    private final ManufacturerRepository manufacturerRepository;
    public BalloonServiceImpl(BalloonRepository balloonRepository, ManufacturerRepository manufacturerRepository) {
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAll();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text,String text2) {
        return balloonRepository.findAllByNameOrDescription(text,text2);
    }

    @Override
    public boolean containsID(Long id) {
        return balloonRepository.existsById(id);
    }

    @Override
    public Balloon findByID(Long id) {
        return balloonRepository.findById(id).orElseThrow();
    }

    @Override
    public void editBalloon(Long id, String name, String description,Long ID) {
        Optional<Balloon> toBeModified = balloonRepository.findById(id);
        toBeModified.ifPresent(balloon -> balloon.setName(name));
        toBeModified.ifPresent(balloon -> balloon.setDescription(description));
        toBeModified.ifPresent(balloon -> balloon.setManufacturer(manufacturerRepository.findById(ID).orElseThrow()));
    }

    @Override
    public void deleteBalloon(Long id) {
        if (balloonRepository.existsById(id)) {
            balloonRepository.deleteById(id);
        }
        else
        {
            throw new RuntimeException();
        }
    }

    @Override
    public void addBalloon(Balloon toBeAdded) {
        balloonRepository.save(toBeAdded);
    }


}
