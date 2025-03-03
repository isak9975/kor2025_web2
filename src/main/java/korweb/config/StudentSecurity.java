package korweb.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StudentSecurity {
    @Before("execution(* korweb.service.StudentService.*(..))")
    public void security(){
        System.out.println("StudentsService 발동");
    }
}

//첫번째 * : 모든 반환 타입의 메소드 뜻
//korweb.service.StudentService : 클래스가 위치한 경로를(src -> main -> java 이후
//두번째.* : 앞에 있는 클래스내 모든 메소드 뜻
//(..) : 메소드들의 매개변수 타입 뜻, (..) : 모든 타입 뜻
