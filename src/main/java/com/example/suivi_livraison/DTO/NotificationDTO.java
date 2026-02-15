package com.example.suivi_livraison.DTO;

import java.time.LocalDateTime;

public class NotificationDTO {
    private Long id;
    private LocalDateTime dateEnvoi;
    private String destinataire;
    private String message;
    private boolean lue;
    private String type;
    private Long userId;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getDateEnvoi() { return dateEnvoi; }
    public void setDateEnvoi(LocalDateTime dateEnvoi) { this.dateEnvoi = dateEnvoi; }
    public String getDestinataire() { return destinataire; }
    public void setDestinataire(String destinataire) { this.destinataire = destinataire; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public boolean isLue() { return lue; }
    public void setLue(boolean lue) { this.lue = lue; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
