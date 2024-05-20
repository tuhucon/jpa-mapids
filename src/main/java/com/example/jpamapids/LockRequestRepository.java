package com.example.jpamapids;

import com.example.jpamapids.entity.LockRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
@Transactional(readOnly = true)
public interface LockRequestRepository extends CrudRepository<LockRequest, Long> {

    @Transactional
    @Query(value = "Insert into lock_request values(:id, :createdDate)", nativeQuery = true)
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    void createNew(Long id, Date createdDate);

}
