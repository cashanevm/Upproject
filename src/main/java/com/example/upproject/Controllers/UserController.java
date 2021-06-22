package com.example.upproject.Controllers;


import com.example.upproject.DAO.EmployeeDAO;
import com.example.upproject.Entities.Employee;
import com.example.upproject.Entities.Project;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.*;

@Controller
public class UserController {
    @Autowired
    EmployeeDAO employeeDAO;


    @GetMapping("/")
    public String index(Model model) throws SQLException {

        String[] employeeData = { "Peter Oven", "Allan Norman" };
        String[] projectData = { "IT Project", "Networking Project" };
        Set<Project> projects = new HashSet<>();

        for (String proj : projectData) {
            projects.add(new Project(proj));
        }

        for (String emp : employeeData) {
            Employee employee = new Employee(emp.split(" ")[0],
                    emp.split(" ")[1]);

            //assertEquals(0, employee.getProjects().size());
            employee.setProjects(projects);
            employeeDAO.save(employee);

            //assertNotNull(employee);
        }

        model.addAttribute("users",employeeDAO.findAll());
        return "index";
    }

//
//
//
//
//    @GetMapping("/signup")
//    public String showSignUpForm(Employee user) {
//
//
//
//        return "add-user";
//
//
//    }
//
//    @PostMapping("/adduser")
//    public String addUser(@Valid Employee user, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "add-user";
//        }
//
//        //userRepository.save(user);
//        return "redirect:/";
//    }
//    @GetMapping("/addrole")
//    public String showRole( @RequestParam(name = "id") String id, Model model ){
//
//        model.addAttribute("user", this.get(Employee.class, Long.valueOf(id)));
//        model.addAttribute("projects", this.getAll(Project.class));
//        return "add-role";
//
//
//    }
//    @PostMapping("/addrole")
//    public String addRole( @RequestParam(name = "position") String position,@RequestParam(name = "id") String id, Model model ){
//        Employee employee = this.get(Employee.class, Long.valueOf(id));
//        Project project = this.get(Project.class, Long.valueOf(position));
//        employee.addPosition(project);
//        project.addUser(employee);
//        this.saveOrUpdate(employee);
//        this.saveOrUpdate(project);
//        return "redirect:/";
//    }
//
//
//
//    @GetMapping("/addmainrole")
//    public String addMainRole() {
//
//            return "add-role__body";
//
//
//    }
//
//    @PostMapping("/addmainrole")
//    public String addMainRole(@RequestParam(name = "title") String title, Model model) {
//        Project project = new Project(title);
//        this.save(project);
//        return "redirect:/";
//    }
//
//    @GetMapping("/deletemainrole")
//    public String deleteMainRole(@RequestParam(name = "id") String id, Model model) {
//        Project project = this.get(Project.class, Long.parseLong(id));
//        this.delete(project);
//
//        System.out.println(project.title);
//
//        return "redirect:/";
//    }
//
//
//
//    public <T> T save(final T o){
//        return (T) sessionFactory.getCurrentSession().save(o);
//    }
//
//
//    public void delete(final Object object){
//
//
//
//        sessionFactory.getCurrentSession().delete(object);
//    }
//
//    /***/
//    public <T> T get(final Class<T> type, final long id){
//        return (T) sessionFactory.getCurrentSession().get(type, id);
//    }
//
//    /***/
//    public <T> T merge(final T o)   {
//        return (T) sessionFactory.getCurrentSession().merge(o);
//    }
//
//    /***/
//    public <T> void saveOrUpdate(final T o){
//        sessionFactory.getCurrentSession().saveOrUpdate(o);
//    }
//
//    public <T> List<T> getAll( Class<T> type) {
//         Session session = sessionFactory.getCurrentSession();
//         Criteria crit = session.createCriteria(type);
//        return crit.list();
//    }
//
//
//
}
