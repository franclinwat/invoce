package com.invoices.web.controller;


import com.invoices.core.controller.InvoiceControllerInterface;
import com.invoices.core.model.Invoice;
import com.invoices.core.service.InvoiceServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Scanner;
@Controller
@RequestMapping("/invoice")
public class InvoiceControllerWeb implements InvoiceControllerInterface {
    @Autowired
    private InvoiceServiceInterface invoiceServiceInterface;


    public void creationDeFacture(){
    Invoice invoice = new Invoice();
    Scanner sc= new Scanner(System.in);
    System.out.println( "enter the user name " );
    String username=sc.nextLine();
    invoice.setCustumerName(username);
    sc.close();
       invoiceServiceInterface.createInvoce(invoice);

    }

    @RequestMapping("/home")
    public String displayHome(){
        System.out.println("just pour voir si la methode est appeler");
        return "home";
    }


    @RequestMapping("/inv1")
    public String invoiceDisplay(HttpServletRequest request){
        System.out.println("la methode sera appeler si cette fonction passe");
        List<Invoice> listInvoices=invoiceServiceInterface.getInvoiceList();
        request.setAttribute("listInvoices1",listInvoices);
        return "HttpServeletRequestDisp";
    }

    @RequestMapping("/inv2")
    public String DisplayInvoiceList(Model model){
        model.addAttribute("invoices",invoiceServiceInterface.getInvoiceList());
        return "index";
    }

    @RequestMapping("/{id}")
    public  String DisplaysInvDetail(@PathVariable("id") String number, Model model){
       model.addAttribute( "invoice",invoiceServiceInterface.getInvoiceByNumber(number));
        return "invoiceDetail";
    }
}
