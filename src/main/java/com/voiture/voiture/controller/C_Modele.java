package com.voiture.voiture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voiture.voiture.api.APIResponse;
import com.voiture.voiture.modele.CaracteristiqueModele;
import com.voiture.voiture.modele.Modele;
import com.voiture.voiture.services.S_CaracteristiqueModele;
import com.voiture.voiture.services.S_Modele;

@RestController
@RequestMapping("/modele")
@CrossOrigin(origins = "*")
public class C_Modele {
    private final S_Modele s_Modele;
    private final S_CaracteristiqueModele s_CaracteristiqueModele;
    
    @Autowired
    public C_Modele(S_Modele s_Modele, S_CaracteristiqueModele s_CaracteristiqueModele) {
        this.s_Modele = s_Modele;
        this.s_CaracteristiqueModele = s_CaracteristiqueModele;
    }

    @PostMapping("/insert")
    public ResponseEntity<APIResponse> save(@RequestBody Modele Modele){
        try {
            Modele te = s_Modele.save(Modele);
            APIResponse api = new APIResponse(null, te);
            return ResponseEntity.ok(api);
        } catch (Exception e) {
            e.printStackTrace();
            APIResponse response = new APIResponse(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<APIResponse> update(@PathVariable int id,@RequestBody Modele Modele){
        try {
            Modele te = s_Modele.update(id,Modele);
            APIResponse api = new APIResponse(null, te);
            return ResponseEntity.ok(api);
        } catch (Exception e) {
            e.printStackTrace();
            APIResponse response = new APIResponse(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<APIResponse> findAll(){
        try {
            List<Modele> te = s_Modele.findAll();
            APIResponse api = new APIResponse(null, te);
            return ResponseEntity.ok(api);
        } catch (Exception e) {
            e.printStackTrace();
            APIResponse response = new APIResponse(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/findByMarque/{idMarque}")
    public ResponseEntity<APIResponse> findByMarque(@PathVariable int idMarque) {
        try {
            List<Modele> te = s_Modele.findByMarque(idMarque);
            APIResponse api = new APIResponse(null, te);
            return ResponseEntity.ok(api);
        } catch (Exception e) {
            e.printStackTrace();
            APIResponse response = new APIResponse(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<APIResponse> findById(@PathVariable int id){
        try {
            Modele te = s_Modele.findById(id);
            APIResponse api = new APIResponse(null, te);
            return ResponseEntity.ok(api);
        } catch (Exception e) {
            e.printStackTrace();
            APIResponse response = new APIResponse(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/findCaracteristique/{idModele}")
    public ResponseEntity<APIResponse> findCaracteristiqueModele(@PathVariable int idModele) {
        try {
            List<CaracteristiqueModele> liste = s_CaracteristiqueModele.findByIdModeles(idModele);
            APIResponse api = new APIResponse(null, liste);
            return ResponseEntity.ok(api);
        } catch (Exception e) {
            e.printStackTrace();
            APIResponse response = new APIResponse(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
