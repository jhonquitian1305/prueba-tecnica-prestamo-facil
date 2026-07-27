package com.cotrafa.prueba_tecnica.infrastructure.web.controller.loan;

import com.cotrafa.prueba_tecnica.domain.loan.Loan;
import com.cotrafa.prueba_tecnica.domain.loan.ports.in.ILoanService;
import com.cotrafa.prueba_tecnica.infrastructure.web.controller.dto.LoanDTO;
import com.cotrafa.prueba_tecnica.infrastructure.web.controller.dto.LoanCreatedResponse;
import com.cotrafa.prueba_tecnica.infrastructure.web.controller.mapper.LoanMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/loans")
@RequiredArgsConstructor
public class LoanController {

    private final ILoanService loanService;

    @PostMapping
    public ResponseEntity<LoanCreatedResponse> createOne(@Valid @RequestBody LoanDTO loanDTO){
        Loan loan = LoanMapper.toModel(loanDTO);
        Loan loanCreated = this.loanService.createOne(loan);
        return new ResponseEntity<>(LoanMapper.toResponse(loanCreated), HttpStatus.CREATED);
    }
}
