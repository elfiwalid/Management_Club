package com.tonorganisation.controller;


import com.tonorganisation.model.*;
import com.tonorganisation.repository.*;
import com.tonorganisation.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/api/classement")
public class ClassementController {

    @Autowired
    private ClassementService classementService;

    @GetMapping
    public ResponseEntity<List<Classement>> getClassement() {
        try {
            List<Classement> classement = classementService.getClassementByPointsDesc();
            return ResponseEntity.ok(classement);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null);
        }
    }
}

