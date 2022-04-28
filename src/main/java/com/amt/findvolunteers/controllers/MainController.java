package com.amt.findvolunteers.controllers;

import java.util.List;

//import java.util.List;
//import javax.validation.Valid;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.amt.findvolunteers.models.Event;
import com.amt.findvolunteers.models.LoginUser;
import com.amt.findvolunteers.models.User;
import com.amt.findvolunteers.services.EventService;
import com.amt.findvolunteers.services.UserService;

@Controller
public class MainController {
//	-----------------------variables-----------------------
    @Autowired
    private UserService userService;
    
    @Autowired
    private EventService eventService;

    // landing page
    @GetMapping("/")
    public String index(Model model, HttpSession session) {
    	model.addAttribute("newUser", new User());
    	model.addAttribute("newLogin", new LoginUser());
    	model.addAttribute("events", eventService.allEvents());
    	if (session.getAttribute("user_id") != null) {
    		Long userId = (Long) session.getAttribute("user_id");
    		User currentUser = userService.findUser(userId);
    		model.addAttribute("user", currentUser);
    	}
        return "/index.jsp";
    }
    
    @GetMapping("/dashboard")
    public String home(HttpSession session, Model model) {
    	Long userId = (Long) session.getAttribute("user_id");
    	if (userId == null) {
    		return "redirect:/";
    	} else {
    		User currentUser = userService.findUser(userId);
    		List<Event> events = eventService.allEvents();
    		model.addAttribute("user", currentUser);
    	    model.addAttribute("events", events);
            model.addAttribute("newEvent", new Event());
    		return "dashboard.jsp";
    	}
    }
    
    // about no login required
    @GetMapping("/about")
    public String about(HttpSession session, Model model) {
    	model.addAttribute("newUser", new User());
    	model.addAttribute("newLogin", new LoginUser());
    	if (session.getAttribute("user_id") != null) {
    		Long userId = (Long) session.getAttribute("user_id");
    		User currentUser = userService.findUser(userId);
    		model.addAttribute("user", currentUser);
    	}
    	return "/about.jsp";
    }

    // contact no login required
    @GetMapping("/contact")
    public String contact(HttpSession session, Model model) {
    	model.addAttribute("newUser", new User());
    	model.addAttribute("newLogin", new LoginUser());
    	if (session.getAttribute("user_id") != null) {
    		Long userId = (Long) session.getAttribute("user_id");
    		User currentUser = userService.findUser(userId);
    		model.addAttribute("user", currentUser);
    	}
    	return "/contact.jsp";
    }
    @PostMapping("/contact")
    public String sendContact() {
    	return null;
    }
    
    @GetMapping("/allEvents")
    public String allEvents(HttpSession session, Model model) {
    	List<Event> events = eventService.allEvents();
    	model.addAttribute("events", events);
    	model.addAttribute("newUser", new User());
    	model.addAttribute("newLogin", new LoginUser());
    	if (session.getAttribute("user_id") != null) {
    		Long userId = (Long) session.getAttribute("user_id");
    		User currentUser = userService.findUser(userId);
    		model.addAttribute("user", currentUser);
    	}
    	return "events.jsp";
    }
    

//    // create
//    @GetMapping("/<project name>/new")
//    public String new(@ModelAttribute("<project table>") <Project Table> <project table>, HttpSession session) {
//        Long userId = (Long) session.getAttribute("user_id");
//    	if (userId == null) {
//    	    return "redirect:/";
//    	} else {
//            return "/<project name>/new<ProjectTable>.jsp
//    }
//    @PostMapping("/<project name>/new")
//    public String create(@Valid @ModelAttribute("<project table>") <Project Table> <project table>, BindingResult result, HttpSession session) {
//        Long userId = (Long) session.getAttribute("user_id");
//    	if (userId == null) {
//    	    return "redirect:/";
//    	} else {
//    	    if (result.hasErrors()) {
//    		return "/<project name>/index.jsp";
//    	    } else {
//    		<project table>Service.create<Project Table>(<project table>);
//    		return "redirect:/<project name>";
//    	    }
//        }
//    }

//    // display one found by id
//    @GetMapping("/<project name>/{id}")
//    public String showOne<project table>ById(@PathVariable("id") Long id, HttpSession session, Model model) {
//        Long userId = (Long) session.getAttribute("user_id");
//    	if (userId == null) {
//    	    return "redirect:/";
//    	} else {
//            <Project Table> <project table>ToShow = <project table>Service.find<Project Table>(id);
//            model.addAttribute("<project table>", <project table>ToShow);
//	    return "/<project name>/show.jsp";
//        }
//    }

//    // update one found by id
//    @GetMapping("/<project name>/{id}/edit")
//    public String edit(@PathVariable("id") Long id, HttpSession session, Model model) {
//        Long userId = (Long) session.getAttribute("user_id");
//    	if (userId == null) {
//    	    return "redirect:/";
//    	} else {
//    	    <Project Table> <project table>ToShow = <project table>Service.find<Project Table>(id);
//            if (userId.equals(<project table>ToShow.get
//	    model.addAttribute("<project table>", <project table>ToShow);
//	    return "/<project name>/edit.jsp";
//        }
//    }
//    @PutMapping("/<project name>/{id}")
//    public String update(@Valid @ModelAttribute("<project table>") <Project Table> <project table>, BindingResult result, HttpSession session) {
//        Long userId = (Long) session.getAttribute("user_id");
//    	if (userId == null) {
//    	    return "redirect:/";
//    	} else {
//	    if (result.hasErrors()) {
//        	return "/<project name>/edit.jsp";
//	    } else {
//                if (userId.equals(projectToDelete.getUser().getId())) {
//    		    <project table>Service.update<Project Table>(<project table>);
//		    return "redirect:/<project name>";
//    		} else {
//    		    return "redirect:/home";
//    		}
//	    }
//        }
//    }

//    // delete one
//    @DeleteMapping("/<project name>/{id}")
//    public String destroy(@PathVariable("id") Long id, HttpSession session) {
//        Long userId = (Long) session.getAttribute("user_id");
//    	if (userId == null) {
//    	    return "redirect:/";
//    	} else {
//            if (userId.equals(<project table>ToDelete.getUser().getId())) {
//    		<project table>Service.delete<Project Table>(id);
//		return "redirect:/<project name>";
//    		} else {
//                    return "redirect:/home";
//                }
//            }
//    }

}