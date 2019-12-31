package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Project;
import com.example.demo.services.MapValidationErrorService;
import com.example.demo.services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController 
{
   @Autowired
   private ProjectService projectService;
   @Autowired
   private MapValidationErrorService mapValidationErrorService;
   
   @PostMapping("")
   public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
	   ResponseEntity<?> errMap = mapValidationErrorService.mapValidationService(result);
	   if(errMap!=null) return errMap;
	   Project createProject = projectService.saveOrUpdateProject(project);
	   return new ResponseEntity<Project>(project,HttpStatus.CREATED);
   }
   @GetMapping("/{projectId}")
   public ResponseEntity<?> getprojectById(@PathVariable String projectId){
	   Project project = projectService.findProjectsById(projectId);
	   return new ResponseEntity<Project>(project,HttpStatus.OK); 
   }
   @GetMapping("")
   public Iterable<Project> getAllProjects(){return projectService.getAllProjects();}
   
	@DeleteMapping("/{projectid}")
	public ResponseEntity<?> deleteProjectById(@PathVariable String projectid) {
		projectService.deleteProjectById(projectid);
		return new ResponseEntity<String>("Project with id '"+projectid+"' was deleted",HttpStatus.OK);
	}
	
}
	
	
	
	
	
