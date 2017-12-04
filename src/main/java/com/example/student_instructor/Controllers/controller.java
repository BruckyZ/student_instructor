package com.example.student_instructor.Controllers;


import com.example.student_instructor.entity.*;
import com.example.student_instructor.repository.coursesRepository;
import com.example.student_instructor.repository.instructorsRepository;
import com.example.student_instructor.repository.studentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
public class controller
{

	@Autowired
	UserService userService;

	@Autowired
	coursesRepository CoursesRepository;

	@Autowired
	studentsRepository StudentsRepository;

	@Autowired
	instructorsRepository InstructorsRepository;

//	@RequestMapping("/")
//	private String mainpage(Model model)
//	{
//		model.addAttribute("Scourses", CoursesRepository.findAll());
//		model.addAttribute("Cinstructer", InstructorsRepository.findAll());
//		model.addAttribute("Cstudents", StudentsRepository.findAll());
//		return "index";
//	}

	@RequestMapping("/")
	private String index(Model model)
	{
		return "index";
	}

	@RequestMapping("/login")
	public String login()
	{
		return "login";
	}


	@GetMapping("/register")
	public String showRegistrationPage(Model model)
	{
		model.addAttribute("user", new User());
		return "registration";
	}

	@PostMapping("/register")
	public String processRegistrationPage(
			@Valid @ModelAttribute("user") User user,
			BindingResult result,
			Model model)
	{

		model.addAttribute("user", user);

		if (result.hasErrors())
		{
			return "registration";
		}
		else
		{
			userService.saveAdmin(user);
			model.addAttribute("message", "User Account Successfully Created");
		}
		return "/login";
	}


	@GetMapping("/addcourse")
	private String coursesform(Model model)
	{
		model.addAttribute("cours", new Courses());
		return "coursesform";
	}

	@PostMapping("/processcourse")
	private String processcourseform(@Valid Courses cours, BindingResult result)
	{
		if (result.hasErrors())
		{
			return "coursesform";
		}
		CoursesRepository.save(cours);

		return "redirect:/courselist";
	}


	@RequestMapping("/courselist")
	private String courselist(Model model)
	{
		model.addAttribute("Scourses", CoursesRepository.findAll());
		return "Courselist";
	}

	@GetMapping("/addinstructors")
	private String instructorsform(Model model)
	{
		model.addAttribute("instructer", new Instructors());
		return "instructorsform";
	}

	@PostMapping("/processinstructors")
	private String processinstructorsform(@Valid Instructors instructer, BindingResult result)
	{
		if (result.hasErrors())
		{
			return "instructorsform";
		}
		InstructorsRepository.save(instructer);
		return "redirect:/instructlist";
	}

	@RequestMapping("/instructlist")
	private String Instructlist(Model model)
	{
		model.addAttribute("Cinstructer", InstructorsRepository.findAll());
		return "Instructorlist";
	}


	@GetMapping("/addstudents")
	private String studnentsform(Model model)
	{
		model.addAttribute("studant", new Students());
		return "studentsform";
	}

	@PostMapping("/processstudents")
	private String processstudentsform(@Valid Students studant, BindingResult result)
	{
		if (result.hasErrors())
		{
			return "studentsform";
		}
		StudentsRepository.save(studant);
		return "redirect:/studentlist";
	}

	@RequestMapping("/studentlist")
	private String Studentlist(Model model)
	{
		model.addAttribute("Cstudents", StudentsRepository.findAll());
		return "Studentlist";
	}


	@GetMapping("/addstudentstoinstructors/{id}")                 //instructors mapped by student
	public String addStudents(@PathVariable("id") long instructor_id, Model model)
	{

		model.addAttribute("instructer", InstructorsRepository.findOne(new Long(instructor_id)));
		model.addAttribute("Studentlist", StudentsRepository.findAll());
		return "studentaddinstructor";
	}

	@PostMapping("/addstudentstoinstructors")
	public String addStudnttoActor(@RequestParam("Students") long studId,
	                               @ModelAttribute("instructer")
			                               Instructors I, Model model)
	{
		Students S = StudentsRepository.findOne(new Long(studId));
		S.addInstructor(I);
		StudentsRepository.save(S);
		model.addAttribute("Instructorlist", InstructorsRepository.findAll());
		model.addAttribute("Studentlist", StudentsRepository.findAll());
		return "redirect:/";
	}

	@GetMapping("/searchform")
	public String searchlist(Model model)
	{
		return "searchform";
	}

	@PostMapping("/searchform")
	public String searchform(@RequestParam("searchtext") String searchtext, Model model)
	{
		model.addAttribute("SearchStudent", StudentsRepository.findAllByEmailContains(searchtext));
		return "searchlist";
	}

}
