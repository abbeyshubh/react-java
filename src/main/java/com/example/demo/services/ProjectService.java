package com.example.demo.services;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Project;
import com.example.demo.exceptions.ProjectIdException;
import com.example.demo.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepo;
	public Project saveOrUpdateProject(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepo.save(project);	
		}catch(Exception e) {
			throw new ProjectIdException("ProjectId '"+project.getProjectIdentifier().toUpperCase()+"' already exists.");
		}
		
		
	}
	public Project findProjectsById(String projectId) {
		Project project =  projectRepo.findByProjectIdentifier(projectId);
		if(project==null) {
			throw new ProjectIdException("ProjectId '"+projectId+"' does not exists.");
		}
		return project;
		}
	public Iterable<Project> getAllProjects() {
		Iterable<Project> project = projectRepo.findAll();
		if(project==null) {
			throw new ProjectIdException("No Projects Available");
		}
		return project;
	}
	public void deleteProjectById(String projectid) {
		Project project =  projectRepo.findByProjectIdentifier(projectid);
		if(project==null) {
			throw new ProjectIdException("ProjectId '"+projectid+"' does not exists.");
		}
		projectRepo.delete(project);
		}
//	public Project updateProjectById(Long id,String proId) {
//		Project project = projectRepo.findByProjectIdentifier(proId);
//		if(project==null) {
//			throw new ProjectIdException("ProjectId '"+proId+"' does not exists.");
//		}
//		project.setId(id);
//		return projectRepo.save(project);
//		
//	}
}
