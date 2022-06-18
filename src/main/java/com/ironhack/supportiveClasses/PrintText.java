package com.ironhack.supportiveClasses;

import com.ironhack.classes.Account;
import com.ironhack.classes.Contact;
import com.ironhack.classes.Lead;
import com.ironhack.classes.Opportunity;
import com.ironhack.enums.Industry;
import com.ironhack.enums.Product;

public class PrintText {

    public static void printAvailableCommands() {

        //System.out.println(Colors.WHITE + Colors.BG_LIGHT_BLUE);
//        System.out.println(" __________________________________________________________________________________");
        //System.out.println();
        System.out.println(Colors.WHITE + Colors.BG_LIGHT_BLUE+" ================================================================================");
        System.out.println();
        System.out.println("   Lead commands to search, track and convert potential opportunities are:           ");
        System.out.println("      New Lead                    To create a new lead                                 ");
        System.out.println("      Show Leads                  To list all Leads' id and name                       ");
        System.out.println("      Lookup Lead [id]            To display the details of a Lead                     ");
        System.out.println("      Convert [id]                To convert a Lead into an Opportunity                ");
        System.out.println();
        System.out.println("   Opportunity commands to search and update opportunities are:                      ");
        System.out.println("      Show Opportunities          To list all Opportunities' id and name        ");
        System.out.println("      Lookup Opportunity [id]     To display the details of an Opportunity ");
        System.out.println("      close-lost [id]             To update the status of a OPEN opportunity    ");
        System.out.println("      close-won [id]              To update the status of a OPEN opportunity    ");
        System.out.println();
        System.out.println("   Application commands:                                                             ");
        System.out.println("      Exit                        To close the application                      ");
        System.out.println();
        System.out.println(" ================================================================================");
//        System.out.println(" __________________________________________________________________________________");
        //System.out.println();
        System.out.println(Colors.RESET);
    }

