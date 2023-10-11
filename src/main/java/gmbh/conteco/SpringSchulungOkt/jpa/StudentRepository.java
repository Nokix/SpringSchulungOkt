package gmbh.conteco.SpringSchulungOkt.jpa;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String fabian);

    List<Student> findTop2ByEmailContainsOrderByNameAsc(String email);

    @Query("select s from Student s where s.name like concat('%', ?1, '%') order by s.email")
    List<Student> findSomeStudents(String name);

    @Query("select count(s) from Student s where s.name = ?1")
    long countByName(String name);

    @Query("select s from Student s where s.name like concat('%', ?1, '%') or s.email like concat('%', ?1, '%')")
    List<Student> findAnywhere(String any);

    @Query("select s from Student s where s.name like concat('%', ?1, '%')")
    List<Student> findByName(String name, Pageable pageable);





//    @Query(value = "SELECT * FROM student", nativeQuery = true)
//    List<Student> findThemAll();
}
