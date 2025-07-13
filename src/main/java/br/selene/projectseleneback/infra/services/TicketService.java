package br.selene.projectseleneback.infra.services;

import br.selene.projectseleneback.domain.ticket.Ticket;
import br.selene.projectseleneback.domain.ticket.dto.TicketResponseDto;
import br.selene.projectseleneback.domain.ticket.repository.ITicketRepository;
import br.selene.projectseleneback.domain.ticket.service.ITicketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService implements ITicketService {

    private final ITicketRepository ticketRepository;

    public TicketService(ITicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public TicketResponseDto findById(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId);
        return mapToResponseDto(ticket);
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public List<TicketResponseDto> findAllByEvent(int eventId) {
        List<Ticket> tickets = ticketRepository.findAllByEvent(eventId);
        return tickets.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketResponseDto> findAllByCustomer(int customerId) {
        List<Ticket> tickets = ticketRepository.findAllByCustomer(customerId);
        return tickets.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public TicketResponseDto invalidateTicket(Long ticketId) {
        Ticket ticket = ticketRepository.invalidateTicket(ticketId);
        return mapToResponseDto(ticket);
    }

    private TicketResponseDto mapToResponseDto(Ticket ticket) {
        return new TicketResponseDto(
                ticket.getId(),
                ticket.getStatus(),
                ticket.getCustomerDocument(),
                ticket.getCustomerName(),
                ticket.getEventTitle(),
                null,
                ticket.getTicketCategoryDescription(),
                ticket.getCreatedAt()
        );
    }
}