    public static void wrongCommand(String error){
        System.err.println(error);
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "Please enter a valid command");
        System.out.println(" ");
    }

    public static void askProduct() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+"Insert the product for this opportunity. Products available: ");
        System.out.println("- " + Product.HYBRID);
        System.out.println("- " + Product.FLATBED);
        System.out.println("- " + Product.BOX);
    }

    public static void wrongProduct() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.LIGHT_RED+Colors.BOLD+"Insert a correct product");
    }

    public static void askNumberOfTrucks() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+"Insert the number of trucks");
    }

    public static void wrongNumberOfTrucks() {
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "Insert a number of trucks bigger than zero");
    }

    public static void askIndustry() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+ "Insert the industry. Industries availables");
        System.out.println("- " + Industry.PRODUCE);
        System.out.println("- " + Industry.ECOMMERCE);
        System.out.println("- " + Industry.MANUFACTURING);
        System.out.println("- " + Industry.MEDICAL);
        System.out.println("- " + Industry.OTHER);
    }

    public static void askNumberOfEmployees() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+"Insert the number of employees");
    }

    public static void wrongIndustry() {
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "Insert a correct industry");
    }

    public static void wrongNumberOfEmployees() {
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "Insert a number of employees bigger than zero");
    }

    public static void askLeadName(){
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+"Insert new Lead's name");
    }

    public static void invalidLeadName(){
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "Invalid name");
    }

    public static void askPhoneNumber(){
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+"Insert a valid phone number");
    }

    public static void invalidPhoneNumber() {
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "Invalid phone number"+Colors.RESET);
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "Phone number should have a valid format:"+Colors.RESET);
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "123456789  +34123456789  (34)123456789"+Colors.RESET);
    }

    public static void askEmail(){
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+"Insert a valid email address");
    }

    public static void invalidEmail() {
        System.err.println(Colors.RESET+Colors.LIGHT_RED+"Invalid email address"+Colors.RESET);
    }

    public static void askCompanyName(){
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+"Insert company's name");
    }

    public static void askCity() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+"Insert account's city");
    }

    public static void askCountry() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BLUE+Colors.BOLD+"Insert account's country");
    }
    public static void leadCreated(Lead lead){
        System.out.println(Colors.RESET);
        System.out.println(Colors.WHITE + Colors.BG_GREEN + Colors.BOLD);
        System.out.println("  New Lead created: " + lead.toStringFormatted());
        System.out.println(Colors.RESET + Colors.WHITE + Colors.BG_GREEN);
    }

    public static void printLead(Lead lead) {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.BLUE + Colors.BOLD);
        System.out.println("    " + lead.toStringFormatted());
        System.out.println();

    }

    public static void printOpportunity(Opportunity opportunity) {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.BLUE + Colors.BOLD);
        System.out.println("    " + opportunity.toStringFormatted());
        System.out.println();
    }

    public static void printAccount(Account account) {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.BLUE + Colors.BOLD);
        System.out.println("  " + account.toStringFormatted());
        System.out.println();
    }

    public static void printAccountWithoutOpportunity(Account account) {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.BLUE + Colors.BOLD);
        System.out.println("     ╚══ " + account.toStringFormattedWithoutOpportunityAndContact());
        System.out.println();
    }

    public static void printContact(Contact contact) {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.BLUE + Colors.BOLD);
        System.out.println("       ╚══ Contact - ID " +contact.getId()+ " : " + contact.toStringFormatted());
        System.out.println();
    }

    public static void noLeadsYet() {
        System.err.println(Colors.RESET+Colors.LIGHT_RED+"There are not leads yet.");
        System.err.println("");
    }

    public static void noOpportunitiesYet() {
        System.err.println(Colors.RESET+Colors.LIGHT_RED+"There are not opportunities yet.");
        System.err.println("");
    }

    public static void noAccountsYet() {
        System.err.println(Colors.RESET+Colors.LIGHT_RED+"There are not accounts yet.");
        System.err.println("");
    }

    public static void wrongIdFormat() {
        System.err.println(Colors.RESET+Colors.LIGHT_RED+"The ID must be a number bigger than zero");
        System.err.println("");
    }

    public static void noIdFound(String message) {
        System.err.println(Colors.RESET+Colors.LIGHT_RED+message);
    }

    public static void closeOpportunityLost(int id) {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.RED + Colors.BOLD);
        System.out.println("  The opportunity " + id + " has been closed as LOST");
        System.out.println();
    }

    public static void closeOpportunityWon(int id) {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.GREEN + Colors.BOLD);
        System.out.println("  The opportunity " + id + " has been closed as WON");
        System.out.println();
    }

    public static void opportunityAlreadyClosed(int id) {
        System.err.println(Colors.RESET+Colors.LIGHT_RED+"  The opportunity " + id + " is already CLOSED");
        System.err.println("");
    }

    public static void headerShowLeads() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.BLUE + Colors.BOLD);
        System.out.println("  Printing existing leads:");
        System.out.println();
    }

    public static void headerShowOpportunities() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.BLUE + Colors.BOLD);
        System.out.println("  Printing existing opportunities:");
        System.out.println();
    }

    public static void headerShowAccounts() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.BLUE + Colors.BOLD);
        System.out.println("  Printing existing accounts:");
        System.out.println();
    }
    public static void headerShowContacts() {
        System.out.println(Colors.RESET);
        System.out.println(Colors.BG_WHITE + Colors.BLUE + Colors.BOLD);
        System.out.println("  Printing existing contacts:");
        System.out.println();
    }

    public static void iDAlreadyExists(String message) {
        System.err.println(Colors.RESET+Colors.LIGHT_RED+message);
    }

    public static void idDoesNotExist(){
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "Introduce another id."+Colors.RESET);
    }

    public static void recommendationShowLeads(){
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "If you are not sure what is the ID, use the command 'Show Leads' to visualize all the leads."+Colors.RESET);
    }

    public static void recommendationShowOpportunities(){
        System.out.println(Colors.RESET+Colors.LIGHT_RED+ "If you are not sure what is the ID, use the command 'Show Opportunities' to visualize all the opportunities."+Colors.RESET);
    }


}
