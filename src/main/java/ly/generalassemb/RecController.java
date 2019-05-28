package ly.generalassemb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecController {

    @Autowired
    private RecRepository recRepository;

    @GetMapping("/recs")
    public Iterable<Rec> getRecs(){
        return recRepository.findAll();
    }

    @PostMapping("/recs")
    public Rec createRec(@RequestBody Rec rec){
        Rec createdRec = recRepository.save(rec);
        return createdRec;
    }

}