package com.tonorganisation.service;
import com.tonorganisation.repository.*;
import com.tonorganisation.model.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class EntraineurService {
    @Autowired
    private EntraineurRepository entraineurRepository;

    @Autowired
    private UserService userService;

    public Entraineur createEntraineur(String nom, String email, String password) {
        // Create user entry
        User user = userService.createUser(email, password, Role.ENTRAINEUR);

        // Create entraineur entry
        Entraineur entraineur = new Entraineur();
        entraineur.setNom(nom);
        entraineur.setEmail(email);
        entraineur.setPassword(password);
        entraineur.setUser(user);

        return entraineurRepository.save(entraineur);
    }
}
