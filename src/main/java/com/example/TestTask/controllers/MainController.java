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
public class MainController
{
    @Autowired
    private MessageRepo messageRepo;
    @GetMapping("/main")
    public String main(Model model)
    {
       model.addAttribute("mes",messageRepo.findAll());
        return "main";
    }
    @GetMapping("/visitorPostList")
    public String admipngetpost(Model model)
    {
        Iterable<Message> messages = messageRepo.findMessageByApprovedIsTrue();
        model.addAttribute("mes",messages);
        return "visitorPostList";
    }
    @PostMapping("/visitorPostList")
    public String search(@RequestParam String title, Model model)
    {
        Iterable<Message> messages;
        if (title!=null && !title.isEmpty())
        {
            messages=messageRepo.findByTitle(title);
            model.addAttribute("mes",messages);
        }
        else
            {
                model.addAttribute("mes",messageRepo.findMessageByApprovedIsTrue());
            }
        return "visitorPostList";
    }
}
