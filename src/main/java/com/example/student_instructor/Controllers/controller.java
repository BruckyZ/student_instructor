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

	@RequestMapping("/")
	private String mainpage(Model model)
	{
		model.addAttribute("Scourses", CoursesRepository.findAll());
		model.addAttribute("Cinstructer", InstructorsRepository.findAll());
		model.addAttribute("Cstudents", StudentsRepository.findAll());
		return "index";
	}

	@RequestMapping("/login")
	public String login()
	{
		return "login";
	}


	@GetMapping("/register")
	public String showRegistrationPage(Model model){
		model.addAttribute("user", new User());
		model.addAttribute("pagenumber","4");
		return "registration";
	}

	@PostMapping("/register")
	public String processRegistrationPage(
			@Valid @ModelAttribute("user") User user,
			BindingResult result,
			Model model){

		model.addAttribute("user", user);

		if (result.hasErrors()) {
			return "registration";
		} else {
			userService.saveUserData(user);
			model.addAttribute("message", "User Account Successfully Created");
		}
		return "index";
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

		return "redirect:/";
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
		return "redirect:/";
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
		return "redirect:/";
	}


	@GetMapping("/addinstructorstostudents/{id}")                 //instructors mapped by student
	public String addInstructors(@PathVariable("id") long studId, Model model)
	{
		Students thisstudents=StudentsRepository.findOne(new Long(studId));
		Iterable instructorinstudents=thisstudents.getInstructor();

		model.addAttribute("stud", thisstudents);
		//		model.addAttribute("instructur",InstructorsRepository.findOne(new Long(student_id)));
		model.addAttribute("studentslist",StudentsRepository.findAll());
		return "studentaddinstructor";
	}

	@GetMapping("/addstudentstoinstructors/{id}")                 //instructors mapped by student
	public String addStudents(@PathVariable("id") long instructor_id, Model model)
	{
		model.addAttribute("instructur",InstructorsRepository.findOne(new Long(instructor_id)));
		model.addAttribute("studentslist",StudentsRepository.findAll());
		return "studentaddinstructor";
	}

	@PostMapping("/addstudentstoinstructors/{stud_id}")
	public String addStudnttoActor(@RequestParam("instructor")String instructor_id,@PathVariable("stud_id")long studId, @ModelAttribute("anInstructor")
                                   Instructors I,Model model)
	{
		Students S=StudentsRepository.findOne(new Long(studId));
		S.addInstructor(InstructorsRepository.findOne(new Long(instructor_id)));
		StudentsRepository.save(S);
		model.addAttribute("instructorlist",InstructorsRepository.findAll());
		model.addAttribute("studnetlist",StudentsRepository.findAll());
		return "redirect:/";
	}

	@RequestMapping("/search")
	public String SearchResult()
	{
		Iterable<Instructors>instructors=InstructorsRepository.findAll();
		for(Instructors I:instructors)
		{
			System.out.println("Instructor Name"+I.getFirst_name());
		}
		for(Students S:StudentsRepository.findAll())
		{
			System.out.println("Get course"+S.getInstructor());
		}
		return "redirect:/";
	}
}
