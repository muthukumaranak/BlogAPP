package com.example.blogapp.controller;

import com.example.blogapp.entity.addBlog;
import com.example.blogapp.service.LoginService;
import com.example.blogapp.service.RegisterService;
import com.example.blogapp.service.addBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogController {

    @Autowired
    RegisterService registerService;

    @Autowired
    LoginService loginService;

    @Autowired
    addBlogService addBlogService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    @RequestMapping("/addblog")
    public String addblog(){
        return "addblog";
    }


    @RequestMapping("/adminlogin")
    public String adminlogin(){
        return "adminlogin";
    }

    @RequestMapping("/adminmodule")
    public String adminmodule(){
        return "adminmodule";
    }

    @RequestMapping("/allblogs")
    public String allblogs(){
        return "allblogs";
    }

    @RequestMapping("/loginpage")
        public String loginpage(Model model, @RequestParam String username, @RequestParam String password){
            String result = loginService.logincheck(username,password);
            String name = loginService.getName(username);
            if(result.equals("positive")){
                model.addAttribute("name",name);
                List<addBlog> list = (List<addBlog>)addBlogService.getall();
                model.addAttribute("list", list);
                return "loginpage";
            }
            return "indexerror";
    }

    @RequestMapping("/adminmodulecheck")
    public String adminmodulecheck(@RequestParam String username, @RequestParam String password){
        String result = loginService.logincheck(username,password);
        if(result.equals("positive"))
            return "adminmodule";
        return "adminloginerror";
    }

    @RequestMapping("/myblogs")
    public String myblogs(){
        return "myblogs";
    }

    @RequestMapping("/registrationpage")
    public String registrationpage(){
        return "registrationpage";
    }

    @RequestMapping("/users")
    public String users(){
        return "users";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String email, @RequestParam String password ) {
        String result = registerService.add(name,email,password);
        if(result.equals("positive"))
            return "index";
        else
            return "errorpage";
    }

    @PostMapping("/addingblog")
    public String addingblog(@RequestParam String email, @RequestParam String title, @RequestParam String blogcontent){
        addBlogService.addBlog(email,title,blogcontent);
        return "loginpage";
    }
}
