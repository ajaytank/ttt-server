package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meals")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @GetMapping(value = "/ex/foos")
    @ResponseBody
    public String getFoosBySimplePath() {

        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));

        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }

        return "Get some Foos";

    }
}
