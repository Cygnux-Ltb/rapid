package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * User Repository
 *
 * @author yellow013
 */
@Repository
public interface UserDao extends JpaRepository<SysUserEntity, Long> {

    @Query("SELECT e FROM #{#entityName} e WHERE 1 = 1"
            + " AND (e.username = :username OR e.email = :email OR e.phone = :phone) ")
    SysUserEntity queryBy(@Param("username") String username,
                          @Param("email") String email,
                          @Param("phone") String phone);

}
