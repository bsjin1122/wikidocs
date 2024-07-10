package com.mysite.sbb.question;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

// 엔티티가 데이터베이스 테이블을 생성했다면, 
// 리포지터리는 이와 같이 생성된 데이터베이스 테이블의 데이터들을 저장, 조회, 수정, 삭제 등을 할 수 있도록 도와주는 인터페이스
public interface QuestionRepository extends JpaRepository<Question, Integer>{ //CRUD 작업을 처리하는 메서드들을 이미 내장
	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject, String content);
	List<Question> findBySubjectLike(String subject);
	Page<Question> findAll(Pageable pageable);
}
