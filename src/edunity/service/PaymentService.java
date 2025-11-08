package edunity.service;

import edunity.storage.DataStorage;
import edunity.model.Payment;

import java.util.*;
import java.util.stream.Collectors;


public class PaymentService {
    private List<Payment> payments;
    private DataStorage storage;
    private int nextId;

    public PaymentService(DataStorage storage) {
        this.storage = storage;
        this.payments = storage.loadPayments();
        this.nextId = payments.isEmpty() ? 1 :
                payments.stream()
                        .mapToInt(p -> Integer.parseInt(p.getPaymentId().substring(1)))
                        .max()
                        .orElse(0) + 1;
    }

    public void recordPayment(String studentId, String courseId, double amount, String paymentMonth, String paymentMethod) {
        String paymentId = "P" + String.format("%04d", nextId++);
        Payment payment = new Payment(paymentId, studentId, courseId, amount, paymentMonth, paymentMethod);
        payments.add(payment);
        storage.savePayments(payments);
        System.out.println("\nPayment recorded successfully! Payment ID: " + paymentId);
    }

    public void displayAllPayments() {
        if (payments.isEmpty()) {
            System.out.println("\nNo payments recorded yet.");
            return;
        }

        System.out.println("\n=== All Payments ===");
        for (Payment payment : payments) {
            System.out.println(payment);
        }
    }

    public void displayPaymentsByStudent(String studentId) {
        List<Payment> studentPayments = payments.stream()
                .filter(p -> p.getStudentId().equalsIgnoreCase(studentId))
                .collect(Collectors.toList());

        if (studentPayments.isEmpty()) {
            System.out.println("\nNo payments found for student: " + studentId);
        } else {
            System.out.println("\n=== Payment History for Student " + studentId + " ===");
            for (Payment payment : studentPayments) {
                System.out.println(payment);
            }
        }
    }

    public void displayPaymentsByCourse(String courseId) {
        List<Payment> coursePayments = payments.stream()
                .filter(p -> p.getCourseId().equalsIgnoreCase(courseId))
                .collect(Collectors.toList());

        if (coursePayments.isEmpty()) {
            System.out.println("\nNo payments found for course: " + courseId);
        } else {
            System.out.println("\n=== Payment History for Course " + courseId + " ===");
            for (Payment payment : coursePayments) {
                System.out.println(payment);
            }
        }
    }
    public void displayPaymentDetails(String paymentId) {
        for (Payment payment : payments) {
            if (payment.getPaymentId().equalsIgnoreCase(paymentId)) {
                System.out.println("\n=== Payment Record Details ===");
                System.out.println("Payment ID: " + payment.getPaymentId());
                System.out.println("Student ID: " + payment.getStudentId());
                System.out.println("Course ID: " + payment.getCourseId());
                System.out.println("Amount Paid: $" + payment.getAmountPaid());
                System.out.println("Transaction Date: " + payment.getTransactionDate());
                System.out.println("Payment Method: " + payment.getPaymentMethod());
                System.out.println("Payment Month: " + payment.getPaymentMonth());
                return;
            }
        }
        System.out.println("\nPayment record not found!");
    }

}