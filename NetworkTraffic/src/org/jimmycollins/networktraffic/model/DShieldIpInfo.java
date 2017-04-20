
package org.jimmycollins.networktraffic.model;

import javafx.beans.property.SimpleStringProperty;

public class DShieldIpInfo 
{
    private SimpleStringProperty IP;
    private SimpleStringProperty FQDN;
    private SimpleStringProperty Blocked;
    private SimpleStringProperty Attacks;
    private SimpleStringProperty Country;
    
    
    
    public DShieldIpInfo(String ip, String fqdn, String blocked, String attacks, String country)
    {
        this.IP = new SimpleStringProperty(ip);
        this.FQDN = new SimpleStringProperty(fqdn);
        this.Blocked = new SimpleStringProperty(blocked);
        this.Attacks = new SimpleStringProperty(attacks);
        this.Country = new SimpleStringProperty(country);
    }
    
    public void SetIP(SimpleStringProperty IP) {
        this.IP = IP;
    }
    
    public SimpleStringProperty IPProperty()
    {
        return IP;
    }
    
    public void SetFqdn(SimpleStringProperty FQDN) {
        this.FQDN = FQDN;
    }
    
    public SimpleStringProperty FQDNProperty()
    {
        return FQDN;
    }

    public SimpleStringProperty BlockedProperty() {
        return Blocked;
    }

    public void SetBlocked(SimpleStringProperty Blocked) {
        this.Blocked = Blocked;
    }

    public SimpleStringProperty AttacksProperty() {
        return Attacks;
    }

    public void SetAttacks(SimpleStringProperty Attacks) {
        this.Attacks = Attacks;
    }

    public SimpleStringProperty CountryProperty() {
        return Country;
    }

    public void SetCountry(SimpleStringProperty Country) {
        this.Country = Country;
    }    
}
