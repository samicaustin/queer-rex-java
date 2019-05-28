package ly.generalassemb;

import javax.persistence.*;


// This is equivalent to an Express model--all rec props need getters
// and setters in this doc!

@Entity
public class Rec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String textInput){
        this.title = textInput;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

}