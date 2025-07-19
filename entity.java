
// Class Entity
package in.sp.main.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "classes")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String className;
    private String time;       // e.g., "08:00 AM"
    private String schedule;   // e.g., "Mon, Wed, Fri"

    public ClassEntity() {}

    public ClassEntity(String className, String time, String schedule) {
        this.className = className;
        this.time = time;
        this.schedule = schedule;
    }

    // Getters and setters below

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }
}


// Contact Message

package in.sp.main.entity;

import jakarta.persistence.*;

@Entity
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String message;

    // Constructors
    public ContactMessage() {}

    public ContactMessage(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}


// Trainer data

package in.sp.main.entity;

public class Trainer {
    private int trainer_id;
    private String trainer_name;
    private String specialization;

    // Constructors
    public Trainer() {}

    public Trainer(int trainer_id, String trainer_name, String specialization) {
        this.trainer_id = trainer_id;
        this.trainer_name = trainer_name;
        this.specialization = specialization;
    }

    // Getters and Setters
    public int getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(int trainer_id) {
        this.trainer_id = trainer_id;
    }

    public String getTrainer_name() {
        return trainer_name;
    }

    public void setTrainer_name(String trainer_name) {
        this.trainer_name = trainer_name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}


// User data


package in.sp.main.entity;

import java.time.LocalDate;

public class user {
    private int mem_id;
    private String mem_name;
    private int mem_age;
    private String mem_plan;
    
    private LocalDate join_date;
    private LocalDate expiry_date;
    
    public user() {
    }

    public user(int mem_id, String mem_name, int mem_age, String mem_plan) {
        this.mem_id = mem_id;
        this.mem_name = mem_name;
        this.mem_age = mem_age;
        this.mem_plan = mem_plan;
    }

    // New constructor with join_date and expiry_date
    public user(int mem_id, String mem_name, int mem_age, String mem_plan, LocalDate join_date, LocalDate expiry_date) {
        this.mem_id = mem_id;
        this.mem_name = mem_name;
        this.mem_age = mem_age;
        this.mem_plan = mem_plan;
        this.join_date = join_date;
        this.expiry_date = expiry_date;
    }

    // Getters and setters

    public int getMem_id() {
        return mem_id;
    }

    public void setMem_id(int mem_id) {
        this.mem_id = mem_id;
    }

    public String getMem_name() {
        return mem_name;
    }

    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }

    public int getMem_age() {
        return mem_age;
    }

    public void setMem_age(int mem_age) {
        this.mem_age = mem_age;
    }

    public String getMem_plan() {
        return mem_plan;
    }

    public void setMem_plan(String mem_plan) {
        this.mem_plan = mem_plan;
    }

    public LocalDate getJoin_date() {
        return join_date;
    }

    public void setJoin_date(LocalDate join_date) {
        this.join_date = join_date;
    }

    public LocalDate getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(LocalDate expiry_date) {
        this.expiry_date = expiry_date;
    }
}
