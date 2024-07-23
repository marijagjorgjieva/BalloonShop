package mk.finki.ukim.mk.lab3.service;

import mk.finki.ukim.mk.lab3.model.Balloon;

import java.util.List;

public interface BalloonService {
    List<Balloon> listAll();

    List<Balloon> searchByNameOrDescription(String text,String text2);

    void addBalloon(Balloon toBeAdded);

    boolean containsID(Long id);

    void editBalloon(Long id, String name, String Description,Long ID);

    void deleteBalloon(Long id);

    public Balloon findByID(Long id);


}
