package main.controller;

import main.dto.RegionDTO;
import main.entity.RegionEntity;
import main.servise.RegionService;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;
    //task =1=
    @PostMapping("")
    public ResponseEntity<?> create (@RequestBody RegionDTO regionDTO){
        regionService.create(regionDTO);
        return ResponseEntity.ok(true);
    }
    // task =2=
    @PutMapping("{id}")
    public ResponseEntity<?> update(
            @PathVariable ("id") Integer id,
            @RequestBody RegionDTO regionDTO){
       regionService.update(id,regionDTO);
        return ResponseEntity.ok(true);
    }
    // task =3=
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id")Integer id){
        regionService.deleteById(id);
      return ResponseEntity.ok(true);
    }
    //task =4=
    @GetMapping("")
    public ResponseEntity<List<RegionDTO>> getAll(){
        List<RegionDTO> dtoList = regionService.getALl();
        return ResponseEntity.ok(dtoList);
    }

//        (id,key,name_uz, name_ru, name_en,visible,created_date)
//            5. Get By Lang (Language keladi shu language dagi name larini berib yuboramiz)
//        (id,key,name) (name ga tegishli name_.. dagi qiymat qo'yiladi.) (visible true)
}
