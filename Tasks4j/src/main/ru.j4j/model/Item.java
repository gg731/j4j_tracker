package model;


import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "task")
    private String task;

    @Column(name = "about")
    private String about;

    @Column(name = "done")
    private int done;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    public Item() {
    }

    public Item(String task, String about, User user) {
        this.task = task;
        this.about = about;
        this.user = user;
    }

}

