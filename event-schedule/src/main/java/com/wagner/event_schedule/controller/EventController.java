package com.wagner.event_schedule.controller;

import com.wagner.event_schedule.dtos.v1.requests.CreateEventRequestDto;
import com.wagner.event_schedule.dtos.v1.requests.UpdateEventRequestDto;
import com.wagner.event_schedule.dtos.v1.responses.EventResponseDto;
import com.wagner.event_schedule.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("event/api/v1/events")
@Tag(name = "Eventos", description = "Endpoints para gerenciar eventos")
public class EventController {

    private final EventService eventService;

    @Operation(
            summary = "Criar um novo evento",
            description = "Cria um evento a partir dos dados fornecidos, como nome, data de início e data de término.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Evento criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados fornecidos são inválidos")
            })
    @PostMapping
    public ResponseEntity<Void> createEvent(@RequestBody @Valid CreateEventRequestDto createEventRequestDto) {
        eventService.createEvent(createEventRequestDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Lista todos os eventos",
            description = "Recupera todos os eventos. Pode filtrar pelos eventos ativos passando o parâmetro 'active'."
    )
    @GetMapping
    public ResponseEntity<List<EventResponseDto>> findAll(@RequestParam(value = "active", required = false) Boolean activeFilter) {
        return ResponseEntity.ok(eventService.findAll(activeFilter));
    }

    @Operation(
            summary = "Exclui um evento pelo ID",
            description = "Deleta um evento existente com base no ID fornecido. Retorna um status 204 (No Content) em caso de sucesso."
    )
    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeEvent(
            @Parameter(
                    description = "ID do evento que será excluído",
                    required = true
            )
            @PathVariable("id") Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar evento", description = "Atualiza os detalhes de um evento com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento atualizado com sucesso",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })
    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDto> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody UpdateEventRequestDto updateEventRequestDto) {

        EventResponseDto updatedEvent = eventService.updateEvent(updateEventRequestDto);
        return ResponseEntity.ok(updatedEvent);
    }

    @Operation(summary = "Obter evento por ID", description = "Recupera as informações de um evento com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento encontrado",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDto> findOneById(@PathVariable Long id) {
        EventResponseDto event = eventService.findOneById(id);
        return ResponseEntity.ok(event);
    }
}
