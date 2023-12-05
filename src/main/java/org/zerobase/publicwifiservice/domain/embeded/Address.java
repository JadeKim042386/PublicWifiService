package org.zerobase.publicwifiservice.domain.embeded;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address that)) return false;
        return this.getAddrState() != null && Objects.equals(this.getAddrState(), that.getAddrState()) &&
                this.getAddrCity() != null && Objects.equals(this.getAddrCity(), that.getAddrCity()) &&
                this.getAddrDetail() != null && Objects.equals(this.getAddrDetail(), that.getAddrDetail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getAddrState(), this.getAddrCity(), this.getAddrDetail());
    }
}
