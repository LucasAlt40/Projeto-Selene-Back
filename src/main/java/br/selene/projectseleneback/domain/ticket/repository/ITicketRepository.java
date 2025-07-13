package br.selene.projectseleneback.domain.ticket.repository;

import br.selene.projectseleneback.domain.ticket.Ticket;

import java.util.List;

public interface ITicketRepository {
    Ticket findById(Long ticketId);
    Ticket save(Ticket ticket);
    List<Ticket> findAllByEvent(int eventId);
    List<Ticket> findAllByCustomer(int customerId);
    Ticket invalidateTicket(Long ticket);
}
