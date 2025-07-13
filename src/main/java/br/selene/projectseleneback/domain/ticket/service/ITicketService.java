package br.selene.projectseleneback.domain.ticket.service;

import br.selene.projectseleneback.domain.ticket.Ticket;
import br.selene.projectseleneback.domain.ticket.dto.TicketResponseDto;

import java.util.List;

public interface ITicketService {
    TicketResponseDto findById(Long ticketId);
    Ticket save(Ticket ticket);
    List<TicketResponseDto> findAllByEvent(int eventId);
    List<TicketResponseDto> findAllByCustomer(int customerId);
    TicketResponseDto invalidateTicket(Long ticket);
}
