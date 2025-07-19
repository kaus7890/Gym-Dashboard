package in.sp.main.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.sp.main.UserDao.UserDao;
import in.sp.main.UserDao.TrainerDao;
import in.sp.main.UserDao.ClassDao;
import in.sp.main.entity.user;
import in.sp.main.entity.Trainer;
import in.sp.main.entity.ClassEntity;
import in.sp.main.entity.ContactMessage;
import in.sp.main.repository.ContactMessageRepository;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TrainerDao trainerDao;

    @Autowired
    private ClassDao classDao;

    @Autowired
    private ContactMessageRepository contactMessageRepository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/form")
    public String showForm() {
        return "userform";
    }

    @GetMapping("/form-trainer")
    public String showTrainerForm() {
        return "trainerform";
    }

    @GetMapping("/add-class")
    public String showAddClassForm(Model model) {
        model.addAttribute("classEntity", new ClassEntity());
        return "add-class";
    }

    @GetMapping("/members")
    public String showMembers(Model model, @ModelAttribute("msg") String msg) {
        List<user> members = userDao.getAllUsers();
        model.addAttribute("members", members);
        if (!msg.isEmpty()) {
            model.addAttribute("msg", msg);
        }
        return "members";
    }

    @PostMapping("/add-user")
    public String addUser(@RequestParam int mem_id,
                          @RequestParam String mem_name,
                          @RequestParam int mem_age,
                          @RequestParam String mem_plan,
                          Model model) {

        LocalDate joinDate = LocalDate.now();
        LocalDate expiryDate = switch (mem_plan) {
            case "Monthly" -> joinDate.plus(1, ChronoUnit.MONTHS);
            case "Quarterly" -> joinDate.plus(3, ChronoUnit.MONTHS);
            case "Annual" -> joinDate.plus(1, ChronoUnit.YEARS);
            default -> joinDate;
        };

        user user1 = new user(mem_id, mem_name, mem_age, mem_plan, joinDate, expiryDate);
        boolean status = userDao.insertUser(user1);

        String cost = switch (mem_plan) {
            case "Monthly" -> "₹1,000";
            case "Quarterly" -> "₹2,700";
            case "Annual" -> "₹10,000";
            default -> "N/A";
        };

        model.addAttribute("message", status ? "Member added successfully!" : "Member registration failed.");
        model.addAttribute("plan", mem_plan);
        model.addAttribute("cost", cost);
        model.addAttribute("expiry", expiryDate);

        return "result";
    }

    @GetMapping("/delete-member")
    public String deleteMember(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        userDao.deleteUserById(id);
        redirectAttributes.addFlashAttribute("msg", "Member deleted successfully!");
        return "redirect:/members";
    }

    @GetMapping("/trainers")
    public String showTrainers(Model model, @ModelAttribute("msg") String msg) {
        List<Trainer> trainers = trainerDao.getAllTrainers();
        model.addAttribute("trainers", trainers);
        if (!msg.isEmpty()) {
            model.addAttribute("msg", msg);
        }
        return "trainer";
    }

    @PostMapping("/add-trainer")
    public String addTrainer(@RequestParam int trainer_id,
                             @RequestParam String trainer_name,
                             @RequestParam String specialization,
                             RedirectAttributes redirectAttributes) {

        Trainer trainer = new Trainer(trainer_id, trainer_name, specialization);
        trainerDao.insertTrainer(trainer);
        redirectAttributes.addFlashAttribute("msg", "Trainer added successfully!");
        return "redirect:/trainers";
    }

    @GetMapping("/delete-trainer")
    public String deleteTrainer(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        trainerDao.deleteTrainerById(id);
        redirectAttributes.addFlashAttribute("msg", "Trainer deleted successfully!");
        return "redirect:/trainers";
    }

    @GetMapping("/classes")
    public String showClasses(Model model) {
        List<ClassEntity> classes = classDao.findAll();
        model.addAttribute("classes", classes);
        return "classes";
    }

    @PostMapping("/add-class")
    public String addClass(@ModelAttribute("classEntity") ClassEntity classEntity, RedirectAttributes redirectAttributes) {
        classDao.save(classEntity);
        redirectAttributes.addFlashAttribute("msg", "Class added successfully!");
        return "redirect:/classes";
    }

    @GetMapping("/delete-class")
    public String deleteClass(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        classDao.deleteById(id);
        redirectAttributes.addFlashAttribute("msg", "Class deleted successfully!");
        return "redirect:/classes";
    }
    
    
    @GetMapping("/submit-contact")
    public String saveContactMessage(@ModelAttribute ContactMessage contactMessage, RedirectAttributes redirectAttributes) {
        contactMessageRepository.save(contactMessage);
        redirectAttributes.addFlashAttribute("msg", "Your message has been sent successfully!");
        return "redirect:/";
    }


    @PostMapping("/submit-contact")
    public String submitContact(@RequestParam String name,
                                @RequestParam String email,
                                @RequestParam String message,
                                RedirectAttributes redirectAttributes) {
        ContactMessage contact = new ContactMessage(name, email, message);
        contactMessageRepository.save(contact);
        redirectAttributes.addFlashAttribute("msg", "Message sent successfully!");
        return "redirect:/";
    }

    @GetMapping("/view-messages")
    public String viewMessages(Model model) {
        model.addAttribute("messages", contactMessageRepository.findAll());
        return "view-messages";
    }


    
}
