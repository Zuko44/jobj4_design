package ru.job4j.ood.tdd;

import java.util.Calendar;
import java.util.Objects;

public class Ticket3D implements Ticket {
    private Account account;
    private int row;
    private int column;
    private Calendar calendar;

    public Ticket3D() {

    }

    public Ticket3D(Account account, int row, int column, Calendar calendar) {
        this.account = account;
        this.row = row;
        this.column = column;
        this.calendar = calendar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket3D ticket3D = (Ticket3D) o;
        return row == ticket3D.row && column == ticket3D.column && account.equals(ticket3D.account)
                && calendar.equals(ticket3D.calendar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, row, column, calendar);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Calendar getDate() {
        return calendar;
    }

    public void setDate(Calendar calendar) {
        this.calendar = calendar;
    }
}
