package br.selene.projectseleneback.presentation;

import br.selene.projectseleneback.domain.ticket.Ticket;
import br.selene.projectseleneback.domain.ticket.dto.TicketResponseDto;
import br.selene.projectseleneback.domain.ticket.service.ITicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final ITicketService ticketService;

    public TicketController(ITicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TicketResponseDto findById(@PathVariable Long id) {
        return ticketService.findById(id);
    }

    @GetMapping("/event/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TicketResponseDto> findAllByEvent(@PathVariable int eventId) {
        return ticketService.findAllByEvent(eventId);
    }

    @GetMapping("/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TicketResponseDto> findAllByCustomer(@PathVariable int customerId) {
        return ticketService.findAllByCustomer(customerId);
    }

    @PutMapping("/{id}/invalidate")
    @ResponseStatus(HttpStatus.OK)
    public TicketResponseDto invalidateTicket(@PathVariable Long id) {
        return ticketService.invalidateTicket(id);
    }
}