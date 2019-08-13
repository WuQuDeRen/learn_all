package com.learn.app.core.domain.aggregate.cargo;

import com.learn.app.core.domain.aggregate.cargo.vo.DeliverySpecification;

public class Cargo {
    private String id;
    private String senderPhone;
    private String description;
    private DeliverySpecification delivery;
    public Cargo(String id) {
        this.id = id;
    }
    public Cargo() {}
    /**
     * Factory method: 预订新的货物
     *
     * @param senderPhone
     * @param description
     * @param delivery
     * @return
     */
    public static Cargo newCargo(String id, String senderPhone, String description,
                                 DeliverySpecification delivery) {
        Cargo cargo = new Cargo(id);
        cargo.senderPhone = senderPhone;
        cargo.description = description;
        cargo.delivery = delivery;
        return cargo;
    }
    public void changeDelivery(String destinationLocationCode) {
//        if (this.delivery
//                .getOriginLocationCode().equals(destinationLocationCode)) { throw new IllegalArgumentException(
//                "destination and origin location cannot be the same."); }
//        this.delivery.setDestinationLocationCode(destinationLocationCode);
    }
    public void changeSender(String senderPhone) {
        this.senderPhone = senderPhone;
    }
}