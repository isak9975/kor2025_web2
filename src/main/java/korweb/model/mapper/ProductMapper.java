package korweb.model.mapper;

import korweb.model.dto.ProductDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper //[2] 해당 인터페이스가 myBatis mapper 임을 명시
public interface ProductMapper { // [1] 인터페이스타입 선언

    //추상메서드
    //[3] @SQL 어노테이션 (" SQL 작성 ")
    //[4] SQL에서 매개변수 표현 : ***#{매개변수}***
    //#{name} : productDto 안에 name/price 멤버변수가 존재하므로 가능하다
        //(1) 제품 등록
//        @Insert("insert into products(name,price)values(#{name},#{price})")
        int save(ProductDto productDto);

        //(2) 제품 전체 조회
//        @Select("select*from products")
        List<ProductDto> findAll();
        //*mybatis @select 에서는 반환타입을 특별하게 명시하지 않아도 자동
        //즉] 자동응로 추상메소드의 타입으로 결과를 변환해준다.
        //조회 결과를 자동으로 List<ProductDo>로 반환해준다.

        //(3) 제품 개별 조회
//        @Select("select*from products where id = #{id}")
        ProductDto find(int id);
        //*mybatis @Select 에서는 #{매개변수}를 추상 메소드 매개변수와 같다.
        //즉] 추상메소드 매개변수에 변수를 #{ } 이용하여 SQL 문에 대입할 수 있다.
            //console 프로젝트 Dao 에서 ps.setInt(1,id);
        //조회결괄르 자동으로 ProductDto 로 변환 해준다.
            //console 프로젝트 Dao 에서 re.next(); re.getInt(id);

        //(4) 제품 개별 수정
            //DAO 방법: update product set name =?, price =? where id = ?;
            //maBits 방법 : update products set name = #{name}, price =#{price} where id=#{id}
//        @Update("update products set name=#{name}, price=#{price} where id=#{id}")
        boolean update(ProductDto productDto);

        //(5) 제품 개별 삭제
//        @Delete("delete from products where id = #{id} ")
        boolean delete(int id);


        //전에쓰던거 Jpa(entity + repository) / 이번에 쓰는거 mybatis(Mapper)


        /**
        int ==⇒ sql 처리 결과 레코드 수 반환

        boolean ==⇒ sql 처리 성공 여부 반환

        List<Dto> ==⇒ sql 처리 결과 자동으로 Dto 반환
        */


}
