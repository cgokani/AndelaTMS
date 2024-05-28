package com.andela.tms.bootstrap;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.andela.tms.entities.Role;
import com.andela.tms.entities.RoleEnum;
import com.andela.tms.entities.Task;
import com.andela.tms.entities.TaskStatusEnum;
import com.andela.tms.entities.User;
import com.andela.tms.repositories.RoleRepository;
import com.andela.tms.repositories.TaskRepository;
import com.andela.tms.repositories.UserRepository;

@Component
public class Seeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Seeder(RoleRepository roleRepository,TaskRepository taskRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
		this.taskRepository = taskRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.loadRoles();
        this.loadUser();
        this.loadAdmin();
        this.loadTask();
    }

    private void loadRoles() {
        RoleEnum[] roleNames = new RoleEnum[] { RoleEnum.USER, RoleEnum.ADMIN };
        Map<RoleEnum, String> roleDescriptionMap = Map.of(
            RoleEnum.USER, "Default user role",
            RoleEnum.ADMIN, "Administrator role"
        );

        Arrays.stream(roleNames).forEach((roleName) -> {
            Optional<Role> optionalRole = roleRepository.findByName(roleName);

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                Role roleToCreate = new Role();

                roleToCreate.setName(roleName);
                roleToCreate.setDescription(roleDescriptionMap.get(roleName));

                roleRepository.save(roleToCreate);
            });
        });
    }
    private void loadUser() {
       
    	 Optional<User> optionalUser = userRepository.findByEmail("cgokani@gmail.com");
    	 
    	 Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);
	        
		    if (optionalRole.isEmpty()) {
		        return ;
		    }
    	 
    	 optionalUser.ifPresentOrElse(System.out::println, () -> {
    		 
              User userToCreate = new User();

              userToCreate.setEmail("cgokani@gmail.com");
              userToCreate.setPassword(passwordEncoder.encode("qwerty"));
              userToCreate.setFullName("Chintan Gokani");
              userToCreate.setRole(optionalRole.get());
              

              userRepository.save(userToCreate);
          });
    }
    
    private void loadAdmin() {
        
   	 Optional<User> optionalUser = userRepository.findByEmail("cgokani1@gmail.com");
   	 
   	 Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);
	        
		    if (optionalRole.isEmpty()) {
		        return ;
		    }
   	 
   	 optionalUser.ifPresentOrElse(System.out::println, () -> {
   		 
             User userToCreate = new User();

             userToCreate.setEmail("cgokani1@gmail.com");
             userToCreate.setPassword(passwordEncoder.encode("qwerty"));
             userToCreate.setFullName("Chintan Gokani");
             userToCreate.setRole(optionalRole.get());
             

             userRepository.save(userToCreate);
         });
   }
    
    private void loadTask() {
        
   	 Optional<Task> optionalTask = taskRepository.findById(1);
   	 
   	 optionalTask.ifPresentOrElse(System.out::println, () -> {
             Task taskToCreate = new Task();

             taskToCreate.setTitle("Task 1");
             taskToCreate.setDescription("New Task");
             taskToCreate.setStatus(TaskStatusEnum.TO_DO);
             taskToCreate.setPriority(5);
             
             taskRepository.save(taskToCreate);
             
             Task taskToCreate2 = new Task();

             taskToCreate2.setTitle("Task 2");
             taskToCreate2.setDescription("New Task");
             taskToCreate2.setStatus(TaskStatusEnum.TO_DO);
             taskToCreate2.setPriority(6);
             
             taskRepository.save(taskToCreate2);
         });
   }
}