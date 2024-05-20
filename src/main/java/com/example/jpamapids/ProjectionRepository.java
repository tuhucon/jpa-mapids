package com.example.jpamapids;

import com.example.jpamapids.entity.Projection;
import com.example.jpamapids.entity.ProjectionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ProjectionRepository extends JpaRepository<Projection, Long> {

    @Query(value = "select p from Projection  p")
    List<Projection> findAllViaJpql();


//    @Query(value = "select p from Projection  p")
//    List<ProjectionDTO> findAllViaProjectionDTOJpql();

//    @Query(value = "select p.col1 from Projection  p")
//    List<ProjectionDTO> findAllViaProjectionDTOJpqlSelectCol1();

    List<ProjectionDTO> findAllByCol1(String col1);
}
