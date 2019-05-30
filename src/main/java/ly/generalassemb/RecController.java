package ly.generalassemb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
public class RecController {

    @Autowired
    private RecRepository recRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/recs")
    public Iterable<Rec> recIndex(){
        System.out.println(recRepository.findAll());
        return recRepository.findAll();
    }

    @PostMapping("/recs")
    public Rec createRec(@RequestBody Rec rec, HttpSession session) throws Exception{
        if(session.getAttribute("username") == null){
            throw new Exception("you must be logged in");
        }
        User user = userRepository.findByUsername(session.getAttribute("username").toString());
        if(user == null){
            throw new Exception("you must be logged in");
        }
        rec.setUser(user);
        Rec createdRec = recRepository.save(rec);
        return createdRec;
    }

    @GetMapping("/recs/{id}")
    public Rec show(@PathVariable("id") Long id) throws Exception{
        Optional<Rec> rec = recRepository.findById(id);
        if(rec.isPresent()){
            return rec.get();
        }
        throw new Exception("This rec doesn't exist!");
    }

    @PutMapping("/recs/{id}")
    public Rec update(@RequestBody Rec formData, @PathVariable("id") Long id) throws Exception{
        Optional<Rec> response = recRepository.findById(id);
        if(response.isPresent()) {
            Rec rec = response.get();
            rec.setTitle(formData.getTitle());
            return recRepository.save(rec);
        }
        throw new Exception("This rec doesn't exist!");
    }

    @DeleteMapping("/recs/{id}")
    public String delete(@PathVariable("id") Long id){
        recRepository.deleteById(id);
        return("Deleted " + id);
    }

}