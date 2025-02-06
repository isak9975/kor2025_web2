package korweb.model.dto;

import korweb.model.entity.MemberEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter@Setter@Builder
@AllArgsConstructor@NoArgsConstructor
public class MemberDto {

    private int mno;

    private String mid;

    private String mpwd;

    private String mname;

    private String memail;

    private String mimg;

    private MultipartFile uploadfile; // 업로드 파일객체

    public MemberEntity toEntity(){
        return MemberEntity.builder().mno(this.mno).mid(this.mid).mpwd(this.mpwd).mname(this.mname).memail(this.memail).mimg( this.mimg) .build();
    }

}
