package com.proyect.park.models.dto;

public class UsuarioDto {
    private Long id;
    private String fullname;

    private Long cellphone;

    private String email;

    public UsuarioDto(Long id, String fullname, Long cellphone, String email) {
        this.id = id;
        this.fullname = fullname;
        this.cellphone = cellphone;
        this.email = email;
    }

    public UsuarioDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
