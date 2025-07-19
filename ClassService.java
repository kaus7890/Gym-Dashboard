package in.sp.main.service;

import in.sp.main.UserDao.ClassDao;
import in.sp.main.entity.ClassEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {

    @Autowired
    private ClassDao classDao;

    public List<ClassEntity> getAllClasses() {
        return classDao.findAll();
    }

    public void saveClass(ClassEntity classEntity) {
        classDao.save(classEntity);
    }

    public void deleteClass(Long id) {
        classDao.deleteById(id);
    }
}
