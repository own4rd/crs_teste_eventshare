package com.wagner.event_schedule.controller;

import com.wagner.event_schedule.dtos.v1.requests.CreateInstitutionRequestDto;
import com.wagner.event_schedule.dtos.v1.responses.InstitutionResponseDto;
import com.wagner.event_schedule.service.InstitutionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("event/api/v1/institutions")
@Tag(name = "Instituições", description = "Endpoints para gerenciar instituições")
public class InstitutionController {

    private final InstitutionService institutionService;


    @Operation(summary = "Criar uma nova instituição",
            description = "Cria uma nova instituição no sistema",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Instituição criada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
            })
    @PostMapping
    public ResponseEntity<Void> createInstitution(@RequestBody @Valid CreateInstitutionRequestDto createInstitutionRequestDto) {
        institutionService.createInstitution(createInstitutionRequestDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar todas as instituições",
            description = "Recupera a lista de todas as instituições cadastradas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de instituições",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = InstitutionResponseDto.class)))
            })
    @GetMapping
    public ResponseEntity<List<InstitutionResponseDto>> findAll() {
        var institutions = institutionService.findAllInstitutions();
        return ResponseEntity.ok(institutions);
    }
}
