package canchamanager.grupo12.upn.model;

public class CanchaDeporte {
    private int id;
    private int canchaId;
    private int deporteId;

    public CanchaDeporte(int id, int canchaId, int deporteId) {
        this.id = id;
        this.canchaId = canchaId;
        this.deporteId = deporteId;
    }

    public CanchaDeporte(int canchaId, int deporteId) {
        this.canchaId = canchaId;
        this.deporteId = deporteId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCanchaId() { return canchaId; }
    public void setCanchaId(int canchaId) { this.canchaId = canchaId; }

    public int getDeporteId() { return deporteId; }
    public void setDeporteId(int deporteId) { this.deporteId = deporteId; }
}
