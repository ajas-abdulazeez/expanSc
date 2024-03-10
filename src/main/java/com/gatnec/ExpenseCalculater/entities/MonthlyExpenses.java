package com.gatnec.ExpenseCalculater.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
@Entity
@Table(name = "monthly_expenses")
public class MonthlyExpenses extends Expenses{

    @Column(name = "year")
    private String year = String.valueOf(LocalDate.now().getYear());

    @Column(name = "month")
    private String month = LocalDate.now().getMonth().toString();

    @ManyToOne
    @JsonIgnore // Add this annotation to break the circular reference
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "electricity_id")
    private Transactions electricity;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "internet_expenses_id")
    private Transactions internetExpenses;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "mobile_recharge_id")
    private Transactions mobileRecharge;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "gas_bills_id")
    private Transactions gasBills;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "water_bills_id")
    private Transactions waterBills;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "room_rent_id")
    private Transactions roomRent;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tax_id")
    private Transactions tax;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vehicle_insurance_id")
    private Transactions vehicleInsurance;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "maintenance_id")
    private Transactions maintenance;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "others_id")
    private List<Transactions> others;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "emi_id")
    private List<Transactions> emi;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "credits_id")
    private List<Transactions> credits;

    @Column(name = "total_daily_expenses")
    private double totalDailyExpenses;


    public MonthlyExpenses(double amount, LocalDate date, Transactions electricity, Transactions internetExpenses, Transactions mobileRecharge, Transactions gasBills, Transactions waterBills, Transactions roomRent, Transactions tax, List<Transactions> emi, List<Transactions> credits, Transactions vehicleInsurance, Transactions maintenance, List<Transactions> others) {
        super(amount, date);
        this.electricity = electricity;
        this.internetExpenses = internetExpenses;
        this.mobileRecharge = mobileRecharge;
        this.gasBills = gasBills;
        this.waterBills = waterBills;
        this.roomRent = roomRent;
        this.tax = tax;
        this.emi = emi;
        this.credits = credits;
        this.vehicleInsurance = vehicleInsurance;
        this.maintenance = maintenance;
        this.others = others;
        this.date = date;
    }

    public MonthlyExpenses(double amount, LocalDate date) {
        super(amount, date);
    }


}
