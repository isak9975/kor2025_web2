package korweb.model.dto;


import lombok.*;

@Setter@Getter@Builder
@AllArgsConstructor@NoArgsConstructor
public class PageDto {

    private long totalcount; // 조회된 자료의 개수

    private int page; // 현재페이지 번호

    private int totalpage; // 전체페이지 번호

    private int starbtn; //조회 페이지의 페이징버튼 시작번호

    private int endbtn; //조회 페이지의 페이징버튼 끝번호

    //+Object 는 자바의 최상위 클래스 임으로 모든 타입들의 자료들을 저장할 수 있다.
        //즉 data 에는 List<boardDto> 혹은 List<ReplyDto> 다양한 페이징한 정보를 대입하기 위해서 오프젝트를 사죶
    private Object data;

}
