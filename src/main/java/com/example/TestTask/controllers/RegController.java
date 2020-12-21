package com.example.TestTask.controllers;

import com.example.TestTask.entities.Role2;
import com.example.TestTask.entities.User2;
import com.example.TestTask.repo.Role2Repo;
import com.example.TestTask.repo.User2Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class RegController
{

    @Autowired
    private User2Repo user2Repo;

    @GetMapping("login2")
    public String loginUser()
    {
        return "login2";
    }

//    @RequestMapping(value = "/login2",method = RequestMethod.POST)
//            public String postlogin()
//    {
//        System.out.println("=========================POST==============================");
//        return "/login2";
//    }

       @PostMapping("/login2")
  public String loginUsefr(
                           User2 user2,
                           BindingResult bindingResult,
                           Model model)
   {
    if (bindingResult.hasErrors())
    {
        model.addAttribute("mes",bindingResult.getAllErrors());
        System.out.println(bindingResult.getAllErrors()+"  ------  ERRORS ----------");
        return "login2";
    }
    // String username= principal.getName();
     user2 = user2Repo.findByUsername(user2.getUsername());
    if (user2==null)
    {
        model.addAttribute("mes","User not exist");
        return "login2";
    }
    // System.out.println(user1.getName()+"   --------U  --"+user1.getRoles()+" t t "+Role.values());
   // Set<Role> roles = user1.getRoles();
    Collection roles = user2.getRoles();
    Set<Role2> list = Set.copyOf(roles);
    System.out.println(roles+"-------Roles--------------------------WORKING-----------------------------------------------------");
    System.out.println(list.stream().map(Role2::getName).findAny().get());
    System.out.println(roles);
    String a = list.stream().map(Role2::getName).findAny().get();


    if (a.equals("ROLE_ADMIN")) return "redirect:/adminPostList";
    if (a.equals("ROLE_VISITOR")) return "redirect:/visitorPostList";
    if (a.equals("ROLE_POSTER")) return "redirect:/posterPostList";
    else
        return "redirect:/main";
  }


   @GetMapping("reg")
    public String regg()
   {
       return "reg";
   }
   @PostMapping("reg")
    public String reg(
           @Validated String A,
           User2 user2, Model model)
   {
       User2 userfdb2 = user2Repo.findByUsername(user2.getUsername());
       if (userfdb2!=null)
       {
           model.addAttribute("mes","User Exists");
           return "reg";
       }
       if (A.equals("A"))
       {
           Role2 role2 = new Role2();
           role2.setName("ROLE_ADMIN");
           user2.setRoles(Collections.singleton(role2));
           System.out.println(user2.getRoles()+ " ROLE2 ");
       }
       if (A.equals("P"))
       {
           Role2 role2 = new Role2();
           role2.setName("ROLE_POSTER");
           user2.setRoles(Collections.singleton(role2));
           System.out.println(user2.getRoles()+ " ROLE2 ");
       }
       if (A.equals("V"))
       {
           Role2 role2 = new Role2();
           role2.setName("ROLE_VISITOR");
           user2.setRoles(Collections.singleton(role2));
           System.out.println(user2.getRoles()+ " ROLE2 ");
       }
       user2.setEmail("se@email.com");
       user2.setUsername(user2.getUsername());
       user2.setPassword(user2.getPassword());
       System.out.println("User2 R" +user2.getRoles()+user2.getUsername()+user2.getPassword()+" ---A--- "+A);
       System.out.println("--------USER2----------------"+user2);
       Set<Role2> roles = (Set)user2.getRoles();
       System.out.println(roles+"-------Roles------"+roles);

      // role2Repo.save();
       user2Repo.save(user2);
       return "redirect:/login2";
   }

}
