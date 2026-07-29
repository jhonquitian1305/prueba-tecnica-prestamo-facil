package com.cotrafa.prueba_tecnica.infrastructure.web.controller;

import com.cotrafa.prueba_tecnica.application.dto.LoanResponse;
import com.cotrafa.prueba_tecnica.application.dto.PageResponseDTO;
import com.cotrafa.prueba_tecnica.application.dto.UpdateStateDTO;
import com.cotrafa.prueba_tecnica.domain.loan.Loan;
import com.cotrafa.prueba_tecnica.domain.loan.ports.in.ILoanService;
import com.cotrafa.prueba_tecnica.infrastructure.web.controller.dto.LoanDTO;
import com.cotrafa.prueba_tecnica.infrastructure.web.controller.dto.LoanCreatedResponse;
import com.cotrafa.prueba_tecnica.infrastructure.web.controller.dto.MessageResponse;
import com.cotrafa.prueba_tecnica.infrastructure.web.controller.dto.TotalApprovedLoansResponse;
import com.cotrafa.prueba_tecnica.infrastructure.web.controller.mapper.LoanMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Tag(name = "Préstamos", description = "Operaciones relacionadas con préstamos")
@RestController
@RequestMapping("api/v1/loans")
@RequiredArgsConstructor
public class LoanController {

    private final ILoanService loanService;

    @Operation(
            summary = "Crear una solicitud de préstamo",
            description = "Registra una nueva solicitud de préstamo."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Solicitud creada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuario o tipo de préstamo no existe")
    })
    @PostMapping
    public ResponseEntity<LoanCreatedResponse> createOne(@Valid @RequestBody LoanDTO loanDTO){
        Loan loan = LoanMapper.toModel(loanDTO);
        Loan loanCreated = this.loanService.createOne(loan);
        return new ResponseEntity<>(LoanMapper.toResponse(loanCreated), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar solicitudes de préstamo")
    @GetMapping
    public ResponseEntity<PageResponseDTO<LoanResponse>> getALl(@RequestParam(required = false) Long loanStateId,
                                                                @RequestParam(defaultValue = "0", required = false) Integer page,
                                                                @RequestParam(defaultValue = "10", required = false) Integer size){
        return new ResponseEntity<>(this.loanService.getAll(loanStateId, page, size), HttpStatus.OK);
    }

    @Operation(
            summary = "Actualizar el estado de un préstamo",
            description = "Actualiza el estado de una solicitud de préstamo."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Estado actualizado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "string",
                                    example = "Estado actualizado con éxito"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud inválida"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Préstamo o estado no encontrado"
            )
    })
    @PutMapping
    public ResponseEntity<MessageResponse> updateState(@RequestBody UpdateStateDTO updateStateDTO){
        this.loanService.updateState(updateStateDTO);
        return new ResponseEntity<>(new MessageResponse("Estado actualizado con éxito"), HttpStatus.OK);
    }

    @Operation(
            summary = "Listar el valor total de los préstamos aprobados",
            description = "Obtiene el monto acumulado de todos los préstamos con estado APROBADO."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Total obtenido correctamente"
            )
    })
    @GetMapping("total-approved")
    public ResponseEntity<TotalApprovedLoansResponse> getTotalApprovedLoans(){
        BigDecimal totalApproved = this.loanService.getTotalApproved();
        return new ResponseEntity<>(new TotalApprovedLoansResponse(totalApproved), HttpStatus.OK);
    }
}
