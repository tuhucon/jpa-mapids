package com.example.jpamapids;

import com.example.jpamapids.entity.Child;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {

    @Query(value = "select * from Child where id < :id", nativeQuery = true)
    @org.springframework.data.jpa.repository.QueryHints(value = {
            @QueryHint(name = QueryHints.NATIVE_SPACES, value = "com.exmaple.jpamapids.entity.Child")
    })
    List<Child> findChildWithIdLessThan(Long id);

}
