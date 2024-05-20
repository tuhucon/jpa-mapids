package com.example.jpamapids;

import com.example.jpamapids.entity.IdRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;

@Repository
@Transactional(readOnly = true)
public interface IdRangeRepository extends JpaRepository<IdRange, Long> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    IdRange findOneByType(Long type);

    IdRange getOneByType(Long type);
}
