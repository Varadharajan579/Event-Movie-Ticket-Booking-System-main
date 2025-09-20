package pbl.project.contollers;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pbl.project.beans.Ticket;
import pbl.project.beans.User;
import pbl.project.database.DatabaseOperation;

@Controller
public class MainController {
    @Autowired
    @Lazy
    private DatabaseOperation databaseOperation;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
            return "redirect:/";
        }
        return "registration";
    }

    @PostMapping("/registration")
    public String register_user(@RequestParam String username, @RequestParam String password,
            @RequestParam String user_email, @RequestParam Long user_contact) {
        User user = databaseOperation.get_user_info(username);
        if (user == null) {
            databaseOperation.add_user(username, password, user_email, user_contact);
            return "redirect:/login?registered";
        }
        return "redirect:/registration?error";
    }

    @GetMapping("/display_ticket/{ticket_id}")
    public String display_ticket(@PathVariable int ticket_id, Model model) {
        model.addAttribute("Ticket", databaseOperation.get_ticket_by_id(ticket_id));
        return "display_ticket";
    }

    @GetMapping("/create_ticket")
    public String create_ticket(Ticket ticket, Model model) {
        model.addAttribute("ticket", ticket);
        ArrayList<User> user_list = databaseOperation.get_all_users();
        model.addAttribute("user_list", user_list);
        return "create_ticket";
    }

    @PostMapping("/add_ticket")
    public String add_ticket(@ModelAttribute Ticket ticket, BindingResult bindingResult) {
        Ticket ticket_to_add = databaseOperation.get_ticket_by_event_name(ticket.getEvent_name());
        if (ticket_to_add == null) {
            databaseOperation.add_ticket(ticket);
            return "redirect:/ticket_list?message";
        }
        return "redirect:/add_ticket?error";
    }

    @GetMapping("/ticket_list")
    public String ticket_list(Model model, Principal principal) {
        String username = principal.getName();
        User user = databaseOperation.get_user_info(username);
        ArrayList<Ticket> ticket_list = databaseOperation.get_all_tickets(user);

        DecimalFormat df = new DecimalFormat("0.0");

        double sub_total = ticket_list.stream().mapToDouble(Ticket::getTicket_price).sum();

        double tax = sub_total * (13.00 / 100.00);

        double total = sub_total + tax;

        sub_total = Double.parseDouble(df.format(sub_total));
        tax = Double.parseDouble(df.format(tax));
        total = Double.parseDouble(df.format(total));

        model.addAttribute("tickets_list", ticket_list);
        model.addAttribute("sub_total", sub_total);
        model.addAttribute("tax", tax);
        model.addAttribute("total", total);

        return "ticket_list";
    }

    @GetMapping("/user_list")
    public String user_list(Model model) {
        ArrayList<User> user_list = databaseOperation.get_all_users();
        model.addAttribute("user_list", user_list);
        return "user_list";
    }

    @GetMapping("/config_ticket/{ticket_id}")
    public String config_ticket(@PathVariable int ticket_id, Model model) {
        model.addAttribute("Ticket", databaseOperation.get_ticket_by_id(ticket_id));
        return "config_ticket";
    }

    @PostMapping("/update_ticket")
    public String update_ticket(@ModelAttribute Ticket ticket, RedirectAttributes redirectAttributes) {
        databaseOperation.update_ticket(ticket);
        redirectAttributes.addFlashAttribute("message", "Successfully Updated !!");
        return "redirect:/ticket_list";
    }

    @GetMapping("/delete_ticket/{ticket_id}")
    public String delete_ticket(@PathVariable int ticket_id, RedirectAttributes redirectAttributes) {
        databaseOperation.delete_ticket(ticket_id);
        redirectAttributes.addFlashAttribute("message", "Successfully Deleted !!");
        return "redirect:/ticket_list";
    }
}
