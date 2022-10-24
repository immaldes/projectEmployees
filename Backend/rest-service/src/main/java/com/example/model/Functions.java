package com.example.model;

import java.util.ArrayList;
import java.util.List;
public class Functions {
    private String name;
    private List<String> roles;
    private enum ManagerRoles {
        Coaching,
        Supervision,
        Planning,
        Teamwork,
    }
    private enum DeveloperRoles{
        Write,
        Test,
        Debug
    }
    private enum AccountingRoles{
        Tax,
        Payroll,
        Financial
    }
    private enum HumanResourcesRoles{
        Recruitment,
        Hiring,
        Training
    }
    private enum MarketingRoles{
        Content,
        Pricing,
        Distribution,
        Promotion,
        Selling
    }

    public Functions(String name, List<String> roles) {
        this.name = name;
        this.roles = roles;
    }
    public Functions() { }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void setManagerRoles(){
        List<String> managerRls = new ArrayList<>();

        for (ManagerRoles managerRoles: ManagerRoles.values()) {
            managerRls.add(managerRoles.toString());
            this.roles = managerRls;
        }
    }
    public void setDeveloperRoles(){
        List<String> developerRls = new ArrayList<>();

        for(DeveloperRoles developerRoles: DeveloperRoles.values()){
            developerRls.add(developerRoles.toString());
            this.roles = developerRls;
        }
    }
    public void setAccountingRoles(){
        List<String> accountingRls = new ArrayList<>();

        for(AccountingRoles accountingRoles: AccountingRoles.values()){
            accountingRls.add(accountingRoles.toString());
            this.roles = accountingRls;
        }
    }
    public void setHumanResourcesRoles(){
        List<String> hrRoles = new ArrayList<>();

        for(HumanResourcesRoles humanResourcesRoles: HumanResourcesRoles.values()){
            hrRoles.add(humanResourcesRoles.toString());
            this.roles = hrRoles;
        }
    }
    public void setMarketingRoles(){
        List<String> marketingRls = new ArrayList<>();

        for(MarketingRoles marketingRoles: MarketingRoles.values()){
            marketingRls.add(marketingRoles.toString());
            this.roles = marketingRls;
        }
    }
}
