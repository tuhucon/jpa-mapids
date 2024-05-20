package com.example.jpamapids;

import com.example.jpamapids.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

    @Modifying
    @Query("Update Test t set t.name = :name where t.id = :id")
    @Transactional
    void updateNameJpql(Long id, String name);

    @Modifying()
    @Query(nativeQuery = true, value = "Update test set name = :name where id = :id")
    @QueryHints(
            @QueryHint(name = org.hibernate.annotations.QueryHints.NATIVE_SPACES, value = "com.example.jpamapids.entity.Test")
    )
    void updateNameRaw(Long id, String name);


    @Query("select t from Test  t where t.id = :id")
    Test findByIdJpal(Long id);

    @Query(value = "select * from test where id = :id", nativeQuery = true)
    Test findByIdRaw(Long id);

    @Query("select t.name from Test t where id.id = :id")
    String findNameByIdJapl(Long id);

    @Query(value = "select name from test where id = :id", nativeQuery = true)
    String findNameByIdRaw(Long id);
}
