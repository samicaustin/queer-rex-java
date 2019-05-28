package ly.generalassemb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    //at the top of the class definition near userRepository and userService:
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    // as another route listed after class variables
    @PostMapping("/login")
    public User login(@RequestBody User login, HttpSession session) throws IOException {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findByUsername(login.getUsername());
        if(user ==  null){
            throw new IOException("Invalid Credentials");
        }
        boolean valid = bCryptPasswordEncoder.matches(login.getPassword(), user.getPassword());
        if(valid){
            session.setAttribute("username", user.getUsername());
            return user;
        }else{
            throw new IOException("Invalid Credentials");
        }
    }





    @GetMapping("/users")
    public Iterable<User> userIndex(){
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        User createdUser = userService.saveUser(user);
        return createdUser;
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
