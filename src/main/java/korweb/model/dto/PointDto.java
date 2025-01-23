package korweb.model.dto;

import korweb.model.entity.PointEntity;
import lombok.*;

@Getter@Setter@Builder
@AllArgsConstructor@NoArgsConstructor
public class PointDto {
    private int pno;
    private String pcontent;
    private int pcount;
    private String cdate;

    //dto --> entity 변환함수
    public PointEntity toEntity(){
        return PointEntity.builder().pno(this.pno).pcontent(this.pcontent).pcount(this.pcount).build();
    }
}
