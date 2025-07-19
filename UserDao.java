
// Class dao
package in.sp.main.UserDao;

import in.sp.main.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassDao extends JpaRepository<ClassEntity, Long> {
    // JpaRepository provides all CRUD operations by default
}

// TrainerDaoImpl

package in.sp.main.UserDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;
import in.sp.main.entity.Trainer;

@Repository
public class TrainerDaoImpl implements TrainerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertTrainer(Trainer trainer) {
        String sql = "INSERT INTO trainer (trainer_id, trainer_name, specialization) VALUES (?, ?, ?)";
        int result = jdbcTemplate.update(sql, trainer.getTrainer_id(), trainer.getTrainer_name(), trainer.getSpecialization());
        return result > 0;
    }

    @Override
    public List<Trainer> getAllTrainers() {
        String sql = "SELECT * FROM trainer";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Trainer.class));
    }

    @Override
    public void deleteTrainerById(int id) {
        String sql = "DELETE FROM trainer WHERE trainer_id = ?";
        jdbcTemplate.update(sql, id);
    }
}


//Trainer Dao

package in.sp.main.UserDao;

import java.util.List;
import in.sp.main.entity.Trainer;

public interface TrainerDao {
    boolean insertTrainer(Trainer trainer);
    List<Trainer> getAllTrainers();
    void deleteTrainerById(int id);
}




// package in.sp.main.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import in.sp.main.entity.user;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean insertUser(user user) {
        boolean status = false;
        try {
            String sql = "INSERT INTO users (mem_id,mem_name, mem_age, mem_plan, join_date, expiry_date) VALUES (?, ?, ?, ?, ?,?)";
            int count = jdbcTemplate.update(sql,
            		user.getMem_id(),
                    user.getMem_name(),
                    user.getMem_age(),
                    user.getMem_plan(),
                    Date.valueOf(user.getJoin_date()),
                    Date.valueOf(user.getExpiry_date())
            );
            status = count > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public List<user> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToUser(rs));
    }

    public boolean deleteUserById(int id) {
        boolean status = false;
        try {
            String sql = "DELETE FROM users WHERE mem_id = ?";
            int count = jdbcTemplate.update(sql, id);
            status = count > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    private user mapRowToUser(ResultSet rs) throws SQLException {
        user u = new user();
        u.setMem_id(rs.getInt("mem_id"));
        u.setMem_name(rs.getString("mem_name"));
        u.setMem_age(rs.getInt("mem_age"));
        u.setMem_plan(rs.getString("mem_plan"));
        u.setJoin_date(rs.getDate("join_date").toLocalDate());
        u.setExpiry_date(rs.getDate("expiry_date").toLocalDate());
        return u;
    }
}
