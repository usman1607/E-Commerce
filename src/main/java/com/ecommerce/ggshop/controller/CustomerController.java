package com.ecommerce.ggshop.controller;

import com.ecommerce.ggshop.model.Customer;
import com.ecommerce.ggshop.model.Role;
import com.ecommerce.ggshop.model.viewmodels.RegisterCustomerModel;
import com.ecommerce.ggshop.repository.CustomerRepository;
import com.ecommerce.ggshop.repository.RoleRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.management.remote.JMXAuthenticator;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    final
    CustomerRepository customerRepository;
    final
    RoleRepository roleRepository;
    final
    PasswordEncoder passwordEncoder;

    public CustomerController(CustomerRepository customerRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register a customer.
    @CrossOrigin(exposedHeaders = "http://localhost:8098")
    @RequestMapping(path = "/customer/register", method = RequestMethod.POST)
    public String register(RedirectAttributes redirectAttributes, @RequestBody RegisterCustomerModel registerCustomerModel) {
//        String regex="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8,20}$";
        //Pattern p = Pattern.compile(regex);
        // Matcher m = p.matcher(registerUserModel.getPassword());
        if (!registerCustomerModel.getPassword().equals(registerCustomerModel.getConfirmPassword())) {
            redirectAttributes.addAttribute("passError", "Password does not match ");
        } else if (customerRepository.existsByUsername(registerCustomerModel.getUsername())) {
            redirectAttributes.addAttribute("emailError", "this email already exist");
        } else if ((!registerCustomerModel.getUsername().contains("@")) || (!registerCustomerModel.getUsername().contains("."))) {
            redirectAttributes.addAttribute("emailError", "the email is not a valid email address");
        } else if (registerCustomerModel.getPassword().isBlank() || registerCustomerModel.getPassword().isEmpty()) {
            redirectAttributes.addAttribute("passError", "Password can not be empty or blank ");
        }

        //       else if( !m.matches()){
//            redirectAttributes.addAttribute("error","Password is not strong enough");
//        }
        else {
            Customer customer = new Customer();
            customer.setUsername(registerCustomerModel.getUsername());
            customer.setPassword(passwordEncoder.encode(registerCustomerModel.getPassword()));
            Optional<Role> optionalRole = roleRepository.findByName("CUSTOMER");
            if (optionalRole.isPresent()) {
                Role role = optionalRole.get();
                List<Role> roleList = new ArrayList<>();
                roleList.add(role);
                customer.setRoles(roleList);
            }

            customer.setFirstName(registerCustomerModel.getFirstName());
            customer.setLastName(registerCustomerModel.getLastName());
            customer.setAddress(registerCustomerModel.getAddress());
            customer.setCity(registerCustomerModel.getCity());
            customer.setState(registerCustomerModel.getState());
            customer.setCountry(registerCustomerModel.getCountry());
            customer.setPhoneNo(registerCustomerModel.getPhoneNo());

            customerRepository.save(customer);
            redirectAttributes.addAttribute("success", "You have successfully registered");
            redirectAttributes.addAttribute("successful", true);
            return "Congratulations! You have successfully registered.";
        }
        return "Sorry! An Error occurred, customer could not be register.";
    }

    //    @CrossOrigin(exposedHeaders = "http://localhost:8098")
//    @RequestMapping(path = "/customer/register", method = RequestMethod.POST)
//    public Customer register(RedirectAttributes redirectAttributes, @RequestBody Customer customer) {
//
//        String userName = customer.getUsername();
//        String password = customer.getPassword();
//        String firstName = customer.getFirstName();
//
//        customerRepository.save(customer);
//        return customer;
//    }
    //Get the list of all customers.
    @CrossOrigin(exposedHeaders = "http://localhost:8098")
    @RequestMapping(path = "/customers", method = RequestMethod.GET)
    public List Customers() {

        return (List) customerRepository.findAll();
    }

    // Get the details of a particular customer by admin.
    @CrossOrigin(exposedHeaders = "http://localhost:8098")
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public Customer customer(@PathVariable("id") long id) {
        Customer customer = customerRepository.findById(id).get();

        return customer;
    }

    //Return details of a customer to the customer when log-in.
    @CrossOrigin(exposedHeaders = "http://localhost:8098")
    @RequestMapping(path = "/customerPage", method = RequestMethod.GET)
    public Customer myPage(Authentication authentication) {
        String username = authentication.getName(); //get Signed UserName...
        Customer customer = customerRepository.findCustomerByUsername(username);

        return customer;
    }

    //Customer update my profile.
    @CrossOrigin(exposedHeaders = "http://localhost:8098")
    @RequestMapping(path = "/customer/update", method = RequestMethod.PUT)
    public String updateProfile(Authentication authentication, RegisterCustomerModel registerCustomerModel) {
        String username = authentication.getName(); //get Signed UserName...
        Customer customer = customerRepository.findCustomerByUsername(username);

        customer.setFirstName(registerCustomerModel.getFirstName());
        customer.setLastName(registerCustomerModel.getLastName());
        customer.setAddress(registerCustomerModel.getAddress());
        customer.setCity(registerCustomerModel.getCity());
        customer.setState(registerCustomerModel.getState());
        customer.setCountry(registerCustomerModel.getCountry());
        customer.setPhoneNo(registerCustomerModel.getPhoneNo());

        customerRepository.save(customer);
        return "Profile updated successfully.";
    }

    //Customer change my password.
    @CrossOrigin(exposedHeaders = "http://localhost:8098")
    @RequestMapping(path = "/customer/updatePassword", method = RequestMethod.PUT)
    public String updatePassword(Authentication authentication, @RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String confirmPassword, RedirectAttributes redirectAttributes) {
        String username = authentication.getName();
        Customer customer = customerRepository.findCustomerByUsername(username);

        if (!passwordEncoder.matches(oldPassword, customer.getPassword())) {
            redirectAttributes.addAttribute("error", "Password is not correct...");
        } else if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addAttribute("passError", "Password does not match ");
        } else if (newPassword.isBlank() || newPassword.isEmpty()) {
            redirectAttributes.addAttribute("passError", "New Password can not be empty or blank ");
        } else {
            String new_p = passwordEncoder.encode(newPassword);
            customer.setPassword(new_p);
            customerRepository.save(customer);
            return "Password updated successfully.";
        }

        return "An error occurred.";
    }

    //Delete a customer account.
    @CrossOrigin(exposedHeaders = "http://localhost:8098")
    @RequestMapping(value = "/customer/delete/{id}", method = RequestMethod.DELETE)
    public String removeCustomer(@PathVariable("id") long id) {
        Customer customer = customerRepository.findById(id).get();

        customerRepository.delete(customer);
        return "Customer deleted successfully.";
    }

    // Customer login.
//    @CrossOrigin(exposedHeaders = "http://localhost:8098")
//    @PostMapping(path = "/login")
//    public boolean login(@RequestParam String username, @RequestParam String password, HttpServletRequest request){
//        Authentication auth = null;
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//        try {
//            JMXAuthenticator authenticationProvider;
//            auth = authenticationProvider.authenticate(token);
//            SecurityContextHolder.getContext().setAuthentication(auth);
//            Customer employee = (Customer) auth.getPrincipal();
//            employee.setPassword(null);
//            return true;
//        }
//        catch (BadCredentialsException exception) {
//            // you can also log the exception message, for example using Logger
//            return false;
//        }
//    }

}
