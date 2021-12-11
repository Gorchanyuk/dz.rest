package dz.rest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Organization {
    //    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_gen")
    //    @SequenceGenerator(name = "my_gen", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "my_gen")
    private long id;
    private String name;
    private String url;


    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
