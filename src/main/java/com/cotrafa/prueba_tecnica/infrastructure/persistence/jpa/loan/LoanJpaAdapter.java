package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan;

import com.cotrafa.prueba_tecnica.application.dto.LoanResponse;
import com.cotrafa.prueba_tecnica.application.dto.PageResponseDTO;
import com.cotrafa.prueba_tecnica.domain.loan.Loan;
import com.cotrafa.prueba_tecnica.domain.loan.ports.out.LoanRepositoryPort;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.entity.LoanEntity;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.mapper.LoanMapperJpa;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.mapper.LoanResponseMapper;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.repository.LoanJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoanJpaAdapter implements LoanRepositoryPort {

    private final LoanJpaRepository loanJpaRepository;

    @Override
    public Loan createOne(Loan loan) {
        LoanEntity loanEntityToSave = LoanMapperJpa.toEntity(loan);
        LoanEntity loanEntitySaved = this.loanJpaRepository.save(loanEntityToSave);

        return LoanMapperJpa.toModel(loanEntitySaved);
    }

    @Override
    public PageResponseDTO<LoanResponse> getAll(Long loanStateId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<LoanEntity> loanPage = loanJpaRepository.getAll(loanStateId, pageable);
        return PageResponseDTO.<LoanResponse>builder()
                .totalElements(loanPage.getTotalElements())
                .totalPages(loanPage.getTotalPages())
                .page(loanPage.getNumber())
                .size(loanPage.getSize())
                .content(loanPage.stream().map(LoanResponseMapper::toResponse).toList())
                .build();
    }
}
