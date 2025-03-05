package korweb.controller;

import korweb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController //스프링 컨테이너에 빈(인스턴스) 등록, HTTP의 요청/응답 처리
@RequestMapping("/students") //해당 크래스내 메소드들의 공통 URL 정의
public class StudentController {

    //IOC : 개발자가 객체를 관리 하지 않고 스프링 컨테이너가 객체 생성/관리 해주는 것
    @Autowired // 스프링 컨테이너에서 빈주입(가져온다), DI : 의존성 주입
    private StudentService studentService;


    //[1] 학생 점수 등록
    @PostMapping("") //HTTP METHOD 중에서 'POST' 선택
    //[POST] http://localhost:8080//students
    public int save(@RequestBody Map<String,Object>map){
        //@RequestBody : HTTP 요청한 데이터가 application/json 타입일때 자바 타입으로 자동 변환
        //JS(JSON)--http-->Java
        //JSON      JSON   JSON모름(JSON -> DTO/MAP)
        //{ }  ------------>DTO / MAP
        //[ ]  ------------>List
        System.out.println("StudentController.save");//soutm
        System.out.println("map = " + map);//soutp
        return studentService.save(map);
    }

    //[2] 학생 전체 조회
    @GetMapping("")
    public List<Map<String,Object>>findAll(){
        System.out.println("StudentController.findAll");
        return studentService.findAll();
    }

    //[3] 특정한 점수 이상의 학생 조회
    @GetMapping("/scores")
    public List<Map<String,Object>>findStudentScores(@RequestParam int minKor,@RequestParam int minMath){
        return studentService.findStudentScores(minKor,minMath);
    }

    //[4] 여러명의 학생 등록
    @GetMapping("/saveAll")
    public boolean saveAll(@RequestBody List<Map<String,Object>>list){
        return studentService.saveAll(list);
    }



}
