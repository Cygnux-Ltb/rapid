package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TbsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User Repository
 *
 * @author yellow013
 */
@Repository
public interface UserDao extends JpaRepository<TbsUser, Long> {

    @Query("SELECT e FROM #{#entityName} e WHERE 1 = 1"
            + " AND e.username = :username "
            + " OR e.email = :email "
            + " OR e.phone = :phone "
            + " AND e.password = :password"
    )
    List<TbsUser> queryBy(@Param("username") String username,
                          @Param("email") String email,
                          @Param("phone") String phone,
                          @Param("password") String password);

}
