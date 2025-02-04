package korweb.model.dto;

import korweb.model.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Setter@Getter@Builder
@AllArgsConstructor@NoArgsConstructor
public class BoardDto {

    private int bno;

    private String btitle;

    private String bcontent;

    private int cno;

    private int bview;

    private LocalDateTime cdate;

    public BoardEntity toEntity(){
        return BoardEntity.builder().bno(this.bno).bcontent(this.bcontent).btitle(this.btitle).bview(this.bview).build();
    }

}

