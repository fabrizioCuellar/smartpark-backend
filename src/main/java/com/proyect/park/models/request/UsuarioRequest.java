package com.proyect.park.models.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UsuarioRequest {
    @NotBlank
    private String fullname;
    private Long cellphone;
    @Email
    private String email;

    public UsuarioRequest() {
    }

    public UsuarioRequest(String fullname, Long cellphone, String email) {
        this.fullname = fullname;
        this.cellphone = cellphone;
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Long getCellphone() {
        return cellphone;
    }

    public void setCellphone(Long cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
