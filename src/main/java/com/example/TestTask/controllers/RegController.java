package com.example.TestTask.controllers;

import com.example.TestTask.entities.Role;
import com.example.TestTask.entities.User;
import com.example.TestTask.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Collections;
import java.util.Set;

@Controller
public class RegController
{
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/login2")
    public String loginUser()
    {
        return "login2";
    }
    @PostMapping("/login2")
    public String loginUsefr(User user, Principal principal)
    {
      // String username= principal.getName();
       User user1 = userRepo.findByName(user.getName());
       // System.out.println(user1.getName()+"   --------U  --"+user1.getRoles()+" t t "+Role.values());
        Set<Role> roles = user1.getRoles();
        System.out.println(roles.stream().map(Role::name).findAny().get());
        System.out.println(roles);
       String a = roles.stream().map(Role::name).findAny().get();

         if (a.equals("ADMIN")) return "redirect:/adminPostList";
         if (a.equals("VISITOR")) return "redirect:/visitorPostList";
         if (a.equals("POSTER")) return "redirect:/posterPostList";
         else return "main";
    }
   @GetMapping("reg")
    public String regg()
   {
       return "reg";
   }
   @PostMapping("reg")
    public String reg(
           @Validated String A,
           User user, Model model)
   {
       User fromDBUser = userRepo.findByName(user.getName());
       if (fromDBUser!=null)
       {
           model.addAttribute("mes","UserExists");
           return "reg";
       }
       System.out.println("A ---------=================="+A);

       if (A.equals("A")) { user.setRoles(Collections.singleton(Role.ADMIN)); }
       if (A.equals("V")) { user.setRoles(Collections.singleton(Role.VISITOR)); }
       if (A.equals("P")) { user.setRoles(Collections.singleton(Role.POSTER)); }

       System.out.println(user.getName()+user.getPassword()+"-------------------user-------------------");
       System.out.println(user+"-------------------ROLE-------------------");
       user.setName(user.getName());
       user.setPassword(user.getPassword());
       user.setActive(true);
      // user.setRoles(Collections.singleton(Role.VISITOR));
       System.out.println(user.getAuthorities());
     //  user.setAuthorities(new ArrayList(Arrays.asList("ADMIN")));
       userRepo.save(user);
       return "redirect:/login2";
   }

}
