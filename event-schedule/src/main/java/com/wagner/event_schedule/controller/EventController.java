package com.wagner.event_schedule.controller;

import com.wagner.event_schedule.dtos.v1.requests.CreateEventRequestDto;
import com.wagner.event_schedule.dtos.v1.responses.EventResponseDto;
import com.wagner.event_schedule.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> findAll(@RequestParam(value = "active", required = false) Boolean activeFilter) {
        return ResponseEntity.ok(eventService.findAll(activeFilter));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeEvent(@PathVariable("id") Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
