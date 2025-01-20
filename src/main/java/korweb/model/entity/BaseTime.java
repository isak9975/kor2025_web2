package korweb.model.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.ExcludeSuperclassListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Getter;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTime {

    @CreatedDate
    private LocalDateTime cdate; //등록일(가입일)
    @LastModifiedDate
    private LocalDateTime udate; //수정날짜(정보수정일)
}
