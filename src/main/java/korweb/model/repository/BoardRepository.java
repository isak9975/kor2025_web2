package korweb.model.repository;

import korweb.model.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Integer> {

    // + JPA 함수명은 무조건 카멜표기법 ( 낙타의 혹처럼) 첫글자 소문자로 시작, 두번째 단어의 첫글자를 대문자
        //-mystudentlist -> myStudentList(카멜표기법)

    //내장 함수
    //findById( pk 번호 ) : 지정한 pk번호의 레코드 조회
    //findAll()         : 모든 레코드 조회

    // 추상 메소드 만들기
    //1. findBy필드명( int 필드명)

    //findByBtitle( int btitle ) : 지정한 게시물 제목으로 레코드 조회
    //findByBcontent( int bcontent ) : 지정한 게시물내용으로 레코드 조회

    //[1] cno 로 레코드 조회
    //Page<BoardEntity> findByCno(int cno, Pageable pageable);
    //만약에 cno가 참조키(fk) 일때는 fk필드명 넣지 않고 엔티티 필드명 사용
    //자바엔티티필드명_참조엔티티필드명 //findByCategoryEntity_Cno
//    Page< BoardEntity > findByCategoryEntity_Cno(int cno, Pageable pageable);

//=====================================================================================================================

    // [2] JPA nativeQuery( SQL 문법 ) 이용한 조회, @Query(value="Sql 문법",nativeQuery = true)
        ///1. 특정한 카테고리 번호에 해당하는 모든 게시물 조회
        //Dao(sql) : select * from board where cno = ? ;
        //JPA(nativeQuery) : select * from board where cno = :매개변수명
    @Query(value="select*from board where cno = :cno",nativeQuery = true)
    List<BoardEntity> findByQuery1(int cno_);

        ///2. 게시물 제목에 키워드를 포함하는 게시물 조회
        //DAO(sql) : select * from board where btitle like %?%
        //JPA(nativeQuery) : select * from board where btitle like %:매개변수명&
    @Query(value="select*from board where btile like %:keyword%",nativeQuery = true)
    List<BoardEntity> findByQuery2(String keyword);

        ///3. 특정한 카테고리 번호에 게시물 제목에서 키워드를 포함하는 모든 게시물 조회
        //Dao(slq) : select * from board where cno = ? and btitle like %?%;
        //JPa(nativeQuery) : select * from board where cno = :cno and btitle like %:keyword%
    @Query(value = "value=select*from board where cno = :cno and btitle like %:keyword%",nativeQuery = true)
    List<BoardEntity> findByQuery3(int cno,String keyword);

        ///4. 매개변수 필드(제목 또는 내용)에 키워드를 포함하는 모든 게시물 조회 , 필드명을 매개변수로 사용할 수 있다.
            // 필드명에 값에 따른 [ sql : if( 조건 , 참 , 거짓 ) ]) 적용한다 / *중첩가능*
        //JPA(nativeQuery) : select * from board where if(:key = 'btitle', btitle like %:keyword%, bcontent like %:keyword%)
    @Query(value = "select*from board where if(:key = 'btitle', btitle like %:keyword%, bcontent like %:keyword%",nativeQuery = true)
    List<BoardEntity> findByQuery4(String key,String keyword);

        // * 카테고리별 ( 1.전체조회[keyword 존재하지 않으면] 2.제목(btitle)검색, 3.내용(bcontent)검색 ) + 페이징 처리
    @Query(value = "select * from board where cno = :cno and " +
            " if( :keyword = '',true," +
            " if( :key = 'btitle', btitle like %:keyword%, " +
            " if( :key = 'bcontent', bcontent like %:keyword%,true)))" ,nativeQuery = true)
    Page<BoardEntity> findBySearch(int cno, String key, String keyword,Pageable pageable);

        ///  if 조건이 실행 되었을때 가정
            //SQL IF 조건문 : if( 조건1, 참1, if( 조건2, 참2, if( 조건3, 참3, 그외 거짓)))
        /// 1. 전체조회 : select * from board where cno = :cno and true
        /// 2. 제목검색 : select * from board where cno = :cno and btitle like &:keyword&
}





