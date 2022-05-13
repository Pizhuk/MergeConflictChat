package com.mergemconflictchat.model.entity;

public class ActivationCodeDTO {

    private int activationCode;

    public ActivationCodeDTO() {
    }

    public ActivationCodeDTO(int activationCode) {
        this.activationCode = activationCode;
    }

    public int getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(int activationCode) {
        this.activationCode = activationCode;
    }

    @Override
    public String toString() {
        return "ActivationCodeDTO{" +
                "activationCode=" + activationCode +
                '}';
    }
}
