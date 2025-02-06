package korweb.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter@Getter@Builder
@AllArgsConstructor@NoArgsConstructor
@Entity@Table(name="category")
public class CategoryEntity extends BaseTime{

    //1. 카테고리번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno;

    //2. 카테고리명
    @Column(columnDefinition = "varchar(50)")
    private String cname;


}
