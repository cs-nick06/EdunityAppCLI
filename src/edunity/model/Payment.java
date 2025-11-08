package edunity.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Payment implements Serializable {
    private String paymentId;
    private String studentId;
    private String courseId;
    private double amountPaid;
    private LocalDate transactionDate;
    private String paymentMethod;
    private String paymentMonth;

    public Payment(String paymentId, String studentId, String courseId, double amountPaid, String paymentMonth, String paymentMethod) {
        this.paymentId = paymentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.amountPaid = amountPaid;
        this.paymentMonth = paymentMonth;
        this.transactionDate = LocalDate.now();
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentId() { return paymentId; }
    public String getStudentId() { return studentId; }
    public String getCourseId() { return courseId; }
    public double getAmountPaid() { return amountPaid; }
    public LocalDate getTransactionDate() { return transactionDate; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getPaymentMonth() { return paymentMonth; }

    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
    public void setAmountPaid(double amountPaid) { this.amountPaid = amountPaid; }
    public void setTransactionDate(LocalDate transactionDate) { this.transactionDate = transactionDate; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setPaymentMonth(String paymentMonth) { this.paymentMonth = paymentMonth; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return String.format("Payment #%s | Student: %s | Course: %s | Amount: $%.2f | Month: %s | Date: %s | Method: %s",
                paymentId, studentId, courseId, amountPaid, paymentMonth, transactionDate.format(fmt), paymentMethod);
    }
}
