# canchamanager

Integrantes:
-----------

SERGIO ZEGARRA CORNE | N00399038 

FRANKLIN ELBER PEÑA ARMÉSTAR | N00445850 

GERSON MIRANDA CRUZ | N00338407 

RENSO CURIÑAUPA CONTRERAS | N00391576



src/
├── canchamanager.grupo12.upn.model/
│   ├── Cliente.java
│   ├── Cancha.java
│   ├── Deporte.java              <-- ✅ NUEVO
│   ├── CanchaDeporte.java        <-- ✅ NUEVO (relación cancha ↔ deporte)
│   ├── Horario.java              <-- ✅ NUEVO (horarios + variación precio)
│   └── Reserva.java

├── canchamanager.grupo12.upn.dao/
│   ├── ConexionDB.java
│   ├── IGestorClientes.java
│   ├── GestorClientesMySQL.java
│   ├── IGestorCanchas.java
│   ├── GestorCanchasMySQL.java
│   ├── IGestorDeportes.java      <-- ✅ NUEVO
│   ├── GestorDeportesMySQL.java  <-- ✅ NUEVO
│   ├── IGestorCanchasDeportes.java <-- ✅ NUEVO
│   ├── GestorCanchasDeportesMySQL.java <-- ✅ NUEVO
│   ├── IGestorHorarios.java      <-- ✅ NUEVO
│   ├── GestorHorariosMySQL.java  <-- ✅ NUEVO
│   ├── IGestorReservas.java
│   └── GestorReservasMySQL.java

├── canchamanager.grupo12.upn.controller/
│   ├── ClienteController.java
│   ├── CanchaController.java
│   ├── DeporteController.java    <-- ✅ NUEVO
│   ├── CanchaDeporteController.java <-- ✅ NUEVO
│   ├── HorarioController.java    <-- ✅ NUEVO
│   └── ReservaController.java

├── canchamanager.grupo12.upn.gui/
│   ├── Login.java
│   ├── PanelPrincipal.java
│   ├── PanelClientes.java
│   ├── PanelReservas.java
│   ├── PanelCanchas.java
│   ├── GestionCanchasFrame.java
│   ├── GestionCanchasDeportesFrame.java <-- ✅ NUEVO
│   ├── GestionHorariosFrame.java        <-- ✅ NUEVO
│   └── GestionDeportesFrame.java        <-- ✅ NUEVO (opcional para CRUD deportes)

└── util/
    ├── TemaUtil.java
    ├── ConexionMonitor.java
    └── ConfigUtil.java
