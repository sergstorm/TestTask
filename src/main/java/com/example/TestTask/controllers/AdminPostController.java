package com.example.TestTask.controllers;

import com.example.TestTask.entities.Message;
import com.example.TestTask.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminPostController
{
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/adminPostList")
    public String admipngetpost(Model model)
    {
        Iterable<Message> messages=messageRepo.findMessageByApprovedIsFalse();
        model.addAttribute("mes",messages);
        return "adminPostList";
    }
    @GetMapping("/aprPost/{id}")
    public String editp(@PathVariable Long id,
                        Model model)
    {
        Message message= messageRepo.findById(id).get();
        message.setApproved(true);
        messageRepo.save(message);
        model.addAttribute("m",message);
        return "redirect:/adminPostList";
    }
//    @PostMapping("/aprPost/{id}")
//    public String editp(@PathVariable Long id,
//                        Model model)
//    {
//        Message message= messageRepo.findById(id).get();
//        message.setApproved(true);
//        messageRepo.save(message);
//        model.addAttribute("m",message);
//        return "redirect:/adminPostList";
//    }

}
