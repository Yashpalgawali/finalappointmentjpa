package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Company;
import com.example.demo.model.Department;
import com.example.demo.service.CompanyService;
import com.example.demo.service.DepartmentService;

@Controller
public class DepartmentController {

	@Autowired
	DepartmentService deptserv;
	
	@Autowired
	Environment env;
	
	@Autowired
	CompanyService compserv;
	
	
	@GetMapping("/adddepartment")
	public String addDepartment(Model model) {
		List<Company> clist= compserv.getAllCOmpanies();
		model.addAttribute("clist", clist);
		return "AddDepartment";
	}
	
	@RequestMapping("/savedepartment")
	public String saveDepartment(@ModelAttribute("Department")Department depart,RedirectAttributes attr) {
		Department dept = deptserv.saveDepartment(depart);
		if(dept!=null) {
			attr.addFlashAttribute("response", "Department is saved Successfully");
			return "redirect:/viewdepartments";
		}
		else {
			attr.addFlashAttribute("response", "Department is not saved");
			return "redirect:/viewdepartments";
		}
	}
		
	@GetMapping("viewdepartments")
	public String viewDepartments(Model model) {
		List<Company> clist= compserv.getAllCOmpanies();
		model.addAttribute("appname", env.getProperty("spring.application.name"));
		model.addAttribute("clist", clist);
		return "ViewDepartments";
	}
	
	@RequestMapping("getdeptbycompid/{id}")
	@ResponseBody
	public List<Department> getDepartmentsByCompId(@PathVariable("id") Long id) {
		List<Department> deplist = deptserv.getAllDepartmentsByCompId(""+id);
		return deplist;
	}
	
	@RequestMapping("/editdeptbyid/{id}")
	public String getDeptByid(@PathVariable("id") String id, Model model ,RedirectAttributes attr) {
		Department dept = deptserv.getDeptByDeptId(id);
		if(dept!=null) {
			List<Company> clist = compserv.getAllCOmpanies();
			model.addAttribute("clist", clist);
			model.addAttribute("dept", dept);
			return "EditDepartment";
		}
		else {
			attr.addFlashAttribute("reserr", "");
			return "redirect:/viewdepartments";
		}
	}
	
	@RequestMapping("/updatedepartment")
	public String updateDepartment(@ModelAttribute("Department")Department depart, RedirectAttributes attr) {
		int result = deptserv.updateDepartment(depart);
		if(result > 0) {
			attr.addFlashAttribute("response", "Department updated successfully");
			return "redirect:/viewdepartments";
		}
		else {
			attr.addFlashAttribute("reserr", "Department is not updated ");
			return "redirect:/viewdepartments";
		}
	}
}
