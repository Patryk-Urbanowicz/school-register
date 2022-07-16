package pl.school.register.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.school.register.model.projections.AccountInfo;
import pl.school.register.service.AccountService;
@RestController()
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExampleController {
    private final AccountService accountService;
    ExampleController(AccountService accountService){
        this.accountService = accountService;
    }
    @GetMapping(value = "/example")
    public String helloWorld(){
        return "Hello World!";
    }

    @GetMapping(value = "/student/info")
    public ResponseEntity<AccountInfo> getCurrentStudentInfo(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        AccountInfo accountInfo = accountService.getInfoByLogin(userDetails.getUsername());
        return ResponseEntity.ok(accountInfo);
    }
}
