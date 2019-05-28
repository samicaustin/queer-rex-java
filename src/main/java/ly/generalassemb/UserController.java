package ly.generalassemb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public Iterable<User> userIndex(){
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("/users/{id}")
    public User oneUser(@PathVariable("id") Long id) throws Exception{
        Optional<User> foundUser = userRepository.findById(id);
        if(foundUser.isPresent()){
            return foundUser.get();
        }
        throw new Exception("This user doesn't exist!");
    }

    @PutMapping("users/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User formData) throws Exception{
        Optional<User> updatedUser = userRepository.findById(id);
        if(updatedUser.isPresent()){
            User user = updatedUser.get();
            user.setUsername(formData.getUsername());
            user.setPassword(formData.getPassword());
            return userRepository.save(user);
        }
        throw new Exception("This user doesn't exist!");
    }

    @DeleteMapping("users/{id}")
    public String delete(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return("Deleted user " + id);
    }



}
