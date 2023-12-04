package org.zerobase.publicwifiservice.domain.embeded;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Address {
    private String addrState;
    private String addrCity;
    private String addrDetail;

    protected Address() {
    }

    private Address(String addrState, String addrCity, String addrDetail) {
        this.addrState = addrState;
        this.addrCity = addrCity;
        this.addrDetail = addrDetail;
    }

    public static Address of(String addrState, String addrCity, String addrDetail) {
        return new Address(addrState, addrCity, addrDetail);
    }
}
