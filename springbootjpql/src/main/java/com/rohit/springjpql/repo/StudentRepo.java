package com.rohit.springjpql.repo;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.rohit.springjpql.entity.Student;

public interface StudentRepo extends PagingAndSortingRepository<Student, Long> {

	@Query(value="select count(id) from student ",nativeQuery = true)
	int getCount();
	
	@Query("from Student s where s.fname=:fname")
	List<Student> findByFname(@Param("fname") String fname);

	@Query("select new Map( s.fname as fname,s.lname  as lname) from Student s")
	List<Map<String, Object>> getFnameAndLname();

	@Query("select new Map( s.fname as fname,s.lname  as lname) from Student s where s.id=:id")
	List<Map<String, Object>> getFnameAndLnameById(@Param("id") Long id);

	@Modifying
	@Query("delete from Student where fname=:fname")
	@Transactional
	void deleteByFname(@Param("fname") String fname);

	@Modifying
	@Query(value = "insert into student (fname,lname,score) values(:fname,:lname,:score)", nativeQuery = true)
	@Transactional
	void insertStudent(@Param("fname") String fname, @Param("lname") String lname, @Param("score") int score);
}
