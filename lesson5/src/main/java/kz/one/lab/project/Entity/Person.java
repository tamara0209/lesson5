package kz.one.lab.project.Entity;
import javax.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;



@Entity
@Getter
@Setter
@Component
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;
    private Person(){}


    public Person(String name,int age){
        this.name = name;
        this.age = age;
    }

    public String toString(){
        return this.name + " " + this.age;
    }

}
