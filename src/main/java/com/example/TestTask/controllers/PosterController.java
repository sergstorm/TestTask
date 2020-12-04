package com.example.TestTask.controllers;

import com.example.TestTask.entities.Message;
import com.example.TestTask.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Controller
public class PosterController
{
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/posterPostList")
    public String poster(Model model)
    {
        model.addAttribute("mes",messageRepo.findAll());
        return "posterPostList";
    }
    @GetMapping("/createPost")
    public String creat(Model model)
    {
        model.addAttribute("m",new Message());
        return "createPost";
    }
    @PostMapping("/createPost")
    public String createTodoPost(@RequestParam String text,@RequestParam String title)
    {
        Date date = new Date();
        Long time = date.getTime();
        System.out.println(text+"  =----------Text-- "+title+" -------Title-------- "+date+"--------DATE---------"+time);
        Message message = new Message(text,title,time);
        messageRepo.save(message);
        return "redirect:/posterPostList";
    }
    @GetMapping("/editPost/{id}")
    public String edit(@PathVariable Long id,Model model)
    {
        Message message= messageRepo.findById(id).get();
        model.addAttribute("m",message);
        return "editPost";
    }
    @PostMapping ("/editPost/{id}")
    public String editp(@PathVariable Long id,
                        @RequestParam String text,
                        @RequestParam String title,
                        Model model)
    {
        Message message= messageRepo.findById(id).get();
        message.setText(text);
        message.setTitle(title);
        message.setApproved(false);
        messageRepo.save(message);
        model.addAttribute("m",message);
        return "redirect:/posterPostList";
    }
    @Transactional
    @GetMapping("/todo/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        messageRepo.deleteMessageById(id);
        return "redirect:/posterPostList";
    }
}
