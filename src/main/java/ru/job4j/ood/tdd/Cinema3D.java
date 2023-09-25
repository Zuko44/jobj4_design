package ru.job4j.ood.tdd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {
    private List<Session> cinema = new ArrayList<>();

    public List<Session> find(Predicate<Session> filter) {
        List<Session> sessions = new ArrayList<>();
        for (Session ses : cinema) {
            if (filter.test(ses)) {
                sessions.add(ses);
            }
        }
        return sessions;
    }

    public Ticket buy(Account account, int row, int column, Calendar date) {
        if (row == -1 || column == -1) {
            throw new IllegalArgumentException();
        }
        return new Ticket3D(account, row, column, date);
    }

    public void add(Session session) {
        cinema.add(session);
    }

    public List<Session> getCinema() {
        return cinema;
    }
}
