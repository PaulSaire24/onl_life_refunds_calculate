package com.bbva.rbvd.dto.insurancerefunds.rimac;

public class CommonFieldsBO {
    private String correo;
    private String telefono;
    private String ruc;
    private String ideCotizacion;
    private String tipoDocumento;
    private String url;
    private Long httpStatus;
    private String mensaje;
    private String status;
    private String proteccionDatosPersonales;
    private String envioComunicacionesComerciales;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getIdeCotizacion() {
        return ideCotizacion;
    }

    public void setIdeCotizacion(String ideCotizacion) {
        this.ideCotizacion = ideCotizacion;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Long httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProteccionDatosPersonales() {
        return proteccionDatosPersonales;
    }

    public void setProteccionDatosPersonales(String proteccionDatosPersonales) {
        this.proteccionDatosPersonales = proteccionDatosPersonales;
    }

    public String getEnvioComunicacionesComerciales() {
        return envioComunicacionesComerciales;
    }

    public void setEnvioComunicacionesComerciales(String envioComunicacionesComerciales) {
        this.envioComunicacionesComerciales = envioComunicacionesComerciales;
    }
}
